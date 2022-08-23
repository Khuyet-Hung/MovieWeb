package com.assm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.assm.dao.MovieDAO;
import com.assm.model.Movie;
import com.assm.model.User;
import com.assm.utils.ShareHelper;

/**
 * Servlet implementation class ManagerMovieSevlet
 */
@MultipartConfig
@WebServlet({"/ManagerMovieSevlet","/ManagerMovieSevlet/create", "/ManagerMovieSevlet/update","/ManagerMovieSevlet/delete","/ManagerMovieSevlet/new", "/ManagerMovieSevlet/edit"})
public class ManagerMovieSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerMovieSevlet() {
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
		
		Movie movie = null;
		if(url.contains("delete")){
			delete(request, response);
		}else if(url.contains("update")){
			update(request, response);
		}else if(url.contains("edit")){
			edit(request, response);
		}else if(url.contains("reset")){
			request.setAttribute("user", movie);
		}
		findAll(request, response);
		setUser(request, response);
		request.getRequestDispatcher("/views/manager/manager-video.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getRequestURL().toString();
		
		if(url.contains("create")) {
			insert(request, response);
		}else if(url.contains("delete")){
			delete(request, response);
		}else if(url.contains("update")){
			update(request, response);
		}else if(url.contains("new")){
			reset(request, response);
		}
		findAll(request, response);
		setUser(request, response);
		request.getRequestDispatcher("/views/manager/manager-video.jsp").forward(request, response);
	}
	


	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			MovieDAO dao = new MovieDAO();
			List<Movie> list = dao.findAll();
			request.setAttribute("movies", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}	
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			Movie movie = new Movie();
			BeanUtils.populate(movie, request.getParameterMap());
			MovieDAO dao = new MovieDAO();
			
		    Part partBanr = request.getPart("background");
		    Part partPost = request.getPart("photo");
		    String fileNameBanr = Paths.get(partBanr.getSubmittedFileName()).getFileName().toString();
		    String fileNamePost = Paths.get(partPost.getSubmittedFileName()).getFileName().toString();
		    movie.setBackground(fileNameBanr);
		    movie.setPhoto(fileNamePost);
		    
			dao.create(movie);
			request.setAttribute("message", "Created successful!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String movieId = request.getParameter("id");
			MovieDAO dao = new MovieDAO();
			dao.delete(movieId);
			request.setAttribute("message", "Delete successful!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Movie movie = new Movie();
			BeanUtils.populate(movie, request.getParameterMap());
			MovieDAO dao = new MovieDAO();
			
			Part partBanr = request.getPart("background");
		    Part partPost = request.getPart("photo");
		    String fileNameBanr = Paths.get(partBanr.getSubmittedFileName()).getFileName().toString();
		    String fileNamePost = Paths.get(partPost.getSubmittedFileName()).getFileName().toString();
		    movie.setBackground(fileNameBanr);
		    movie.setPhoto(fileNamePost);
		    
			dao.update(movie);
			request.setAttribute("message", "Update successful!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	private void reset(HttpServletRequest request, HttpServletResponse response) {
		try {
			Movie movie = new Movie();
			request.setAttribute("movie", movie);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		try {
			String movieId = request.getParameter("id");
			MovieDAO dao = new MovieDAO();
			Movie movie = dao.findByID(movieId);
			request.setAttribute("movie", movie);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}
	
	private void setUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = ShareHelper.USER;
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
