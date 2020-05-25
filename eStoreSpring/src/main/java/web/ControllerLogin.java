package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("request")
public class ControllerLogin {

	@Autowired
	private User user;
	
	@Autowired
	private MySQLUserDAO userDB;
	
	@Autowired
	private HttpSession session;
	
	private boolean showForm;
	
	private static final String NAME_OF_JSP = "login";
	private static final String SHOW_FORM_KEY = "showForm";
	private static final String CART_KEY ="CART_VALUE";
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getGetLoginPage() {
		showForm = (session.getAttribute("keyUser") != null);
		session.setAttribute(SHOW_FORM_KEY, showForm);
		
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		return NAME_OF_JSP;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "loginOut" })
	public void doLogOut(@RequestParam("loginOut") String loginOut, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		if (loginOut != null) {
			session.invalidate();
			session = request.getSession(true);
			showForm = false;
			session.setAttribute(SHOW_FORM_KEY, showForm);
			session.removeAttribute("keyUser");
			session.removeAttribute("currentUserName");
			PrintWriter out;
			
			try {
				out = response.getWriter();
				out.write("SIGN IN");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "logOut" })
	public String getPostLoginPage(@RequestParam("logOut") String logOut, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		if (logOut != null) {
			session.invalidate();
			session = request.getSession(true);
			showForm = false;
			session.setAttribute(SHOW_FORM_KEY, showForm);
			session.removeAttribute("access");
			session.removeAttribute("keyUser");
			session.removeAttribute("currentUserName");
		}

		return NAME_OF_JSP;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, params = { "login", "password" })
	public String getPostLoginPage(@RequestParam("login") String login, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {

		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		if (login != null || password != null) {
			user = new User();
			user.setLogin(login);
			user.setPassword(password);
			request.setAttribute("currentUser", user);
			userDB.setUser(user);
			session.setAttribute("access", userDB.getAccess());
			
			if (userDB.getAccess().equals("Successfully logged")) {
				session.setAttribute("keyUser", "sessionCheck");
				showForm = false;
				session.setAttribute(SHOW_FORM_KEY, showForm);
				session.setAttribute("access", userDB.getAccess());
			}

			if (session.getAttribute("keyUser") != null) {
				session.setAttribute("currentUserName", "Hello " + userDB.getUserName() + "!");
				showForm = (session.getAttribute("keyUser") != null) ? true : false;
				session.setAttribute(SHOW_FORM_KEY, showForm);
			}
		} else {
			getGetLoginPage();
		}

		return NAME_OF_JSP;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getPostLoginPage(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("keyUser") == null) {
			showForm = false;
		}
		
		session.setAttribute(SHOW_FORM_KEY, showForm);
		
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}

		return NAME_OF_JSP;
	}
}
