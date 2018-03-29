package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class UpdateAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateAnswerController.class);

    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
    	if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
    	
    	
    	long questionId = Long.parseLong(req.getParameter("questionId"));
    	
    	Question question = questionDao.findById(questionId);
    	
    	
        Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"),
                Long.parseLong(req.getParameter("questionId")));
        log.debug("answer : {}", answer);

        Answer savedAnswer = answerDao.insert(answer);
        
        
        questionDao.addCount(Long.parseLong(req.getParameter("questionId")));
        
        
        return jsonView().addObject("answer", savedAnswer);
    }
}
