package com.wine.client;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.PageTelImpl;

import telInfoDAO.ClientDAO;
import telInfoVO.ClientVO;
import telInfoVO.PageVO;

public class ClientPage implements PageTelImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		ClientDAO dao = null;
		try {
			dao = new ClientDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int totalcount = dao.getTotalCount();
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

		if (totalcount == 0)
			totalcount = 1;

		PageVO paging = new PageVO();
		paging.setPageNo(page); // get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
		paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
		paging.setTotalCount(totalcount);

		page = (page - 1) * 10 + 1; // select해오는 기준을 구한다.
		int page2 = page + 9;
		ArrayList<ClientVO> clist = dao.getList(page, page2);

		request.setAttribute("clientsum", totalcount);
		request.setAttribute("clist", clist);
		request.setAttribute("paging", paging);
	}
}