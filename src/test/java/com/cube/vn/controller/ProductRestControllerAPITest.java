package com.cube.vn.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductDynamicSearch;
import com.cube.vn.dao.ProductPK;
import com.cube.vn.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestControllerAPI.class)
public class ProductRestControllerAPITest {
	private static final ObjectMapper OM = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	@Test
	public void testGetProductInfoByKey() throws Exception {
		
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product productReturn = null;
    	ProductPK productPK = new ProductPK();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1005");
		
		Mockito.when(this.productService.getProductInfoByKey(Mockito.any(ProductPK.class))).thenReturn(Optional.of(product));
		
		requestBuilder = MockMvcRequestBuilders
				.get("/product/getProductInfoByKey/test1001" ) 
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		
		productReturn = ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product.class);
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(productReturn.getColor(), product.getColor());
		Assert.assertEquals(productReturn.getMaker(), product.getMaker());
		Assert.assertEquals(productReturn.getProductName(), product.getProductName());
		Assert.assertEquals(productReturn.getSalePoint(), product.getSalePoint());
		Assert.assertEquals(productReturn.getSimilarProductId(), product.getSimilarProductId());
		Assert.assertEquals(productReturn.getSize(), product.getSize());
		Assert.assertEquals(productReturn.getPrice(), product.getPrice());
		Assert.assertEquals(productReturn.getImage().length, product.getImage().length);
		for (int i = 0; i < productReturn.getImage().length; i++) {
			Assert.assertEquals(productReturn.getImage()[i], product.getImage()[i]);
		}
		Assert.assertEquals(productReturn.getStockQuantity(), product.getStockQuantity());
		Assert.assertEquals(productReturn.getPrimaryKey().getProductId(), product.getPrimaryKey().getProductId());
		
		Mockito.verify(this.productService, Mockito.times(1)).getProductInfoByKey(Mockito.any(ProductPK.class));
	}
	
	@Test
	public void testGetProductInfoByProductIDList() throws Exception {
		String productId ="test1001,test1002";
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1005");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("B4モバイルノート GSX400R");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(10830.60));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1005");
		listProducts.add(product);
		listProducts.add(product2);
		
		Mockito.when(this.productService.getProductInfoByProductIDList(Mockito.any(String.class))).thenReturn(listProducts);
		
		requestBuilder = MockMvcRequestBuilders
				.get("/product/getProductInfoByProductIDList/" + productId)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).getProductInfoByProductIDList(Mockito.any(String.class));
	
	}
	
	@Test
	public void testSearchProductData_SearchNoKeys_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1005");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("B4モバイルノート GSX400R");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(10830.60));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1005");
		listProducts.add(product);
		listProducts.add(product2);
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchAllKeys_NoData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(12000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(new ArrayList<Product>() );
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(0, listProductsReturn.size());
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchByProductname_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(12000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		listProducts.add(product2);
		listProducts.add(product3);
		
		productDynamicSearch.setProductName("5G対応 6.5型大画面スマートフォン");
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchByMaker_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(12000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		listProducts.add(product);
		listProducts.add(product2);
		listProducts.add(product3);
		
		productDynamicSearch.setMaker("PC工房");
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchByPriceFrom_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		listProducts.add(product2);
		listProducts.add(product3);
		
		productDynamicSearch.setFromPrice(new BigDecimal(11000));
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchByPriceTo_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		listProducts.add(product);
		
		productDynamicSearch.setToPrice(new BigDecimal(10830.60));
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
	
	@Test
	public void testSearchProductData_SearchByPriceFromTo_HasData() throws Exception {
		RequestBuilder requestBuilder = null;
    	MvcResult result = null;
    	MockHttpServletResponse response = null;
    	Product product = new Product();
    	Product product2 = new Product();
    	Product product3 = new Product();
    	ProductPK productPK = new ProductPK();
    	ProductPK productPK2 = new ProductPK();
    	ProductPK productPK3 = new ProductPK();
    	List<Product> listProducts = new ArrayList<Product>();
    	List<Product> listProductsReturn = null;
    	ProductDynamicSearch productDynamicSearch = new ProductDynamicSearch();
    	
    	productPK.setProductId("test1001");
		product.setPrimaryKey(productPK);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		product.setColor("赤,青,黒");
		product.setSize("X,L");
		product.setSimilarProductId("test1002");
		productPK2.setProductId("test1002");
		product2.setPrimaryKey(productPK2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000));
		product2.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product2.setStockQuantity(99);
		product2.setColor("赤,青,黒");
		product2.setSize("X,L");
		product2.setSimilarProductId("test1003");
		productPK3.setProductId("test1003");
		product3.setPrimaryKey(productPK3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(20000));
		product3.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product3.setStockQuantity(99);
		product3.setColor("赤,青,黒");
		product3.setSize("X,L");
		product3.setSimilarProductId("test1001");
		
		listProducts.add(product);
		listProducts.add(product2);
		listProducts.add(product3);
		
		productDynamicSearch.setFromPrice(new BigDecimal(10830.60));
		productDynamicSearch.setToPrice(new BigDecimal(20000));
		
		Mockito.when(this.productService.searchProductData(Mockito.any(ProductDynamicSearch.class))).thenReturn(listProducts);
		requestBuilder = MockMvcRequestBuilders
				.post("/product/searchProductData")
				.accept(MediaType.APPLICATION_JSON).content(ProductRestControllerAPITest.OM.writeValueAsString(productDynamicSearch))
				.contentType(MediaType.APPLICATION_JSON);
		result = this.mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		Product[] arrProduct = (ProductRestControllerAPITest.OM.readValue(response.getContentAsString(), Product[].class));
		listProductsReturn = new ArrayList<Product>(Arrays.asList(arrProduct));
		
		Assert.assertEquals(listProducts.size(), listProductsReturn.size());
		for (int i = 0; i < listProductsReturn.size(); i++) {
			
			Assert.assertEquals(listProducts.get(i).getColor(), listProductsReturn.get(i).getColor());
			Assert.assertEquals(listProducts.get(i).getMaker(), listProductsReturn.get(i).getMaker());
			Assert.assertEquals(listProducts.get(i).getProductName(), listProductsReturn.get(i).getProductName());
			Assert.assertEquals(listProducts.get(i).getSalePoint(), listProductsReturn.get(i).getSalePoint());
			Assert.assertEquals(listProducts.get(i).getSimilarProductId(), listProductsReturn.get(i).getSimilarProductId());
			Assert.assertEquals(listProducts.get(i).getSize(), listProductsReturn.get(i).getSize());
			Assert.assertEquals(listProducts.get(i).getPrice(), listProductsReturn.get(i).getPrice());
			Assert.assertEquals(listProducts.get(i).getImage().length, listProductsReturn.get(i).getImage().length);
			for (int j = 0; j < listProducts.get(i).getImage().length; j++) {
				Assert.assertEquals(listProducts.get(i).getImage()[j], listProductsReturn.get(i).getImage()[j]);
			}
			Assert.assertEquals(listProducts.get(i).getStockQuantity(), listProductsReturn.get(i).getStockQuantity());
			Assert.assertEquals(listProducts.get(i).getPrimaryKey().getProductId(), listProductsReturn.get(i).getPrimaryKey().getProductId());
		}
		Mockito.verify(this.productService, Mockito.times(1)).searchProductData(Mockito.any(ProductDynamicSearch.class));
	}
}
