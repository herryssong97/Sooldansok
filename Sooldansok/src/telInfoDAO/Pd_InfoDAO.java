package telInfoDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;

import telInfoVO.Pd_InfoVO;

public class Pd_InfoDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Pd_InfoDAO() throws ClassNotFoundException, SQLException {
		con = new TelInfoDBConn().getConnection();
		
	}
	
	public ArrayList<Pd_InfoVO> getAllPd_Info() throws SQLException {
		ArrayList<Pd_InfoVO> tiarray = new ArrayList<Pd_InfoVO>();
		String sql = "SELECT * FROM Pd_Info ORDER BY info_num";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();  
		while (rs.next()) {     
			int info_num = rs.getInt(1);
			String info_name = rs.getString(2);
			String info_price = rs.getString(3);
			String kind = rs.getString(4);
			String country = rs.getString(5);
			int capacity = rs.getInt(6);
			String alcohol = rs.getString(7);
			int made_year = rs.getInt(8);
			int stock = rs.getInt(9);
			String img = rs.getString(10);
			Pd_InfoVO tv = new Pd_InfoVO(info_num,info_name,info_price,kind,country,capacity,alcohol,made_year,stock,img); 
			tiarray.add(tv); 
		}
		return tiarray; 
		
	}// getAllInfo-end
	
	public Pd_InfoVO getPd_Info(String info_num1) throws SQLException {
		Pd_InfoVO tv = null;
		String sql = "SELECT * FROM  Pd_Info WHERE INFO_NUM = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, info_num1);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			int info_num = rs.getInt(1);
			String info_name = rs.getString(2);
			String info_price = rs.getString(3);
			String kind = rs.getString(4);
			String country = rs.getString(5);
			int capacity = rs.getInt(6);
			String alcohol = rs.getString(7);
			int made_year = rs.getInt(8);
			int stock = rs.getInt(9);
			String img = rs.getString(10);
			tv = new Pd_InfoVO(info_num,info_name,info_price,kind,country,capacity,alcohol,made_year,stock,img);
		}else {
			tv = null;
		}
		return tv;
	}
	
	public boolean insert_product(String info_name,String info_price,String kind,String country,int capacity,
								String alcohol,int made_year,int stock,String img) {
		String sql= "INSERT INTO PD_INFO(INFO_NAME,INFO_PRICE,KIND,COUNTRY,CAPACITY,ALCOHOL,MADE_YEAR,STOCK,IMG) VALUES(?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,info_name);
				pstmt.setString(2,info_price);
				pstmt.setString(3,kind);
				pstmt.setString(4,country);
				pstmt.setInt(5,capacity);
				pstmt.setString(6,alcohol);
				pstmt.setInt(7,made_year);
				pstmt.setInt(8,stock);
				pstmt.setString(9,img);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				
				System.out.println("insert Exception");
				return false;
			}
		return true;
	}
	
	public boolean delete_pd_info(int info_num) {
		String sql="DELETE FROM PD_INFO WHERE INFO_NUM=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, info_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete Exception");
			return false;
		}
		return true;
	}
	
	public boolean update_pd_Info (String info_name,String info_price,String kind,String country,int capacity,
								String alcohol,int made_year,int stock,int info_num) {
		
		String sql = "UPDATE PD_INFO SET INFO_NAME=?, INFO_PRICE=?, KIND=?, COUNTRY=?, CAPACITY=?, ALCOHOL=?," +
	                 " MADE_YEAR=?, STOCK=? WHERE INFO_NUM=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, info_name);
			pstmt.setString(2, info_price);
			pstmt.setString(3, kind);
			pstmt.setString(4, country);
			pstmt.setInt(5, capacity);
			pstmt.setString(6, alcohol);
			pstmt.setInt(7, made_year);
			pstmt.setInt(8, stock);
			pstmt.setInt(9, info_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update Exception");
			return false;
		}
		return true;
	}
	
	public boolean imgchange_pd_Info (String img,int info_num) {

			String sql = "UPDATE PD_INFO SET IMG=? WHERE INFO_NUM=?";
			try {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, img);
					pstmt.setInt(2, info_num);
					pstmt.executeUpdate();
				} catch (SQLException e) {
				System.out.println("Update Exception");
				return false;
			}
			return true;
		}
	
	public int ProductUpdate(int cnt,int pd_id) {
        String sql = "UPDATE PD_INFO SET STOCK=STOCK-? WHERE INFO_NUM=?";
        int rows_affected = 0;

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, cnt);
            pstmt.setInt(2, pd_id);
           
            rows_affected = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update Exception");
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("update Exception");
                }
            }
        }
        return rows_affected;
    }
	
	public ArrayList<Pd_InfoVO> getList(int startRow, int endRow) {
	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
	    
	    String sql = "SELECT * " +
	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
	                 "      FROM (SELECT * " +
	                 "            FROM PD_INFO " +
	                 "            ORDER BY INFO_NUM DESC) t " +
	                 "      WHERE ROWNUM <= ?) " +
	                 "WHERE rnum >= ?";

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, endRow);
	        pstmt.setInt(2, startRow); 
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int info_num = rs.getInt(1);
	            String info_name = rs.getString(2);
	            String info_price = rs.getString(3);
	            String kind = rs.getString(4);
	            String country = rs.getString(5);
	            int capacity = rs.getInt(6);
	            String alcohol = rs.getString(7);
	            int made_year = rs.getInt(8);
	            int stock = rs.getInt(9);
	            String img = rs.getString(10);
	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
	            pdlist.add(pdv);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return pdlist;
	}
	   
	  public int getTotalCount(){
	    int pdtotal = 0;
	     
	    try {
	   
	      String sql = "SELECT COUNT(*) FROM PD_INFO";
	      pstmt = con.prepareStatement(sql);
	       
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	    	pdtotal = rs.getInt(1);
	      }
	    } catch (Exception e){
	      e.printStackTrace();
	    } 
	    return pdtotal;
	  }
	  
	  public ArrayList<Pd_InfoVO> getFilterList(int startRow, int endRow,String filter, String search) {
          ArrayList<Pd_InfoVO> pdvflist = new ArrayList<Pd_InfoVO>();
          
          String sql = "SELECT * " +
                       "FROM (SELECT t.*, ROWNUM AS rnum " +
                       "      FROM (SELECT * " +
                       "            FROM PD_INFO " +
                       "           WHERE UPPER(" + filter + ") LIKE ? " +   
                       "            ORDER BY INFO_NUM) t " +
                       "      WHERE ROWNUM <= ?) " +
                       "WHERE rnum >= ?";
          String search1 = "%" + search.toUpperCase() + "%";
          
          try {
              pstmt = con.prepareStatement(sql);
              pstmt.setString(1, search1);
              pstmt.setInt(2, endRow);
              pstmt.setInt(3, startRow);
              rs = pstmt.executeQuery();

              while (rs.next()) {
  	            int info_num = rs.getInt(1);
  	            String info_name = rs.getString(2);
  	            String info_price = rs.getString(3);
  	            String kind = rs.getString(4);
  	            String country = rs.getString(5);
  	            int capacity = rs.getInt(6);
  	            String alcohol = rs.getString(7);
  	            int made_year = rs.getInt(8);
  	            int stock = rs.getInt(9);
  	            String img = rs.getString(10);
  	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
  	          pdvflist.add(pdv);
  	        }

          } catch (Exception e) {
              e.printStackTrace();
          }
          return pdvflist;
      }
     
     public int getFilterTotal(String filter, String search) throws SQLException {
         // TODO Auto-generated method stub
         int pdvftotal=0;

         try {
         String sql = "SELECT COUNT(*) FROM PD_INFO WHERE UPPER(" + filter + ") LIKE ? ORDER BY INFO_NUM";
         String search1 = "%" + search.toUpperCase() + "%";

         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, search1);

         rs = pstmt.executeQuery();

         if(rs.next()){
              pdvftotal = rs.getInt(1);
            }
          } catch (Exception e){
            e.printStackTrace();
          } 
          return pdvftotal;
        }
     
     public ArrayList<Pd_InfoVO> getFranceList(int startRow, int endRow) {
 	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
 	    
 	    String sql = "SELECT * " +
 	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
 	                 "      FROM (SELECT * " +
 	                 "            FROM PD_INFO " +
 	                 "			  WHERE COUNTRY='FRANCE' " +
 	                 "            ORDER BY INFO_NUM) t " +
 	                 "      WHERE ROWNUM <= ?) " +
 	                 "WHERE rnum >= ?";

 	    try {
 	        pstmt = con.prepareStatement(sql);
 	        pstmt.setInt(1, endRow);
 	        pstmt.setInt(2, startRow); 
 	        rs = pstmt.executeQuery();

 	        while (rs.next()) {
 	            int info_num = rs.getInt(1);
 	            String info_name = rs.getString(2);
 	            String info_price = rs.getString(3);
 	            String kind = rs.getString(4);
 	            String country = rs.getString(5);
 	            int capacity = rs.getInt(6);
 	            String alcohol = rs.getString(7);
 	            int made_year = rs.getInt(8);
 	            int stock = rs.getInt(9);
 	            String img = rs.getString(10);
 	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
 	            pdlist.add(pdv);
 	        }

 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }
 	    return pdlist;
 	}
 	   
 	  public int getFranceTotalCount(){
 	    int pdtotal = 0;
 	     
 	    try {
 	   
 	      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE COUNTRY='FRANCE'";
 	      pstmt = con.prepareStatement(sql);
 	       
 	      rs = pstmt.executeQuery();
 	      if(rs.next()){
 	    	pdtotal = rs.getInt(1);
 	      }
 	    } catch (Exception e){
 	      e.printStackTrace();
 	    } 
 	    return pdtotal;
 	  }
 	  
 	 public ArrayList<Pd_InfoVO> getChileList(int startRow, int endRow) {
  	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
  	    
  	    String sql = "SELECT * " +
  	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
  	                 "      FROM (SELECT * " +
  	                 "            FROM PD_INFO " +
  	                 "			  WHERE COUNTRY='CHILE' " +
  	                 "            ORDER BY INFO_NUM) t " +
  	                 "      WHERE ROWNUM <= ?) " +
  	                 "WHERE rnum >= ?";

  	    try {
  	        pstmt = con.prepareStatement(sql);
  	        pstmt.setInt(1, endRow);
  	        pstmt.setInt(2, startRow); 
  	        rs = pstmt.executeQuery();

  	        while (rs.next()) {
  	            int info_num = rs.getInt(1);
  	            String info_name = rs.getString(2);
  	            String info_price = rs.getString(3);
  	            String kind = rs.getString(4);
  	            String country = rs.getString(5);
  	            int capacity = rs.getInt(6);
  	            String alcohol = rs.getString(7);
  	            int made_year = rs.getInt(8);
  	            int stock = rs.getInt(9);
  	            String img = rs.getString(10);
  	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
  	            pdlist.add(pdv);
  	        }

  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    return pdlist;
  	}
  	   
  	  public int getChileTotalCount(){
  	    int pdtotal = 0;
  	     
  	    try {
  	   
  	      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE COUNTRY='CHILE'";
  	      pstmt = con.prepareStatement(sql);
  	       
  	      rs = pstmt.executeQuery();
  	      if(rs.next()){
  	    	pdtotal = rs.getInt(1);
  	      }
  	    } catch (Exception e){
  	      e.printStackTrace();
  	    } 
  	    return pdtotal;
  	  }
  	  
  	public ArrayList<Pd_InfoVO> getItalyList(int startRow, int endRow) {
  	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
  	    
  	    String sql = "SELECT * " +
  	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
  	                 "      FROM (SELECT * " +
  	                 "            FROM PD_INFO " +
  	                 "			  WHERE COUNTRY='ITALY' " +
  	                 "            ORDER BY INFO_NUM) t " +
  	                 "      WHERE ROWNUM <= ?) " +
  	                 "WHERE rnum >= ?";

  	    try {
  	        pstmt = con.prepareStatement(sql);
  	        pstmt.setInt(1, endRow);
  	        pstmt.setInt(2, startRow); 
  	        rs = pstmt.executeQuery();

  	        while (rs.next()) {
  	            int info_num = rs.getInt(1);
  	            String info_name = rs.getString(2);
  	            String info_price = rs.getString(3);
  	            String kind = rs.getString(4);
  	            String country = rs.getString(5);
  	            int capacity = rs.getInt(6);
  	            String alcohol = rs.getString(7);
  	            int made_year = rs.getInt(8);
  	            int stock = rs.getInt(9);
  	            String img = rs.getString(10);
  	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
  	            pdlist.add(pdv);
  	        }

  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    return pdlist;
  	}
  	   
  	  public int getItalyTotalCount(){
  	    int pdtotal = 0;
  	     
  	    try {
  	   
  	      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE COUNTRY='ITALY'";
  	      pstmt = con.prepareStatement(sql);
  	       
  	      rs = pstmt.executeQuery();
  	      if(rs.next()){
  	    	pdtotal = rs.getInt(1);
  	      }
  	    } catch (Exception e){
  	      e.printStackTrace();
  	    } 
  	    return pdtotal;
  	  }
  	  
  	public ArrayList<Pd_InfoVO> getSpainList(int startRow, int endRow) {
  	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
  	    
  	    String sql = "SELECT * " +
  	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
  	                 "      FROM (SELECT * " +
  	                 "            FROM PD_INFO " +
  	                 "			  WHERE COUNTRY='SPAIN' " +
  	                 "            ORDER BY INFO_NUM) t " +
  	                 "      WHERE ROWNUM <= ?) " +
  	                 "WHERE rnum >= ?";

  	    try {
  	        pstmt = con.prepareStatement(sql);
  	        pstmt.setInt(1, endRow);
  	        pstmt.setInt(2, startRow); 
  	        rs = pstmt.executeQuery();

  	        while (rs.next()) {
  	            int info_num = rs.getInt(1);
  	            String info_name = rs.getString(2);
  	            String info_price = rs.getString(3);
  	            String kind = rs.getString(4);
  	            String country = rs.getString(5);
  	            int capacity = rs.getInt(6);
  	            String alcohol = rs.getString(7);
  	            int made_year = rs.getInt(8);
  	            int stock = rs.getInt(9);
  	            String img = rs.getString(10);
  	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
  	            pdlist.add(pdv);
  	        }

  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    return pdlist;
  	}
  	   
  	  public int getSpainTotalCount(){
  	    int pdtotal = 0;
  	     
  	    try {
  	   
  	      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE COUNTRY='SPAIN'";
  	      pstmt = con.prepareStatement(sql);
  	       
  	      rs = pstmt.executeQuery();
  	      if(rs.next()){
  	    	pdtotal = rs.getInt(1);
  	      }
  	    } catch (Exception e){
  	      e.printStackTrace();
  	    } 
  	    return pdtotal;
  	  }
  	  
  	public ArrayList<Pd_InfoVO> getSearchAll(int startRow, int endRow,String search) {
	    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
	    
	    String sql = "SELECT * " +
	                 "FROM (SELECT t.*, ROWNUM AS rnum " +
	                 "      FROM (SELECT * " +
	                 "            FROM PD_INFO " +
	                 "			  WHERE INFO_NAME like ? "+
	                 "            ORDER BY INFO_NUM) t " +
	                 "      WHERE ROWNUM <= ?) " +
	                 "WHERE rnum >= ?";

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + search + "%");
	        pstmt.setInt(2, endRow);
	        pstmt.setInt(3, startRow); 
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int info_num = rs.getInt(1);
	            String info_name = rs.getString(2);
	            String info_price = rs.getString(3);
	            String kind = rs.getString(4);
	            String country = rs.getString(5);
	            int capacity = rs.getInt(6);
	            String alcohol = rs.getString(7);
	            int made_year = rs.getInt(8);
	            int stock = rs.getInt(9);
	            String img = rs.getString(10);
	            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
	            pdlist.add(pdv);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return pdlist;
	}
	   
	  public int getSearchTotalCount(String search){
	    int pdtotal = 0;
	     
	    try {
	   
	      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE INFO_NAME like ?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, "%" + search + "%"); 
	      rs = pstmt.executeQuery();
	      
	      if(rs.next()){
	    	pdtotal = rs.getInt(1);
	      }
	    } catch (Exception e){
	      e.printStackTrace();
	    } 
	    return pdtotal;
	  }
	  
	  
	  public ArrayList<Pd_InfoVO> getSearchFrance(int startRow, int endRow,String search) {
		    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
		    
		    String sql = "SELECT * " +
		                 "FROM (SELECT t.*, ROWNUM AS rnum " +
		                 "      FROM (SELECT * " +
		                 "            FROM PD_INFO " +
		                 "			  WHERE INFO_NAME like ? AND COUNTRY='FRANCE' "+
		                 "            ORDER BY INFO_NUM) t " +
		                 "      WHERE ROWNUM <= ?) " +
		                 "WHERE rnum >= ?";

		    try {
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, "%" + search + "%");
		        pstmt.setInt(2, endRow);
		        pstmt.setInt(3, startRow); 
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            int info_num = rs.getInt(1);
		            String info_name = rs.getString(2);
		            String info_price = rs.getString(3);
		            String kind = rs.getString(4);
		            String country = rs.getString(5);
		            int capacity = rs.getInt(6);
		            String alcohol = rs.getString(7);
		            int made_year = rs.getInt(8);
		            int stock = rs.getInt(9);
		            String img = rs.getString(10);
		            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
		            pdlist.add(pdv);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return pdlist;
		}
		   
		  public int getSearchFranceCount(String search){
		    int pdtotal = 0;
		     
		    try {
		   
		      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE INFO_NAME like ? AND COUNTRY='FRANCE'";
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, "%" + search + "%"); 
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()){
		    	pdtotal = rs.getInt(1);
		      }
		    } catch (Exception e){
		      e.printStackTrace();
		    } 
		    return pdtotal;
		  }
		  
		  public ArrayList<Pd_InfoVO> getSearchItaly(int startRow, int endRow,String search) {
			    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
			    
			    String sql = "SELECT * " +
			                 "FROM (SELECT t.*, ROWNUM AS rnum " +
			                 "      FROM (SELECT * " +
			                 "            FROM PD_INFO " +
			                 "			  WHERE INFO_NAME like ? AND COUNTRY='ITALY' "+
			                 "            ORDER BY INFO_NUM) t " +
			                 "      WHERE ROWNUM <= ?) " +
			                 "WHERE rnum >= ?";

			    try {
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, "%" + search + "%");
			        pstmt.setInt(2, endRow);
			        pstmt.setInt(3, startRow); 
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            int info_num = rs.getInt(1);
			            String info_name = rs.getString(2);
			            String info_price = rs.getString(3);
			            String kind = rs.getString(4);
			            String country = rs.getString(5);
			            int capacity = rs.getInt(6);
			            String alcohol = rs.getString(7);
			            int made_year = rs.getInt(8);
			            int stock = rs.getInt(9);
			            String img = rs.getString(10);
			            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
			            pdlist.add(pdv);
			        }

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			    return pdlist;
			}
			   
			  public int getSearchItalyCount(String search){
			    int pdtotal = 0;
			     
			    try {
			   
			      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE INFO_NAME like ? AND COUNTRY='ITALY'";
			      pstmt = con.prepareStatement(sql);
			      pstmt.setString(1, "%" + search + "%"); 
			      rs = pstmt.executeQuery();
			      
			      if(rs.next()){
			    	pdtotal = rs.getInt(1);
			      }
			    } catch (Exception e){
			      e.printStackTrace();
			    } 
			    return pdtotal;
			  }
			  
			  public ArrayList<Pd_InfoVO> getSearchChile(int startRow, int endRow,String search) {
				    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
				    
				    String sql = "SELECT * " +
				                 "FROM (SELECT t.*, ROWNUM AS rnum " +
				                 "      FROM (SELECT * " +
				                 "            FROM PD_INFO " +
				                 "			  WHERE INFO_NAME like ? AND COUNTRY='CHILE' "+
				                 "            ORDER BY INFO_NUM) t " +
				                 "      WHERE ROWNUM <= ?) " +
				                 "WHERE rnum >= ?";

				    try {
				        pstmt = con.prepareStatement(sql);
				        pstmt.setString(1, "%" + search + "%");
				        pstmt.setInt(2, endRow);
				        pstmt.setInt(3, startRow); 
				        rs = pstmt.executeQuery();

				        while (rs.next()) {
				            int info_num = rs.getInt(1);
				            String info_name = rs.getString(2);
				            String info_price = rs.getString(3);
				            String kind = rs.getString(4);
				            String country = rs.getString(5);
				            int capacity = rs.getInt(6);
				            String alcohol = rs.getString(7);
				            int made_year = rs.getInt(8);
				            int stock = rs.getInt(9);
				            String img = rs.getString(10);
				            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
				            pdlist.add(pdv);
				        }

				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				    return pdlist;
				}
				   
				  public int getSearchChileCount(String search){
				    int pdtotal = 0;
				     
				    try {
				   
				      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE INFO_NAME like ? AND COUNTRY='CHILE'";
				      pstmt = con.prepareStatement(sql);
				      pstmt.setString(1, "%" + search + "%"); 
				      rs = pstmt.executeQuery();
				      
				      if(rs.next()){
				    	pdtotal = rs.getInt(1);
				      }
				    } catch (Exception e){
				      e.printStackTrace();
				    } 
				    return pdtotal;
				  } 
				  
				  public ArrayList<Pd_InfoVO> getSearchSpain(int startRow, int endRow,String search) {
					    ArrayList<Pd_InfoVO> pdlist = new ArrayList<Pd_InfoVO>();
					    
					    String sql = "SELECT * " +
					                 "FROM (SELECT t.*, ROWNUM AS rnum " +
					                 "      FROM (SELECT * " +
					                 "            FROM PD_INFO " +
					                 "			  WHERE INFO_NAME like ? AND COUNTRY='SPAIN' "+
					                 "            ORDER BY INFO_NUM) t " +
					                 "      WHERE ROWNUM <= ?) " +
					                 "WHERE rnum >= ?";

					    try {
					        pstmt = con.prepareStatement(sql);
					        pstmt.setString(1, "%" + search + "%");
					        pstmt.setInt(2, endRow);
					        pstmt.setInt(3, startRow); 
					        rs = pstmt.executeQuery();

					        while (rs.next()) {
					            int info_num = rs.getInt(1);
					            String info_name = rs.getString(2);
					            String info_price = rs.getString(3);
					            String kind = rs.getString(4);
					            String country = rs.getString(5);
					            int capacity = rs.getInt(6);
					            String alcohol = rs.getString(7);
					            int made_year = rs.getInt(8);
					            int stock = rs.getInt(9);
					            String img = rs.getString(10);
					            Pd_InfoVO pdv = new Pd_InfoVO(info_num, info_name, info_price, kind, country, capacity, alcohol, made_year, stock, img);
					            pdlist.add(pdv);
					        }

					    } catch (Exception e) {
					        e.printStackTrace();
					    }
					    return pdlist;
					}
					   
					  public int getSearchSpainCount(String search){
					    int pdtotal = 0;
					     
					    try {
					   
					      String sql = "SELECT COUNT(*) FROM PD_INFO WHERE INFO_NAME like ? AND COUNTRY='SPAIN'";
					      pstmt = con.prepareStatement(sql);
					      pstmt.setString(1, "%" + search + "%"); 
					      rs = pstmt.executeQuery();
					      
					      if(rs.next()){
					    	pdtotal = rs.getInt(1);
					      }
					    } catch (Exception e){
					      e.printStackTrace();
					    } 
					    return pdtotal;
					  }
  	  
	
}
