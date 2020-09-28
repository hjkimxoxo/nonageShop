package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.OrderDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;
import nonageShop.dto.Product;

public class OrderDaoImpl implements OrderDao {
	private static final OrderDaoImpl instance = new OrderDaoImpl();

	public OrderDaoImpl() {};

	public static OrderDaoImpl getInstance() {
		return instance;
	}
	
	
	
	
	
	@Override
	public Order listByOrderById(String id, String result, int no) {
		String sql = "SELECT ONO, ODNO, ORDERDATE, MEMBERID, MNAME, ZIP_NUM, ADDRESS, PHONE, PNO, PNAME, QUANTITY, SALEPRICE, RESULT"
				+ " FROM ORDER_VIEW WHERE MEMBERID = ? AND RESULT LIKE '%'||?||'%' AND ONO = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setString(1, id);
				pstmt.setString(2, result);
				pstmt.setInt(3, no);
					
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Order order = new Order();
	                    order.setNo(rs.getInt("ONO"));
	                    Member member = new Member(rs.getString("MEMBERID"), rs.getString("MNAME"));
	                    member.setZipNum(rs.getString("ZIP_NUM"));
	                    member.setAddress(rs.getString("ADDRESS"));
	                    member.setPhone(rs.getString("PHONE"));
	                    order.setMember(member);
	                    
						ArrayList<OrderDetail> detail = new ArrayList<OrderDetail>();
						do {
							detail.add(getOrderDetail(rs));
						} while (rs.next());
						
						order.setDetails(detail);
						order.setOrderDate(detail.get(0).getOrderDate());
						return order;
					}
				}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> selectNoOrdering(String id) {
		
		String sql = "SELECT DISTINCT ONO FROM ORDER_VIEW WHERE MEMBERID = ? AND RESULT = '1' ORDER BY ONO DESC";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<Integer> list = new ArrayList<>();
					do {
						list.add(rs.getInt(1));
					} while (rs.next());
					return list;
				}
			} catch(SQLException e) {
				throw new CustomSQLException(e);
			}
	} catch (SQLException e) {
		throw new CustomSQLException(e);
	}
		return null;
	}

	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
		//"SELECT ONO, ODNO, ORDERDATE, MEMBERID, MNAME, ZIP_NUM, ADDRESS,
		//PHONE, PNO, PNAME, QUANTITY, SALEPRICE, RESULT"
				
		Order o = new Order();
		OrderDetail od = new OrderDetail();
		Cart c = new Cart();
		Product p = new Product();
		Member m = new Member();
		
		od.setCart(c);
		o.setMember(m);
		c.setMember(m);
		c.setProduct(p);
		
		
		o.setNo(rs.getInt("ONO"));
		od.setNo(rs.getInt("ODNO"));
		od.setOrderDate(rs.getTimestamp("ORDERDATE"));
		m.setId(rs.getString("MEMBERID"));
		m.setName(rs.getString("MNAME"));
		m.setZipNum(rs.getString("ZIP_NUM"));
		m.setAddress(rs.getString("ADDRESS"));
		m.setPhone(rs.getString("PHONE"));
		p.setNo(rs.getInt("PNO"));
		p.setName(rs.getString("PNAME"));
		c.setQuantity(rs.getInt("QUANTITY"));
		p.setSalePrice(rs.getInt("SALEPRICE"));
		od.setResult(rs.getString("RESULT"));
		
		return od;
	}

	public int selectMaxOrderNo() {
		 String sql = "select max(no) from orders";
	        try (Connection con = JdbcUtil.getConnection();
	        		PreparedStatement pstmt = con.prepareStatement(sql);
	                ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new CustomSQLException(e);
	        }
	        return 0;
	    }

	@Override
	public ArrayList<OrderDetail> listOrderDetailById(String id) {
		String sql = "SELECT ONO, ODNO, ORDERDATE, MEMBERID, MNAME, ZIP_NUM, ADDRESS, "
				+ "PHONE, PNO, PNAME, QUANTITY, SALEPRICE, RESULT FROM ORDER_VIEW WHERE MEMBERID = ? ORDER BY odno desc";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				pstmt.setString(1, id);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						ArrayList<OrderDetail> detail = new ArrayList<OrderDetail>();
						do {
							detail.add(getOrderDetail(rs));
						} while (rs.next());
						return detail;
					}
				}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	
}
