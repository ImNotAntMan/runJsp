package com.shopadmin.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopadmin.myapp.PageDTO;
import com.shopadmin.myapp.PageViewDTO;
import com.shopadmin.myapp.ProductVO;
import com.shopadmin.service.AdminService;
import com.shopadmin.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/product/")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService service;
	
	@Setter(onMethod_ = @Autowired)
	private AdminService adminservice;
	
	@GetMapping("/list")
	private String list(Model model, PageDTO page, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			int total = service.getTotalCount();
			PageViewDTO pageview = new PageViewDTO(page, total);
			model.addAttribute("pageview", pageview);
			model.addAttribute("list", service.getList(page));
			return "/product/list";			
		}
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			return "/product/insert";
		}

	}
	
	@PostMapping("/insert")
	public String insert(ProductVO product, HttpSession session) {
		service.insert(product);
		return "redirect:/product/list";
	}
	
	@GetMapping("/read")
	private String read(ProductVO product, Model model, HttpSession session) {
		log.info(product);
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			product = service.read(product);
			model.addAttribute("product", product);		
			return "/product/read";
		}
	}
	
	@GetMapping("/update")
	private String update(ProductVO product, Model model, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			product = service.read(product);
			System.out.println(product);
			model.addAttribute("product", product);
			return "/product/update";
		}
	}
	
	@PostMapping("/update")
	private String update(ProductVO product, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			service.update(product);
			return "redirect:/product/read?p_code=" + product.getP_code();			
		}
	}
	
	@GetMapping("/delete")
	private String delete(ProductVO product, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			service.delete(product);
			return "redirect:/product/list";			
		}
	}
	
	@GetMapping("/imgupload")
	public String imgupload(ProductVO product, Model model, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			log.info(product);
			model.addAttribute("p_code", product.getP_code());
			return "/product/imgupload";
		}
	}
	
	@PostMapping("/imgupload")
	public String imgupload(HttpServletRequest request, HttpSession session) {
		String a_id = (String) session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			DiskFileUpload upload =  new DiskFileUpload();
			try {
				List items = upload.parseRequest(request);	// 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
				Iterator params = items.iterator();	// 반복자 생성
				String imgpath = "D:\\myWorkSpace\\runJsp\\ShopAdmin\\src\\main\\webapp\\resources\\product";
				String p_code = "";
				log.info(items);
				while(params.hasNext()) {	// form 객체가 있을 경우
					FileItem item = (FileItem)params.next();	// 폼 경식 객체를 변수에 저장
					if(item.isFormField()) {	// 파일 형식이 아니라면
						p_code = item.getString();	// 파일보다 먼저 반환 됨
					} else {	// 바이너리 파일이라면 
						File imgfile = new File(imgpath + "/" + p_code + ".jpg");	// 파일객체 생성
						item.write(imgfile);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return "/product/imgupload";
		}
	}
}
