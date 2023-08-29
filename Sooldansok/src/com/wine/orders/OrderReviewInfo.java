package com.wine.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.OrdersTelImpl;

import telInfoDAO.OrdersDAO;
import telInfoVO.OrdersVO;

public class OrderReviewInfo implements OrdersTelImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		OrdersDAO dao = new OrdersDAO();

		String od_num = request.getParameter("OD_NUM");

		OrdersVO ov = dao.getOrderInfo(od_num);

		request.setAttribute("ov", ov);
	}

}
