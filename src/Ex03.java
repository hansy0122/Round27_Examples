import java.sql.*;
import java.util.*;

public class Ex03 {
	public static void main(String ar[]){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("dirver search ok");
		}catch(ClassNotFoundException e){
			System.err.println("error="+e);
		}
	
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String url="jdbc:oracle:thin:@192.168.0.9:1521:JAVA"; //localhost도 가능 127.0.0.1
		String id="scott";
		String pass="tiger";
		String query="select * from dept";
		try{
			conn=DriverManager.getConnection(url, id, pass);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			while(rs.next()){
				int deptno=rs.getInt(1); //rs.getInt("deptno");               //colum num은 뽑아온 녀석 순서대로임 !! 한마디로 select name jumin만 하면 name 이 1 jumin은 2임
				String dname=rs.getString(2); //rs.getString("dname");
				String loc=rs.getString(3);
				System.out.println(deptno+"\t"+dname+"\t"+loc);
				
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException ee){
			System.err.println("error sql="+ee);
		}
	
	
	
	
	}
}