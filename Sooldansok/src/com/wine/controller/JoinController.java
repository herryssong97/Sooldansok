package com.wine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.JoinTelImpl;
import com.wine.join.JoinInsert;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("*.join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		JoinTelImpl scmd1 = null;
		boolean result = true;
		switch (c) {

		/// 회원가입
		case "/InsertJoin.join":
			scmd1 = new JoinInsert();

			try {
				result = scmd1.jointelImpl(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = false;
			}
			if (result) {
				str = "joinComplete.jsp";
			} else {
				str = "joinFail.jsp";
			}
			break;

		}// ===switch-end

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
