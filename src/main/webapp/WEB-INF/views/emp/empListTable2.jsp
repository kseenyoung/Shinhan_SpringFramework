<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/20/24
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basepath" value="${pageContext.servletContext.contextPath}" scope="application"></c:set>
<p>${ empData }</p>
<table class="table table-hover">
  <tr>
    <td>id</td>
    <td>fname</td>
    <td>salary</td>
    <td>부서명</td>
    <td>도시</td>
    <td>나라</td>
    
  </tr>
  <c:forEach items="${empData}" var="emp">
    <tr id="${emp.employee_id}">
      <td><a href="${basepath}/emp/detail.do?emp_id=${emp.employee_id}">${emp.employee_id}</a></td>
      <td><a href="${basepath}/emp/detail.do?emp_id=${emp.employee_id}">${emp.first_name}</a></td>
      <td>${emp.salary}</td>
      <td>${emp.department_name}</td>
      <td>${emp.city}</td>
      <td>${emp.country_name}</td>
      <td>
        <button onclick="location.href='${basepath}/emp/delete.do?emp_id=${emp.employee_id}'">삭제</button>
      </td>
        <%--            <td><button onclick="f_update('${emp}')">수정</button></td>--%>
    </tr>
  </c:forEach>
</table>