package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_results")
public class PurchaseResults {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_no", nullable = false)
    private int order_no;
	
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
	
	@Column(name = "order_dt", nullable = false)
	private Date order_dt;
	
	@Column(name = "order_status", nullable = false, length = 20)
	private String order_status;
	
	@Column(name = "payment_method", nullable = false, length = 10)
	private String payment_method;
	
	@Column(name = "payment_no", nullable = false)
	private int payment_no;
	
	@Column(name = "shipping_address_no", nullable = false)
	private int shipping_address_no;
	
	@Column(name = "delivery_plan_dt", nullable = false)
	private Date delivery_plan_dt;
	
	@Column(name = "delivery_completion_dt")
	private Date delivery_completion_dt;

	public int getOrderNo() {
		return this.order_no;
	}

	public void setOrderNo(int order_no) {
		this.order_no = order_no;
	}

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

	public Date getOrderDt() {
		return this.order_dt;
	}

	public void setOrderDt(Date order_dt) {
		this.order_dt = order_dt;
	}

	public String getOrderStatus() {
		return this.order_status;
	}

	public void setOrderStatus(String order_status) {
		this.order_status = order_status;
	}

	public String getPaymentMethod() {
		return this.payment_method;
	}

	public void setPaymentMethod(String payment_method) {
		this.payment_method = payment_method;
	}

	public int getPaymentNo() {
		return this.payment_no;
	}

	public void setPaymentNo(int payment_no) {
		this.payment_no = payment_no;
	}

	public int getShippingAddressNo() {
		return this.shipping_address_no;
	}

	public void setShippingAddressNo(int shipping_address_no) {
		this.shipping_address_no = shipping_address_no;
	}

	public Date getDeliveryPlanDt() {
		return this.delivery_plan_dt;
	}

	public void setDeliveryPlanDt(Date delivery_plan_dt) {
		this.delivery_plan_dt = delivery_plan_dt;
	}

	public Date getDeliveryCompletionDt() {
		return this.delivery_completion_dt;
	}

	public void setDeliveryCompletionDt(Date delivery_completion_dt) {
		this.delivery_completion_dt = delivery_completion_dt;
	}
}
