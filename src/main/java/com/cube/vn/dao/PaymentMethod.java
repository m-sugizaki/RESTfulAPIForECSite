package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {
	private PaymentMethodPK paymentMethodPK;
	
	@Column(name = "payment_method", nullable = false, length = 10)
	private String payment_method;
	
	@Column(name = "card_number", nullable = false, length = 20)
	private String card_number;
	
	@Column(name = "expiration_date", nullable = false)
	private Date expiration_date;
	
	@Column(name = "card_holder_name", nullable = false, length = 50)
	private String card_holder_name;
	
	@EmbeddedId
    public PaymentMethodPK getPrimaryKey() {
        return this.paymentMethodPK;
    }
 
    public void setPrimaryKey(PaymentMethodPK pk) {
    	this.paymentMethodPK = pk;
    }

	public String getPaymentMethod() {
		return this.payment_method;
	}

	public void setPaymentMethod(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCardNumber() {
		return this.card_number;
	}

	public void setCardNumber(String card_number) {
		this.card_number = card_number;
	}

	public Date getExpirationDate() {
		return this.expiration_date;
	}

	public void setExpirationDate(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getCardHolderName() {
		return this.card_holder_name;
	}

	public void setCardHolderName(String card_holder_name) {
		this.card_holder_name = card_holder_name;
	}
}
