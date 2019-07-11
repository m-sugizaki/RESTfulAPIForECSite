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

public class PurchaseResultsModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(PurchaseResults.class, Table.class);
		Assert.assertEquals("purchase_results", table.name());
	}

	@Test
	public void testOrderNo() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "order_no", Column.class);
		Assert.assertEquals(col.name(), "order_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "order_no", int.class));
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "user_id", String.class));
	}

	@Test
	public void testProductId() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "product_id", Column.class);
		Assert.assertEquals(col.name(), "product_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "product_id", String.class));
	}

	@Test
	public void testQuantity() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "quantity", Column.class);
		Assert.assertEquals(col.name(), "quantity");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "quantity", int.class));
	}

	@Test
	public void testSize() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "size", Column.class);
		Assert.assertEquals(col.name(), "size");
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "size", String.class));
	}

	@Test
	public void testColor() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "color", Column.class);
		Assert.assertEquals(col.name(), "color");
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "color", String.class));
	}

	@Test
	public void testOrderDate() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "order_dt", Column.class);
		Assert.assertEquals(col.name(), "order_dt");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "order_dt", Date.class));
	}

	@Test
	public void testOrderStatus() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "order_status", Column.class);
		Assert.assertEquals(col.name(), "order_status");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 20);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "order_status", String.class));
	}

	@Test
	public void testPaymentMethod() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "payment_method", Column.class);
		Assert.assertEquals(col.name(), "payment_method");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "payment_method", String.class));
	}

	@Test
	public void testPaymentNo() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "payment_no", Column.class);
		Assert.assertEquals(col.name(), "payment_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "payment_no", int.class));
	}

	@Test
	public void testShippingAddressNo() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "shipping_address_no", Column.class);
		Assert.assertEquals(col.name(), "shipping_address_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "shipping_address_no", int.class));
	}

	@Test
	public void testDeliveryPlanDate() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "delivery_plan_dt", Column.class);
		Assert.assertEquals(col.name(), "delivery_plan_dt");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "delivery_plan_dt", Date.class));
	}

	@Test
	public void testDeliveryCompletionDate() {
		Column col = TestUtil.getFieldAnnotation(PurchaseResults.class, "delivery_completion_dt", Column.class);
		Assert.assertEquals(col.name(), "delivery_completion_dt");
		Assert.assertTrue(TestUtil.isDataType(PurchaseResults.class, "delivery_completion_dt", Date.class));
	}

	@Test
	public void testPrimaryKey() {
		Id id = TestUtil.getFieldAnnotation(PurchaseResults.class, "order_no", Id.class);
		GeneratedValue generrate = TestUtil.getFieldAnnotation(PurchaseResults.class, "order_no", GeneratedValue.class);
		Assert.assertEquals(generrate.strategy(), GenerationType.IDENTITY);
		Assert.assertTrue(id != null);
	}
}
