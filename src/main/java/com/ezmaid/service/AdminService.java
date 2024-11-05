package com.ezmaid.service;

import java.util.List;

import com.ezmaid.entity.Admin;

public interface AdminService {
	
	public String saveAdmin(Admin admin);
	public Admin fetchOne(String adminId);
	public String deleteOne(String adminId);
	public List<Admin> fetchAll();
	public String updateAdmin(Admin existingAdmin);

}
