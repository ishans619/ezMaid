package com.ezmaid.service;

import java.util.List;

import com.ezmaid.dto.RequestDTO;
import com.ezmaid.entity.Request;
import com.ezmaid.entity.RequestDetail;

public interface RequestService {

	public Request save(RequestDTO requestDTO);
	
	public List<Request> fetchAll();
	public List<Request> fetchAllByCustomer();
	public List<Request> fetchAllByCustomer(String customerId);
	
	public Request fetchOne(String rqstId);
	public String updateRequest(Request existingRequest);
	
	public List<Request> fetchAllByMaid(String maidId);
	public List<Request> fetchAllByMaid();
	
	public List<RequestDetail> fetchAllRequestDetailsByMaid(String maidId);
	public List<Request> fetchAllRequestDetailsOfThisMaid(String maidId);
}
