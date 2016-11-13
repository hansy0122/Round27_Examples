import java.sql.*;
//참고 버퍼~~ scanner로 편하게 가능 하지만 둘다 외워야함 이것도 외워야함!
public class Ex01 {
	public static void main(String ar[]){
		//16단원에 나오는 내용인데
		
		//1.드라이버 파일을 찾아서 객체발생
		try{
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();//클래스 찾기 +객체 발생    // 객체발생은 굳이 안해도됨
		System.out.println("mysql Driver search complete");
		}catch(Exception e){ System.err.println("error=드라이버 오류");}
		

/*		try{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();//클래스 찾기 +객체 발생
		System.out.println("오라클  Driver search complete");
		}catch(Exception e){ System.err.println("error=드라이버 오류");}
		*/
		
		
		//2.connector 생성	
		
		Connection my_con=null;
		//url=프로토콜(jdbc):서브프로토콜(:mysql://192.168.0.9:3306/):SID(java)로 구성        //거의다 readme에서 확인가능
		
		
		//3.작업객체생성 
				Statement my_stmt=null;
				PreparedStatement my_pstmt=null;
				CallableStatement my_cstmt=null;
				
				/*Statement ora_stmt=null;
				PreparedStatement ora_pstmt=null;
				CallableStatement ora_cstmt=null;
				*/
				
				
		try{
			my_con=DriverManager.getConnection("jdbc:mysql://192.168.0.9:3306/java", "root", "12345678");
			System.out.println("My-sql과 연결");
			
			my_stmt=my_con.createStatement(); //정적 쿼리 처리용
			my_pstmt=my_con.prepareStatement("select * from person");//동적쿼리 처리용
			my_cstmt=my_con.prepareCall("");//프로시져 처리용
			
			System.out.println("my_stmt="+my_stmt);
			System.out.println("my_pstmt="+my_pstmt);
			
			//5. 결과값 반환받기
			//ResultSet rs=my_stmt.excuteQuery(query);
			///ResultSet rs1=my_pstmt.excuteQuery();
			//int x=my_stmt.excuteUpdate(query);
			//int x1=my_pstmt.excuteUpdate();
			
			//6. 접속종료
			my_pstmt.close();
			my_stmt.close();
			my_con.close();
			
			
		}catch(SQLException ee){
			System.err.println("error=연결객체 생성 실패"+ee);}
		
		//Connection ora_con=null;
		/*
		try{
			ora_con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.9:1521:java", "scott", "tiger");
			System.out.println("Oracle과 연결");
			ora_stmt=ora_con.createStatement(); //정적 쿼리 처리용
			ora_pstmt=ora_con.prepareStatement("select * from dept");//동적쿼리 처리용
			ora_cstmt=ora_con.prepareCall("");//프로시져 처리용
		}catch(SQLException ee){
			System.err.println("error=연결객체 생성 실패"+ee);}
		*/
		
	
		
		
	}
}
