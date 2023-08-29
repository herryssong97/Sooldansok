package com.wine.product;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.PageTelImpl;

import telInfoDAO.Pd_InfoDAO;
import telInfoVO.PageVO;
import telInfoVO.Pd_InfoVO;

public class Pd_InfoPageFilter implements PageTelImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String filter = request.getParameter("product_filter");
		String search = request.getParameter("product_search");

		Pd_InfoDAO dao = null;

		int filtertotalcount = 0;
		try {
			dao = new Pd_InfoDAO();

			filtertotalcount = dao.getFilterTotal(filter, search);
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
		paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
		paging.setTotalCount(filtertotalcount);

		page = (page - 1) * 10 + 1; // select해오는 기준을 구한다.
		int page2 = page + 9;

		ArrayList<Pd_InfoVO> pdlist = dao.getFilterList(page, page2, filter, search);

		request.setAttribute("productsum", filtertotalcount);
		request.setAttribute("pdlist", pdlist);
		request.setAttribute("paging", paging);
	}
}