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

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.service.PaymentMethodService;
import com.cube.vn.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PaymentMethodRestControllerAPI.class)
public class PaymentMethodRestControllerAPITest {
	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PaymentMethodService paymentMethodService;
	
	@Test
	public void testInsertNewPaymentMethod() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod();
		PaymentMethod paymentMethodReturn = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
        
    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");

		Mockito.when(this.paymentMethodService.insertNewPaymentMethod(
				Mockito.any(PaymentMethod.class))).thenReturn(paymentMethod);

		requestBuilder = MockMvcRequestBuilders
				.post("/paymentmethod/insertNewPaymentMethod")
				.accept(MediaType.APPLICATION_JSON).content(PaymentMethodRestControllerAPITest.OM.writeValueAsString(paymentMethod))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		paymentMethodReturn = PaymentMethodRestControllerAPITest.OM.readValue(response.getContentAsString(), PaymentMethod.class);

		Assert.assertEquals(paymentMethodReturn.getExpirationDate(), paymentMethod.getExpirationDate());
		Assert.assertEquals(paymentMethodReturn.getPaymentMethod(), paymentMethod.getPaymentMethod());
		Assert.assertEquals(paymentMethodReturn.getCardHolderName(), paymentMethod.getCardHolderName());
		Assert.assertEquals(paymentMethodReturn.getCardNumber(), paymentMethod.getCardNumber());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getUserId(), paymentMethod.getPrimaryKey().getUserId());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getPaymentNo(), paymentMethod.getPrimaryKey().getPaymentNo());
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Mockito.verify(this.paymentMethodService, Mockito.times(1)).insertNewPaymentMethod(Mockito.any(PaymentMethod.class));
	}
	
	@Test
	public void testUpdatePaymentMethod() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod();
		PaymentMethod paymentMethodReturn = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
        
    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");

		Mockito.when(this.paymentMethodService.updatePaymentMethod(
				Mockito.any(PaymentMethod.class))).thenReturn(paymentMethod);

		requestBuilder = MockMvcRequestBuilders
				.put("/paymentmethod/updatePaymentMethod")
				.accept(MediaType.APPLICATION_JSON).content(PaymentMethodRestControllerAPITest.OM.writeValueAsString(paymentMethod))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		paymentMethodReturn = PaymentMethodRestControllerAPITest.OM.readValue(response.getContentAsString(), PaymentMethod.class);

		Assert.assertEquals(paymentMethodReturn.getExpirationDate(), paymentMethod.getExpirationDate());
		Assert.assertEquals(paymentMethodReturn.getPaymentMethod(), paymentMethod.getPaymentMethod());
		Assert.assertEquals(paymentMethodReturn.getCardHolderName(), paymentMethod.getCardHolderName());
		Assert.assertEquals(paymentMethodReturn.getCardNumber(), paymentMethod.getCardNumber());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getUserId(), paymentMethod.getPrimaryKey().getUserId());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getPaymentNo(), paymentMethod.getPrimaryKey().getPaymentNo());
		Mockito.verify(this.paymentMethodService, Mockito.times(1)).updatePaymentMethod(Mockito.any(PaymentMethod.class));
	}
	
	@Test
    public void testDeletePaymentMethod() throws Exception {
		PaymentMethodPK paymentMethodPK = new PaymentMethodPK();

	    paymentMethodPK.setUserId("testuser");
	    paymentMethodPK.setPaymentNo(1);

		Mockito.doNothing().when(this.paymentMethodService).deletePaymentMethod(Mockito.any(PaymentMethodPK.class));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/paymentmethod/deletePaymentMethod/testuser/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(this.paymentMethodService, Mockito.times(1)).deletePaymentMethod(Mockito.any(PaymentMethodPK.class));
    }
	
	@Test
	public void testGetMaxPaymentMethodNoOfUser() throws Exception {
		String userId = "testuser";
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	
		Mockito.when(this.paymentMethodService.getMaxPaymentMethodNoOfUser(
				Mockito.any(String.class))).thenReturn(20);

		requestBuilder = MockMvcRequestBuilders
				.get("/paymentmethod/getMaxPaymentMethodNoOfUser/" + userId)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();

		Assert.assertEquals(PaymentMethodRestControllerAPITest.OM.readValue(response.getContentAsString(), Integer.class).intValue(), 
				20);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Mockito.verify(this.paymentMethodService, Mockito.times(1)).getMaxPaymentMethodNoOfUser(Mockito.any(String.class));
	}

	@Test
	public void testGetPaymentMethodInfoByKey() throws Exception {
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethod paymentMethodReturn = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();

    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
    	
		Mockito.when(this.paymentMethodService.getPaymentMethodInfoByKey(
				Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(Optional.of(paymentMethod));

		requestBuilder = MockMvcRequestBuilders
				.get("/paymentmethod/getPaymentMethodInfoByKey/testuser/1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		paymentMethodReturn = PaymentMethodRestControllerAPITest.OM.readValue(response.getContentAsString(), PaymentMethod.class);
        
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(paymentMethodReturn.getExpirationDate(), paymentMethod.getExpirationDate());
		Assert.assertEquals(paymentMethodReturn.getPaymentMethod(), paymentMethod.getPaymentMethod());
		Assert.assertEquals(paymentMethodReturn.getCardHolderName(), paymentMethod.getCardHolderName());
		Assert.assertEquals(paymentMethodReturn.getCardNumber(), paymentMethod.getCardNumber());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getUserId(), paymentMethod.getPrimaryKey().getUserId());
		Assert.assertEquals(paymentMethodReturn.getPrimaryKey().getPaymentNo(), paymentMethod.getPrimaryKey().getPaymentNo());
		Mockito.verify(this.paymentMethodService, Mockito.times(1)).getPaymentMethodInfoByKey(Mockito.any(String.class), Mockito.any(Integer.class));
	}
	
	@Test
	public void testGetPaymentMethodInfoByUserID() throws Exception {
		String userId = "testuser";
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
    	PaymentMethod paymentMethod2 = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK2 = new PaymentMethodPK();
    	List<PaymentMethod> listPaymentMethod = new ArrayList<PaymentMethod>();
    	List<PaymentMethod> listPaymentMethodReturn = null;

    	paymentMethodPK.setUserId(userId);
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
    	paymentMethodPK2.setUserId(userId);
    	paymentMethodPK2.setPaymentNo(2);
    	paymentMethod2.setPrimaryKey(paymentMethodPK2);
    	paymentMethod2.setPaymentMethod("商品代引き");
    	paymentMethod2.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod2.setCardNumber("2222-2222-2222-2222");
    	paymentMethod2.setCardHolderName("テストユーザー2");
    	listPaymentMethod.add(paymentMethod);
    	listPaymentMethod.add(paymentMethod2);
    	
		Mockito.when(this.paymentMethodService.getPaymentMethodInfoByUserID(
				Mockito.any(String.class))).thenReturn(listPaymentMethod);

		requestBuilder = MockMvcRequestBuilders
				.get("/paymentmethod/getPaymentMethodInfoByUserID/" + userId)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		PaymentMethod[] arrPaymentMethod = (PaymentMethodRestControllerAPITest.OM.readValue(response.getContentAsString(), PaymentMethod[].class));
		listPaymentMethodReturn = new ArrayList<PaymentMethod>(Arrays.asList(arrPaymentMethod));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listPaymentMethod.size(), listPaymentMethodReturn.size());
		for (int i = 0; i < listPaymentMethod.size(); i++) {
			Assert.assertEquals(listPaymentMethod.get(i).getExpirationDate(), listPaymentMethodReturn.get(i).getExpirationDate());
			Assert.assertEquals(listPaymentMethod.get(i).getPaymentMethod(), listPaymentMethodReturn.get(i).getPaymentMethod());
			Assert.assertEquals(listPaymentMethod.get(i).getCardHolderName(), listPaymentMethodReturn.get(i).getCardHolderName());
			Assert.assertEquals(listPaymentMethod.get(i).getCardNumber(), listPaymentMethodReturn.get(i).getCardNumber());
			Assert.assertEquals(listPaymentMethod.get(i).getPrimaryKey().getUserId(), listPaymentMethodReturn.get(i).getPrimaryKey().getUserId());
			Assert.assertEquals(listPaymentMethod.get(i).getPrimaryKey().getPaymentNo(), listPaymentMethodReturn.get(i).getPrimaryKey().getPaymentNo());
		}
		Mockito.verify(this.paymentMethodService, Mockito.times(1)).getPaymentMethodInfoByUserID(Mockito.any(String.class));
	}
}
