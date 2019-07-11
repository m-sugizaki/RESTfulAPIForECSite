package com.cube.vn.dao;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.cube.vn.util.TestUtil;

public class ProductDynamicSearchModelTest {

	@Test
	public void testProductName() {
		Assert.assertTrue(TestUtil.isDataType(ProductDynamicSearch.class, "product_name", String.class));
	}

	@Test
	public void testMaker() {
		Assert.assertTrue(TestUtil.isDataType(ProductDynamicSearch.class, "maker", String.class));
	}

	@Test
	public void testFromPrice() {
		Assert.assertTrue(TestUtil.isDataType(ProductDynamicSearch.class, "fromPrice", BigDecimal.class));
	}

	@Test
	public void testToPrice() {
		Assert.assertTrue(TestUtil.isDataType(ProductDynamicSearch.class, "toPrice", BigDecimal.class));
	}
}
