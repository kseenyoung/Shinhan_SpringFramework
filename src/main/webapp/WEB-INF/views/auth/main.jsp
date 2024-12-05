<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basepath" value="${pageContext.servletContext.contextPath}" scope="application"></c:set>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h1>업무선택</h1>
    <ul>
      <li><a href="${basepath}/auth/login.do">로그인</a></li>
      <hr>
      <li><a href="${basepath}/emp/list.do">직원조회</a></li>
      <li><a href="${basepath}/emp/insert.do">직원추가</a></li>
      <hr>
      <li><a href="${basepath}/dept/list.do">부서조회</a></li>
    </ul>
  </body>
</html>
