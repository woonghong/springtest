<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<title>마이페이지</title>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>Board_Detail</h3>
			</div>
			<div>
				<form method="post">	
					<div>작성일자 : <fmt:formatDate value= "${data.write_date}" pattern="yyyy-MM-dd a HH:mm:ss" />
					</div>
					<div>제목 <input name="subject" id="subject" size="80" value="${data.subject }">
					</div>
					<div>내용 <input name="content" id="content" row=4 value="${data.content }">
					</div>
					<div>이름 <input name="id" id="id" value="${data.id }">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>