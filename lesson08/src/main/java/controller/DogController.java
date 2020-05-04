package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;

/**
 * Servlet implementation class DogController
 */
public class DogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Dog dog;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DogController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/NewDog.jsp");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("name")!=null || request.getParameter("age") != null) {
			dog = new Dog(request.getParameter("name"), Integer.valueOf(request.getParameter("age")));
			request.setAttribute("ourDog", dog);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/GoodBoy.jsp");
			rd.forward(request, response);
		} else {
			doGet(request, response);
		}

	}

}
