package mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.DAO;
import mvc.model.Form;

@Controller
public class TarefasController {
	DAO dao = new DAO();
	
    /* Mostra a lista de todos os Posts */  
    @RequestMapping("/board")  
    public String viewemp(Model m){  
        List<Form> list = dao.AllForms();  
        m.addAttribute("list",list);
        return "board";  
    }
    
    /* Mostra o formulario com o post que deseja editar*/  
    @RequestMapping("editpost")  
    public String edit(String editid,String editquestion,HttpSession ses){
    	Form form = new Form();
    	form.setId(Integer.parseInt(editid));
    	form.setQuestion(editquestion);
    	ses.setAttribute("form", form);
        return "formTarefaEdit";  
    }
    
    /* Atualiza um Post */  
    @RequestMapping("/editsave")  
    public String editsave(Form p){
    	DAO dao = new DAO();
    	System.out.println(p.getId());
        dao.edita(p.getAnswer(), p.getId());  
        return "redirect:board";  
    }
    
    /* Deleta o post da id tirada da URL e redireciona para Home */  
    @RequestMapping("deletpost")  
    public String delete(String deletid,HttpSession ses){  
    	DAO dao = new DAO();
        dao.remove(Integer.parseInt(deletid)); 
        return "redirect:board";  
    } 

	@RequestMapping("newPost")
	public String form(String username,HttpSession ses) {
		ses.setAttribute("username", username);
		return "formTarefa";
	}

	@RequestMapping("/save")
	public String adiciona(Form form,String username) {
		DAO dao = new DAO();
		System.out.println(username);
		dao.adiciona(form.getQuestion(), form.getUserId(),username);
		return "redirect:board";
	}

}