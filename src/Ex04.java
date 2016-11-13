import java.io.*;
import java.util.*;
import java.sql.*;
public class Ex04 {
	public static void main(String args[]){
		
		
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			
		}catch(ClassNotFoundException e){}
		
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String url="jdbc:mysql://192.168.0.9:3306/java";
		String user="root";
		String password="12345678";

		
		String query1="insert into person values (null,'eee','444444-44444444','2005-12-22')";
		
//		String name="eee";
//		String jumin="444444-4444444";
//		String date="2001-11-11";
//		String query2="insert into person values(null,'"+name+"','"+jumin+"','"+date+"')";
		
		String query3="insert into person values(null,?,?,now())";
		
		String query4="select * from person where num>?";
		try{
			conn=DriverManager.getConnection(url, user, password);
			/*stmt=conn.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			pstmt=conn.prepareStatement(query3);
			pstmt.setString(1, name);
			pstmt.setString(2, jumin);*/
			//pstmt.setDate(parameterIndex, x);
			//pstmt.setString(3, date);
			/*pstmt.executeUpdate();*/
			
			
			pstmt=conn.prepareStatement(query4);
			pstmt.setInt(1, 4);
			rs=pstmt.executeQuery(); // 결과값을 리턴하는 query임
			while(rs.next()){
				int num=rs.getInt(1); //rs.getInt("num");               //colum num은 뽑아온 녀석 순서대로임 !! 한마디로 select name jumin만 하면 name 이 1 jumin은 2임
				String name=rs.getString(2); //rs.getString("name");
				String jumin=rs.getString(3);
				java.sql.Date date=rs.getDate(4); // 우선 sql에 날짜형식 이므로 얻어옴
				java.util.Date d=new java.util.Date(date.getTime()); // 우리는 util 형태를 사용해야하므로 date의 날짜만 뽑아와서 다시 객체 생성
				System.out.println(num+"\t"+name+"\t"+jumin+"\t"+d.toString());
				
			}
			System.out.println("ok");
			
			pstmt.close();
			/*stmt.close();*/
			conn.close();
		}catch(SQLException e){System.err.println("error="+e);}
	}
}
