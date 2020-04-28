package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SomeString;

/**
 * Servlet implementation class SomeStringController
 */
public class SomeStringController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SomeStringController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SomeString someString1 = new SomeString();
		SomeString someString2 = new SomeString();
		SomeString someString3 = new SomeString();

		someString1.setLine("Something1");
		someString2.setLine("Something2");
		someString3.setLine("Something3");

		List<SomeString> listSomeStrings = new ArrayList<SomeString>();
		listSomeStrings.add(someString1);
		listSomeStrings.add(someString2);
		listSomeStrings.add(someString3);

		request.setAttribute("key", listSomeStrings);
		RequestDispatcher rd = request.getRequestDispatcher("/SomeString.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
