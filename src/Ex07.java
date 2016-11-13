import java.io.*;
import java.util.*;
import java.sql.*;

public class Ex07 {
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
		System.out.println("업로드 할 파일=");
		String filename=sc.next();
		File f=new File(filename);
		if(!f.exists()){
			System.out.println("파일이 존재하지 않습니다");
			System.exit(1);
		
		}
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		FileInputStream fis=new FileInputStream(f); // 사실 이녀석만 있어도 가능합니다.
		while(true){
			int x=fis.read();
			if(x==-1)break; //끝가지 가면 끝내자
			bos.write(x); //bos에 x를 쓰겠습니다.
		}
		fis.close();
		bos.close();
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		
		query="insert into filetable values (null,?,?)";
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, filename);
			pstmt.setBinaryStream(2, bis,bos.size());
			//pstmt.setBinaryStream(2, fis,fis.size()); 이렇게 해도 가능
			pstmt.executeUpdate();
			System.out.println("업로드 성공");
			pstmt.close();
			conn.close();
		
		
		}catch(SQLException e){System.err.println("sql error="+e);}
		
		
}
}
