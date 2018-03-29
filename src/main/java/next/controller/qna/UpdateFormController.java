package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class UpdateFormController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateFormController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

    	if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
    	
    	long questionId = Long.parseLong(req.getParameter("questionId"));
    	Question question = questionDao.findById(questionId);
    	
    	
    	if (!question.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }
    	
    	
        return jspView("/qna/updateForm.jsp").addObject("question", question);
    }
}
