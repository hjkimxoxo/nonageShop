package nonageShop.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import nonageShop.dao.impl.QnaDaoImpl;
import nonageShop.dto.Qna;

public class QnaDaoTest {
	private static QnaDaoImpl dao = QnaDaoImpl.getInstance();
	
	@Test
	public void testListQna() {
		System.out.println("list");
		ArrayList<Qna> list = dao.listQna();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void testlistQnaById() {
		System.out.println("selectId");
		ArrayList<Qna> list = dao.listQnaById("one");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void testGetQnaByNo() {
		System.out.println("selectNo");
		Qna qna = dao.getQnaByNo(2);
		System.out.println(qna);
	}

	//@Test
	public void testInsertQna() {
		System.out.println("insert");
		Qna qna = new Qna("제목제머ㅗㄱ", "내용내용", "ekqqks", "one");
		int res = dao.insertQna(qna);
		Assert.assertEquals(1, res);
		System.out.println("~~확인~~");
		ArrayList<Qna> list = dao.listQnaById("one");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

}
