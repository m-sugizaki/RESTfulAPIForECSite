package com.cube.vn.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "login_history")
public class LoginHistory {
	private LoginHistoryPK loginHistoryPK;
	
	public LoginHistory() {
    }
 
    @EmbeddedId
    public LoginHistoryPK getPrimaryKey() {
        return this.loginHistoryPK;
    }
 
    public void setPrimaryKey(LoginHistoryPK pk) {
    	this.loginHistoryPK = pk;
    }
}
