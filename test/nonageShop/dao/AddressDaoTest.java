package nonageShop.dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import nonageShop.dao.impl.AddressDaoImpl;
import nonageShop.dto.Address;

public class AddressDaoTest {
	private static AddressDaoImpl dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		dao = AddressDaoImpl.getInstance(); 
	}
	
	@Test
	public void testSelectAddressByDong() {
		String dong = "동";
		ArrayList<Address> list = dao.selectAddressByDong(dong);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
