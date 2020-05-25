package web;

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
public class ControllerRegister {

	@Autowired
	private User user;

	@Autowired
	private MySQLUserDAO userDB;
	
	@Autowired
	private HttpSession session;
	
	private static final String NAME_OF_JSP = "register";
	private static final String CART_KEY = "CART_VALUE";
	private static final String REGISTRATION_KEY = "registration";
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getGetRegisterPage() {
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		return NAME_OF_JSP;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST, params = { "login", "name", "password", "age",
			"gender", "comments", "address", "amigo" })
	public String getPostRegisterPage(@RequestParam("login") String login, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("age") String age,
			@RequestParam("gender") String gender, @RequestParam("comments") String comments,
			@RequestParam("address") String address, @RequestParam("amigo") String amigo, HttpSession session,
			ModelMap model) {
		
		if (session.getAttribute(CART_KEY) == null) {
			session.setAttribute(CART_KEY, 0);
		}
		
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setAge(Integer.valueOf(age));
		user.setGender(gender);
		user.setComments(comments);
		user.setAddress(address);
		user.setAmigo(amigo);
		userDB.setUser(user);
		model.addAttribute("checkLogin", userDB.getCheckLogin());

		if (!userDB.getCheckLogin()) {
			model.addAttribute(REGISTRATION_KEY, userDB.getRegistration());
		} else {
			model.addAttribute(REGISTRATION_KEY, "fault");
		}

		return NAME_OF_JSP;
	}
}
