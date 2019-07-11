package com.cube.vn.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.LoginHistoryPK;
import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;
import com.cube.vn.dao.User;
import com.cube.vn.dao.UserPK;
import com.cube.vn.dao.UserStore;
import com.cube.vn.dao.UserStorePK;
import com.cube.vn.repository.LoginHistoryRepository;
import com.cube.vn.repository.PaymentMethodRepository;
import com.cube.vn.repository.UserRepository;
import com.cube.vn.repository.UserStoreRepository;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@MockBean
	UserStoreRepository userStoreRepository;
	
	@MockBean
	LoginHistoryRepository loginHistoryRepository;
	
	@MockBean
	PaymentMethodRepository paymentMethodRepository;

	@Test
	public void testCheckAccount_HasData() throws NoSuchAlgorithmException {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		List<UserStore> listUserStore = new ArrayList<UserStore>();
		String hashtext = "";
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		listUserStore.add(userStore);

		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(userStore.getPassword().getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        hashtext = no.toString(16);
        
		Mockito.when(this.userStoreRepository.findByUserIdAndPass(userStore.getPrimaryKey().getUserId(), hashtext)).thenReturn(listUserStore);
		
		Assert.assertEquals(this.userService.checkAccount(userStore.getPrimaryKey().getUserId(), userStore.getPassword()), true);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).findByUserIdAndPass(userStore.getPrimaryKey().getUserId(), hashtext);
	}
	
	@Test
	public void testCheckAccount_NoData() throws NoSuchAlgorithmException {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		List<UserStore> listUserStore = new ArrayList<UserStore>();
		String hashtext = "";
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(userStore.getPassword().getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        hashtext = no.toString(16);
        
		Mockito.when(this.userStoreRepository.findByUserIdAndPass(userStore.getPrimaryKey().getUserId(), hashtext)).thenReturn(listUserStore);
		
		Assert.assertEquals(this.userService.checkAccount(userStore.getPrimaryKey().getUserId(), userStore.getPassword()), false);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).findByUserIdAndPass(userStore.getPrimaryKey().getUserId(), hashtext);
	}
	
	@Test
	public void testCheckUserExist_HasData() {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		Mockito.when(this.userStoreRepository.findById(Mockito.any(UserStorePK.class))).thenReturn(Optional.of(userStore));
		
		Assert.assertEquals(this.userService.checkUserExist(userStorePK.getUserId()), true);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).findById(Mockito.any(UserStorePK.class));
	}

	@Test
	public void testCheckUserExist_NoData() {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		Mockito.when(this.userStoreRepository.findById(Mockito.any(UserStorePK.class))).thenReturn(null);
		
		Assert.assertEquals(this.userService.checkUserExist(userStorePK.getUserId()), false);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).findById(Mockito.any(UserStorePK.class));
	}

	@Test
	public void testGetUserInfo() {
		User user = new User();
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 14, 0, 0, 0));
		user.setEmail("test@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userRepository.findByUserId(userPK.getUserId())).thenReturn(user);
		
		Assert.assertEquals(this.userService.getUserInfo(userPK.getUserId()), user);
		Mockito.verify(this.userRepository,Mockito.times(1)).findByUserId(userPK.getUserId());
	}
	
	@Test
	public void testGetUserLastLoginHistory() {
		LoginHistory loginHistory = new LoginHistory();
		LoginHistoryPK loginHistoryPK = new LoginHistoryPK();
		LoginHistory loginHistory2 = new LoginHistory();
		LoginHistoryPK loginHistoryPK2 = new LoginHistoryPK();
		List<LoginHistory> listLoginHistory = new ArrayList<LoginHistory>();
		
		loginHistoryPK.setUserId("testuser");
		loginHistoryPK.setLoginDt(TestUtil.getDate(2019, 5, 1, 12, 29, 1));
		loginHistory.setPrimaryKey(loginHistoryPK);
		loginHistoryPK2.setUserId("testuser");
		loginHistoryPK2.setLoginDt(TestUtil.getDate(2019, 5, 14, 12, 29, 1));
		loginHistory2.setPrimaryKey(loginHistoryPK2);
		
		listLoginHistory.add(loginHistory);
		listLoginHistory.add(loginHistory2);
		
		Mockito.when(this.loginHistoryRepository.getLoginTimeOfUser(loginHistoryPK.getUserId())).thenReturn(listLoginHistory);
		
		Assert.assertEquals(this.userService.getUserLastLoginHistory(loginHistoryPK.getUserId()), loginHistory2);
		Mockito.verify(this.loginHistoryRepository, Mockito.times(1)).getLoginTimeOfUser(loginHistoryPK.getUserId());
	}

	@Test
	public void testHasPaymentMethod_HasData() {
		String userId = "testuser";
		PaymentMethod paymentMethod = new PaymentMethod();
		PaymentMethodPK paymentMethodPK = new PaymentMethodPK();
		List<PaymentMethod> listPaymentMethods = new ArrayList<PaymentMethod>();
		
		paymentMethodPK.setUserId("testuser");
		paymentMethod.setCardHolderName("テストユーザ");
		paymentMethod.setCardNumber("1111-1111-1111-1111");
		paymentMethod.setExpirationDate(TestUtil.getDate(2019, 5, 1, 0, 0, 0));
		paymentMethod.setPaymentMethod("銀行振込み");
		paymentMethod.setPrimaryKey(paymentMethodPK);
		
		listPaymentMethods.add(paymentMethod);
		
		Mockito.when(this.paymentMethodRepository.findByUserId(userId)).thenReturn(listPaymentMethods);
		
		Assert.assertEquals(this.userService.hasPaymentMethod(userId), true);
		Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).findByUserId(userId);
	}
	
	@Test
	public void testHasPaymentMethod_NoData() {
		String userId = "testuser";
		List<PaymentMethod> listPaymentMethods = new ArrayList<PaymentMethod>();
		
		Mockito.when(this.paymentMethodRepository.findByUserId(userId)).thenReturn(listPaymentMethods);
		
		Assert.assertEquals(this.userService.hasPaymentMethod(userId), false);
		Mockito.verify(this.paymentMethodRepository, Mockito.times(1)).findByUserId(userId);
	}

	@Test
	public void testInsertLoginHistory() {
		LoginHistory loginHistory = new LoginHistory();
		LoginHistoryPK loginHistoryPK = new LoginHistoryPK();
		
		loginHistoryPK.setUserId("testuser");
		loginHistoryPK.setLoginDt(TestUtil.getDate(2019, 5, 14, 12, 45, 2));
		loginHistory.setPrimaryKey(loginHistoryPK);
		
		Mockito.when(this.loginHistoryRepository.save(loginHistory)).thenReturn(loginHistory);
		
		Assert.assertEquals(this.userService.insertLoginHistory(loginHistory), loginHistory);
		Mockito.verify(this.loginHistoryRepository, Mockito.times(1)).save(loginHistory);
	}

	@Test
	public void testUpdateAccount() {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		Mockito.when(this.userStoreRepository.save(userStore)).thenReturn(userStore);
		
		Assert.assertEquals(this.userService.updateAccount(userStore), userStore);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).save(userStore);
	}
	
	@Test 
	public void testInsertNewAccount() {
		UserStore userStore = new UserStore();
		UserStorePK userStorePK = new UserStorePK();
		
		userStorePK.setUserId("testuser");
		userStore.setPrimaryKey(userStorePK);
		userStore.setPassword("123456");
		
		Mockito.when(this.userStoreRepository.save(userStore)).thenReturn(userStore);
		
		Assert.assertEquals(this.userService.insertNewAccount(userStore), userStore);
		Mockito.verify(this.userStoreRepository, Mockito.times(1)).save(userStore);
	}
	
	@Test
	public void testInsertNewUserInfo() {
		User user = new User();
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(1988, 10, 12, 0, 0, 0));
		user.setEmail("test@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userRepository.save(user)).thenReturn(user);
		
		Assert.assertEquals(this.userService.insertNewUserInfo(user), user);
		Mockito.verify(this.userRepository, Mockito.times(1)).save(user);
	}
	
	@Test
	public void testUpdateUserInfo() {
		User user = new User();
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(1988, 12, 15, 0, 0, 0));
		user.setEmail("test@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userRepository.save(user)).thenReturn(user);
		
		Assert.assertEquals(this.userService.updateUserInfo(user), user);
		Mockito.verify(this.userRepository, Mockito.times(1)).save(user);
	}
	
	@Test
	public void testGetUserInfoByUserIdList() {
		User user = new User();
		User user2 = new User();
		UserPK userPK = new UserPK();
		UserPK userPK2 = new UserPK();
		List<User> listUser= new ArrayList<User>();
		List<String> listUserId = new ArrayList<String>();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(1988, 12, 15, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		userPK2.setUserId("testuser2");
		user2.setAddress1("都道府県、市町村、丁目番地２");
		user2.setAddress2("ビル、マンション２");
		user2.setBirthday(TestUtil.getDate(1989, 11, 10, 0, 0, 0));
		user2.setEmail("testuser2@gmail.com");
		user2.setMemberRank("一般");
		user2.setName("Test Test２");
		user2.setNickname("Test２");
		user2.setPhoneNumber("22222-2222-2222");
		user2.setPostalCode("222-2222");
		user2.setPrimaryKey(userPK2);
		
		listUser.add(user2);
		listUser.add(user);
		
		listUserId.add(userPK.getUserId());
		listUserId.add(userPK2.getUserId());
		
		Mockito.when(this.userRepository.getUserInfoByUserIdList(listUserId)).thenReturn(listUser);
		
		Assert.assertEquals(this.userService.getUserInfoByUserIdList(userPK.getUserId() + "," + userPK2.getUserId()), listUser);
		Mockito.verify(this.userRepository, Mockito.times(1)).getUserInfoByUserIdList(listUserId);
	}
}
