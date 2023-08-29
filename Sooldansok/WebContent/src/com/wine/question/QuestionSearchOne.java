package com.wine.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;
import telInfoVO.QuestionVO;

public class QuestionSearchOne implements QuestionTelImpl {

	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qsdao = new QuestionDAO();

		String q_num = request.getParameter("q_num");

		QuestionVO tv = qsdao.getQuestion(q_num);

		request.setAttribute("sn_title", q_num);
		request.setAttribute("stv", tv);
	}
}