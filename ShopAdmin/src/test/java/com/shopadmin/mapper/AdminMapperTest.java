package com.shopadmin.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AdminMapperTest {

	@Setter(onMethod_ =  @Autowired)
	private AdminMapper mapper;
	
	//@Test
	public void testRead() {
		AdminVO admin = new AdminVO();
		admin.setA_id("admin");
		admin = mapper.read(admin);
		log.info(admin);
	}
	
	//@Test
	public void testGetList() {
		AdminVO admin = new AdminVO();
		PageDTO page = new PageDTO();
		page.setPageAmount(10);
		page.setPageNum(1);
		System.out.println(mapper.getList(page));
	}
}
