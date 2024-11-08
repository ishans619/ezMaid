package com.ezmaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmaid.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

}
