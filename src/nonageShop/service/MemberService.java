package nonageShop.service;

import nonageShop.dao.impl.MemberDaoImpl;
import nonageShop.dto.Member;

public class MemberService {
	public  MemberDaoImpl dao = MemberDaoImpl.getInstance();
	
	public Member getMember(String id) {
		return dao.getMember(id);
	}
	
	public int insertMember(Member member) {
		return dao.insertMember(member);
	}
	
	public int confirmId(String id) {
		return dao.confirmId(id);
	}

}
