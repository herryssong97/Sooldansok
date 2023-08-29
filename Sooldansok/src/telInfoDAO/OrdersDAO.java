package telInfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;

import telInfoVO.OrdersVO;

public class OrdersDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public OrdersDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();

	}

	public ArrayList<OrdersVO> getAllOrders() throws SQLException {
		ArrayList<OrdersVO> tiarray = new ArrayList<OrdersVO>();
		String sql = "SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
				   + "       TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999'), O.OD_DATE "
				   + "FROM ORDERS O JOIN PD_INFO P "
				   + "ON P.INFO_NUM = O.PD_ID "
				   + "ORDER BY O.OD_NUM DESC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int od_num = rs.getInt(1);
			String mem_id = rs.getString(2);
			int pd_id = rs.getInt(3);
			int cnt = rs.getInt(4);
			String pay = rs.getString(5);
			String delivery = rs.getString(6);
			String info_name = rs.getString(7);
			String info_price = rs.getString(8);
			Date od_date = rs.getDate(9);
			OrdersVO tv = new OrdersVO(od_num, mem_id, pd_id, cnt, pay, delivery, info_name, info_price, od_date);
			tiarray.add(tv);
		}
		return tiarray;

	}// getAllInfo-end

	public boolean update_orders(int od_num, String delivery) {

		String sql = "UPDATE ORDERS SET DELIVERY=? WHERE OD_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, delivery);
			pstmt.setInt(2, od_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}

	public ArrayList<OrdersVO> Search_Orders(String type, String searchname) throws SQLException {
		ArrayList<OrdersVO> tiarray = new ArrayList<OrdersVO>();
		String sql = "SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
				   + "       TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999'), O.OD_DATE "
				   + "FROM ORDERS O " + "JOIN PD_INFO P "
				   + "ON P.INFO_NUM = O.PD_ID "
				   + "WHERE O.? like ?%"
				   + "ORDER BY O.OD_NUM DESC";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, type);
		pstmt.setString(2, searchname);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int od_num = rs.getInt(1);
			String mem_id = rs.getString(2);
			int pd_id = rs.getInt(3);
			int cnt = rs.getInt(4);
			String pay = rs.getString(5);
			String delivery = rs.getString(6);
			String info_name = rs.getString(7);
			String info_price = rs.getString(8);
			Date od_date = rs.getDate(9);
			OrdersVO tv = new OrdersVO(od_num, mem_id, pd_id, cnt, pay, delivery, info_name, info_price, od_date);
			tiarray.add(tv);
		}
		return tiarray;

	}// getAllInfo-end

	public int OrderInsert(String mem_id, int pd_id, int cnt, String delivery) {
		String sql = "INSERT INTO ORDERS(MEM_ID, PD_ID, CNT ,PAY, DELIVERY) VALUES (?, ?, ?, 'CARD', ?)";
		int rows_affected = 0;

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mem_id);
			pstmt.setInt(2, pd_id);
			pstmt.setInt(3, cnt);
			pstmt.setString(4, delivery);
			rows_affected = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert Exception");
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("insert Exception");
				}
			}
		}
		return rows_affected;
	}

	public ArrayList<OrdersVO> getList(int startRow, int endRow) {
		ArrayList<OrdersVO> ovlist = new ArrayList<OrdersVO>();

		String sql = "SELECT * "
				   + "FROM (SELECT t.*, ROWNUM AS rnum "
				   + "      FROM (SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
				   + "                   TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999,999'), O.OD_DATE "
				   + "            FROM ORDERS O JOIN PD_INFO P"
				   + "                 ON P.INFO_NUM = O.PD_ID "
				   + "                 ORDER BY O.OD_NUM DESC) t"
				   + "      WHERE ROWNUM <= ?)"
				   + "WHERE rnum >= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int od_num = rs.getInt(1);
				String mem_id = rs.getString(2);
				int pd_id = rs.getInt(3);
				int cnt = rs.getInt(4);
				String pay = rs.getString(5);
				String delivery = rs.getString(6);
				String info_name = rs.getString(7);
				String info_price = rs.getString(8);
				Date od_date = rs.getDate(9);
				OrdersVO ov = new OrdersVO(od_num, mem_id, pd_id, cnt, pay, delivery, info_name, info_price, od_date);
				ovlist.add(ov);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ovlist;
	}

	public int getTotalCount() {
		int total = 0;

		try {

			String sql = "SELECT COUNT(*) FROM ORDERS";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public ArrayList<OrdersVO> getFilterList(int startRow, int endRow, String filter, String search1, String search2) {
		ArrayList<OrdersVO> odvflist = new ArrayList<OrdersVO>();

		String sql = null;
		String search3 = null;
		int search4;
		String search11 = "%" + search1.toUpperCase() + "%";
		String search33 = null;
		Date search44 = null;

		if (filter.equals("DATE_PD")) {
			System.out.println("1 : " + search2);
			
			if (((String) search2).matches("-?\\d+")) {
				System.out.println("3 : " + search2);
				DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
				
				search4 = Integer.parseInt(search2);
				System.out.println("들어온 값 int로 형변환 : " + search4);
				
				String dateString = String.valueOf(search4);
				
				try {
					java.util.Date date = inputFormat.parse(dateString);
					search44 = new Date(date.getTime());
					System.out.println("int를 date로 형변환 : " + search44);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				search3 = (String) search2;
				search33 = !search3.isEmpty() ? "%" + search3.toUpperCase() + "%" : "%%";
				System.out.println("2 : " + search2);
			}

			sql = "SELECT * "
			    + "FROM (SELECT t.*, ROWNUM AS rnum "
				+ "    FROM (SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
				+ "                 TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999,999'), O.OD_DATE "
				+ "          FROM ORDERS O JOIN PD_INFO P "
				+ "          ON P.INFO_NUM = O.PD_ID "
				+ "          WHERE UPPER(O.MEM_ID) LIKE ? "
				+ "          AND (O.OD_DATE LIKE ? "
				+ "               OR UPPER(P.INFO_NAME) LIKE ?) "
				+ "          ORDER BY O.OD_NUM DESC) t "
				+ "    WHERE ROWNUM <= ?) "
				+ "WHERE rnum >= ?";
		//System.out.println(search22);
		} else if (filter.equals("MEM_ID")) {
			search33 = !search2.isEmpty() ? "%" + search2.toUpperCase() + "%" : "";
			sql = "SELECT * "
				+ "FROM (SELECT t.*, ROWNUM AS rnum "
				+ "    FROM (SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
				+ "                 TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999,999'), O.OD_DATE "
				+ "          FROM ORDERS O JOIN PD_INFO P "
				+ "          ON P.INFO_NUM = O.PD_ID "
				+ "          WHERE UPPER(O.MEM_ID) LIKE ? "
				+ "                AND (UPPER(O.MEM_ID) LIKE ? "
				+ "                     OR UPPER(O.MEM_ID) LIKE ?) "
				+ "          ORDER BY O.OD_NUM DESC) t "
				+ "    WHERE ROWNUM <= ?) "
				+ "WHERE rnum >= ?";
		} else {
			search33 = !search2.isEmpty() ? "%" + search2.toUpperCase() + "%" : "";
			sql = "SELECT * "
					+ "FROM (SELECT t.*, ROWNUM AS rnum "
					+ "    FROM (SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, "
					+ "                 TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999,999'), O.OD_DATE "
					+ "          FROM ORDERS O JOIN PD_INFO P "
					+ "          ON P.INFO_NUM = O.PD_ID "
					+ "          WHERE UPPER(O.MEM_ID) LIKE ? "
					+ "                AND (UPPER(" + filter + ") LIKE ? "
					+ "                     OR UPPER(" + filter + ") LIKE ?) "
					+ "          ORDER BY O.OD_NUM DESC) t "
					+ "    WHERE ROWNUM <= ?) "
					+ "WHERE rnum >= ?";
		}

		try {

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, search11);
			
			if (((String) search2).matches("-?\\d+")) {
				pstmt.setDate(2, search44);
				pstmt.setString(3, search33);
			} else {
				pstmt.setString(2, search33);
				pstmt.setString(3, search33);
			}
			pstmt.setInt(4, endRow);
			pstmt.setInt(5, startRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int od_num = rs.getInt(1);
				String mem_id = rs.getString(2);
				int pd_id = rs.getInt(3);
				int cnt = rs.getInt(4);
				String pay = rs.getString(5);
				String delivery = rs.getString(6);
				String info_name = rs.getString(7);
				String info_price = rs.getString(8);
				Date od_date = rs.getDate(9);
				OrdersVO ov = new OrdersVO(od_num, mem_id, pd_id, cnt, pay, delivery, info_name, info_price, od_date);
				odvflist.add(ov);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return odvflist;
	}

	public int getFilterTotal(String filter, String search1, String search2) throws SQLException {
		// TODO Auto-generated method stub
		int odvftotal = 0;
		String sql = null;
		String search3 = null;
		int search4;
		String search11 = "%" + search1.toUpperCase() + "%";
		String search33 = null;
		Date search44 = null;

		if (filter.equals("DATE_PD")) {
			System.out.println("1 : " + search2);
			
			if (((String) search2).matches("-?\\d+")) {
				System.out.println("3 : " + search2);
				DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
				
				search4 = Integer.parseInt(search2);
				System.out.println("들어온 값 int로 형변환 : " + search4);
				
				String dateString = String.valueOf(search4);
				
				try {
					java.util.Date date = inputFormat.parse(dateString);
					search44 = new Date(date.getTime());
					System.out.println("int를 date로 형변환 : " + search44);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				search3 = (String) search2;
				search33 = !search3.isEmpty() ? "%" + search3.toUpperCase() + "%" : "%%";
				System.out.println("2 : " + search2);
			}

			sql = "SELECT COUNT(*) "
				+ "FROM ORDERS O JOIN PD_INFO P "
				+ "ON P.INFO_NUM = O.PD_ID "
				+ "WHERE UPPER(O.MEM_ID) LIKE ? "
				+ "      AND (O.OD_DATE LIKE ? "
				+ "           OR UPPER(P.INFO_NAME) LIKE ?) "
				+ "ORDER BY O.OD_NUM DESC";
		} else if (filter.equals("MEM_ID")) {
			search3 = search2.toString();
			search33 = !search3.isEmpty() ? "%" + search3.toUpperCase() + "%" : "";
			sql = "SELECT COUNT(*) "
				+ "FROM ORDERS O JOIN PD_INFO P "
				+ "ON P.INFO_NUM = O.PD_ID "
				+ "WHERE UPPER(O.MEM_ID) LIKE ? "
				+ "      AND (UPPER(O.MEM_ID) LIKE ? "
				+ "           OR UPPER(O.MEM_ID) LIKE ?) "
				+ "ORDER BY O.OD_NUM DESC";
		} else {
			search33 = !search2.isEmpty() ? "%" + search2.toUpperCase() + "%" : "";
			sql = "SELECT COUNT(*) "
				+ "FROM ORDERS O JOIN PD_INFO P "
				+ "ON P.INFO_NUM = O.PD_ID "
				+ "WHERE UPPER(O.MEM_ID) LIKE ? "
				+ "      AND (UPPER(" + filter + ") LIKE ? "
				+ "           OR UPPER(" + filter + ") LIKE ?) "
				+ "ORDER BY O.OD_NUM DESC";
		}

		pstmt = con.prepareStatement(sql);

		pstmt.setString(1, search11);

		if (((String) search2).matches("-?\\d+")) {
			pstmt.setDate(2, search44);
			pstmt.setString(3, search33);
		} else {
			pstmt.setString(2, search33);
			pstmt.setString(3, search33);
		}

		rs = pstmt.executeQuery();

		if (rs.next()) {
			odvftotal = rs.getInt(1);
		}

		return odvftotal;
	}
	
	public ArrayList<OrdersVO> getFilterList_mngr(int startRow, int endRow,String filter, String search) {
        ArrayList<OrdersVO> odvflist = new ArrayList<OrdersVO>();
        
        String sql = "SELECT * " +
	             "FROM (" +
	             "    SELECT t.*, ROWNUM AS rnum " +
	             "    FROM (SELECT O.OD_NUM, O.MEM_ID, O.PD_ID, O.CNT, O.PAY, O.DELIVERY, P.INFO_NAME, " +
	             "               TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * O.CNT, 'FM999,999,999'),O.OD_DATE " +
	             "        FROM ORDERS O " +
	             "        JOIN PD_INFO P ON P.INFO_NUM = O.PD_ID " +
	            "           WHERE UPPER(" + filter + ") LIKE ? " +
	             "        ORDER BY O.OD_NUM DESC) t" +	
	             "    WHERE ROWNUM <= ?)" +	          
	             "WHERE rnum >= ?";
        
        String search1 = "%" + search.toUpperCase() + "%";
        
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, search1);
            pstmt.setInt(2, endRow);
            pstmt.setInt(3, startRow);
            rs = pstmt.executeQuery();

            while (rs.next()) {     
				int od_num = rs.getInt(1);
				String mem_id = rs.getString(2);
				int pd_id = rs.getInt(3);
				int cnt = rs.getInt(4);
				String pay = rs.getString(5);
				String delivery = rs.getString(6);
				String info_name = rs.getString(7);
				String info_price = rs.getString(8);
				Date od_date = rs.getDate(9);
				OrdersVO ov = new OrdersVO(od_num,mem_id,pd_id,cnt,pay,delivery,info_name,info_price,od_date); 
				odvflist.add(ov); 
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return odvflist;
    }
   
   public int getFilterTotal_mngr(String filter, String search) throws SQLException {
       // TODO Auto-generated method stub
       int odvftotal=0;

       try {
       String search1 = "%" + search.toUpperCase() + "%";
       String sql = "SELECT COUNT(*) "
    			  + "FROM ORDERS O, PD_INFO P "
    			  + "WHERE O.PD_ID = P.INFO_NUM "
    			  + "      AND UPPER(" + filter + ") LIKE ? "
    			  + "ORDER BY O.OD_NUM DESC";

       pstmt = con.prepareStatement(sql);

       pstmt.setString(1, search1);

       rs = pstmt.executeQuery();

       if(rs.next()){
            odvftotal = rs.getInt(1);
          }
        } catch (Exception e){
          e.printStackTrace();
        } 
        return odvftotal;
      }
   
   public OrdersVO getOrderInfo(String od_num1) {
		// TODO Auto-generated method stub
		OrdersVO ov1 = null;
		int od_num2 = Integer.parseInt(od_num1);
		
		String sql = "SELECT O.OD_NUM, O.OD_DATE, P.INFO_NAME, P.INFO_PRICE, P.KIND, P.COUNTRY, P.ALCOHOL, P.MADE_YEAR "
				   + "FROM ORDERS O, PD_INFO P "
				   + "WHERE O.PD_ID = P.INFO_NUM "
				   + "      AND O.OD_NUM = ? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, od_num2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int od_num = rs.getInt(1);
				Date od_date = rs.getDate(2);
				String info_name = rs.getString(3);
				String info_price = rs.getString(4);
				String kind = rs.getString(5);
				String country = rs.getString(6);
				String alcohol = rs.getString(7);
				int made_year = rs.getInt(8);

				ov1 = new OrdersVO(od_num, od_date, info_name, info_price, kind, country, alcohol, made_year);
			}
		} catch (SQLException e) {
			System.out.println("getOrderInfo Exception");
		}
		return ov1;
	}
   
   public ArrayList<OrdersVO> getReview(String id) {
		ArrayList<OrdersVO> od_rv = new ArrayList<OrdersVO>();
		
		String sql = "SELECT O.OD_NUM, O.DELIVERY, R.REV_NUM, R.COMMENTS, R.STARS "
				   + "FROM ORDERS O, REVIEW R "
				   + "WHERE O.OD_NUM = R.OD_NUM "
				   + "      AND R.MEM_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int od_num = rs.getInt(1);
				String delivery = rs.getString(2);
				int rev_num = rs.getInt(3);
				String comments = rs.getString(4);
				int stars = rs.getInt(5);
				
				OrdersVO ov = new OrdersVO(od_num, delivery, rev_num, comments, stars);
				od_rv.add(ov);
			}
			} catch	(SQLException e) {
	        e.printStackTrace();
		}
		
		return od_rv;
	}

}
