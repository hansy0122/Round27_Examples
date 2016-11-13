package mydb;
//connection class �ۼ�
//�ٸ������� ����Ͽ� ������ ���̽� ������ ���� ������.

import java.sql.*;
import java.util.*;
import java.io.*;

public class MyDB {
	//�����ͺ��̽� ���� ó���߿� ������ �߻��ϰų� Ȥ�� �⺻�����α׸� ��Ÿ������ �Ѵٸ� �װ��� �α����Ϸ� �����ϱ� ���ؼ� ���� ������� ����Ѵ�.
	private File log=new File("db.log");
	private PrintWriter log_out;
	private String driver ,url,id,pass;
	private Connection conn;
	private MyDB(){
		//�⺻�� ����
		try{
			//�����ͺ��̽��� ����� ������ �Ź� �ڹ� ������ ���� ������ ���� ������ �ϴ� �۾��� �ϱⰡ ����� ������
			//�Ϲ� �׽�Ʈ������ ����� ���� �װ��� ������ �о� �����ϵ��� �Ѵ�.
			File data=new File("dbinfo.txt"); // classpath ���� C:\java\eclipse\workspace\Round27_Examples �� �⺻������ ��
			BufferedReader in=new BufferedReader(new FileReader(data));
			driver =in.readLine();
			url=in.readLine();
			id=in.readLine();
			pass=in.readLine();
			in.close();
			//true�� �����ϸ� log ������ ���������͸� �����ϸ鼭 �߰��ȴ�.
			log_out=new PrintWriter(new BufferedWriter(new FileWriter(log,true)));
		}catch(IOException e){
			e.printStackTrace();
		}
		//����̹� �˻� 
		try{
			Class.forName(driver);
			log_out.println(new java.util.Date().toString()+ ": Driver �˻� �Ϸ�");
			log_out.flush();
		}catch(ClassNotFoundException e){
			log_out.println(new java.util.Date()+" : Driver �˻� ����");
			log_out.flush();
			System.exit(-1);
		}
		
		//DB�� ���� ��ü ����
		try{
			conn=DriverManager.getConnection(url,id,pass);
			log_out.println(new java.util.Date()+" : Connection ��ü���� �Ϸ�");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : Connection ��ü���� ����");
			log_out.flush();
			System.exit(-1); // -1�� ������ ���� 0�� ��������
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
			log_out.println(new java.util.Date()+" : Statement ��ü���� ����");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : Statement ��ü���� ����");
			log_out.flush();
		}
		return stmt;
	}
	
	public PreparedStatement getPstmt(String query){
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(query);
			log_out.println(new java.util.Date()+" : PreaparedStatement ��ü���� ����");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : PreparedStatement ��ü���� ����");
			log_out.flush();
		}
		return pstmt;
	}
	
	public CallableStatement getCstmt(String procedure){
		CallableStatement cstmt=null;
		try{
			cstmt=conn.prepareCall(procedure);
			log_out.println(new java.util.Date()+" : CallableStatement ��ü���� ����");
			log_out.flush();
		}catch(SQLException e){
			log_out.println(new java.util.Date()+" : CallableStatement ��ü���� ����");
			log_out.flush();
		}
		return cstmt;
	}
	
	public void close(){
		if(conn!=null){
			try{
				if(!conn.isClosed()){
					conn.close();
					log_out.println(new java.util.Date()+" : Connection ��ü ��ü ����");
					log_out.flush();
				}
			}catch(SQLException e ){
				log_out.println(new java.util.Date()+" : Connection ��ü ��ü ����");
				log_out.flush();
			}
			conn=null;
		}
	}
}
