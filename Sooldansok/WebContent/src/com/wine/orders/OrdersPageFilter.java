package com.wine.orders;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.PageTelImpl;

import telInfoDAO.OrdersDAO;
import telInfoVO.OrdersVO;
import telInfoVO.PageVO;

public class OrdersPageFilter implements PageTelImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String filter = request.getParameter("orders_filter");
		String search = request.getParameter("orders_search");
		String id = request.getParameter("id");

		OrdersDAO dao = null;

		int filtertotalcount = 0;
		try {
			dao = new OrdersDAO();

			filtertotalcount = dao.getFilterTotal(filter, id, search);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (filtertotalcount == 0)
			filtertotalcount = 1;

		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		PageVO paging = new PageVO();
		paging.setPageNo(page); // get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		paging.setPageSize(5); // 한페이지에 불러낼 게시물의 개수 지정
		paging.setTotalCount(filtertotalcount);

		page = (page - 1) * 5 + 1; // select해오는 기준을 구한다.
		int page2 = page + 4;

		ArrayList<OrdersVO> od_rv = dao.getReview(id);

		ArrayList<OrdersVO> odlist = dao.getFilterList(page, page2, filter, id, search);

		request.setAttribute("orderssum", filtertotalcount);
		request.setAttribute("odlist", odlist);
		request.setAttribute("paging", paging);
		request.setAttribute("od_rv", od_rv);
	}

}
