package com.wine.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;
import telInfoVO.Pd_RequestVO;

public class RequestGetOne_mngr implements RequestImpl {

	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO rd1 = null;
		Pd_RequestVO rv1 = null;

		String req_num = request.getParameter("req_num");

		try {
			rd1 = new Pd_RequestDAO();
			rv1 = rd1.requestGetOne(req_num);
		} catch (Exception e) {

		}
		request.setAttribute("rv", rv1);
	}
}