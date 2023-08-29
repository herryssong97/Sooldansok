package com.wine.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.NoticeTelImpl;

import telInfoDAO.NoticeDAO;
import telInfoVO.NoticeVO;

public class NoticeSearchOne implements NoticeTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		NoticeDAO ntdao = new NoticeDAO();

		String n_num = request.getParameter("n_num");

		NoticeVO tv = ntdao.getNotice(n_num);

		request.setAttribute("sn_title", n_num);
		request.setAttribute("stv", tv);
	}
}
