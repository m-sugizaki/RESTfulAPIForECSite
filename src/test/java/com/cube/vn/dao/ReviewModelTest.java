package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class ReviewModelTest {

	@Test
	public void testTableName() {
		Table table = TestUtil.getClassAnnotation(Review.class, Table.class);
		Assert.assertEquals("review", table.name());
	}

	@Test
	public void testProductId() {
		Column col = TestUtil.getFieldAnnotation(ReviewPK.class, "product_id", Column.class);
		Assert.assertEquals(col.name(), "product_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(ReviewPK.class, "product_id", String.class));
	}

	@Test
	public void testReviewNo() {
		Column col = TestUtil.getFieldAnnotation(ReviewPK.class, "review_no", Column.class);
		Assert.assertEquals(col.name(), "review_no");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(ReviewPK.class, "review_no", int.class));
	}

	@Test
	public void testUserId() {
		Column col = TestUtil.getFieldAnnotation(Review.class, "user_id", Column.class);
		Assert.assertEquals(col.name(), "user_id");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 10);
		Assert.assertTrue(TestUtil.isDataType(Review.class, "user_id", String.class));
	}

	@Test
	public void testEvaluation() {
		Column col = TestUtil.getFieldAnnotation(Review.class, "evaluation", Column.class);
		Assert.assertEquals(col.name(), "evaluation");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(Review.class, "evaluation", int.class));
	}

	@Test
	public void testReviewContent() {
		Column col = TestUtil.getFieldAnnotation(Review.class, "review_content", Column.class);
		Assert.assertEquals(col.name(), "review_content");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertEquals(col.length(), 100);
		Assert.assertTrue(TestUtil.isDataType(Review.class, "review_content", String.class));
	}

	@Test
	public void testReviewDate() {
		Column col = TestUtil.getFieldAnnotation(Review.class, "review_dt", Column.class);
		Assert.assertEquals(col.name(), "review_dt");
		Assert.assertEquals(col.nullable(), false);
		Assert.assertTrue(TestUtil.isDataType(Review.class, "review_dt", Date.class));
	}

	@Test
	public void testPrimaryKey() {
		EmbeddedId embeddedId = TestUtil.getMethodAnnotation(Review.class, "getPrimaryKey", EmbeddedId.class);
		Assert.assertTrue(embeddedId != null);
	}
}
