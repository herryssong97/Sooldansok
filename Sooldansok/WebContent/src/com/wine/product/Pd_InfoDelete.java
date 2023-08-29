package com.wine.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ProductAllTelImpl;

import telInfoDAO.Pd_InfoDAO;

public class Pd_InfoDelete implements ProductAllTelImpl {
	public void productalltelImpl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_InfoDAO td1 = new Pd_InfoDAO();

		int info_num = Integer.parseInt(request.getParameter("info_num"));

		td1.delete_pd_info(info_num);
	}
}
