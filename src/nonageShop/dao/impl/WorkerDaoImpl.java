package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nonageShop.dao.WorkerDao;
import nonageShop.ds.JdbcUtil;

public class WorkerDaoImpl implements WorkerDao {
	private static final WorkerDaoImpl instance = new WorkerDaoImpl();
	
	public WorkerDaoImpl() {}
	
	public static WorkerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int workerCheck(String id, String pwd) {
		String sql = "SELECT pwd FROM WORKER WHERE id = ? ";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					String dbPwd = rs.getString(1);
					if(dbPwd.equals(pwd)) {
						return 1; //일치
					}else {
						return 0; //불일치
					}
				}
				return -1; //아이디에러
			}
		} catch (SQLException e) {
			new CustomSQLException(e);
		}
		return 2; //디비에러
	}

}
