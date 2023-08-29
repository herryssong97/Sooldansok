package telInfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.BasketVO;

public class BasketDAO {
    private Connection con;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public BasketDAO() throws ClassNotFoundException, SQLException {
        con = new TelInfoDBConn().getConnection();
    }
    
    public ArrayList<BasketVO> getAllBasket(String mem_id2) throws SQLException {
        ArrayList<BasketVO> tiarray = new ArrayList<BasketVO>();
        String sql = "SELECT B.BAS_NUM, B.MEM_ID, B.PD_ID, B.CNT, P.INFO_PRICE, P.INFO_NAME, TO_CHAR(TO_NUMBER(REPLACE(P.info_price, ',', '')) * B.CNT, 'FM999,999,999') " +
                     "FROM BASKET B " +
                     "JOIN PD_INFO P ON P.INFO_NUM = B.PD_ID " +
                     "WHERE B.MEM_ID = ? " +
                     "ORDER BY B.BAS_NUM ASC";
        

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1,mem_id2);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int bas_num = rs.getInt(1);
            String mem_id = rs.getString(2);
            int pd_id = rs.getInt(3);
            int cnt = rs.getInt(4);
            String price = rs.getString(5);
            String pd_name = rs.getString(6);
            String totprice = rs.getString(7);
            BasketVO tv = new BasketVO(bas_num, mem_id, pd_id, price, cnt, pd_name, totprice);
            tiarray.add(tv);
        }
        return tiarray;
        
    }//getAllBasket-end
    
    
    public int deleteSelectedBasket(String mem_id, int[] selectedBasNums) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM BASKET WHERE MEM_ID = ? AND BAS_NUM = ?";
        int deletedRows = 0;
        
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, mem_id);
        
        for (int bas_num : selectedBasNums) {
            pstmt.setInt(2, bas_num);
            deletedRows += pstmt.executeUpdate();
        }

        pstmt.close();
        con.close();

        return deletedRows;
        
    }//deleteSelectedBasket-end
    
    
    public int addToBasket(String mem_id, int pd_id, int cnt) {
        String sql = "INSERT INTO BASKET(MEM_ID, PD_ID, CNT) VALUES (?, ?, ?)";
        int rows_affected = 0;

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, mem_id);
            pstmt.setInt(2, pd_id);
            pstmt.setInt(3, cnt);
            rows_affected = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                	System.out.println("insert Exception");
                }
            }
        }
        return rows_affected;
    }//addToBasket-end
    
    public int BasketDelete(int bas_num) {
		String sql="DELETE FROM BASKET WHERE BAS_NUM=?";
        int rows_affected = 0;

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, bas_num);
   
            rows_affected = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete Exception");
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("delete Exception");
                }
            }
        }
        return rows_affected;
    }
   
}

    



