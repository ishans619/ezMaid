package com.ezmaid.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezmaid.entity.Customer;
import com.ezmaid.exception.CustomerNotFoundException;
import com.ezmaid.repository.CustomerDao;
import com.ezmaid.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	

	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		customer.setIsFirstLogin(true);
		customer.setIsVerified(false);
		customer.setCrtdDate(LocalDate.now());
		customer.setLstUpdtDate(LocalDate.now());
		customer.setLstUpdtBy("system");
		
		customer = customerDao.save(customer);
		return customer;
	}
	
	
	@Override
	public Customer fetchOne(String customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("No customer record found with the provided ID: "+customerId);
		}
		return customer.get();
	}


	@Override
	public void deleteOne(String customerId) {
		Customer customer = fetchOne(customerId);
		customerDao.deleteById(customer.getCustomerId());
	}


	@Override
	public List<Customer> fetchAll() {
		return customerDao.findAll();
	}


	@Override
	public String updateCustomer(Customer existingCustomer) {
		existingCustomer = customerDao.save(existingCustomer);
		return existingCustomer.getCustomerId();
	}

	
}
