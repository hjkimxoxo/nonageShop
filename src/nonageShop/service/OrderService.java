package nonageShop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageShop.dao.CartDao;
import nonageShop.dao.impl.CartDaoImpl;
import nonageShop.dao.impl.OrderDaoImpl;
import nonageShop.ds.JdbcUtil;
import nonageShop.ds.JndiDS;
import nonageShop.dto.Order;
import nonageShop.dto.OrderDetail;

public class OrderService {
	private OrderDaoImpl oDao = OrderDaoImpl.getInstance();
	private CartDao cDao = CartDaoImpl.getInstance(); 

	
	public Order listByOrderById(String id, String result, int no){
		return oDao.listByOrderById(id, result, no);
	}
	
	public ArrayList<Integer> selectNoOrdering(String id){
		return oDao.selectNoOrdering(id);
	}
	
	public ArrayList<OrderDetail> listOrderDetailById(String id){
		return oDao.listOrderDetailById(id);
	}
	
	public int addOrderAndDetail(Order order) {
		String orderSql = "INSERT INTO ORDERS(ID, ORDER_DATE) VALUES(?, SYSDATE)";
		String detailSql = "INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY, RESULT_YN) VALUES(?, ?, ?, '1')";
		Connection con = null;
        PreparedStatement orderPstmt = null;
        PreparedStatement detailPstmt = null;
        int ordersMaxNo = 0;
       
        try {
            con = JdbcUtil.getConnection();
            con.setAutoCommit(false);
            
            orderPstmt = con.prepareStatement(orderSql);
            orderPstmt.setString(1, order.getMember().getId());
            orderPstmt.executeUpdate();
            
            detailPstmt = con.prepareStatement(detailSql);
            ordersMaxNo = oDao.selectMaxOrderNo();
            
            ArrayList<OrderDetail> details = order.getDetails();
            order.setDetails(details);
            
            for(OrderDetail od : order.getDetails()) {
                detailPstmt.setInt(1, ordersMaxNo);
                detailPstmt.setInt(2, od.getCart().getProduct().getNo());
                detailPstmt.setInt(3, od.getCart().getQuantity());
                detailPstmt.executeUpdate();
                
                cDao.updateCartResult(od.getCart());
            }
            
            con.commit();
        } catch (SQLException e) {
            rollbackUtil(con, e);
        } finally {
            closeUtil(con, orderPstmt, detailPstmt);
        }
		return ordersMaxNo;
	}

	
	private void rollbackUtil(Connection con, SQLException e) {
        try {
            System.out.println("roll back");
            con.rollback();
            throw new RuntimeException(e);
        } catch (SQLException ex) {
        }
    }

    private void closeUtil(Connection con, PreparedStatement dPstmt, PreparedStatement tPstmt) {
        try {
            if (dPstmt != null) {
                dPstmt.close();
            }
            if (tPstmt != null) {
                tPstmt.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
        }
    }
}

