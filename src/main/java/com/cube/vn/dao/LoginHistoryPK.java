package com.cube.vn.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class LoginHistoryPK implements Serializable {
	@Column(name = "user_id", nullable = false, length = 10)
	private String userId;

	@Column(name = "login_dt", nullable = false)
	private Date loginDt;
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String user_id) {
		this.userId = user_id;
	}

	public Date getLoginDt() {
		return this.loginDt;
	}

	public void setLoginDt(Date login_dt) {
		this.loginDt = login_dt;
	}
}
