package com.cube.vn.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;
import com.cube.vn.util.TestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private ReviewRepository reviewRepository;

	@Test
	public void testGetReviewOfProduct_HasData() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();
		List<Review> reviews = null;

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review.setUserId("testuser");
		this.testEntityManager.persist(review);
		this.testEntityManager.flush();
		reviews = this.reviewRepository.getReviewInfoByProductID(reviewPK.getProductId());

		Assert.assertTrue(reviews != null && reviews.size() == 1);
		Assert.assertEquals(reviews.get(0).getUserId(), review.getUserId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
		Assert.assertEquals(reviews.get(0).getReviewContent(), review.getReviewContent());
		Assert.assertEquals(reviews.get(0).getEvaluation(), review.getEvaluation());
		Assert.assertEquals(reviews.get(0).getReviewDt(), review.getReviewDt());
	}

	@Test
	public void testGetReviewInfoByProductID_HasData() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();
		List<Review> reviews = null;

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review.setUserId("testuser");
		this.testEntityManager.persist(review);
		this.testEntityManager.flush();
		reviews = this.reviewRepository.getReviewInfoByProductID(reviewPK.getProductId());

		Assert.assertTrue(reviews != null && reviews.size() == 1);
		Assert.assertEquals(reviews.get(0).getUserId(), review.getUserId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
		Assert.assertEquals(reviews.get(0).getReviewContent(), review.getReviewContent());
		Assert.assertEquals(reviews.get(0).getEvaluation(), review.getEvaluation());
		Assert.assertEquals(reviews.get(0).getReviewDt(), review.getReviewDt());
	}

	@Test
	public void testGetMaxReviewNoOfProduct_HasData() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();
		Review review2 = new Review();
		ReviewPK reviewPK2 = new ReviewPK();
		
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
		review2.setReviewContent("値段がやすい！！！！");
		review2.setReviewDt(TestUtil.getDate(2019, 5, 13, 14, 23, 0));
		review2.setUserId("testuser2");
		
		this.testEntityManager.persist(review);
		this.testEntityManager.persist(review2);
		this.testEntityManager.flush();
		
		Assert.assertTrue(this.reviewRepository.getMaxReviewNoOfProduct(reviewPK.getProductId()) == 2);
	}
	
	@Test
	public void testInsertNewReviewInfo() throws ParseException {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();
		List<Review> reviews = null;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		Date curDate = dateformat.parse(dateformat.format(cal.getTime()));

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setUserId("testuser");
		this.reviewRepository.insertNewReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), review.getUserId(), review.getEvaluation(), review.getReviewContent());
		reviews = this.reviewRepository.getReviewInfoByProductID(reviewPK.getProductId());

		Assert.assertTrue(reviews != null && reviews.size() == 1);
		Assert.assertEquals(reviews.get(0).getUserId(), review.getUserId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
		Assert.assertEquals(reviews.get(0).getReviewContent(), review.getReviewContent());
		Assert.assertEquals(reviews.get(0).getEvaluation(), review.getEvaluation());
		Assert.assertEquals(dateformat.format(reviews.get(0).getReviewDt()), dateformat.format(curDate));
	}
	
	@Test
	public void testUpdateReviewInfo() {
		Review review = new Review();
		ReviewPK reviewPK = new ReviewPK();
		List<Review> reviews = null;

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setUserId("testuser");
		
		this.reviewRepository.insertNewReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), review.getUserId(), review.getEvaluation(), review.getReviewContent());
		
		review.setEvaluation(4);
		review.setReviewContent("値段がやすい");
		
		this.reviewRepository.updateReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), review.getEvaluation(), review.getReviewContent());
		this.testEntityManager.flush();
		
		reviews = this.reviewRepository.getReviewInfoByProductID(reviewPK.getProductId());

		Assert.assertTrue(reviews != null && reviews.size() == 1);
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
		Assert.assertEquals(reviews.get(0).getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
		Assert.assertEquals(reviews.get(0).getReviewContent(), review.getReviewContent());
		Assert.assertEquals(reviews.get(0).getEvaluation(), review.getEvaluation());
	}
	
	@Test
	public void testGetReviewOfProduct_NoData() {
		List<Review> reviews = null;
		
		reviews = this.reviewRepository.getReviewInfoByProductID("TEST100001");

		Assert.assertTrue(reviews.size()==0 && reviews !=null);
	}
	
	@Test
	public void testGetReviewInfoByProductID_NoData() {
		List<Review> reviews = null;
		
		reviews = this.reviewRepository.getReviewInfoByProductID("TEST100001");

		Assert.assertTrue(reviews != null && reviews.size() == 0);
	}
	
	@Test
	public void testGetMaxReviewNoOfProduct_NoData() {
		Assert.assertTrue(this.reviewRepository.getMaxReviewNoOfProduct("TEST100001") == null);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testInsertNewReviewInfo_NoData()
	{
		Review review = new Review();
		Review review2 = new Review();
		ReviewPK reviewPK = new ReviewPK();
		ReviewPK reviewPK2 = new ReviewPK();

		reviewPK.setProductId("TEST100001");
		reviewPK.setReviewNo(1);
		review.setPrimaryKey(reviewPK);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早めに買いて");
		review.setUserId("testuser");
		
		this.reviewRepository.insertNewReviewInfo(reviewPK.getProductId(), reviewPK.getReviewNo(), review.getUserId(), 
				review.getEvaluation(), review.getReviewContent());
		
		reviewPK2.setProductId("TEST100001");
		reviewPK2.setReviewNo(1);
		review2.setPrimaryKey(reviewPK2);
		review2.setEvaluation(5);
		review2.setReviewContent("よいよい！！！早めに買いて");
		review2.setUserId("testuser");
		
		this.reviewRepository.insertNewReviewInfo(reviewPK2.getProductId(), reviewPK2.getReviewNo(), review2.getUserId(), 
				review2.getEvaluation(), review2.getReviewContent());
	}
}
