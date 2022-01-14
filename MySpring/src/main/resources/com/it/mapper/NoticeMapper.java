package com.it.mapper;

import java.util.List;

import com.it.notice.NoticeVO;

public interface NoticeMapper {

	public List<NoticeVO> getList();
	
	public void insert(NoticeVO notice);
	
	public NoticeVO read(NoticeVO notice);
	
	public void update(NoticeVO notice);
	
	public void delete(NoticeVO notice);
}
