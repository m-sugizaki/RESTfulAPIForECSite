package com.cube.vn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductDynamicSearch;
import com.cube.vn.dao.ProductPK;
import com.cube.vn.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductRestControllerAPI {
    @Autowired
    ProductService productService;
    
    @CrossOrigin
	@PostMapping(path = "searchProductData")
    List<Product> searchProductData(@RequestBody ProductDynamicSearch productSearch) {
        return this.productService.searchProductData(productSearch);
    }
    
    @CrossOrigin
	@GetMapping(path = "getProductInfoByKey/{productId}")
    Optional<Product> getProductInfoByKey(@PathVariable String productId) {
    	ProductPK pk = new ProductPK();
    	pk.setProductId(productId);
        return this.productService.getProductInfoByKey(pk);
    }
    
    @CrossOrigin
	@GetMapping(path = "getProductInfoByProductIDList/{productIdLst}")
    List<Product> getProductInfoByProductIDList(@PathVariable String productIdLst) {
    	return this.productService.getProductInfoByProductIDList(productIdLst);
    }
}
