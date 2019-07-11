package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.PurchaseResults;

@Repository
public interface PurchaseResultsRepository extends JpaRepository<PurchaseResults, Integer> {
	@Modifying
	@Query(value = "INSERT INTO purchase_results (user_id, product_id, quantity, size, color, order_dt, order_status, payment_method, payment_no, shipping_address_no, delivery_plan_dt, delivery_completion_dt) "
		         + "VALUES (:userId, :productId, :quantity, :size, :color, NOW(), :orderStatus, :paymentMethod, :paymentNo, :shippingAddressNo, TIMESTAMPADD(DAY, 2, CURRENT_DATE), NULL)", nativeQuery = true)
	void insertPurchaseResult(@Param("userId") String userId, @Param("productId") String productId, 
	        @Param("quantity") Integer quantity, @Param("size") String size, @Param("color") String color, @Param("orderStatus") String orderStatus, @Param("paymentMethod") String paymentMethod,
	        @Param("paymentNo") int paymentNo, @Param("shippingAddressNo") int shippingAddressNo);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE purchase_results SET order_status=:orderStatus WHERE order_no=:orderNo", nativeQuery = true)
	void updatePurchaseResultStatus(@Param("orderNo") int orderNo, @Param("orderStatus") String orderStatus);
	
	@Query("SELECT p FROM PurchaseResults p WHERE user_id IN :userIdLst ORDER BY p.order_dt DESC")
	List<PurchaseResults> getPurchaseResultsInfoByUserIDList(@Param("userIdLst") List<String> userIdLst);
}
