import java.sql.*;
import java.util.*;

public class Ex02 {
	public static void main(String ar[]){
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("dirver search ok");
		}catch(ClassNotFoundException e){
			System.err.println("error="+e);
		}
	
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String url="jdbc:mysql://127.0.0.1:3306/java"; //localhost도 가능 127.0.0.1
		String id="root";
		String pass="12345678";
		String query="select * from person";
		try{
			conn=DriverManager.getConnection(url, id, pass);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			while(rs.next()){
				int num=rs.getInt(1); //rs.getInt("num");               //colum num은 뽑아온 녀석 순서대로임 !! 한마디로 select name jumin만 하면 name 이 1 jumin은 2임
				String name=rs.getString(2); //rs.getString("name");
				String jumin=rs.getString(3);
				java.sql.Date date=rs.getDate(4); // 우선 sql에 날짜형식 이므로 얻어옴
				java.util.Date d=new java.util.Date(date.getTime()); // 우리는 util 형태를 사용해야하므로 date의 날짜만 뽑아와서 다시 객체 생성
				System.out.println(num+"\t"+name+"\t"+jumin+"\t"+d.toString());
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException ee){
			System.err.println("error sql="+ee);
		}
	
	
	
	
	}
}
