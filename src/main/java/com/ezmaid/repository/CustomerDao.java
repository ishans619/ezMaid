package com.ezmaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmaid.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {

}
