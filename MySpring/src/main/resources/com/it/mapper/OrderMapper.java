package com.it.mapper;

import java.util.List;

import com.it.domain.OrderdetailDTO;
import com.it.domain.OrdermainVO;
import com.it.domain.OrdersubVO;

public interface OrderMapper {
	
	public void insertMain(OrdermainVO ordermain);
	
	public void insertSub(OrdersubVO ordersub);
	
	public List<OrdermainVO> getListMain(OrdermainVO ordermain);
	
	public OrdermainVO readMainid(OrdermainVO ordermaqin);
	
	public List<OrderdetailDTO> getListOrderDetail(OrdermainVO ordermain);

}
