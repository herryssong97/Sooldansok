package telInfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.QuestionVO;

public class QuestionDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public QuestionDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();

	}

	public ArrayList<QuestionVO> questionMyAll(String mem_id2) throws SQLException {
		ArrayList<QuestionVO> qArr = new ArrayList<QuestionVO>();
		String sql = "SELECT * FROM QUESTION WHERE MEM_ID = ? ORDER BY Q_DATE DESC";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int q_num = rs.getInt("Q_NUM");
				String q_title = rs.getString("Q_TITLE");
				String q_comment = rs.getString("Q_COMMENT");
				String answer = rs.getString("ANSWER");
				String mem_id = rs.getString("mem_id");
				Date q_date = rs.getDate("Q_DATE");
				Date answer_date = rs.getDate("ANSWER_DATE");

				QuestionVO qv1 = new QuestionVO(q_num, q_title, q_comment, answer, mem_id, q_date, answer_date);
				qArr.add(qv1);
			}
		} catch (SQLException e) {
			System.out.println("Question My All Exception");
		}
		return qArr;

	}// getAllInfo-end

	public QuestionVO questionMyOne(int q_num2, String mem_id2) throws SQLException {
		QuestionVO qv1 = null;
		String sql = "SELECT * FROM QUESTION WHERE Q_NUM = ? AND MEM_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_num2);
			pstmt.setString(2, mem_id2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int q_num = rs.getInt(1);
				String q_title = rs.getString(2);
				String q_comment = rs.getString(3);
				String answer = rs.getString(4);
				String mem_id = rs.getString(5);
				Date q_date = rs.getDate(6);
				Date answer_date = rs.getDate(7);

				qv1 = new QuestionVO(q_num, q_title, q_comment, answer, mem_id, q_date, answer_date);
			}
		} catch (SQLException e) {
			System.out.println("Question My One Exception");
		}
		return qv1;
	}

	// 문의사항 입력
	public boolean questionInsert(String title, String comment, String mem_id) throws SQLException {
		String sql = "INSERT INTO QUESTION(Q_TITLE,Q_COMMENT,MEM_ID) VALUES (?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, comment);
			pstmt.setString(3, mem_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println("Question Insert Exception");
			return false;
		}
		return true;
	}

	// 문의사항 수정
	public boolean questionUpdate(String q_title, String q_comment, int q_num) throws SQLException {

		// 업데이트 한 날짜(sysdate)로 q_date
		String sql = "UPDATE QUESTION SET Q_TITLE = ?, Q_COMMENT = ? "
				+ "WHERE Q_NUM = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, q_title);
			pstmt.setString(2, q_comment);
			pstmt.setInt(3, q_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Question Update Exception");
			return false;
		}
		return true;
	}

	// 문의사항 삭제
	public boolean questionDelete(int q_num) throws SQLException {
		String sql = "DELETE FROM QUESTION WHERE Q_NUM = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Question Delete Exception");
			return false;
		}
		return true;
	}
	
	
	public ArrayList<QuestionVO> getAllQuestion() throws SQLException {
		ArrayList<QuestionVO> qsarray = new ArrayList<QuestionVO>();
		String sql = "SELECT * FROM QUESTION ORDER BY Q_NUM DESC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int q_num = rs.getInt("Q_NUM");
			String q_title = rs.getString("Q_TITLE");
			String q_comment = rs.getString("Q_COMMENT");
			String answer = rs.getString("answer");
			String mem_id = rs.getString("MEM_ID");
			Date q_date = rs.getDate("Q_DATE");
			Date answer_date = rs.getDate("ANSWER_DATE");

			QuestionVO qsv = new QuestionVO(q_num, q_title, q_comment, answer, mem_id, q_date, answer_date);
			qsarray.add(qsv);
		}
		return qsarray;

	}// getAllInfo-end

	public QuestionVO getQuestion(String q_num1) throws SQLException {
		QuestionVO qsv = null;
		String sql = "SELECT * FROM  QUESTION WHERE Q_NUM = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, q_num1);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int q_num = rs.getInt(1);
			String q_title = rs.getString(2);
			String q_comment = rs.getString(3);
			String answer = rs.getString(4);
			String mem_id = rs.getString(5);
			Date q_date = rs.getDate(6);
			Date answer_date = rs.getDate(7);

			qsv = new QuestionVO(q_num, q_title, q_comment, answer, mem_id, q_date, answer_date);
		} else {
			qsv = null;
		}
		return qsv;
	}

	public boolean insert_question(String title, String comment) {
		String sql = "INSERT INTO QUESTION(Q_TITLE,Q_COMMENT,MEM_ID) VALUES (?,?,'asd123')";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, comment);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			System.out.println("insert Exception");
			return false;
		}
		return true;
	}

	public boolean update_question(String q_title, String q_comment, int q_num) {

		String sql = "UPDATE QUESTION SET Q_TITLE=?, Q_COMMENT=?,Q_DATE=SYSDATE WHERE Q_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, q_title);
			pstmt.setString(2, q_comment);
			pstmt.setInt(3, q_num);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}

	public boolean update_answer(String answer, int q_num) {

		String sql = "UPDATE QUESTION SET ANSWER=?,ANSWER_DATE=SYSDATE WHERE Q_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, answer);
			pstmt.setInt(2, q_num);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}

	public boolean delete_question(int q_num) {
		String sql = "DELETE FROM QUESTION WHERE Q_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, q_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}

	public ArrayList<QuestionVO> getList(int startRow, int endRow) {
		ArrayList<QuestionVO> qslist = new ArrayList<QuestionVO>();

		String sql = "SELECT * " +
				  	  "FROM (SELECT t.*, ROWNUM AS rnum " +
				  	  "      FROM (SELECT * " +
				  	  "            FROM QUESTION " +
				  	  "            ORDER BY Q_NUM DESC) t " +
				  	  "      WHERE ROWNUM <= ?) " +
				  	  "WHERE rnum >= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int q_num = rs.getInt("Q_NUM");
				String q_title = rs.getString("Q_TITLE");
				String q_comment = rs.getString("Q_COMMENT");
				String answer = rs.getString("ANSWER");
				String mem_id = rs.getString("MEM_ID");
				Date q_date = rs.getDate("Q_DATE");
				Date answer_date = rs.getDate("ANSWER_DATE");

				QuestionVO tv = new QuestionVO(q_num, q_title, q_comment, answer, mem_id, q_date, answer_date);
				qslist.add(tv);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return qslist;
	}

	public int getTotalCount() {
		int qstotal = 0;

		try {

			String sql = "SELECT COUNT(*) FROM QUESTION";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				qstotal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qstotal;
	}

	public ArrayList<QuestionVO> getFilterList(int startRow, int endRow, String filter, String search1, String search2) {
		ArrayList<QuestionVO> qsflist = new ArrayList<QuestionVO>();

		String sql = null;
		String search11 = null;
		String search22 = "%" + search2.toUpperCase() + "%";

		if (filter.equals("TITLE_COMMENT")) {
		search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
			sql = "SELECT * " +
					"FROM (SELECT t.*, ROWNUM AS rnum " +
					"      FROM (SELECT * " +
					"            FROM QUESTION " +
					"			 WHERE UPPER(MEM_ID) like ? " +
					"			 AND (UPPER(Q_TITLE) like ? " +
					"			 OR UPPER(Q_COMMENT) like ? ) " +
					"            ORDER BY Q_NUM DESC) t " +
					"      WHERE ROWNUM <= ?) " +
					"WHERE rnum >= ?";
		} else if (filter.equals("MEM_ID")) {
			search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
			sql = "SELECT * " +
					"FROM (SELECT t.*, ROWNUM AS rnum " +
					"      FROM (SELECT * " +
					"            FROM QUESTION " +
					"			 WHERE UPPER(MEM_ID) like ? " +
					"			 OR UPPER(MEM_ID) like ? " +
					"			 OR UPPER(MEM_ID) like ? " +
					"            ORDER BY Q_NUM DESC) t " +
					"      WHERE ROWNUM <= ?) " +
					"WHERE rnum >= ?";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search11);
			pstmt.setString(2, search22);
			pstmt.setString(3, search22);
			pstmt.setInt(4, endRow);
			pstmt.setInt(5, startRow);
			rs = pstmt.executeQuery();
			//System.out.println(endRow);
			//System.out.println(startRow);

			while (rs.next()) {     
  				int q_num = rs.getInt("Q_NUM");
  				String q_title = rs.getString("Q_TITLE");
  				String q_comment = rs.getString("Q_COMMENT");
  				String answer= rs.getString("ANSWER");
  				String mem_id = rs.getString("MEM_ID");
  				Date q_date=rs.getDate("Q_DATE");
  				Date answer_date=rs.getDate("ANSWER_DATE");
  				
  				QuestionVO tv = new QuestionVO(q_num,q_title,q_comment,answer,mem_id,q_date,answer_date); 
  				qsflist.add(tv);
  				//System.out.println(tv.getQ_title());
  			}

          } catch (Exception e) {
              e.printStackTrace();
          }
          return qsflist;
	}

	public int getFilterTotal(String filter, String search1, String search2) throws SQLException {
		// TODO Auto-generated method stub
		int qsftotal = 0;
		String search11 = null;
		String search22 = "%" + search2.toUpperCase() + "%";
		String sql = null;

		try {
			if (filter.equals("TITLE_COMMENT")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
				sql = "SELECT COUNT(*) "
					+ "FROM QUESTION "
					+ "WHERE UPPER(MEM_ID) like ? "
					+ "		 AND (UPPER(Q_TITLE) like ? "
					+ "		 OR UPPER(Q_COMMENT) like ?) "
					+ "ORDER BY Q_NUM";
			} else if (filter.equals("MEM_ID")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
				sql = "SELECT COUNT(*) "
					+ "FROM QUESTION "
					+ "WHERE UPPER(MEM_ID) like ? "
					+ "		 OR UPPER(MEM_ID) like ? "
					+ "		 OR UPPER(MEM_ID) like ? "
					+ "ORDER BY Q_NUM";
			}
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, search11);
			pstmt.setString(2, search22);
			pstmt.setString(3, search22);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				qsftotal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qsftotal;
	}
	
	

}