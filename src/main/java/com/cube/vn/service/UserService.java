package com.cube.vn.service;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.User;
import com.cube.vn.dao.UserStore;
import com.cube.vn.dao.UserStorePK;
import com.cube.vn.repository.LoginHistoryRepository;
import com.cube.vn.repository.PaymentMethodRepository;
import com.cube.vn.repository.UserRepository;
import com.cube.vn.repository.UserStoreRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserStoreRepository userStoreRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoginHistoryRepository loginHistoryRepository;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@CrossOrigin
	public boolean checkAccount(String userId, String password) {
		boolean checkResult = true;
		List<UserStore> userStoreLst = null;
		String hashtext = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		userStoreLst = this.userStoreRepository.findByUserIdAndPass(userId, hashtext);
		if (userStoreLst == null || userStoreLst.size() == 0) {
			checkResult = false;
		}		
		return checkResult;
	}
	
	@CrossOrigin
	public boolean checkUserExist(String userId) {
		boolean checkResult = false;
		UserStorePK pk = new UserStorePK();
		pk.setUserId(userId);
		Optional<UserStore> userStore = this.userStoreRepository.findById(pk);
		if (userStore != null && userStore.isPresent() && userStore.get().getPrimaryKey().getUserId().equals(userId)) {
			checkResult = true;
		}
		return checkResult;
	}
	
	@CrossOrigin
	public User getUserInfo(String userId) {
		User user = null;
		user = this.userRepository.findByUserId(userId);
		return user;
	}
	
	@CrossOrigin
	public LoginHistory getUserLastLoginHistory(String userId) {
		List<LoginHistory> loginHistoryLst = null;
		LoginHistory loginHistory = null;
		loginHistoryLst = this.loginHistoryRepository.getLoginTimeOfUser(userId);
		if (loginHistoryLst != null && loginHistoryLst.size() > 0) {
			for (LoginHistory item : loginHistoryLst) {
				if (loginHistory == null || loginHistory.getPrimaryKey().getLoginDt().compareTo(item.getPrimaryKey().getLoginDt()) < 0) {
					loginHistory = item;
				}
			}
		}
		return loginHistory;
	}
	
	@CrossOrigin
	public boolean hasPaymentMethod(String userId) {
		boolean checkResult = true;
		List<PaymentMethod> paymentMethodLst = null;
		paymentMethodLst = this.paymentMethodRepository.findByUserId(userId);
		if (paymentMethodLst == null || paymentMethodLst.size() == 0) {
			checkResult = false;
		}
		return checkResult;
	}
	
	@CrossOrigin
	public LoginHistory insertLoginHistory(LoginHistory loginHistory) {
		return this.loginHistoryRepository.save(loginHistory);
	}

	@CrossOrigin
	public UserStore updateAccount(UserStore userStore) {
		String hashtext = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(userStore.getPassword().getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            userStore.setPassword(hashtext);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return this.userStoreRepository.save(userStore);
	}
	
	@CrossOrigin
	public UserStore insertNewAccount(UserStore userStore) {
		String hashtext = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(userStore.getPassword().getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            userStore.setPassword(hashtext);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return this.userStoreRepository.save(userStore);
	}
	
	@CrossOrigin
	public User updateUserInfo(User user) {
		return this.userRepository.save(user);
	}
	
	@CrossOrigin
	public User insertNewUserInfo(User user) {
		return this.userRepository.save(user);
	}
	
	@CrossOrigin
	public List<User> getUserInfoByUserIdList(String userIdLst) {
		List<String> listUserId = null;
		if (userIdLst.trim().length() > 0) {
			listUserId = new ArrayList<String>(Arrays.asList(userIdLst.split(",")));
		}
		if (listUserId == null) {
			return null;
		}
		return this.userRepository.getUserInfoByUserIdList(listUserId);
	}
}
