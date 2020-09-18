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
	
	
}
