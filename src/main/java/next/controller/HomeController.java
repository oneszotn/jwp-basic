package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

public class HomeController extends AbstractController{
	private QuestionDao questionDao = new QuestionDao();
    
	@Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //req.setAttribute("questions", questionDao.findAll());
        //return new JspView("home.jsp");
        
        return jspView("home.jsp").addObject("questions", questionDao.findAll());
        
    }
}
