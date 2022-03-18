package com.shopmember.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopmember.mapper.ShippingMapper;
import com.shopmember.myapp.ShippingVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ShippingServiceImpl implements ShippingService {

	@Setter(onMethod_ = @Autowired)
	private ShippingMapper mapper;
	
	@Override
	public ShippingVO read(ShippingVO shipping) {
		return mapper.read(shipping);
	}
	
	@Override
	public void insert(ShippingVO shipping) {
		mapper.insert(shipping);
	}
	
	@Override
	public List<ShippingVO> getList(ShippingVO shipping) {
		return mapper.getList(shipping);
	}
	
	@Override
	public void update(ShippingVO shipping) {
		mapper.update(shipping);
	}
	
	@Override
	public void delete(ShippingVO shipping) {
		mapper.delete(shipping);
	}

	@Override
	public int getTotalCount(ShippingVO shipping) {
		return mapper.getTotalCount(shipping);
	}

}
