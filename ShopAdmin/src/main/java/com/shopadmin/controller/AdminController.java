package com.shopadmin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopadmin.domain.AdminVO;
import com.shopadmin.service.AdminService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/")
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminService service;
	
	@GetMapping("/login")
	public String login() {
		return "/admin/login";
	}

	@PostMapping("/login")
	public String login(AdminVO admin, HttpSession session, Model model) {
		boolean chk = service.auth(admin);
		log.info(admin);
		if(chk == true) {
			admin = service.read(admin);
			System.out.println(admin);
			System.out.println(admin.getA_id());
			System.out.println(admin.getA_name());
			session.setAttribute("a_id", admin.getA_id());
			session.setAttribute("a_name", admin.getA_name());
			model.addAttribute("a_id", admin.getA_id());
			model.addAttribute("a_name", admin.getA_name());
			return "redirect:/";
		} else {
			return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();//세션 끊기
		return "redirect:/";
	}

}
