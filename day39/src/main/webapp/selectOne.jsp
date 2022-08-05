<%@page import="model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mvo" class="model.vo.MemberVO" />
<jsp:useBean id="mdao" class="model.dao.MemberDAO" scope="session"/>
<jsp:setProperty property="*" name="mvo" />
<%
	MemberVO data = mdao.selectOne(mvo);
	if(data==null){
		response.sendRedirect("main.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table border=2px;>
<tr>
<td><%=data.getMpk()%>번</td>
</tr>
<tr>
<td><%=data.getName()%>님</td>
</tr>
<tr>
<td><%=data.getScore()%>점</td>
</tr>
</table>

</body>
</html>