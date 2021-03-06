<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
		<title>Admin Account</title>				
			<style type="text/css">
    			<%@include file="style.css" %>
    	    		.active {
					background-color:#096333;
					}	    		
			</style> 
	</head>
</head>
<body>
	<ul>
		<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
		<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
		<c:choose>
			<c:when test="${empty email}">
				<li><a
					href="http://localhost:8081/tjones50/login?from=${pageContext.request.contextPath}/course">Login</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				<li><a href="http://localhost:8081/tjones50/userAccount">Account
						Information</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<h1>Admin Account</h1>
	<h2>Account info:</h2>
	<p2> Welcome Admin </p2>

	<h2>Advice</h2>
	<c:forEach items="${admin.arrAdvice}" var="advice">
		<tr>
			<td><p1> Course was taken ${advice.semester} of
				${advice.classYear} <br>
				<br>
				Advice was left by a ${advice.userMajor} student who was a
				${advice.userClassYear} and had a GPA of ${advice.userGPA }. <br>
				Student got a ${advice.gradeReceived} in the class taught by
				${advice.professor } <br>
				<br>
				"${advice.text }" <br>
				<br>
				Difficulty was ${advice.adviceRating.difficulty} <br>
				Instruction quality was ${advice.adviceRating.instruction} <br>
				Cost of supplies was ${advice.adviceRating.suppliesCost} <br>
				Enjoyment was ${advice.adviceRating.enjoyment} <br>
				<br>
				<table>
					<tr>
						<th>
							<form action="${pageContext.servletContext.contextPath}/admin"
								method="post">
								<button type="submit" class="activateButtons">Approve
									Advice</button>
								<input name="action" type="hidden" value="approve" /> <input
									name="adviceId" type="hidden" value="${advice.adviceId}" />
							</form>
						</th>
						<th>User activated is ${advice.userActivated}</th>
					</tr>
					<tr>
						<th>
							<form action="${pageContext.servletContext.contextPath}/admin"
								method="post">
								<button type="submit" class="activateButtons">Deactivate
									Account</button>
								<input name="action" type="hidden" value="deactivate" /> <input
									name="adviceId" type="hidden" value="${advice.adviceId}" />
							</form>
						</th>
						<th>
							<form action="${pageContext.servletContext.contextPath}/admin"
								method="post">
								<button type="submit" class="activateButtons">Activate
									Account</button>
								<input name="action" type="hidden" value="activate" /> <input
									name="adviceId" type="hidden" value="${advice.adviceId}" />
							</form>
						</th>
					<tr>
				</table>
				</p1></td>
		</tr>
	</c:forEach>
</body>
</html>