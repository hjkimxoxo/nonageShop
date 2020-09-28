package nonageShop.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import nonageShop.dao.impl.WorkerDaoImpl;

public class WorkerDaoTest {
	private WorkerDaoImpl dao = WorkerDaoImpl.getInstance();
	@Test
	public void test() {
		
		int res = dao.workerCheck("admin", "1234");
		System.out.println(res);
	}

}
