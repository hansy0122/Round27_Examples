package Book;

import java.io.*;
import java.sql.*;

public class D_Ex {
	public static void main(String ar[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		DAO dao = new DAO();
		while (true) {
			System.out.println("1.ȸ������  2.�α���  3.��������  4.��������  5.����");
			int x = System.in.read() - 48;
			System.in.read();
			System.in.read();
			if (x == 1) {
				System.out.println("ȸ������ ������ �Դϴ�");
				System.out.print("�̸�: ");
				String name = in.readLine();
				System.out.print("ID: ");
				String id = in.readLine();
				System.out.print("Password: ");
				String pass = in.readLine();
				System.out.print("e-mail: ");
				String email = in.readLine();
				boolean bool = dao.registerMember(name, id, pass, email);
				if (bool)
					System.out.println("ȸ�����Կ� �����߽��ϴ�.");
				else
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�");
				System.out.println();
			} else if (x == 2) {
				System.out.println("�α��� ������ �Դϴ�");
				System.out.print("ID: ");
				String id = in.readLine();
				System.out.print("Password: ");
				String pass = in.readLine();
				boolean bool = dao.loginMember(id, pass);
				if (bool)
					System.out.println("�α��ο� �����߽��ϴ�.");
				else
					System.out.println("�α��ο� �����Ͽ����ϴ�");
				System.out.println();
			} else if (x == 3) {
				System.out.println("�������� ������ �Դϴ�");
				System.out.print("ID: ");
				String id = in.readLine();
				System.out.print("�̸�: ");
				String name = in.readLine();
				System.out.print("Password: ");
				String pass = in.readLine();
				System.out.print("e-mail: ");
				String email = in.readLine();
				boolean bool = dao.editMember(id, name, pass, email);
				if (bool)
					System.out.println("ȸ������������ �����߽��ϴ�.");
				else
					System.out.println("ȸ������������ �����Ͽ����ϴ�");
				System.out.println();
			} else if (x == 4) {
				System.out.println("�������� ������ �Դϴ�");
				System.out.print("ID: ");
				String id = in.readLine();
				boolean bool = dao.deleteMember(id);
				if (bool)
					System.out.println("ȸ������������ �����߽��ϴ�.");
				else
					System.out.println("ȸ������������ �����Ͽ����ϴ�");
				System.out.println();

			} else if (x == 5) {
				System.out.println("�����մϴ�");
				System.exit(0);
			}else {System.out.println("�߸��Է�");}
		}

	}
}
