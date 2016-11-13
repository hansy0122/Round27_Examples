package Book;

import java.sql.*;

public class DAO {
	private Connection dc;

	// 생성자
	public DAO() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			dc = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "12345678");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입 메소드
	public boolean registerMember(String name, String id, String pass, String email) {
		try {
			PreparedStatement pstmt = dc.prepareStatement("insert into D_Ex values (null,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pass);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 로그인 메소드
	public boolean loginMember(String id,String pass){
		try{
			PreparedStatement pstmt=dc.prepareStatement("select * from D_Ex where id = ? and pass = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			ResultSet rs=pstmt.executeQuery();
			if(!rs.next()){
				rs.close();
				pstmt.close();
				return false;
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e){
			System.err.println("로그인 처리 실패");
			e.printStackTrace();
		}
		return true;
	}

	// 회원정보 수정을 위해서
	public boolean editMember(String id, String name, String pass, String email) {
		try {
			PreparedStatement pstmt = dc.prepareStatement("update D_Ex set name=?, pass=?,email=? where id=?");
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.err.println("회원 정보수정 실패");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//회원 삭제 메소드
	public boolean deleteMember (String id){
		try{
			PreparedStatement pstmt=dc.prepareStatement("delete from D_Ex where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e){
			System.err.println("회원삭제 실패");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
