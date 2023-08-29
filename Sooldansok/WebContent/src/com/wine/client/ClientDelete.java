package com.wine.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ClientTelImpl;

import telInfoDAO.ClientDAO;

public class ClientDelete implements ClientTelImpl {

	@Override
	public void clienttel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		ClientDAO ctdao = new ClientDAO();

		String id1 = request.getParameter("id1");

		ctdao.deleteClient(id1);
	}

}
