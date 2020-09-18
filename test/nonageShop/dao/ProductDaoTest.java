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
	
	@Test
	public void testListNewProduct() {
		System.out.print("NewProduct");
		ArrayList<Product> list = dao.listNewProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testListBestProduct() {
		System.out.print("BestProduct, 8슬리퍼 핑크샌달 3슬리퍼");
		ArrayList<Product> list = dao.listBestProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testGetProduct() {
		System.out.println("getProduct");
		Product p = dao.getProduct(1);
		Assert.assertNotNull(p);
		System.out.println(p);
	}

	@Test
	public void testListKindProduct() {
		System.out.println("kindProduct -> 2");
		ArrayList<Product> list = dao.listKindProduct("2");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
