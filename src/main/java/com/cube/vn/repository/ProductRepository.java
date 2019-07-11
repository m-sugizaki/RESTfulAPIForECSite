package com.cube.vn.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductPK;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductPK> {
	List<Product> findAll(Specification<Product> specification);
	
	@Query("SELECT p FROM Product p WHERE product_id IN :productIdLst")
	List<Product> getProductInfoByProductIDList(@Param("productIdLst") List<String> productIdLst);
}
