package com.cube.vn.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.cube.vn.dao.Product;
import com.cube.vn.dao.ProductDynamicSearch;
import com.cube.vn.dao.ProductPK;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	@Autowired
	private ProductRepository productRepository;

	@Test
    public void testGetProductInfoByProductIDList_HasData() {
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		List<Product> listProductInfoFoundByProductID = null;
		List<String> listProductId = new ArrayList<String>();
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		listProductId.add(pk.getProductId());
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(99);
		
		listProductId.add(pk2.getProductId());
		
		this.testEntityManager.persist(product2);
		this.testEntityManager.flush();
		
		listProductInfoFoundByProductID = this.productRepository.getProductInfoByProductIDList(listProductId);
		
		Assert.assertTrue(listProductInfoFoundByProductID != null && listProductInfoFoundByProductID.size() == 2);
		Assert.assertEquals(product.getPrimaryKey().getProductId(), listProductInfoFoundByProductID.get(0).getPrimaryKey().getProductId());
		Assert.assertEquals(product.getProductName(), listProductInfoFoundByProductID.get(0).getProductName());
		Assert.assertEquals(product.getMaker(), listProductInfoFoundByProductID.get(0).getMaker());
		Assert.assertEquals(product.getImage(), listProductInfoFoundByProductID.get(0).getImage());
		Assert.assertEquals(product.getPrice(), listProductInfoFoundByProductID.get(0).getPrice());
		Assert.assertEquals(product.getSalePoint(), listProductInfoFoundByProductID.get(0).getSalePoint());
		Assert.assertEquals(product.getStockQuantity(), listProductInfoFoundByProductID.get(0).getStockQuantity());
		Assert.assertEquals(product2.getPrimaryKey().getProductId(), listProductInfoFoundByProductID.get(1).getPrimaryKey().getProductId());
		Assert.assertEquals(product2.getProductName(), listProductInfoFoundByProductID.get(1).getProductName());
		Assert.assertEquals(product2.getMaker(), listProductInfoFoundByProductID.get(1).getMaker());
		Assert.assertEquals(product2.getImage(), listProductInfoFoundByProductID.get(1).getImage());
		Assert.assertEquals(product2.getPrice(), listProductInfoFoundByProductID.get(1).getPrice());
		Assert.assertEquals(product2.getSalePoint(), listProductInfoFoundByProductID.get(1).getSalePoint());
		Assert.assertEquals(product2.getStockQuantity(), listProductInfoFoundByProductID.get(1).getStockQuantity());
	}
	
	@Test
    public void testGetProductInfoByProductIDList_NoData() {
		List<Product> listProductInfoFoundByProductID = null;
		List<String> listProductId = new ArrayList<String>() ;
		
		listProductId.add("test1001");
		listProductId.add("test1002");
		
		listProductInfoFoundByProductID = this.productRepository.getProductInfoByProductIDList(listProductId);
		
		Assert.assertTrue(listProductInfoFoundByProductID != null && listProductInfoFoundByProductID.size() == 0);
	}
	
	@Test
    public void testFindAll_NoData() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setProductName("あああああああああああ");
		searchKeys.setMaker("PC工房");
		searchKeys.setFromPrice(new BigDecimal(10000));
		searchKeys.setToPrice(new BigDecimal(20000));
		
		specification = getSpecification(searchKeys);
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 0);
	}
	
	@Test
    public void testFindAll_HasData_ByProductName() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setProductName("5G対応 6.5型大画面スマートフォン");
		specification = getSpecification(searchKeys);
        
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 2);
        Assert.assertTrue(listSearchedProduct.contains(product2));
        Assert.assertTrue(listSearchedProduct.contains(product3));
	}
	
	@Test
    public void testFindAll_HasData_ByMaker() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setMaker("PC工房");
		specification = getSpecification(searchKeys);
        
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 3);
        Assert.assertTrue(listSearchedProduct.contains(product));
        Assert.assertTrue(listSearchedProduct.contains(product2));
        Assert.assertTrue(listSearchedProduct.contains(product3));
	}
	
	@Test
    public void testFindAll_HasData_ByPriceFrom() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setFromPrice(new BigDecimal(11000));
		specification = getSpecification(searchKeys);
        
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 2);
        Assert.assertTrue(listSearchedProduct.contains(product2));
        Assert.assertTrue(listSearchedProduct.contains(product3));
	}
	
	@Test
    public void testFindAll_HasData_ByPriceTo() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setToPrice(new BigDecimal(11000));
		specification = getSpecification(searchKeys);
        
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 1);
        Assert.assertTrue(listSearchedProduct.contains(product));
	}
	
	@Test
    public void testFindAll_HasData_ByPriceFromTo() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setFromPrice(new BigDecimal(11000));
		searchKeys.setToPrice(new BigDecimal(13000));
		specification = getSpecification(searchKeys);
        
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 2);
        Assert.assertTrue(listSearchedProduct.contains(product2));
        Assert.assertTrue(listSearchedProduct.contains(product3));
	}
	
	@Test
    public void testFindAll_HasData_ByAllKeys() {
		ProductDynamicSearch searchKeys = new ProductDynamicSearch();
		ProductPK pk = new ProductPK();
		ProductPK pk2 = new ProductPK();
		ProductPK pk3 = new ProductPK();
		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Specification<Product> specification = null;
		List<Product> listSearchedProduct = null;
		
		pk.setProductId("test1001");
		product.setPrimaryKey(pk);
		product.setProductName("B4モバイルノート GSX400R");
		product.setMaker("PC工房");
		product.setImage("テスト".getBytes());
		product.setPrice(new BigDecimal(10830.60));
		product.setSalePoint("格安ハイスペックPC！ 普段使いにストレスを感じさせません！！！");
		product.setStockQuantity(99);
		
		this.testEntityManager.persist(product);
		
		pk2.setProductId("test1002");
		product2.setPrimaryKey(pk2);
		product2.setProductName("5G対応 6.5型大画面スマートフォン RVG250");
		product2.setMaker("PC工房");
		product2.setImage("テスト".getBytes());
		product2.setPrice(new BigDecimal(11000.60));
		product2.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product2.setStockQuantity(100);
		
		this.testEntityManager.persist(product2);
		
		pk3.setProductId("test1003");
		product3.setPrimaryKey(pk3);
		product3.setProductName("5G対応 6.5型大画面スマートフォン RVG350");
		product3.setMaker("PC工房");
		product3.setImage("テスト".getBytes());
		product3.setPrice(new BigDecimal(12000.60));
		product3.setSalePoint("先行発売！ ハンドメイドのため台数限定です。お申し込みはお早めに！！！");
		product3.setStockQuantity(101);
		
		this.testEntityManager.persist(product3);
		this.testEntityManager.flush();
		
		searchKeys.setProductName("B4モバイルノート GSX400R");
		searchKeys.setMaker("PC工房");
		searchKeys.setFromPrice(new BigDecimal(10000));
		searchKeys.setToPrice(new BigDecimal(200000));
		
		specification = getSpecification(searchKeys);
        listSearchedProduct = this.productRepository.findAll(specification);
        
        Assert.assertTrue(listSearchedProduct != null && listSearchedProduct.size() == 1);
        Assert.assertTrue(listSearchedProduct.contains(product));
	}
	
	@SuppressWarnings("serial")
	private Specification<Product> getSpecification(ProductDynamicSearch searchKeys) {
		return new Specification<Product>() {
			@Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
                if(searchKeys.getProductName() != null && searchKeys.getProductName().trim().length() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("productName"), "%" + searchKeys.getProductName().trim() + "%")));
                }
                if(searchKeys.getMaker() != null && searchKeys.getMaker().trim().length() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("maker"), "%" + searchKeys.getMaker().trim() + "%")));
                }
                if(searchKeys.getFromPrice() != null && searchKeys.getFromPrice().doubleValue() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchKeys.getFromPrice().doubleValue())));
                }
                if(searchKeys.getToPrice() != null && searchKeys.getToPrice().doubleValue() > 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchKeys.getToPrice().doubleValue())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
	}
}
