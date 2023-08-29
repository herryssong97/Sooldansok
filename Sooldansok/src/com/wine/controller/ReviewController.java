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

import com.wine.impl.ReviewImpl;
import com.wine.review.ReviewDelete;
import com.wine.review.ReviewInsert;
import com.wine.review.ReviewMyAll;
import com.wine.review.ReviewMyOne;
import com.wine.review.ReviewUpdate;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("*.review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewController() {
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

		ReviewImpl scmd1 = null;

		HttpSession ses1 = request.getSession();

		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			switch (c) {
			case "/ReviewMyAll.review":
				scmd1 = new ReviewMyAll();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "reviewMyAll.jsp";
				break;

			case "/ReviewMyOne.review":
				scmd1 = new ReviewMyOne();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "myreviewDetail.jsp";
				break;

			case "/ReviewMyOne1.review":
				scmd1 = new ReviewMyOne();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "myreviewUpdate.jsp";
				break;

			case "/ReviewUpdate.review":
				scmd1 = new ReviewUpdate();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "reviewfilter.page?review_filter=MEM_ID&review_search=" + sid + "&id=" + sid;
				break;

			case "/ReviewDelete.review":
				scmd1 = new ReviewDelete();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "reviewfilter.page?review_filter=MEM_ID&review_search=" + sid + "&id=" + sid;
				break;

			case "/ReviewInsert.review":
				scmd1 = new ReviewInsert();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "/reviewfilter.page?review_filter=MEM_ID&review_search=" + sid + "&id=" + sid;
				break;

			} // switch-end

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
