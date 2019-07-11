package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.ShippingAddress;
import com.cube.vn.dao.ShippingAddressPK;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, ShippingAddressPK> {
	@Query("SELECT MAX(sa.primaryKey.shippingAddressNo) FROM ShippingAddress sa WHERE user_id=(:userId)")
	Integer getMaxShippingAddressNoOfUser(@Param("userId") String userId);
	
	@Query("SELECT sa FROM ShippingAddress sa WHERE user_id=(:userId)")
	List<ShippingAddress> findByUserId(@Param("userId") String userId);
}
