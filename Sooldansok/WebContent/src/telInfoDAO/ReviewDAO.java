package telInfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.ReviewVO;

public class ReviewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ReviewDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();
	}

	// 내가 쓴 모든 리뷰 조회 (일단 날짜는 스킵)
	public ArrayList<ReviewVO> reviewMyAll(String mem_id2) throws SQLException {
		ArrayList<ReviewVO> rArr = new ArrayList<ReviewVO>();
		String sql = "SELECT R.REV_NUM, P.INFO_NAME, R.COMMENTS, R.STARS, R.MEM_ID"
				+ " FROM REVIEW R, PD_INFO P, ORDERS O"
				+ " WHERE R.OD_NUM = O.OD_NUM"
				+ " AND R.MEM_ID = O.MEM_ID"
				+ " AND O.PD_ID = P.INFO_NUM"
				+ " AND R.MEM_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int rev_num = rs.getInt("REV_NUM");
				String pd_name = rs.getString("INFO_NAME");
				String mem_id = rs.getString("MEM_ID");
				String comments = rs.getString("COMMENTS");
				int stars = rs.getInt("STARS");
				int od_num = rs.getInt("REV_NUM");
				
				ReviewVO rv1 = new ReviewVO(rev_num, pd_name, mem_id, comments, stars,od_num);
				rArr.add(rv1);
			}

		} catch (SQLException e) {
			System.out.println("Review My All Exception");
		}
		return rArr;

	} // reviewMyAll-end

	// 본인 리뷰 개별 조회 (review, orders, pd_info 조)
	public ReviewVO reviewMyOne(int rev_num2) throws SQLException {
		ReviewVO rv1 = null;
		String sql = "SELECT * " + "FROM REVIEW " + "WHERE REV_NUM = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_num2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int rev_num = rs.getInt(1);
				String mem_id = rs.getString(2);
				String comments = rs.getString(3);
				int stars = rs.getInt(4);
				int od_num = rs.getInt(5);
				rv1 = new ReviewVO(rev_num, mem_id, comments, stars, od_num);
			}
		} catch (SQLException e) {
			System.out.println("Review My One Exception");
		}
		return rv1;
	}
	
	// 리뷰 수정
	public boolean reviewUpdate(String comments, int stars, int rev_num) throws SQLException {
		String sql = "UPDATE REVIEW SET COMMENTS = ?, STARS = ?"
				+ " WHERE REV_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, comments);
			pstmt.setInt(2, stars);
			pstmt.setInt(3, rev_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Review Update Exception");
			return false;
		}
		return true;
	}
	
	// 리뷰 삭제
	public boolean reviewDelete(int rev_num) throws SQLException {
		String sql = "DELETE FROM REVIEW WHERE REV_NUM = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Review Delete Exception");
			return false;
		}
		return true;
	}
	

	// 상품별 리뷰 조회
	// 여러개 조회해야 하므로 ArrayList
	public ArrayList<ReviewVO> reviewDetail(String info_num) throws SQLException {
		
		ArrayList<ReviewVO> rArr = new ArrayList<ReviewVO>();
		
		String sql = "select r.mem_id, r.comments, r.stars"
				+ " from review r, orders o, pd_info p"
				+ " where r.od_num = o.od_num"
				+ " and o.pd_id = p.info_num"
				+ " and p.info_num = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info_num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String mem_id = rs.getString(1);
				String comments = rs.getString(2);
				int stars = rs.getInt(3);
				
				ReviewVO rv1 = new ReviewVO(mem_id, comments, stars);
				rArr.add(rv1);
			}
			
		} catch (SQLException e) {
			System.out.println("Review Detail Exception");
		}
		return rArr;
	}
	
	
	public ArrayList<ReviewVO> getAllReview() throws SQLException {
		ArrayList<ReviewVO> rvarray = new ArrayList<ReviewVO>();
		String sql = "SELECT r.*, o.INFO_NAME PD_NAME "
				   + "FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
				   + "                FROM PD_INFO p, ORDERS o "
				   + "                WHERE p.INFO_NUM = o.PD_ID) o "
				   + "WHERE r.OD_NUM = o.OD_NUM "
				   + "ORDER BY r.REV_NUM DESC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int rev_num = rs.getInt("REV_NUM");
			String mem_id = rs.getString("MEM_ID");
			String comments = rs.getString("COMMENTS");
			int stars = rs.getInt("STARS");
			int od_num = rs.getInt("OD_NUM");
			String pd_name = rs.getString("PD_NAME");
									
			ReviewVO rvv = new ReviewVO(rev_num, pd_name, mem_id, comments, stars,od_num);
			rvarray.add(rvv);
		}
		return rvarray;

	}

	public int getFilterTotal(String filter, String search1, String search2) {
		// TODO Auto-generated method stub
		int rvftotal = 0;
		String search11 = null;
		String search22 = "%" + search2.toUpperCase() + "%";
		String sql = null;

		try {
			if (filter.equals("PD_NAME")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
				sql = "SELECT COUNT(*) "
					+ "FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
					+ "                FROM PD_INFO p, ORDERS o "
					+ "                WHERE p.INFO_NUM = o.PD_ID) o "
					+ "WHERE r.OD_NUM = o.OD_NUM "
					+ "		 AND UPPER(r.MEM_ID) LIKE ? "
					+ "		 AND UPPER(o.INFO_NAME) LIKE ? "
					+ "ORDER BY r.REV_NUM DESC";
			} else if (filter.equals("MEM_ID")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
				sql = "SELECT COUNT(*) "
					+ "FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
					+ "                FROM PD_INFO p, ORDERS o "
					+ "                WHERE p.INFO_NUM = o.PD_ID) o "
					+ "WHERE r.OD_NUM = o.OD_NUM "
					+ "		 AND UPPER(r.MEM_ID) LIKE ? "
					+ "		 AND UPPER(r.MEM_ID) LIKE ? "
					+ "ORDER BY r.REV_NUM DESC";
			}
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, search11);
			pstmt.setString(2, search22);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rvftotal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rvftotal;
	}
/////////이거 맞추고 jsp 수정 후 테스트 하면 됨
	public ArrayList<ReviewVO> getFilterList(int startRow, int endRow, String filter, String search1, String search2) {
		// TODO Auto-generated method stub
		ArrayList<ReviewVO> rvflist = new ArrayList<ReviewVO>();

		String sql = null;
		String search11 = null;
		String search22 = "%" + search2.toUpperCase() + "%";
		
		if(filter.equals("PD_NAME")) {
			search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
			sql = "SELECT * "
				+ "FROM (SELECT t.*, ROWNUM AS rnum "
				+ "      FROM (SELECT r.*, o.INFO_NAME PD_NAME "
				+ "			   FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
				+ "                			   FROM PD_INFO p, ORDERS o "
				+ "                			   WHERE p.INFO_NUM = o.PD_ID) o "
				+ "			   WHERE r.OD_NUM = o.OD_NUM "
				+ "					 AND UPPER(r.MEM_ID) LIKE ? "
				+ "					 AND UPPER(o.INFO_NAME) LIKE ? "
				+ "			   ORDER BY r.REV_NUM DESC) t "
				+ "      WHERE ROWNUM <= ?) "
				+ "WHERE rnum >= ?";
		} else if (filter.equals("MEM_ID")) {
			search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
			sql = "SELECT * "
				+ "FROM (SELECT t.*, ROWNUM AS rnum "
				+ "      FROM (SELECT r.*, o.INFO_NAME PD_NAME "
				+ "			   FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
				+ "                			   FROM PD_INFO p, ORDERS o "
				+ "                			   WHERE p.INFO_NUM = o.PD_ID) o "
				+ "			   WHERE r.OD_NUM = o.OD_NUM "
				+ "					 AND UPPER(r.MEM_ID) LIKE ? "
				+ "					 AND UPPER(r.MEM_ID) LIKE ? "
				+ "			   ORDER BY r.REV_NUM DESC) t "
				+ "      WHERE ROWNUM <= ?) "
				+ "WHERE rnum >= ?";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search11);
			pstmt.setString(2, search22);
			pstmt.setInt(3, endRow);
			pstmt.setInt(4, startRow);
			rs = pstmt.executeQuery();
			//System.out.println(endRow);
			//System.out.println(startRow);

			while (rs.next()) {
				int rev_num = rs.getInt("REV_NUM");
				String mem_id = rs.getString("MEM_ID");
				String comments = rs.getString("COMMENTS");
				int stars = rs.getInt("STARS");
				int od_num = rs.getInt("OD_NUM");
				String pd_name = rs.getString("PD_NAME");
								
				ReviewVO rvv = new ReviewVO(rev_num, pd_name, mem_id, comments, stars,od_num);
				rvflist.add(rvv);
  				//System.out.println(tv.getQ_title());
  			}

          } catch (Exception e) {
              e.printStackTrace();
          }
          return rvflist;
	}

	public ReviewVO getReview(String rev_num1) throws SQLException {
		
		ReviewVO rv = null;

		String sql = "SELECT r.*, o.INFO_NAME PD_NAME "
				   + "FROM REVIEW r, (SELECT p.INFO_NAME, o.OD_NUM "
				   + "				  FROM PD_INFO p, ORDERS o "
				   + "				  WHERE p.INFO_NUM = o.PD_ID) o "
				   + "WHERE r.OD_NUM = o.OD_NUM "
				   + "		AND REV_NUM LIKE ? "
				   + "ORDER BY r.REV_NUM DESC";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, rev_num1);
		
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			int rev_num = rs.getInt("REV_NUM");
			String mem_id = rs.getString("MEM_ID");
			String comments = rs.getString("COMMENTS");
			int stars = rs.getInt("STARS");
			int od_num = rs.getInt("OD_NUM");
			String pd_name = rs.getString("PD_NAME");

			rv = new ReviewVO(rev_num, pd_name, mem_id, comments, stars,od_num);
		} else {
			rv = null;
		}
		return rv;
	}
	
	public boolean reviewInsert(String mem_id, String comments, int stars, int od_num) {
		String sql = "INSERT INTO REVIEW(MEM_ID, COMMENTS, STARS, OD_NUM) VALUES (?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, comments);
			pstmt.setInt(3, stars);
			pstmt.setInt(4, od_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("reviewInsert Exception");
			return false;
		}
		
		return true;
	}
	
} // ReviewDAO-end
