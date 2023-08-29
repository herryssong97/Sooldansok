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
 * Servlet implementation class phoneCheck
 */
@WebServlet("/phoneCheck")
public class phoneCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public phoneCheck() {
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

		String userPhone = request.getParameter("userPhone");
		TelInfoDAO dao = null;

		dao = new TelInfoDAO();

		PrintWriter out = response.getWriter();

		int phoneCheck = 0;
		try {
			if (userPhone.length() > 3) {

				phoneCheck = dao.checkPhone(userPhone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (phoneCheck == 0) {
			System.out.println("exist phone");

		} else if (phoneCheck == 1) {
			System.out.println("not found phone");

		}

		out.write(phoneCheck + "");

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
