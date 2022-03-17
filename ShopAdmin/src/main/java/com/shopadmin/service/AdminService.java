package com.shopadmin.service;

import org.springframework.stereotype.Service;

import com.shopadmin.domain.AdminVO;

@Service
public interface AdminService {
	
	public AdminVO read(AdminVO admin);	// 단일 레코드 조회
	
//	public AdminVO read(String a_id);
	
	public Boolean auth(AdminVO admin);	// 아이디와 암호를 전달하여 인증 처리 T or F
	
//	public void insert(AdminVO admin);	// 레코드 입력
//	
//	public List<AdminVO> getList();	// 레코드 리스트
//	
//	public void update(AdminVO admin);	// 수정
//	
//	public void delete(AdminVO admin);	// 삭제

}
