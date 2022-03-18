package com.shopadmin.service;

import java.util.List;

import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.PageDTO;

public interface AdminService {
	public List<AdminVO> getList(PageDTO page);
	
	public AdminVO read(AdminVO admin);
	
	public AdminVO read(String a_id);
	
	public void insert(AdminVO admin);
	
	public void update(AdminVO admin);
	
	public void delete(AdminVO admin);
	
	public Boolean auth(AdminVO admin);
	
	public int getTotalCount();
}
