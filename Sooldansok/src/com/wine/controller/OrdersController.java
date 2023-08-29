package com.wine.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.OrdersTelImpl;
import com.wine.orders.OrderReviewInfo;
import com.wine.orders.OrdersInsert;
import com.wine.orders.OrdersOneInsert;
import com.wine.orders.OrdersSearch;
import com.wine.orders.OrdersUpdate;
import com.wine.product.Pd_InfoInsert;

@WebServlet("*.orders")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersController() {
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
		response.setContentType("text/html; charset=UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		OrdersTelImpl scmd1 = null;

		PrintWriter out = response.getWriter();

		HttpSession ses1 = request.getSession();

		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			switch (c) {

			case "/OrdersUpdate.orders":
				scmd1 = new OrdersUpdate();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "orders.page";
				break;

			case "/search.orders":
				scmd1 = new OrdersSearch();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "orders_mngr.jsp";
				break;

			case "/orderinsert.orders":
				scmd1 = new OrdersInsert();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "orderSubmit.jsp";
				break;

			case "/orderoneinsert.orders":
				scmd1 = new OrdersOneInsert();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "orderSubmit.jsp";
				break;

			case "/reviewinfo.orders":
				scmd1 = new OrderReviewInfo();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "myreviewInsert.jsp";
				break;

			}
			RequestDispatcher rd1 = request.getRequestDispatcher(str);
			rd1.forward(request, response);

		} else { // 세션 아이디/패스워드가 없다면 (로그인된 상태가 아니면)
			out.print("<script>alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동하겠습니까?');");
			out.println("window.location.href='login.jsp';");
			out.println("</script>");
		}

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
