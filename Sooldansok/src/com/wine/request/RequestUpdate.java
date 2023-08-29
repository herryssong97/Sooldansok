package com.wine.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;

public class RequestUpdate implements RequestImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO rd1 = null;

		String req_name = request.getParameter("req_name");
		String req_country = request.getParameter("req_country");
		int req_made_year = Integer.parseInt(request.getParameter("req_made_year"));
		String req_comment = request.getParameter("req_comment");
		int req_num = Integer.parseInt(request.getParameter("req_num"));

		try {
			rd1 = new Pd_RequestDAO();
			rd1.updateRequest(req_name, req_country, req_made_year, req_comment, req_num);
		} catch (Exception e) {

		}
	}
}
