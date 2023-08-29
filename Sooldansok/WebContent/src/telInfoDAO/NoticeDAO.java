package telInfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.NoticeVO;


public class NoticeDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public NoticeDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();
		
	}
	
	public ArrayList<NoticeVO> getAllNotice() throws SQLException {
		ArrayList<NoticeVO> nvarray = new ArrayList<NoticeVO>();
		String sql = "SELECT * FROM NOTICE ORDER BY N_NUM DESC";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();  
		while (rs.next()) {     
			int n_num = rs.getInt("N_NUM");
			String n_title = rs.getString("N_TITLE");
			String n_comment = rs.getString("N_COMMENT");
			String mem_id = rs.getString("MEM_ID");
			Date n_date = rs.getDate("N_DATE");
			NoticeVO nv = new NoticeVO(n_num,n_title,n_comment,mem_id,n_date); 
			nvarray.add(nv); 
		}
		return nvarray; 
		
	}// getAllInfo-end
	
	
	public NoticeVO getNotice(String n_num1) throws SQLException {
		NoticeVO nv = null;
		String sql = "SELECT * FROM  NOTICE WHERE N_NUM = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, n_num1);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			int n_num = rs.getInt(1);
			String n_title = rs.getString(2);
			String n_comment = rs.getString(3);
			String mem_id = rs.getString(4);
			Date n_date = rs.getDate(5);
			nv = new NoticeVO(n_num,n_title,n_comment,mem_id,n_date); 
		}else {
			nv = null;
		}
		return nv;
	}
	
	public boolean insert_notice(String title, String comment) {
		String sql= "insert into NOTICE(N_TITLE,N_COMMENT,MEM_ID) values (?,?,'admin')";
			
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, comment);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				
				System.out.println("insert Exception");
				return false;
			}
		return true;
	}

	public boolean update_notice (String n_title, String n_comment,int n_num) {
	
		String sql="UPDATE NOTICE SET N_TITLE=? , N_COMMENT=?, N_DATE=SYSDATE  WHERE N_NUM=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, n_title);
			pstmt.setString(2, n_comment);
			pstmt.setInt(3, n_num);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}
	
	public boolean delete_notice(int n_num) {
		String sql="DELETE FROM NOTICE WHERE N_NUM=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, n_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}
	
	
	public ArrayList<NoticeVO> getList(int startRow, int endRow) {
	    ArrayList<NoticeVO> nvlist = new ArrayList<NoticeVO>();
	    
	    String sql = "SELECT * " +
	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
	                 "      FROM (SELECT * " +
	                 "            FROM NOTICE " +
	                 "            ORDER BY N_NUM DESC) t " +
	                 "      WHERE ROWNUM <= ?) " +
	                 "WHERE rnum >= ?";

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, endRow);
	        pstmt.setInt(2, startRow); 
	        rs = pstmt.executeQuery();
	        while (rs.next()) {     
				int n_num = rs.getInt("N_NUM");
				String n_title = rs.getString("N_TITLE");
				String n_comment = rs.getString("N_COMMENT");
				String mem_id = rs.getString("MEM_ID");
				Date n_date = rs.getDate("N_DATE");
				NoticeVO nv = new NoticeVO(n_num,n_title,n_comment,mem_id,n_date); 
				nvlist.add(nv); 
			}

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return nvlist;
	}
	   
	  public int getTotalCount(){
	    int nvtotal = 0;
	     
	    try {
	   
	      String sql = "SELECT COUNT(*) FROM NOTICE";
	      pstmt = con.prepareStatement(sql);
	       
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        nvtotal = rs.getInt(1);
	      }
	    } catch (Exception e){
	      e.printStackTrace();
	    } 
	    return nvtotal;
	  }
	  
	  public ArrayList<NoticeVO> getFilterList(int startRow, int endRow, String search) {
          ArrayList<NoticeVO> nvflist = new ArrayList<NoticeVO>();
          
          String sql = "SELECT * " +
					"FROM (SELECT t.*, ROWNUM AS rnum " +
					"      FROM (SELECT * " +
					"            FROM NOTICE " +					
					"			 WHERE (UPPER(N_TITLE) like ? " +
					"			 OR UPPER(N_COMMENT) like ? )" +
					"            ORDER BY N_NUM DESC) t " +
					"      WHERE ROWNUM <= ?) " +
					"WHERE rnum >= ?";
          String search1 = "%" + search.toUpperCase() + "%";
          
          try {
              pstmt = con.prepareStatement(sql);
              pstmt.setString(1, search1);
              pstmt.setString(2, search1);
              pstmt.setInt(3, endRow);
              pstmt.setInt(4, startRow);
              rs = pstmt.executeQuery();

              while (rs.next()) {     
  				int n_num = rs.getInt("N_NUM");
  				String n_title = rs.getString("N_TITLE");
  				String n_comment = rs.getString("N_COMMENT");
  				String mem_id = rs.getString("MEM_ID");
  				Date n_date = rs.getDate("N_DATE");
  				NoticeVO nv = new NoticeVO(n_num,n_title,n_comment,mem_id,n_date); 
  				nvflist.add(nv); 
  			}

          } catch (Exception e) {
              e.printStackTrace();
          }
          return nvflist;
      }
     
     public int getFilterTotal(String search) throws SQLException {
         // TODO Auto-generated method stub
         int nvftotal=0;

         try {
         String sql = "SELECT COUNT(*) FROM NOTICE WHERE UPPER(N_TITLE) like ? OR UPPER(N_COMMENT) like ? ORDER BY N_NUM";
         String search1 = "%" + search.toUpperCase() + "%";

         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, search1);
         pstmt.setString(2, search1);
         
         rs = pstmt.executeQuery();

         if(rs.next()){
              nvftotal = rs.getInt(1);
            }
          } catch (Exception e){
            e.printStackTrace();
          } 
          return nvftotal;
        }
}
