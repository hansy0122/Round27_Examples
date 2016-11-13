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
		System.out.println("���ε� �� ����=");
		String filename=sc.next();
		File f=new File(filename);
		if(!f.exists()){
			System.out.println("������ �������� �ʽ��ϴ�");
			System.exit(1);
		
		}
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		FileInputStream fis=new FileInputStream(f); // ��� �̳༮�� �־ �����մϴ�.
		while(true){
			int x=fis.read();
			if(x==-1)break; //������ ���� ������
			bos.write(x); //bos�� x�� ���ڽ��ϴ�.
		}
		fis.close();
		bos.close();
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		
		query="insert into filetable values (null,?,?)";
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, filename);
			pstmt.setBinaryStream(2, bis,bos.size());
			//pstmt.setBinaryStream(2, fis,fis.size()); �̷��� �ص� ����
			pstmt.executeUpdate();
			System.out.println("���ε� ����");
			pstmt.close();
			conn.close();
		
		
		}catch(SQLException e){System.err.println("sql error="+e);}
		
		
}
}
