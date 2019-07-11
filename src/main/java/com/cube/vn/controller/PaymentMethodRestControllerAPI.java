package com.cube.vn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.service.PaymentMethodService;

@RestController
@RequestMapping("paymentmethod")
public class PaymentMethodRestControllerAPI {
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@CrossOrigin
	@GetMapping(path = "getMaxPaymentMethodNoOfUser/{userId}")
	Integer getMaxPaymentMethodNoOfUser(@PathVariable String userId) {
        return this.paymentMethodService.getMaxPaymentMethodNoOfUser(userId);
    }
	
	@CrossOrigin
	@GetMapping(path = "getPaymentMethodInfoByUserID/{userId}")
    List<PaymentMethod> getPaymentMethodInfo(@PathVariable String userId) {
        return this.paymentMethodService.getPaymentMethodInfoByUserID(userId);
    }
    
	@CrossOrigin
	@GetMapping(path = "getPaymentMethodInfoByKey/{userId}/{paymentNo}")
    PaymentMethod getPaymentMethodInfoByKey(@PathVariable String userId, @PathVariable int paymentNo) {
		Optional<PaymentMethod> paymentMethod = this.paymentMethodService.getPaymentMethodInfoByKey(userId, paymentNo);
		if (paymentMethod != null && paymentMethod.isPresent() ) {
			return paymentMethod.get();
		} else {
			return null;
		}
    }
	
	@CrossOrigin
    @PutMapping(path = "updatePaymentMethod")
	PaymentMethod updatePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
    	return this.paymentMethodService.updatePaymentMethod(paymentMethod);
    }
	
	@CrossOrigin
	@DeleteMapping(path = "deletePaymentMethod/{userId}/{paymentNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePaymentMethod(@PathVariable String userId, @PathVariable Integer paymentNo) {
		PaymentMethodPK pk = new PaymentMethodPK();
		pk.setUserId(userId);
		pk.setPaymentNo(paymentNo);
    	this.paymentMethodService.deletePaymentMethod(pk);
    }
    
	@CrossOrigin
	@PostMapping(path = "insertNewPaymentMethod")
    @ResponseStatus(HttpStatus.CREATED)
	PaymentMethod insertNewPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		Integer maxPaymentNo = this.paymentMethodService.getMaxPaymentMethodNoOfUser(paymentMethod.getPrimaryKey().getUserId());
		paymentMethod.getPrimaryKey().setPaymentNo(maxPaymentNo + 1);
    	return this.paymentMethodService.insertNewPaymentMethod(paymentMethod);
    }
}
