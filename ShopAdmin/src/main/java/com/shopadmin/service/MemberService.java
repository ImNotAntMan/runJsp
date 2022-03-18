package com.shopadmin.service;

import java.util.List;

import com.shopadmin.myapp.MemberVO;
import com.shopadmin.myapp.PageDTO;

public interface MemberService {
	public List<MemberVO> getList(PageDTO page);
	
	public MemberVO read(MemberVO member);
	
	public void insert(MemberVO member);
	
	public void update(MemberVO member);
	
	public void delete(MemberVO member);
	
	public int getTotalCount();

}
