package com.assm.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assm.dao.FavoriteDAO;
import com.assm.dao.MovieDAO;
import com.assm.dao.UserDAO;
import com.assm.model.Favorite;
import com.assm.model.Movie;
import com.assm.model.User;
import com.assm.utils.ShareHelper;

/**
 * Servlet implementation class MovieDetailServlet
 */
@WebServlet({"/MovieDetailServlet", "/MovieDetailServlet/like"})
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetailServlet() {
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
		if(url.contains("like")) {
			String userId = ShareHelper.USER.getUserId();
			String movieId = request.getQueryString().toString();
			
			Favorite fav = new Favorite();
			UserDAO userdao = new UserDAO();
			MovieDAO moviedao = new MovieDAO();
			fav.setUser(userdao.findByID(userId));
			fav.setMovie(moviedao.findByID(movieId));
			fav.setLikedate(new Date());
			
			FavoriteDAO dao = new FavoriteDAO();
			Favorite favr = dao.findLike(movieId, userId);
			if(favr == null) {
				dao.create(fav);
			}else {
				dao.delete(favr.getIdfv());
			}
			request.getRequestDispatcher("/MovieDetailServlet").forward(request, response);
		}else {
			if(ShareHelper.USER != null) {
				load(request, response);
			}else {
				request.getRequestDispatcher("/views/account/Login.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void findByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getQueryString().toString();
		try {
			MovieDAO dao = new MovieDAO();
			Movie movie = dao.findByID(id);
			request.setAttribute("movie", movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void showLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String movieId = request.getQueryString().toString();
		String userId = ShareHelper.USER.getUserId();
		try {
			FavoriteDAO dao = new FavoriteDAO();
			Favorite fav =  dao.findLike(movieId,userId);
			if(fav != null) {
				request.setAttribute("checkLike", true);
			}else {
				request.setAttribute("checkLike", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			showLike(request, response);
			findByID(request, response);
			request.getRequestDispatcher("/views/moviedetail/MovieDetail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
