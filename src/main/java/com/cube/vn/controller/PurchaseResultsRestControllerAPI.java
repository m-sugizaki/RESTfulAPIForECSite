package com.cube.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cube.vn.dao.PurchaseResults;
import com.cube.vn.service.PurchaseResultsService;

@RestController
@RequestMapping("purchaseresults")
public class PurchaseResultsRestControllerAPI {
    @Autowired
    PurchaseResultsService purchaseResultsService;
    
    @CrossOrigin
	@PostMapping(path = "insertPurchaseResult")
    @ResponseStatus(HttpStatus.CREATED)
    void insertPurchaseResult(@RequestBody PurchaseResults purchaseResults) {
    	this.purchaseResultsService.insertPurchaseResult(purchaseResults);
    }
    
    @CrossOrigin
    @PutMapping(path = "updatePurchaseResultStatus")
    void updatePurchaseResultStatus(@RequestBody PurchaseResults purchaseResults) {
    	this.purchaseResultsService.updatePurchaseResultStatus(purchaseResults.getOrderNo(), purchaseResults.getOrderStatus());
    }
    
	@CrossOrigin
	@GetMapping(path = "getPurchaseResultsInfoByUserIDList/{userIdLst}")
    List<PurchaseResults> getPurchaseResultsInfoByUserIDList(@PathVariable String userIdLst) {
    	return this.purchaseResultsService.getPurchaseResultsInfoByUserIDList(userIdLst);
    }
}
