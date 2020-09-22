package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.MemberDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Member;
import nonageShop.dto.Product;

public class MemberDaoImpl implements MemberDao {
	private static final MemberDaoImpl instance = new MemberDaoImpl();
	
	public MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		return instance;
	}

	private Member getMember(ResultSet rs) throws SQLException {
		Member mem = new Member();
		mem.setId(rs.getString("ID"));
		mem.setPwd(rs.getString("PWD"));
		mem.setName(rs.getString("NAME"));
		mem.setEmail(rs.getString("EMAIL"));
		mem.setZipNum(rs.getString("ZIP_NUM"));
		mem.setAddress(rs.getString("ADDRESS"));
		mem.setPhone(rs.getString("PHONE"));
		mem.setLeaveYn(rs.getString("LEAVE_YN"));
		mem.setJoinDate(rs.getTimestamp("JOIN_DATE"));
		return mem;
	}
	
	@Override
	public Member getMember(String id) {
		String sql = "SELECT ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE FROM MEMBER WHERE ID = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getMember(rs);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}



	@Override
	public int insertMember(Member member) {
		String sql = "insert into member(id, pwd, name, email, zip_num, address, phone)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getZipNum());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPhone());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	
	}

	@Override
	public int confirmId(String id) {
		int result = -1;
		String sql = "select * from member where id = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = 1;
				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return result;
	}
	
	

}
