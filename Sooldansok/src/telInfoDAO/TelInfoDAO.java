package telInfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import telInfoDBConn.TelInfoDBConn;
import telInfoVO.ClientVO;
import telInfoVO.Pd_InfoVO;
import telInfoVO.QuestionVO;

public class TelInfoDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs1 = null;

	public TelInfoDAO() {
		try {
			con = new TelInfoDBConn().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pstmtClose() throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}

	public void getAllInfoClose() throws SQLException {
		if (rs1 != null) {
			rs1.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
	}

//	
//	// ��ü ���-----------------------
//	// db�� �ִ� ���� ���� ��� ArrayList�� ���� ��
//	public ArrayList<QuestionVO> getAllInfo(String id) throws SQLException, ClassNotFoundException {
//		// RecordSearch���� cd = searchTxt.getText(); �Է¹��� ������ �̸��� �Ķ���ͷ�~
//		// getAllInfo(String word) ���ڷ� �޴´�
//
//		// RecordSearch.java > getAllInfo(cd)
//		// --------> RecordInfoDAO.java > getAllInfo(String word)
//
//		ArrayList<QuestionVO> qArr = new ArrayList<QuestionVO>();
//		// recordArr ��ü�� RecordInfoVO�� ��� �Ӽ��� ��´�
//
//		String sql = "select * from Question" + " where lower(MEM_ID) like 'sd'"; // ? = client id
//
//		////////////////////////////////////////////////////////////////////////
//
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, id); // word == ������ �̸�
//		rs1 = pstmt.executeQuery(); // ��¸� executeQuery ******** 1) ��ü ������ DB�ȿ� �ִ´�
//
//		System.out.println("ȭ�鿡�� �Է¹޾� DAO ���� �Ѱ��ִ� �� : " + id);
//
//		while (rs1.next()) { // 2) rs1�� ���� �ִ� ����~ DB�ȿ��� ���� �ϳ��� ������
//
//			int q_num = rs1.getInt("q_num");
//			String q_title = rs1.getString("q_title");
//			String q_comment = rs1.getString("q_comment");
//			String answer = rs1.getString("answer");
//			String mem_id = rs1.getString("mem_id");
//
//			// db�� �� ������ Ȯ��
//			System.out.println(
//					"DB�� ���� �� : " + q_num + "\t" + q_title + "\t" + q_comment + "\t" + answer + "\t" + mem_id);
//
//			QuestionVO quest = new QuestionVO(q_num, q_title, q_comment, answer, mem_id);
//			// 3) record��ü �ȿ� 5������ �ʱ�ġ�� �־� RecordInfoVO�� ���ڰ� �ִ� �����ڷ� ����
//
//			qArr.add(quest); // 4) record��ü(1���� ����)�� recordArr ����Ʈ�ȿ� �ִ´�
//								// 5) DB���� ������ �� �Ҷ� ���� �� �۾��� �ݺ��Ѵ�
//								// ex) 100���̸� 100���� �۾��� ����Ʈ���� 100���� ������ ���� �ȴ�
//		}
//
//		return qArr; // recordArr�� �Ѱ��ش�
//
//	} // ��ü ���-end -----------------------
	
	
//
	/// ----------------------- id찾기
		public String id_search2(String name, String email){  //이름,이메일로 찾기

			String id=null ; //찾을아이디
			  
			  String sql="select ID from CLIENT where NAME=? and EMAIL=?";
			  try{
			   pstmt=con.prepareStatement(sql); //쿼리
			   pstmt.setString(1, name); //첫번째 ?를 스트링 id로 넣음
			   pstmt.setString(2, email); //두번째 ?에 스트링 pw 넣음
			   
			   rs1=pstmt.executeQuery(); //쿼리를 실행해서 결과값을 rs로 저장
			   while(rs1.next()){  //rs가 끝날때까지 반복
			    id=rs1.getString("id"); //cnt를 디비에서 가져온 cnt에 저장  
			   }
			   con.close();

			  }catch(Exception e){
			   System.out.println(e);
			  }
			  System.out.println("good "+id);
			  return id;
			  
			 }
		/// ----------------------- id찾기-end
		
		/// ----------------------- pw찾기
		public String pw_search2(String id, String phone){  //이름,이메일로 찾기

			String pw=null ; //찾을 비번
			  
			  String sql="select PW from CLIENT where ID=? and PHONE=?";
			  try{
			   pstmt=con.prepareStatement(sql); //쿼리
			   pstmt.setString(1, id); //첫번째 ?를 스트링 id로 넣음
			   pstmt.setString(2, phone); //두번째 ?에 스트링 pw 넣음
			   
			   rs1=pstmt.executeQuery(); //쿼리를 실행해서 결과값을 rs로 저장
			   while(rs1.next()){  //rs가 끝날때까지 반복
			    pw=rs1.getString("pw"); //cnt를 디비에서 가져온 cnt에 저장  
			   }
			   con.close();

			  }catch(Exception e){
			   System.out.println(e);
			  }
			  System.out.println("입력한1 :: "+id);
			  System.out.println("입력한2 :: "+phone);
			  System.out.println("good pw "+pw);
			  
			  
			  return pw;
			  
			 }
		/// ----------------------- pw찾기-end
	
	/// ----------------------- 아이디 중복검사
	public int checkId(String id) throws SQLException {
		
		String sql = "SELECT * FROM CLIENT WHERE ID = ?";
		int idCheck = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs1 = pstmt.executeQuery();

			if (rs1.next() || id.equals("")) {
				idCheck = 0; // 이미 존재/생성불가
			} else {
				idCheck = 1; // 존재x/생성가능
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
		}

		return idCheck;

	}
	/// ----------------------- 아이디 중복검사-end
	
	 /// ----------------------- 이메일 중복검사
	   public int checkEmail(String email) throws SQLException {
	      
	      String sql = "SELECT * FROM CLIENT WHERE EMAIL = ?";
	      int emailCheck = 0;

	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, email);
	         rs1 = pstmt.executeQuery();

	         if (rs1.next() || email.equals("")) {
	            emailCheck = 0; // 이미 존재/생성불가
	         } else {
	            emailCheck = 1; // 존재x/생성가능
	         }

	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         con.close();
	      }
	      System.out.println(emailCheck);
	      return emailCheck;

	   }
	   /// ----------------------- 이메일 중복검사-end
	   
	   /// ----------------------- 연락처 중복검사
	   public int checkPhone(String phone) throws SQLException {
	      
	      String sql = "SELECT * FROM CLIENT WHERE PHONE = ?";
	      int phoneCheck = 0;

	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, phone);
	         rs1 = pstmt.executeQuery();

	         if (rs1.next() || phone.equals("")) {
	            phoneCheck = 0; // 이미 존재/생성불가
	         } else {
	            phoneCheck = 1; // 존재x/생성가능
	         }

	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         con.close();
	      }
	      System.out.println(phoneCheck);
	      return phoneCheck;

	   }
	   /// ----------------------- 연락처 중복검사-end


	/// ----------------------- 회원가입 insert
	public boolean join_insert(String id, String pw, String pw2, String name, String birth, String address,
			String email, String phone) {
		String sql = "INSERT INTO CLIENT values(?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, address);
			pstmt.setString(6, email);
			pstmt.setString(7, phone);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(
					id + "\t" + pw + "\t" + name + "\t" + birth + "\t" + address + "\t" + email + "\t" + phone);
			System.out.println("Exception:" + e);
			System.out.println("insert Exception");
			System.out.println("가입 실패");
			return false;
//			e.printStackTrace();
		}
		System.out.println("가입 성공");
		return true;

	} // ----------------------- 회원가입 insert~end
	
	
	/// ----------------------- 와인전체 조회 
	public ArrayList<Pd_InfoVO> productAllInfo() throws SQLException, ClassNotFoundException {
		
		ArrayList<Pd_InfoVO> pdArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO"; // 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql);
		rs1 = pstmt.executeQuery(); // 

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price");
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year"); 
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");


			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			pdArr.add(quest); 
		}

		return pdArr; 

	}
	/// ----------------------- 와인전체 조회 -end

	
	/// ----------------------- 프랑스 와인전체 조회 
	// select * from PD_INFO where country in ('FRANCE');  
	public ArrayList<Pd_InfoVO> productFranceInfo() throws SQLException, ClassNotFoundException {
		
		ArrayList<Pd_InfoVO> frArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where country in ('FRANCE')"; // 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql);
		rs1 = pstmt.executeQuery(); 

		while (rs1.next()) { // 2) 

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol");
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			frArr.add(quest); 
		}

		return frArr; 
	}
	/// ----------------------- 프랑스 와인전체 조회 -end
	
	
	/// ----------------------- 칠레 와인전체 조회 
	// select * from PD_INFO where country in ('CHILE');  
	public ArrayList<Pd_InfoVO> productChileInfo() throws SQLException, ClassNotFoundException {
		
		ArrayList<Pd_InfoVO> chArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where country in ('CHILE')"; // 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql);
		rs1 = pstmt.executeQuery(); // 1)

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind");
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			chArr.add(quest); 
		}

		return chArr; 
	}
	/// -----------------------칠레 와인전체 조회 -end
	
	/// ----------------------- 이태리 와인전체 조회 
	// select * from PD_INFO where country in ('ITALY');  
	public ArrayList<Pd_InfoVO> productItalyInfo() throws SQLException, ClassNotFoundException {
		
		ArrayList<Pd_InfoVO> itArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where country in ('ITALY')"; // 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql);
		rs1 = pstmt.executeQuery(); // 1) 

		while (rs1.next()) { // 2) 

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); // ��ǰ��
			String info_price = rs1.getString("info_price"); // ����
			String kind = rs1.getString("kind"); // ����
			String country = rs1.getString("country"); // ����
			int capacity = rs1.getInt("capacity"); // ? 
			String alcohol = rs1.getString("alcohol"); // ����
			int made_year = rs1.getInt("made_year"); // �⵵ 
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			itArr.add(quest); 
		}

		return itArr; 
	}
	/// ----------------------- 이태리 와인전체 조회 -end
	
	
	
	/// ----------------------- 스페인 와인전체 조회
	// select * from PD_INFO where country in ('SPAIN');  
	public ArrayList<Pd_InfoVO> productSpainInfo() throws SQLException, ClassNotFoundException {
		
		ArrayList<Pd_InfoVO> spArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where country in ('SPAIN')"; // 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql);
		rs1 = pstmt.executeQuery(); // 1)

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity");
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year"); 
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");


			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			spArr.add(quest); 
		}

		return spArr; 
	}
	/// ----------------------- 스페인 와인전체 조회-end
	
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	
	/// ----------------------- 전체와인에서 검색
	public ArrayList<Pd_InfoVO> pdSearchAllInfo(String pd_name) throws SQLException, ClassNotFoundException {
		ArrayList<Pd_InfoVO> pdArr = new ArrayList<Pd_InfoVO>();
		String sql = "select * from PD_INFO where info_name like ?"; 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql); //
		pstmt.setString(1, "%" + pd_name + "%");
		rs1 = pstmt.executeQuery(); // 1)

		System.out.println("전체에서 검색: ");

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year"); 
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			pdArr.add(quest); 
		}

		return pdArr; 

	}
	/// ----------------------- 스페인 와인전체 조회-end
	

	/// ----------------------- 프랑스 와인전체 조회
	public ArrayList<Pd_InfoVO> pdSearchFranceInfo(String pd_name) throws SQLException, ClassNotFoundException {
		ArrayList<Pd_InfoVO> frArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where info_name like ? and country in ('FRANCE')"; 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, "%" + pd_name + "%");
		rs1 = pstmt.executeQuery(); 

		System.out.println("프랑스에서 검색 : ");

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			frArr.add(quest); 
		}

		return frArr; 

	}
	/// ----------------------- 프랑스 와인전체 조회-end
	
	
	
	
	
	/// ----------------------- 칠레 와인전체 조회
	public ArrayList<Pd_InfoVO> pdSearchChileInfo(String pd_name) throws SQLException, ClassNotFoundException {
		ArrayList<Pd_InfoVO> chArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where info_name like ? and country in ('CHILE')"; 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, "%" + pd_name + "%");
		rs1 = pstmt.executeQuery(); 

		while (rs1.next()) { 

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity"); 
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			chArr.add(quest); 
		}

		return chArr; 

	}
	/// ----------------------- 칠레 와인전체 조회-end
	
	

	/// ----------------------- 이태리 와인전체 조회
	public ArrayList<Pd_InfoVO> pdSearchItalyInfo(String pd_name) throws SQLException, ClassNotFoundException {
		ArrayList<Pd_InfoVO> itArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where info_name like ? and country in ('ITALY')"; 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, "%" + pd_name + "%");
		rs1 = pstmt.executeQuery(); 

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind");
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity");
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			itArr.add(quest); 
		}

		return itArr;

	}
	/// ----------------------- 이태리 와인전체 조회-end
	
	
	/// ----------------------- 스페인 와인전체 조회
	public ArrayList<Pd_InfoVO> pdSearchSpainInfo(String pd_name) throws SQLException, ClassNotFoundException {
		ArrayList<Pd_InfoVO> spArr = new ArrayList<Pd_InfoVO>();

		String sql = "select * from PD_INFO where info_name like ? and country in ('SPAIN')"; 
		////////////////////////////////////////////////////////////////////////
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, "%" + pd_name + "%");
		rs1 = pstmt.executeQuery(); 

		while (rs1.next()) { // 2)

			int info_num = rs1.getInt("info_num");
			String info_name = rs1.getString("info_name"); 
			String info_price = rs1.getString("info_price"); 
			String kind = rs1.getString("kind"); 
			String country = rs1.getString("country"); 
			int capacity = rs1.getInt("capacity");
			String alcohol = rs1.getString("alcohol"); 
			int made_year = rs1.getInt("made_year");
			int stock = rs1.getInt("stock");
			String img = rs1.getString("img");

			Pd_InfoVO quest = new Pd_InfoVO(info_num, info_name, info_price, kind, country,
					capacity, alcohol, made_year, stock, img);

			spArr.add(quest); 
		}

		return spArr; 

	}
	/// ----------------------- 스페인 와인전체 조회-end
	
}
