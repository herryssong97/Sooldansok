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

@WebServlet("*.imgchange")
public class ImgChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImgChangeController() {
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

			String infoNumParam = mr1.getParameter("info_num");
			int info_num = Integer.parseInt(infoNumParam);

			td1.imgchange_pd_Info(img, info_num);
			response.sendRedirect("Pd_InfoSearchOne_mngr.product?info_num=" + info_num);
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