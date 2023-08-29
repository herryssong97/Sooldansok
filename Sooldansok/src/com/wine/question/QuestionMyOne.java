package com.wine.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;
import telInfoVO.QuestionVO;

public class QuestionMyOne implements QuestionTelImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qd1 = null;
		QuestionVO qv1 = null;

		HttpSession ses1 = request.getSession();

		int q_num2 = Integer.parseInt(request.getParameter("q_num"));
		String mem_id2 = (String) ses1.getAttribute("sid");

		try {
			qd1 = new QuestionDAO();
			qv1 = qd1.questionMyOne(q_num2, mem_id2);
		} catch (Exception e) {

		}
		request.setAttribute("qmo", qv1);
	}
}
