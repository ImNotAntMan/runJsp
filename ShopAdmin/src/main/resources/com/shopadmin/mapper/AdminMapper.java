package com.shopadmin.mapper;

import java.util.List;

import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.PageDTO;

public interface AdminMapper {

	public List<AdminVO> getList(PageDTO page);
	
	public AdminVO read(AdminVO admin);
	
	public void insert(AdminVO admin);
	
	public void update(AdminVO admin);
	
	public void delete(AdminVO admin);
	
	public AdminVO auth(AdminVO admin);
	
	public int getTotalCount();

	public AdminVO read(String a_id);
}
