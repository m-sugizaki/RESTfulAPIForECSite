package com.cube.vn.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_store")
public class UserStore {
	private UserStorePK userStorePK;

	@Column(name = "password", nullable = false, length = 50)
	private String password;
	
	@EmbeddedId
    public UserStorePK getPrimaryKey() {
        return this.userStorePK;
    }
 
    public void setPrimaryKey(UserStorePK pk) {
    	this.userStorePK = pk;
    }

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
