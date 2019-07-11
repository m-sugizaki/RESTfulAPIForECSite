package com.cube.vn.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.PurchaseResults;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseResultsRepositoryTest {
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private PurchaseResultsRepository purchaseResultsRepository;
	
	@Test
    public void testInsertPurchaseResult() throws ParseException {
		PurchaseResults purchaseResults = new PurchaseResults();
		PurchaseResults returnPurchaseResults = new PurchaseResults();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));
		cal.add(Calendar.DATE, 2);
		Date twoDaysAfterDate = dateformat.parse(dateformat.format(cal.getTime()));
		List<PurchaseResults> listPurchaseResults = null;
		
		purchaseResults.setUserId("testuser");
		purchaseResults.setProductId("test1001");
		purchaseResults.setQuantity(99);
		purchaseResults.setSize("XL");
		purchaseResults.setColor("赤");
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setShippingAddressNo(0);
		
		this.purchaseResultsRepository.insertPurchaseResult(purchaseResults.getUserId(), purchaseResults.getProductId(), purchaseResults.getQuantity(), 
				purchaseResults.getSize(), purchaseResults.getColor(), purchaseResults.getOrderStatus(), 
				purchaseResults.getPaymentMethod(), purchaseResults.getPaymentNo(), purchaseResults.getShippingAddressNo());
		listPurchaseResults = this.purchaseResultsRepository.findAll();
		
		Assert.assertTrue(listPurchaseResults != null);
		Assert.assertTrue(listPurchaseResults.size() == 1);
		
		returnPurchaseResults = this.testEntityManager.find(PurchaseResults.class, listPurchaseResults.get(0).getOrderNo());
		
		Assert.assertTrue(returnPurchaseResults != null);
		Assert.assertTrue(returnPurchaseResults.getDeliveryPlanDt() != null);
		Assert.assertTrue(returnPurchaseResults.getDeliveryPlanDt().compareTo(twoDaysAfterDate) == 0);
		Assert.assertEquals(purchaseResults.getUserId(), returnPurchaseResults.getUserId());
		Assert.assertEquals(purchaseResults.getProductId(), returnPurchaseResults.getProductId());
		Assert.assertEquals(purchaseResults.getSize(), returnPurchaseResults.getSize());
		Assert.assertEquals(purchaseResults.getColor(), returnPurchaseResults.getColor());
		Assert.assertEquals(purchaseResults.getOrderStatus(), returnPurchaseResults.getOrderStatus());
		Assert.assertEquals(purchaseResults.getPaymentMethod(), returnPurchaseResults.getPaymentMethod());
		Assert.assertEquals(purchaseResults.getQuantity(), returnPurchaseResults.getQuantity());
		Assert.assertEquals(purchaseResults.getPaymentNo(), returnPurchaseResults.getPaymentNo());
		Assert.assertEquals(purchaseResults.getShippingAddressNo(), returnPurchaseResults.getShippingAddressNo());
		Assert.assertEquals(purchaseResults.getShippingAddressNo(), returnPurchaseResults.getShippingAddressNo());
		Assert.assertEquals(dateformat.format(curDate), dateformat.format(returnPurchaseResults.getOrderDt()));
	}
	
	@Test
    public void testUpdatePurchaseResultStatus() throws ParseException {
		PurchaseResults purchaseResults = new PurchaseResults();
		Optional<PurchaseResults> returnPurchaseResults = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));
		cal.add(Calendar.DATE, 2);
		Date twoDaysAfterDate = dateformat.parse(dateformat.format(cal.getTime()));
		List<PurchaseResults> listPurchaseResults = null;
		
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
		
		this.testEntityManager.persistAndFlush(purchaseResults);
		
		listPurchaseResults = this.purchaseResultsRepository.findAll();
		
		Assert.assertTrue(listPurchaseResults != null);
		Assert.assertTrue(listPurchaseResults.size() == 1);

		this.purchaseResultsRepository.updatePurchaseResultStatus(listPurchaseResults.get(0).getOrderNo(), "注文キャンセル");

		returnPurchaseResults = this.purchaseResultsRepository.findById(listPurchaseResults.get(0).getOrderNo());
		Assert.assertTrue(returnPurchaseResults.isPresent());
		Assert.assertTrue(returnPurchaseResults.get().getOrderStatus().equals("注文キャンセル"));
	}
	
	@Test
    public void testGetPurchaseResultsInfoByUserIDList_HasData() throws ParseException {
		PurchaseResults purchaseResults = new PurchaseResults();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));
		cal.add(Calendar.DATE, 2);
		Date twoDaysAfterDate = dateformat.parse(dateformat.format(cal.getTime()));
		List<PurchaseResults> listPurchaseResults = null;
		List<PurchaseResults> listReturnPurchaseResults = null;
		List<String> listUserId = new ArrayList<String>();
		
		listUserId.add("testuser");
		listUserId.add("testuser2");
		
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
		
		this.testEntityManager.persistAndFlush(purchaseResults);
		
		listPurchaseResults = this.purchaseResultsRepository.findAll();
		
		Assert.assertTrue(listPurchaseResults != null);
		Assert.assertTrue(listPurchaseResults.size() == 1);
		
		purchaseResults = listPurchaseResults.get(0);
		
		listReturnPurchaseResults = this.purchaseResultsRepository.getPurchaseResultsInfoByUserIDList(listUserId);
		
		Assert.assertTrue(listReturnPurchaseResults != null && listReturnPurchaseResults.size() == 1);
		Assert.assertEquals(purchaseResults, listReturnPurchaseResults.get(0));
	}
	
	@Test
    public void testGetPurchaseResultsInfoByUserIDList_NoData() {
		List<PurchaseResults> listPurchaseResults = null;
		List<String> listUserId = new ArrayList<String>();
		
		listUserId.add("testuser");
		listUserId.add("testuser2");
		listPurchaseResults = this.purchaseResultsRepository.getPurchaseResultsInfoByUserIDList(listUserId);

		Assert.assertTrue(listPurchaseResults != null && listPurchaseResults.size() == 0);
	}
}
