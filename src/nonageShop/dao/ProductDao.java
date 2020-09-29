package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Product;

public interface ProductDao {
	//신상리스트
	ArrayList<Product> listNewProduct();
	//베스트상품 리스트
	ArrayList<Product> listBestProduct();
	//상품리스트
	Product getProduct(int no);
	//종류별 상품리스트
	ArrayList<Product> listKindProduct(String kind);
	
	//--관리자-- 
	
	int totalRecord(String productName);
	//페이지 이동
	String pageNumber(int totalPage, String name);
	
	ArrayList<Product> listProduct(int totalPage, String productName);
	
	int insertProduct(Product product);
	
	int updateProduct(Product product);
	
	int deleteProduct(Product product);
	
}
