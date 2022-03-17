package com.shopadmin.mapper;

import java.util.List;

import com.shopadmin.domain.ProductVO;

public interface ProductMapper {
	
	public List<ProductVO> getList();
	
	public void insert(ProductVO product);
	
	public ProductVO read(ProductVO product);
	
	public void update(ProductVO product);
	
	public void delete(ProductVO product);
	
	public int getTotalCount();
}
