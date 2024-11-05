package com.ezmaid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezmaid.entity.Maid;

@Repository
public interface MaidDao extends JpaRepository<Maid, String> {
	public List<Maid> findByIsVerifiedTrue();
}
