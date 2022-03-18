package com.shopadmin.mapper;

import java.util.List;

import com.shopadmin.myapp.Board2VO;
import com.shopadmin.myapp.PageDTO;

public interface Board2Mapper {
	
	public List<Board2VO> getList(PageDTO page);
	
	public void insert(Board2VO  board);
	
	public Board2VO read(Board2VO board);
	
	public Board2VO read(int b_num);
	
	public void update(Board2VO board);
	
	public void delete(Board2VO board);
	
	public int getTotalCount();
}
