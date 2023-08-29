package com.wine.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ProductAllTelImpl;

import telInfoDAO.Pd_InfoDAO;

public class Pd_InfoInsert implements ProductAllTelImpl {
	public void productalltelImpl(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_InfoDAO td1 = new Pd_InfoDAO();

		String info_name = request.getParameter("info_name");
		String info_price = request.getParameter("info_price");
		String kind = request.getParameter("kind");
		String country = request.getParameter("country");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String alcohol = request.getParameter("alcohol");
		int made_year = Integer.parseInt(request.getParameter("made_year"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String img = request.getParameter("img");

		td1.insert_product(info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);

	}
}
