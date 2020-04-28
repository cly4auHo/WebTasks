package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import data.DBWorker;
import model.Product;

public class CardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CART = "CART";

	public CardController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Product> listProduct;
		request.setAttribute("imgHeight", "100px");
		if (session.getAttribute(CART) != null) {
			listProduct = (List<Product>) session.getAttribute(CART);
		} else {
			listProduct = new ArrayList<Product>();
		}

		request.setAttribute("listProducts", listProduct);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Products.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBWorker worker = new DBWorker();
		HttpSession session = request.getSession();
		List<Product> listProduct;

		if (session.getAttribute(CART) != null) {
			listProduct = (List<Product>) session.getAttribute(CART);
		} else {
			listProduct = new ArrayList<Product>();
		}

		listProduct.addAll(worker.getProduct(Integer.valueOf(request.getParameter("idProduct"))));
		session.setAttribute(CART, listProduct);
		request.setAttribute("listProducts", listProduct);
		response.sendRedirect("/lesson08/products");
	}

}
