package USER;

import telInfoDAO.TelInfoDAO;

public class UserMgr {

	TelInfoDAO dao = new TelInfoDAO();

	public String id_search2(String name, String email) { // 이름,이메일로 아이디찾기
		return dao.id_search2(name, email);
	}

	public String pw_search2(String id, String phone) { // 아이디,연락처로 비번찾기
		return dao.pw_search2(id, phone);
	}

	/*
	 * public String newPassword(String pw, String id, String phone) throws
	 * SQLException { return dao.new_password(pw, id, phone); }
	 */

}
