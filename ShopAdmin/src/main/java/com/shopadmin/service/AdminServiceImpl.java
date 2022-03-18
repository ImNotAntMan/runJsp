package com.shopadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopadmin.mapper.AdminMapper;
import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AdminServiceImpl implements AdminService {

	@Setter(onMethod_ = @Autowired)
	private AdminMapper mapper;
	
	@Override
	public List<AdminVO> getList(PageDTO page) {
		return mapper.getList(page);
	}
	
	@Override
	public AdminVO read(AdminVO admin) {
		return mapper.read(admin);
	}

	@Override
	public AdminVO read(String a_id) {
		return mapper.read(a_id);
	}
	
	@Override
	public void insert(AdminVO admin) {
		mapper.insert(admin);
	}
	
	@Override
	public void update(AdminVO admin) {
		mapper.update(admin);
	}
	
	@Override
	public void delete(AdminVO admin) {
		mapper.delete(admin);
	}
	
	@Override
	public Boolean auth(AdminVO admin) {
		AdminVO tmp;
		tmp = mapper.read(admin);
		if(tmp != null) {	// 아이디가 틀린 경우.
			log.info(tmp);
			log.info(admin);
			log.info("인증 성공");
			return true;
		} else {	// 아이디는 이치하나 암호가 일치하지 않는 경우
			log.info(tmp);
			log.info(admin);
			log.info("아이디는 동일하나 암호가 틀림");
			return false;
		}
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
}
