package com.ezmaid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezmaid.entity.Maid;
import com.ezmaid.entity.RequestDetail;

public interface RequestDetailDao extends JpaRepository<RequestDetail, Long> {

	List<RequestDetail> findByMaid(Maid maid);
	List<RequestDetail> findByMaid_MaidId(String maidId);
}
