package nonageShop.dto;

import java.util.Date;

public class Cart {
	private int no;
	private int quantity;
	private Member member; // id,name
	private Product product; // pno, pname, salePrice
	private String resultYn;
	private Date regDate;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Cart(int no) {
		super();
		this.no = no;
	}



	public Cart(int quantity, Member member, Product product, String resultYn, Date regDate) {
		
		this.quantity = quantity;
		this.member = member;
		this.product = product;
		this.resultYn = resultYn;
		this.regDate = regDate;
	}


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String getResultYn() {
		return resultYn;
	}
	public void setResultYn(String resultYn) {
		this.resultYn = resultYn;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return String.format("Cart [no=%s, quantity=%s, member=%s, product=%s, resultYn=%s, regDate=%s]", no, quantity,
				member, product, resultYn, regDate);
	}

	

}
