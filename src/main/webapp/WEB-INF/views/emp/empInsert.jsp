<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/18/24
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<style>
:required, #red {
	color: red;
	font-weight: bold;
}
</style>
<title>Title</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<h1>직원 등록</h1>
	<%--<p><span id="red" style="margin: 3px">빨간색</span>은 필수입니다.</p>--%>
	<form action="insert.do" method="post">
		<div class="input-group mb-3">
			<span class="input-group-text">직원 번호</span> <input type="number"
				class="form-control" placeholder="숫자입력" name="employee_id" value=3
				required="required">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">성</span> <input type="text"
				class="form-control" placeholder="성" name="first_name" value="kim">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">이름</span> <input type="text"
				class="form-control" placeholder="이름" name="last_name"
				value="Shinyeong" required="required">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">이메일</span> <input type="text"
				class="form-control" placeholder="이메일" name="email"
				value="kseenyoung@gmail.com" required="required">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">핸드폰 번호</span> <input type="text"
				class="form-control" placeholder="" name="phone_number">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">고용일</span> <input type="date"
				class="form-control" name="hire_date" required="required">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">직책</span> <select required="required"
				class="form-control" name="job_id">
				<c:forEach items="${joblist}" var="job">
					<option ${job.job_id=='IT_PROG' ? 'selected':''}
						value="${job.job_id}">${job.job_title}</option>
				</c:forEach>
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">연봉</span> <input type="number"
				class="form-control" placeholder="연봉" name="salary" value="10000">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">commission pct</span> <input
				type="number" class="form-control" name="commission_pct"
				pattern="[0]\.[0-9]{1,2}" value=0.0>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">상사</span> <select class="form-control"
				placeholder="상사 번호" name="manager_id">
				<option value="-1">상사 없음</option>
				<c:forEach items="${managerlist}" var="manager">
					<option value="${manager.employee_id}">${manager.last_name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">부서</span> <select class="form-control"
				name="department_id">
				<option value="-1">부서 없음</option>
				<c:forEach items="${departmentlist}" var="department">
					<option value="${department.department_id}">${department.department_name}</option>
				</c:forEach>
			</select>
		</div>
		<input type="submit" value="직원 등록">
	</form>
	<script>
		$(function() {
			// $("input[name='hire_date']").attr("value", '2024-11-18');  // yyyy-mm-dd 형식이어야 함
			$("input[name='hire_date']").attr("value",
					new Date().toISOString().split("T")[0]); // yyyy-mm-dd 형식이어야 함
		})
	</script>
</body>
</html>
