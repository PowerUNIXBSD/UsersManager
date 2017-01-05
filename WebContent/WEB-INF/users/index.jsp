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
	
	<form method="get">
		<div class="row">
		    <div class="col s12">
		    
		  	<div class="">
		  		<input type="text" name="search" value="${ search }" class=" left-align search orange-text">
		  		<a href="/UserManagementWebApplication/UsersServlet" title="Search" alt="search"> <i class="material-icons center-text orange-text">search</i> </a>
		  		<a href="/UserManagementWebApplication/UsersServlet" title="Reset" alt="reset"> <i class="material-icons orange-text">close</i> </a>
		  	</div>
		    
		     
		    </div>
	  	</div>
  	</form>

	<form method="get" action="/UserManagementWebApplication/UsersServlet">
		<table class="bordered marginTop20 marginBottom20">
			<tr>
				<th>
				<a href="">First Name</a> / 
					<a href="/UserManagementWebApplication/UsersServlet?order=LastName&orderType=${ orderType }&s=${ search }">Last Name</a>
	
				</th>
				
				<th>
					<a href="">Date of Birth</a>
				</th>
				
				<th>
					<a href="">Phone number</a>
				</th>
				
				<th>
					<a href="">E-Mail</a>
				</th>
			</tr>
	
			<c:forEach var="user" items="${users}">
				<tr>
					<td>
					
      
						<input type="checkbox" id="du${ user.getId() }" name="du" value="${ user.getId() }">
						<label for="du${ user.getId() }"><a href='<c:url value="/UsersServlet?action=details&id=${ user.getId() }"  />'>${ user.getFirstName() }
							${ user.getLastName() } </a></label>
						
					</td>
					
					<td> ${ user.getDateBirth() } </td>
					 
					<td> ${ user.getPhoneNumber() } </td>
					 
					<td> ${ user.getEMail() } </td>
					 
				</tr>
			</c:forEach>
		</table>
	
		<div class="center-align">
			<button class="btn marginH10 orange">Add new user</button>
			<button class="btn marginH10 orange lighten-5 orange-text">Select</button>
			<button class="btn marginH10 orange lighten-5 orange-text">Select all</button>
			<input type="submit" onclick="return confirm('Do you want to delete selected users?')" class="btn marginH10 orange lighten-5 orange-text" value="Delete selected users">
		</div>
		<input type="hidden" name="action" value="delete">
	</form>
	
	
</div>
<jsp:include page="/WEB-INF/layout/footer.jsp" />