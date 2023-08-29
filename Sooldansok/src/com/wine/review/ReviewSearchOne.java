package com.wine.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ReviewImpl;

import telInfoDAO.ReviewDAO;
import telInfoVO.ReviewVO;

public class ReviewSearchOne implements ReviewImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");

		ReviewDAO rvdao = new ReviewDAO();

		String rev_num = request.getParameter("rev_num");

		ReviewVO rv = rvdao.getReview(rev_num);

		request.setAttribute("rv", rv);

	}

}
