package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;
import com.it.domain.OrdermainVO;
import com.it.service.CartService;
import com.it.service.MemberService;
import com.it.service.OrderService;
import com.it.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/shop/")
public class ShopController {

	@Setter(onMethod_ = @Autowired)
	private ProductService serviceproduct;
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberservice;
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartservice;
	
	@Setter(onMethod_ = @Autowired)
	private OrderService orderservice;
	
	@GetMapping("/list")
	private void list(Model model) {
		model.addAttribute("list", serviceproduct.getList());
	}
	
	@GetMapping("/cart")
	private void cartsub(CartsubVO cartsub, Model model) {
		log.info(cartsub);
		model.addAttribute("cartsub", serviceproduct.read(cartsub));
	}
	
	@PostMapping("/cart")
	private String cartsub(CartsubVO cartsub, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");
		if(m_id != null) {
			CartmainVO cartmain = new CartmainVO();
			cartmain.setM_id(m_id);
			cartservice.cartinsert(cartmain, cartsub);
			return "redirect:/shop/cartinfo";
		} else {
			return "redirect:/shop/member/login";
		}
	}
	
//	@GetMapping("/cartinfo")
//	public void cartinfo(CartsubVO cartsub, Model model) {
//		// 세션아이디를 이요해서 cm_code를 구하고
//		// cm_code를 이요해서 getListCart를 조회해서 리스트 출력
//		String m_id = (String) session.getAttribute("m_id");
//		CartmainVO cartmain = new CartmainVO();
//		//int cs = cartmain.getCm_code();
//		CartsubVO cs = new CartsubVO();
//		cs.
//		model.addAttribute("list",cs)
//	}
	
	@GetMapping("/cartinfo")
	public String cartinfo(HttpSession session, Model model) {
		// 로그인 상태 확인 (제어구조(세션변수를 확인)를 먼저 만들어보자)
		String m_id = (String)session.getAttribute("m_id");
		String m_name = (String)session.getAttribute("m_name");
		if (m_id != null) {
		// 세션아이디를 이용해서 cm_code를 조회해야 함 / 로그인이 되어있다는 가정 하에 진행 
			CartmainVO cartmain = new CartmainVO();
			cartmain.setM_id(m_id);
			cartmain = cartservice.readMainid(cartmain);
			log.info(cartmain);
			if (cartmain == null) { // 로그인은 되었는데 cartmain(장바구니)이 있을수도 있고 없을 수도 있으니 확인해야 한다.
				log.info("장바구니 내용 없음");
				return "redirect:/shop/list";
			} else {
				// int cm_code = cartmain.getCm_code(); // 로그인된 사용자의 아이디를 사용하는 cartmain의 cm_code / 필요없어졌다.
				// cartservice.getListCart(cartmain).forEach(cartsub -> log.info(cartsub)); // 디버깅용으로 쓴거니까 주석
				model.addAttribute("list", cartservice.getListCartDetail(cartmain));
				CartmemberDTO carttotal =  cartservice.getCartTotal(cartmain);
				carttotal.setCm_code(cartmain.getCm_code());
				carttotal.setM_name(m_name);
				carttotal.setM_id(m_id);
//				System.out.println(carttotal);
				model.addAttribute("carttotal", carttotal);
				model.addAttribute("cartmain", cartmain.getCm_code());	// cm_code 전달을 위해서....
				log.info("장바구니 내용 있음"); // cm_code를 통해 cartSub를 조회할거야
			}
		// cm_code를 이용해서 getListCart를 조회해서 리스트 출력
			log.info("로그인 상태");
			return "/shop/cartinfo";	// cartinfo 페이지로 이동(반드시 작성)
		} else {
			log.info("로그아웃 상태");
		}	return "/member/login";
	}
	
	@PostMapping("/cartupdate")
	public String cartupdate(CartsubVO cartsub) {
		log.info(cartsub);
		cartservice.updateSub(cartsub);
		return "redirect:/shop/cartinfo";
	}
	
	@GetMapping("/cartdelete")
	public String cartdelete(CartsubVO cartsub) {
		log.info(cartsub);
		cartservice.deleteSub(cartsub);
		return "redirect:/shop/cartinfo";
	}
	
	@GetMapping("/cartdeleteall")
	public String cartdeleteall(CartmainVO cartmain) {
		cartservice.deletesuball(cartmain);
		return "redirect:/shop/list";
	}
	
	@GetMapping("/orderinfo")
	public String orderinfo(HttpSession session, Model model, CartmainVO cartmain) {
		String m_id = (String)session.getAttribute("m_id");
		if (m_id != null) {
			cartmain.setM_id(m_id);
			OrdermainVO ordermain = orderservice.orderproc(cartmain); // om_code 획득
			model.addAttribute("list", orderservice.getListCartDetail(ordermain));
			log.info(cartmain);
			return "/shop/orderinfo";
		} else {
			log.info("로그인 필요");
			return "redirect:/member/login";
		}
	}
}
