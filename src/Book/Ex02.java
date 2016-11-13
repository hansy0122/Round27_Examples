package Book;

import java.sql.*;

public class Ex02 {
	public static void main(String ar[]) {

		String url = "jdbc:mysql://localhost:3306/java";
		String id = "root";
		String pass = "12345678";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection conn = DriverManager.getConnection(url, id, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Ex02_Table");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " : " + rs.getString(2));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.err.println("class not found");
		} catch (SQLException ee) {
			System.err.println("connect exception");
		}
	}
}
