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
		String url="jdbc:mysql://127.0.0.1:3306/java"; //localhost�� ���� 127.0.0.1
		String id="root";
		String pass="12345678";
		String query="select * from person";
		try{
			conn=DriverManager.getConnection(url, id, pass);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			while(rs.next()){
				int num=rs.getInt(1); //rs.getInt("num");               //colum num�� �̾ƿ� �༮ ��������� !! �Ѹ���� select name jumin�� �ϸ� name �� 1 jumin�� 2��
				String name=rs.getString(2); //rs.getString("name");
				String jumin=rs.getString(3);
				java.sql.Date date=rs.getDate(4); // �켱 sql�� ��¥���� �̹Ƿ� ����
				java.util.Date d=new java.util.Date(date.getTime()); // �츮�� util ���¸� ����ؾ��ϹǷ� date�� ��¥�� �̾ƿͼ� �ٽ� ��ü ����
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
