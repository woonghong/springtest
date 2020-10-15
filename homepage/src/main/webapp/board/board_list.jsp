
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		$("#write_btn").click(function(){
			location.href='./board_write_form.do';
		})
	});

</script>
<title>게시판 목록</title>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<c:if test="${ member != null }">
				<p class="w3-right">
					<button class="w3-button w3-black w3-round" id="write_btn">글 작성</button>
				</p>
			</c:if>
			<div class="w3-center w3-large w3-margin-top">
				<h3>게시판 리스트</h3>
				<table border="1">
				<tr>
				<th>no</th>
				<th>게시자</th>
				<th>제목</th>
				<th>내용</th>
				<th>등록날짜</th>
				</tr>
				<c:forEach var="row" items="${list}">
				<tr>
				<td>${row.bod_no}</td>
				<td>${row.id}</td>
				<td><a href="./board_detail.do?bod_no=${row.bod_no}">${row.subject}</a></td>
				<td>${row.content}</td>
				<td>${row.write_date}</td>
				</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>