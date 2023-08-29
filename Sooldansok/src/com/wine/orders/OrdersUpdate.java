package com.wine.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.OrdersTelImpl;

import telInfoDAO.OrdersDAO;

public class OrdersUpdate implements OrdersTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrdersDAO oddao = new OrdersDAO();

		int od_num = Integer.parseInt(request.getParameter("od_num"));
		String delivery = request.getParameter("new_delivery");

		oddao.update_orders(od_num, delivery);

	}
}
