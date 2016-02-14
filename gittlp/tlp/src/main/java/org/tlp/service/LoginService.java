 package org.tlp.service;

import org.tlp.bean.AuthorBean;
import org.tlp.dao.LoginDao;
import org.tlp.response.Response;

public class LoginService {

	LoginDao logindao;
	Response response = new Response() ;
	
	public Response auntheticate(AuthorBean ab) {
		
			logindao = new LoginDao();
			String validuser /*= logindao.validateUserLogin(ab)*/;			
			validuser = "SUCCESS"; //default value for testing.
			if (validuser.equals("INVALID")) {
				 response.setStatus("INVALID");
			     response.setMessage("Password is wrong!");
			     response.setData(null);
			} else if (validuser == "FAILED") {
				 response.setStatus("FAILED");
			     response.setMessage("Either UserID or Password is wrong!");
			     response.setData(null);
			} else if (validuser == "ERROR") {
				 response.setStatus("ERROR");
			     response.setMessage("Login not successful! Please try again.");
			     response.setData(null);
			} else {
				  response.setStatus("SUCCESS");
			      response.setMessage("Login successful");
			      response.setData(null);
			}
			
		
		return response;
	}
	
	public boolean getLogout(String user) {
		
		//logindao = new LoginDao();		
	//return logindao.logout(user);
		return true;
}
	
	

}
