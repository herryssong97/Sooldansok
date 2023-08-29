package com.wine.basket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.BasketTelImpl;

import telInfoDAO.BasketDAO;

public class BasketInsert implements BasketTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BasketDAO bdao = new BasketDAO();

		String mem_id = request.getParameter("mem_id");
		int pd_id = Integer.parseInt(request.getParameter("pd_id"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));

		bdao.addToBasket(mem_id, pd_id, cnt);

	}
}
