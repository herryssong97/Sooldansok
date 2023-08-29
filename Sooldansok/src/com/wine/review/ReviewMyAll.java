package com.wine.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.ReviewImpl;

import telInfoDAO.ReviewDAO;
import telInfoVO.ReviewVO;

public class ReviewMyAll implements ReviewImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ReviewDAO rd1 = new ReviewDAO();

		HttpSession ses1 = request.getSession();

		String mem_id = (String) ses1.getAttribute("sid");

		ArrayList<ReviewVO> myRev = rd1.reviewMyAll(mem_id);

		request.setAttribute("myRev", myRev);

	}
}
