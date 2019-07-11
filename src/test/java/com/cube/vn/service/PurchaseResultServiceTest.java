package com.cube.vn.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.PurchaseResults;
import com.cube.vn.repository.PurchaseResultsRepository;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseResultServiceTest {

	@MockBean
	PurchaseResultsRepository purchaseResultsRepository;

	@Autowired
	PurchaseResultsService purchaseResultsService;

	@Test
	public void testGetPurchaseResultsInfoByUserIDList() {
		PurchaseResults purchaseResults = new PurchaseResults();
		PurchaseResults purchaseResults2 = new PurchaseResults();
		List<PurchaseResults> purchaseResultsList = new ArrayList<PurchaseResults>();
		List<String> userIdLst = new ArrayList<String>();
		List<PurchaseResults> listReturnPurchaseResults = null;

		purchaseResults.setColor("Red");
		purchaseResults.setDeliveryCompletionDt(TestUtil.getDate(2019, 5, 3, 0, 0, 0));
		purchaseResults.setDeliveryPlanDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderNo(1);
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setProductId("TEST100001");
		purchaseResults.setQuantity(10);
		purchaseResults.setShippingAddressNo(0);
		purchaseResults.setSize("L");
		purchaseResults.setUserId("testuser");
		
		purchaseResults2.setColor("Blue");
		purchaseResults2.setDeliveryCompletionDt(TestUtil.getDate(2019, 5, 3, 0, 0, 0));
		purchaseResults2.setDeliveryPlanDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults2.setOrderDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults2.setOrderNo(2);
		purchaseResults2.setOrderStatus("注文確定");
		purchaseResults2.setPaymentMethod("銀行引落し");
		purchaseResults2.setPaymentNo(0);
		purchaseResults2.setProductId("TEST100001");
		purchaseResults2.setQuantity(10);
		purchaseResults2.setShippingAddressNo(0);
		purchaseResults2.setSize("L");
		purchaseResults2.setUserId("testuser2");

		purchaseResultsList.add(purchaseResults);
		purchaseResultsList.add(purchaseResults2);
		userIdLst.add("testuser");
		userIdLst.add("testuser2");
		String userId = "testuser,testuser2";

		Mockito.when(this.purchaseResultsRepository.getPurchaseResultsInfoByUserIDList(userIdLst))
				.thenReturn(purchaseResultsList);
		listReturnPurchaseResults = this.purchaseResultsService.getPurchaseResultsInfoByUserIDList(userId);
		Assert.assertEquals(listReturnPurchaseResults, purchaseResultsList);
		Mockito.verify(this.purchaseResultsRepository, Mockito.times(1)).getPurchaseResultsInfoByUserIDList(userIdLst);
	}

	@Test
	public void testInsertPurchaseResult_ShouldInsertEntry() {
		PurchaseResults purchaseResults = new PurchaseResults();

		purchaseResults.setColor("Red");
		purchaseResults.setDeliveryCompletionDt(TestUtil.getDate(2019, 5, 3, 0, 0, 0));
		purchaseResults.setDeliveryPlanDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderNo(1);
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setProductId("TEST100001");
		purchaseResults.setQuantity(10);
		purchaseResults.setShippingAddressNo(0);
		purchaseResults.setSize("L");
		purchaseResults.setUserId("testuser");

		Mockito.doNothing().when(this.purchaseResultsRepository).insertPurchaseResult(purchaseResults.getUserId(),
				purchaseResults.getProductId(), purchaseResults.getQuantity(), purchaseResults.getSize(),
				purchaseResults.getColor(), purchaseResults.getOrderStatus(), purchaseResults.getPaymentMethod(),
				purchaseResults.getPaymentNo(), purchaseResults.getShippingAddressNo());

		this.purchaseResultsService.insertPurchaseResult(purchaseResults);

		Mockito.verify(this.purchaseResultsRepository, Mockito.times(1)).insertPurchaseResult(
				purchaseResults.getUserId(), purchaseResults.getProductId(), purchaseResults.getQuantity(),
				purchaseResults.getSize(), purchaseResults.getColor(), purchaseResults.getOrderStatus(),
				purchaseResults.getPaymentMethod(), purchaseResults.getPaymentNo(),
				purchaseResults.getShippingAddressNo());
	}

	@Test
	public void testUpdatePurchaseResultStatus() {
		PurchaseResults purchaseResults = new PurchaseResults();

		purchaseResults.setColor("Red");
		purchaseResults.setDeliveryCompletionDt(TestUtil.getDate(2019, 5, 3, 0, 0, 0));
		purchaseResults.setDeliveryPlanDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderDt(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		purchaseResults.setOrderNo(1);
		purchaseResults.setOrderStatus("注文確定");
		purchaseResults.setPaymentMethod("銀行引落し");
		purchaseResults.setPaymentNo(0);
		purchaseResults.setProductId("TEST100001");
		purchaseResults.setQuantity(10);
		purchaseResults.setShippingAddressNo(0);
		purchaseResults.setSize("L");
		purchaseResults.setUserId("testuser");

		Mockito.doNothing().when(this.purchaseResultsRepository).updatePurchaseResultStatus(purchaseResults.getOrderNo(), purchaseResults.getOrderStatus());

		this.purchaseResultsService.updatePurchaseResultStatus(purchaseResults.getOrderNo(), purchaseResults.getOrderStatus());

		Mockito.verify(this.purchaseResultsRepository, Mockito.times(1)).updatePurchaseResultStatus(purchaseResults.getOrderNo(), purchaseResults.getOrderStatus());
	}
}
