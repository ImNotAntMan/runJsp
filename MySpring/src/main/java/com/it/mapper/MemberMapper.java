package com.it.mapper;

import java.util.List;

import com.it.domain.MemberVO;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	public void insert(MemberVO member);
	
	public MemberVO read(MemberVO member);
	
//	public MemberVO auth(MemberVO member);
	
	public void update(MemberVO member);
	
	public void delete(MemberVO member);
}
