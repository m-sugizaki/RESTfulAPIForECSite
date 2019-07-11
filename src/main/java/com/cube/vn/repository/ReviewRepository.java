package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.Review;
import com.cube.vn.dao.ReviewPK;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewPK> {
	@Query("SELECT r FROM Review r WHERE product_id=(:product_id) ORDER BY review_dt DESC")
	List<Review> getReviewOfProduct(@Param("product_id") String product_id);
	
	@Query("SELECT r FROM Review r WHERE product_id=(:product_id) ORDER BY review_dt DESC")
	List<Review> getReviewInfoByProductID(@Param("product_id") String product_id);
	
	@Query("SELECT MAX(r.primaryKey.reviewNo) FROM Review r WHERE product_id=(:productId)")
	Integer getMaxReviewNoOfProduct(@Param("productId") String productId);
	
	@Modifying
	@Query(value = "INSERT INTO review (product_id, review_no, user_id, evaluation, review_content, review_dt) "
		         + "VALUES (:productId, :reviewNo, :userId, :evaluation, :review_content, NOW())", nativeQuery = true)
	void insertNewReviewInfo(@Param("productId") String productId, @Param("reviewNo") int reviewNo, @Param("userId") String userId, 
	        @Param("evaluation") Integer evaluation, @Param("review_content") String review_content);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE review SET evaluation=:evaluation, review_content=:reviewContent, review_dt=NOW() WHERE product_id=:productId AND review_no=:reviewNo", nativeQuery = true)
	void updateReviewInfo(@Param("productId") String productId, @Param("reviewNo") int reviewNo, @Param("evaluation") int evaluation, @Param("reviewContent") String reviewContent);
}
