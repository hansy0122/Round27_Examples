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
			System.out.println("1.ģ���߰� 2.�ֹι�ȣ�ΰ˻� 3.��ü���4.����");
			int dist=sc.nextInt();
			if(dist==1){
				System.out.print("�̸�=");
				String name=sc.next();
				System.out.print("�ֹι�ȣ=");
				String jumin=sc.next();
				query="insert into friend values(null,?,?)";
				
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, name);
					pstmt.setString(2, jumin);
					pstmt.executeUpdate();
					pstmt.close();
					System.out.println("�����ϿϷ�");
				} catch (SQLException e) {
					System.err.println("Error=" + e);
					System.exit(0);
				}
				
			}else if(dist==2){
				System.out.print("ã�� ģ�� �ֹι�ȣ=");
				String jumin=sc.next();
				query="select num, name from friend where jumin=?";
				
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, jumin);
					rs=pstmt.executeQuery();
					if(rs.next()){
					System.out.println("num="+rs.getInt("num")+"\nname="+rs.getString("name"));
					}else{
						System.out.println("ã�� ģ���� ��� �Ǿ������ʽ��ϴ�");
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
				System.out.println("���� �մϴ�.");
			
				try{
					rs.close();
					conn.close();	
				}catch(SQLException e){
					System.err.println("error="+e);System.exit(0);
				}
				System.exit(0);
			}else{
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
			System.out.println();
		}
		
		
		
	}
}
