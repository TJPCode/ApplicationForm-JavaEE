<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty logged}">
	<c:redirect url="index.jsp"/>	  	
</c:if>
<c:choose>
	<c:when test="${sessionScope.searchedName != null}">		
		<jsp:useBean id="getSearchedBean" class="pkg.bean.GetSearchedBean">
			<jsp:setProperty name="getSearchedBean" property="searchValue" value="${sessionScope.searchedName}" />
		</jsp:useBean>
		<c:set var="applicants" value="${getSearchedBean.applicants}"/>
	</c:when>						
	  <c:otherwise>
		<jsp:useBean id="getAllBean" class="pkg.bean.GetAllBean"/>
		<c:set var="applicants" value="${getAllBean.applicants}"/>		
	  </c:otherwise>
</c:choose>				
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<title>Applicants</title>
	<script>
		function confirmDelete() {
		    var x = confirm("Do you really want to delete selected applicants?");
		    if (x)
		        return true;
		    else
		      return false;
		}
	</script>
</head>
<body>
	<div class="body-content">
		<jsp:include page="admintop.html"/>				
		<div id="wrapper">
			<div class="content">				
				<div class="page-title">
					<h2>All applicants</h2>
				</div>		
				<form action="SearchServlet" method="post" accept-charset="utf-8">			
					<div class="form-part">
						<table>
							<tr>
								<td width="30%"><input class="form-item" type="text" name="searchname" autofocus required></td>
								<td width="20%"><input class="btn" type="submit" value="Search"></td>
								<td width="50%"><input class="btn float-right" value="Get all" onclick="location.href='ApplicantsServlet'"></td>
							</tr>
						</table>								
					</div>																																													
				</form>				
				<div class="form-part">${applicants.size()} applicants found.</div>			
				<form action="ApplicantsServlet" method="post" accept-charset="utf-8">				
					<table>
						<tr>
							<th class='table-header column-1'></th>
							<th class='table-header column-2'>Name</th>
							<th class='table-header column-3'>Application</th>
						</tr>									
						<c:forEach items="${applicants}" var="name">
							<tr>
								<td class="cell-style">
									<input type="checkbox" value="${name.getKey()}" name="readboxes">
								</td>
								<td class="cell-style">
									<c:choose>
									  <c:when test="${name.getIsRead() == 0}">
									  	<label class="error-text">${name.getFirstname()}  ${name.getLastname()}</label>
									  </c:when>									  
									  <c:otherwise>
									  	<label class="success-text">${name.getFirstname()}  ${name.getLastname()}</label>
									  </c:otherwise>
									</c:choose>																	
								</td>
								<td class="cell-style column-3">
									<input class="app-btn" type="submit" value="${name.getKey()}" name="applicationbtn">	
								</td>
							</tr>			
						</c:forEach>												
					</table>
					<div class="form-part">	
						<input class="btn" type="submit" value="Mark as read" name="readbtn">
						<input class="btn" type="submit" value="Mark as unread" name="unreadbtn">
						<input class="btn float-right" type="submit" value="Delete" name="deletebtn" onclick="return confirmDelete()">
					</div>
				</form>						
				<c:if test="${requestScope.applicationletter != null}">
				  	<div class="form-part">
					  	<label>${applicationletter[0]} ${applicationletter[1]}</label>
					  	<textarea class="form-item" readonly rows="7">${applicationletter[2]}</textarea>
				  	</div>
				</c:if>				
			</div>
		</div>
	</div>
</body>
</html>