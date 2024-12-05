<%--
  Created by IntelliJ IDEA.
  User: ksy
  Date: 11/14/24
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<html>
<head>
<script>
let resultMessage = "${resultMessage}";
if(resultMessage) {
    alert(resultMessage);
}
</script>

</head>
<body>
<div class="container mt-3">
    <%-- include 디렉티브 태크는 jsp를 합쳐서 컴파일한다. --%>
    <%@ include file="../common/header.jsp" %>
    <%--    <p>로그인한 Member정보 request:${loginMember1.memberName}</p>--%>
    <%--    <p>로그인한 Member정보 session:${loginMember2.memberName}</p>--%>
    <%--    <p>로그인한 Member정보 app:${loginMember3.memberName}</p>--%>

	<hr>
	<button id="btnSalary" class="btn btn-danger" id="">조회(only 급여)</button>
	<button id="btnJob" class="btn btn-danger" id="">조회(only 직책)</button>
	<button id="btnDept" class="btn btn-danger" id="">조회(only 부서)</button>
	<button id="btnJobJoin" class="btn btn-danger" id="">조회(only 직책 join)</button>
	<button id="btnJobJoin2" class="btn btn-danger" id="">조회(only 직책 join-map)</button>
	<button id="btnArray" class="btn btn-danger" id="">부서조회</button>
	<button id=btnTran class="btn btn-danger" id="">Transaction연습</button>
	<hr>
    <fieldset>
        <div class="input-group mb-3">
            <span class="input-group-text">부서</span>
            <select class="form-control" name="department_id">
                <option value="-1">부서 없음</option>
                <c:forEach items="${departmentlist}" var="department">
                    <option value="${department.department_id}">${department.department_name}</option>
                </c:forEach>
            </select>

            <span class="input-group-text">직책</span>
            <select required="required" class="form-control" name="job_id">
                <option value="-1">선택 안 함</option>
                <c:forEach items="${joblist}" var="job">
                    <option ${job.job_id=='IT_PROG' ? 'selected':''} value="${job.job_id}">${job.job_title}</option>
                </c:forEach>
            </select>
            <span class="input-group-text">연봉(이상)</span>
            <input type="number" class="form-control" placeholder="연봉" name="salary" value="5000">
            <span class="input-group-text">고용일(이후)</span>
            <input type="date" class="form-control" name="hire_date">
            <div class="input-group-text">
                <input class="form-check-input" type="checkbox" id="gridCheck" name="chkDate" checked>
                <label class="form-check-label" for="gridCheck">
                    모든 일자
                </label>
            </div>
            <button id="btn_search" class="btn btn-primary">조건 조회</button>
        </div>
    </fieldset>
    <h1>직원 List</h1>
    <div id="table_here">
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
    </div>
</div>
<script>
/* <button id="btnSalary" class="btn btn-danger" id="">조회(only 급여)</button>
<button id="btnJob" class="btn btn-danger" id="">조회(only 직책)</button>
<button id="btnDept" class="btn btn-danger" id="">조회(only 부서)</button>
<button id="btnJobJoin" class="btn btn-danger" id="">조회(only 직책 join)</button> */


    $(function () {
        // README 20년 전 날짜 계산하기
        let d = new Date();
        d.setFullYear(d.getFullYear()-20);

        $('[name="hire_date').val(d.toISOString().split('T')[0])
        $("#btn_search").on("click", f_ajax);
        $("#btnSalary").on("click", f_salary);
        $("#btnJob").on("click", f_job);
        $("#btnDept").on("click", f_dept);
        $("#btnJobJoin").on("click", f_jobjoin);
        $("#btnJobJoin2").on("click", f_jobjoin2);
        $("#btnArray").on("click", f_deptArray);
        $("#btnTran").on("click", f_transaction);
    });
    
    function f_transaction(){
    	alert("f_transaction");
    	$.ajax({
    		url : "${basepath}/emp/transaction.do",
    		success : function(result){
    			alert(result);
    		},
    		error : function(){}
    	})
    }
    
    function f_deptArray(){
    	alert("f_deptArray");
    	$.ajax({
    		url : "${basepath}/emp/listByArray.do",
    		data : {deptArr : [10, 60, 90]},
    		success : function(result){
    			$("#table_here").html(result);
    		},
    		error : function(){}
    	})
    }
    
    function f_salary(){
    	$.ajax({
            url: "${basepath}/emp/listBySalary.do",
            type: 'get',
            data: {
                "salary": $("input[name='salary']").val()
            },
            success: function (result) {
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }
    function f_job(){
    	$.ajax({
            url: "${basepath}/emp/listByJob.do",
            type: 'get',
            data: {
                "job_id": $("select[name='job_id']").val()
            },
            success: function (result) {
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }
    function f_dept(){
    	$.ajax({
            url: "${basepath}/emp/listByDept.do",
            type: 'get',
            data: {
                "department_id": $("select[name='department_id']").val()
            },
            success: function (result) {
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }
    function f_jobjoin(){
    	$.ajax({
            url: "${basepath}/emp/listByJobJoin.do",
            type: 'get',
            data: {
                "job_id": $("select[name='job_id']").val()
            },
            success: function (result) {
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }
    
    function f_jobjoin2(){
    	$.ajax({
            url: "${basepath}/emp/listByJobJoin2.do",
            type: 'get',
            data: {
                "job_id": $("select[name='job_id']").val()
            },
            success: function (result) {
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }

    function f_ajax() {
        let result = $.ajax({
            url: "${basepath}/emp/list2.do",
            type: 'get',
            data: {
                "department_id": $("[name='department_id']").val(),
                "job_id": $("[name='job_id']").val(),
                "salary": $("[name='salary']").val(),
                "hire_date": $("[name='hire_date']").val(),
                "chkDate": $("[name='chkDate']").prop("checked"),
            },
            success: function (result) {
                //2.  data를 받아서 HTML 만들까? NO
                // 3. JSP 받아서 현재화면에 대치(replace)
                $("#table_here").html(result);
            },
            error: function (err) {
                alert(err);
            }
        })
    }
</script>
</body>
</html>
