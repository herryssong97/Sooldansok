package com.wine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.client.ClientPage;
import com.wine.client.ClientPageFilter;
import com.wine.impl.PageTelImpl;
import com.wine.notice.NoticePage;
import com.wine.notice.NoticePageFilter;
import com.wine.orders.MyOrdersPageFilter;
import com.wine.orders.OrdersPage;
import com.wine.orders.OrdersPageFilter;
import com.wine.orders.OrdersPageFilter_mngr;
import com.wine.product.Pd_InfoPage;
import com.wine.product.Pd_InfoPageFilter;
import com.wine.product.ProductAllPage;
import com.wine.product.ProductChilePage;
import com.wine.product.ProductFrancePage;
import com.wine.product.ProductItalyPage;
import com.wine.product.ProductSearchAllPage;
import com.wine.product.ProductSearchChilePage;
import com.wine.product.ProductSearchFrancePage;
import com.wine.product.ProductSearchItalyPage;
import com.wine.product.ProductSearchSpainPage;
import com.wine.product.ProductSpainPage;
import com.wine.question.QuestionPage;
import com.wine.question.QuestionPageFilter;
import com.wine.request.RequestPage;
import com.wine.request.RequestPageFilter;
import com.wine.review.ReviewPageFilter;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.page")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageController() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		String requestURI = request.getRequestURI();
		String[] URIList = requestURI.split("/");
		String cmdURI = URIList[URIList.length - 1];

		String str = null;
		PageTelImpl action = null;

		HttpSession ses1 = request.getSession();
		String sid = (String) ses1.getAttribute("sid");
		String spw = (String) ses1.getAttribute("spw");

		if (sid != null && spw != null) {

			if (cmdURI.equals("product.page")) {
				action = new Pd_InfoPage();
				action.execute(request, response);

				str = "product_mngr.jsp";
			}

			else if (cmdURI.equals("productfilter.page")) {
				action = new Pd_InfoPageFilter();
				action.execute(request, response);

				str = "product_mngr.jsp";
			}

			else if (cmdURI.equals("client.page")) {
				action = new ClientPage();
				action.execute(request, response);

				str = "client_mngr.jsp";
			}

			else if (cmdURI.equals("clientfilter.page")) {
				action = new ClientPageFilter();
				action.execute(request, response);

				str = "client_mngr.jsp";
			}

			else if (cmdURI.equals("question.page")) {
				action = new QuestionPage();
				action.execute(request, response);
				if (sid.equals("admin")) {
					str = "questionBoard_mngr.jsp";
				} else {
					str = "mypost.jsp";
				}

			}

			else if (cmdURI.equals("questionfilter.page")) {
				action = new QuestionPageFilter();
				action.execute(request, response);
				if (sid.equals("admin")) {
					str = "questionBoard_mngr.jsp";
				} else {
					str = "mypost.jsp";
				}
			}

			else if (cmdURI.equals("notice.page")) {
				action = new NoticePage();
				action.execute(request, response);
				if (sid.equals("admin")) {
					str = "noticeBoard_mngr.jsp";
				} else {
					str = "noticeBoard.jsp";
				}

			}

			else if (cmdURI.equals("noticefilter.page")) {
				action = new NoticePageFilter();
				action.execute(request, response);
				if (sid.equals("admin")) {
					str = "noticeBoard_mngr.jsp";
				} else {
					str = "noticeBoard.jsp";
				}
			}

			else if (cmdURI.equals("orders.page")) {
				action = new OrdersPage();
				action.execute(request, response);

				str = "orders_mngr.jsp";
			}

			else if (cmdURI.equals("ordersfilter.page")) {
				action = new OrdersPageFilter();
				action.execute(request, response);
				if (sid == "admin") {
					str = "orders_mngr.jsp";
				} else {
					str = "myorders.jsp";
				}
			}

			else if (cmdURI.equals("ordersfilter_mngr.page")) {
				action = new OrdersPageFilter_mngr();
				action.execute(request, response);
				if (sid == "admin") {
					str = "orders_mngr.jsp";
				} else {
					str = "myorders.jsp";
				}
			}

			else if (cmdURI.equals("reviewfilter.page")) {
				action = new ReviewPageFilter();
				action.execute(request, response);
				if (sid == "admin") {
					str = "";
				} else {
					str = "myreview.jsp";
				}
			}

			else if (cmdURI.equals("request.page")) {
				action = new RequestPage();
				action.execute(request, response);
				if (sid == "admin") {
					str = "requestBoard_mngr.jsp";
				} else {
					str = "";
				}
			}

			else if (cmdURI.equals("requestfilter.page")) {
				action = new RequestPageFilter();
				action.execute(request, response);
				if (sid == "admin") {
					str = "requestBoard_mngr.jsp";
				} else {
					str = "myrequest.jsp";
				}
			} else if (cmdURI.equals("productall.page")) {
				action = new ProductAllPage();
				action.execute(request, response);

				str = "productAll.jsp";
			}

			else if (cmdURI.equals("productsearchall.page")) {
				action = new ProductSearchAllPage();
				action.execute(request, response);

				str = "productAll.jsp";
			}

			else if (cmdURI.equals("france.page")) {
				action = new ProductFrancePage();
				action.execute(request, response);

				str = "france.jsp";
			}

			else if (cmdURI.equals("chile.page")) {
				action = new ProductChilePage();
				action.execute(request, response);

				str = "chile.jsp";
			}

			else if (cmdURI.equals("italy.page")) {
				action = new ProductItalyPage();
				action.execute(request, response);

				str = "italy.jsp";
			}

			else if (cmdURI.equals("spain.page")) {
				action = new ProductSpainPage();
				action.execute(request, response);

				str = "spain.jsp";
			}

			else if (cmdURI.equals("productsearchfrance.page")) {
				action = new ProductSearchFrancePage();
				action.execute(request, response);

				str = "france.jsp";
			}

			else if (cmdURI.equals("productsearchitaly.page")) {
				action = new ProductSearchItalyPage();
				action.execute(request, response);

				str = "italy.jsp";
			}

			else if (cmdURI.equals("productsearchchile.page")) {
				action = new ProductSearchChilePage();
				action.execute(request, response);

				str = "chile.jsp";
			}

			else if (cmdURI.equals("productsearchspain.page")) {
				action = new ProductSearchSpainPage();
				action.execute(request, response);

				str = "spain.jsp";
			}

		} else { // 로그인안해도 볼수있게 하려면 여기에도 추가
			if (cmdURI.equals("productall.page")) {
				action = new ProductAllPage();
				action.execute(request, response);

				str = "productAll.jsp";
			}

			else if (cmdURI.equals("france.page")) {
				action = new ProductFrancePage();
				action.execute(request, response);

				str = "france.jsp";
			}

			else if (cmdURI.equals("chile.page")) {
				action = new ProductChilePage();
				action.execute(request, response);

				str = "chile.jsp";
			}

			else if (cmdURI.equals("italy.page")) {
				action = new ProductItalyPage();
				action.execute(request, response);

				str = "italy.jsp";
			}

			else if (cmdURI.equals("spain.page")) {
				action = new ProductSpainPage();
				action.execute(request, response);

				str = "spain.jsp";
			}

			else if (cmdURI.equals("productsearchall.page")) {
				action = new ProductSearchAllPage();
				action.execute(request, response);

				str = "productAll.jsp";
			}

			else if (cmdURI.equals("productsearchfrance.page")) {
				action = new ProductSearchFrancePage();
				action.execute(request, response);

				str = "france.jsp";
			}

			else if (cmdURI.equals("productsearchitaly.page")) {
				action = new ProductSearchItalyPage();
				action.execute(request, response);

				str = "italy.jsp";
			}

			else if (cmdURI.equals("productsearchchile.page")) {
				action = new ProductSearchChilePage();
				action.execute(request, response);

				str = "chile.jsp";
			}

			else if (cmdURI.equals("productsearchspain.page")) {
				action = new ProductSearchSpainPage();
				action.execute(request, response);

				str = "spain.jsp";
			}

			else if (cmdURI.equals("notice.page")) {
				action = new NoticePage();
				action.execute(request, response);
				str = "noticeBoard.jsp";

			}

		}

		RequestDispatcher dis = request.getRequestDispatcher(str);
		dis.forward(request, response);

	}
}