package com.ezmaid.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezmaid.dto.RequestDTO;
import com.ezmaid.entity.Request;
import com.ezmaid.entity.RequestDetail;
import com.ezmaid.exception.RequestNotFoundException;
import com.ezmaid.repository.RequestDao;
import com.ezmaid.repository.RequestDetailDao;
import com.ezmaid.service.CustomerService;
import com.ezmaid.service.MaidService;
import com.ezmaid.service.RequestService;
import com.ezmaid.service.UtilityService;
import com.ezmaid.util.AppConstants;

import jakarta.persistence.EntityManager;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private EntityManager entityManager;
	
	private UtilityService utilityService;
	private CustomerService customerService;
	private MaidService maidService;
	private RequestDao requestDao;
	private RequestDetailDao requestDetailDao;

	public RequestServiceImpl(UtilityService utilityService, CustomerService customerService, MaidService maidService, RequestDao requestDao, RequestDetailDao requestDetailDao) {
		this.utilityService = utilityService;
		this.customerService = customerService;
		this.maidService = maidService;
		this.requestDao = requestDao;
		this.requestDetailDao = requestDetailDao;
	} 

	@Override
	public Request save(RequestDTO requestDTO) {

		Request newRequest = new Request();

		newRequest.setRqstType(requestDTO.getRqstType());
		newRequest.setRqstTitle(requestDTO.getRqstTitle());
		newRequest.setRqstDescription(requestDTO.getRqstDesc());
		newRequest.setIsFulfilled(false);
		newRequest.setIsCancelled(false);
		newRequest.setIsFinalized(false);
		newRequest.setStatus(AppConstants.REQUEST_TYPE_OPEN);
		newRequest.setCrtdDate(LocalDate.now());
		newRequest.setLstUpdtDate(LocalDate.now());
		newRequest.setCustomer(customerService.fetchOne(utilityService.getLoggedInUserId()));

		newRequest.setRequestDetail(new ArrayList<RequestDetail>());

		for (String maidId: requestDTO.getMaidIds()) {

			RequestDetail details = new RequestDetail();
			details.setIsAccepted(false);
			details.setIsRejected(false);
			details.setIsCustAccepted(false);
			details.setIsCustRejected(false);
			details.setIsAbsent(false);

			details.setMaid(maidService.fetchOne(maidId));

			details.setLstUpdtDate(LocalDate.now());
			details.setLstUpdtBy(utilityService.getLoggedInUserId());

			details.setRequest(newRequest);

			newRequest.getRequestDetail().add(details);
		}

		Request savedRequest = requestDao.save(newRequest);

		return savedRequest;
	}

	@Override
	public List<Request> fetchAll() {
		return requestDao.findAll();
	}

	@Override
	public List<Request> fetchAllByCustomer() {
		return requestDao.findByCustomer(customerService.fetchOne(utilityService.getLoggedInUserId()));
	}

	@Override
	public List<Request> fetchAllByMaid() {
		List<Request> daoRqst = requestDao.findByRequestDetailMaidMaidId(utilityService.getLoggedInUserId());
//		List<Request> daoRqst = requestDao.findByRequestDetailMaidMaidIdCustom(utilityService.getLoggedInUserId());

		for (Request request : daoRqst) {
			// removing request object from customer
			request.getCustomer().setRequest(null);

			// keeping only request detail object in request, whose maid id matches the current maid id
			List<RequestDetail> daoRqstDtl = request.getRequestDetail();
			List<RequestDetail> dtoRqstDtl = new ArrayList<>();

			for (RequestDetail rqstDtl : daoRqstDtl) {

				rqstDtl.getMaid().setRequestDetail(null);

				if (rqstDtl.getMaid().getMaidId().equals(utilityService.getLoggedInUserId())) {
					dtoRqstDtl.add(rqstDtl);
				}
			}

			request.setRequestDetail(null);
			request.setRequestDetail(dtoRqstDtl);
		}

		return daoRqst;
	}
	
	@Override
	public List<Request> fetchAllByMaid(String maidId) {
		entityManager.clear();
		List<Request> daoRqst = requestDao.findByRequestDetailMaidMaidId(maidId);

		for (Request request : daoRqst) {
			// removing request object from customer
			request.getCustomer().setRequest(null);

			// keeping only request detail object in request, whose maid id matches the current maid id
			List<RequestDetail> daoRqstDtl = request.getRequestDetail();
			List<RequestDetail> dtoRqstDtl = new ArrayList<>();

			for (RequestDetail rqstDtl : daoRqstDtl) {

				rqstDtl.getMaid().setRequestDetail(null);

				if (rqstDtl.getMaid().getMaidId().equals(maidId)) {
					dtoRqstDtl.add(rqstDtl);
				}
			}

			request.setRequestDetail(null);
			request.setRequestDetail(dtoRqstDtl);
		}

		return daoRqst;
	}

	@Override
	public Request fetchOne(String rqstId) {
		Optional<Request> request =  requestDao.findById(rqstId);
		if(request.isEmpty()) {
			throw new RequestNotFoundException("No request record found with the provided ID: " + rqstId);
		}
		return request.get();
	}

	@Override
	public String updateRequest(Request existingRequest) {
		existingRequest = requestDao.save(existingRequest);
		return existingRequest.getRqstId();
	}

	@Override
	public List<Request> fetchAllRequestDetailsOfThisMaid(String maidId) {
		return requestDao.findByRequestDetailMaidMaidId(maidId);
	}

	@Override
	public List<RequestDetail> fetchAllRequestDetailsByMaid(String maidId) {
		return requestDetailDao.findByMaid_MaidId(maidId);
	}

	@Override
	public List<Request> fetchAllByCustomer(String customerId) {
		return requestDao.findByCustomer(customerService.fetchOne(customerId));
	}



}
