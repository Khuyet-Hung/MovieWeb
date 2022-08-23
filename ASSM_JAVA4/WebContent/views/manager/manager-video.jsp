<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="com.assm.model.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="/ASSM_JAVA4/css/style.css">

<base href="/ASSM_JAVA4/">
</head>

<body>

	<nav id="sidebar-wrapper">
        <div class="wap">
            <div class="myrow">
                <div class="logo col-1">
                    <a href="/ASSM_JAVA4/Main"> <img src="/ASSM_JAVA4/img/Web/Logo.jpg" alt="">
                    </a>
                </div>

                <div class="listmenu-item col-6">
                    <div class="movies hover_text">
                        <a href="#"> <span>Movies</span>
                            <div class="listfilm-item">
                                <ul class="sidebar-nav">
                                    <li class="sidebar-nav-item"><a class="js-scroll-trigger item" href="#UpcomingMovies">Coming
                                        Next</a></li>
                                    <li class="sidebar-nav-item"><a class="js-scroll-trigger item" href="#TopRatedMovies">Top
                                        Movies</a></li>
                                    <li class="sidebar-nav-item"><a class="js-scroll-trigger item" href="#LatestMovies">Latest
                                        Movies</a></li>
                                    <li class="sidebar-nav-item"><a class="js-scroll-trigger item" href="#EnglishMovies">English
                                        Movies</a></li>
                                </ul>
                            </div>
                        </a>
                    </div>
                    <div class="comic hover_text">
                        <a href=""> <span>Comic</span>
                        </a>
                    </div>
                    <div class="characters hover_text">
                        <a href=""> <span>Characters</span>
                        </a>
                    </div>
                    <div class="games hover_text">
                        <a href=""> <span>Games</span>
                        </a>
                    </div>
                    <div class="new hover_text">
                        <a href=""> <span>New</span>
                        </a>
                    </div>
                    <div class="more hover_text">
                        <a href=""> <span>More</span>
                        </a>
                    </div>
                </div>


                <div class="search col-3">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Seach">
                    </div>
                    <i class="fa fa-search"></i>
                </div>

                <div class="avata col-1">
                    <c:choose>
                        <c:when test="${empty user}">
                            <div class="avatar" style="background-image: url(/ASSM_JAVA4/img/Avatar/default.jpg); background-repeat: no-repeat; background-size: cover;">
                        </c:when>
                        <c:when test="${not empty user}">
                            <div class="avatar" style="background-image: url(/ASSM_JAVA4/img/Avatar/${user.avatar});  background-repeat: no-repeat; background-size: cover;">
                        </c:when>
                    </c:choose>

                    <div class="acc_list">
                        <ul>
                            <a href="/ASSM_JAVA4/Main/account/sign-in">
                                <li>Sign In</li>
                            </a>
                            <a href="">
                                <li>Sign Up</li>
                            </a>
                            <a href="">
                                <li>Log out</li>
                            </a>
                            <c:if test="${user.role}">
                                <a href="/ASSM_JAVA4/Main/manager-video">
                                    <li>Manager</li>
                                </a>
                            </c:if>
                        </ul>
                    </div>
                    </div>
                    </div>
                </div>
            </div>
    </nav>
    
    
	<div class="container" style="margin-top: 100px">
    <div class="row">
		<div class="col">
			<c:if test="${not empty message}">
				<div class="alert alert-success">${message}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
		</div>
	</div>
	
		<form action="ManagerMovieSevlet" method="post" enctype="multipart/form-data">
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="Leave a comment here"
					id="MovieID" name="id"><c:out value="${movie.id}"></c:out></textarea>
				<label for="MovieID">Movie ID</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="Leave a comment here"
					id="MovieName" name="movieName"><c:out value="${movie.movieName}"></c:out></textarea>
				<label for="MovieName">Movie Name</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="Leave a comment here"
					id="Genre" name="genre"><c:out value="${movie.genre}"></c:out></textarea>
				<label for="Genre">Genre</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="Leave a comment here"
					id="MovieYear" name="movieYear"><c:out value="${movie.movieYear}"></c:out></textarea>
				<label for="MovieYear">Movie Year</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="Leave a comment here"
					id="floatingTextarea2" style="height: 100px" name="note"><c:out value="${movie.note}"></c:out></textarea>
				<label for="floatingTextarea2">Note</label>
			</div>
			<div class="mb-3">
				<label for="formFile" class="form-control">Banner Movie (Image)</label> <input accept="image/png, image/jpeg"
					class="form-control" type="file" id="formFile" name="background">
					<img width= "126px" alt="" src="/ASSM_JAVA4/img/Images/PosterImages/${movie.photo}">
			</div>
			<div class="mb-3">
				<label for="formFile" class="form-control">Poster Movie (Image)</label> <input accept="image/png, image/jpeg"
					class="form-control" type="file" id="formFile" name="photo">
					<img width="250px" alt="" src="/ASSM_JAVA4/img/MovieDetail/${movie.background}">
			</div>
			<hr>
			<button formaction="/ASSM_JAVA4/ManagerMovieSevlet/create" type="submit"
				class="btn btn-success">Create</button>
			<button formaction="/ASSM_JAVA4/ManagerMovieSevlet/delete" type="submit"
				class="btn btn-danger">Delete</button>
			<button formaction="/ASSM_JAVA4/ManagerMovieSevlet/update" type="submit"
				class="btn btn-primary">Update</button>
			<button formaction="/ASSM_JAVA4/ManagerMovieSevlet/new" type="submit"
				class="btn btn-outline-success">New</button>
		</form>

		<div class="row">
			<div class="col">
				<table class="table table-stripe">
					<tr>
						<td>Movie ID</td>
						<td>Movie Name</td>
						<td>Genre</td>
						<td>Movie Year</td>
						<td>Note</td>
						<td>Banner Movie</td>
						<td>Poster Movie</td>
						<td>&nbsp;</td>
					</tr>
					<c:forEach var="item" items="${movies}">
						<tr>
							<td>${item.id}</td>
							<td>${item.movieName}</td>
							<td>${item.genre}</td>
							<td>${item.movieYear}</td>
							<td>${item.note}</td>
							<td>${item.background}</td>
							<td>${item.photo}</td>
							<td>
								<a style="color: blue" href="/ASSM_JAVA4/ManagerMovieSevlet/edit?id=${item.id}">Edit</a>
								<a style="color: blue" href="/ASSM_JAVA4/ManagerMovieSevlet/delete?id=${item.id}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	
		<footer id="footer">
		<i class="social-icon fa fa-facebook"></i> <i
			class="social-icon fa fa-twitter"></i> <i
			class="social-icon fa fa-instagram"></i> <i
			class="social-icon fa fa-envelope"></i> <i
			class="social-icon fa fa-pinterest"></i> <i
			class="social-icon fa fa-youtube"></i>
		<p>© Copyright 2022 Marvel</p>
	</footer>
</body>

</html>