<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty logged}">
	<c:redirect url="index.jsp"/>	  	
</c:if>
<!DOCTYPE html>
<html>
<head>
	<title>Admin home</title>
	<meta http-equiv="content-type" content="text/html" charset="utf-8" />
	<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

	<div class="body-content">
		<jsp:include page="admintop.html"/>	
		<div id="wrapper">
			<div class="content">
				<div class="page-title">
					<h2>Welcome admin!</h2>
				</div>
				<div class="form-part">
					This is administrators front page. Admin can view all applicants and search applicants from database.
				</div>
			</div>
		</div>
	</div>
</body>
</html>