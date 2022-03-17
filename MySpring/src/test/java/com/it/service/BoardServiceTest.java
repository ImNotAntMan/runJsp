package com.it.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	//@Test
	public void testRead() {
		BoardVO board = new BoardVO();
		board.setB_num(10);
		board = service.read(board);
		log.info(board);
	}
	
	//@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));// 레코드만큼 반복하는 람다식
	}
	
	//@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setB_subject("게시판 입력 테스트당ㅇㅇㅇㅇㅇ...");
		board.setB_name("홍길동");
		board.setB_contents("테스트아니당ㅇㅇㅇㅇㅇㅇ");
		service.insert(board);
		log.info(board);
	}
	
	//@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setB_num(12);
		board.setB_subject("수정입니당......");
		board.setB_name("홍범도");
		board.setB_contents("오누ㅡㄹ도 추운 날 즐거운 날이에요..~!~!!!!!");
		service.update(board);
		log.info(board);
	}
	
	//@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setB_num(14);
		service.delete(board);
	}
}
