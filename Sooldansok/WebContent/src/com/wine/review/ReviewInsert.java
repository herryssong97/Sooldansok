package com.wine.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wine.impl.ReviewImpl;

import telInfoDAO.ReviewDAO;

public class ReviewInsert implements ReviewImpl {

	@Override
	public void tel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("\"text/html; charset=UTF-8\"");
		PrintWriter out = response.getWriter();

		ReviewDAO rd1 = new ReviewDAO();
		HttpSession ses1 = request.getSession();

		String mem_id = (String) ses1.getAttribute("sid");
		String comments = request.getParameter("reviewcontent");
		int stars1 = Integer.parseInt(request.getParameter("stars"));
		int od_num = Integer.parseInt(request.getParameter("od_num"));

		boolean rvin = rd1.reviewInsert(mem_id, comments, stars1, od_num);

		if (rvin) {
			out.print("<script>alert('리뷰가 등록되었습니다.');</script>");
		} else {
			out.println("<script>alert('리뷰 등록에 실패했습니다.');");
			out.println("window.location.href='myreviewInsert.jsp?prevalue=comments&stars=stars1';");
			out.println("</script>");// jsp에 stars 유지조건 추가할것
		}

	}

}
