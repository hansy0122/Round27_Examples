import java.util.*;
import java.sql.*;

public class Ex10 {
	public static void main(String ar[]) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("dirver search ok");
		} catch (ClassNotFoundException e) {
			System.err.println("error=" + e);
		}

		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@192.168.0.9:1521:JAVA"; // localhost도 가능
																// 127.0.0.1
		String id = "scott";
		String pass = "tiger";
		String query = null;
		Scanner sc = new Scanner(System.in);

		try {
			conn = DriverManager.getConnection(url, id, pass);

		} catch (SQLException ee) {
			System.err.println("error sql=" + ee);
		}

		System.out.println("누구의 나이를 알고싶나요?");
		String name = sc.next();

		query = "(call myproc(?,?))";

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, name);
			cstmt.registerOutParameter(2, Types.INTEGER); // 이놈이 쫌 특이하군
			cstmt.executeQuery();
			int age=cstmt.getInt(2); // 두번째를 얻겠다
			System.out.println(name+"님의 나이는"+age);
			
			cstmt.close();
			conn.close();
			
		} catch (SQLException ee) {
			System.err.println("error sql=" + ee);
		}

	}
}