package com.wine.basket;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.BasketTelImpl;

import telInfoDAO.BasketDAO;
import telInfoDAO.ClientDAO;
import telInfoVO.BasketVO;
import telInfoVO.ClientVO;

public class BasketGetAll implements BasketTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BasketDAO bd1 = new BasketDAO();
		ClientDAO cd1 = new ClientDAO();
		String mem_id2 = request.getParameter("mem_id");

		ArrayList<BasketVO> alist1 = bd1.getAllBasket(mem_id2);
		ClientVO clist1 = cd1.getOneInfo(mem_id2);

		request.setAttribute("alist1", alist1);
		request.setAttribute("clist1", clist1);
	}

}
