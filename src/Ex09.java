import java.util.*;
import java.io.*;
import java.sql.*;

public class Ex09 {
	public static void main(String ar[])throws IOException{
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ce) {
			System.exit(0);
		}

		Connection conn = null;
		String url = "jdbc:mysql://localhost/java";
		String user = "root";
		String password = "12345678";
		
		
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.err.println("Error=" + e);
			System.exit(0);
		}
		

		query="select * from datetable";
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()){
				int num=rs.getInt(1);
				java.sql.Date mydate=rs.getDate("mydate");
				long a=mydate.getTime();
				java.sql.Time mytime=rs.getTime("mytime");
				long b=mytime.getTime(); // long 형태로변환
				java.sql.Timestamp mytimestamp=rs.getTimestamp("mytimestamp");
				long c=mytimestamp.getTime();
				System.out.println("num="+num);
				System.out.println("date="+new java.util.Date(a)); //long 형태로변환한것을 다시 변형후 출력
				System.out.println("time="+new java.util.Date(b));
				System.out.println("timestamp="+new java.util.Date(c));
				
			}
			
			
			rs.close();
			stmt.close();
			conn.close();
			
			
		}catch(SQLException se){
			System.err.println("error="+se);
		}
		
		
		
		
		
		
	}
}
