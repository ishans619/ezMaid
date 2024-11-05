package com.ezmaid.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.RequestDTO;
import com.ezmaid.dto.RequestDetailDTO;
import com.ezmaid.entity.Customer;
import com.ezmaid.entity.Maid;
import com.ezmaid.entity.Request;
import com.ezmaid.entity.RequestDetail;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.exception.RequestFinalizeException;
import com.ezmaid.repository.RequestDetailDao;
import com.ezmaid.service.CustomerService;
import com.ezmaid.service.MaidService;
import com.ezmaid.service.RequestService;
import com.ezmaid.service.UtilityService;
import com.ezmaid.util.AppConstants;
import com.ezmaid.util.AppUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
public class RequestController {

	private static final Logger log = LoggerFactory.getLogger(RequestController.class);

	private RequestService requestService;
	private RequestDetailDao requestDetailDao;
	private UtilityService utilityService;
	private CustomerService customerService;
	private MaidService maidService;

	public RequestController(RequestService requestService, RequestDetailDao requestDetailDao, 
			UtilityService utilityService, MaidService maidService, CustomerService customerService) {
		super();
		this.requestService = requestService;
		this.requestDetailDao = requestDetailDao;
		this.utilityService = utilityService;
		this.maidService = maidService;
		this.customerService = customerService;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PostMapping(path = "/requests")
	public String save(@Valid @RequestBody RequestDTO requestDTO) {
		System.out.println("requestDTO = "+ requestDTO);

		Request savedRequest = requestService.save(requestDTO);

		return savedRequest.getRqstId();
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/cancel")
	public String cancelRequest(@RequestBody RequestDTO requestDTO) {

		Request existingRequest = requestService.fetchOne(requestDTO.getRqstId());

		existingRequest.setIsCancelled(true);
		existingRequest.setLstUpdtDate(LocalDate.now());

		return requestService.updateRequest(existingRequest);
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/finalize")
	public String finalizeRequest(@RequestBody RequestDTO requestDTO) {

		Request existingRequest = requestService.fetchOne(requestDTO.getRqstId());

		existingRequest.setIsFinalized(true);
		existingRequest.setStatus(AppConstants.REQUEST_TYPE_CLOSE);
		existingRequest.setLstUpdtDate(LocalDate.now());

		for (RequestDetail detail : existingRequest.getRequestDetail()) {

			if (requestDTO.getMaidIds().contains(detail.getMaid().getMaidId())) {
				if (!detail.getIsAccepted() && !detail.getIsRejected()) {
					// means request from maid side is pending, so can't finalize
					// return exception
					throw new RequestFinalizeException("Request is pending at maid side, so can't finalize!");
				}
				if (!detail.getIsAccepted() && detail.getIsRejected()) {
					// means request from maid side is rejected, so can't finalize
					// return exception
					throw new RequestFinalizeException("Request is reject by maid, so can't finalize!");
				}
				detail.setIsCustAccepted(true);
				detail.setIsCustRejected(false);

			} else {
				detail.setIsCustAccepted(false);
				detail.setIsCustRejected(true);
			}

			detail.setLstUpdtDate(LocalDate.now());
			detail.setLstUpdtBy(utilityService.getLoggedInUserId());
		} 

		return requestService.updateRequest(existingRequest);
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/fulfill")
	public String fulfillRequest(@RequestBody RequestDTO requestDTO) {

		List<String> maidList = new ArrayList<>();

		Request existingRequest = requestService.fetchOne(requestDTO.getRqstId());

		existingRequest.setIsFulfilled(true);
		existingRequest.setLstUpdtDate(LocalDate.now());

		for (RequestDetail detail : existingRequest.getRequestDetail()) {

			if (requestDTO.getMaidIds().contains(detail.getMaid().getMaidId())) {

				detail.setMaidRating(requestDTO.getMaidRatings().get(requestDTO.getMaidIds().indexOf(detail.getMaid().getMaidId())));
				detail.setLstUpdtDate(LocalDate.now());
				detail.setLstUpdtBy(utilityService.getLoggedInUserId());

				maidList.add(detail.getMaid().getMaidId());
			} 
		} 

		String rqstId = requestService.updateRequest(existingRequest);

		aggregateAndUpdateRatingOfThisMaid(maidList);

		return rqstId;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/updateRatings")
	public String updateRatings(@RequestBody RequestDTO requestDTO) {

		List<String> maidList = new ArrayList<>();

		Request existingRequest = requestService.fetchOne(requestDTO.getRqstId());

		for (RequestDetail detail : existingRequest.getRequestDetail()) {

			if (requestDTO.getMaidIds().contains(detail.getMaid().getMaidId())) {

				detail.setMaidRating(requestDTO.getMaidRatings().get(requestDTO.getMaidIds().indexOf(detail.getMaid().getMaidId())));
				detail.setLstUpdtDate(LocalDate.now());
				detail.setLstUpdtBy(utilityService.getLoggedInUserId());

				maidList.add(detail.getMaid().getMaidId());
			} 
		} 

		String rqstId = requestService.updateRequest(existingRequest);

		aggregateAndUpdateRatingOfThisMaid(maidList);

		return rqstId;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/customers/ratings")
	public String customerRatings(@RequestBody RequestDTO requestDTO) {

		Request existingRequest = requestService.fetchOne(requestDTO.getRqstId());

		existingRequest.setCustRating(requestDTO.getCustRating());

		String rqstId = requestService.updateRequest(existingRequest);

		aggregateAndUpdateRatingOfThisCustomer(existingRequest.getCustomer().getCustomerId());

		return rqstId;
	}

	private void aggregateAndUpdateRatingOfThisCustomer(String custId) {

		if (!custId.isEmpty()) {
			int count = 0;
			float aggregateRating = 0.0F;
			List<Float> ratings = new ArrayList<>();

			List<Request> custRequests = requestService.fetchAllByCustomer(custId);

			for (Request request : custRequests) {
				if(request.getCustRating()!=null) {
					count++;
					ratings.add(request.getCustRating());
				}
			}

			float sum = (float) ratings.stream().mapToDouble(Float::doubleValue).sum();
			aggregateRating = sum/count;

			Customer existingCustomer = customerService.fetchOne(custId);
			existingCustomer.setRating(aggregateRating);
			customerService.updateCustomer(existingCustomer);
		}
	}

	private void aggregateAndUpdateRatingOfThisMaid(List<String> maidIdList) {

		if (!maidIdList.isEmpty()) {
			for (String maidId : maidIdList) {

				int count = 0;
				float aggregateRating = 0.0F;
				List<Float> ratings = new ArrayList<>();

				List<Request> maidRequests = requestService.fetchAllByMaid(maidId);

				for (Request request : maidRequests) {
					for (RequestDetail detail: request.getRequestDetail()) {
						if(detail.getMaidRating()!=null) {
							count++;
							ratings.add(detail.getMaidRating());
						}
					}
				}

				float sum = (float) ratings.stream().mapToDouble(Float::doubleValue).sum();
				aggregateRating = sum/count;

				Maid existingMaid = maidService.fetchOne(maidId);
				existingMaid.setRating(aggregateRating);
				maidService.updateMaid(existingMaid);
			}
		}
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/requests/customers")
	public List<Request> fetchByLoggedInCustomer() {
		return requestService.fetchAllByCustomer(); 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/requests/maids")
	public List<Request> fetchByLoggedInMaid() {
		return requestService.fetchAllByMaid();
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/requests/maids/{maidId}")
	public List<Request> tempMethod(@PathVariable String maidId) {
		return requestService.fetchAllByMaid(maidId);
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/details/maids/accept")
	public Boolean maidAcceptingRqst(@RequestBody RequestDetailDTO requestDetailDTO) {

		Optional<RequestDetail> existingRqstDtl = requestDetailDao.findById(requestDetailDTO.getId());

		if (!existingRqstDtl.isEmpty()) {
			existingRqstDtl.get().setIsAccepted(true);
			existingRqstDtl.get().setIsRejected(false);
		} 

		requestDetailDao.save(existingRqstDtl.get());

		return true;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/requests/details/maids/reject")
	public Boolean maidRejectingRqst(@RequestBody RequestDetailDTO requestDetailDTO) {

		Optional<RequestDetail> existingRqstDtl = requestDetailDao.findById(requestDetailDTO.getId());

		if (!existingRqstDtl.isEmpty()) {
			existingRqstDtl.get().setIsAccepted(false);
			existingRqstDtl.get().setIsRejected(true);
		} 

		requestDetailDao.save(existingRqstDtl.get());

		return true;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

		StringBuilder sbError = new StringBuilder();

		ex.getBindingResult().getAllErrors().forEach(AppUtils.fetchErrors(sbError));

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				AppConstants.VALIDATION_FAILED_DETAIL, sbError.toString()); 

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}

}
