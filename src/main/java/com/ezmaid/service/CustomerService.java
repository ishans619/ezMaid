package com.ezmaid.service;

import java.util.List;

import com.ezmaid.entity.Customer;

public interface CustomerService {
	
	public Customer saveCustomer(Customer customer);
	public Customer fetchOne(String custId);
	public void deleteOne(String custId);
	public List<Customer> fetchAll();
	public String updateCustomer(Customer existingCustomer);

}
