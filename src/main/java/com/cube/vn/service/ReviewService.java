package com.cube.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;
import com.cube.vn.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	
	@CrossOrigin
	public List<Review> getReviewOfProduct(String productId) {
		return this.reviewRepository.getReviewOfProduct(productId);
	}
	
	@CrossOrigin
	public Review getLastReviewInfoByProductID(String productId) {
		List<Review> listReviews = this.reviewRepository.getReviewInfoByProductID(productId);
		if (listReviews != null && listReviews.size() > 0) {
			return listReviews.get(0);
		}
		return null;
	}
	
	@CrossOrigin
	public Review getReviewInfoByKey(String productId, int reviewNo) {
		ReviewPK pk = new ReviewPK();
		pk.setProductId(productId);
		pk.setReviewNo(reviewNo);
		Optional<Review> review = null;
		review =  this.reviewRepository.findById(pk);
		if (review != null && review.isPresent() ) {
			return review.get();
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	public Integer getMaxReviewNoOfProduct(String productId) {
		Integer re = 0;
		re = this.reviewRepository.getMaxReviewNoOfProduct(productId);
		if (re == null) {
			re = 0;
		}
		return re;
	}

	@CrossOrigin
	public void updateReviewInfo(Review review) {
		this.reviewRepository.updateReviewInfo(review.getPrimaryKey().getProductId(), review.getPrimaryKey().getReviewNo(),
				review.getEvaluation(), review.getReviewContent());
	}
	
	@CrossOrigin
	public void deleteReview(ReviewPK pk) {
		this.reviewRepository.deleteById(pk);
	}
	
	@CrossOrigin
	public void insertNewReviewInfo(Review review) {
		this.reviewRepository.insertNewReviewInfo(review.getPrimaryKey().getProductId(), review.getPrimaryKey().getReviewNo(), 
				review.getUserId(), review.getEvaluation(), review.getReviewContent());
	}
}
