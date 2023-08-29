package com.wine.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import telInfoDAO.Pd_InfoDAO;

@WebServlet("*.upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Pd_InfoDAO td1 = null;
		try {
			td1 = new Pd_InfoDAO();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		String bang = "images"; // WebContent아래에 upbang을 미리 만든다
		ServletContext context = getServletContext();
		String jeojang = context.getRealPath(bang);
		String img = null;

		try {
			MultipartRequest mr1 = new MultipartRequest(request, jeojang, 15 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());

			Enumeration<String> fileNames = mr1.getFileNames();
			if (fileNames.hasMoreElements()) {
				String fileName = fileNames.nextElement();
				img = mr1.getFilesystemName(fileName);
			}

			String info_name = mr1.getParameter("info_name");
			String info_price = mr1.getParameter("info_price");
			String kind = mr1.getParameter("kind");
			String country = mr1.getParameter("country");
			String capacity1 = mr1.getParameter("capacity");
			int capacity = capacity1 != null && !capacity1.isEmpty() ? Integer.parseInt(capacity1) : 0;
			String alcohol = mr1.getParameter("alcohol");
			String made_year1 = mr1.getParameter("made_year");
			int made_year = made_year1 != null && !made_year1.isEmpty() ? Integer.parseInt(made_year1) : 0;
			String stock1 = mr1.getParameter("stock");
			int stock = stock1 != null && !stock1.isEmpty() ? Integer.parseInt(stock1) : 0;

			boolean insert = td1.insert_product(info_name, info_price, kind, country, capacity, alcohol, made_year,
					stock, img);

			if (insert) {
				String successMessage = "상품 추가가 완료되었습니다.";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter()
						.println("<script>alert('" + successMessage + "'); location.href='product.page';</script>");
			} else {
				String errorMessage = "상품 추가에 실패했습니다.";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter()
						.println("<script>alert('" + errorMessage + "'); location.href='product.page';</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: 에러 처리
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}