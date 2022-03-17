package com.it.service;

import java.util.List;

import com.it.domain.CartmainVO;
import com.it.domain.OrderdetailDTO;
import com.it.domain.OrdermainVO;

public interface OrderService {
	
	public OrdermainVO orderproc(CartmainVO cartmain);
	
	public List<OrderdetailDTO> getListCartDetail(OrdermainVO ordermain);
	
}
