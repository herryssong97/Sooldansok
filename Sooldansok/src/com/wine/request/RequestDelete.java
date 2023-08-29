package com.wine.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;

public class RequestDelete implements RequestImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO rd1 = null;
		int req_num = Integer.parseInt(request.getParameter("req_num"));

		try {
			rd1 = new Pd_RequestDAO();
			rd1.deleteRequest(req_num);
		} catch (NumberFormatException e) {

		} catch (Exception e) {

		}
	}

}
