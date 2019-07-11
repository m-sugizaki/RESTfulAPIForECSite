package com.cube.vn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.cube.vn.dao.LoginHistory;
import com.cube.vn.dao.User;
import com.cube.vn.dao.UserStore;
import com.cube.vn.service.JwtService;
import com.cube.vn.service.UserService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("user")
public class UserRestControllerAPI{
    @Autowired
    UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    private String token = "";

    @CrossOrigin
	@PostMapping(path = "checkAccount")
    boolean checkAccount(@RequestBody UserStore userStore) {
        return this.userService.checkAccount(userStore.getPrimaryKey().getUserId(), userStore.getPassword());
    }
    
    @CrossOrigin
	@GetMapping(path = "{token}")
    User getUserInfo(@PathVariable String token) { 
    	String userId = "";
    	userId = jwtService.getUsernameFromToken(token);
    	return this.userService.getUserInfo(userId);    	
    }
    
    @CrossOrigin
   	@GetMapping(path = "getUserLastLoginHistory/{token}")
    LoginHistory getUserLastLoginHistory(@PathVariable String token) {
       String userId = "";
       String password = "";
       userId = jwtService.getUsernameFromToken(token);
       password = jwtService.getPasswordFromToken(token);
       if(this.userService.checkAccount(userId, password)) {
    	   return this.userService.getUserLastLoginHistory(userId);   
       }
       return null;
    }

    @CrossOrigin
	@GetMapping(path = "hasPaymentMethod/{userId}")
    boolean hasPaymentMethod(@PathVariable String userId) {
        return this.userService.hasPaymentMethod(userId);
    }
    
    @CrossOrigin
	@PostMapping(path = "insertLoginHistory")
    @ResponseStatus(HttpStatus.CREATED)
    LoginHistory insertLoginHistory(@RequestBody LoginHistory loginHistory) {
    	return this.userService.insertLoginHistory(loginHistory);
    }
    
    @CrossOrigin
    @PutMapping(path = "updateAccount")
    UserStore updateAccount(@RequestBody UserStore userStore) {
    	return this.userService.updateAccount(userStore);
    }
    
	@CrossOrigin
	@PostMapping(path = "insertNewAccount")
    @ResponseStatus(HttpStatus.CREATED)
	UserStore insertNewUser(@RequestBody UserStore userStore) {
		if (this.userService.checkUserExist(userStore.getPrimaryKey().getUserId())) {
			return null;
		}
    	return this.userService.insertNewAccount(userStore);
    }
	
	@CrossOrigin
    @PutMapping(path = "updateUserInfo")
	User updateUserInfo(@RequestBody User user) {
    	return this.userService.updateUserInfo(user);
    }
    
	@CrossOrigin
	@PostMapping(path = "insertNewUserInfo")
    @ResponseStatus(HttpStatus.CREATED)
	User insertNewUserInfo(@RequestBody User user) {
    	return this.userService.insertNewUserInfo(user);
    }
	
	@CrossOrigin
	@PostMapping(path = "login")
	public ResponseEntity<Object> login(HttpServletRequest request, @RequestBody UserStore userStore) {
	    HttpStatus httpStatus = null;
	    List<JSONObject> entities = new ArrayList<JSONObject>();
    	JSONObject entity = new JSONObject();
	    try {
	        if (this.userService.checkAccount(userStore.getPrimaryKey().getUserId(), userStore.getPassword())) {	    	
	            token = this.jwtService.generateTokenLogin(userStore.getPrimaryKey().getUserId(), userStore.getPassword());
		        entity.put("token", token);
		        entity.put("userId", userStore.getPrimaryKey().getUserId());
		        entity.put("password", userStore.getPassword());
		        entities.add(entity);
		        httpStatus = HttpStatus.OK;
	      } else {
	    	  	token = "Wrong userId and password";
	    	  	httpStatus = HttpStatus.BAD_REQUEST;
	      }
	    } catch (Exception ex) {
	    	token = "Server Error";
	    	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new ResponseEntity<Object>(entities, httpStatus);	    
	}
	
	@CrossOrigin
   	@PostMapping(path = "verifyToken")
	public ResponseEntity<Object> verifyToken(@RequestBody String token) {
		HttpStatus httpStatus = null;
		List<JSONObject> entities = new ArrayList<JSONObject>();
    	JSONObject entity = new JSONObject();
    	try {
	        if (this.jwtService.validateTokenLogin(token)) {
	        	String userId = "";
	            userId = jwtService.getUsernameFromToken(token);
	            User user = this.userService.getUserInfo(userId);
	        	entity.put("permission", user.getMemberRank());
	        	entity.put("userId", user.getPrimaryKey().getUserId());
	        	entity.put("fullName", user.getName());
	        	entities.add(entity);
	        	httpStatus = HttpStatus.OK;
	        }
	        else {
	        	token = "Wrong token";
	    	  	httpStatus = HttpStatus.BAD_REQUEST;
	        }
    	} catch (Exception ex) {
    		token = "Server Error";
	    	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
		return new ResponseEntity<Object>(entities, httpStatus);
	}
	
	@CrossOrigin
	@GetMapping(path = "getUserInfoByUserIdList/{userIdLst}")
    List<User> getUserInfoByUserIdList(@PathVariable String userIdLst) {
    	return this.userService.getUserInfoByUserIdList(userIdLst);
    }
}