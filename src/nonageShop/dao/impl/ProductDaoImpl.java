package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageShop.dao.ProductDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Product;

public class ProductDaoImpl implements ProductDao {
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	
	public ProductDaoImpl() {}
	
	public static ProductDaoImpl getInstance() {
		return instance;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		//SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT
		
		Product p = new Product();
		
		p.setNo(rs.getInt("NO"));
		p.setName(rs.getString("NAME"));
		p.setImage(rs.getString("IMAGE"));
		p.setSalePrice(rs.getInt("SALEPRICE"));
		try {
			p.setMargin(rs.getInt("MARGIN"));
			p.setKind(rs.getString("KIND"));
			p.setPrice(rs.getInt("PRICE"));
			p.setContent(rs.getString("CONTENT"));
			p.setDelYn(rs.getString("DEL_YN"));
			p.setBestYn(rs.getString("BEST_YN"));
			p.setRegDate(rs.getTimestamp("REG_DATE"));
		} catch (SQLException e) {
			
		}
		return p;
	}
	
	
	@Override
	public ArrayList<Product> listNewProduct() {
		String sql = "SELECT NO, NAME, SALEPRICE, IMAGE FROM NEW_PRO_VIEW";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<>();
				do {
					list.add(getProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}


	@Override
	public ArrayList<Product> listBestProduct() {
		String sql = "SELECT  NO, NAME, SALEPRICE, IMAGE FROM best_pro_view";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<>();
				do {
					list.add(getProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public Product getProduct(int no) {
		String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public ArrayList<Product> listKindProduct(String kind) {
		String sql = "SELECT NO, NAME, KIND, PRICE, SALEPRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE KIND = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, kind);
				try( ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						ArrayList<Product> list = new ArrayList<>();
						do {
							list.add(getProduct(rs));
						} while (rs.next());
						return list;
					}
				} catch (SQLException e) {
					throw new CustomSQLException(e);
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}		
		return null;
	}
}
