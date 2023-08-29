package com.wine.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;
import telInfoVO.Pd_RequestVO;

public class RequestMyOne implements RequestImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO rd1 = null;
		Pd_RequestVO rv1 = null;

		HttpSession ses1 = request.getSession();

		int req_num2 = Integer.parseInt(request.getParameter("req_num"));
		String mem_id2 = (String) ses1.getAttribute("sid");

		try {
			rd1 = new Pd_RequestDAO();
			rv1 = rd1.requestMyOne(req_num2, mem_id2);
		} catch (Exception e) {

		}
		request.setAttribute("rmo", rv1);

	}
}
