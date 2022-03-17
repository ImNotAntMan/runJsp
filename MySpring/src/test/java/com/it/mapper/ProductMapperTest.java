package com.it.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.ProductVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProductMapperTest {

	@Setter(onMethod_ = @Autowired)
	private ProductMapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(product -> log.info(product));
	}
	
	//@Test
	public void testInsert() {
		ProductVO product = new ProductVO();
		product.setP_name("계산기");
		product.setP_price(1300);
		mapper.insert(product);
		log.info(product);
		testGetList();
	}
	
	//@Test
	public void testRead() {
		ProductVO product = new ProductVO();
		product.setP_code(1002);	// 1을 전달 VO 객체에 저장
		product = mapper.read(product);	// read 메소드 호출해서 객체 변환
		log.info(product);
	}

}
