package mydb;
//connection class 작성
//다른곳에서 사용하여 데이터 베이스 접속을 좀더 쉽게함.

import java.sql.*;
import java.util.*;
import java.io.*;

public class MyDB {
	//데이터베이스 관련 처리중에 에러가 발생하거나 혹은 기본정보로그를 나타내고자 한다면 그것을 로그파일로 관리하기 위해서 파일 입출력을 사용한다.
	private File log=new File("db.log");
	private PrintWriter log_out;
	private String driver ,url,id,pass;
	private Connection conn;
	private MyDB(){
		//기본적 설정
		try{
			//데이터베이스가 변경될 때마다 매번 자바 파일을 열고 수정한 다음 컴파일 하는 작업을 하기가 힘들기 떄문에
			//일반 테스트파일을 만들어 놓고 그곳의 정보를 읽어 수정하도록 한다.
			File data=new File("dbinfo.txt"); // classpath 상의 C:\java\eclipse\workspace\Round27_Examples 는 기본적으로 들어감
			BufferedReader in=new BufferedReader(new FileReader(data));
			driver =in.readLine();
			url=in.readLine();
			id=in.readLine();
			pass=in.readLine();
			in.close();
			//true로 설정하면 log 파일의 기존데이터를 유지하면서 추가된다.
			log_out=new PrintWriter(new BufferedWriter(new FileWriter(log,true)));
		}catch(IOException e){
			e.printStackTrace();
		}
		//드라이버 검색 
		try{
			Class.forName(driver);
			log_out.println(new java.util.Date().toString()+ ": Driver 검색 완료");
			log_out.flush();
		}catch(ClassNotFoundException e){
			log_out.println(new java.util.Date()+" : Driver 검색 실패");
			log_out.flush();
			System.exit(-1);
		}
		
		//DB와 연결 객체 생성
		try{
			conn=DriverManager.getConnection(url,id,pass);
			log_out.println(new java.util.Date()+" : Connection 객체생성 완료");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : Connection 객체생성 실패");
			log_out.flush();
			System.exit(-1); // -1은 비정상 종료 0은 정상종료
		}
	}
	
	public static MyDB getinstance(){
		return new MyDB();
	}
	
	public Connection getConn(){
		return conn;
	}
	
	public Statement getStmt(){
		Statement stmt=null;
		try{
			stmt=conn.createStatement();
			log_out.println(new java.util.Date()+" : Statement 객체생성 성공");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : Statement 객체생성 실패");
			log_out.flush();
		}
		return stmt;
	}
	
	public PreparedStatement getPstmt(String query){
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(query);
			log_out.println(new java.util.Date()+" : PreaparedStatement 객체생성 성공");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : PreparedStatement 객체생성 실패");
			log_out.flush();
		}
		return pstmt;
	}
	
	public CallableStatement getCstmt(String procedure){
		CallableStatement cstmt=null;
		try{
			cstmt=conn.prepareCall(procedure);
			log_out.println(new java.util.Date()+" : CallableStatement 객체생성 성공");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : CallableStatement 객체생성 실패");
			log_out.flush();
		}
		return cstmt;
	}
	
	public void close(){
		if(conn!=null){
			try{
				if(!conn.isClosed()){
					conn.close();
					log_out.println(new java.util.Date()+" : Connection 객체 해체 성공");
					log_out.flush();
				}
			}catch(SQLException e ){
				log_out.println(new java.util.Date()+" : Connection 객체 해체 실패");
				log_out.flush();
			}
			conn=null;
		}
	}
}
