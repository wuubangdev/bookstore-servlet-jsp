package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

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
 * Servlet implementation class CustomerController
 */
@WebServlet("/customer-controller")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String activity = request.getParameter("activity");
		
		if (activity.equals("register")) {
			register(request, response);
		} else if (activity.equals("login")) {
			login(request, response);
		} else if (activity.equals("logout")) {
			logout(request, response);
		} else if (activity.equals("changePass")) {
			changePass(request, response);
		} else if (activity.equals("changeInfo")) {
			changeInfo(request, response);
		}
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
	private void register(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customerName = request.getParameter("fullName");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String rePassword = request.getParameter("rePassword");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String address = request.getParameter("address");
			String receiAddress = request.getParameter("receiAddress");
			String buyAddress = request.getParameter("buyAddress");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");

			String isRecei_email = request.getParameter("isRecei_email");
			int recei_email = 0;
			recei_email = (isRecei_email == null) ? 0 : 1;

			String url = "";
			String err = "";

			if (!password.equals(rePassword)) {
				err = "Mật khẩu nhập lại không khớp!";
			} else if (CustomerDAO.getInstance().hasUserName(userName)) {
				err = "Tài khoản đã tồn tại";
			}

			if (err.length() > 0) {
				url = "/customer-page/register.jsp";
				request.setAttribute("errMsg", err);
				Customer customer_clearpass = new Customer("", customerName, userName, "", gender, address,
						receiAddress, buyAddress, Date.valueOf(dob), phoneNumber, email, recei_email);
				request.setAttribute("customer", customer_clearpass);
			} else {
				url = "/customer-page/success.jsp";
				String randomId;
				Customer customerChek;
				do {
					Random rd = new Random();
					int i = rd.nextInt(99, 1000);
					randomId = "KH_" + i;
					customerChek = new Customer(randomId);
				} while (CustomerDAO.getInstance().selectById(customerChek) != null);

				password = Encode.encodePass(password);
				Customer customer = new Customer(randomId, customerName, userName, password, gender, address,
						receiAddress, buyAddress, Date.valueOf(dob), phoneNumber, email, recei_email);
				CustomerDAO.getInstance().insert(customer);
			}

			RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
			rDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
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
				url = "/customer-page/login.jsp";
			} else {
				url = "/index.jsp";
				session.setAttribute("customer", CustomerDAO.getInstance().selectByUserAndPass(customer));
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void changePass(HttpServletRequest request, HttpServletResponse response) {
			try {
				String passwordOld = request.getParameter("passwordOld");
				passwordOld = Encode.encodePass(passwordOld);
				String passwordNew = request.getParameter("passwordNew");
				passwordNew = Encode.encodePass(passwordNew);
				String passwordReNew = request.getParameter("passwordReNew");
				passwordReNew = Encode.encodePass(passwordReNew);
				
				String err ="";
				String url ="/customer-page/change-password.jsp";
				
				HttpSession session = request.getSession();
				Object obj = session.getAttribute("customer");
				Customer customer = null;
				
				if (obj!= null) {
					customer = (Customer) obj;
				}
				
				if (customer!= null) {
					if (!passwordOld.equals(customer.getPassword())) {
						err = "Nhập mật khẩu cũ chưa chính xác!";
					} else if (!passwordNew.equals(passwordReNew)) {
						err = "Mật khẩu nhập lại chưa chính xác!";
					} else if (passwordNew.equals(passwordOld)) {
						err = "Mật khẩu mới giống với mật khẩu cũ!";
					} 
					if (err.length() >0 ) {
						request.setAttribute("err", err);
					} else {
						Customer customer_select = CustomerDAO.getInstance().selectById(customer);
						int check = CustomerDAO.getInstance().updatePass(customer_select, passwordNew);
						if (check!=0) {
							url = "/customer-page/success.jsp";
							session.setAttribute("customer", CustomerDAO.getInstance().selectById(customer_select));						
						}
					}
				}
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	private void changeInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customerName = request.getParameter("fullName");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String address = request.getParameter("address");
			String receiAddress = request.getParameter("receiAddress");
			String buyAddress = request.getParameter("buyAddress");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");

			String url = "";
			String err = "";

			HttpSession session = request.getSession();
			
			Customer customer = (Customer) session.getAttribute("customer");
			
			if (customer!= null) {
				if (err.length() > 0) {
					url = "/customer-page/change-info.jsp";
					request.setAttribute("err", err);
				} else {
					url = "/customer-page/success.jsp";
					Customer customer_change = new Customer();
					customer_change.setCustomerId(customer.getCustomerId());
					customer_change.setCustomerName(customerName);
					customer_change.setGender(gender);
					customer_change.setDob(Date.valueOf(dob));
					customer_change.setAddress(address);
					customer_change.setreceiAddress(receiAddress);
					customer_change.setbuyAddress(buyAddress);
					customer_change.setPhoneNumber(phoneNumber);
					customer_change.setEmail(email);
					int check = CustomerDAO.getInstance().updateInfo(customer_change);
					if (check > 0) {
						session.setAttribute("customer", CustomerDAO.getInstance().selectById(customer_change));
					}
				}
			}
			RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
			rDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
