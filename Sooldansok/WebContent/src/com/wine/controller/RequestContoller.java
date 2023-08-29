package com.wine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.RequestImpl;
import com.wine.request.RequestDelete;
import com.wine.request.RequestGetOne_mngr;
import com.wine.request.RequestInsert;
import com.wine.request.RequestMyAll;
import com.wine.request.RequestMyOne;
import com.wine.request.RequestUpdate;
import com.wine.request.RequestUpdate_mngr;

/**
 * Servlet implementation class RequestContoller
 */
@WebServlet("*.request")
public class RequestContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestContoller() {
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

		PrintWriter out = response.getWriter();

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		RequestImpl scmd1 = null;

		HttpSession ses1 = request.getSession();

		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) { // 세션 아이디/패스워드가 있다면

			switch (c) {

			case "/Request.request":
				str = "requestInsertForm.jsp";
				break;

			case "/RequestInsert.request":
				scmd1 = new RequestInsert();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "requestfilter.page?request_filter=MEM_ID&request_search="+sid+"&id="+sid; 
				break;
				
			case "/RequestMyAll.request":
				scmd1 = new RequestMyAll();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "requestMyAll.jsp";
				break;

			case "/RequestMyOne.request":
				scmd1 = new RequestMyOne();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "requestMyOne.jsp";
				break;

			case "/RequestUpdate.request":
				scmd1 = new RequestUpdate();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "index.jsp";
				break;

			case "/RequestDelete.request":
				scmd1 = new RequestDelete();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "index.jsp";
				break;

			case "/RequestUpdate_mngr.request":
				scmd1 = new RequestUpdate_mngr();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "request.page";
				break;

			case "/RequestGetOne_mngr.request":
				scmd1 = new RequestGetOne_mngr();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "requestDetail_mngr.jsp";
				break;

			} // switch-end

			RequestDispatcher rd1 = request.getRequestDispatcher(str); // str 에 적힌 파일로 이동
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
