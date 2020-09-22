package nonageShop.dto;

public class Kind {
	private int no; 
	private String name;
	
	
	
	public Kind(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return String.format("Kind [no=%s, name=%s]", no, name);
	}
	
	
	
}
