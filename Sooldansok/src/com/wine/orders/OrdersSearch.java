package com.wine.orders;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.OrdersTelImpl;

import telInfoDAO.OrdersDAO;

import telInfoVO.OrdersVO;

public class OrdersSearch implements OrdersTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrdersDAO oddao = new OrdersDAO();

		String type = request.getParameter("type");
		String searchname = request.getParameter("searchname");

		ArrayList<OrdersVO> odlist = oddao.Search_Orders(type, searchname);

		request.setAttribute("odlist", odlist);

	}

}