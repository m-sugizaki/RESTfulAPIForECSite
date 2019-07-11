package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class ProductCartModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(ProductCart.class, Table.class);
		Assert.assertEquals("product_cart", table.name());
	}

	@Test
	public void testProductCartId() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "product_cart_id", Column.class);
		Assert.assertEquals(col.name(), "product_cart_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "product_cart_id", int.class));
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "user_id", String.class));
	}

	@Test
	public void testProductId() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "product_id", Column.class);
		Assert.assertEquals(col.name(), "product_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "product_id", String.class));
	}

	@Test
	public void testQuantity() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "quantity", Column.class);
		Assert.assertEquals(col.name(), "quantity");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "quantity", int.class));
	}

	@Test
	public void testSize() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "size", Column.class);
		Assert.assertEquals(col.name(), "size");
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "size", String.class));
	}

	@Test
	public void testColor() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "color", Column.class);
		Assert.assertEquals(col.name(), "color");
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "color", String.class));
	}

	@Test
	public void testCardRegistDate() {
		Column col = TestUtil.getFieldAnnotation(ProductCart.class, "cart_regist_dt", Column.class);
		Assert.assertEquals(col.name(), "cart_regist_dt");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(ProductCart.class, "cart_regist_dt", Date.class));
	}

	@Test
	public void testPrimaryKey() {
		Id id = TestUtil.getFieldAnnotation(ProductCart.class, "product_cart_id", Id.class);
		GeneratedValue generate = TestUtil.getFieldAnnotation(ProductCart.class, "product_cart_id", GeneratedValue.class);
		Assert.assertEquals(generate.strategy(), GenerationType.IDENTITY);
		Assert.assertTrue(id != null);
	}
}
