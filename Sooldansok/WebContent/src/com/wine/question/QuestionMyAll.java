package com.wine.question;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.QuestionTelImpl;

import telInfoDAO.QuestionDAO;
import telInfoVO.QuestionVO;

public class QuestionMyAll implements QuestionTelImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		QuestionDAO td1 = new QuestionDAO();

		HttpSession ses1 = request.getSession();

		String mem_id = (String) ses1.getAttribute("sid");

		ArrayList<QuestionVO> myQ = td1.questionMyAll(mem_id);

		request.setAttribute("myQ", myQ);
	}

}
