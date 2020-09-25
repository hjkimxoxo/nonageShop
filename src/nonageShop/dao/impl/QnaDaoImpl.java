package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.QnaDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.Qna;

public class QnaDaoImpl implements QnaDao {
	private static final QnaDaoImpl instance = new QnaDaoImpl();

	public QnaDaoImpl() {
	}

	public static QnaDaoImpl getInstance() {
		return instance;
	}

	private Qna getQna(ResultSet rs) throws SQLException {
		Qna qna = new Qna();
		qna.setNo(rs.getInt("NO"));
		qna.setSubject(rs.getString("SUBJECT"));
		qna.setContent(rs.getString("CONTENT"));
		qna.setRep(rs.getString("REP"));
		qna.setId(rs.getString("ID"));
		qna.setRepYn(rs.getString("REP_YN"));
		qna.setWriteDate(rs.getTimestamp("WRITE_DATE"));

		return qna;
	}

	@Override
	public ArrayList<Qna> listQna() {
		String sql = "SELECT NO,SUBJECT,CONTENT,REP,ID,REP_YN,WRITE_DATE FROM QNA order by no desc";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Qna> list = new ArrayList<>();
				do {
					list.add(getQna(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public ArrayList<Qna> listQnaById(String id) {
		String sql = "SELECT NO,SUBJECT,CONTENT,REP,ID,REP_YN,WRITE_DATE FROM QNA WHERE ID = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<Qna> list = new ArrayList<>();
					do {
						list.add(getQna(rs));
					} while (rs.next());
					return list;
				}
			} catch (SQLException e) {
				throw new CustomSQLException(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	@Override
	public Qna getQnaByNo(int no) {
		String sql = "SELECT NO,SUBJECT,CONTENT,REP,ID,REP_YN,WRITE_DATE FROM QNA WHERE NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getQna(rs);
				}
			} catch (SQLException e) {
				throw new CustomSQLException(e);
			}
		} catch (SQLException e1) {
			throw new CustomSQLException(e1);
		}
		return null;

	}

	@Override
	public int insertQna(Qna qna) {
		String sql = "INSERT INTO qna (SUBJECT,CONTENT,REP,ID,WRITE_DATE) VALUES (?, ?, ?, ?, sysdate)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, qna.getSubject());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getRep());
			pstmt.setString(4, qna.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		
	}

}
