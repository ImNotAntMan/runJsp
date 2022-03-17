package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.notice.NoticeVO;
import com.it.service.NoticeService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/notice/")
public class NoticeController {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeService service;
	
	@GetMapping("/list")
	public void getList(Model model, PageDTO page) {
		model.addAttribute("notice", service.getList(page));
		int total = service.getTotalCount();
		log.info(total);
		log.info(page);
		PageViewDTO pageview = new PageViewDTO(page, total);
		model.addAttribute("pageview", pageview);
	}
	
	@GetMapping("/read")
	public void read(NoticeVO notice, Model model, PageDTO page) {
		model.addAttribute("notice", service.read(notice));
		log.info(page);
		model.addAttribute("page", page);
	}
	
	@GetMapping("/insert")
	public void insert() {
		
	}
	
	@PostMapping("/insert")
	public String insert(NoticeVO notice) {
		service.insert(notice);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/update")
	public void update(NoticeVO notice, Model model) {
		model.addAttribute("notice", service.read(notice));
	}
	
	@PostMapping("/update")
	public String update(NoticeVO notice) {
		service.update(notice);
		return "redirect:/notice/read?n_num=" + notice.getN_num();
	}
	
	@GetMapping("/delete")
	public String delete(NoticeVO notice) {
		service.delete(notice);
		return "redirect:/notice/list";
	}
}
