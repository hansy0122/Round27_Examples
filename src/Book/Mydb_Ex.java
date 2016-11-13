package Book;

import java.sql.*;
import mydb.*;

public class Mydb_Ex {

		public static void main(String ar[]){
			//1. ����̺� �˻�, ����, ��ɹ� �ۼ�, ��� ���, �ݱ�
			
			MyDB mb=mydb.MyDB.getinstance();
			Statement stmt=mb.getStmt();
			PreparedStatement pstmt=mb.getPstmt("insert into Ex02_Table values (null,?)");
			
			try{
				pstmt.setString(1, "����Ŭ");
				pstmt.executeUpdate();
				ResultSet rs=stmt.executeQuery("select * from Ex02_Table");
				while(rs.next()){
					System.out.println(rs.getInt("num")+" : "+rs.getString("name"));
				}
				rs.close();
				stmt.close();
				pstmt.close();
				mb.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
		}
	}


