package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;

public class delAnswerController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(delAnswerController.class);
	
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	
    	
    	log.info("=========================" + req.getParameter("answerId"));
    	
    	AnswerDao answerDao = new AnswerDao();
    	answerDao.delete(Long.parseLong(req.getParameter("answerId")));
    	
    	ObjectMapper mapper = new ObjectMapper();
    	resp.setContentType("application/json;charset=UTF-8");  
    	PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(Result.ok()));  
    	
    	return null; 
    }
}
