package net.codejava.javaee.bank;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

	@Override
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		customerDAO = new CustomerDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCustomer(request, response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCustomer(request, response);
				break;
			default:
				listCustomer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listCustomer = customerDAO.listAllCustomer();
		request.setAttribute("listCustomer", listCustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Customer.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Customer existingCustomer = customerDAO.getCustomer(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Customer.jsp");
		request.setAttribute("customer", existingCustomer);
		dispatcher.forward(request, response);

	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String accountno = request.getParameter("accountno");
		float balance = Float.parseFloat(request.getParameter("balance"));

		Customer newCustomer = new Customer(name, accountno, balance);
		customerDAO.insertCustomer(newCustomer);
		response.sendRedirect("list");
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String accountno = request.getParameter("accountno");
		float balance = Float.parseFloat(request.getParameter("balance"));

		Customer customer = new Customer(id, name, accountno, balance);
		customerDAO.updateCustomer(customer);
		response.sendRedirect("list");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Customer customer = new Customer(id);
		customerDAO.deleteCustomer(customer);
		response.sendRedirect("list");

	}

}
