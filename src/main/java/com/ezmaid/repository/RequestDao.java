package com.ezmaid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmaid.entity.Customer;
import com.ezmaid.entity.Request;
import com.ezmaid.entity.RequestDetail;

@Repository
public interface RequestDao extends JpaRepository<Request, String> {

	List<Request> findByCustomer(Customer customer);
	Request findByRequestDetail(RequestDetail detail);

	List<Request> findByRequestDetailMaidMaidId(String maidId);
}
