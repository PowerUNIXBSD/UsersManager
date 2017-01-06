<%@page import="com.MarkovskiSolutions.Entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="UsersManager" scope="request" />
<c:import url="/WEB-INF/layout/header.jsp" />



<div class="container">

	<nav class="marginTop20 marginBottom20 orange lighten-3 z-depth-0">
		<div class="nav-wrapper">
			<div class="col s12">
				<a href="#!" class="breadcrumb padding20">Users</a>
			</div>
		</div>
	</nav>
	
	<form method="get" id="searchForm">
		<div class="row">
		    <div class="col s12">
		    
			  	<div class="">
			  		<input id="search" type="text" name="search" value="${ search }" class=" left-align search orange-text">
			  		<button class="btn-flat btn-floating" type="submit"><i class="material-icons center-text orange-text">search</i></button>
			  		<div id="searchResetBtn" class="btn-flat btn-floating" type="reset"><i class="material-icons center-text orange-text">close</i></div>
			  	</div>
			  	
		    </div>
	  	</div>
  	</form>

	<form method="get">
		<table class="bordered marginTop20 marginBottom20">
			<tr>
				<th>
				<a href="#">First Name</a> / 
					<a href="/UserManagementWebApplication/UsersServlet?order=LastName&orderType=${ orderType }&search=${ search }">Last Name</a>
	
				</th>
				
				<th class="center-align">
					<a href="/UserManagementWebApplication/UsersServlet?order=DateBirth&orderType=${ orderType }&search=${ search }">Date of Birth</a>
				</th>
				
				<th class="center-align">
					<a href="#">Phone number</a>
				</th>
				
				<th class="center-align">
					<a href="#">E-Mail</a>
				</th>
			</tr>
	
			<c:forEach var="user" items="${users}">
				<tr>
					<td>
						<input type="checkbox" class="userDeleteCheckBox" id="du${ user.getId() }" name="du" value="${ user.getId() }">
						<label for="du${ user.getId() }"><a href='<c:url value="/UsersServlet?action=details&id=${ user.getId() }"  />'>${ user.getFirstName() }
							${ user.getLastName() } </a></label>
					</td>
					
					<td class="center-align"> ${ user.getDateBirth() } </td>
					 
					<td class="center-align"> ${ user.getPhoneNumber() } </td>
					 
					<td class="left-align"> ${ user.getEMail() } </td>
					 
				</tr>
			</c:forEach>
		</table>
	
		<div class="center-align">
			<a href="<c:url value="/UsersServlet?action=details" />"><div class="btn marginH10 orange">Add new user</div></a>
			<div id="selectAllBtn" class="btn marginH10 orange lighten-5 orange-text">Select all</div>
			<input type="submit" name="action" value="delete" onclick="return confirm('Do you want to delete selected users?')" class="btn marginH10 orange lighten-5 orange-text" value="Delete selected users">
		</div>
		
	</form>
	
	
</div>
<jsp:include page="/WEB-INF/layout/footer.jsp" />