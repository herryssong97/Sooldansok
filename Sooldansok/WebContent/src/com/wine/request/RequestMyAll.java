package com.wine.request;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.wine.impl.RequestImpl;

import telInfoDAO.Pd_RequestDAO;
import telInfoVO.Pd_RequestVO;

public class RequestMyAll implements RequestImpl {
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_RequestDAO rd1 = new Pd_RequestDAO();

		HttpSession ses1 = request.getSession();

		String mem_id2 = (String) ses1.getAttribute("sid");

		ArrayList<Pd_RequestVO> myReq = rd1.requestMyAll(mem_id2);

		// 전체 검색 처리
		request.setAttribute("myReq", myReq);
	}
}
