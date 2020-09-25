package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.CartDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Cart;
import nonageShop.dto.Member;
import nonageShop.dto.Product;

public class CartDaoImpl implements CartDao {
	private static final CartDaoImpl instance = new CartDaoImpl();
	
	public CartDaoImpl() {}
	
	public static CartDaoImpl getInstance() {
		return instance;
	}

	private Cart getCart(ResultSet rs) throws SQLException {
		Cart c = new Cart();
		Product p = new Product();
		Member m = new Member();
		c.setProduct(p);
		c.setMember(m);
		
		c.setNo(rs.getInt("NO"));
		p.setNo(rs.getInt("PNO"));
		m.setId(rs.getString("MEMBERID"));
		m.setName(rs.getString("MNAME"));
		p.setName(rs.getString("PNAME"));
		c.setQuantity(rs.getInt("QUANTITY"));
		p.setSalePrice(rs.getInt("PSALEPRICE"));
		c.setRegDate(rs.getTimestamp("REG_DATE"));
		
		try {
			c.setResultYn(rs.getString("RESULT_YN"));
		}catch (SQLException e) {
			
		}
		return c;
	}

	@Override
	public ArrayList<Cart> cartList() {
		String sql = "SELECT NO, PNO, MEMBERID, MNAME, PNAME, QUANTITY, PSALEPRICE, REG_DATE FROM CART_VIEW";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Cart> list = new ArrayList<>();
				do {
					list.add(getCart(rs));
				}while(rs.next());
				return list;
			}
			
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}
	
	
	@Override
	public ArrayList<Cart> cartListById(String userId) {
		String sql = "SELECT NO, PNO, MEMBERID, MNAME, PNAME, QUANTITY, PSALEPRICE, REG_DATE FROM CART_VIEW WHERE MEMBERID = ? ";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
					pstmt.setString(1, userId);
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						ArrayList<Cart> list = new ArrayList<>();
						do {
							list.add(getCart(rs));
						}while(rs.next());
						return list;
					}
				} catch(SQLException e) {
					throw new CustomSQLException(e);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	
	}
	@Override
	public int insertCart(Cart cart) {
		String sql = "INSERT INTO CART(PNO, MEMBERID, QUANTITY, RESULT_YN, REG_DATE) VALUES(?, ?, ?, '1', SYSDATE)";
		Product product = cart.getProduct();
		Member member = cart.getMember();
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, product.getNo());
			pstmt.setString(2, member.getId());
			pstmt.setInt(3, cart.getQuantity());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public int deleteCart(int no) {
		String sql = "DELETE FROM CART WHERE NO = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int updateCartResult(Cart cart) {
		String sql = "update cart set result_YN=2 where no=?";
        try (Connection con = JdbcUtil.getConnection();
        		PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, cart.getNo());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }


}


