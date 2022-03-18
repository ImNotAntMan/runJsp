package com.shopadmin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopadmin.myapp.AdminVO;
import com.shopadmin.myapp.MemberVO;
import com.shopadmin.myapp.PageDTO;
import com.shopadmin.myapp.PageViewDTO;
import com.shopadmin.service.AdminService;
import com.shopadmin.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/")
public class MemberController {

	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private AdminService adminservice;
	
	@GetMapping("/list")
	public String getList(Model model, PageDTO page, HttpSession session) {
		AdminVO admin = new AdminVO();
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		}
		admin.setA_id(a_id);
		System.out.println(admin);
		admin = adminservice.read(admin);
		System.out.println(admin);
		if(admin != null) {
			model.addAttribute("list",service.getList(page));
			int total = service.getTotalCount();
			PageViewDTO pageview = new PageViewDTO(page, total);
			model.addAttribute("pageview", pageview);
			return "/member/list";
		} else {
			return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/read")
	public void read(MemberVO member, Model model, PageDTO page) {
		model.addAttribute("member", service.read(member));
		model.addAttribute("pageNum", page.getPageNum());
	}
	
	@GetMapping("/insert")
	public void insert(MemberVO member) {
		
	}
	
	@PostMapping("/insert")
	public String insert(MemberVO member, PageDTO page) {
		service.insert(member);
		return "redirect:/member/list?pageNum=" + page.getPageNum();
	}
	
	@GetMapping("/update")
	public void update(MemberVO member, Model model, PageDTO page) {
		member =  service.read(member);
		log.info(member);
		model.addAttribute("member", member);
		model.addAttribute("pageNum", page.getPageNum());
	}
	
	@PostMapping("/update")
	public String update(MemberVO member, PageDTO page, Model model) {
		service.update(member);
		return "redirect:/member/read?m_id=" + member.getM_id() + "&pageNUm=" + page.getPageNum();
	}
	
	@GetMapping("/delete")
	public String delete(MemberVO member) {
		service.delete(member);
		return "redirect:/member/list";
	}
}
