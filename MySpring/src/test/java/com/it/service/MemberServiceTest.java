package com.it.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	
	//@Test
	public void testAuth() {
		MemberVO member = new MemberVO();
		member.setM_id("hama");
		member.setM_passwd("1234");
		service.auth(member);
	}
	
	//@Test
	public void testRead() {
		MemberVO member = new MemberVO();
		member.setM_id("myDolphine");
		member = service.read(member);
		log.info(member);
	}
	
	//@Test
	public void testGetList() {
		service.getList().forEach(member -> log.info(member));// 레코드만큼 반복하는 람다식
	}
	
	//@Test
	public void testInsert() {
		MemberVO member = new MemberVO();
		member.setM_id("mydog");
		member.setM_passwd("1234");
		member.setM_name("돌돌이");
		service.insert(member);
		log.info(member);
	}
	
	//@Test
	public void testUpdate() {
		MemberVO member = new MemberVO();
		member.setM_id("mydog");
		member.setM_passwd("4321");
		member.setM_name("호랑이");;
		service.update(member);
		log.info(member);
	}
	
	//@Test
	public void testDelete() {
		MemberVO member = new MemberVO();
		member.setM_id("mydog");
		service.delete(member);
	}

}
