package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.domain.BoardVO;
import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model, PageDTO page, @RequestParam("user") String user, 
			@RequestParam("age") int age) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		if(user == null) user="이순신";
		model.addAttribute("list", service.getList(page));	// getList로 조회한 내용을 리스트 변수로 전달model 객체
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		log.info(pageview);
		model.addAttribute("pageview", pageview);
		log.info("______________________________________");
		log.info(user);
		log.info(age + 10);
		log.info("______________________________________");
		model.addAttribute("user", user);
		model.addAttribute("age", age);
	}
	
	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO board) {
		log.info(board);
		service.insert(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void read(Model model, BoardVO board, PageDTO page) {
		log.info(board);
		board = service.read(board);
		log.info(board);
		log.info(page);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", page.getPageNum());
	}
	
	@GetMapping("/update")
	public void update(Model model, @RequestParam("b_num") int b_num, PageDTO page) {
		BoardVO board = new BoardVO();
		log.info(board);
		log.info(page);
		board.setB_num(b_num);
		model.addAttribute("board", service.read(board));
		model.addAttribute("page", page);
	}
	
	@PostMapping("/update")
	public String update(BoardVO board, PageDTO page) {
		log.info(board);
		log.info(page);
		service.update(board);
		return "redirect:/board/read?b_num=" + board.getB_num() + "&pageNum=" + page.getPageNum();
	}
	
	@GetMapping("/delete")
	public String delete(BoardVO board) {
		service.delete(board);
		return "redirect:/board/list";
	}
}
