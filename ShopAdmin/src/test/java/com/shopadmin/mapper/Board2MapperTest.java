package com.shopadmin.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shopadmin.myapp.Board2VO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class Board2MapperTest {

	@Setter(onMethod_ = @Autowired)
	private Board2Mapper mapper;
	
	//@Test
	public void testRead() {
		Board2VO board = new Board2VO();
		board.setB_num(3575);
		board = mapper.read(board);
		log.info(board);
	}
}
