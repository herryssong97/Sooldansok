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

import com.wine.impl.QuestionTelImpl;
import com.wine.question.QuestionMyOne;
import com.wine.question.QuestionSearchOne;
import com.wine.question.QuestionAnswerUpdate;
import com.wine.question.QuestionDelete;
import com.wine.question.QuestionInsert;
import com.wine.question.QuestionMyAll;
import com.wine.question.QuestionUpdate;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("*.question")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
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

		QuestionTelImpl scmd1 = null;

		HttpSession ses1 = request.getSession();

		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			switch (c) {
			case "/Question.question":
				str = "questionInsertForm.jsp";
				break;

			case "/QuestionMyAll.question":
				scmd1 = new QuestionMyAll();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "questionMyAll.jsp";
				break;

			case "/QuestionInsert.question":
				scmd1 = new QuestionInsert();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "questionfilter.page?question_filter=MEM_ID&question_search="+sid+"&id="+sid;
				break;

			case "/QuestionMyOne.question":
				scmd1 = new QuestionMyOne();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "questionMyOne.jsp";
				break;

			case "/QuestionUpdate.question":
				scmd1 = new QuestionUpdate();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "index.jsp";
				break;

			case "/QuestionDelete.question":
				scmd1 = new QuestionDelete();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "question.page";
				break;

			case "/QuestionDelete_mngr.question":
				scmd1 = new QuestionDelete();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "question.page";
				break;

			case "/QuestionSearchOne.question":
				scmd1 = new QuestionSearchOne();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (sid.equals("admin")) {
					str = "questionDetail_mngr.jsp";
				} else {
					str = "questionDetail.jsp";
				}
				break;

			case "/answer_update.question":
				scmd1 = new QuestionAnswerUpdate();
				try {
					scmd1.tel(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				str = "QuestionSearchOne.question";
				break;
			}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
