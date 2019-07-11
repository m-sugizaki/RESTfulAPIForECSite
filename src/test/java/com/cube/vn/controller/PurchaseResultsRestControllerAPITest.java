package com.cube.vn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import com.cube.vn.dao.PurchaseResults;
import com.cube.vn.service.PurchaseResultsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PurchaseResultsRestControllerAPI.class)
public class PurchaseResultsRestControllerAPITest {
	
	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PurchaseResultsService purchaseResultsService;
	
	@Test
	public void testInsertPurchaseResults() throws Exception {
		PurchaseResults purchaseResults = new PurchaseResults();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;

		purchaseResults.setUserId("testuser");
		purchaseResults.setProductId("test1001");
		purchaseResults.setQuantity(99);
		purchaseResults.setSize("XL");
		purchaseResults.setColor("赤");
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setShippingAddressNo(0);
		
		Mockito.doNothing().when(this.purchaseResultsService)
				.insertPurchaseResult(Mockito.any(PurchaseResults.class));
		requestBuilder = MockMvcRequestBuilders
				.post("/purchaseresults/insertPurchaseResult")
				.accept(MediaType.APPLICATION_JSON).content(PurchaseResultsRestControllerAPITest.OM.writeValueAsString(purchaseResults))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Mockito.verify(this.purchaseResultsService, Mockito.times(1)).insertPurchaseResult(Mockito.any(PurchaseResults.class));
	}
	
	@Test
	public void testUpdatePurchaseResultStatus() throws Exception {
		PurchaseResults purchaseResults = new PurchaseResults();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));
		cal.add(Calendar.DATE, 2);
		Date twoDaysAfterDate = dateformat.parse(dateformat.format(cal.getTime()));
		List<PurchaseResults> listPurchaseResults = new ArrayList<PurchaseResults>();
		List<PurchaseResults> listReturnPurchaseResults = null;
		
		purchaseResults.setOrderNo(1);
		purchaseResults.setUserId("testuser");
		purchaseResults.setProductId("test1001");
		purchaseResults.setQuantity(99);
		purchaseResults.setSize("XL");
		purchaseResults.setColor("赤");
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setShippingAddressNo(0);
		purchaseResults.setDeliveryPlanDt(twoDaysAfterDate);
		purchaseResults.setOrderDt(curDate);

		listPurchaseResults.add(purchaseResults);
		
		Mockito.doNothing().when(this.purchaseResultsService)
			.updatePurchaseResultStatus(listPurchaseResults.get(0).getOrderNo(), "注文キャンセル");
		requestBuilder = MockMvcRequestBuilders
				.put("/purchaseresults/updatePurchaseResultStatus")
				.accept(MediaType.APPLICATION_JSON).content(PurchaseResultsRestControllerAPITest.OM.writeValueAsString(purchaseResults))
				.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder).andReturn();
		
		Mockito.verify(this.purchaseResultsService, Mockito.times(1)).updatePurchaseResultStatus(Mockito.any(Integer.class), Mockito.any(String.class));
	
		listPurchaseResults.get(0).setOrderStatus("注文キャンセル");
		Mockito.when(this.purchaseResultsService.getPurchaseResultsInfoByUserIDList(
				Mockito.any(String.class))).thenReturn(listPurchaseResults);
		requestBuilder = MockMvcRequestBuilders
				.get("/purchaseresults/getPurchaseResultsInfoByUserIDList/testuser")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		PurchaseResults[] arrPurchaseResults = (PurchaseResultsRestControllerAPITest.OM.readValue(response.getContentAsString(), PurchaseResults[].class));
		listReturnPurchaseResults = new ArrayList<PurchaseResults>(Arrays.asList(arrPurchaseResults));
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(1, listReturnPurchaseResults.size());
		Assert.assertEquals("注文キャンセル", listReturnPurchaseResults.get(0).getOrderStatus());
		Mockito.verify(this.purchaseResultsService, Mockito.times(1)).getPurchaseResultsInfoByUserIDList(Mockito.any(String.class));
	}
	
	@Test
	public void testGetPurchaseResultsInfoByUserIDList() throws Exception {
		PurchaseResults purchaseResults = new PurchaseResults();
		PurchaseResults purchaseResults2 = new PurchaseResults();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));
		cal.add(Calendar.DATE, 2);
		Date twoDaysAfterDate = dateformat.parse(dateformat.format(cal.getTime()));
		List<PurchaseResults> listPurchaseResults = new ArrayList<PurchaseResults>();;
		List<PurchaseResults> listReturnPurchaseResults = null;
		
		purchaseResults.setOrderNo(1);
		purchaseResults.setUserId("testuser");
		purchaseResults.setProductId("test1001");
		purchaseResults.setQuantity(99);
		purchaseResults.setSize("XL");
		purchaseResults.setColor("赤");
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setShippingAddressNo(0);
		purchaseResults.setDeliveryPlanDt(twoDaysAfterDate);
		purchaseResults.setOrderDt(curDate);
		
		purchaseResults.setOrderNo(2);
		purchaseResults2.setUserId("testuser2");
		purchaseResults2.setProductId("test1002");
		purchaseResults2.setQuantity(99);
		purchaseResults2.setSize("L");
		purchaseResults2.setColor("赤");
		purchaseResults2.setOrderStatus("注文確定");
		purchaseResults2.setPaymentMethod("銀行引落し");
		purchaseResults2.setPaymentNo(0);
		purchaseResults2.setShippingAddressNo(0);
		purchaseResults2.setDeliveryPlanDt(twoDaysAfterDate);
		purchaseResults2.setOrderDt(curDate);

		listPurchaseResults.add(purchaseResults);
		listPurchaseResults.add(purchaseResults2);
		
		Mockito.when(this.purchaseResultsService.getPurchaseResultsInfoByUserIDList(
				Mockito.any(String.class))).thenReturn(listPurchaseResults);
		requestBuilder = MockMvcRequestBuilders
				.get("/purchaseresults/getPurchaseResultsInfoByUserIDList/testuser,testuser2")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		PurchaseResults[] arrPurchaseResults = (PurchaseResultsRestControllerAPITest.OM.readValue(response.getContentAsString(), PurchaseResults[].class));
		listReturnPurchaseResults = new ArrayList<PurchaseResults>(Arrays.asList(arrPurchaseResults));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listPurchaseResults.size(), listReturnPurchaseResults.size());
		for (int i = 0; i < listPurchaseResults.size(); i++) {
			Assert.assertEquals(listReturnPurchaseResults.get(i).getColor(), listPurchaseResults.get(i).getColor());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getOrderStatus(), listPurchaseResults.get(i).getOrderStatus());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getPaymentMethod(), listPurchaseResults.get(i).getPaymentMethod());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getProductId(), listPurchaseResults.get(i).getProductId());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getSize(), listPurchaseResults.get(i).getSize());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getUserId(), listPurchaseResults.get(i).getUserId());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getDeliveryCompletionDt(), listPurchaseResults.get(i).getDeliveryCompletionDt());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getDeliveryPlanDt(), listPurchaseResults.get(i).getDeliveryPlanDt());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getOrderDt(), listPurchaseResults.get(i).getOrderDt());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getOrderNo(), listPurchaseResults.get(i).getOrderNo());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getPaymentNo(), listPurchaseResults.get(i).getPaymentNo());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getQuantity(), listPurchaseResults.get(i).getQuantity());
	    	Assert.assertEquals(listReturnPurchaseResults.get(i).getShippingAddressNo(), listPurchaseResults.get(i).getShippingAddressNo());
		}
		Mockito.verify(this.purchaseResultsService, Mockito.times(1)).getPurchaseResultsInfoByUserIDList(Mockito.any(String.class));
	}
}
