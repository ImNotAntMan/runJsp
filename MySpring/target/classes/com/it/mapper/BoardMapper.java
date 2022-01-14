package com.it.mapper;

import java.util.List;

import com.it.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList();
	
	public void insert(BoardVO  board);
	
	public BoardVO read(BoardVO board);
	
	public void update(BoardVO board);
	
	public void delete(BoardVO board);
}
