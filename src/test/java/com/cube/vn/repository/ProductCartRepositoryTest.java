package com.cube.vn.repository;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.ProductCart;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductCartRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private ProductCartRepository productCartRepository; 
	
	@Test
    public void testGetProductCartInfoByUserID_HasData() {
		ProductCart productCart = new ProductCart();
		List<ProductCart> listProductCartInfoFoundByUserID = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		productCart.setCartRegistDt(TestUtil.getDate(2019, 05, 14, 9, 50, 10));
		productCart.setUserId("testuser");
		productCart.setProductId("test10001");
		productCart.setQuantity(99);
		this.testEntityManager.persist(productCart);
		this.testEntityManager.flush();
		
		listProductCartInfoFoundByUserID = this.productCartRepository.getProductCartInfoByUserID(productCart.getUserId());
		
		Assert.assertTrue(listProductCartInfoFoundByUserID != null && listProductCartInfoFoundByUserID.size() == 1);
		Assert.assertEquals(dateformat.format(productCart.getCartRegistDt()), dateformat.format(listProductCartInfoFoundByUserID.get(0).getCartRegistDt()));
		Assert.assertEquals(productCart.getProductId(), listProductCartInfoFoundByUserID.get(0).getProductId());
		Assert.assertEquals(productCart.getUserId(), listProductCartInfoFoundByUserID.get(0).getUserId());
		Assert.assertEquals(productCart.getQuantity(), listProductCartInfoFoundByUserID.get(0).getQuantity());
	}
	
	@Test
    public void testGetProductCartInfoByUserID_NoData() {
		List<ProductCart> listProductCartInfoFoundByUserID = null;
		
		listProductCartInfoFoundByUserID = this.productCartRepository.getProductCartInfoByUserID("testuser");
		
		Assert.assertTrue(listProductCartInfoFoundByUserID != null && listProductCartInfoFoundByUserID.size() == 0);
	}
	
	@Test
    public void testInsertNewProductCart() {
		ProductCart productCart = new ProductCart();
		List<ProductCart> listProductCartInfoFoundByUserID = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		productCart.setUserId("testuser0");
		productCart.setProductId("test10001");
		productCart.setQuantity(99);
		productCart.setSize("XL,L,M,S");
		productCart.setColor("赤,青,黒");
		
		this.productCartRepository.insertNewProductCart(productCart.getUserId(), productCart.getProductId(), productCart.getQuantity(), productCart.getSize(), productCart.getColor());
		listProductCartInfoFoundByUserID = this.productCartRepository.getProductCartInfoByUserID(productCart.getUserId());
		
		Assert.assertTrue(listProductCartInfoFoundByUserID != null && listProductCartInfoFoundByUserID.size() == 1);
		Assert.assertEquals(productCart.getProductId(), listProductCartInfoFoundByUserID.get(0).getProductId());
		Assert.assertEquals(productCart.getUserId(), listProductCartInfoFoundByUserID.get(0).getUserId());
		Assert.assertEquals(productCart.getQuantity(), listProductCartInfoFoundByUserID.get(0).getQuantity());
		Assert.assertEquals(productCart.getSize(), listProductCartInfoFoundByUserID.get(0).getSize());
		Assert.assertEquals(productCart.getColor(), listProductCartInfoFoundByUserID.get(0).getColor());
		Assert.assertTrue(listProductCartInfoFoundByUserID.get(0).getCartRegistDt() != null &&
				dateformat.format(listProductCartInfoFoundByUserID.get(0).getCartRegistDt()).length() > 0);
	}
}
