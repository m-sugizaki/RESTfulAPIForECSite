package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.PaymentMethod;
import com.cube.vn.dao.PaymentMethodPK;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, PaymentMethodPK> {
	@Query("SELECT pm FROM PaymentMethod pm WHERE user_id=(:userId)")
	List<PaymentMethod> findByUserId(@Param("userId") String userId);
	
	@Query("SELECT MAX(pm.primaryKey.paymentNo) FROM PaymentMethod pm WHERE user_id=(:userId)")
	Integer getMaxPaymentMethodNoOfUser(@Param("userId") String userId);
}
