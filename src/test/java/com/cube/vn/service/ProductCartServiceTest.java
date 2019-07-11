package com.cube.vn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.ProductCart;
import com.cube.vn.repository.ProductCartRepository;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCartServiceTest {

	@MockBean
	ProductCartRepository productCartRepository;
	
	@Autowired
	ProductCartService productCartService;
	
	@Test
	public void testInsertNewProductCart_ShouldInsertEntry() {
		ProductCart productCart = new ProductCart();
		
		productCart.setProductCartId(1);
		productCart.setUserId("testuser");
		productCart.setProductId("TEST100001");
		productCart.setSize("X");
		productCart.setColor("赤");
		productCart.setQuantity(10);
		productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 14, 12, 15, 0));

		Mockito.doNothing().when(this.productCartRepository).insertNewProductCart(productCart.getUserId()
				, productCart.getProductId(), productCart.getQuantity()
				, productCart.getSize(), productCart.getColor());
		this.productCartService.insertNewProductCart(productCart);
		Mockito.verify(this.productCartRepository,Mockito.times(1)).insertNewProductCart(productCart.getUserId()
				, productCart.getProductId(), productCart.getQuantity()
				, productCart.getSize(), productCart.getColor());
	}
	
	@Test
	public void testUpdateProductCart_ShouldUpdateEntry()
	{
		ProductCart productCart = new ProductCart();
		 
		productCart.setProductCartId(1);
		productCart.setUserId("testuser");
		productCart.setProductId("TEST100001");
		productCart.setSize("X");
		productCart.setColor("赤");
		productCart.setQuantity(10);
		productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 14, 12, 15, 0));

		Mockito.when(this.productCartRepository.save(productCart)).thenReturn(productCart);
		
		Assert.assertEquals(this.productCartService.updateProductCart(productCart), productCart);
		Mockito.verify(this.productCartRepository, Mockito.times(1)).save(productCart);
	}
	
	@Test
	public void testDeleteProductCart() {
		int product_cart_id = 1;
		
		Mockito.doNothing().when(this.productCartRepository).deleteById(product_cart_id);
		this.productCartService.deleteProductCart(product_cart_id);
		
		Mockito.verify(this.productCartRepository,Mockito.times(1)).deleteById(product_cart_id);
	}
	
	@Test
	public void testGetProductCartInfoByUserID() {
		ProductCart productCart = new ProductCart();
		ProductCart productCart2 = new ProductCart();
		List<ProductCart> listProductCarts  = new ArrayList<ProductCart>();
		
		productCart.setProductCartId(1);
		productCart.setUserId("testuser");
		productCart.setProductId("TEST100001");
		productCart.setSize("X");
		productCart.setColor("赤");
		productCart.setQuantity(10);
		productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 14, 12, 15, 0));
		
		productCart2.setProductCartId(2);
		productCart2.setUserId("testuser");
		productCart2.setProductId("TEST101001");
		productCart2.setSize("M");
		productCart2.setColor("青");
		productCart2.setQuantity(11);
		productCart2.setCartRegistDt(TestUtil.getDate(2019, 5, 15, 12, 20, 0));
		
		listProductCarts.add(productCart);
		listProductCarts.add(productCart2);
		
		Mockito.when(this.productCartService.getProductCartInfoByUserID("testuser")).thenReturn(listProductCarts);
		
		Assert.assertEquals(this.productCartService.getProductCartInfoByUserID("testuser"), listProductCarts);
		Mockito.verify(this.productCartRepository, Mockito.times(1)).getProductCartInfoByUserID("testuser");
	}
	
	@Test
	public void testGetProductCartInfoByKey() {
		ProductCart productCart = new ProductCart();
		 
		productCart.setProductCartId(1);
		productCart.setUserId("testuser");
		productCart.setProductId("TEST100001");
		productCart.setSize("X");
		productCart.setColor("赤");
		productCart.setQuantity(10);
		productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 14, 12, 15, 0));
		
		Optional<ProductCart> optional = Optional.of(productCart);
		
		Mockito.when(this.productCartRepository.findById(1)).thenReturn(optional);
		
		Assert.assertEquals(this.productCartService.getProductCartInfoByKey(1).get(), optional.get());
		Mockito.verify(this.productCartRepository, Mockito.times(1)).findById(1);
	}
}
