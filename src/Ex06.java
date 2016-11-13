import java.util.*;
import java.sql.*;
public class Ex06 {
	public static void main(String ar[]){
		
	try {
		Class.forName("org.gjt.mm.mysql.Driver");
	} catch (ClassNotFoundException ce) {
		System.exit(0);
	}

	Connection conn = null;
	String url = "jdbc:mysql://localhost/java";
	String user = "root";
	String password = "12345678";
	
	
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
	System.out.println("µ¿=");
	String dongname=sc.next();
	query="select * from zipcode where dong like ?";
	
	try{
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, "%"+dongname+"%");
		rs=pstmt.executeQuery();
		while(rs.next()){
			String addr="("+rs.getString("zipcode")+") ";
			addr +=rs.getString("sido")+" ";
			addr +=rs.getString("gugun")+" ";
			addr +=rs.getString("dong")+" ";
			String a=rs.getString("ri");
			String b=rs.getString("bunji");
			if(a!=null &&a.trim().length()!=0)
				addr +=a+" ";
			if(b!=null &&b.trim().length()!=0)
				addr +=b+"";
				System.out.println(addr);
		}
	}catch(SQLException e){
		
	}
	
	
	
}
}
