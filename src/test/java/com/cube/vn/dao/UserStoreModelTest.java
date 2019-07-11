package com.cube.vn.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class UserStoreModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(UserStore.class, Table.class);
		Assert.assertEquals("user_store", table.name());
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(UserStorePK.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(UserStorePK.class, "user_id", String.class));
	}

	@Test
	public void testPassword() {
		Column col = TestUtil.getFieldAnnotation(UserStore.class, "password", Column.class);
		Assert.assertEquals(col.name(), "password");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 50);
		Assert.assertTrue(TestUtil.isDataType(UserStore.class, "password", String.class));
	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(UserStore.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
