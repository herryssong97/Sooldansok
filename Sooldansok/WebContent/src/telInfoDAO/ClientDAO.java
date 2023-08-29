package telInfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.ClientVO;

public class ClientDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ClientDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();
	}

	public ArrayList<ClientVO> getAllInfo() throws SQLException {
		ArrayList<ClientVO> cvarray = new ArrayList<ClientVO>();
		String sql = "SELECT * FROM CLIENT ORDER BY ID";

		pstmt = con.prepareStatement(sql);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString("ID");
			String pw = rs.getString("PW");
			String name = rs.getString("NAME");
			Date birth = rs.getDate("BIRTH");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("EMAIL");
			String phone = rs.getString("PHONE");

			ClientVO cv = new ClientVO(id, pw, name, birth, address, email, phone);

			cvarray.add(cv);
			// System.out.println(cv.getId());
		}

		/*
		 * if (cvarray.size() != 0) { for (int i = 0; i < cvarray.size(); i++) {
		 * System.out.println(cvarray.get(i) + " "); } } else if (cvarray.size() == 0) {
		 * System.out.println("없는뎁숑 ㅜ"); }
		 */

		return cvarray;
	}

	public int clientSum() throws SQLException {
		int clsum = 0;
		String sql = "SELECT COUNT(*) FROM CLIENT";
		pstmt = con.prepareStatement(sql);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			clsum = rs.getInt("count(*)");
		} else {
			clsum = 0;
		}

		return clsum;
	}

	public ClientVO getOneInfo(String id2) throws SQLException {
		ClientVO cv = null;
		String sql = "SELECT * FROM CLIENT WHERE ID = ?";
		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id2);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			String id = rs.getString("ID");
			String pw = rs.getString("PW");
			String name = rs.getString("NAME");
			Date birth = rs.getDate("BIRTH");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("EMAIL");
			String phone = rs.getString("PHONE");

			cv = new ClientVO(id, pw, name, birth, address, email, phone);

		} else {
			cv = null;
		}
		return cv;
	}

	public boolean updateClient(String id1, String pw1, String name1, String address1, String email1, String phone1)
			throws SQLException {
		String sql = "UPDATE CLIENT SET PW=?, NAME=?, ADDRESS=?, EMAIL=?, PHONE=? WHERE ID=?";
		pstmt = con.prepareStatement(sql);

		/*
		 * int year = Integer.parseInt(birth1.substring(0, 4)) - 1900; int month =
		 * Integer.parseInt(birth1.substring(4, 6)) - 1; int day =
		 * Integer.parseInt(birth1.substring(6, 8));
		 */

		try {
			pstmt.setString(1, pw1);
			pstmt.setString(2, name1);
			pstmt.setString(3, address1);
			pstmt.setString(4, email1);
			pstmt.setString(5, phone1);
			pstmt.setString(6, id1);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Client Update Exception");
			return false;
		}
		return true;
	}
	
	public boolean updateClient_mngr(String id1, String name1, String address1, String email1, String phone1)
			throws SQLException {
		String sql = "UPDATE CLIENT SET NAME=?, ADDRESS=?, EMAIL=?, PHONE=? WHERE ID=?";
		pstmt = con.prepareStatement(sql);

		/*
		 * int year = Integer.parseInt(birth1.substring(0, 4)) - 1900; int month =
		 * Integer.parseInt(birth1.substring(4, 6)) - 1; int day =
		 * Integer.parseInt(birth1.substring(6, 8));
		 */

		try {
			pstmt.setString(1, name1);
			pstmt.setString(2, address1);
			pstmt.setString(3, email1);
			pstmt.setString(4, phone1);
			pstmt.setString(5, id1);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Client Update Exception");
			return false;
		}
		return true;
	}

	public boolean deleteClient(String id1) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE CLIENT WHERE ID=?";

		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id1);

		pstmt.executeUpdate();

		return true;
	}

	public ArrayList<ClientVO> getFilterInfo(String filter, String search) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<ClientVO> cvarray = new ArrayList<ClientVO>();

		String sql = "SELECT * FROM CLIENT WHERE UPPER(" + filter + ") LIKE ? ORDER BY ID";
		String search1 = "%" + search.toUpperCase() + "%";

		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, search1);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			String id = rs.getString("ID");
			String pw = rs.getString("PW");
			String name = rs.getString("NAME");
			Date birth = rs.getDate("BIRTH");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("EMAIL");
			String phone = rs.getString("PHONE");

			ClientVO cv = new ClientVO(id, pw, name, birth, address, email, phone);

			cvarray.add(cv);
		}

		return cvarray;
	}

	public ArrayList<ClientVO> getList(int startRow, int endRow) {
		ArrayList<ClientVO> cvlist = new ArrayList<ClientVO>();

		String sql = "SELECT * " + "FROM (SELECT t.*, ROWNUM AS rnum " + "      FROM (SELECT * "
				+ "            FROM CLIENT " + "            ORDER BY ID) t " + "      WHERE ROWNUM <= ?) "
				+ "WHERE rnum >= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("ID");
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				Date birth = rs.getDate("BIRTH");
				String address = rs.getString("ADDRESS");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");

				ClientVO cv = new ClientVO(id, pw, name, birth, address, email, phone);

				cvlist.add(cv);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvlist;
	}

	public int getTotalCount() {
		int cvtotal = 0;

		try {

			String sql = "SELECT COUNT(*) FROM CLIENT";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvtotal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvtotal;
	}

	public ArrayList<ClientVO> getFilterList(int startRow, int endRow, String filter, String search) {
		ArrayList<ClientVO> cvflist = new ArrayList<ClientVO>();

		String sql = "SELECT * " + "FROM (SELECT t.*, ROWNUM AS rnum " + "      FROM (SELECT * "
				+ "            FROM CLIENT " + "           WHERE UPPER(" + filter + ") LIKE ? "
				+ "            ORDER BY ID) t " + "      WHERE ROWNUM <= ?) " + "WHERE rnum >= ?";
		String search1 = "%" + search.toUpperCase() + "%";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search1);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("ID");
				String pw = rs.getString("PW");
				String name = rs.getString("NAME");
				Date birth = rs.getDate("BIRTH");
				String address = rs.getString("ADDRESS");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");

				ClientVO cv = new ClientVO(id, pw, name, birth, address, email, phone);

				cvflist.add(cv);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvflist;
	}

	public int getFilterTotal(String filter, String search) throws SQLException {
		// TODO Auto-generated method stub
		int cvftotal = 0;

		try {
			String sql = "SELECT COUNT(*) FROM CLIENT WHERE UPPER(" + filter + ") LIKE ? ORDER BY ID";
			String search1 = "%" + search.toUpperCase() + "%";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, search1);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cvftotal = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvftotal;
	}

	public ClientVO getClient(String id, String pw) throws SQLException {
		ClientVO cv1 = null;
		String sql = "SELECT * FROM CLIENT WHERE ID = ? AND PW = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cv1 = new ClientVO();
				cv1.setId(rs.getString("id"));
				cv1.setPw(rs.getString("pw"));
				cv1 = new ClientVO(id, pw);
			} else {
				cv1 = null;
			}

		} // try-end

		catch (Exception e) {
			e.printStackTrace();
		}
		return cv1;

	} // getClient-end

}