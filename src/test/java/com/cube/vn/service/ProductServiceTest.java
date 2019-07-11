package com.cube.vn.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductDynamicSearch;
import com.cube.vn.dao.ProductPK;
import com.cube.vn.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@MockBean
	ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@Test
	public void testGetProductInfoByKey() {
		Product product = new Product();
		ProductPK productPK = new ProductPK();

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);

		Optional<Product> optional = Optional.of(product);

		Mockito.when(this.productRepository.findById(productPK)).thenReturn(optional);

		Assert.assertEquals(this.productService.getProductInfoByKey(productPK), optional);
		Mockito.verify(this.productRepository, Mockito.times(1)).findById(productPK);
	}

	@Test
	public void testGetProductInfoByProductIDList() {
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<String> productIdLst = new ArrayList<String>();

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(98000));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);

		products.add(product);
		products.add(product2);

		String productId = productPK.getProductId() + "," + productPK2.getProductId();
		productIdLst.add(productPK.getProductId());
		productIdLst.add(productPK2.getProductId());

		Mockito.when(this.productRepository.getProductInfoByProductIDList(productIdLst)).thenReturn(products);

		Assert.assertEquals(this.productService.getProductInfoByProductIDList(productId), products);
		Mockito.verify(this.productRepository, Mockito.times(1)).getProductInfoByProductIDList(productIdLst);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchNoKeys_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(98000));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(128000));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product);
		products.add(product2);
		products.add(product3);
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		
		Assert.assertEquals(this.productService.searchProductData(dynamicSearch), products);
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchAllKeys_NoData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(98000));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(128000));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product);
		products.add(product2);
		products.add(product3);
		
		dynamicSearch.setProductName("あああああああああああ");
		dynamicSearch.setMaker("PC工房");
		dynamicSearch.setFromPrice(new BigDecimal(10000));
		dynamicSearch.setToPrice(new BigDecimal(20000));
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(new ArrayList<Product>());
		
		Assert.assertTrue(this.productService.searchProductData(dynamicSearch).size() == 0);
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchByProductname_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<Product> listSearchedProduct = null;

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(11000.60));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(12000.60));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product2);
		products.add(product3);
		
		dynamicSearch.setProductName("5G対応 6.5型大画面スマートフォン");
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		listSearchedProduct = this.productService.searchProductData(dynamicSearch);
		Assert.assertTrue(listSearchedProduct.size() == 2 && listSearchedProduct.contains(product2) && listSearchedProduct.contains(product3));
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchByMaker_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<Product> listSearchedProduct = null;

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(11000.60));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(12000.60));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product);
		products.add(product2);
		products.add(product3);
		
		dynamicSearch.setMaker("PC工房");
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		listSearchedProduct = this.productService.searchProductData(dynamicSearch);
		Assert.assertTrue(listSearchedProduct.size() == 3 && listSearchedProduct.contains(product) &&
				listSearchedProduct.contains(product2) && listSearchedProduct.contains(product3));
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchByPriceFrom_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<Product> listSearchedProduct = null;

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(11000.60));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(12000.60));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product2);
		products.add(product3);
		
		dynamicSearch.setFromPrice(new BigDecimal(11000));
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		listSearchedProduct = this.productService.searchProductData(dynamicSearch);
		Assert.assertTrue(listSearchedProduct.size() == 2 && 
				listSearchedProduct.contains(product2) && listSearchedProduct.contains(product3));
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchByPriceTo_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<Product> listSearchedProduct = null;

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(11000.60));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(12000.60));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product);
		
		dynamicSearch.setToPrice(new BigDecimal(11000));
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		listSearchedProduct = this.productService.searchProductData(dynamicSearch);
		Assert.assertTrue(listSearchedProduct.size() == 1 && listSearchedProduct.contains(product));
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSearchProductData_SearchByPriceFromTo_HasData() {
		ProductDynamicSearch dynamicSearch = new ProductDynamicSearch();
		Product product = new Product();
		ProductPK productPK = new ProductPK();
		Product product2 = new Product();
		ProductPK productPK2 = new ProductPK();
		Product product3 = new Product();
		ProductPK productPK3 = new ProductPK();
		List<Product> products = new ArrayList<Product>();
		List<Product> listSearchedProduct = null;

		productPK.setProductId("TEST100001");
		product.setPrimaryKey(productPK);
		product.setColor("Red,Blue");
		product.setImage("テスト".getBytes());
		product.setMaker("PC工房");
		product.setPrice(new BigDecimal(10830.60));
		product.setProductName("B4モバイルノート GSX400R");
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setSize("X,XL,M");
		product.setSimilarProductId("TEST101001,TEST101002");
		product.setStockQuantity(99);
		
		productPK2.setProductId("TEST101001");
		product2.setPrimaryKey(productPK2);
		product2.setColor("青,赤");
		product2.setImage("テスト".getBytes());
		product2.setMaker("PC工房");
		product2.setPrice(new BigDecimal(11000.60));
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setSize("X,XL,M");
		product2.setSimilarProductId("TEST101002");
		product2.setStockQuantity(100);
		
		productPK3.setProductId("TEST101002");
		product3.setPrimaryKey(productPK3);
		product3.setColor("青,赤");
		product3.setImage("テスト".getBytes());
		product3.setMaker("PC工房");
		product3.setPrice(new BigDecimal(12000.60));
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setSize("X,XL,M");
		product3.setSimilarProductId("");
		product3.setStockQuantity(200);

		products.add(product2);
		products.add(product3);
		
		dynamicSearch.setFromPrice(new BigDecimal(11000));
		dynamicSearch.setToPrice(new BigDecimal(13000));
		
		Mockito.when(this.productRepository.findAll(Mockito.any(Specification.class))).thenReturn(products);
		listSearchedProduct = this.productService.searchProductData(dynamicSearch);
		Assert.assertTrue(listSearchedProduct.size() == 2 && 
				listSearchedProduct.contains(product2) && listSearchedProduct.contains(product3));
		Mockito.verify(this.productRepository,Mockito.times(1)).findAll(Mockito.any(Specification.class));
	}
}
