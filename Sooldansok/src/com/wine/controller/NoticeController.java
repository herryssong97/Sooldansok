package com.wine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.NoticeTelImpl;
import com.wine.notice.NoticeDelete;
import com.wine.notice.NoticeInsert;
import com.wine.notice.NoticeSearchOne;
import com.wine.notice.NoticeUpdate;

/**
 * Servlet implementation class Frontcontroller
 */
@WebServlet("*.notice")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		NoticeTelImpl scmd1 = null;
		HttpSession ses1 = request.getSession();
		String sid = (String) ses1.getAttribute("sid");

		switch (c) {

		case "/NoticeSearchOne.notice":
			scmd1 = new NoticeSearchOne();
			try {
				scmd1.tel(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (sid == null) {
				str = "noticeDetail.jsp";
			} else if (sid.equals("admin")) {
				str = "noticeDetail_mngr.jsp";
			} else {
				str = "noticeDetail.jsp";
			}
			break;

		case "/NoticeInsert.notice":
			scmd1 = new NoticeInsert();
			try {
				scmd1.tel(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "notice.page";
			break;

		case "/NoticeUpdate.notice":
			scmd1 = new NoticeUpdate();
			try {
				scmd1.tel(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "notice.page";
			break;

		case "/NoticeDelete.notice":
			scmd1 = new NoticeDelete();
			try {
				scmd1.tel(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "notice.page";
			break;

		}

		RequestDispatcher rd1 = request.getRequestDispatcher(str);
		rd1.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
