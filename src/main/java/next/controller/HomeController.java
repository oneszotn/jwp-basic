package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;

public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;
    
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	req.setAttribute("users", DataBase.findAll());
    	return "home.jsp";
    }
}
