package nonageShop.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	private int no;
	private Member member;
	private Date orderDate;
	private ArrayList<OrderDetail> details;

	public Order() {
		
	}

	public Order(int no) {
		
		this.no = no;
	}

	public Order(int no, Member member, Date orderDate, ArrayList<OrderDetail> details) {
		
		this.no = no;
		this.member = member;
		this.orderDate = orderDate;
		this.details = details;
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

	public ArrayList<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<OrderDetail> details) {
		this.details = details;
	}

	
	@Override
	public String toString() {
		return String.format("Order [no=%s, member=%s, orderDate=%s, details=%s]", no, member, orderDate, details);
	}

}
