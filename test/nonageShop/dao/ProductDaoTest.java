package nonageShop.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.ProductDaoImpl;
import nonageShop.dto.Product;


public class ProductDaoTest {
	private static ProductDaoImpl dao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		dao = ProductDaoImpl.getInstance();
	}
	
	//@Test
	public void testListNewProduct() {
		System.out.print("NewProduct");
		ArrayList<Product> list = dao.listNewProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	//@Test
	public void testListBestProduct() {
		
		System.out.print("BestProduct, 8슬리퍼 핑크샌달 3슬리퍼");
		ArrayList<Product> list = dao.listBestProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	//@Test
	public void testGetProduct() {
		System.out.println("getProduct");
		Product p = dao.getProduct(1);
		Assert.assertNotNull(p);
		System.out.println(p);
	}

	//@Test
	public void testListKindProduct() {
		System.out.println("kindProduct -> 2");
		ArrayList<Product> list = dao.listKindProduct("2");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	//@Test 
	public void testTotalRecord() {
		System.out.println("totalRecord");
		int name = dao.totalRecord("스");
		System.out.println(name);
	}
	
	//@Test
	public void testProductList() {
		ArrayList<Product> list =dao.listProduct(1, "스");
		list.stream().forEach(System.out::println);
	}
	
	//@Test
	public void testInsert() {
		Product product = new Product("새상품", "1", 15000, 20000, 5000, "새상품ㅇㅇ", "이미지");
		int res = dao.insertProduct(product);
		System.out.println(res);
	}
	
	//@Test 
	public void testUpdate() {
		Product p = dao.getProduct(12);
		p.setName("새상품변경");
		p.setKind("2");
		p.setPrice(17000);
		p.setSalePrice(20000);
		p.setMargin(3000);
		p.setContent("새상품내용변경");
		p.setImage("새상품이미지변경");
		
		int res = dao.updateProduct(p);
		System.out.println(res);
	}
	
	@Test
	public void testTotalPage() {
		int res = dao.totalRecord("");
		System.out.println(res);
	}
}
