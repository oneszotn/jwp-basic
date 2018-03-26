package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	
    	Long questionId = Long.parseLong(req.getParameter("questionId"));
    	
    	QuestionDao qd = new QuestionDao();
    	Question qu = qd.qnaDetail(questionId);
    	
    	req.setAttribute("question", qu);
        return "/qna/show.jsp";
    }
}
