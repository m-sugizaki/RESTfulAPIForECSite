package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_cart")
public class ProductCart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_cart_id", nullable = false)
    private int product_cart_id;
	
	@Column(name = "user_id", nullable = false, length = 10)
	private String user_id;
	
	@Column(name = "product_id", nullable = false, length = 10)
	private String product_id;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "size", length = 10)
	private String size;
	
	@Column(name = "color", length = 10)
	private String color;
	
	@Column(name = "cart_regist_dt", nullable = false)
	private Date cart_regist_dt;

	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public String getProductId() {
		return this.product_id;
	}

	public void setProductId(String product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCartRegistDt() {
		return this.cart_regist_dt;
	}

	public void setCartRegistDt(Date cart_regist_dt) {
		this.cart_regist_dt = cart_regist_dt;
	}

	public int getProductCartId() {
		return this.product_cart_id;
	}

	public void setProductCartId(int product_cart_id) {
		this.product_cart_id = product_cart_id;
	}
}
