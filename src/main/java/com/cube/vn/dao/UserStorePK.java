package com.cube.vn.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class UserStorePK  implements Serializable {
	@Column(name = "user_id", nullable = false, length = 10)
	private String user_id;
	
	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
}
