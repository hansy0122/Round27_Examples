import java.sql.*;
//���� ����~~ scanner�� ���ϰ� ���� ������ �Ѵ� �ܿ����� �̰͵� �ܿ�����!
public class Ex01 {
	public static void main(String ar[]){
		//16�ܿ��� ������ �����ε�
		
		//1.����̹� ������ ã�Ƽ� ��ü�߻�
		try{
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();//Ŭ���� ã�� +��ü �߻�    // ��ü�߻��� ���� ���ص���
		System.out.println("mysql Driver search complete");
		}catch(Exception e){ System.err.println("error=����̹� ����");}
		

/*		try{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();//Ŭ���� ã�� +��ü �߻�
		System.out.println("����Ŭ  Driver search complete");
		}catch(Exception e){ System.err.println("error=����̹� ����");}
		*/
		
		
		//2.connector ����	
		
		Connection my_con=null;
		//url=��������(jdbc):������������(:mysql://192.168.0.9:3306/):SID(java)�� ����        //���Ǵ� readme���� Ȯ�ΰ���
		
		
		//3.�۾���ü���� 
				Statement my_stmt=null;
				PreparedStatement my_pstmt=null;
				CallableStatement my_cstmt=null;
				
				/*Statement ora_stmt=null;
				PreparedStatement ora_pstmt=null;
				CallableStatement ora_cstmt=null;
				*/
				
				
		try{
			my_con=DriverManager.getConnection("jdbc:mysql://192.168.0.9:3306/java", "root", "12345678");
			System.out.println("My-sql�� ����");
			
			my_stmt=my_con.createStatement(); //���� ���� ó����
			my_pstmt=my_con.prepareStatement("select * from person");//�������� ó����
			my_cstmt=my_con.prepareCall("");//���ν��� ó����
			
			System.out.println("my_stmt="+my_stmt);
			System.out.println("my_pstmt="+my_pstmt);
			
			//5. ����� ��ȯ�ޱ�
			//ResultSet rs=my_stmt.excuteQuery(query);
			///ResultSet rs1=my_pstmt.excuteQuery();
			//int x=my_stmt.excuteUpdate(query);
			//int x1=my_pstmt.excuteUpdate();
			
			//6. ��������
			my_pstmt.close();
			my_stmt.close();
			my_con.close();
			
			
		}catch(SQLException ee){
			System.err.println("error=���ᰴü ���� ����"+ee);}
		
		//Connection ora_con=null;
		/*
		try{
			ora_con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.9:1521:java", "scott", "tiger");
			System.out.println("Oracle�� ����");
			ora_stmt=ora_con.createStatement(); //���� ���� ó����
			ora_pstmt=ora_con.prepareStatement("select * from dept");//�������� ó����
			ora_cstmt=ora_con.prepareCall("");//���ν��� ó����
		}catch(SQLException ee){
			System.err.println("error=���ᰴü ���� ����"+ee);}
		*/
		
	
		
		
	}
}
