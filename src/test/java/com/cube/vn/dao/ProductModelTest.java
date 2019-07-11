package com.cube.vn.dao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class ProductModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(Product.class, Table.class);
		Assert.assertEquals("product", table.name());
	}

	@Test
	public void testProductId() {
		Column col = TestUtil.getFieldAnnotation(ProductPK.class, "product_id", Column.class);
		Assert.assertEquals(col.name(), "product_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ProductPK.class, "product_id", String.class));
	}

	@Test
	public void testProductName() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "product_name", Column.class);
		Assert.assertEquals(col.name(), "product_name");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "product_name", String.class));
	}

	@Test
	public void testMaker() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "maker", Column.class);
		Assert.assertEquals(col.name(), "maker");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "maker", String.class));
	}

	@Test
	public void testPrice() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "price", Column.class);
		Assert.assertEquals(col.name(), "price");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "price", BigDecimal.class));
	}

	@Test
	public void testSize() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "size", Column.class);
		Assert.assertEquals(col.name(), "size");
		Assert.assertEquals(col.length(), 20);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "size", String.class));
	}

	@Test
	public void testColor() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "color", Column.class);
		Assert.assertEquals(col.name(), "color");
		Assert.assertEquals(col.length(), 20);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "color", String.class));
	}

	@Test
	public void testSalePoint() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "sale_point", Column.class);
		Assert.assertEquals(col.name(), "sale_point");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 200);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "sale_point", String.class));
	}

	@Test
	public void testImage() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "image", Column.class);
		Assert.assertEquals(col.name(), "image");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "image", byte[].class));
	}

	@Test
	public void testStockQuantity() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "stock_quantity", Column.class);
		Assert.assertEquals(col.name(), "stock_quantity");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "stock_quantity", int.class));
	}

	@Test
	public void testSimilarProductId() {
		Column col = TestUtil.getFieldAnnotation(Product.class, "similar_product_id", Column.class);
		Assert.assertEquals(col.name(), "similar_product_id");
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(Product.class, "similar_product_id", String.class));
	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(Product.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
