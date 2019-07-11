package com.cube.vn.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtServiceTest {

	@Autowired
	JwtService jwtService;
	
	@Test
	public void testGenerateTokenLogin_Success() throws JOSEException {
		String usernameTest = "usertest";
		String passwordTest = "123456";
		String tokenTest = null;	
		JWSSigner signer = new MACSigner(JwtService.SECRET_KEY.getBytes());
	    JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
	    builder.claim(JwtService.USERNAME, usernameTest);
	    builder.claim(JwtService.PASSWORD, passwordTest);
	    builder.expirationTime(new Date(System.currentTimeMillis() + JwtService.EXPIRE_TIME));
	    JWTClaimsSet claimsSet = builder.build();
	    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
	    signedJWT.sign(signer);
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		
		Assert.assertNotNull(tokenTest);
		Assert.assertEquals(signedJWT.serialize(),tokenTest);
	}

	@Test
	public void testGetUsernameFromToken_Success() {
		String usernameTest = "usertest";
		String passwordTest = "123456";
		String usernameExpected = "usertest";
		String usernameActual = "";
		String tokenTest = null;
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		usernameActual = this.jwtService.getUsernameFromToken(tokenTest);
		
		Assert.assertEquals(usernameExpected, usernameActual);
	}
	
	@Test
	public void testGetPasswordFromToken_Success() {
		String usernameTest = "usertest";
		String passwordTest = "123456";
		String passwordExpected = "123456";
		String passwordActual = "";
		String tokenTest = null;
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		passwordActual = this.jwtService.getPasswordFromToken(tokenTest);
		
		Assert.assertEquals(passwordExpected, passwordActual);
	}

	@Test
	public void testValidateTokenLogin_Success() {
		String usernameTest = "usertestt";
		String passwordTest = "1234567";
		String tokenTest = null;
		Boolean isValid = false;
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		isValid = this.jwtService.validateTokenLogin(tokenTest);
		
		Assert.assertTrue(isValid);
	}
	
	@Test
	public void testValidateTokenLogin_Fail() {
		String tokenTest = null;
		String tokenTrimLengthIsEmpty = "   ";
		Boolean isValid = false;
		Boolean isValid2 = false;
		
		isValid = this.jwtService.validateTokenLogin(tokenTest);
		isValid2 = this.jwtService.validateTokenLogin(tokenTrimLengthIsEmpty);
		
		Assert.assertFalse(isValid);
		Assert.assertFalse(isValid2);
	}

	@Test
	public void testGetClaimsFromToken() {
		String usernameTest = "usertestt";
		String passwordTest = "1234567";
		String tokenTest = null;
		JWTClaimsSet claims = null;
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		claims = this.jwtService.getClaimsFromToken(tokenTest);

		Assert.assertTrue(claims != null );
		Assert.assertEquals(claims.getClaim("username"), usernameTest);
		Assert.assertEquals(claims.getClaim("password"), passwordTest);
	}

	@Test
	public void testGenerateExpirationDate() {
		Date expireDate;
		expireDate = this.jwtService.generateExpirationDate();
		
		Assert.assertTrue(expireDate != null);
		Assert.assertEquals(expireDate, new Date(System.currentTimeMillis() + JwtService.EXPIRE_TIME));
	}
	
	@Test
	public void testGetExpirationDateFromToken() {
		Date expireDateFromToken;
		String usernameTest = "usertestt";
		String passwordTest = "1234567";
		String tokenTest = null;
		Date expireDate;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		expireDate = this.jwtService.generateExpirationDate();
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		expireDateFromToken = this.jwtService.getExpirationDateFromToken(tokenTest);

		Assert.assertTrue(expireDateFromToken != null);
		Assert.assertEquals(dateformat.format(expireDate), dateformat.format(expireDateFromToken));
	}

	@Test
	public void testGenerateShareSecret() {
		byte[] secretKey;
		byte[] skey = new byte[32];
		
		skey = JwtService.SECRET_KEY.getBytes();
		secretKey = this.jwtService.generateShareSecret();

		Assert.assertTrue(secretKey != null && secretKey.length != 0);
		Assert.assertEquals(secretKey.toString().contains("[B@"), skey.toString().contains("[B@"));
	}

	@Test
	public void testIsTokenExpired() {
		Boolean isExpired = true;
		String usernameTest = "usertestt";
		String passwordTest = "1234567";
		String tokenTest = null;
		
		tokenTest = this.jwtService.generateTokenLogin(usernameTest, passwordTest);
		isExpired = this.jwtService.isTokenExpired(tokenTest);
		
		Assert.assertEquals(isExpired, this.jwtService.getExpirationDateFromToken(tokenTest).before(new Date()));
	}
}
