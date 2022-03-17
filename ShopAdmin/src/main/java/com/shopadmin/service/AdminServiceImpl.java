package com.shopadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopadmin.domain.AdminVO;
import com.shopadmin.mapper.AdminMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AdminServiceImpl implements AdminService {
	@Setter(onMethod_ = @Autowired)
	private AdminMapper mapper;
	
	@Override
	public AdminVO read(AdminVO admin) {
		return mapper.read(admin);
	}
	
	@Override
	public Boolean auth(AdminVO admin) {
		AdminVO tmp;
		tmp = mapper.read(admin);
		if(tmp != null) {
			log.info(tmp);
			log.info(admin);
			log.info("인증 성공");
			return true;
		} else {
			log.info(admin);
			log.info("아이디가 존재하지 않음.");
			return false;
		}
	}
//	
//	@Override
//	public void insert(AdminVO admin) {
//		mapper.insert(admin);
//	}
//	
//	@Override
//	public List<AdminVO> getList() {
//		return mapper.getList();
//	}
	
//	@Override
//	public void update(AdminVO admin) {
//		mapper.update(admin);
//	}
//	
//	@Override
//	public void delete(AdminVO admin) {
//		mapper.delete(admin);
//	}
//
//	@Override
//	public AdminVO read(String a_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
