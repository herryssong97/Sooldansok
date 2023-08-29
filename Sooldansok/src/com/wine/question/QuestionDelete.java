package com.wine.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;

public class QuestionDelete implements QuestionTelImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qd1 = null;
		int q_num = Integer.parseInt(request.getParameter("q_num"));

		try {
			qd1 = new QuestionDAO();
			qd1.questionDelete(q_num);
		} catch (Exception e) {

		}
	}

}
