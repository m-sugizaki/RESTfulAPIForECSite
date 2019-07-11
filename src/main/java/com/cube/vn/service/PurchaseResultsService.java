package com.cube.vn.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.PurchaseResults;
import com.cube.vn.repository.PurchaseResultsRepository;

@Service
@Transactional
public class PurchaseResultsService {
	@Autowired
	PurchaseResultsRepository purchaseResultsRepository;
	
	@CrossOrigin
	public void insertPurchaseResult(PurchaseResults purchaseResults) {
		this.purchaseResultsRepository.insertPurchaseResult(purchaseResults.getUserId(), purchaseResults.getProductId(), 
				purchaseResults.getQuantity(), purchaseResults.getSize(), purchaseResults.getColor(),
				purchaseResults.getOrderStatus(), purchaseResults.getPaymentMethod(), purchaseResults.getPaymentNo(),
				purchaseResults.getShippingAddressNo());
	}

	@CrossOrigin
	public List<PurchaseResults> getPurchaseResultsInfoByUserIDList(String userIdLst) {
		List<String> listUserId = null;
		if (userIdLst.trim().length() > 0) {
			listUserId = new ArrayList<String>(Arrays.asList(userIdLst.split(",")));
		}
		if (listUserId == null) {
			return null;
		}
		return this.purchaseResultsRepository.getPurchaseResultsInfoByUserIDList(listUserId);
	}
	
	@CrossOrigin
	public void updatePurchaseResultStatus(int orderNo, String orderStatus) {
		this.purchaseResultsRepository.updatePurchaseResultStatus(orderNo, orderStatus);
	}
}
