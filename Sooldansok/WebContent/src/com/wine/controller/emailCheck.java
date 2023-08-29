package com.wine.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import telInfoDAO.TelInfoDAO;

/**
 * Servlet implementation class emailCheck
 */
@WebServlet("/emailCheck")
public class emailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public emailCheck() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String userEmail = request.getParameter("userEmail");
		TelInfoDAO dao = null;

		dao = new TelInfoDAO();

		PrintWriter out = response.getWriter();

		int emailCheck = 0;
		try {
			if (userEmail.length() > 3) {

				emailCheck = dao.checkEmail(userEmail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (emailCheck == 0) {
			System.out.println("exist email");

		} else if (emailCheck == 1) {
			System.out.println("not found email");

		}

		out.write(emailCheck + "");

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
