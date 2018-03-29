package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import next.service.QnaService;

public class ApiDeleteQnaController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private QnaService qnaService = QnaService.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        
        Question question = questionDao.findById(questionId);
        if(question.getCountOfComment() > 0){
        	throw new IllegalStateException("답변이 있으면 삭제할 수 없습니다..");
        }
        
        try {
            qnaService.deleteQuestion(questionId, UserSessionUtils.getUserFromSession(req.getSession()));
            return jsonView().addObject("result", Result.ok());
        } catch (Exception e) {
            return jsonView().addObject("result", Result.fail(e.getMessage()));
        }
        
        
    }
}
