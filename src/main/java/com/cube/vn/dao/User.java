package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	private UserPK userPK;
	
	@Column(name = "birthday", nullable = false)
	private Date birthday;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "nickname", nullable = false, length = 50)
	private String nickname;
	
	@Column(name = "postal_code", nullable = false, length = 10)
	private String postal_code;
	
	@Column(name = "address1", nullable = false, length = 100)
	private String address1;
	
	@Column(name = "address2", length = 100)
	private String address2;
	
	@Column(name = "phone_number", nullable = false, length = 15)
	private String phone_number;
	
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "member_rank", nullable = false, length = 5)
	private String member_rank;
	
	@EmbeddedId
    public UserPK getPrimaryKey() {
        return this.userPK;
    }
 
    public void setPrimaryKey(UserPK pk) {
    	this.userPK = pk;
    }

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPostalCode() {
		return this.postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhoneNumber() {
		return this.phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberRank() {
		return this.member_rank;
	}

	public void setMemberRank(String member_rank) {
		this.member_rank = member_rank;
	}
}
