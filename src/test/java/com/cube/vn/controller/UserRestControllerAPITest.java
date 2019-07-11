package com.cube.vn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.LoginHistoryPK;
import com.cube.vn.dao.User;
import com.cube.vn.dao.UserPK;
import com.cube.vn.dao.UserStore;
import com.cube.vn.dao.UserStorePK;
import com.cube.vn.service.JwtService;
import com.cube.vn.service.UserService;
import com.cube.vn.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserRestControllerAPI.class)
public class UserRestControllerAPITest {

	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	JwtService jwtService;
	
	@MockBean
	UserService userService;
	
	@Test
	public void testCheckAccount() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	UserStore userStore = new UserStore();
    	UserStorePK userStorePK = new  UserStorePK();
    	boolean check;
    	
    	userStorePK.setUserId("testuser");
    	userStore.setPassword("123456");
    	userStore.setPrimaryKey(userStorePK);
    	
    	Mockito.when(this.userService.checkAccount(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
    	
    	requestBuilder = MockMvcRequestBuilders
				.post("/user/checkAccount")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(userStore))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		check = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), Boolean.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(check, true);
		
		Mockito.verify(this.userService, Mockito.times(1)).checkAccount(Mockito.any(String.class), Mockito.any(String.class));
	}
	
	@Test
	public void testGetUserInfo() throws Exception {
		String token = "abcd1523";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	User user = new User();
    	User userReturn = null;
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userService.getUserInfo(Mockito.any(String.class))).thenReturn(user);
		Mockito.when(this.jwtService.getUsernameFromToken(Mockito.any(String.class))).thenReturn(user.getPrimaryKey().getUserId());
		
		requestBuilder = MockMvcRequestBuilders
				.get("/user/" + token)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		userReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), User.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(userReturn.getPrimaryKey().getUserId(), user.getPrimaryKey().getUserId());
		Assert.assertEquals(userReturn.getAddress1(), user.getAddress1());
		Assert.assertEquals(userReturn.getAddress2(), user.getAddress2());
		Assert.assertEquals(userReturn.getBirthday(), user.getBirthday());
		Assert.assertEquals(userReturn.getEmail(), user.getEmail());
		Assert.assertEquals(userReturn.getMemberRank(), user.getMemberRank());
		Assert.assertEquals(userReturn.getName(), user.getName());
		Assert.assertEquals(userReturn.getNickname(), user.getNickname());
		Assert.assertEquals(userReturn.getPhoneNumber(), user.getPhoneNumber());
		Assert.assertEquals(userReturn.getPostalCode(), user.getPostalCode());
		
		Mockito.verify(this.userService, Mockito.times(1)).getUserInfo(Mockito.any(String.class));
		Mockito.verify(this.jwtService , Mockito.times(1)).getUsernameFromToken(Mockito.any(String.class));	
	}
	
	@Test
	public void testGetUserLastLoginHistory() throws Exception {
		String token= "abcd1523";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
        LoginHistory loginHistory = new LoginHistory();
        LoginHistory loginHistoryReturn = new LoginHistory();
        LoginHistoryPK loginHistoryPK = new LoginHistoryPK();
        
        loginHistoryPK.setLoginDt(TestUtil.getDate(2019, 5, 1, 12, 20, 0));
        loginHistoryPK.setUserId("testuser");
        loginHistory.setPrimaryKey(loginHistoryPK);
        
        Mockito.when(this.userService.getUserLastLoginHistory(Mockito.any(String.class))).thenReturn(loginHistory);
        Mockito.when(this.userService.checkAccount(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
        Mockito.when(this.jwtService.getUsernameFromToken(Mockito.any(String.class))).thenReturn("testuser");
        Mockito.when(this.jwtService.getPasswordFromToken(Mockito.any(String.class))).thenReturn("123456");
        
        requestBuilder = MockMvcRequestBuilders
				.get("/user/getUserLastLoginHistory/" + token)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		loginHistoryReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), LoginHistory.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(loginHistoryReturn.getPrimaryKey().getUserId(), loginHistory.getPrimaryKey().getUserId());
		Assert.assertEquals(loginHistoryReturn.getPrimaryKey().getLoginDt(), loginHistory.getPrimaryKey().getLoginDt());
		
		Mockito.verify(this.userService, Mockito.times(1)).getUserLastLoginHistory(Mockito.any(String.class));
		Mockito.verify(this.userService, Mockito.times(1)).checkAccount(Mockito.any(String.class), Mockito.any(String.class));
		Mockito.verify(this.jwtService, Mockito.times(1)).getPasswordFromToken(Mockito.any(String.class));
		Mockito.verify(this.jwtService, Mockito.times(1)).getUsernameFromToken(Mockito.any(String.class));
	}

	@Test
	public void testHasPaymentMethod() throws Exception {
		String userId= "testuser";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	boolean check;
    	
    	Mockito.when(this.userService.hasPaymentMethod(Mockito.any(String.class))).thenReturn(true);
    	
    	requestBuilder = MockMvcRequestBuilders
				.get("/user/hasPaymentMethod/" + userId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		check = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), Boolean.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(check,true);
		
		Mockito.verify(this.userService, Mockito.times(1)).hasPaymentMethod(Mockito.any(String.class));
	}

	@Test
	public void testInsertLoginHistory() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
        LoginHistory loginHistory = new LoginHistory();
        LoginHistory loginHistoryReturn = new LoginHistory();
        LoginHistoryPK loginHistoryPK = new LoginHistoryPK();
        
        loginHistoryPK.setLoginDt(TestUtil.getDate(2019, 5, 1, 12, 29, 3));
        loginHistoryPK.setUserId("testuser");
        loginHistory.setPrimaryKey(loginHistoryPK);
        
        Mockito.when(this.userService.insertLoginHistory(Mockito.any(LoginHistory.class))).thenReturn(loginHistory);
        
        requestBuilder = MockMvcRequestBuilders
				.post("/user/insertLoginHistory")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(loginHistory))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		loginHistoryReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), LoginHistory.class);
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertEquals(loginHistoryReturn.getPrimaryKey().getUserId(), loginHistory.getPrimaryKey().getUserId());
		Assert.assertEquals(loginHistoryReturn.getPrimaryKey().getLoginDt(), loginHistory.getPrimaryKey().getLoginDt());
		
		Mockito.verify(this.userService, Mockito.times(1)).insertLoginHistory(Mockito.any(LoginHistory.class));
	}
	
	@Test
	public void testUpdateAccount() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	UserStore userStore = new UserStore();
    	UserStore userStoreReturn = new UserStore();
    	UserStorePK userStorePK = new  UserStorePK();
    	
    	userStorePK.setUserId("testuser");
    	userStore.setPassword("123456");
    	userStore.setPrimaryKey(userStorePK);
    	
    	Mockito.when(this.userService.updateAccount(Mockito.any(UserStore.class))).thenReturn(userStore);
    	
    	requestBuilder = MockMvcRequestBuilders
				.put("/user/updateAccount")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(userStore))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		userStoreReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), UserStore.class);
		
		Assert.assertEquals(userStoreReturn.getPrimaryKey().getUserId(), userStore.getPrimaryKey().getUserId());
		Assert.assertEquals(userStoreReturn.getPassword(), userStore.getPassword());
		
		Mockito.verify(this.userService, Mockito.times(1)).updateAccount(Mockito.any(UserStore.class));
	}
	
	@Test
	public void testInsertNewUser() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	UserStore userStore = new UserStore();
    	UserStore userStoreReturn = new UserStore();
    	UserStorePK userStorePK = new  UserStorePK();
    	
    	userStorePK.setUserId("testuser");
    	userStore.setPassword("123456");
    	userStore.setPrimaryKey(userStorePK);
    	
    	Mockito.when(this.userService.insertNewAccount(Mockito.any(UserStore.class))).thenReturn(userStore);
    	Mockito.when(this.userService.checkUserExist(Mockito.any(String.class))).thenReturn(false);
    	
    	requestBuilder = MockMvcRequestBuilders
				.post("/user/insertNewAccount")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(userStore))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		userStoreReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), UserStore.class);
    	
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Assert.assertEquals(userStoreReturn.getPrimaryKey().getUserId(), userStore.getPrimaryKey().getUserId());
		Assert.assertEquals(userStoreReturn.getPassword(), userStore.getPassword());
		
		Mockito.verify(this.userService, Mockito.times(1)).insertNewAccount(Mockito.any(UserStore.class));
		Mockito.verify(this.userService, Mockito.times(1)).checkUserExist(Mockito.any(String.class));
	}
	
	@Test
	public void testUpdateUserInfo() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	User user = new User();
    	User userReturn = null;
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userService.updateUserInfo(Mockito.any(User.class))).thenReturn(user);
		
		requestBuilder = MockMvcRequestBuilders
				.put("/user/updateUserInfo")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		userReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), User.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(userReturn.getPrimaryKey().getUserId(), user.getPrimaryKey().getUserId());
		Assert.assertEquals(userReturn.getAddress1(), user.getAddress1());
		Assert.assertEquals(userReturn.getAddress2(), user.getAddress2());
		Assert.assertEquals(userReturn.getBirthday(), user.getBirthday());
		Assert.assertEquals(userReturn.getEmail(), user.getEmail());
		Assert.assertEquals(userReturn.getMemberRank(), user.getMemberRank());
		Assert.assertEquals(userReturn.getName(), user.getName());
		Assert.assertEquals(userReturn.getNickname(), user.getNickname());
		Assert.assertEquals(userReturn.getPhoneNumber(), user.getPhoneNumber());
		Assert.assertEquals(userReturn.getPostalCode(), user.getPostalCode());
		
		Mockito.verify(this.userService, Mockito.times(1)).updateUserInfo(Mockito.any(User.class));
	}
	
	@Test
	public void testInsertNewUserInfo() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	User user = new User();
    	User userReturn = null;
		UserPK userPK = new UserPK();
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userService.insertNewUserInfo(Mockito.any(User.class))).thenReturn(user);
		
		requestBuilder = MockMvcRequestBuilders
				.post("/user/insertNewUserInfo")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		userReturn = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), User.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.CREATED.value());
		Assert.assertEquals(userReturn.getPrimaryKey().getUserId(), user.getPrimaryKey().getUserId());
		Assert.assertEquals(userReturn.getAddress1(), user.getAddress1());
		Assert.assertEquals(userReturn.getAddress2(), user.getAddress2());
		Assert.assertEquals(userReturn.getBirthday(), user.getBirthday());
		Assert.assertEquals(userReturn.getEmail(), user.getEmail());
		Assert.assertEquals(userReturn.getMemberRank(), user.getMemberRank());
		Assert.assertEquals(userReturn.getName(), user.getName());
		Assert.assertEquals(userReturn.getNickname(), user.getNickname());
		Assert.assertEquals(userReturn.getPhoneNumber(), user.getPhoneNumber());
		Assert.assertEquals(userReturn.getPostalCode(), user.getPostalCode());
		
		Mockito.verify(this.userService, Mockito.times(1)).insertNewUserInfo(Mockito.any(User.class));
	}
	
	@Test
	public void testGetUserInfoByUserIdList() throws Exception {
		String userId = "testuser,testuser1";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	User user = new User();
    	User user2 = new User();
		UserPK userPK = new UserPK();
		UserPK userPK2 = new UserPK();
		List<User> listUsers = new ArrayList<User>();
		List<User> listUsersReturn = null;
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		userPK2.setUserId("testuser1");
		user2.setAddress1("都道府県、市町村、丁目番地１");
		user2.setAddress2("ビル、マンション１");
		user2.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user2.setEmail("testuser1@gmail.com");
		user2.setMemberRank("一般");
		user2.setName("Test Test");
		user2.setNickname("Test");
		user2.setPhoneNumber("11111-1111-1111");
		user2.setPostalCode("111-1111");
		user2.setPrimaryKey(userPK2);
		listUsers.add(user2);
		listUsers.add(user);
		
		Mockito.when(this.userService.getUserInfoByUserIdList(Mockito.any(String.class))).thenReturn(listUsers);
		
		requestBuilder = MockMvcRequestBuilders
				.get("/user/getUserInfoByUserIdList/" + userId)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		User[] arrUser = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), User[].class);
		listUsersReturn = new ArrayList<User>(Arrays.asList(arrUser));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listUsers.size(), listUsersReturn.size());
		for (int i = 0; i < listUsersReturn.size(); i++) {
			Assert.assertEquals(listUsers.get(i).getPrimaryKey().getUserId(), listUsersReturn.get(i).getPrimaryKey().getUserId());
			Assert.assertEquals(listUsers.get(i).getAddress1(), listUsersReturn.get(i).getAddress1());
			Assert.assertEquals(listUsers.get(i).getAddress2(), listUsersReturn.get(i).getAddress2());
			Assert.assertEquals(listUsers.get(i).getBirthday(), listUsersReturn.get(i).getBirthday());
			Assert.assertEquals(listUsers.get(i).getEmail(), listUsersReturn.get(i).getEmail());
			Assert.assertEquals(listUsers.get(i).getMemberRank(), listUsersReturn.get(i).getMemberRank());
			Assert.assertEquals(listUsers.get(i).getName(), listUsersReturn.get(i).getName());
			Assert.assertEquals(listUsers.get(i).getNickname(), listUsersReturn.get(i).getNickname());
			Assert.assertEquals(listUsers.get(i).getPhoneNumber(), listUsersReturn.get(i).getPhoneNumber());
			Assert.assertEquals(listUsers.get(i).getPostalCode(), listUsersReturn.get(i).getPostalCode());
		}
		
		Mockito.verify(this.userService, Mockito.times(1)).getUserInfoByUserIdList(Mockito.any(String.class));
	}
	
	@Test
	public void testVerifyToken() throws Exception {
		String token = "aaabbb201905";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
		User user = new User();
		UserPK userPK = new UserPK();
		List<UserResponse> listUserResponses = null;
		
		userPK.setUserId("testuser");
		user.setAddress1("都道府県、市町村、丁目番地");
		user.setAddress2("ビル、マンション");
		user.setBirthday(TestUtil.getDate(2019, 5, 13, 0, 0, 0));
		user.setEmail("testuser@gmail.com");
		user.setMemberRank("一般");
		user.setName("Test Test");
		user.setNickname("Test");
		user.setPhoneNumber("11111-1111-1111");
		user.setPostalCode("111-1111");
		user.setPrimaryKey(userPK);
		
		Mockito.when(this.userService.getUserInfo(Mockito.any(String.class))).thenReturn(user);
		Mockito.when(this.jwtService.validateTokenLogin(Mockito.any(String.class))).thenReturn(true);
		Mockito.when(this.jwtService.getUsernameFromToken(Mockito.any(String.class))).thenReturn(userPK.getUserId());
		
		requestBuilder = MockMvcRequestBuilders
				.post("/user/verifyToken")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(token))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		UserResponse[] arrUser = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), UserResponse[].class);
		listUserResponses = new ArrayList<UserResponse>(Arrays.asList(arrUser));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertTrue(listUserResponses!=null && listUserResponses.size() == 1);
		Assert.assertEquals(listUserResponses.get(0).fullName, user.getName());
		Assert.assertEquals(listUserResponses.get(0).permission, user.getMemberRank());
		Assert.assertEquals(listUserResponses.get(0).userId, user.getPrimaryKey().getUserId());
		
		Mockito.verify(this.userService, Mockito.times(1)).getUserInfo(Mockito.any(String.class));
		Mockito.verify(this.jwtService, Mockito.times(1)).validateTokenLogin(Mockito.any(String.class));
		Mockito.verify(this.jwtService, Mockito.times(1)).getUsernameFromToken(Mockito.any(String.class));
	}
	
	@Test
	public void testLogin() throws Exception {
		String token = "aaabbb201905";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	UserStore userStore = new UserStore();
    	UserStorePK userStorePK = new  UserStorePK();
    	List<UserStoreResponse> listUserStoreResponse = null;
    	
    	userStorePK.setUserId("testuser");
    	userStore.setPassword("123456");
    	userStore.setPrimaryKey(userStorePK);
    	
    	Mockito.when(this.userService.checkAccount(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
    	Mockito.when(this.jwtService.generateTokenLogin(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(token);
    	
    	requestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.accept(MediaType.APPLICATION_JSON).content(UserRestControllerAPITest.OM.writeValueAsString(userStore))
				.contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		UserStoreResponse[] arrUser = UserRestControllerAPITest.OM.readValue(response.getContentAsString(), UserStoreResponse[].class);
		listUserStoreResponse = new ArrayList<UserStoreResponse>(Arrays.asList(arrUser));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertTrue(listUserStoreResponse!=null && listUserStoreResponse.size() == 1);
		Assert.assertEquals(listUserStoreResponse.get(0).token, token);
		Assert.assertEquals(listUserStoreResponse.get(0).userId, userStore.getPrimaryKey().getUserId());
		Assert.assertEquals(listUserStoreResponse.get(0).password, userStore.getPassword());
	
		Mockito.verify(this.userService, Mockito.times(1)).checkAccount(Mockito.any(String.class), Mockito.any(String.class));
		Mockito.verify(this.jwtService, Mockito.times(1)).generateTokenLogin(Mockito.any(String.class), Mockito.any(String.class));
	}
}

class UserResponse{
	public String fullName;
	public String permission;
	public String userId;
}

class UserStoreResponse{
	public String token;
	public String userId;
	public String password;
}
