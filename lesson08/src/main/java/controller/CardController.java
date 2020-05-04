package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<Product, Integer> listProduct;
		request.setAttribute("imgHeight", "100px");
		if (session.getAttribute(CART) != null) {
			listProduct = (Map) session.getAttribute(CART);
		} else {
			listProduct = new HashMap<Product, Integer>();
		}

		request.setAttribute("listProducts", listProduct);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Cart.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBWorker worker = new DBWorker();
		HttpSession session = request.getSession();

		Map<Product, Integer> productsMap = new HashMap<Product, Integer>();

		if (session.getAttribute(CART) != null) {
			productsMap = (Map) session.getAttribute(CART);
		}
		Product product = worker.getProduct(Integer.valueOf(request.getParameter("idProduct")));
		if (productsMap.containsKey(product)) {
			productsMap.put(product, productsMap.get(product) + 1);
		}else {
			productsMap.put(product, 1);
		}

		session.setAttribute(CART, productsMap);

		response.sendRedirect("/lesson08/products");
	}

}
