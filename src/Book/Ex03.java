package Book;
import java.sql.*;
public class Ex03 {
	public static void main(String ar[]){
		//1. 드라이브 검색, 연결, 명령문 작성, 결과 출력, 닫기
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","12345678");
			Statement stmt=conn.createStatement();
			PreparedStatement pstmt=conn.prepareStatement("insert into Ex02_Table values (null,?)");
			pstmt.setString(1, "마이클");
			pstmt.executeUpdate();
			ResultSet rs=stmt.executeQuery("select * from Ex02_Table");
			while(rs.next()){
				System.out.println(rs.getInt("num")+" : "+rs.getString("name"));
			}
			
			rs.close();
			pstmt.close();
			stmt.close();
			conn.close();
			
		}catch(ClassNotFoundException e){
			System.err.println("class not found");
		}catch(SQLException e){
			System.err.println("connect exception");
		}
		
	}
}
