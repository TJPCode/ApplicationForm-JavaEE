<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Application form</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="body-content">
		<jsp:include page="usertop.html"/>	
		<div id="wrapper">
			<div class="content">
				<div class="page-title">
					<h2>Please fill out this form</h2>
				</div>
				<form action="AppFormServlet" method="post" accept-charset="utf-8">
					<div class="form-part">
						First name<input class="form-item" type="text" name="firstname" placeholder="required"  autofocus required>
					</div>
					<div class="form-part">
						Last name<input class="form-item" type="text" name="lastname" placeholder="required"  required>
					</div>
					<div class="form-part">
						Gender
						<select class="form-item" name="gender">
							<option>-</option>
							<option value="male">Male</option>
							<option value="female">Female</option>
						</select>
					</div>
					<div class="form-part">
						Why are you applying to this job?
						<textarea class="form-item" rows="5" cols="50" name="application"></textarea>
					</div>				
					<div class="form-part">	
						<input class="btn" type="submit" value="Submit" name="postForm">
					</div>																			
				</form>														
				 <c:if test="${requestScope.result == 0}">
				 	<div class="form-part error-text">Error: Problems with database connection. Cannot perform update.</div>
				 </c:if>	
				 <c:if test="${requestScope.result > 0}">
				 	<div class="form-part success-text">Your application has been sent successfully.</div>
				 </c:if>									  					
			</div>
		</div>
	</div>
</body>
</html>