package com.cube.vn.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ReviewPK implements Serializable {
	@Column(name = "product_id", nullable = false, length = 10)
	private String product_id;

	@Column(name = "review_no", nullable = false)
	private int review_no;
	
	public String getProductId() {
		return this.product_id;
	}

	public void setProductId(String product_id) {
		this.product_id = product_id;
	}

	public int getReviewNo() {
		return this.review_no;
	}

	public void setReviewNo(int review_no) {
		this.review_no = review_no;
	}
}
