package com.wine.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.ProductAllTelImpl;

import telInfoDAO.Pd_InfoDAO;
import telInfoDAO.ReviewDAO;
import telInfoVO.Pd_InfoVO;
import telInfoVO.ReviewVO;

public class Pd_InfoSearchOne implements ProductAllTelImpl {
	public void productalltelImpl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Pd_InfoDAO td1 = new Pd_InfoDAO();
		ReviewDAO rd1 = new ReviewDAO();

		String info_num = request.getParameter("info_num");
		// int info_num2 = Integer.parseInt(info_num);

		Pd_InfoVO tv = td1.getPd_Info(info_num);
		ArrayList<ReviewVO> pdRev = rd1.reviewDetail(info_num);

		request.setAttribute("sinfo_num", info_num);
		request.setAttribute("stv", tv);
		request.setAttribute("pdRev", pdRev);

	}
}
