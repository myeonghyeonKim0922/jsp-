<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
    <%@page import="DTO.Vote" %>
     <%request.setCharacterEncoding("UTF-8");
    ArrayList<Vote> list = new ArrayList<Vote>();
    list = (ArrayList<Vote>)request.getAttribute("list");
    %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<%@include file="topmenu.jsp"%>
<section>
<div class="title">투표검수조회</div>
<div class="wrapper">
	<table style="width: 900px">
		<tr>
			<th>성명</th>
			<th>생년월일</th>
			<th>나이(만)</th>
			<th>성별</th>
			<th>후보번호</th>
			<th>투표시간</th>
			<th>유권자 확인</th>
		</tr>
 		<%for(Vote v : list){%>
		<tr>
			<td><%=v.getV_name() %></td>
			<td><%=v.getV_birth()%></td>
			<td><%=v.getV_age()%>세</td>
			<td><%=v.getV_s() %></td>
			<td><%=v.getM_no() %></td>
			<td><%=v.getV_time() %></td>
			<td><%=v.getV_confirm() %></td>
		</tr> 
		 <%}%> 
	</table>
</div>
</section>
<%@include file="footer.jsp"%>
</body>
</html>