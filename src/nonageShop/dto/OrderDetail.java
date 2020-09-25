package nonageShop.dto;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDetail {
	private int no; 
	private Date orderDate; 
	private String result;
	private Cart cart;
	
	public OrderDetail() {}
	
	public OrderDetail(int no) {
		super();
		this.no = no;
	}
	
	
	
	public OrderDetail(Cart cart) {
		super();
		this.cart = cart;
	}

	public OrderDetail(int no, Date orderDate, Cart cart, String result) {
		super();
		this.no = no;
		this.orderDate = orderDate;
		this.cart = cart;
		this.result = result;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return String.format("OrderDetail [no=%s, orderDate=%s, cart=%s, result=%s]", no, orderDate, cart, result);
	}
	
	
	
	
	
	

}
