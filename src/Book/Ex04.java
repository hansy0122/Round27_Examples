package Book;
import java.sql.*;
import java.util.Scanner;
import java.io.*;


public class Ex04 {
	public static void main(String ar[]) throws IOException{
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement  pstmt=null;
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","12345678");
			stmt=conn.createStatement();
			pstmt=conn.prepareStatement("insert into Ex04_table values(null,?,?)");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		Scanner sc=new Scanner(System.in);
		while(true){
			System.out.print("1: 입력 2: 출력 3: 종료");
			int x=Integer.parseInt(sc.nextLine());
			if(x==1){
				System.out.println("이름:");
				String name =sc.nextLine();
				System.out.println("주민번호:");
				String jumin=sc.nextLine();
				try{
				pstmt.setString(1, name);
				pstmt.setString(2, jumin);
				System.out.println(name);
				pstmt.executeUpdate();
				}catch(SQLException ee){
					ee.printStackTrace();
				}

				}else if(x==2){
					try{
						ResultSet rs=stmt.executeQuery("select * from Ex04_table");
						while(rs.next()){
							System.out.println(rs.getInt(1)+" : "+rs.getString(2)+" --> "+ rs.getString(3));
						}
						rs.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
				
				
			}else if(x==3){
				System.out.println("system end");
				try{
					pstmt.close();
					stmt.close();
					conn.close();
				}catch(SQLException e){
					System.err.println("error");
				}
				System.exit(0);
			}
			
		}
		
	}
}
