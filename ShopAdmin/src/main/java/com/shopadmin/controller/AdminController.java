package com.shopadmin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.PageDTO;
import com.shopadmin.myapp.PageViewDTO;
import com.shopadmin.service.AdminService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/")
public class AdminController {

	@Setter(onMethod_ = @Autowired)
	private AdminService service;

	@GetMapping("/list")
	public void list(Model model, PageDTO page) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		model.addAttribute("list", service.getList(page));	// getList로 조회한 내용을 리스트 변수로 전달model 객체
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		model.addAttribute("pageview", pageview);
	}
	
	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public String insert(AdminVO admin) {
		log.info(admin);
		service.insert(admin);
		return "redirect:/admin/list";
	}
	
	@GetMapping("/read")
	public void read(Model model, AdminVO admin) {
		log.info(admin);
		admin = service.read(admin);
		log.info(admin);
		model.addAttribute("admin", admin);
	}
	
	@GetMapping("/update")
	public void update(Model model, @RequestParam("a_id") String a_id) {
		AdminVO admin = new AdminVO();
		log.info(admin);
		admin.setA_id(a_id);
		model.addAttribute("admin", service.read(admin));
	}
	
	@PostMapping("/update")
	public String update(AdminVO admin) {
		log.info(admin);
		service.update(admin);
		return "redirect:/admin/read?a_id=" + admin.getA_id();
	}
	
	@GetMapping("/delete")
	public String delete(AdminVO admin) {
		service.delete(admin);
		return "redirect:/admin/list";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public String login(AdminVO admin, HttpSession session) {
		log.info(admin);
		boolean chk = service.auth(admin);
		if(chk == true) {
			admin = service.read(admin);
			log.info("인증성공");
			session.setAttribute("a_id", admin.getA_id());
			session.setAttribute("a_name", admin.getA_name());
			return "redirect:/";
		} else {
			log.info("인증실패");
			return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();//세션 끊기
		return "redirect:/";
	}

}
