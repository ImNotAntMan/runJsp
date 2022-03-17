package com.shopadmin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopadmin.domain.Board2VO;
import com.shopadmin.domain.PageDTO;
import com.shopadmin.domain.PageViewDTO;
import com.shopadmin.service.Board2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board2/")
public class Board2Controller {

	@Setter(onMethod_ = @Autowired)
	private Board2Service service;
	
	@GetMapping("/read")
	public void read(Board2VO board, Model model, PageDTO page) {
		board = service.read(board);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", page.getPageNum());
	}
	
	@GetMapping("/update")
	public void update(Board2VO board, Model model, PageDTO page) {
		board = service.read(board);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", page.getPageNum());		
	}
	
	@PostMapping("/update")
	public String update(Board2VO board, PageDTO page) {
		service.update(board);
		return "redirect:/board2/read?b_num=" + board.getB_num() + "&pageNum=" + page;
	}
	
	@GetMapping("/list")
	public void list(Model model, PageDTO page) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		model.addAttribute("list", service.getList(page));	// getList로 조회한 내용을 리스트 변수로 전달model 객체
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		log.info(pageview);
		model.addAttribute("pageview", pageview);
	}

	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}

	@PostMapping("/insert")
	public String fileupload(HttpServletRequest request) {
		DiskFileUpload upload =  new DiskFileUpload();
		try {
			List items = upload.parseRequest(request);	// 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
			Iterator params = items.iterator();	// 반복자 생성
			String filepath = "C:\\myWorkSpace\\runJsp\\pds";
			Board2VO board = new Board2VO();
			while(params.hasNext()) {	// form 객체가 있을 경우
				FileItem item = (FileItem)params.next();	// 폼 경식 객체를 변수에 저장
				System.out.println(item);
				if(item.isFormField()) {	// 파일 형식이 아니라면
					//p_code = item.getString();	// 파일보다 먼저 반환 됨
					String fieldname = item.getFieldName();
					String fieldvalue =  item.getString("utf-8");
					log.info(fieldname + ":" + fieldvalue);
					if(fieldname.equals("b_subject")) {
						board.setB_subject(fieldvalue);
					} else if(fieldname.equals("b_name")) {
						board.setB_name(fieldvalue);
					} else if(fieldname.equals("b_contents")) {
						board.setB_contents(fieldvalue);
					}
				} else {	// 바이너리 파일이라면 
					String fname = item.getName();
					log.info(fname);
					if(fname != "") {
						board.setB_file(fname);
						File file = new File(filepath + "/" + fname);	// 파일객체 생성
						item.write(file);						
					}
				}
			}
			log.info(board);
			service.insert(board);
			return "redirect:/board2/list";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@GetMapping("/download")
	public void download(Board2VO board, HttpServletResponse response) {
		try {
			board = service.read(board);
			String filepath = "C:\\myWorkSpace\\runJsp\\pds\\" + board.getB_file();
			File file = new File(filepath); // 실제경로 지정
			
			// 자바에서의 다운로드가 아니라 웹브라우저에서의 다운로드로 인식시키기 위한 코드
			// text/html 이 기본값
			// 클라이언트 웹브라우저에 파일 다운로드로 처림됨
			String newName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + newName);
			log.info(file.getName());
			log.info(newName);
			System.out.println(board);
			System.out.println(file.getName());
			System.out.println(newName);
			
			FileInputStream fis = new FileInputStream(filepath);
			OutputStream out = response.getOutputStream();
			
			int read = 0;	// 1024 단위로 읽은 바이트 수 
			byte[] buffer = new byte[1024];	//임의 로 조정 가능
			while((read = fis.read(buffer)) != -1) {	// 읽지 못하면 -1 반환
				out.write(buffer, 0, read);	// 웹브라우저에 출력
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
