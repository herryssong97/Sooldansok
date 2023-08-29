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

import com.wine.client.ClientDelete;
import com.wine.client.ClientGetMyInfo;
import com.wine.client.ClientGetOne;
import com.wine.client.ClientOrder;
import com.wine.client.ClientUpdate;
import com.wine.client.ClientUpdate_mngr;
import com.wine.impl.ClientTelImpl;

/**
 * Servlet implementation class ClientController
 */
@WebServlet("*.client")
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientController() {
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
		response.setContentType("text/html; charset=UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		ClientTelImpl ctim = null;
		PrintWriter out = response.getWriter();

		HttpSession ses1 = request.getSession();
		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			if (sid.equals("admin")) {

				switch (c) {

				case "/clientgetone.client":
					ctim = new ClientGetOne();
					System.out.println("컨트롤러가 받은 id : " + request.getParameter("id"));
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "clientGetOne_mngr.jsp";

					break;

				case "/clientupdate.client":
					ctim = new ClientUpdate_mngr();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "/client.page";

					break;

				case "/clientdelete.client":
					ctim = new ClientDelete();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "/client.page";

					break;

				}

			} else {

				switch (c) {
				case "/getmyinfo.client":
					ctim = new ClientGetMyInfo();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "/myPage.jsp";

					break;

				// 회원탈퇴 (사용자 기준)
				case "/mypagedelete.client":
					ctim = new ClientDelete();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					str = "/logout.jsp";

					break;

				case "/mypageupdate.client":
					ctim = new ClientUpdate();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "/getmyinfo.client";

					break;

				case "/orderclient.client":
					ctim = new ClientOrder();
					try {
						ctim.clienttel(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					str = "/orderOnePage.jsp";

					break;
				}

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