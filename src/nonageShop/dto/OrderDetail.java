package nonageShop.dto;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDetail {
	private int no; 
	private Date orderDate; 
	private Order order; //no
	private Member member; //id, name, zipNum, address, phone, 
	private Product product; // name, salePrice
	private Cart cart; //pno, quantity, resultYn
	
	
	
	public OrderDetail() {
	}
	


	public OrderDetail(int no) {
		super();
		this.no = no;
	}



	public OrderDetail(Date orderDate, Order order, Member member, Product product, Cart cart) {
		
		
		this.orderDate = orderDate;
		this.order = order;
		this.member = member;
		this.product = product;
		this.cart = cart;
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
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}


	@Override
	public String toString() {
		return String.format("OrderDetail [no=%s, orderDate=%s, order=%s, member=%s, product=%s, cart=%s]", 
				no, orderDate, order, member, product, cart);
	}
	
	
	
	
	

}
