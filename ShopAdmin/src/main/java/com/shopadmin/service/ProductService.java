package com.shopadmin.service;

import java.util.List;

import com.shopadmin.myapp.PageDTO;
import com.shopadmin.myapp.ProductVO;

public interface ProductService {
	
	public List<ProductVO> getList(PageDTO page);
	
	public void insert(ProductVO product);
	
	public ProductVO read(ProductVO product);
	
	public void update(ProductVO product);
	
	public void delete(ProductVO product);
	
	public int getTotalCount();

}
