package com.wine.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.NoticeTelImpl;

import telInfoDAO.NoticeDAO;

public class NoticeUpdate implements NoticeTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		NoticeDAO ntdao = new NoticeDAO();

		String n_title = request.getParameter("title");
		String n_comment = request.getParameter("textarea1");
		int n_num = Integer.parseInt(request.getParameter("n_num"));

		ntdao.update_notice(n_title, n_comment, n_num);

	}
}
