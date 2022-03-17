package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.PageDTO;
import com.it.mapper.NoticeMapper;
import com.it.notice.NoticeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeMapper mapper;
	
	@Override
	public List<NoticeVO> getList(PageDTO page) {
		return mapper.getList();
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public void insert(NoticeVO notice) {
		mapper.insert(notice);
	}
	
	@Override
	public NoticeVO read(NoticeVO notice) {
		return mapper.read(notice);
	}
	
	@Override
	public void update(NoticeVO notice) {
		mapper.update(notice);
	}
	
	@Override
	public void delete(NoticeVO notice) {
		mapper.delete(notice);
	}
}
