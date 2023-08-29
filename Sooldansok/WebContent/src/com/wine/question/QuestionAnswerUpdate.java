package com.wine.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;

public class QuestionAnswerUpdate implements QuestionTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qsdao = new QuestionDAO();

		String answer = request.getParameter("answer");
		int q_num = Integer.parseInt(request.getParameter("q_num"));

		qsdao.update_answer(answer, q_num);

	}
}
