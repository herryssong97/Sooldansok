package com.wine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ProductAllTelImpl;
import com.wine.product.Pd_InfoDelete;
import com.wine.product.Pd_InfoInsert;
import com.wine.product.Pd_InfoSearchOne;
import com.wine.product.Pd_InfoUpdate;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("*.product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
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

		ProductAllTelImpl pda1 = null;

		switch (c) {

		case "/Pd_InfoSearchOne.product": // 제품 상세 페이지 조회
			pda1 = new Pd_InfoSearchOne();
			try {
				pda1.productalltelImpl(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "productDetail.jsp";
			break;

		case "/Pd_InfoSearchOne_mngr.product": // 제품 상세 페이지 조회
			pda1 = new Pd_InfoSearchOne();
			try {
				pda1.productalltelImpl(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "productDetail_mngr.jsp";
			break;

		case "/ProductInsert.product":
			pda1 = new Pd_InfoInsert();
			try {
				pda1.productalltelImpl(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "board.board";
			break;

		case "/Pd_InfoDelete.product":
			pda1 = new Pd_InfoDelete();
			try {
				pda1.productalltelImpl(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "product.page";
			break;

		case "/Pd_InfoUpdate.product":
			pda1 = new Pd_InfoUpdate();
			try {
				pda1.productalltelImpl(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "Pd_InfoSearchOne_mngr.product";
			break;

		}

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
