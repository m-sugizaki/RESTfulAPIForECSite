package com.cube.vn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cube.vn.dao.ProductCart;
import com.cube.vn.repository.ProductCartRepository;

@Service
@Transactional
public class ProductCartService {
	@Autowired
	ProductCartRepository productCartRepository;
	
	@CrossOrigin
	public void insertNewProductCart(ProductCart productCart) {
		this.productCartRepository.insertNewProductCart(productCart.getUserId(), productCart.getProductId(), 
				productCart.getQuantity(), productCart.getSize(), productCart.getColor());
	}

	@CrossOrigin
	public Optional<ProductCart> getProductCartInfoByKey(int productCartId) {
		return this.productCartRepository.findById(productCartId);
	}

	@CrossOrigin
	public List<ProductCart> getProductCartInfoByUserID(String userId) {
		return this.productCartRepository.getProductCartInfoByUserID(userId);
	}

	@CrossOrigin
	public ProductCart updateProductCart(ProductCart productCart) {
		return this.productCartRepository.save(productCart);
	}

	@CrossOrigin
	public void deleteProductCart(int productCartId) {
		this.productCartRepository.deleteById(productCartId);
	}
}
