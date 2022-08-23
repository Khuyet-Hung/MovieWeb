
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="com.assm.model.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<title>Hello</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.5.5/css/simple-line-icons.min.css">
<link rel="stylesheet" href="/ASSM_JAVA4/css/style.css" type="text/css">
</head>

<body id="page-top">
	<a href="#" class="menu-toggle rounded"> <i class="fa fa-bars"></i>
	</a>

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
									<li class="sidebar-nav-item"><a
										class="js-scroll-trigger item" href="#UpcomingMovies">Coming
											Next</a></li>
									<li class="sidebar-nav-item"><a
										class="js-scroll-trigger item" href="#TopRatedMovies">Top
											Movies</a></li>
									<li class="sidebar-nav-item"><a
										class="js-scroll-trigger item" href="#LatestMovies">Latest
											Movies</a></li>
									<li class="sidebar-nav-item"><a
										class="js-scroll-trigger item" href="#EnglishMovies">English
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
							<div class="avatar"
								style="background-image: url(/ASSM_JAVA4/img/Avatar/default.jpg); background-repeat: no-repeat; background-size: cover;">
						</c:when>
						<c:when test="${not empty user}">
							<div class="avatar"
								style="background-image: url(/ASSM_JAVA4/img/Avatar/avatar_1.jpg); 
					background-repeat: no-repeat; background-size: cover;">
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
							<c:if test="${user != null}">
								<a href="/ASSM_JAVA4/Main/log-out">
								<li>Log out</li>
							</a>
							</c:if>
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

	<header class="masthead d-flex">
		<div class="container text-center my-auto">
			<a class="btn btn-primary btn-xl js-scroll-trigger"
				href="Movies/BlackWidow/BlackWidow.html"> <i
				class="fa fa-play-circle"></i>
			</a>
		</div>
		<div class="overlay"></div>
	</header>


	<section id="UpcomingMovies">
		<div class="Container">
			<h2>Upcoming Movies</h2>
			<div class="row Movies">
				<c:forEach var="film" items="${movies}">
					<c:if test="${film.genre == 'UpcomingMovies'}">
						<div class="col-md-2">
							<a href="/ASSM_JAVA4/MovieDetailServlet?${film.id}">
								<div class="item">
									<img src="/ASSM_JAVA4/img/Images/PosterImages/${film.photo}">
									<span class="name">${film.movieName}</span> <span class="year">${film.movieYear}</span>
								</div>
							</a>
						</div>
					</c:if>
				</c:forEach>

			</div>
		</div>
	</section>
	<section id="TopRatedMovies">
		<div class="Container">
			<h2>Top Rated Movies</h2>
			<div class="row Movies">
				<c:forEach var="film" items="${movies}">
					<c:if test="${film.genre == 'TopRatedMovies'}">
						<div class="col-md-2">
							<a href="/ASSM_JAVA4/MovieDetailServlet?${film.id}">
								<div class="item">
									<img src="/ASSM_JAVA4/img/Images/PosterImages/${film.photo}">
									<span class="name">${film.movieName}</span> <span class="year">${film.movieYear}</span>
								</div>
							</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- top rated movies section end -->


	<!-- latest movies section start -->
	<section id="LatestMovies">
		<div class="Container">
			<h2>Latest Movies</h2>
			<div class="row Movies">
				<c:forEach var="film" items="${movies}">
					<c:if test="${film.genre == 'LatesMovies'}">
						<div class="col-md-2">
							<a href="/ASSM_JAVA4/MovieDetailServlet?${film.id}">
								<div class="item">
									<img src="/ASSM_JAVA4/img/Images/PosterImages/${film.photo}">
									<span class="name">${film.movieName}</span> <span class="year">${film.movieYear}</span>
								</div>
							</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- latest movies section end -->


	<!-- english movies section start -->
	<section id="EnglishMovies">
		<div class="Container">
			<h2>English Movies</h2>
			<div class="row Movies">
				<c:forEach var="film" items="${movies}">
					<c:if test="${film.genre == 'EnglishMovies'}">
						<div class="col-md-2">
							<a href="/ASSM_JAVA4/MovieDetailServlet?${film.id}">
								<div class="item">
									<img src="/ASSM_JAVA4/img/Images/PosterImages/${film.photo}">
									<span class="name">${film.movieName}</span> <span class="year">${film.movieYear}</span>
								</div>
							</a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</section>
	<!-- english movies section end -->



	<!-- footer section start -->
	<footer id="footer">
		<i class="social-icon fa fa-facebook"></i> <i
			class="social-icon fa fa-twitter"></i> <i
			class="social-icon fa fa-instagram"></i> <i
			class="social-icon fa fa-envelope"></i> <i
			class="social-icon fa fa-pinterest"></i> <i
			class="social-icon fa fa-youtube"></i>
		<p>© Copyright 2022 Marvel</p>
	</footer>
	<!-- footer section end -->




	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
		<i class="fa fa-angle-up"></i>
	</a>


	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<script src="/ASSM_JAVA4/js/main.js"></script>
</body>

</html>