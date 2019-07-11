package com.cube.vn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;
import com.cube.vn.service.ReviewService;
import com.cube.vn.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewRestControllerAPI.class)
public class ReviewRestControllerAPITest {
	
	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewService;
	
	@Test
	public void testInsertNewReview() throws Exception {
		ReviewPK pk = new ReviewPK();
		Review review = new Review();
		RequestBuilder requestBuilder = null;
		MvcResult result = null;
		MockHttpServletResponse response = null;
		
		pk.setReviewNo(1);
		pk.setProductId("test1001");
		review.setPrimaryKey(pk);
		review.setEvaluation(5);
		review.setReviewContent("値段がやすい！！！");
		review.setUserId("testuser");
		
		Mockito.doNothing().when(this.reviewService)
				.insertNewReviewInfo(Mockito.any(Review.class));
		requestBuilder = MockMvcRequestBuilders
				.post("/review/insertNewReviewInfo")
				.accept(MediaType.APPLICATION_JSON).content(ReviewRestControllerAPITest.OM.writeValueAsString(review))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		Mockito.verify(this.reviewService, Mockito.times(1)).insertNewReviewInfo(Mockito.any(Review.class));
	}
	
	@Test
	public void testDeleteReview() throws Exception {
		ReviewPK pk = new ReviewPK();
		
		pk.setReviewNo(1);
		pk.setProductId("test1001");
		
		Mockito.doNothing().when(this.reviewService).deleteReview(Mockito.any(ReviewPK.class));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/review/deleteReview/test1001/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		Mockito.verify(this.reviewService, Mockito.times(1)).deleteReview(Mockito.any(ReviewPK.class));
	}

	@Test
	public void testUpdateReviewInfo() throws Exception {
		ReviewPK pk = new ReviewPK();
		Review review = new Review();
		RequestBuilder requestBuilder = null;
		
		pk.setReviewNo(1);
		pk.setProductId("test1001");
		review.setPrimaryKey(pk);
		review.setEvaluation(5);
		review.setReviewContent("値段がやすい！！！");
		review.setReviewDt(TestUtil.getDate(2019, 5, 1, 12, 20, 1));
		review.setUserId("testuser");
		
		Mockito.doNothing().when(this.reviewService).updateReviewInfo(Mockito.any(Review.class));
		requestBuilder = MockMvcRequestBuilders
				.put("/review/updateReviewInfo")
				.accept(MediaType.APPLICATION_JSON).content(ReviewRestControllerAPITest.OM.writeValueAsString(review))
				.contentType(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(this.reviewService, Mockito.times(1)).updateReviewInfo(Mockito.any(Review.class));
	}
	
	@Test
	public void testGetLastReviewInfoByProductID() throws Exception {
		String productId = "test1002";
		Review review = new Review();
		Review reviewReturn = new Review();
		ReviewPK pk = new ReviewPK();
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	
    	pk.setReviewNo(1);
    	pk.setProductId(productId);
    	review.setPrimaryKey(pk);
		review.setEvaluation(5);
		review.setReviewContent("値段がやすい！！！");
		review.setReviewDt(TestUtil.getDate(2019, 5, 1, 12, 40, 2));
		review.setUserId("testuser");
    	
    	Mockito.when(this.reviewService.getLastReviewInfoByProductID(
    			Mockito.any(String.class))).thenReturn(review);
    	requestBuilder = MockMvcRequestBuilders
    			.get("/review/getLastReviewInfoByProductID/" + productId)
    			.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
    	result = mockMvc.perform(requestBuilder).andReturn();
    	response = result.getResponse();
    	
    	reviewReturn = ReviewRestControllerAPITest.OM.readValue(response.getContentAsString(), Review.class);

    	Assert.assertEquals(reviewReturn.getReviewContent(), review.getReviewContent());
    	Assert.assertEquals(reviewReturn.getUserId(), review.getUserId());
    	Assert.assertEquals(reviewReturn.getEvaluation(), review.getEvaluation());
    	Assert.assertEquals(reviewReturn.getReviewDt(), review.getReviewDt());
    	Assert.assertEquals(reviewReturn.getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
    	Assert.assertEquals(reviewReturn.getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
    	Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
    	Mockito.verify(this.reviewService, Mockito.times(1)).getLastReviewInfoByProductID(Mockito.any(String.class));
	}
	
	@Test
	public void testGetReviewOfProduct() throws Exception {
		String productId = "test1002";
		Review review = new Review();
		Review review2 = new Review();
		ReviewPK pk = new ReviewPK();
		ReviewPK pk2 = new ReviewPK();
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	List<Review> listReview = new ArrayList<Review>();
    	List<Review> listReviewReturn = null;
    	
    	pk.setReviewNo(1);
    	pk.setProductId(productId);
    	review.setPrimaryKey(pk);
		review.setEvaluation(5);
		review.setReviewContent("値段がやすい！！！");
		review.setReviewDt(TestUtil.getDate(2019, 5, 1, 12, 40, 2));
		review.setUserId("testuser");
		
		pk2.setReviewNo(2);
    	pk2.setProductId(productId);
    	review2.setPrimaryKey(pk);
		review2.setEvaluation(5);
		review2.setReviewContent("よいよい！！！早く買って！！");
		review2.setReviewDt(TestUtil.getDate(2019, 5, 1, 14, 2, 40));
		review2.setUserId("testuser2");
		
		listReview.add(review);
		listReview.add(review2);

		Mockito.when(this.reviewService.getReviewOfProduct(
				Mockito.any(String.class))).thenReturn(listReview);
		requestBuilder = MockMvcRequestBuilders
				.get("/review/getReviewOfProduct/" + productId)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();

		Review[] arrReview = (ReviewRestControllerAPITest.OM.readValue(response.getContentAsString(), Review[].class));
		listReviewReturn = new ArrayList<Review>(Arrays.asList(arrReview));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listReview.size(), listReviewReturn.size());
		for (int i = 0; i < listReview.size(); i++) {
			Assert.assertEquals(listReviewReturn.get(i).getReviewContent(), listReview.get(i).getReviewContent());
	    	Assert.assertEquals(listReviewReturn.get(i).getUserId(), listReview.get(i).getUserId());
	    	Assert.assertEquals(listReviewReturn.get(i).getEvaluation(), listReview.get(i).getEvaluation());
	    	Assert.assertEquals(listReviewReturn.get(i).getReviewDt(), listReview.get(i).getReviewDt());
	    	Assert.assertEquals(listReviewReturn.get(i).getPrimaryKey().getProductId(), listReview.get(i).getPrimaryKey().getProductId());
	    	Assert.assertEquals(listReviewReturn.get(i).getPrimaryKey().getReviewNo(), listReview.get(i).getPrimaryKey().getReviewNo());
		}
		
		Mockito.verify(this.reviewService, Mockito.times(1)).getReviewOfProduct(Mockito.any(String.class));
	}
	
	@Test
	public void testGetReviewInfoByKey() throws Exception {
		String productId = "test1002";
		Review review = new Review();
		Review reviewReturn = new Review();
		ReviewPK pk = new ReviewPK();
    	RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	
    	pk.setReviewNo(1);
    	pk.setProductId(productId);
    	review.setPrimaryKey(pk);
		review.setEvaluation(5);
		review.setReviewContent("よいよい！！！早く買って！！");
		review.setReviewDt(TestUtil.getDate(2019, 5, 1, 12, 3, 20));
		review.setUserId("testuser");

		Mockito.when(this.reviewService.getReviewInfoByKey(
				Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(review);
		requestBuilder = MockMvcRequestBuilders
				.get("/review/getReviewInfoByKey/" + productId + "/1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		reviewReturn = ReviewRestControllerAPITest.OM.readValue(response.getContentAsString(), Review.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(reviewReturn.getReviewContent(), review.getReviewContent());
    	Assert.assertEquals(reviewReturn.getUserId(), review.getUserId());
    	Assert.assertEquals(reviewReturn.getEvaluation(), review.getEvaluation());
    	Assert.assertEquals(reviewReturn.getReviewDt(), review.getReviewDt());
    	Assert.assertEquals(reviewReturn.getPrimaryKey().getProductId(), review.getPrimaryKey().getProductId());
    	Assert.assertEquals(reviewReturn.getPrimaryKey().getReviewNo(), review.getPrimaryKey().getReviewNo());
		Mockito.verify(this.reviewService, Mockito.times(1)).getReviewInfoByKey(Mockito.any(String.class), Mockito.any(Integer.class));
	}
}
