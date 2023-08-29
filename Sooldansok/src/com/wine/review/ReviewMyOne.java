package com.wine.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ReviewImpl;

import telInfoDAO.OrdersDAO;
import telInfoDAO.ReviewDAO;
import telInfoVO.OrdersVO;
import telInfoVO.ReviewVO;

public class ReviewMyOne implements ReviewImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		OrdersDAO od1 = new OrdersDAO();
		ReviewDAO rd1 = new ReviewDAO();

		int rev_num = Integer.parseInt(request.getParameter("REV_NUM"));
		String od_num = request.getParameter("OD_NUM");

		ReviewVO rv1 = rd1.reviewMyOne(rev_num);
		OrdersVO ov1 = od1.getOrderInfo(od_num);

		request.setAttribute("rv", rv1);
		request.setAttribute("ov", ov1);
	}
}
