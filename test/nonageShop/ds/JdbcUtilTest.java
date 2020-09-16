package nonageShop.ds;


import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;


public class JdbcUtilTest {

	@Test
	public void testGetConnection() {
		System.out.println("TestGetConnection");
		Connection con = JdbcUtil.getConnection();
		Assert.assertNotNull(con);
		System.out.println(con);
	}

}
