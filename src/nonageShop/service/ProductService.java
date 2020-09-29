package nonageShop.service;

import java.util.ArrayList;

import nonageShop.dao.impl.ProductDaoImpl;
import nonageShop.dto.Product;

public class ProductService {
	public ProductDaoImpl dao = ProductDaoImpl.getInstance();
	
	public ArrayList<Product> listNewProduct(){
		return dao.listNewProduct();
	}
		
	public ArrayList<Product> listBestProduct(){
		return dao.listBestProduct();
	}
		
	public Product getProduct(int no) {
		return dao.getProduct(no);
	}
		
	public ArrayList<Product> listKindProduct(String kind){
		return dao.listKindProduct(kind);
	}
	
	public int totalRecord(String productName) {
		return dao.totalRecord(productName);
	}
	//페이지 이동
	public String pageNumber(int totalPage, String name) {
		return dao.pageNumber(totalPage, name);
	}
	
	public ArrayList<Product> listProduct(int totalPage, String productName) {
		return dao.listProduct(totalPage, productName);
	}
	
	public int insertProduct(Product product) {
		return dao.insertProduct(product);
	}
	
	public int updateProduct(Product product) {
		return dao.updateProduct(product);
	}
	
	public int deleteProduct(Product product) {
		return dao.deleteProduct(product);
	}
}
