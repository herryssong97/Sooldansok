package com.wine.product;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.PageTelImpl;

import telInfoDAO.Pd_InfoDAO;
import telInfoVO.PageVO;
import telInfoVO.Pd_InfoVO;

public class ProductSearchFrancePage implements PageTelImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String search = request.getParameter("pd_name");

		Pd_InfoDAO pddao = null;
		try {
			pddao = new Pd_InfoDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int totalcount = pddao.getSearchFranceCount(search);
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		if (totalcount == 0)
			totalcount = 1;

		PageVO paging = new PageVO();
		paging.setPageNo(page); // get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		paging.setPageSize(8); // 한페이지에 불러낼 게시물의 개수 지정
		paging.setTotalCount(totalcount);

		page = (page - 1) * 8 + 1; // select해오는 기준을 구한다.
		int page2 = page + 7;
		ArrayList<Pd_InfoVO> frArr = pddao.getSearchFrance(page, page2, search);

		request.setAttribute("productsum", totalcount);
		request.setAttribute("frArr", frArr);
		request.setAttribute("paging", paging);
	}
}