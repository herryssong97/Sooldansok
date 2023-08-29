package com.wine.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import telInfoDAO.ClientDAO;
import telInfoVO.ClientVO;

/**
 * Servlet implementation class LoginSession
 */
@WebServlet("/LoginSession")
public class LoginSession extends HttpServlet {

	private Connection con;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	// SHA 256 로직

	// SHA-256으로 해싱하는 메소드
	public static String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());

		return bytesToHex(md.digest());

	}

	// 바이트를 헥스값으로 변환
	public static String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	// 1. 메시지 다이제스트를 문자열로 return
	public static String ShaEncoder(String userPw) {
		try {
			// 알고리즘을 "SHA-256"으로 하여 MessageDigest 객체 생성
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// 해시된 데이터는 바이트 배열의 바이너리 데이터이다.
			byte[] hash = digest.digest(userPw.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			// 바이트 배열을 16진수(hex) 문자열로 변환
			for (byte b : hash) { // hash에서 데이터를 꺼내 b에 담는다.
				// byte 8비트 ->int 32bit 형변환 시 앞의 18비트가 19번째 비트와 같은 값으로 채우는데
				// 이 경우에 원본 값과 다른 경우가 되는 것을 방지하기 위한 연산이다.
				String hex = Integer.toHexString(0xff & b);

				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	// 2. 메시지 다이제스트를 바이트 배열로 return
	public static byte[] shaEncoderByte(String message) {
		// 이 메소드는 바이트배열을 16진수 문자열로 변환하지 않음
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
			return hash;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	// SHA 256 로직 종료

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// ϴ
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pw = null;
		try {
			pw = sha256(request.getParameter("pw")); // 로그인 화면에서 입력 pw를 암호화
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 콘솔 확인용
//		System.out.println("id : " + id);
//		System.out.println("pw : " + pw);

		ClientDAO cd1 = null;
		ClientVO cv1 = null;

		try {
			cd1 = new ClientDAO();
			cv1 = cd1.getClient(id, pw);

			//
			if (cv1 != null && id.equals(cv1.getId()) && pw.equals(cv1.getPw())) {

				// System.out.println("입력한 아이디 : " + id);
				// System.out.println("입력한 비번 : " + pw);

				// 입력 pw을 sha 256 으로 암호화하여 DB에 저장된 암호화 비번과 비교

				HttpSession ses1 = request.getSession(); // return,
				ses1.setAttribute("sid", id); // ̵
				ses1.setAttribute("spw", pw); // й ȣ
				ses1.setMaxInactiveInterval(1800); // 30

				//
				if (id.equals("admin")) {
					RequestDispatcher rd1 = request.getRequestDispatcher("index_mngr.jsp");
					rd1.forward(request, response);
				} else {
					RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
					rd1.forward(request, response);
				}

				out.print("<script>alert('로그인 성공');");
				out.print("history.back();");
				out.println("</script>");

//				return;

			} else { //
				out.print("<script>alert('로그인 실패');");
				out.print("history.back();");
				out.println("</script>");

				System.out.println("입력한 아이디 : " + id);
				System.out.println("입력한 비번 : " + pw);
			}

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
