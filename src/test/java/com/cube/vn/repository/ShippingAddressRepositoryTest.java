package com.cube.vn.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShippingAddressRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Test
    public void testFindByUserId_HasData() {
		ShippingAddressPK pk = new ShippingAddressPK();
		ShippingAddress shippingAddress = new ShippingAddress();
		List<ShippingAddress> listFoundByUserId = null;
		
		pk.setUserId("testuser4");
		pk.setShippingAddressNo(1);
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setPhoneNumber("09112-4952-7644");
		shippingAddress.setPostalCode("456-7897");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		this.testEntityManager.persist(shippingAddress);
		this.testEntityManager.flush();
		listFoundByUserId = this.shippingAddressRepository.findByUserId(pk.getUserId());

		Assert.assertTrue(listFoundByUserId != null && listFoundByUserId.size() == 1);
		Assert.assertEquals(shippingAddress.getPrimaryKey().getUserId(), listFoundByUserId.get(0).getPrimaryKey().getUserId());
		Assert.assertEquals(shippingAddress.getPrimaryKey().getShippingAddressNo(), listFoundByUserId.get(0).getPrimaryKey().getShippingAddressNo());
		Assert.assertEquals(shippingAddress.getAddress1(), listFoundByUserId.get(0).getAddress1());
		Assert.assertEquals(shippingAddress.getPhoneNumber(), listFoundByUserId.get(0).getPhoneNumber());
		Assert.assertEquals(shippingAddress.getPostalCode(), listFoundByUserId.get(0).getPostalCode());
		Assert.assertEquals(shippingAddress.getShippingAddressName(), listFoundByUserId.get(0).getShippingAddressName());
		this.testEntityManager.clear();
	}
	
	@Test
    public void testFindByUserId_NoData() {
		List<ShippingAddress> listFoundByUserId = null;
		
		listFoundByUserId = this.shippingAddressRepository.findByUserId("testuser");
		
		Assert.assertTrue(listFoundByUserId != null && listFoundByUserId.size() == 0);
	}
	
	@Test
    public void testGetMaxShippingAddressNoOfUser_HasData() {
		ShippingAddressPK pk = new ShippingAddressPK();
		ShippingAddress shippingAddress = new ShippingAddress();
		Integer maxShippingAddressNoOfUser = 0;
		
		pk.setUserId("testuser4");
		pk.setShippingAddressNo(1);
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地");
		shippingAddress.setPhoneNumber("09112-4952-7644");
		shippingAddress.setPostalCode("456-7897");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名");
		
		this.testEntityManager.persist(shippingAddress);
		
		pk.setUserId("testuser4");
		pk.setShippingAddressNo(2);
		shippingAddress.setPrimaryKey(pk);
		shippingAddress.setAddress1("都道府県、市町村、丁目番地２");
		shippingAddress.setPhoneNumber("08112-5952-8644");
		shippingAddress.setPostalCode("123-7897");
		shippingAddress.setShippingAddressName("テストユーザのお届け先名２");
		
		this.testEntityManager.persist(shippingAddress);
		this.testEntityManager.flush();
		
		maxShippingAddressNoOfUser = this.shippingAddressRepository.getMaxShippingAddressNoOfUser(pk.getUserId());

		Assert.assertTrue(maxShippingAddressNoOfUser != null && maxShippingAddressNoOfUser == 2);
	}
	
	@Test
    public void testGetMaxShippingAddressNoOfUser_NoData() {
		Integer maxShippingAddressNoOfUser = 0;
		maxShippingAddressNoOfUser = this.shippingAddressRepository.getMaxShippingAddressNoOfUser("testuser");
		Assert.assertTrue(maxShippingAddressNoOfUser == null);
	}
}
