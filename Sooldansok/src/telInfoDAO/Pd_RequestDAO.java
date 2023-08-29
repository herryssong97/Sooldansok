package telInfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.ClientVO;
import telInfoVO.Pd_RequestVO;

public class Pd_RequestDAO {

   private Connection con;
   PreparedStatement pstmt = null;
   ResultSet rs = null;

   public Pd_RequestDAO() throws ClassNotFoundException, SQLException {
      con = new TelInfoDBConn().getConnection();
   }

   public ArrayList<Pd_RequestVO> requestMyAll(String mem_id2) throws SQLException {
      ArrayList<Pd_RequestVO> reqArr = new ArrayList<Pd_RequestVO>();
      String sql = "SELECT * FROM PD_REQUEST WHERE MEM_ID = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, mem_id2);
      rs = pstmt.executeQuery();

      while (rs.next()) {
         int req_num = rs.getInt("req_num");
         String mem_id = rs.getString("mem_id");
         String req_name = rs.getString("req_name");
         String req_country = rs.getString("req_country");
         int req_made_year = rs.getInt("req_made_year");
         String req_comment = rs.getString("req_comment");
         String req_state = rs.getString("req_state");

         Pd_RequestVO rv1 = new Pd_RequestVO(req_num, mem_id, req_name, req_country, req_made_year, req_comment, req_state);
         reqArr.add(rv1);
      }
      return reqArr;
   }

   // 상품요청 1건 조회
   public Pd_RequestVO requestMyOne(int req_num2, String mem_id2) throws SQLException {
      Pd_RequestVO rv1 = null;
      String sql = "SELECT * FROM PD_REQUEST WHERE REQ_NUM = ? AND MEM_ID = ?";

      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, req_num2);
      pstmt.setString(2, mem_id2);
      rs = pstmt.executeQuery();

      if (rs.next()) {
         int req_num = rs.getInt(1);
         String mem_id = rs.getString(2);
         String req_name = rs.getString(3);
         String req_country = rs.getString(4);
         int req_made_year = rs.getInt(5);
         String req_comment = rs.getString(6);
         String req_state = rs.getString(7);

         rv1 = new Pd_RequestVO(req_num, mem_id, req_name, req_country, req_made_year, req_comment, req_state);
      } else {
         rv1 = null;
      }
      return rv1;
   }
   
// 상품요청 조회 (관리자 입장)
   public Pd_RequestVO requestGetOne(String req_num2) throws SQLException {

      Pd_RequestVO rv1 = null;
      String sql = "SELECT REQ_NUM, MEM_ID, REQ_NAME, REQ_COUNTRY, REQ_MADE_YEAR, REQ_COMMENT, REQ_STATE"
            + " FROM PD_REQUEST WHERE REQ_NUM = ?";

      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, req_num2);
      rs = pstmt.executeQuery();

      if (rs.next()) {
         int req_num = rs.getInt(1);
         String mem_id = rs.getString(2);
         String req_name = rs.getString(3);
         String req_country = rs.getString(4);
         int req_made_year = rs.getInt(5);
         String req_comment = rs.getString(6);
         String req_state = rs.getString(7);

         rv1 = new Pd_RequestVO(req_num, mem_id, req_name, req_country, req_made_year, req_comment, req_state);
      } else {
         rv1 = null;
      }
      return rv1;
   }

   // 상품요청 입력
   public boolean insertRequest(String mem_id, String req_name, String req_country, int req_made_year,
         String req_comment) {

      String sql = "insert into pd_request (mem_id, req_name, req_country, req_made_year, req_comment) "
            + "values (?, ?, ?, ?, ?)";

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, mem_id);
         pstmt.setString(2, req_name);
         pstmt.setString(3, req_country);
         pstmt.setInt(4, req_made_year);
         pstmt.setString(5, req_comment);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("Request Insert Exception");
         return false;
      }
      return true;
   }

   // 상품요청 수정
   public boolean updateRequest(String req_name, String req_country, int req_made_year, String req_comment,
         int req_num) throws SQLException {
      String sql = "UPDATE PD_REQUEST SET REQ_NAME = ?, REQ_COUNTRY = ?, REQ_MADE_YEAR = ?,"
            + " REQ_COMMENT = ? WHERE REQ_NUM = ?";

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, req_name);
         pstmt.setString(2, req_country);
         pstmt.setInt(3, req_made_year);
         pstmt.setString(4, req_comment);
         pstmt.setInt(5, req_num);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("Request Update Exception");
         return false;
      }
      return true;
   }

   // 상품요청 삭제
   public boolean deleteRequest(int req_num) throws SQLException {

      String sql = "DELETE FROM PD_REQUEST WHERE REQ_NUM = ?";

      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, req_num);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("Request Delete Exception");
         return false;
      }
      return true;
   }
   
   public ArrayList<Pd_RequestVO> getList(int startRow, int endRow) {
       ArrayList<Pd_RequestVO> reqlist = new ArrayList<Pd_RequestVO>();
       
       String sql = "SELECT * " +
                    "FROM (SELECT t.*, ROWNUM AS rnum " +
                    "      FROM (SELECT * " +
                    "            FROM PD_REQUEST " +
                    "            ORDER BY REQ_NUM DESC) t " +
                    "      WHERE ROWNUM <= ?) " +
                    "WHERE rnum >= ?";

       try {
           pstmt = con.prepareStatement(sql);
           pstmt.setInt(1, endRow);
           pstmt.setInt(2, startRow); 
           rs = pstmt.executeQuery();

           while (rs.next()) {
        	   int req_num = rs.getInt("req_num");
               String mem_id = rs.getString("mem_id");
               String req_name = rs.getString("req_name");
               String req_country = rs.getString("req_country");
               int req_made_year = rs.getInt("req_made_year");
               String req_comment = rs.getString("req_comment");
               String req_state = rs.getString("req_state");

               Pd_RequestVO rv1 = new Pd_RequestVO(req_num, mem_id, req_name, req_country, req_made_year, req_comment, req_state);

            
            reqlist.add(rv1);
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
       return reqlist;
   }
      
     public int getTotalCount(){
       int reqtotal = 0;
        
       try {
      
         String sql = "SELECT COUNT(*) FROM PD_REQUEST";
         pstmt = con.prepareStatement(sql);
          
         rs = pstmt.executeQuery();
         if(rs.next()){
           reqtotal = rs.getInt(1);
         }
       } catch (Exception e){
         e.printStackTrace();
       } 
       return reqtotal;
     }
     
     public boolean update_request (int req_num,String req_state) {
 		
 		String sql="UPDATE PD_REQUEST SET REQ_STATE=? WHERE REQ_NUM=?";
 		try {
 			pstmt=con.prepareStatement(sql);
 			pstmt.setString(1, req_state);
 			pstmt.setInt(2, req_num);
 			pstmt.executeUpdate();
 		} catch (SQLException e) {
 			System.out.println("Update Exception");
 			return false;
 		}
 		return true;
 	}
     
     public ArrayList<Pd_RequestVO> getFilterList(int startRow, int endRow,String filter, String search1, String search2) {
         ArrayList<Pd_RequestVO> reqvflist = new ArrayList<Pd_RequestVO>();
         
         String sql = null;
 		String search11 = null;
 		String search22 = "%" + search2.toUpperCase() + "%";

 		if (filter.equals("TITLE_COMMENT")) {
 		search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
 			sql =  "SELECT * " +
                      "FROM (SELECT t.*, ROWNUM AS rnum " +
                      "      FROM (SELECT * " +
                      "            FROM PD_REQUEST " +
  					"			 WHERE UPPER(MEM_ID) like ? " +
  					"			 AND (UPPER(REQ_NAME) like ? " +
  					"			 OR UPPER(REQ_COMMENT) like ? ) " +   
                      "            ORDER BY REQ_NUM DESC) t " +
                      "      WHERE ROWNUM <= ?) " +
                      "WHERE rnum >= ?";
		} else if (filter.equals("MEM_ID")) {
			search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
			sql = "SELECT * " +
					"FROM (SELECT t.*, ROWNUM AS rnum " +
					"      FROM (SELECT * " +
					"            FROM PD_REQUEST " +
					"			 WHERE UPPER(MEM_ID) like ? " +
					"			 OR UPPER(MEM_ID) like ? " +
					"			 OR UPPER(MEM_ID) like ? " +
					"            ORDER BY REQ_NUM DESC) t " +
					"      WHERE ROWNUM <= ?) " +
					"WHERE rnum >= ?";
		
		} else if (filter.equals("REQ_STATE")) {
			search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
			sql = "SELECT * " +
					"FROM (SELECT t.*, ROWNUM AS rnum " +
					"      FROM (SELECT * " +
					"            FROM PD_REQUEST " +
					"			 WHERE UPPER(MEM_ID) like ? " +
					"		 AND (UPPER(REQ_STATE) like ? " +
					"		 AND UPPER(REQ_STATE) like ?) " +
					"            ORDER BY REQ_NUM DESC) t " +
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

             while (rs.next()) {
          	   int req_num = rs.getInt("req_num");
                 String mem_id = rs.getString("mem_id");
                 String req_name = rs.getString("req_name");
                 String req_country = rs.getString("req_country");
                 int req_made_year = rs.getInt("req_made_year");
                 String req_comment = rs.getString("req_comment");
                 String req_state = rs.getString("req_state");

                 Pd_RequestVO rv1 = new Pd_RequestVO(req_num, mem_id, req_name, req_country, req_made_year, req_comment, req_state);

              
              reqvflist.add(rv1);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
         return reqvflist;
     }
    
    public int getFilterTotal(String filter, String search1, String search2) throws SQLException {
        // TODO Auto-generated method stub
        int reqvftotal=0;
		String search11 = null;
		String search22 = "%" + search2.toUpperCase() + "%";
		String sql = null;

		try {
			if (filter.equals("TITLE_COMMENT")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "%%";
				sql = "SELECT COUNT(*) "
						+ "FROM PD_REQUEST "
						+ "WHERE UPPER(MEM_ID) like ? "
						+ "		 AND (UPPER(REQ_NAME) like ? "
						+ "		 OR UPPER(REQ_COMMENT) like ?) "
						+ "ORDER BY REQ_NUM";
			} else if (filter.equals("MEM_ID")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
				sql = "SELECT COUNT(*) "
					+ "FROM PD_REQUEST "
					+ "WHERE UPPER(MEM_ID) like ? "
					+ "		 OR UPPER(MEM_ID) like ? "
					+ "		 OR UPPER(MEM_ID) like ? "
					+ "ORDER BY REQ_NUM";
			} else if (filter.equals("REQ_STATE")) {
				search11 = !search1.isEmpty() ? "%" + search1.toUpperCase() + "%" : "";
				sql = "SELECT COUNT(*) "
					+ "FROM PD_REQUEST "
					+ "WHERE UPPER(MEM_ID) like ? "
					+ "		 AND (UPPER(REQ_STATE) like ? "
					+ "		 AND UPPER(REQ_STATE) like ?) "
					+ "ORDER BY REQ_NUM";
			}
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, search11);
			pstmt.setString(2, search22);
			pstmt.setString(3, search22);

        rs = pstmt.executeQuery();

        if(rs.next()){
             reqvftotal = rs.getInt(1);
           }
         } catch (Exception e){
           e.printStackTrace();
         } 
         return reqvftotal;
       } 
     
}