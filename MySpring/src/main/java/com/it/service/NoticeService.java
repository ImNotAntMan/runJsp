package com.it.service;

import java.util.List;

import com.it.domain.PageDTO;
import com.it.notice.NoticeVO;

public interface NoticeService {
	
	public List<NoticeVO> getList(PageDTO page);
	
	public int getTotalCount();
	
	public NoticeVO read(NoticeVO notice);
	
	public void insert(NoticeVO notice);
	
	public void update(NoticeVO notice);
	
	public void delete(NoticeVO notice);
}
