package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class LoginHistoryModelTest {
	@Test
	public void testTableName() {
	    Table table = TestUtil.getClassAnnotation(LoginHistory.class, Table.class);
	    Assert.assertEquals("login_history", table.name());
	}
	
	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(LoginHistoryPK.class, "userId", Column.class);
	    Assert.assertEquals(col.name(), "user_id");
	    Assert.assertEquals(col.nullable(), false);
	    Assert.assertEquals(col.length(), 10);
	    Assert.assertTrue(TestUtil.isDataType(LoginHistoryPK.class, "userId", String.class));
	}
	
	@Test
	public void testLoginDt() {
	    Column col = TestUtil.getFieldAnnotation(LoginHistoryPK.class, "loginDt", Column.class);
	    Assert.assertEquals(col.name(), "login_dt");
	    Assert.assertEquals(col.nullable(), false);
	    Assert.assertTrue(TestUtil.isDataType(LoginHistoryPK.class, "loginDt", Date.class));
	}
	
	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(LoginHistory.class, "getPrimaryKey", EmbeddedId.class);
	    Assert.assertTrue(embeddedId != null);
	}
}
