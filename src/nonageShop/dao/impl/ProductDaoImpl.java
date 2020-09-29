package nonageShop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import nonageShop.dao.ProductDao;
import nonageShop.ds.JdbcUtil;
import nonageShop.dto.OrderDetail;
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

	
	

//관리자모드////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public int totalRecord(String productName) {
		String sql = "SELECT COUNT(*) FROM PRODUCT  WHERE name LIKE ?";
		int total_page = 0;
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				
				if(productName.equals("")) {
					pstmt.setString(1, "%%");
				}else {
					pstmt.setString(1, "%" + productName + "%");
				}
				
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					total_page = rs.getInt(1); //레코드개수
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return total_page;
	}

	
	private static int VIEW_ROWS = 5; // 페이지의 개수
	private static int COUNTS = 5; // 한 페이지에 나타낼 상품의 개수

	@Override
	public String pageNumber(int tpage, String name) {
		/*String str = "";
		  
		  int total_pages = totalRecord(name);
		  int page_count = (total_pages / COUNTS) + 1;
		  System.out.println("total: " + total_pages + ", page_count: " + page_count);
		  
		  if (total_pages % COUNTS == 0) {
		     page_count--;
		  }
		  
		  if (tpage < 1) {
		     tpage = 1;
		  }
		  
		  
		  int start_page = tpage - (tpage % VIEW_ROWS) + 1;
		  int end_page = start_page + (COUNTS - 1);
		  
		  
		  System.out.println(start_page + ", " + end_page);
		  if (end_page > page_count) {
		     end_page = page_count;
		  }
		  
		  if (start_page > VIEW_ROWS) {
		     str += "<a href='adminProductList.do?tpage=1&key="+ name +"'>&lt;&lt;</a>&nbsp;&nbsp;";
		     str += "<a href='adminProductList.do?tpage="+ (start_page - 1) +"&key=<%=product_name%>'>&lt;</a>&nbsp;&nbsp;";
		  }
		  
		  for (int i = start_page; i <= end_page; i++) {
		     if (i == tpage) {
		        str += "<font color=red>["+ i +"]&nbsp;&nbsp;</font>";
		     } else {
		        str += "<a href='adminProductList.do?tpage="+ i +"&key="+ name +"'>["+ i +"]</a>&nbsp;&nbsp;";
		     }
		  }
		  
		  if (page_count > end_page) {
		     str += "<a href='adminProductList.do?tpage="+ (end_page + 1) +"&key="+ name +"'>&gt;</a>&nbsp;&nbsp;";
		     str += "<a href='adminProductList.do?tpage="+ page_count +"&key="+ name +"'>&gt;&gt;</a>&nbsp;&nbsp;";
		  }
		  
		  
		  return str;
		*/
		StringBuilder sb = new StringBuilder("");
		
		int total_pages = totalRecord(name);
		int page_count = (total_pages / COUNTS) + 1; 
		
		if(total_pages % COUNTS == 0) {
			page_count--;
		}
		
		if(tpage < 1) {
			tpage = 1;
		}
		
		int start_page = tpage - (tpage % VIEW_ROWS) + 1;
		int end_page = start_page + (COUNTS - 1);
		
		if (end_page > page_count) {
			end_page = page_count;
		}
		
		if (start_page > VIEW_ROWS) {
			sb.append("<a href='adminProductList.do?page=1&key=" + name + "'>&lt;&lt;</a>&nbsp;&nbsp;");
			sb.append("<a href='adminProductList.do?tpage=" + (start_page - 1));
			sb.append("&key={$product_name}'>&lt;</a>&nbsp;&nbsp;");
		}
		
		for(int i = start_page; i <= end_page; i++) {
			if(i == tpage) {
				sb.append("<font color=red>[" + i + "]</a>&nbsp&nbsp;");
			} else {
				sb.append("<a href='adminProductList.do?tpage=" + i + "&key=" + name + "'>[" + i + "]</a>&nbsp&nbsp;");
			}
		}
		
		if(page_count > end_page) {
			sb.append("<a href='adminProductList.do?tpage=" + (end_page + 1) + "&key=" + name +"'> &g; </a>&nbsp;&nbsp;");
			sb.append("<a href='adminProductList.do?tpage=" + page_count + "&key=" + name +"'> &g; </a>&nbsp;&nbsp;");
		}
		
		return sb.toString();
	}

	@Override
	public ArrayList<Product> listProduct(int tPage, String productName) {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String sql = "SELECT NO,NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,DEL_YN,BEST_YN,REG_DATE "
				+ "FROM product WHERE name LIKE '%' || ? || '%' ORDER BY NO DESC";
		
		System.out.println(tPage);
		int absolutePage = 1;
		absolutePage = (tPage - 1) * COUNTS + 1;
		
		System.out.println(absolutePage);
		
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);){
				
				if(productName.equals("")) {
					pstmt.setString(1, "%");
				}else {
					pstmt.setString(1, productName);
				}
				
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						rs.absolute(absolutePage);
						int count = 0;
						while(count < COUNTS) {
							productList.add(getProduct(rs));
							if(rs.isLast()) {
								break;
							}
							rs.next();
							count++;
						}
					}
				}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return productList;
	}
	

	@Override
	public int insertProduct(Product product) {
		String sql = "insert into product(name, kind, price, saleprice, margin, content, image) "
				+ "values(?,?,?,?,?,?,?)";
		 try (Connection con = JdbcUtil.getConnection();
	        		PreparedStatement pstmt = con.prepareStatement(sql);){
			 	pstmt.setString(1, product.getName());
			 	pstmt.setString(2, product.getKind());
			 	pstmt.setInt(3, product.getPrice());
			 	pstmt.setInt(4, product.getSalePrice());
			 	pstmt.setInt(5, product.getMargin());
			 	pstmt.setString(6, product.getContent());
			 	pstmt.setString(7, product.getImage());
			 	
			 	return pstmt.executeUpdate();
		 } catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		
	}

	@Override
	public int updateProduct(Product product) {
		String sql = "UPDATE PRODUCT SET NAME = ?, KIND =?, PRICE=?, SALEPRICE = ?,"
				+ " MARGIN = ?, CONTENT = ?, IMAGE = ? WHERE NO = ?";
		 try (Connection con = JdbcUtil.getConnection();
	        		PreparedStatement pstmt = con.prepareStatement(sql);){
			 	pstmt.setString(1, product.getName());
			 	pstmt.setString(2, product.getKind());
			 	pstmt.setInt(3, product.getPrice());
			 	pstmt.setInt(4, product.getSalePrice());
			 	pstmt.setInt(5, product.getMargin());
			 	pstmt.setString(6, product.getContent());
			 	pstmt.setString(7, product.getImage());
			 	pstmt.setInt(8, product.getNo());
			 	
			 	return pstmt.executeUpdate();
		 } catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public int deleteProduct(Product product) {
		String sql = "delete from product where no = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, product.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}
}
