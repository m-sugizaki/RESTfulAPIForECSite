package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.ProductCart;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {
	@Modifying
	@Query(value = "INSERT INTO product_cart (user_id, product_id, quantity, size, color, cart_regist_dt) VALUES (:userId, :productId, :quantity, :size, :color, NOW())", nativeQuery = true)
	void insertNewProductCart(@Param("userId") String userId, @Param("productId") String productId, 
	        @Param("quantity") Integer quantity, @Param("size") String size, @Param("color") String color);
	
	@Query("SELECT pc FROM ProductCart pc WHERE user_id=(:userId) ORDER BY pc.cart_regist_dt DESC")
	List<ProductCart> getProductCartInfoByUserID(@Param("userId") String userId);
}
