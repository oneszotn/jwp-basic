package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info(req.getParameter("userId"));
    	
    	User user = DataBase.findUserById(req.getParameter("userId"));
 		if(user == null){
 			log.debug("User Not Found!!");
 			resp.sendRedirect("/user/login_failed.html");
 		}else{
     		if(user.getPassword().equals(req.getParameter("password"))){
     			log.debug("login success!!");
     			
     			HttpSession session = req.getSession();
     			session.setAttribute("user", user);
     			resp.sendRedirect("/user/list");
     		}else{
     			log.debug("Password Mismatch");
     			resp.sendRedirect("/user/login_failed.html");
     		}
 		}
	
    }
}
