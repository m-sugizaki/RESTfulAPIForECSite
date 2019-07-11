package com.cube.vn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cube.vn.dao.ProductCart;
import com.cube.vn.service.ProductCartService;
import com.cube.vn.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductCartRestControllerAPI.class)
public class ProductCartRestControllerAPITest {
	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProductCartService productCartService;
	
	@Test
	public void testGetProductCartInfoByKey() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	ProductCart productCart = new ProductCart();
    	ProductCart productCartReturn = new ProductCart();
    	
    	productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 1, 12, 28, 2));
    	productCart.setColor("赤");
    	productCart.setProductCartId(1);
    	productCart.setQuantity(10);
    	productCart.setSize("X");
    	productCart.setUserId("testuser");
    	productCart.setProductId("test10001");
    	
    	Mockito.when(this.productCartService.getProductCartInfoByKey(Mockito.any(Integer.class))).thenReturn(Optional.of(productCart));
    	
    	requestBuilder = MockMvcRequestBuilders
    			.get("/productcart/getProductCartInfoByKey/1")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON);
    	result = this.mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	productCartReturn = ProductCartRestControllerAPITest.OM.readValue(response.getContentAsString(), ProductCart.class);
    	
    	Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
    	Assert.assertEquals(productCartReturn.getColor(), productCart.getColor());
    	Assert.assertEquals(productCartReturn.getProductId(), productCart.getProductId());
    	Assert.assertEquals(productCartReturn.getSize(), productCart.getSize());
    	Assert.assertEquals(productCartReturn.getUserId(), productCart.getUserId());
    	Assert.assertEquals(productCartReturn.getCartRegistDt(), productCart.getCartRegistDt());
    	Assert.assertEquals(productCartReturn.getProductCartId(), productCart.getProductCartId());
    	Assert.assertEquals(productCartReturn.getQuantity(), productCart.getQuantity());
    	
    	Mockito.verify(this.productCartService, Mockito.times(1)).getProductCartInfoByKey(Mockito.any(Integer.class));
	}
	
	@Test
	public void testGetProductCartInfoByUserID() throws Exception {
		String userId = "testuser";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	ProductCart productCart = new ProductCart();
    	ProductCart productCart2 = new ProductCart();
    	List<ProductCart> listProductCarts = new ArrayList<ProductCart>();
    	List<ProductCart> listProductCartsReturn = null;
    	
    	productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 1, 12, 28, 2));
    	productCart.setColor("赤");
    	productCart.setProductCartId(1);
    	productCart.setQuantity(10);
    	productCart.setSize("X");
    	productCart.setUserId("testuser");
    	productCart.setProductId("test10001");
    	productCart2.setCartRegistDt(TestUtil.getDate(2019, 5, 17, 12, 28, 2));
    	productCart2.setColor("青");
    	productCart2.setProductCartId(2);
    	productCart2.setQuantity(12);
    	productCart2.setSize("X");
    	productCart2.setUserId("testuser");
    	productCart2.setProductId("test10002");
    	listProductCarts.add(productCart);
    	listProductCarts.add(productCart2);
    	
    	Mockito.when(this.productCartService.getProductCartInfoByUserID(Mockito.any(String.class))).thenReturn(listProductCarts);
    	requestBuilder = MockMvcRequestBuilders
    			.get("/productcart/getProductCartInfoByUserID/" + userId)
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON);
    	result = this.mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	ProductCart[] productCarts = ProductCartRestControllerAPITest.OM.readValue(response.getContentAsString(), ProductCart[].class);
    	listProductCartsReturn = new ArrayList<ProductCart>(Arrays.asList(productCarts));
    	
    	Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
    	Assert.assertEquals(listProductCarts.size(), listProductCartsReturn.size());
    	for (int i = 0; i < listProductCartsReturn.size(); i++) {
    		Assert.assertEquals(listProductCarts.get(i).getColor(), listProductCartsReturn.get(i).getColor());
    		Assert.assertEquals(listProductCarts.get(i).getProductId(), listProductCartsReturn.get(i).getProductId());
    		Assert.assertEquals(listProductCarts.get(i).getSize(), listProductCartsReturn.get(i).getSize());
    		Assert.assertEquals(listProductCarts.get(i).getUserId(), listProductCartsReturn.get(i).getUserId());
    		Assert.assertEquals(listProductCarts.get(i).getCartRegistDt(), listProductCartsReturn.get(i).getCartRegistDt());
    		Assert.assertEquals(listProductCarts.get(i).getProductCartId(), listProductCartsReturn.get(i).getProductCartId());
    		Assert.assertEquals(listProductCarts.get(i).getQuantity(), listProductCartsReturn.get(i).getQuantity());
		}
    	Mockito.verify(this.productCartService,Mockito.times(1)).getProductCartInfoByUserID(userId);
	}
	
	@Test
	public void testUpdateProductCart() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	ProductCart productCart = new ProductCart();
    	ProductCart productCartReturn = new ProductCart();
    	
    	productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 1, 12, 28, 2));
    	productCart.setColor("赤");
    	productCart.setProductCartId(1);
    	productCart.setQuantity(10);
    	productCart.setSize("X");
    	productCart.setUserId("testuser");
    	productCart.setProductId("test10001");
    	
    	Mockito.when(this.productCartService.updateProductCart(Mockito.any(ProductCart.class))).thenReturn(productCart);
    	
    	requestBuilder = MockMvcRequestBuilders
    			.put("/productcart/updateProductCart")
    			.accept(MediaType.APPLICATION_JSON).content(ProductCartRestControllerAPITest.OM.writeValueAsString(productCart))
    			.contentType(MediaType.APPLICATION_JSON);
    	result = this.mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	productCartReturn = ProductCartRestControllerAPITest.OM.readValue(response.getContentAsString(), ProductCart.class);
    	
    	Assert.assertEquals(productCartReturn.getColor(), productCart.getColor());
    	Assert.assertEquals(productCartReturn.getProductId(), productCart.getProductId());
    	Assert.assertEquals(productCartReturn.getSize(), productCart.getSize());
    	Assert.assertEquals(productCartReturn.getUserId(), productCart.getUserId());
    	Assert.assertEquals(productCartReturn.getCartRegistDt(), productCart.getCartRegistDt());
    	Assert.assertEquals(productCartReturn.getProductCartId(), productCart.getProductCartId());
    	Assert.assertEquals(productCartReturn.getQuantity(), productCart.getQuantity());
    	
    	Mockito.verify(this.productCartService, Mockito.times(1)).updateProductCart(Mockito.any(ProductCart.class));
	}
	
	@Test
	public void testDeleteProductCart() throws Exception {
		int productCartId = 1;
		
		Mockito.doNothing().when(this.productCartService).deleteProductCart(Mockito.any(Integer.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/productcart/deleteProductCart/"+productCartId))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		Mockito.verify(this.productCartService, Mockito.times(1)).deleteProductCart(Mockito.any(Integer.class));
	}
	
	@Test
	public void testInsertNewProductCart() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
		ProductCart productCart = new ProductCart();
		
		productCart.setCartRegistDt(TestUtil.getDate(2019, 5, 1, 12, 28, 2));
    	productCart.setColor("赤");
    	productCart.setProductCartId(1);
    	productCart.setQuantity(10);
    	productCart.setSize("X");
    	productCart.setUserId("testuser");
    	productCart.setProductId("test10001");
    	
    	Mockito.doNothing().when(this.productCartService).insertNewProductCart(Mockito.any(ProductCart.class));
    	
    	requestBuilder = MockMvcRequestBuilders.post("/productcart/insertNewProductCart")
    			.accept(MediaType.APPLICATION_JSON)
    			.content(ProductCartRestControllerAPITest.OM.writeValueAsString(productCart))
    			.contentType(MediaType.APPLICATION_JSON);
    	result = this.mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	
    	Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    	
    	Mockito.verify(this.productCartService, Mockito.times(1)).insertNewProductCart(Mockito.any(ProductCart.class));
	}
}
