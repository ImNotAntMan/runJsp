package com.shopadmin.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shopadmin.myapp.AdminVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AdminServiceTest {

	@Setter(onMethod_ = @Autowired)
	private AdminService service;
	
	//@Test
	public void testRead() {
		AdminVO admin = new AdminVO();
		admin.setA_id("admin");
		log.info(admin);
		admin = service.read("admin");
		log.info(admin);
	}
	
	//@Test
	public void testAuth() {
		AdminVO admin = new AdminVO();
		admin.setA_id("admin");
		admin.setA_passwd("1234");
		service.auth(admin);
		admin = service.read(admin);
		log.info(admin);
	}
	
	//@Test
	public void testInsert() {
		AdminVO admin = new AdminVO();
		admin.setA_id("HotDog");
		admin.setA_passwd("1234");
		admin.setA_name("영업부");
		service.insert(admin);
		log.info(service.read(admin));
	}
}
