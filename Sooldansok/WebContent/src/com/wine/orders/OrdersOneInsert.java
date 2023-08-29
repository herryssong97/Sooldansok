package com.wine.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.OrdersTelImpl;
import telInfoDAO.OrdersDAO;
import telInfoDAO.Pd_InfoDAO;

public class OrdersOneInsert implements OrdersTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrdersDAO od1 = new OrdersDAO();
		Pd_InfoDAO pd1 = new Pd_InfoDAO();

		String mem_id = request.getParameter("mem_id");
		String delivery = request.getParameter("delivery");
		int pd_id = 1;
		int cnt = 1;

		String pdIdParam = request.getParameter("pd_id");
		String cntParam = request.getParameter("cnt");

		if (pdIdParam != null && !pdIdParam.isEmpty()) {
			pd_id = Integer.parseInt(pdIdParam);
		}

		if (cntParam != null && !cntParam.isEmpty()) {
			cnt = Integer.parseInt(cntParam);
		}

		od1.OrderInsert(mem_id, pd_id, cnt, delivery);
		pd1.ProductUpdate(cnt, pd_id);
	}
}