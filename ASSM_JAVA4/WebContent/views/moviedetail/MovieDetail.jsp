<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<title>${movie.movieName}</title>
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="/ASSM_JAVA4/css/moviedetail/MovieDetail.css">
<style type="text/css">
.trailer i {
	
}

.trailer a {
	text-decoration: none;
}
</style>

</head>

<body>
	<div class="banner"
		style="background: url(/ASSM_JAVA4/img/MovieDetail/${movie.background});     background-position: center;
    background-size: cover;">
		<div class="content">
			<h2>${movie.movieName}</h2>
			<p>${movie.note}</p>
			<a href="#" class="play" onclick="toggle();"><img
				src="/ASSM_JAVA4/img/Web/play.png" alt="">watch now</a>
			<c:if test="${not checkLike}">
				<a href="/ASSM_JAVA4/MovieDetailServlet/like?${movie.id}"> <i
					class='bx bx-like bx-tada'
					style="color: #ffffff; font-size: 50px; margin-left: 30px;"></i>
				</a>
			</c:if>
			<c:if test="${checkLike}">
				<a href="/ASSM_JAVA4/MovieDetailServlet/like?${movie.id}"> 
				<i class='bx bxs-like' style="color: #ffffff; font-size: 50px; margin-left: 30px;"></i>
				</a>
			</c:if>
		</div>
	</div>

	<div class="trailer">
		<video src="/ASSM_JAVA4/video/movie/DoctorStrange2.mp4"
			controls="true"></video>
		<img src="/ASSM_JAVA4/img/Web/close.png" alt="" class="close"
			onclick="toggle();">
	</div>


	<script>
		function toggle() {
			var trailer = document.querySelector('.trailer');
			let video = document.querySelector('.video');
			trailer.classList.toggle('active');
			video.currentTime = 0;
			video.pause();
		}
	</script>
</body>

</html>