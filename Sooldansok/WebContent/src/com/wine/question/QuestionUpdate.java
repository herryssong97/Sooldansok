package com.wine.question;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;

public class QuestionUpdate implements QuestionTelImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qd1 = null;

		String q_title = request.getParameter("q_title");
		String q_comment = request.getParameter("q_comment");
		int q_num = Integer.parseInt(request.getParameter("q_num"));

		try {
			qd1 = new QuestionDAO();
			qd1.questionUpdate(q_title, q_comment, q_num);
		} catch (Exception e) {
		}
	}
}
