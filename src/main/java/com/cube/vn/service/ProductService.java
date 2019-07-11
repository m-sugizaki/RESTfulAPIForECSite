package com.cube.vn.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductDynamicSearch;
import com.cube.vn.dao.ProductPK;
import com.cube.vn.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@SuppressWarnings("serial")
	@CrossOrigin
	public List<Product> searchProductData(ProductDynamicSearch productSearch) {
		return this.productRepository.findAll(new Specification<Product>() {
			@Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
                if(productSearch.getProductName() != null && productSearch.getProductName().trim().length() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("productName"), "%" + productSearch.getProductName().trim() + "%")));
                }
                if(productSearch.getMaker() != null && productSearch.getMaker().trim().length() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("maker"), "%" + productSearch.getMaker().trim() + "%")));
                }
                if(productSearch.getFromPrice() != null && productSearch.getFromPrice().doubleValue() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), productSearch.getFromPrice().doubleValue())));
                }
                if(productSearch.getToPrice() != null && productSearch.getToPrice().doubleValue() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), productSearch.getToPrice().doubleValue())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
	}
	
	@CrossOrigin
	public Optional<Product> getProductInfoByKey(ProductPK pk) {
		return this.productRepository.findById(pk);
	}
	
	@CrossOrigin
	public List<Product> getProductInfoByProductIDList(String productIdLst) {
		List<String> listProductId = null;
		if (productIdLst.trim().length() > 0) {
			listProductId = new ArrayList<String>(Arrays.asList(productIdLst.split(",")));
		}
		if (listProductId == null) {
			return null;
		}
		return this.productRepository.getProductInfoByProductIDList(listProductId);
	}
}
