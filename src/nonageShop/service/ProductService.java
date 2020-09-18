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
}
