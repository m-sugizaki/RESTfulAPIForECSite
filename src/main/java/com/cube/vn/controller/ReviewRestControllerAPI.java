package com.cube.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;
import com.cube.vn.service.ReviewService;

@RestController
@RequestMapping("review")
public class ReviewRestControllerAPI {
    @Autowired
    ReviewService reviewService;
    
    @CrossOrigin
	@GetMapping(path = "getReviewOfProduct/{productId}")
    List<Review> getReviewOfProduct(@PathVariable String productId) { 
    	return this.reviewService.getReviewOfProduct(productId);
    }
    
	@CrossOrigin
	@GetMapping(path = "getLastReviewInfoByProductID/{productId}")
    Review getLastReviewInfoByProductID(@PathVariable String productId) {
    	return this.reviewService.getLastReviewInfoByProductID(productId);
    }
	
	@CrossOrigin
	@GetMapping(path = "getReviewInfoByKey/{productId}/{reviewNo}")
	Review getReviewInfoByKey(@PathVariable String productId, @PathVariable int reviewNo) {
        return this.reviewService.getReviewInfoByKey(productId, reviewNo);
    }
	
    @CrossOrigin
    @PutMapping(path = "updateReviewInfo")
    void updateReviewInfo(@RequestBody Review review) {
    	this.reviewService.updateReviewInfo(review);
    }
    
	@CrossOrigin
	@DeleteMapping(path = "deleteReview/{productId}/{reviewNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteReview(@PathVariable String productId, @PathVariable Integer reviewNo) {
		ReviewPK pk = new ReviewPK();
		pk.setProductId(productId);
		pk.setReviewNo(reviewNo);
    	this.reviewService.deleteReview(pk);
    }
	
	@CrossOrigin
	@PostMapping(path = "insertNewReviewInfo")
    @ResponseStatus(HttpStatus.CREATED)
	void insertNewReviewInfo(@RequestBody Review review) {
		Integer maxReviewNo = this.reviewService.getMaxReviewNoOfProduct(review.getPrimaryKey().getProductId());
		review.getPrimaryKey().setReviewNo(maxReviewNo + 1);
    	this.reviewService.insertNewReviewInfo(review);
    }
}
