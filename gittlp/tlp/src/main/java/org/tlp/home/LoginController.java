package org.tlp.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tlp.bean.AuthorBean;
import org.tlp.response.Response;
import org.tlp.service.LoginService;

@Controller
public class LoginController  implements HttpSessionListener{
	
	 
	private static String userid;
	Response reponse ;
	public LoginService loginService ;	
	public HttpSession session;
			 	
	@RequestMapping(value="/author", method=RequestMethod.GET)
    public String greetingForm(Model model,HttpServletRequest req) {
		session = req.getSession();
		if(session!=null){
			model.addAttribute("ab", new AuthorBean());
			return "loginPage";
		}
    	else{
    		model.addAttribute("msg","Your session expired");
    		return "loginErrorPage";
    	}
        
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String aurthorLoginSubmit(@ModelAttribute AuthorBean ab, Model model, HttpServletRequest req) {
		loginService = new LoginService();
		model.addAttribute("ab", new AuthorBean());
		
		System.out.println("SID in login"+req.getSession().getId());			
		reponse = loginService.auntheticate(ab);
			if(reponse!= null){
			 	loginService = new LoginService();
				if(reponse.status.equals("SUCCESS")){
					if(session == null){
						session = req.getSession();
					}					
					System.out.println(session.getMaxInactiveInterval());
					session.setMaxInactiveInterval(1800);
					System.out.println(session.getMaxInactiveInterval());
					userid = ab.getUserid();
					session.setAttribute("user",ab.getUserid());
				    return "createPage";
				}else{
					model.addAttribute("msg",reponse.message);
					return "loginErrorPage";
				}
				
			}
		model.addAttribute("msg","Login After Sometime.");
		return "loginErrorPage";	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String aurthorLoginGetRedirect(@ModelAttribute AuthorBean ab, Model model, HttpServletRequest req) {
		model.addAttribute("ab", new AuthorBean());				 
		model.addAttribute("msg","Login After Sometime.");
		return "loginPage";	
	}
	
	/******************secrure Pages*********************/
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
    public String monitor(Model model,HttpServletRequest req) {
    	if(checkingUserSessionStatus(req))
    		return "createPage";
    	else{
    		model.addAttribute("msg","Your session expired,login again");
    		model.addAttribute("ab", new AuthorBean());
    		return "loginErrorPage";
    	}
    }
	
    /******************secrure Pages*********************/
    
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(Model model,HttpServletRequest req) {   	
    	model.addAttribute("ab", new AuthorBean());
    	model.addAttribute("msg","Enter Your Login Details");
    	loginService = new LoginService();   	
    	if(session != null && loginService.getLogout(session.getAttribute("user").toString())){  	
	    	if(session == null){
	    		return "index";
	    	}
		    	System.out.println("SID in logout B"+session.getId());    	 
		    	session = req.getSession(false);    	
		    	//System.out.println("SID in logout B"+session.getId());   	    	
		    	if(session != null){
		    		session.invalidate();
		    	}    	
		    	session = req.getSession(false);
		    	if(session == null){
		    		model.addAttribute("msg","Thanks for your visit.");
		    	}else{ 
		    		model.addAttribute("msg","Problem in logout");
		    	} 
    	}	    	   		
		return "loginErrorPage";   
    }
    
    
    public boolean checkingUserSessionStatus(HttpServletRequest req){  
    	session = req.getSession(false);  	
    	if(session != null )
    		return true;
    	
    	return false;
    }

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("session created");		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("s Destroy");
		loginService = new LoginService();
		if(loginService.getLogout(userid)){
			System.out.println("logout status changed.");
		}
		
	}

   
}
