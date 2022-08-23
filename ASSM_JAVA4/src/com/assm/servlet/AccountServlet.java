package com.assm.servlet;

import java.io.IOException;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.assm.dao.MovieDAO;
import com.assm.dao.UserDAO;
import com.assm.model.Movie;
import com.assm.model.User;
import com.assm.utils.ShareHelper;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet({ "/AccountServlet", "/AccountServlet/Login-form", "/AccountServlet/Sign-in", "/AccountServlet/Sign-up" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		if (url.contains("/AccountServlet/Sign-in")) {
			if (SignIn(request, response)) {
				request.setAttribute("Tài khoản hoặc mật khẩu không chính xác", "message");
				request.getRequestDispatcher("/Main").forward(request, response);
			} else {
				request.getRequestDispatcher("/views/account/Login.jsp").forward(request, response);
			}
		} else if (url.contains("/AccountServlet/Sign-up")) {
			SignUp(request, response);
			System.out.println(ShareHelper.USER.getUserId());
			request.getRequestDispatcher("/Main").forward(request, response);
		}
	}

	private void SignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			UserDAO dao = new UserDAO();
			dao.create(user);
			ShareHelper.USER = user;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean SignIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userID = request.getParameter("UserID");
			String password = request.getParameter("Password");
			UserDAO dao = new UserDAO();
			User user = dao.findByID(userID);
			if (user == null) {
				System.out.println("User không tồn tại");
				return false;
			} else if (password.equals(user.getPassword())) {
				ShareHelper.USER = user;
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
