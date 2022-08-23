package com.assm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assm.dao.MovieDAO;
import com.assm.model.Movie;
import com.assm.model.User;
import com.assm.utils.ShareHelper;

/**
 * Servlet implementation class Main
 */
@WebServlet({"/Main", "/Main/account/sign-in", "/Main/manager-video", "/Main/log-out"})
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		if(url.contains("account/sign-in")) {
			request.getRequestDispatcher("/views/account/Login.jsp").forward(request, response);
		}else if(url.contains("manager-video")) {
			request.getRequestDispatcher("/ManagerMovieSevlet").forward(request, response);
		}else if(url.contains("log-out")) {
			ShareHelper.USER = null;
			request.getRequestDispatcher("/Main").forward(request, response);
		}else {
			findAll(request, response);
			User user = ShareHelper.USER;
			if(user != null) {
				request.setAttribute("user", user);
			}
			request.getRequestDispatcher("/views/index.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			MovieDAO dao = new MovieDAO();
			List<Movie> list = dao.findAll();
			request.setAttribute("movies", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
