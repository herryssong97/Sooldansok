package com.wine.basket;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wine.impl.BasketTelImpl;

import telInfoDAO.BasketDAO;
import telInfoVO.BasketVO;

public class BasketDeleteSelected implements BasketTelImpl {

	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String mem_id = request.getParameter("mem_id");
		String[] selectedBasNumsArr = request.getParameterValues("selectedBasNums");

		if (selectedBasNumsArr != null) {
			int[] selectedBasNums = new int[selectedBasNumsArr.length];
			for (int i = 0; i < selectedBasNumsArr.length; i++) {
				selectedBasNums[i] = Integer.parseInt(selectedBasNumsArr[i]);
			}

			BasketDAO bDAO = new BasketDAO();
			int deletedRows = bDAO.deleteSelectedBasket(mem_id, selectedBasNums);

			if (deletedRows > 0) {
				request.setAttribute("msg", "선택한 항목이 삭제되었습니다.");
			} else {
				request.setAttribute("msg", "선택한 항목 삭제에 실패하였습니다.");
			}
		} else {
			request.setAttribute("msg", "선택된 항목이 없습니다.");
		}

		// 장바구니 목록 가져오기
		BasketDAO basketDAO = new BasketDAO();
		ArrayList<BasketVO> alist1 = basketDAO.getAllBasket(mem_id);

		request.setAttribute("alist1", alist1);
	}
}
