package com.wine.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.ClientTelImpl;

import telInfoDAO.ClientDAO;
import telInfoVO.ClientVO;

public class ClientGetMyInfo implements ClientTelImpl {

	@Override
	public void clienttel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		ClientDAO ctdao = new ClientDAO();
		HttpSession ses1 = request.getSession();

		String id = (String) ses1.getAttribute("sid");

		ClientVO getone = ctdao.getOneInfo(id);

		request.setAttribute("getone", getone);

	}

}
