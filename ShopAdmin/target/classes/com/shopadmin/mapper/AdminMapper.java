package com.shopadmin.mapper;

import com.shopadmin.domain.AdminVO;

public interface AdminMapper {

	public AdminVO auth(AdminVO admin);
	
	public AdminVO read(AdminVO admin);
	
}
