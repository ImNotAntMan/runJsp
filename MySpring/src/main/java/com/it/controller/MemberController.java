package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.MemberVO;
import com.it.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/")
public class MemberController {
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@GetMapping("/list")
	public void list(Model model) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		model.addAttribute("list", service.getList());	// getList로 조회한 내용을 리스트 변수로 전달model 객체
	}
	
	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public String insert(MemberVO member) {
		log.info(member);
		service.insert(member);
		return "redirect:/member/list";
	}
	
	@GetMapping("/read")
	public void read(Model model, MemberVO member) {
		log.info("___________________읽기 전_________________________");
		log.info(member);
		member = service.read(member);
		log.info("___________________읽은 후_________________________");
		log.info(member);
		model.addAttribute("member", member);
	}
	
	@GetMapping("/update")
	public void update(Model model, MemberVO member) {
		log.info(member);
		model.addAttribute("member", service.read(member));
	}
	
	@PostMapping("/update")
	public String update(MemberVO member) {
		log.info(member);
		service.update(member);
		return "redirect:/member/read?m_id=" + member.getM_id();
	}
	
	@GetMapping("/delete")
	public String delete(MemberVO member) {
		service.delete(member);
		return "redirect:/member/list";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public String login(MemberVO member, HttpSession session) {
		log.info(member);
		boolean chk = service.auth(member);
		if(chk == true) {
			member = service.read(member);
			log.info("인증성공");
			session.setAttribute("m_id", member.getM_id());
			session.setAttribute("m_name", member.getM_name());
			return "redirect:/";
		} else {
			log.info("인증실패");
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();//세션 끊기
		return "redirect:/";
	}
}
