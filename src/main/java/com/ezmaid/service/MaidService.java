package com.ezmaid.service;

import java.util.List;

import com.ezmaid.entity.Maid;

public interface MaidService {
	
	public Maid saveMaid(Maid maid); 
	public Maid fetchOne(String maidId);
	public void deleteOne(String maidId);
	public List<Maid> fetchAll();
	public List<Maid> fetchAllVerified();
	public String updateMaid(Maid existingMaid);
}


