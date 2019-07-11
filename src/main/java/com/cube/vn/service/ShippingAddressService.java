package com.cube.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;
import com.cube.vn.repository.ShippingAddressRepository;

@Service
@Transactional
public class ShippingAddressService {
	@Autowired
	ShippingAddressRepository shippingAddressRepository;
	
	@CrossOrigin
	public Integer getMaxShippingAddressNoOfUser(String userId) {
		Integer re = 0;
		re = this.shippingAddressRepository.getMaxShippingAddressNoOfUser(userId);
		if (re == null) {
			re = 0;
		}
		return re;
	}
	
	@CrossOrigin
	public List<ShippingAddress> getShippingAddressInfoByUserID(String userId) {
		return this.shippingAddressRepository.findByUserId(userId);
	}

	@CrossOrigin
	public ShippingAddress getShippingAddressInfoByKey(String userId, int shippingAddressNo) {
		ShippingAddressPK pk = new ShippingAddressPK();
		pk.setUserId(userId);
		pk.setShippingAddressNo(shippingAddressNo);
		Optional<ShippingAddress> shippingAddress = null;
		shippingAddress =  this.shippingAddressRepository.findById(pk);
		if (shippingAddress != null && shippingAddress.isPresent() ) {
			return shippingAddress.get();
		} else {
			return null;
		}
	}

	@CrossOrigin
	public ShippingAddress updateShippingAddress(ShippingAddress shippingAddress) {
		return this.shippingAddressRepository.save(shippingAddress);
	}
	
	@CrossOrigin
	public ShippingAddress insertNewShippingAddress(ShippingAddress shippingAddress) {
		return this.shippingAddressRepository.save(shippingAddress);
	}
	
	@CrossOrigin
	public void deleteShippingAddress(ShippingAddressPK pk) {
		this.shippingAddressRepository.deleteById(pk);
	}
}
