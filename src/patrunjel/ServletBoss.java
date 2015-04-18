package patrunjel;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletBoss")
public class ServletBoss extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BD obiectAjutor; // tine minte pe obiectul care face conexiunea

	public ServletBoss() {
		super();
		obiectAjutor = new BD();  // obiect creat o SINGURA data
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String parola = request.getParameter("parola");  // din JSP
		String username = request.getParameter("user");

		Map<String, String> usrPrl = obiectAjutor.getUsersFromDB(); // din BD
		for (Map.Entry<String,String> entry : usrPrl.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    System.out.println("User = " + key + " password = " + value);
		}
		
		boolean isAuthorized = false;
		// verificare user: daca ce a bagat in JSP este si in BD
		if(usrPrl.containsKey(username)){  // if(usrPrl.containsKey("John"))
			if(usrPrl.get(username).equals(parola)){  // if(usrPrl.get("John").equals("1234"))
				isAuthorized = true;
			}
		}
		
		// 'cutie'
		
		String mesaj=   "";
		
		
		if(isAuthorized){
			System.out.println("You are authorized");
			mesaj = "Welcome home!";
			response.sendRedirect("Success.jsp");
			
		}else{
			System.out.println("Fuck off");
			mesaj = "Go fuck yourself!";
			response.sendRedirect("Welcome.jsp");
		}
		
		HttpSession sesiune = request.getSession(true); 
		sesiune.setAttribute("mesaj_informare", mesaj);
	}

}
