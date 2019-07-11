package com.cube.vn.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PaymentMethodPK implements Serializable {
	@Column(name = "user_id", nullable = false, length = 10)
	private String user_id;

	@Column(name = "payment_no", nullable = false)
	private int payment_no;
	
	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public int getPaymentNo() {
		return this.payment_no;
	}

	public void setPaymentNo(int payment_no) {
		this.payment_no = payment_no;
	}
}
