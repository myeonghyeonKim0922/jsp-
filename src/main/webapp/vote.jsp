<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*" %>
    <%@ page import="DTO.Vote" %>
   <%
   request.setCharacterEncoding("UTF-8");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="topmenu.jsp" %>
	<section>
		<div class="title">투표하기</div>
		<form name="frm" action="insert">
		<div class="wrapper">
			<table>
				<tr>
					<th>주민번호</th>
					<td><input type="text" name="v_jumin">예 : 8906153154726</td>
				</tr>
				<tr>
					<th>성명</th>
					<td><input type="text" name="v_name"></td>
				</tr>
				<tr>
					<th>투표번호</th>
					<td><input type="text" name="m_no"></td>
				</tr>
				<tr>
					<th>투표시간</th>
					<td><input type="text" name="v_time"></td>
				</tr>
				<tr>
					<th>투표장소</th>
					<td><input type="text" name="v_area"></td>
				</tr>
				<tr>
					<th>투표장소</th>
					<td><input type="radio" name="v_confirm" value="Y"><label for="select">확인</label>
					<input type="radio" name="v_confirm" value="N"><label for="select2">미확인</label>
					</td>
				</tr>
					<td colspan="2">
						<button class="btn" type="submit" onclick="location.href='update'">투표하기</button>
						<button class="btn" type="button" onclick="location='vote'">다시하기</button>
					</td>
			</table>
		</div>
		</form>
	</section>	
	<%@ include file="footer.jsp" %>
</body>
</html>