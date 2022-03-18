package com.shopadmin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

import com.shopadmin.myapp.Board2VO;
import com.shopadmin.myapp.PageDTO;
import com.shopadmin.myapp.PageViewDTO;
import com.shopadmin.service.Board2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board2/")
public class Board2Controller {

	@Setter(onMethod_ = @Autowired)
	private Board2Service service;
	
	@GetMapping("/list")
	public void list(Model model, PageDTO page) {	// 객체를 저장해서 jsp 파일로 전송
		// list.jsp에 데이터를 전달
		model.addAttribute("list", service.getList(page));	// getList로 조회한 내용을 리스트 변수로 전달model 객체
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		log.info(pageview);
		model.addAttribute("pageview", pageview);
	}
	
	@GetMapping("/read")
	public void read(Model model, Board2VO board, PageDTO page) {
		board = service.read(board);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", page.getPageNum());
		
	}
	
	@GetMapping("/update")
	public void update(Board2VO board, Model model) {
		board = service.read(board);
		model.addAttribute("board", board);
	}
	
	@PostMapping("/update")
	public String update(Board2VO board, PageDTO page) {
		service.update(board);
		return "redirect:/board2/read?b_num=" + board.getB_num() + "&pageNum=" + page.getPageNum();
	}

	@GetMapping("/insert")
	public void insert() {
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public void fileupload(HttpServletRequest request) {
		DiskFileUpload upload =  new DiskFileUpload();
		try {
			List items = upload.parseRequest(request);	// 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
			Iterator params = items.iterator();	// 반복자 생성
			String filepath = "D:\\myWorkSpace\\runJsp\\pds";
			Board2VO board = new Board2VO();
			while(params.hasNext()) {	// form 객체가 있을 경우
				FileItem item = (FileItem)params.next();	// 폼 경식 객체를 변수에 저장
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
					if(fname != "") {
						board.setB_file(fname);
			        	System.out.println("file name : " + fname);
			        	String ext = fname.substring(fname.lastIndexOf(".") + 1);
			        	fname = getUuid() + "." + ext;
			        	board.setB_filehash(fname);
						File file = new File(filepath + "/" + fname);	// 파일객체 생성
			        	System.out.println("hash file : " + fname);
			        	System.out.println("extension : " + ext);

						item.write(file);						
					}
				}
			}
			log.info(board);
			service.insert(board);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@GetMapping("/download")
	public void download(HttpServletResponse response, Board2VO board) {
        try {
        	board = service.read(board);
        	String path = "D:\\myWorkSpace\\runJsp\\pds\\" + board.getB_filehash();
//        	File file = new File(path);
        	String filename = new String(board.getB_file().getBytes("UTF-8"), "ISO-8859-1");

        	response.setHeader("Content-Disposition", "attachment;filename=" + filename); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
        	FileInputStream fileInputStream = new FileInputStream(path);
        	OutputStream out = response.getOutputStream();
        	int read = 0;
        	byte[] buffer = new byte[1024];
        	while((read =  fileInputStream.read(buffer)) != -1) {
        		out.write(buffer);
        	}
        } catch (Exception e) {
        		System.out.println(e);
        }
	}
	
	//uuid생성 
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}
}
