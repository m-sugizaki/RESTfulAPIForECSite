package com.cube.vn.controller;

import java.util.List;

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

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;
import com.cube.vn.service.ShippingAddressService;

@RestController
@RequestMapping("shippingaddress")
public class ShippingAddressRestControllerAPI {
	@Autowired
	ShippingAddressService shippingAddressService;
	
	@CrossOrigin
	@GetMapping(path = "getMaxShippingAddressNoOfUser/{userId}")
	Integer getMaxShippingAddressNoOfUser(@PathVariable String userId) {
        return this.shippingAddressService.getMaxShippingAddressNoOfUser(userId);
    }
	
	@CrossOrigin
	@GetMapping(path = "getShippingAddressInfoByUserID/{userId}")
    List<ShippingAddress> getShippingAddressInfoByUserID(@PathVariable String userId) {
        return this.shippingAddressService.getShippingAddressInfoByUserID(userId);
    }
    
	@CrossOrigin
	@GetMapping(path = "getShippingAddressInfoByKey/{userId}/{shippingAddressNo}")
	ShippingAddress getShippingAddressInfoByKey(@PathVariable String userId, @PathVariable int shippingAddressNo) {
        return this.shippingAddressService.getShippingAddressInfoByKey(userId, shippingAddressNo);
    }
	
	@CrossOrigin
    @PutMapping(path = "updateShippingAddress")
	ShippingAddress updateShippingAddress(@RequestBody ShippingAddress shippingAddress) {
    	return this.shippingAddressService.updateShippingAddress(shippingAddress);
    }
	
	@CrossOrigin
	@DeleteMapping(path = "deleteShippingAddress/{userId}/{shippingAddressNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteShippingAddress(@PathVariable String userId, @PathVariable Integer shippingAddressNo) {
		ShippingAddressPK pk = new ShippingAddressPK();
		pk.setUserId(userId);
		pk.setShippingAddressNo(shippingAddressNo);
    	this.shippingAddressService.deleteShippingAddress(pk);
    }
    
	@CrossOrigin
	@PostMapping(path = "insertNewShippingAddress")
    @ResponseStatus(HttpStatus.CREATED)
	ShippingAddress insertNewShippingAddress(@RequestBody ShippingAddress shippingAddress) {
		Integer maxShippingAddressNo = this.shippingAddressService.getMaxShippingAddressNoOfUser(shippingAddress.getPrimaryKey().getUserId());
		shippingAddress.getPrimaryKey().setShippingAddressNo(maxShippingAddressNo + 1);
    	return this.shippingAddressService.insertNewShippingAddress(shippingAddress);
    }
}
