package com.shopadmin.service;

import java.util.List;

import com.shopadmin.myapp.Board2VO;
import com.shopadmin.myapp.PageDTO;

public interface Board2Service {
	
	public Board2VO read(Board2VO board);	// 단일 레코드 조회
	
	public Board2VO read(int b_num);
	
	public void insert(Board2VO board);	// 레코드 입력
	
	public List<Board2VO> getList(PageDTO page);	// 레코드 리스트
	
	public void update(Board2VO board);	// 수정
	
	public void delete(Board2VO board);	// 삭제
	
	public int getTotalCount();

}
