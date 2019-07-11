package com.cube.vn.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ShippingAddressPK implements Serializable {
	@Column(name = "user_id", nullable = false, length = 10)
	private String user_id;

	@Column(name = "shipping_address_no", nullable = false)
	private int shipping_address_no;
	
	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public int getShippingAddressNo() {
		return this.shipping_address_no;
	}

	public void setShippingAddressNo(int shipping_address_no) {
		this.shipping_address_no = shipping_address_no;
	}
}
