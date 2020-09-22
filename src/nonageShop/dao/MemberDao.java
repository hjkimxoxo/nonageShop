package nonageShop.dao;

import nonageShop.dto.Member;

public interface MemberDao {
	Member getMember(String id);
	
	int insertMember(Member member);
	
	int confirmId(String id);
}
