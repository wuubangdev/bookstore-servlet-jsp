package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CustomerDAO;
import model.Customer;
import util.Encode;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		password = Encode.encodePass(password);

		String url = "";
		String errMsg = "";

		Customer customer = new Customer();
		customer.setUsername(userName);
		customer.setPassword(password);

		HttpSession session = request.getSession();

		if (userName.length() == 0 || password.length() == 0) {
			errMsg += "Vui lòng nhập tài khoản đăng nhập và mật khẩu";
		} else if (CustomerDAO.getInstance().selectByUserAndPass(customer) == null) {
			errMsg = "Tên đăng nhập hoặc mật khẩu không tồn tại";
		}

		if (errMsg.length() > 0) {
			request.setAttribute("errMgs", errMsg);
			url = "/login.jsp";
		} else {
			url = "/index.jsp";
			session.setAttribute("userName", userName);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
