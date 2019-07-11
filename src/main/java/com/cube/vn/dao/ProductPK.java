package com.cube.vn.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProductPK implements Serializable {
	@Column(name = "product_id", nullable = false, length = 10)
	private String product_id;

	public String getProductId() {
		return this.product_id;
	}

	public void setProductId(String product_id) {
		this.product_id = product_id;
	}
}
