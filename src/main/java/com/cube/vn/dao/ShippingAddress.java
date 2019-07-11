package com.cube.vn.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_address")
public class ShippingAddress {
	private ShippingAddressPK shippingAddressPK;
	
	@Column(name = "postal_code", nullable = false, length = 10)
	private String postal_code;
	
	@Column(name = "address1", nullable = false, length = 100)
	private String address1;
	
	@Column(name = "address2", length = 100)
	private String address2;
	
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phone_number;
	
	@Column(name = "shipping_address_name", nullable = false, length = 50)
	private String shipping_address_name;
	
	@EmbeddedId
    public ShippingAddressPK getPrimaryKey() {
        return this.shippingAddressPK;
    }
 
    public void setPrimaryKey(ShippingAddressPK pk) {
    	this.shippingAddressPK = pk;
    }

	public String getPostalCode() {
		return this.postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhoneNumber() {
		return this.phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getShippingAddressName() {
		return this.shipping_address_name;
	}

	public void setShippingAddressName(String shipping_address_name) {
		this.shipping_address_name = shipping_address_name;
	}
	
	
}
