package Book;

public class Ex01 {
	public static void main(String ar[]){
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("success in installing Dirver");
		}catch(ClassNotFoundException ee){
			System.err.println("ClassNotFound");
			
		}
	}
}
