package com.wine.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ReviewImpl;

import telInfoDAO.ReviewDAO;

public class ReviewDelete implements ReviewImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ReviewDAO rd1 = null;
		int rev_num = Integer.parseInt(request.getParameter("rev_num"));

		try {
			rd1 = new ReviewDAO();
			rd1.reviewDelete(rev_num);
		} catch (Exception e) {

		}
	}
}
