package nonageShop.dto;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
	private int no;
	private Member member;
	private Date orderDate;
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int no) {
		super();
		this.no = no;
	}
	public Order(Member member, Date orderDate) {
		
		super();
		this.member = member;
		this.orderDate = orderDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
