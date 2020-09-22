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
import oracle.jdbc.proxy.annotation.Pre;
import sun.net.www.content.text.plain;

public class OrderDaoImpl implements OrderDao {
	private static final OrderDaoImpl instance = new OrderDaoImpl();

	public OrderDaoImpl() {
	};

	public static OrderDaoImpl getInstance() {
		return instance;
	}

	private void rollBack(Connection con, SQLException e) {
		try {
			con.rollback();
			throw new CustomSQLException(e);
		} catch (SQLException ex) {
		}
	}

	private void closeUtil(Connection con, PreparedStatement maxPstmt, PreparedStatement insertPstmt) {
		try {
			if (maxPstmt != null) {
				maxPstmt.close();
			}
			if (insertPstmt != null) {
				insertPstmt.close();
			}
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		} catch (SQLException ex) {
		}

	}

	@Override
	public int insertOrder(ArrayList<Cart> cartList, String id) {

		int maxNo = 0;

		String max = "SELECT max(no) FROM orders";
		String insert = "insert into orders(id, order_date) values(?, sysdate)";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement maxPstmt = con.prepareStatement(max);
				ResultSet rs = maxPstmt.executeQuery()) {

			con.setAutoCommit(false);

			if (rs.next()) {
				maxNo = rs.getInt(1);
			}
			maxPstmt.close();

			PreparedStatement insertPstmt = con.prepareStatement(insert);
			insertPstmt.setString(1, id);
			insertPstmt.executeUpdate();

			for (Cart cart : cartList) {
				insertOrderDetail(cart, maxNo);
			}
			con.commit();

		} catch (SQLException e) {
			Connection con = null;
			rollBack(con, e);
		} finally {
			Connection con = null;
			PreparedStatement maxPstmt = null;
			PreparedStatement insertPstmt = null;
			closeUtil(con, maxPstmt, insertPstmt);
		}

		return 0;
	}

	@Override
	public int insertOrderDetail(Cart cart, int maxNo) {
		String insert = "INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(?, ?, ?, '1')";
		String updateCart = "UPDATE cart SET result_yn = 2 WHERE NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement insertPstmt = con.prepareStatement(insert)) {

			con.setAutoCommit(false);

			insertPstmt.setInt(1, maxNo);
			insertPstmt.setInt(2, cart.getNo());
			insertPstmt.setInt(3, cart.getQuantity());
			insertPstmt.executeUpdate();

			PreparedStatement updatePstmt = con.prepareStatement(updateCart);
			updatePstmt.setInt(1, cart.getNo());
			updatePstmt.executeUpdate();

			con.commit();

		} catch (SQLException e) {
			Connection con = null;
			rollBack(con, e);
		} finally {
			Connection con = null;
			PreparedStatement maxPstmt = null;
			PreparedStatement insertPstmt = null;
			closeUtil(con, maxPstmt, insertPstmt);
		}
		return 0;
	}

	@Override
	public ArrayList<Order> listByOrderById(String id, String result, int no) {
		String sql = "SELECT ONO, ODNO, ORDERDATE, MEMBERID, MNAME, ZIP_NUM, ADDRESS, PHONE, PNO, PNAME, QUANTITY, SALEPRICE, RESULT FROM ORDER_VIEW WHERE memberid = ? AND RESULT LIKE '%'||?||'%' AND ono = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Order> list = new ArrayList<>();
				do {
					list.add(getOrder(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public ArrayList<Integer> selectNoOrdering(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private Order getOrder(ResultSet rs) throws SQLException {
		Order o = new Order();
		OrderDetail od = new OrderDetail();
		Member m = new Member();
		Cart c = new Cart();
		Product p = new Product();
		
		od.setOrder(o);
		od.setCart(c);
		od.setMember(m);
		od.setProduct(p);
		
		od.setNo(rs.getInt("ODNO"));
		o.setNo(rs.getInt("ONO"));
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
		c.setResultYn(rs.getString("RESULT"));
		
		return o;
	}
}
