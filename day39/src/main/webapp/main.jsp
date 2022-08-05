<%@page import="model.vo.MemberVO"%>
<%@page import="model.dao.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="mvo" class="model.vo.MemberVO" />
<jsp:useBean id="mdao" class="model.dao.MemberDAO" scope="session"/>
<jsp:setProperty property="*" name="mvo" />
<% 

if (request.getMethod().equals("POST")) {
	mdao.insert(mvo); // 데이터 추가

	if(request.getParameter("mpk") != null){
	
		if(request.getParameter("score")!=null){ // 점수값이 존재한다면 해당 데이터의 점수변경
			mdao.update(mvo);
		}
		
		else{ // 없으면 pk만 들어온것 이므로 데이터 삭제
			mdao.delete(mvo);
		}
		
	}

}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표지페이지</title>
</head>
<body>

	<form action="main.jsp" method="post">
		이름: <input type="text" name="name"> <br> 
		점수: <input type="number" min="0" max="100" name="score"> <br> 
		<input type="submit" value="데이터 등록하기">
	</form>
	<hr>
	<form action="main.jsp" method="post">
		변경할 학생번호: <input type="text" name="mpk"> <br> 
		점수: <input type="number" min="0" max="100" name="score"> <br> 
		<input type="submit" value="점수 수정하기">
	</form>
	<hr>
	<form action="main.jsp" method="post">
		삭제할 학생번호: <input type="text" name="mpk"> <br> 
		<input type="submit" value="삭제하기">
	</form>
	<hr>


	<h3>데이터 목록</h3>
	<%
	 	ArrayList<MemberVO> datas= mdao.selectAll(mvo);
		if(datas.size()>0){
			for(MemberVO vo : datas){
	%>
	<A HREF = "selectOne.jsp?mpk=<%=vo.getMpk()%>"><%=vo.getMpk()%>번 <%=vo.getName()%>님 <%=vo.getScore()%>점</A> <br>
	<% 
			}
		}
		else{
			out.println("<H3>출력할 데이터가 없습니다...</H3>");
		}
	%>


</body>
</html>