package com.cube.vn.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class PaymentMethodModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(PaymentMethod.class, Table.class);
		Assert.assertEquals("payment_method", table.name());
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethodPK.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethodPK.class, "user_id", String.class));
	}

	@Test
	public void testPaymentNo() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethodPK.class, "payment_no", Column.class);
		Assert.assertEquals(col.name(), "payment_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethodPK.class, "payment_no", int.class));
	}

	@Test
	public void testPaymentMethod() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethod.class, "payment_method", Column.class);
		Assert.assertEquals(col.name(), "payment_method");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethod.class, "payment_method", String.class));
	}

	@Test
	public void testCardNumber() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethod.class, "card_number", Column.class);
		Assert.assertEquals(col.name(), "card_number");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 20);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethod.class, "card_number", String.class));
	}

	@Test
	public void testExpirationDate() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethod.class, "expiration_date", Column.class);
		Assert.assertEquals(col.name(), "expiration_date");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethod.class, "expiration_date", Date.class));
	}

	@Test
	public void testCardHolderName() {
		Column col = TestUtil.getFieldAnnotation(PaymentMethod.class, "card_holder_name", Column.class);
		Assert.assertEquals(col.name(), "card_holder_name");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(PaymentMethod.class, "card_holder_name", String.class));

	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(PaymentMethod.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
