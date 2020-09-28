package nonageShop.service;

import nonageShop.dao.WorkerDao;
import nonageShop.dao.impl.WorkerDaoImpl;

public class WorkerService {
	private WorkerDao dao = WorkerDaoImpl.getInstance();
	
	public int adminCheck(String id, String pwd) {
		return dao.workerCheck(id, pwd);
	}

}
