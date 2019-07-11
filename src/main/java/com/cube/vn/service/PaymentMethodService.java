package com.cube.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.repository.PaymentMethodRepository;

@Service
@Transactional
public class PaymentMethodService {
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@CrossOrigin
	public Integer getMaxPaymentMethodNoOfUser(String userId) {
		Integer re = 0;
		re = this.paymentMethodRepository.getMaxPaymentMethodNoOfUser(userId);
		if (re == null) {
			re = 0;
		}
		return re;
	}
	
	@CrossOrigin
	public List<PaymentMethod> getPaymentMethodInfoByUserID(String userId) {
		return this.paymentMethodRepository.findByUserId(userId);
	}

	@CrossOrigin
	public Optional<PaymentMethod> getPaymentMethodInfoByKey(String userId, int paymentNo) {
		PaymentMethodPK pk = new PaymentMethodPK();
		pk.setUserId(userId);
		pk.setPaymentNo(paymentNo);
		Optional<PaymentMethod> paymentMethod = null;
		paymentMethod =  this.paymentMethodRepository.findById(pk);
		if (paymentMethod != null && paymentMethod.isPresent() ) {
			return paymentMethod;
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
		return this.paymentMethodRepository.save(paymentMethod);
	}
	
	@CrossOrigin
	public PaymentMethod insertNewPaymentMethod(PaymentMethod paymentMethod) {
		return this.paymentMethodRepository.save(paymentMethod);
	}
	
	@CrossOrigin
	public void deletePaymentMethod(PaymentMethodPK pk) {
		this.paymentMethodRepository.deleteById(pk);
	}
}
