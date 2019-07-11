package com.cube.vn.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class ShippingAddressModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(ShippingAddress.class, Table.class);
		Assert.assertEquals("shipping_address", table.name());
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddressPK.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddressPK.class, "user_id", String.class));
	}

	@Test
	public void testShippingAddressNo() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddressPK.class, "shipping_address_no", Column.class);
		Assert.assertEquals(col.name(), "shipping_address_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddressPK.class, "shipping_address_no", int.class));
	}

	@Test
	public void testPostalCode() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddress.class, "postal_code", Column.class);
		Assert.assertEquals(col.name(), "postal_code");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddress.class, "postal_code", String.class));
	}

	@Test
	public void testAddress1() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddress.class, "address1", Column.class);
		Assert.assertEquals(col.name(), "address1");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 100);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddress.class, "address1", String.class));
	}

	@Test
	public void testAddress2() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddress.class, "address2", Column.class);
		Assert.assertEquals(col.name(), "address2");
		Assert.assertEquals(col.length(), 100);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddress.class, "address2", String.class));
	}

	@Test
	public void testPhoneNumber() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddress.class, "phone_number", Column.class);
		Assert.assertEquals(col.name(), "phone_number");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 15);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddress.class, "phone_number", String.class));
	}

	@Test
	public void testShippingAddressName() {
		Column col = TestUtil.getFieldAnnotation(ShippingAddress.class, "shipping_address_name", Column.class);
		Assert.assertEquals(col.name(), "shipping_address_name");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(ShippingAddress.class, "shipping_address_name", String.class));
	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(ShippingAddress.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
