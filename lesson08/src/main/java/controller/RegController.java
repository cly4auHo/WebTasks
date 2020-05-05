package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DBWorker;

public class RegController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String regexMail = "^(.+)@(.+)$";
	private static final String regexPassword = "(?=(.*[a-zA-Z0-9]){2})";
	private static final int minLenghtPassword = 8;
	private StringBuilder errorText;
	private static final String KEY_ERROR = "erroeText";

	public RegController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reg.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String repassword = request.getParameter("re-password");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String comments = request.getParameter("comments");
		String amigo = request.getParameter("amigo");
		String name = request.getParameter("name");

		errorText = new StringBuilder("<ul>");
		boolean existError = false;

		if (login != null) {
			existError = false;

			if (login.length() == 0) {
				existError = true;
				errorText.append("<li>Empty Login</li>");
			} else {
				Pattern pattern = Pattern.compile(regexMail);
				Matcher matcher = pattern.matcher(login);

				if (!matcher.matches()) {
					existError = true;
					errorText.append("<li>email must be clever</li>");
				}
			}

			if (isEmptyOrNull(password)) {
				existError = true;
				errorText.append("<li>Empty Password</li>");
			} else {

				if (password.length() < minLenghtPassword) {
					existError = true;
					errorText.append("<li>Password must have min 8 characters</li>");
				}

				Pattern pattern = Pattern.compile(regexPassword);
				Matcher matcher = pattern.matcher(password);

				if (!matcher.find()) {
					existError = true;
					errorText.append("<li>Password must have: 2 numbers, 2 big and small letters</li>");
				}
			}

			if (isEmptyOrNull(repassword)) {
				existError = true;
				errorText.append("<li>Empty RePassword</li>");
			}

			if (!password.equals(repassword)) {
				existError = true;
				errorText.append("<li>Password and RePassword must be equals</li>");
			}

			if (isEmptyOrNull(age)) {
				existError = true;
				errorText.append("<li>Age field is empty</li>");
			} else {
				int ageInt = Integer.parseInt(age);

				if (ageInt < 12 || ageInt > 100) {
					existError = true;
					errorText.append("<li>age must be more than 11 and less than 100</li>");
				}
			}

			if (gender == null) {
				existError = true;
				errorText.append("<li>chose your destiny</li>");
			}

			if (isEmptyOrNull(comments)) {
				existError = true;
				errorText.append("<li>Write comments pls</li>");
			}

			errorText.append("</ul>");
		}

		if (existError) {
			request.setAttribute(KEY_ERROR, errorText.toString());
			doGet(request, response);
		} else {
			request.setAttribute(KEY_ERROR, null);
			DBWorker worker = new DBWorker();
			worker.addUser(name, repassword, login, Integer.valueOf(age), gender, comments, address);
			
			RequestDispatcher rd = request.getRequestDispatcher("/lesson08/CardController");
			rd.forward(request, response);
		}
	}

	private boolean isEmptyOrNull(String str) {
		return (str == null || str.isEmpty() ? true : false);
	}
}
