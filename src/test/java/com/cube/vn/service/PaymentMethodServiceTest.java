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

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.repository.PaymentMethodRepository;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentMethodServiceTest {
    @MockBean
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    PaymentMethodService paymentMethodService;
    
    @Test
    public void testInsertNewPaymentMethod_ShouldInsertEntry() {
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
        
    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
		
	    Mockito.when(this.paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);
	    
	    Assert.assertEquals(this.paymentMethodService.insertNewPaymentMethod(paymentMethod), paymentMethod);
	    Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).save(paymentMethod);
    }
    
    @Test
    public void testUpdatePaymentMethod_ShouldUpdateEntry() {
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();

    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
		
	    Mockito.when(this.paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

	    Assert.assertEquals(this.paymentMethodService.updatePaymentMethod(paymentMethod), paymentMethod);
	    Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).save(paymentMethod);
    }
    
    @Test
    public void testDeletePaymentMethod_ShouldDeleteEntry() {
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();

    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);

    	Mockito.doNothing().when(this.paymentMethodRepository).deleteById(paymentMethodPK);
    	this.paymentMethodService.deletePaymentMethod(paymentMethodPK);

    	Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).deleteById(paymentMethodPK);
    }
    
    @Test
    public void testGetMaxPaymentMethodNoOfUser() {
    	String userId = "testuser";
    	
    	Mockito.when(this.paymentMethodRepository.getMaxPaymentMethodNoOfUser(userId)).thenReturn(20);
    	
    	Assert.assertEquals(this.paymentMethodService.getMaxPaymentMethodNoOfUser(userId).intValue(), 20);
    	Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).getMaxPaymentMethodNoOfUser(userId);
    }
    
    @Test
    public void testGetPaymentMethodInfoByUserID_OK() {
    	String userId = "testuser";
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
    	PaymentMethod paymentMethod2 = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK2 = new PaymentMethodPK();
    	List<PaymentMethod> listPaymentMethod = new ArrayList<PaymentMethod>();

    	paymentMethodPK.setUserId(userId);
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
    	paymentMethodPK2.setUserId(userId);
    	paymentMethodPK2.setPaymentNo(2);
    	paymentMethod2.setPrimaryKey(paymentMethodPK2);
    	paymentMethod2.setPaymentMethod("商品代引き");
    	paymentMethod2.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod2.setCardNumber("2222-2222-2222-2222");
    	paymentMethod2.setCardHolderName("テストユーザー2");
    	listPaymentMethod.add(paymentMethod);
    	listPaymentMethod.add(paymentMethod2);
    	
    	Mockito.when(this.paymentMethodRepository.findByUserId(userId)).thenReturn(listPaymentMethod);
    	
    	Assert.assertEquals(this.paymentMethodService.getPaymentMethodInfoByUserID(userId), listPaymentMethod);
    	Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).findByUserId(userId);
    }
    
    @Test
    public void testGetPaymentMethodInfoByKey_OK() {
    	PaymentMethod paymentMethod = new PaymentMethod();
    	PaymentMethodPK paymentMethodPK = new PaymentMethodPK();

    	paymentMethodPK.setUserId("testuser");
    	paymentMethodPK.setPaymentNo(1);
    	paymentMethod.setPrimaryKey(paymentMethodPK);
    	paymentMethod.setPaymentMethod("銀行振込み");
    	paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
    	paymentMethod.setCardNumber("1111-1111-1111-1111");
    	paymentMethod.setCardHolderName("テストユーザー");
    	
    	Mockito.when(this.paymentMethodRepository.findById(Mockito.any(PaymentMethodPK.class))).thenReturn(Optional.of(paymentMethod));
    	
    	Assert.assertEquals(this.paymentMethodService.getPaymentMethodInfoByKey(
    			paymentMethodPK.getUserId(), paymentMethodPK.getPaymentNo()), Optional.of(paymentMethod));
    	Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).findById(Mockito.any(PaymentMethodPK.class));
    }
}
