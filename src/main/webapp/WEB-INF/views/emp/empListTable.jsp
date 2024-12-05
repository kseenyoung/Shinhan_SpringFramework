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
<table class="table table-hover">
  <tr>
    <td>id</td>
    <td>fname</td>
    <td>lname</td>
    <td>email</td>
    <td>phone</td>
    <td>job</td>
    <td>hiredate</td>
    <td>commission</td>
    <td>salary</td>
    <td>manager</td>
    <td>depratment_id</td>
  </tr>
  <c:forEach items="${empData}" var="emp">
    <tr id="${emp.employee_id}">
      <td><a href="${basepath}/emp/detail.do?emp_id=${emp.employee_id}">${emp.employee_id}</a></td>
      <td><a href="${basepath}/emp/detail.do?emp_id=${emp.employee_id}">${emp.first_name}</a></td>
      <td>${emp.last_name}</td>
      <td>${emp.email}</td>
      <td>${emp.phone_number}</td>
      <td>${emp.job_id}</td>
      <td>${emp.hire_date}</td>
      <td>${emp.commission_pct}</td>
      <td>${emp.salary}</td>
      <td>${emp.manager_id}</td>
      <td>${emp.department_id}</td>
      <td>
        <button onclick="location.href='${basepath}/emp/delete.do?emp_id=${emp.employee_id}'">삭제</button>
      </td>
        <%--            <td><button onclick="f_update('${emp}')">수정</button></td>--%>
    </tr>
  </c:forEach>
</table>
