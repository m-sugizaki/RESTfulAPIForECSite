package com.cube.vn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
	private ReviewPK reviewPK;
	
	@Column(name = "user_id", nullable = false, length = 10)
	private String user_id;
	
	@Column(name = "evaluation", nullable = false)
	private int evaluation;
	
	@Column(name = "review_content", nullable = false, length = 100)
	private String review_content;
	
	@Column(name = "review_dt", nullable = false)
	private Date review_dt;
	
	public String getUserId() {
		return this.user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	@EmbeddedId
    public ReviewPK getPrimaryKey() {
        return this.reviewPK;
    }
 
    public void setPrimaryKey(ReviewPK pk) {
    	this.reviewPK = pk;
    }

	public int getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public String getReviewContent() {
		return this.review_content;
	}

	public void setReviewContent(String review_content) {
		this.review_content = review_content;
	}

	public Date getReviewDt() {
		return this.review_dt;
	}

	public void setReviewDt(Date review_dt) {
		this.review_dt = review_dt;
	}
}
