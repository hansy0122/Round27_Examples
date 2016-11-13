import java.util.*;
import java.io.*;
import java.sql.*;

public class Ex08 {
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
		System.out.println("다운로드할 번호=");
		int num=sc.nextInt();
		
		query="select * from filetable where num=?";
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				String filename=rs.getString("filename");
				System.out.println("파일명="+filename);
				
				Blob blob=rs.getBlob("filedata");
				InputStream is=blob.getBinaryStream();
				
				
				while(true){
					int x=is.read();
					if(x==-1)break;
					System.out.print((char)x);
				}
				is.close();
			}else{
				System.out.println("찾는 번호가 없습니다.");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e){
			System.err.println("sql error="+e);
		}
		
	}
}