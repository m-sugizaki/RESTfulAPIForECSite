package com.cube.vn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;
import com.cube.vn.repository.ShippingAddressRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShippingAddressServiceTest {

	@MockBean
	ShippingAddressRepository shippingAddressRepository;

	@Autowired
	ShippingAddressService shippingAddressService;

	@Test
	public void testGetMaxShippingAddressNoOfUser() {
		String userId = "testuser";

		Mockito.when(this.shippingAddressRepository.getMaxShippingAddressNoOfUser(userId)).thenReturn(20);

		Assert.assertEquals(this.shippingAddressService.getMaxShippingAddressNoOfUser(userId).intValue(), 20);
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).getMaxShippingAddressNoOfUser(userId);
	}

	@Test
	public void testGetShippingAddressInfoByUserID() {
		String userId = "testuser";
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddressPK shippingAddressPK = new ShippingAddressPK();
		ShippingAddress shippingAddress2 = new ShippingAddress();
		ShippingAddressPK shippingAddressPK2 = new ShippingAddressPK();
		List<ShippingAddress> shippingAddresses = new ArrayList<ShippingAddress>();
		
		shippingAddressPK.setShippingAddressNo(1);
		shippingAddressPK.setUserId("testuser");
		shippingAddress.setPostalCode("111-1111");
		shippingAddress.setPhoneNumber("11111-1111-1111");
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		shippingAddressPK2.setShippingAddressNo(2);
		shippingAddressPK2.setUserId("testuser");
		shippingAddress2.setPostalCode("111-1111");
		shippingAddress2.setPhoneNumber("11111-1111-1111");
		shippingAddress2.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress2.setAddress2(null);
		shippingAddress2.setShippingAddressName("テストユーザのお届け先名２");
		
		shippingAddresses.add(shippingAddress);
		shippingAddresses.add(shippingAddress2);
		
		Mockito.when(this.shippingAddressRepository.findByUserId(userId)).thenReturn(shippingAddresses);
		
		Assert.assertEquals(this.shippingAddressService.getShippingAddressInfoByUserID(userId), shippingAddresses);
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).findByUserId(userId);
	}

	@Test
	public void testGetShippingAddressInfoByKey() {
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddressPK shippingAddressPK = new ShippingAddressPK();
		
		shippingAddressPK.setShippingAddressNo(1);
		shippingAddressPK.setUserId("testuser");
		shippingAddress.setPostalCode("111-1111");
		shippingAddress.setPhoneNumber("11111-1111-1111");
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		Mockito.when(this.shippingAddressRepository.findById(Mockito.any(ShippingAddressPK.class))).thenReturn(
				Optional.of(shippingAddress));
		
		Assert.assertEquals(this.shippingAddressService.getShippingAddressInfoByKey(
				shippingAddressPK.getUserId(), shippingAddressPK.getShippingAddressNo()),shippingAddress);
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).findById(Mockito.any(ShippingAddressPK.class));
	}

	@Test
	public void testUpdateShippingAddress_ShouldUpdateEntry() {
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddressPK shippingAddressPK = new ShippingAddressPK();
		
		shippingAddressPK.setShippingAddressNo(1);
		shippingAddressPK.setUserId("testuser");
		shippingAddress.setPostalCode("111-1111");
		shippingAddress.setPhoneNumber("11111-1111-1111");
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		Mockito.when(this.shippingAddressRepository.save(shippingAddress)).thenReturn(shippingAddress);
		
		Assert.assertEquals(this.shippingAddressService.updateShippingAddress(shippingAddress), shippingAddress);
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).save(shippingAddress);
	}
	
	@Test
	public void testInsertNewShippingAddress() {
		ShippingAddress shippingAddress = new ShippingAddress();
		ShippingAddressPK shippingAddressPK = new ShippingAddressPK();
		
		shippingAddressPK.setShippingAddressNo(1);
		shippingAddressPK.setUserId("testuser");
		shippingAddress.setPostalCode("111-1111");
		shippingAddress.setPhoneNumber("11111-1111-1111");
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setAddress2(null);
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		Mockito.when(this.shippingAddressRepository.save(shippingAddress)).thenReturn(shippingAddress);
		
		Assert.assertEquals(this.shippingAddressService.insertNewShippingAddress(shippingAddress), shippingAddress);
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).save(shippingAddress);
	}

	@Test
	public void testDeleteShippingAddress() {
		ShippingAddressPK shippingAddressPK = new ShippingAddressPK();
		
		shippingAddressPK.setShippingAddressNo(1);
		shippingAddressPK.setUserId("testuser");
		
		Mockito.doNothing().when(this.shippingAddressRepository).deleteById(shippingAddressPK);
		this.shippingAddressService.deleteShippingAddress(shippingAddressPK);
		
		Mockito.verify(this.shippingAddressRepository, Mockito.times(1)).deleteById(shippingAddressPK);
	}
}
