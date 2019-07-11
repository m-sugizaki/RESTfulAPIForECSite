package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class UserModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(User.class, Table.class);
		Assert.assertEquals("user", table.name());
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(UserPK.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(UserPK.class, "user_id", String.class));
	}

	@Test
	public void testBirthday() {
		Column col = TestUtil.getFieldAnnotation(User.class, "birthday", Column.class);
		Assert.assertEquals(col.name(), "birthday");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(User.class, "birthday", Date.class));
	}

	@Test
	public void testName() {
		Column col = TestUtil.getFieldAnnotation(User.class, "name", Column.class);
		Assert.assertEquals(col.name(), "name");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(User.class, "name", String.class));
	}

	@Test
	public void testNickName() {
		Column col = TestUtil.getFieldAnnotation(User.class, "nickname", Column.class);
		Assert.assertEquals(col.name(), "nickname");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(User.class, "nickname", String.class));
	}

	@Test
	public void testPostalCode() {
		Column col = TestUtil.getFieldAnnotation(User.class, "postal_code", Column.class);
		Assert.assertEquals(col.name(), "postal_code");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(User.class, "postal_code", String.class));
	}

	@Test
	public void testAddress1() {
		Column col = TestUtil.getFieldAnnotation(User.class, "address1", Column.class);
		Assert.assertEquals(col.name(), "address1");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 100);
		Assert.assertTrue(TestUtil.isDataType(User.class, "address1", String.class));
	}

	@Test
	public void testAddress2() {
		Column col = TestUtil.getFieldAnnotation(User.class, "address2", Column.class);
		Assert.assertEquals(col.name(), "address2");
		Assert.assertEquals(col.length(), 100);
		Assert.assertTrue(TestUtil.isDataType(User.class, "address2", String.class));
	}

	@Test
	public void testPhoneNumber() {
		Column col = TestUtil.getFieldAnnotation(User.class, "phone_number", Column.class);
		Assert.assertEquals(col.name(), "phone_number");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 15);
		Assert.assertTrue(TestUtil.isDataType(User.class, "phone_number", String.class));
	}

	@Test
	public void testEmail() {
		Column col = TestUtil.getFieldAnnotation(User.class, "email", Column.class);
		Assert.assertEquals(col.name(), "email");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(User.class, "email", String.class));
	}

	@Test
	public void testMemberRank() {
		Column col = TestUtil.getFieldAnnotation(User.class, "member_rank", Column.class);
		Assert.assertEquals(col.name(), "member_rank");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 5);
		Assert.assertTrue(TestUtil.isDataType(User.class, "member_rank", String.class));
	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(User.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
