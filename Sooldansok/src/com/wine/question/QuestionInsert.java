package com.wine.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;
import telInfoVO.QuestionVO;

public class QuestionInsert implements QuestionTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO qd1 = new QuestionDAO();
		HttpSession ses1 = request.getSession();

		// 입력창에서 값 받기
		String q_title = request.getParameter("title");
		String q_comment = request.getParameter("textarea1");
		String mem_id = (String) ses1.getAttribute("sid");

		qd1.questionInsert(q_title, q_comment, mem_id);
		
		request.setAttribute("mem_id", mem_id);
	}
}
