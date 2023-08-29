package com.wine.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.NoticeTelImpl;

import telInfoDAO.NoticeDAO;

public class NoticeInsert implements NoticeTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		NoticeDAO ntdao = new NoticeDAO();

		String title = request.getParameter("title");
		String comment = request.getParameter("textarea1");

		ntdao.insert_notice(title, comment);

	}
}
