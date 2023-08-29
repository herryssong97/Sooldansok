package com.wine.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.NoticeTelImpl;

import telInfoDAO.NoticeDAO;

public class NoticeDelete implements NoticeTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		NoticeDAO ntdao = new NoticeDAO();

		int n_num = Integer.parseInt(request.getParameter("n_num"));

		ntdao.delete_notice(n_num);
	}
}
