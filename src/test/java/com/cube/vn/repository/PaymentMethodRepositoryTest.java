package com.cube.vn.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentMethodRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Test
    public void testFindByUserId_HasData() {
		PaymentMethodPK pk = new PaymentMethodPK();
		PaymentMethod paymentMethod = new PaymentMethod();
		List<PaymentMethod> listUserFoundById = null;
		
		pk.setUserId("testuser");
		pk.setPaymentNo(1);
		paymentMethod.setPrimaryKey(pk);
		paymentMethod.setCardHolderName("テストユーザー");
		paymentMethod.setCardNumber("7819-4564-1231-1211");
		paymentMethod.setExpirationDate(TestUtil.getDate(2019, 12, 01, 0, 0, 0));
		paymentMethod.setPaymentMethod("銀行振込み");
		this.testEntityManager.persist(paymentMethod);
		this.testEntityManager.flush();
		listUserFoundById = this.paymentMethodRepository.findByUserId(pk.getUserId());
		
		Assert.assertTrue(listUserFoundById != null && listUserFoundById.size() == 1);
		Assert.assertEquals(paymentMethod.getPrimaryKey().getUserId(), listUserFoundById.get(0).getPrimaryKey().getUserId());
		Assert.assertEquals(paymentMethod.getPrimaryKey().getPaymentNo(), listUserFoundById.get(0).getPrimaryKey().getPaymentNo());
		Assert.assertEquals(paymentMethod.getCardHolderName(), listUserFoundById.get(0).getCardHolderName());
		Assert.assertEquals(paymentMethod.getCardNumber(), listUserFoundById.get(0).getCardNumber());
		Assert.assertEquals(paymentMethod.getExpirationDate(), listUserFoundById.get(0).getExpirationDate());
		Assert.assertEquals(paymentMethod.getPaymentMethod(), listUserFoundById.get(0).getPaymentMethod());
	}
	
	@Test
    public void testFindByUserId_NoData() {
		List<PaymentMethod> listUserFoundById = null;

		listUserFoundById = this.paymentMethodRepository.findByUserId("testuser");
		
		Assert.assertTrue(listUserFoundById != null && listUserFoundById.size() == 0);
	}
	
	@Test
    public void testMaxPaymentMethodNoOfUser_HasData() {
		PaymentMethodPK pk = new PaymentMethodPK();
		PaymentMethod paymentMethod = new PaymentMethod();
		int maxPaymentMethodNoOfUser = 0;
		
		pk.setUserId("testuser");
		pk.setPaymentNo(1);
		paymentMethod.setPrimaryKey(pk);
		paymentMethod.setCardHolderName("テストユーザー");
		paymentMethod.setCardNumber("7819-4564-1231-1211");
		paymentMethod.setExpirationDate(TestUtil.getDate(2019, 12, 01, 0, 0, 0));
		paymentMethod.setPaymentMethod("銀行振込み");
		this.testEntityManager.persist(paymentMethod);
		
		pk.setUserId("testuser");
		pk.setPaymentNo(2);
		paymentMethod.setPrimaryKey(pk);
		paymentMethod.setCardHolderName("テストユーザー");
		paymentMethod.setCardNumber("7819-4564-1231-1211");
		paymentMethod.setExpirationDate(TestUtil.getDate(2019, 12, 5, 0, 0, 0));
		paymentMethod.setPaymentMethod("商品代引き");
		this.testEntityManager.persist(paymentMethod);
		this.testEntityManager.flush();
		
		maxPaymentMethodNoOfUser = this.paymentMethodRepository.getMaxPaymentMethodNoOfUser(pk.getUserId());
		
		Assert.assertTrue(maxPaymentMethodNoOfUser == 2);
	}
	
	@Test
    public void testMaxPaymentMethodNoOfUser_NoData() {
		Integer maxPaymentMethodNoOfUser = 0;
	
		maxPaymentMethodNoOfUser = this.paymentMethodRepository.getMaxPaymentMethodNoOfUser("testuser");

		Assert.assertTrue(maxPaymentMethodNoOfUser == null);
	}
}
