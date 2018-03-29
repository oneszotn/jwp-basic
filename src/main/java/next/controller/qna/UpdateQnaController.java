package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class UpdateQnaController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateQnaController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	
    	Question question = new Question(req.getParameter("writer"), req.getParameter("title"), req.getParameter("contents"));
    	long questionId = Long.parseLong(req.getParameter("questionId"));
    	question.setQuestionId(questionId);
    	
    	
    	questionDao.update(question);
        
        return jspView("redirect:/");
    }
}
