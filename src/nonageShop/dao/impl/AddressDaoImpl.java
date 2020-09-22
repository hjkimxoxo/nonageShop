package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.AddressDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Address;
import nonageShop.dto.Product;

public class AddressDaoImpl implements AddressDao {
	private static final AddressDaoImpl instance = new AddressDaoImpl();

	public AddressDaoImpl() {};
	
	public static AddressDaoImpl getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Address> selectAddressByDong(String dong) {
		String sql = "SELECT * FROM ADDRESS WHERE dong LIKE '%' || ? || '%'";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setString(1, dong);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						ArrayList<Address> list = new ArrayList<>();
						do {
							list.add(getAddress(rs));
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


	private Address getAddress(ResultSet rs) throws SQLException {
		Address a = new Address();
		a.setZipNum(rs.getString("ZIP_NUM"));
		a.setSido(rs.getString("SIDO"));
		a.setGugun(rs.getString("GUGUN"));
		a.setDong(rs.getString("DONG"));
		a.setZipCode(rs.getString("ZIP_CODE"));
		a.setBunji(rs.getString("BUNJI"));
		return a;
	}
}
