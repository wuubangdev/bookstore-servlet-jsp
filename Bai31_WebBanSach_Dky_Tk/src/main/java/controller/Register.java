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

/**
 * Servlet implementation class Register
 */
@WebServlet("/do-register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String customerName = request.getParameter("fullName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String address= request.getParameter("address");
		String receiAddress= request.getParameter("receiAddress");
		String buyAddress = request.getParameter("buyAddress");
		String phoneNumber = request.getParameter("phoneNumber");
		String email= request.getParameter("email");
		
		String isRecei_email= request.getParameter("isRecei_email");
		int recei_email = 0;
		recei_email = (isRecei_email==null)?0:1;
		
		String url ="";
		String err = "";
		
		HttpSession session = request.getSession();
		
		if (!password.equals(rePassword)) {
			err = "Mật khẩu nhập lại không khớp!";
		} else if (CustomerDAO.getInstance().hasUserName(userName)) {
			err = "Tài khoản đã tồn tại";
		}
		
		if (err.length()>0) {
			url ="/register.jsp";
			request.setAttribute("errMsg", err);
			Customer customer_clearpass = 
					new Customer("", customerName, userName,"", gender, 
							address, receiAddress, buyAddress, Date.valueOf(dob), 
							phoneNumber, email, recei_email);
			session.setAttribute("customer", customer_clearpass);
		} else {
			url ="/success.jsp";
			String randomId; 
			Customer customerChek;
			do {
				Random rd = new Random();
				int i = rd.nextInt(99,1000);
				randomId = "KH_"+i;
				customerChek = new Customer(randomId);
			} while (CustomerDAO.getInstance().selectById(customerChek)!=null);
			Customer customer = 
					new Customer(randomId, customerName, userName, password, gender, 
							address, receiAddress, buyAddress, Date.valueOf(dob), 
							phoneNumber, email, recei_email);
			CustomerDAO.getInstance().insert(customer);
		}
		
		RequestDispatcher rDispatcher = getServletContext().getRequestDispatcher(url);
		rDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
