<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL Tag를 사용하기 위한 필수 설정 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameboardDetail</title>
<link href="${pageContext.request.contextPath}/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/fontawesome-free-6.2.0-web/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>


	<!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <div class="container px-4">
                <a class="navbar-brand" href="test.jsp">Team Happy</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                	<ul class="navbar-nav ms-auto">
					<c:choose>
						<c:when test="${sessionScope.userId != null}">
							<li class="nav-item" style="color: white;">환영합니다. ${sessionScope.userId} 님
							&nbsp;</li><li class="nav-item" style="color: white; font-size: 15px;
							cursor: pointer;" onclick="location.href='Logout.jsp';">[ Logout ]</li>
						</c:when>

						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="NewLogin.jsp">LogIn</a></li>
							<li class="nav-item"><a class="nav-link" href="NewSignUp.jsp">SignUp</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>
            </div>
        </nav>
	
	
	<div style="margin: 5rem auto 0; border: 1px solid black; width:700px; height: 500px;">

		<br><h2 class="text-center">게시글 보기</h2>
		<p>&nbsp;</p>
		<div class="table table-responsive">
			<table class="table">
				<tr>
					<th class="success">글번호</th>
					<td>${requestScope.resultContent.board_num}</td>
					<th class="success">조회수</th>
					<td>${requestScope.resultContent.read_num}</td>
				</tr>


				<tr>
					<th class="success">작성자</th>
					<td>${requestScope.resultContent.user_id}</td>
					<th class="success">작성일</th>
					<td>${requestScope.resultContent.board_created_date}</td>
				</tr>

				<tr>
					<th class="success">제목</th>
					<td colspan="3">${requestScope.resultContent.board_title}</td>
				</tr>

				<tr>
					<th class="success">글 내용</th>
					<td colspan="3">${requestScope.resultContent.board_context}</td>
				</tr>
			</table>
		</div>
		<form action="board" method="post">
			<input type="text" name="title" placeholder="댓글을 입력해주세요."> <input
				type="submit" value="저장">
		</form>
	</div>
	

	<div style="margin: 0 auto; border: 1px solid black; text-align: right; width:700px;">
		<button type="button" class="btn btn-sm btn-primary" id="btnUpdate" onclick="location.href='Update.jsp'">수정</button>
		<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
		<button type="button" class="btn btn-sm btn-primary" id="btnList" onclick="location.href='Gameboard.jsp'">목록</button>
	</div>

	<!-- Footer-->
	<footer class="py-5 bg-dark" style="position: absolute; bottom: 0; width: 100%;">
		<div class="container px-4">
			<p class="m-0 text-center text-white">Team Happy _ 주현 , 재선 , 정현</p>
		</div>
	</footer>
</body>
</html>