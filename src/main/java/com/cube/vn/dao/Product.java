package com.cube.vn.dao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	private ProductPK productPK;
	
	@Column(name = "product_name", nullable = false, length = 50)
	private String product_name;
	
	@Column(name = "maker", nullable = false, length = 50)
	private String maker;
	
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@Column(name = "size", length = 20)
	private String size;
	
	@Column(name = "color", length = 20)
	private String color;
	
	@Column(name = "sale_point", nullable = false, length = 200)
	private String sale_point;
	
	@Lob
	@Column(name = "image", nullable = false)
	private byte[] image;
	
	@Column(name = "stock_quantity", nullable = false)
	private int stock_quantity;
	
	@Column(name = "similar_product_id", length = 50)
	private String similar_product_id;
	
	@EmbeddedId
    public ProductPK getPrimaryKey() {
        return this.productPK;
    }
 
    public void setPrimaryKey(ProductPK pk) {
    	this.productPK = pk;
    }

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

	public String getSalePoint() {
		return this.sale_point;
	}

	public void setSalePoint(String sale_point) {
		this.sale_point = sale_point;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getStockQuantity() {
		return this.stock_quantity;
	}

	public void setStockQuantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String getSimilarProductId() {
		return this.similar_product_id;
	}

	public void setSimilarProductId(String similar_product_id) {
		this.similar_product_id = similar_product_id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
