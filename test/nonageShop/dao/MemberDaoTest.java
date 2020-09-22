package nonageShop.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageShop.dao.impl.MemberDaoImpl;
import nonageShop.dao.impl.ProductDaoImpl;
import nonageShop.dto.Member;

public class MemberDaoTest {
	private static MemberDaoImpl dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		dao = MemberDaoImpl.getInstance();
	}
	@Test
	public void testGetMember() {
		System.out.println("getMember");
		Member m = dao.getMember("one");
		Assert.assertNotNull(m);
		System.out.println(m);
		
	}

	@Test
	public void testInsertMember() {
		System.out.println("insert");
		Member m = new Member("three", "1234", "김혜진", "test@jdkf", "41141", "대구", "010-5656-5659");
		int res = dao.insertMember(m);
		Assert.assertNotNull(res);
		
	}
	
	@Test
	public void testConfirmId() {
		System.out.println("confirm");
		String id = "";
		int res = dao.confirmId(id);
		Assert.assertNotNull(res);
		System.out.println(res);
	}
}
