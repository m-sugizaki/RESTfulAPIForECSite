package com.cube.vn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cube.vn.dao.ProductCart;
import com.cube.vn.service.ProductCartService;

@RestController
@RequestMapping("productcart")
public class ProductCartRestControllerAPI {
    @Autowired
    ProductCartService productCartService;
    
    @CrossOrigin
	@PostMapping(path = "insertNewProductCart")
    @ResponseStatus(HttpStatus.CREATED)
    void insertNewProductCart(@RequestBody ProductCart productCart) {
    	this.productCartService.insertNewProductCart(productCart);
    }

	@CrossOrigin
	@GetMapping(path = "getProductCartInfoByKey/{productCartId}")
    Optional<ProductCart> getProductCartInfoByKey(@PathVariable int productCartId) {
        return this.productCartService.getProductCartInfoByKey(productCartId);
    }
	
	@CrossOrigin
	@GetMapping(path = "getProductCartInfoByUserID/{userId}")
	List<ProductCart> getProductCartInfoByUserID(@PathVariable String userId) {
        return this.productCartService.getProductCartInfoByUserID(userId);
    }

	@CrossOrigin
    @PutMapping(path = "updateProductCart")
	ProductCart updateProductCart(@RequestBody ProductCart productCart) {
    	return this.productCartService.updateProductCart(productCart);
    }
	
	@CrossOrigin
	@DeleteMapping(path = "deleteProductCart/{productCartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProductCart(@PathVariable int productCartId) {
    	this.productCartService.deleteProductCart(productCartId);
    }
}
