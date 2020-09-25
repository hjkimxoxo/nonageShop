package nonageShop.service;

import java.util.ArrayList;

import nonageShop.dao.impl.QnaDaoImpl;
import nonageShop.dto.Qna;

public class QnaService {
	private QnaDaoImpl dao = QnaDaoImpl.getInstance();
	
	public ArrayList<Qna> listQna(){
		return dao.listQna();
	}
	
	public ArrayList<Qna> listQnaById(String id){
		return dao.listQnaById(id);
	}
	
	public Qna getQnaByNo(int no) {
		return dao.getQnaByNo(no);
	}
	
	public int insertQna(Qna qna) {
		return dao.insertQna(qna);
	}
}
