package com.wine.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;

public class RequestUpdate_mngr implements RequestImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO reqdao = new Pd_RequestDAO();

		int req_num = Integer.parseInt(request.getParameter("req_num"));
		String req_state = request.getParameter("new_state");

		reqdao.update_request(req_num, req_state);

	}
}
