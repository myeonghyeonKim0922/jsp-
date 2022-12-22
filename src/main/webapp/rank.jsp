<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.RankDTO" %>
   <%
  request.setCharacterEncoding("UTF-8");
  ArrayList<RankDTO> list = new ArrayList<RankDTO>();
  list = (ArrayList<RankDTO>)request.getAttribute("list");
  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="rankstyle.css">
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
<div class="title">후보자등수</div>
<div class="wrapper">
	<table class="ranktable">
		<tr>
			<th>후보번호</th>
			<th>성명</th>
			<th>총투표건수</th>
		</tr>
 		<%for(RankDTO r : list){%>
		<tr class ="ranktd">
			<td><%=r.getM_no() %></td>
			<td><%= r.getM_name()%></td>
			<td><%= r.getV_vote() %></td>
		</tr>
		<% } %>   
	</table>
</div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>