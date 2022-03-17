package com.it.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.notice.NoticeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeMapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(notice -> log.info(notice));		
	}
	
	//@Test
	public void testInsert() {
		NoticeVO notice = new NoticeVO();
		notice.setN_subject("명량대첩입니당....");
		notice.setN_name("이순신");
		notice.setN_contents("이겼습니당......VVVVVVVVVVVVVVVVVVVVVVV");
		mapper.insert(notice);
	}
	
	//@Test
	public void testRead() {
		NoticeVO notice = new NoticeVO(); 
		notice.setN_num(2);
		notice = mapper.read(notice);
		log.info(notice);
	}
	
	//@Test
	public void testUpdate() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(2);
		notice.setN_subject("두번째 공지사항입니다.");
		notice.setN_name("부운영자");
		notice.setN_contents("반드시 공지를 지켜주세요 안 그럼 악플답니다.");
		mapper.update(notice);
	}
	
	//@Test
	public void testDelete() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(3);
		mapper.delete(notice);
	}
}
