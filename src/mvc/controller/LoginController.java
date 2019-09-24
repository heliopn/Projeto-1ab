package mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.DAO;
import mvc.model.User;

@Controller
public class LoginController {
	DAO dao = new DAO();
	
    /* Verifica Login */  
    @RequestMapping("login")  
    public String viewemp(String username,String password,HttpSession ses){  
    	DAO dao = new DAO();
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	if (dao.VerificaUser(user)) {
            ses.setAttribute("username", username);
            ses.setAttribute("password", password);
            return "redirect:board"; 
    	} else {
    		return "errorPage";
    	}
 
    }
    
    /* Faz o cadastro */  
    @RequestMapping("sign")
    public String edit(String username,String password,HttpSession ses){
    	DAO dao = new DAO();
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	dao.AdicionaUser(user);
    	ses.setAttribute("username", username);
        ses.setAttribute("password", password);
        return "redirect:board";  
    }
}