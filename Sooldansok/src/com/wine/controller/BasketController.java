package com.wine.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.basket.BasketDeleteSelected;
import com.wine.basket.BasketGetAll;
import com.wine.basket.BasketInsert;
import com.wine.impl.BasketTelImpl;
import com.wine.impl.ProductAllTelImpl;

import telInfoDAO.BasketDAO;

/**
 * Servlet implementation class Frontcontroller
 */
@WebServlet("*.basket")
public class BasketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BasketController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		BasketGetAll scmd1 = null;
		BasketDeleteSelected scmd2 = null;
		BasketTelImpl scmd3 = null;

		PrintWriter out = response.getWriter();

		HttpSession ses1 = request.getSession();

		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			switch (c) {
			case "/BasketGetAll.basket":
				scmd1 = new BasketGetAll();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "basketIn.jsp";
				break;

			case "/BasketDeleteSelected.basket":
				scmd2 = new BasketDeleteSelected();
				try {
					scmd2.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "BasketGetAll.basket";
				break;

			case "/BasketInsert.basket":
				scmd3 = new BasketInsert();
				try {
					scmd3.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "BasketGetAll.basket";
				break;

			} // switch-end
			RequestDispatcher rd1 = request.getRequestDispatcher(str);
			rd1.forward(request, response);

		} else { // 세션 아이디/패스워드가 없다면 (로그인된 상태가 아니면)
			out.print("<script> var result = confirm('로그인이 필요한 서비스입니다. 로그인 페이지로 이동하겠습니까?');");
			out.println("if (result == true) {");
			out.println("window.location.href='login.jsp';");
			out.println("} else {");
			out.println("window.location.href='index.jsp';");
			out.println("} </script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());

		if ("/BasketAddTo.basket".equals(c)) {
			String mem_id = request.getParameter("mem_id");
			int pd_id = Integer.parseInt(request.getParameter("pd_id"));
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			String message;

			try {
				BasketDAO basketDAO = new BasketDAO();
				int rows_affected = basketDAO.addToBasket(mem_id, pd_id, cnt);
				if (rows_affected > 0) {
					message = "success";
				} else {
					message = "fail";
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				message = "fail";
			} catch (SQLException e) {
				e.printStackTrace();
				message = "fail";
			}

			response.setContentType("text/plain");
			try {
				response.getWriter().write(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			doGet(request, response);
		}

	}

}