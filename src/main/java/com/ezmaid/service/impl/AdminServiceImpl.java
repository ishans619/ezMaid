package com.ezmaid.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezmaid.entity.Admin;
import com.ezmaid.exception.AdminNotFoundException;
import com.ezmaid.repository.AdminDao;
import com.ezmaid.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;

	public AdminServiceImpl(AdminDao adminDao) {
		super();
		this.adminDao = adminDao;
	}

	@Override
	public String saveAdmin(Admin admin) {
		admin.setCrtdDate(LocalDate.now());
		admin.setLstUpdtDate(LocalDate.now());
		admin.setIsSuperAdmin(false);
		
		admin = adminDao.save(admin);
		return admin.getAdminId();
	}
	
	
	@Override
	public Admin fetchOne(String adminId) {
		Optional<Admin> admin =  adminDao.findById(adminId);
		if(admin.isEmpty()) {
			throw new AdminNotFoundException("No admin record found with the provided ID: "+adminId);
		}
		return admin.get();
	}


	@Override
	public String deleteOne(String adminId) {
		String status = "Deleted";
		Admin admin = fetchOne(adminId);
		if(!admin.getIsSuperAdmin()) {
			adminDao.deleteById(adminId);
		}
		else {
			status = "Super Admin cannot be deleted";
		}
		return status;
	}


	@Override
	public List<Admin> fetchAll() {
		return adminDao.findAll();
	}


	@Override
	public String updateAdmin(Admin existingAdmin) {
		existingAdmin = adminDao.save(existingAdmin);
		return existingAdmin.getAdminId();
	}

	
}
