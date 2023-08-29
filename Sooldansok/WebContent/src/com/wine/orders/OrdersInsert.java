package com.wine.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.OrdersTelImpl;

import telInfoDAO.BasketDAO;
import telInfoDAO.OrdersDAO;
import telInfoDAO.Pd_InfoDAO;

public class OrdersInsert implements OrdersTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrdersDAO od1 = new OrdersDAO();
		BasketDAO bd1 = new BasketDAO();
		Pd_InfoDAO pd1 = new Pd_InfoDAO();

		String mem_id = request.getParameter("mem_id");
		String delivery = request.getParameter("delivery");
		int pd_id = Integer.parseInt(request.getParameter("pd_id"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		int bas_num = Integer.parseInt(request.getParameter("bas_num"));

		od1.OrderInsert(mem_id, pd_id, cnt, delivery);
		bd1.BasketDelete(bas_num);
		pd1.ProductUpdate(cnt, pd_id);

	}
}