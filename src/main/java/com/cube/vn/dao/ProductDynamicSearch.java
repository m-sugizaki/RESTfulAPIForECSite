package com.cube.vn.dao;

import java.math.BigDecimal;

public class ProductDynamicSearch {
	private String product_name;
	
	private String maker;
	
	private BigDecimal fromPrice;
	
	private BigDecimal toPrice;
	
	public String getProductName() {
		return this.product_name;
	}

	public void setProductName(String product_name) {
		this.product_name = product_name;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public BigDecimal getFromPrice() {
		return this.fromPrice;
	}

	public void setFromPrice(BigDecimal price) {
		this.fromPrice = price;
	}
	
	public BigDecimal getToPrice() {
		return this.toPrice;
	}

	public void setToPrice(BigDecimal price) {
		this.toPrice = price;
	}
}
