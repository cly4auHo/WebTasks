package web;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.Product;

@Controller
public class ControllerProducts {

	@Autowired
	private MySQLProductDAO productDB;

	@Autowired
	private HttpSession session;
	
	private static final String CART_COUNT = "CART_VALUE";
	private static final String PARAM_OF_HEIGHT = "imgHeight";
	private static final String LIST_OF_PRODUCTS_KEY = "listProducts";
	private static final String NAME_OF_JSP_WITH_PRODUCTS = "products";
	private static final String COUNT_OF_PRODUCTS = "count";
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getGetProductsPage(ModelMap model) {
		List<Product> listProduct = productDB.getProducts();
		model.addAttribute(LIST_OF_PRODUCTS_KEY, listProduct);
		model.addAttribute(COUNT_OF_PRODUCTS, listProduct.size());
		
		if (session.getAttribute(CART_COUNT) == null) {
			session.setAttribute(CART_COUNT, 0);
		}
		
		model.addAttribute(PARAM_OF_HEIGHT, "200px");
		return NAME_OF_JSP_WITH_PRODUCTS;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, params = { "category" })
	public String getGetProductsPageByCategory(@RequestParam(value = "category", required = false) String category, ModelMap model) {
		List<Product> listProduct = productDB.getProducts();

		if (category != null) {
			listProduct = productDB.getProductsCategory(category);
			model.addAttribute(LIST_OF_PRODUCTS_KEY, listProduct);
			model.addAttribute(COUNT_OF_PRODUCTS, listProduct.size());
		}
		
		if (session.getAttribute(CART_COUNT) == null) {
			session.setAttribute(CART_COUNT, 0);
		}
		
		model.addAttribute(PARAM_OF_HEIGHT, "200px");
		return NAME_OF_JSP_WITH_PRODUCTS;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, params = { "id" })
	public String getGetProductsPageById(@RequestParam(value = "id", required = false) String id, ModelMap model) {
		List<Product> listProduct = productDB.getProducts();

		if (id != null) {
			int idProduct = Integer.valueOf(id);
			Product product = productDB.getProductById(idProduct);
			model.addAttribute(LIST_OF_PRODUCTS_KEY, product);
			model.addAttribute(COUNT_OF_PRODUCTS, 1);
		}else {
			model.addAttribute(LIST_OF_PRODUCTS_KEY, listProduct);
			model.addAttribute(COUNT_OF_PRODUCTS, listProduct.size());
		}
		
		if (session.getAttribute(CART_COUNT) == null) {
			session.setAttribute(CART_COUNT, 0);
		}
		
		model.addAttribute(PARAM_OF_HEIGHT, "200px");
		return NAME_OF_JSP_WITH_PRODUCTS;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, params = { "idProduct" })
	public String getPostProductsPage(HttpSession session, ModelMap model, @RequestParam("idProduct") String idProd) {
		if (session.getAttribute(CART_COUNT) == null) {
			session.setAttribute(CART_COUNT, 0);
		}
					
		if (idProd != null) {
			int idProduct = Integer.valueOf(idProd);			
			Product product = productDB.getProductById(idProduct);
			model.addAttribute(LIST_OF_PRODUCTS_KEY, product);
			model.addAttribute(COUNT_OF_PRODUCTS, 1);
		} else {
			List<Product> listProduct = productDB.getProducts();
			model.addAttribute(LIST_OF_PRODUCTS_KEY, listProduct);
			model.addAttribute(COUNT_OF_PRODUCTS, listProduct.size());			
		}
		
		model.addAttribute(PARAM_OF_HEIGHT, "200px");
		return NAME_OF_JSP_WITH_PRODUCTS;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String getPostProductsPage(HttpSession session, ModelMap model) {
		if (session.getAttribute(CART_COUNT) == null) {
			session.setAttribute(CART_COUNT, 0);
		}

		List<Product> listProduct = productDB.getProducts();
		model.addAttribute(LIST_OF_PRODUCTS_KEY, listProduct);
		model.addAttribute(COUNT_OF_PRODUCTS, listProduct.size());
		model.addAttribute(PARAM_OF_HEIGHT, "200px");
		return NAME_OF_JSP_WITH_PRODUCTS;
	}
}
