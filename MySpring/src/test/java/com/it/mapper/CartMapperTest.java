package com.it.mapper;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.CartdetailDTO;
import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CartMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private CartMapper mapper;
	
	//@Test
	public void testGetListMain() {
		mapper.getListMain().forEach(product -> log.info(product));
	}
	
	//@Test
	public void testInsertMain() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setM_id("hama");
		mapper.insertMain(cartmain);
	}
	
	//@Test
	public void testInsert() {
		CartsubVO cartsub = new CartsubVO();
		cartsub.setCm_code(1004);
		cartsub.setP_code(1003);
		cartsub.setCs_cnt(4);
		mapper.insertSub(cartsub);
		log.info(cartsub);
		cartsub.setCm_code(1004);
		cartsub.setP_code(1004);
		cartsub.setCs_cnt(44);
		mapper.insertSub(cartsub);
		log.info(cartsub);
		testGetListMain();
	}
	
	//@Test
	public void testDeleteSub() {
		CartsubVO cartsub = new CartsubVO();
		cartsub.setCs_code(1010);
		mapper.deleteSub(cartsub);
	}
	
	//@Test
	public void testDeleteMain() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1003);
		mapper.deleteMain(cartmain);
	}
	
	//@Test
	public void testCartdetailTest() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1005);
		List<CartdetailDTO> cartdetail = mapper.getListCartDetail(cartmain);
		cartdetail.forEach(cd -> log.info(cd));
	}
	
	//@Test
	public void testCartTotalTest() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1005);
		CartmemberDTO cartmember = mapper.getCartTotal(cartmain);
		log.info(cartmember);
	}
}
