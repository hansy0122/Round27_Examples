import java.io.*;
import java.sql.*;
import java.util.*;

public class Ex05 {
	public static void main(String ar[]) {

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ce) {
			System.exit(0);
		}

		Connection conn = null;
		String url = "jdbc:mysql://localhost/java";
		String user = "root";
		String password = "12345678";
		
		Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.err.println("Error=" + e);
			System.exit(0);
		}
		
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.println("1.친구추가 2.주민번호로검색 3.전체출력4.종료");
			int dist=sc.nextInt();
			if(dist==1){
				System.out.print("이름=");
				String name=sc.next();
				System.out.print("주민번호=");
				String jumin=sc.next();
				query="insert into friend values(null,?,?)";
				
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, name);
					pstmt.setString(2, jumin);
					pstmt.executeUpdate();
					pstmt.close();
					System.out.println("정상등록완료");
				} catch (SQLException e) {
					System.err.println("Error=" + e);
					System.exit(0);
				}
				
			}else if(dist==2){
				System.out.print("찾을 친구 주민번호=");
				String jumin=sc.next();
				query="select num, name from friend where jumin=?";
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, jumin);
					rs=pstmt.executeQuery();
					if(rs.next()){
					System.out.println("num="+rs.getInt("num")+"\nname="+rs.getString("name"));
					}else{
						System.out.println("찾는 친구는 등록 되어있지않습니다");
					}
					pstmt.close();
					
				} catch (SQLException e) {
					System.err.println("Error=" + e);
					System.exit(0);
				}
			}else if(dist==3){
				
				query="select * from friend";
				try{
					stmt=conn.createStatement();
					rs=stmt.executeQuery(query);
					while(rs.next()){
						int a=rs.getInt(1);
						String b=rs.getString(2);
						String c=rs.getString(3);
						System.out.println(a+":"+b+":"+c);
					}
					
					stmt.close();
					
				}catch(SQLException e){
					System.err.println("error="+e);System.exit(0);
				}
				
				
				
			}else if(dist==4){
				System.out.println("종료 합니다.");
			
				try{
					rs.close();
					conn.close();	
				}catch(SQLException e){
					System.err.println("error="+e);System.exit(0);
				}
				System.exit(0);
			}else{
				System.out.println("잘못 입력하셨습니다.");
			}
			System.out.println();
		}
		
		
		
	}
}
