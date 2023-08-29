package com.wine.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ClientTelImpl;
import com.wine.security.PwEncoding;

import telInfoDAO.ClientDAO;

public class ClientUpdate implements ClientTelImpl {

	@Override
	public void clienttel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ClientDAO cd1 = null;

		String id = request.getParameter("id");
		String pw = PwEncoding.sha256(request.getParameter("pw")); // 암호화
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		try {
			cd1 = new ClientDAO();
			cd1.updateClient(id, pw, name, address, email, phone);
		} catch (Exception e) {

		}

	}

}