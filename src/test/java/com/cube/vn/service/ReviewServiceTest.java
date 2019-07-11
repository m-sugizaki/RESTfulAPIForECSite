package com.cube.vn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;
import com.cube.vn.repository.ReviewRepository;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceTest {

	@MockBean
	ReviewRepository reviewRepository;

	@Autowired
	ReviewService reviewService;

	@Test
	public void testGetReviewOfProduct() {
		Review review = new Review();
		Review review2 = new Review();
		ReviewPK reviewPK = new ReviewPK();
		ReviewPK reviewPK2 = new ReviewPK();
		List<Review> reviews = new ArrayList<Review>();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review.setUserId("testuser");
		
		reviewPK2.setProductId("TEST100001");
		reviewPK2.setReviewNo(2);
		review2.setPrimaryKey(reviewPK2);
		review2.setEvaluation(4);
		review2.setReviewContent("値段がやすい");
		review2.setReviewDt(TestUtil.getDate(2019, 5, 15, 14, 23, 0));
		review2.setUserId("testuser2");

		reviews.add(review);
		reviews.add(review2);

		Mockito.when(this.reviewRepository.getReviewOfProduct("TEST100001")).thenReturn(reviews);

		Assert.assertEquals(this.reviewService.getReviewOfProduct("TEST100001"), reviews);
		Mockito.verify(this.reviewRepository, Mockito.times(1)).getReviewOfProduct("TEST100001");
	}

	@Test
	public void testgetLastReviewInfoByProductID() {
		Review review = new Review();
		Review review2 = new Review();
		ReviewPK reviewPK = new ReviewPK();
		ReviewPK reviewPK2 = new ReviewPK();
		List<Review> reviews = new ArrayList<Review>();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review.setUserId("testuser");
		
		reviewPK2.setProductId("TEST100001");
		reviewPK2.setReviewNo(2);
		review2.setPrimaryKey(reviewPK2);
		review2.setEvaluation(4);
		review2.setReviewContent("値段がやすい");
		review2.setReviewDt(TestUtil.getDate(2019, 5, 15, 14, 23, 0));
		review2.setUserId("testuser2");

		reviews.add(review2);

		Mockito.when(this.reviewRepository.getReviewInfoByProductID(reviewPK.getProductId())).thenReturn(reviews);
		
		Assert.assertEquals(this.reviewService.getLastReviewInfoByProductID(reviewPK.getProductId()), reviews.get(0));
		Mockito.verify(this.reviewRepository, Mockito.times(1)).getReviewInfoByProductID(reviewPK.getProductId());
	}

	@Test
	public void testGetReviewInfoByKey() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review.setUserId("testuser");

		Mockito.when(this.reviewRepository.findById(Mockito.any(ReviewPK.class))).thenReturn(Optional.of(review));

    	Assert.assertEquals(this.reviewService.getReviewInfoByKey(reviewPK.getProductId(), reviewPK.getReviewNo()), review);
    	Mockito.verify(this.reviewRepository, Mockito.times(1)).findById(Mockito.any(ReviewPK.class));
	}

	@Test
	public void testDeleteReview_ShouldDeleteEntry() {
		ReviewPK reviewPK = new ReviewPK();
		
		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		
		Mockito.doNothing().when(this.reviewRepository).deleteById(reviewPK);
		this.reviewService.deleteReview(reviewPK);
		
		Mockito.verify(this.reviewRepository, Mockito.times(1)).deleteById(reviewPK);
	}

	@Test
	public void testUpdateReviewInfo_ShouldUpdateEntry() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 15, 0, 0, 0));
		review.setUserId("testuser");
		
		Mockito.doNothing().when(this.reviewRepository).updateReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(),
				review.getEvaluation(), review.getReviewContent());
		
		this.reviewService.updateReviewInfo(review);
		Mockito.verify(this.reviewRepository, Mockito.times(1)).updateReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(),
				review.getEvaluation(), review.getReviewContent());
	}

	@Test
	public void testInsertNewReviewInfo_ShouldInsertEntry() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 15, 0, 0, 0));
		review.setUserId("testuser");
		
		Mockito.doNothing().when(this.reviewRepository).insertNewReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), 
				review.getUserId(), review.getEvaluation(), review.getReviewContent());
		
		this.reviewService.insertNewReviewInfo(review);
		
		Mockito.verify(this.reviewRepository, Mockito.times(1)).insertNewReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), 
				review.getUserId(), review.getEvaluation(), review.getReviewContent());
	}

	@Test
	public void testGetMaxReviewNoOfProduct() {
		int maxReview = 20;
		String productId = "TEST100001";
		
		Mockito.when(this.reviewRepository.getMaxReviewNoOfProduct(productId)).thenReturn(maxReview);
		
		Assert.assertTrue(this.reviewService.getMaxReviewNoOfProduct(productId) == maxReview);
		Mockito.verify(this.reviewRepository,Mockito.times(1)).getMaxReviewNoOfProduct(productId);
	}
}
