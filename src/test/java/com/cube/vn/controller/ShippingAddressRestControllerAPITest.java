package com.cube.vn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;
import com.cube.vn.service.ShippingAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShippingAddressRestControllerAPI.class)
public class ShippingAddressRestControllerAPITest {

	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShippingAddressService shippingAddressService;
	
	@Test
	public void testInsertNewShippingAddress() throws Exception {
		ShippingAddressPK pk = new ShippingAddressPK();
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddress shippingAddressReturn = new ShippingAddress();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;
		
		pk.setShippingAddressNo(1);
		pk.setUserId("testuser");
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setPhoneNumber("09111-4951-7644");
		shippingAddress.setPostalCode("123-4567");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		Mockito.when(this.shippingAddressService.insertNewShippingAddress(
				Mockito.any(ShippingAddress.class))).thenReturn(shippingAddress);
		
		requestBuilder = MockMvcRequestBuilders
				.post("/shippingaddress/insertNewShippingAddress")
				.accept(MediaType.APPLICATION_JSON).content(ShippingAddressRestControllerAPITest.OM.writeValueAsString(shippingAddress))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		shippingAddressReturn = ShippingAddressRestControllerAPITest.OM.readValue(response.getContentAsString(), ShippingAddress.class);
		
		Assert.assertEquals(shippingAddressReturn.getAddress1(), shippingAddress.getAddress1());
		Assert.assertEquals(shippingAddressReturn.getAddress2(), shippingAddress.getAddress2());
		Assert.assertEquals(shippingAddressReturn.getPhoneNumber(), shippingAddress.getPhoneNumber());
		Assert.assertEquals(shippingAddressReturn.getPostalCode(), shippingAddress.getPostalCode());
		Assert.assertEquals(shippingAddressReturn.getShippingAddressName(), shippingAddress.getShippingAddressName());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getUserId(), shippingAddress.getPrimaryKey().getUserId());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getShippingAddressNo(), shippingAddress.getPrimaryKey().getShippingAddressNo());
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Mockito.verify(this.shippingAddressService, Mockito.times(1)).insertNewShippingAddress(Mockito.any(ShippingAddress.class));
	}
	
	@Test
	public void testUpdateShippingAddress() throws Exception {
		
		ShippingAddressPK pk = new ShippingAddressPK();
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddress shippingAddressReturn = new ShippingAddress();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;
		
		pk.setShippingAddressNo(1);
		pk.setUserId("testuser");
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setPhoneNumber("09111-4951-7644");
		shippingAddress.setPostalCode("123-4567");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		Mockito.when(this.shippingAddressService.updateShippingAddress(
				Mockito.any(ShippingAddress.class))).thenReturn(shippingAddress);
		
		requestBuilder = MockMvcRequestBuilders
				.put("/shippingaddress/updateShippingAddress")
				.accept(MediaType.APPLICATION_JSON).content(ShippingAddressRestControllerAPITest.OM.writeValueAsString(shippingAddress))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		shippingAddressReturn = ShippingAddressRestControllerAPITest.OM.readValue(response.getContentAsString(), ShippingAddress.class);
		
		Assert.assertEquals(shippingAddressReturn.getAddress1(), shippingAddress.getAddress1());
		Assert.assertEquals(shippingAddressReturn.getAddress2(), shippingAddress.getAddress2());
		Assert.assertEquals(shippingAddressReturn.getPhoneNumber(), shippingAddress.getPhoneNumber());
		Assert.assertEquals(shippingAddressReturn.getPostalCode(), shippingAddress.getPostalCode());
		Assert.assertEquals(shippingAddressReturn.getShippingAddressName(), shippingAddress.getShippingAddressName());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getUserId(), shippingAddress.getPrimaryKey().getUserId());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getShippingAddressNo(), shippingAddress.getPrimaryKey().getShippingAddressNo());
		Mockito.verify(this.shippingAddressService, Mockito.times(1)).updateShippingAddress(Mockito.any(ShippingAddress.class));
	}
	
	@Test
	public void testDeleteShippingAddress() throws Exception {
		
		ShippingAddressPK pk = new ShippingAddressPK();
		
		pk.setShippingAddressNo(1);
		pk.setUserId("testuser");
		
		Mockito.doNothing().when(this.shippingAddressService).deleteShippingAddress(Mockito.any(ShippingAddressPK.class));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/shippingaddress/deleteShippingAddress/testuser/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		Mockito.verify(this.shippingAddressService, Mockito.times(1)).deleteShippingAddress(Mockito.any(ShippingAddressPK.class));
	}
	
	@Test
	public void testGetMaxShippingAddressNoOfUser() throws Exception {
		
		String userId = "testuser";
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	
    	Mockito.when(this.shippingAddressService.getMaxShippingAddressNoOfUser(
    			Mockito.any(String.class))).thenReturn(20);
    	requestBuilder = MockMvcRequestBuilders
    			.get("/shippingaddress/getMaxShippingAddressNoOfUser/" + userId)
    			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    	result = mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	
    	Assert.assertEquals(ShippingAddressRestControllerAPITest.OM.readValue(response.getContentAsString(), Integer.class).intValue(), 20);
    	Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
    	Mockito.verify(this.shippingAddressService, Mockito.times(1)).getMaxShippingAddressNoOfUser(Mockito.any(String.class));
	}

	@Test
	public void testGetShippingAddressInfoByKey() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	ShippingAddressPK pk = new ShippingAddressPK();
    	ShippingAddress shippingAddress = new ShippingAddress();
    	ShippingAddress shippingAddressReturn = new ShippingAddress();
    	
		pk.setShippingAddressNo(1);
		pk.setUserId("testuser");
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setPhoneNumber("09111-4951-7644");
		shippingAddress.setPostalCode("123-4567");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");

		Mockito.when(this.shippingAddressService.getShippingAddressInfoByKey(
				Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(shippingAddress);
		requestBuilder = MockMvcRequestBuilders
				.get("/shippingaddress/getShippingAddressInfoByKey/testuser/1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		shippingAddressReturn = ShippingAddressRestControllerAPITest.OM.readValue(response.getContentAsString(), ShippingAddress.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(shippingAddressReturn.getAddress1(), shippingAddress.getAddress1());
		Assert.assertEquals(shippingAddressReturn.getAddress2(), shippingAddress.getAddress2());
		Assert.assertEquals(shippingAddressReturn.getPhoneNumber(), shippingAddress.getPhoneNumber());
		Assert.assertEquals(shippingAddressReturn.getPostalCode(), shippingAddress.getPostalCode());
		Assert.assertEquals(shippingAddressReturn.getShippingAddressName(), shippingAddress.getShippingAddressName());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getUserId(), shippingAddress.getPrimaryKey().getUserId());
		Assert.assertEquals(shippingAddressReturn.getPrimaryKey().getShippingAddressNo(), shippingAddress.getPrimaryKey().getShippingAddressNo());
		Mockito.verify(this.shippingAddressService, Mockito.times(1)).getShippingAddressInfoByKey(Mockito.any(String.class), Mockito.any(Integer.class));
	}
	
	@Test
	public void testGetShippingAddressInfoByUserID() throws Exception {
		String userId = "testuser";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	ShippingAddressPK pk = new ShippingAddressPK();
    	ShippingAddressPK pk2 = new ShippingAddressPK();
    	ShippingAddress shippingAddress = new ShippingAddress();
    	ShippingAddress shippingAddress2 = new ShippingAddress();
    	List<ShippingAddress> listShippingAddress = new ArrayList<ShippingAddress>();
    	List<ShippingAddress> listShippingAddressReturn = null;

		pk.setShippingAddressNo(1);
		pk.setUserId("testuser");
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setPhoneNumber("09111-4951-7644");
		shippingAddress.setPostalCode("123-4567");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		pk2.setShippingAddressNo(2);
		pk2.setUserId("testuser");
		shippingAddress2.setPrimaryKey(pk2);
		shippingAddress2.setAddress1("都道府県、市町村、丁目番地２");
		shippingAddress2.setAddress2(null);
		shippingAddress2.setPhoneNumber("09111-4951-7644");
		shippingAddress2.setPostalCode("123-4567");
		shippingAddress2.setShippingAddressName("テストユーザのお届け先名２");
		
		listShippingAddress.add(shippingAddress);
		listShippingAddress.add(shippingAddress2);
		
		Mockito.when(this.shippingAddressService.getShippingAddressInfoByUserID(
				Mockito.any(String.class))).thenReturn(listShippingAddress);

		requestBuilder = MockMvcRequestBuilders
				.get("/shippingaddress/getShippingAddressInfoByUserID/" + userId)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		ShippingAddress[] arrShippingAddress = (ShippingAddressRestControllerAPITest.OM.readValue(response.getContentAsString(), ShippingAddress[].class));
		listShippingAddressReturn = new ArrayList<ShippingAddress>(Arrays.asList(arrShippingAddress));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listShippingAddress.size(), listShippingAddressReturn.size());
		for (int i = 0; i < listShippingAddress.size(); i++) {
			Assert.assertEquals(listShippingAddress.get(i).getAddress1(), listShippingAddressReturn.get(i).getAddress1());
			Assert.assertEquals(listShippingAddress.get(i).getAddress2(), listShippingAddressReturn.get(i).getAddress2());
			Assert.assertEquals(listShippingAddress.get(i).getPhoneNumber(), listShippingAddressReturn.get(i).getPhoneNumber());
			Assert.assertEquals(listShippingAddress.get(i).getPostalCode(), listShippingAddressReturn.get(i).getPostalCode());
			Assert.assertEquals(listShippingAddress.get(i).getShippingAddressName(), listShippingAddressReturn.get(i).getShippingAddressName());
			Assert.assertEquals(listShippingAddress.get(i).getPrimaryKey().getUserId(), listShippingAddressReturn.get(i).getPrimaryKey().getUserId());
			Assert.assertEquals(listShippingAddress.get(i).getPrimaryKey().getShippingAddressNo(), listShippingAddressReturn.get(i).getPrimaryKey().getShippingAddressNo());
		}
		Mockito.verify(this.shippingAddressService, Mockito.times(1)).getShippingAddressInfoByUserID(Mockito.any(String.class));
	}
}
