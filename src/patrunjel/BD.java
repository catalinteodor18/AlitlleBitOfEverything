package patrunjel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BD {

	private Connection conn = null;  // tina minte conexiunea
	private Statement stmt = null;
	
	public BD(){
		try{
			System.out.println("*********CONNECTING TO DB********************");
			Class.forName("com.mysql.jdbc.Driver"); // incarcarea driverului cu
													// ajutorul clase
													// Class.forName
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jsp",
					"root", "1234"); // legatura cu bd
		}catch(Exception  e){
			e.printStackTrace();
		}
	}
	
	public  Map<String, String> getUsersFromDB() {

		Map<String, String> useriSiParole = new HashMap<>();

		try {
			
			String select = "Select * from info;";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select);

			while (rs.next()) {
				String user = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(user + " " + password);
				useriSiParole.put(user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useriSiParole;
	}

}
