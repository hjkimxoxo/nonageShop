package nonageShop.dao;

import java.util.ArrayList;

import nonageShop.dto.Qna;

public interface QnaDao {
	ArrayList<Qna> listQna();
	
	ArrayList<Qna> listQnaById(String id);
	
	Qna getQnaByNo(int no);
	
	int insertQna(Qna qna);

	

}
