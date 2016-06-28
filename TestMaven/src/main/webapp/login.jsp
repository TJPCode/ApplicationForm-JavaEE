<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="body-content">
		<jsp:include page="usertop.html"/>
		<div id="wrapper">
			<div class="content">
				<div class="page-title">
					<h2>Please login</h2>
				</div>
				<form action="LoginServlet" method="post" accept-charset="utf-8">
					<div class="form-part">
						Username<input class="form-item" type="text" name="username" placeholder="required" autofocus required>
					</div>
					<div class="form-part">
						Password<input class="form-item" type="password" name="password" placeholder="required" required>
					</div>				
					<div class="form-part">	
						<input class="btn" type="submit" value="Login" name="postForm">
					</div>																				
				</form>			
				<c:if test="${requestScope.loginfail != null}">
					<div class='form-part error-text'>Wrong username or password.</div>
				</c:if>	
				<c:if test="${requestScope.connectionfail != null}">
					<div class='form-part error-text'>Error: Problem with database connection.</div>
				</c:if>									  					
			</div>
		</div>
	</div>
</body>
</html>