<%@page import="com.MarkovskiSolutions.Entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="UsersManager" scope="request" />
<c:import url="/WEB-INF/layout/header.jsp" />

<div class="container ">

	<nav class="marginTop20 marginBottom20 orange lighten-3 z-depth-0">
		<div class="nav-wrapper ">
			<div class="col s12">
				<a href="/UserManagementWebApplication/UsersServlet"
					class="breadcrumb padding20 orange-text">Users</a> <a href="#!"
					class="breadcrumb padding20"> 
						<c:if test="${ user.getId() != 0 }">Update</c:if>
						<c:if test="${ user.getId() == 0 }">Add new</c:if> user
				</a>
			</div>
		</div>
	</nav>
	
	<div class="">
		<p class="valign center-align red-text">${ error }</p>
	</div>

	<div class="row">
		<form class="col s12" method="post"
			action="/UserManagementWebApplication/UsersServlet">
			<div class="row">
				<div class="input-field col s6">
					<input id="FirstName" name="FirstName"
						value="${ user.getFirstName() }" type="text" class="validate">
					<label for="FirstName" class="orange-text">First Name</label>
				</div>

				<div class="input-field col s6">
					<input id="LastName" name="LastName"
						value="${ user.getLastName() }" type="text" class="validate">
					<label for="LastName" class="orange-text">Last Name</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s3">
					<input id="DateBirth" name="DateBirth"
						value="${ user.getDateBirth() }" type="date" class="datepicker"
						placeholder="Date of Birth">
					<!-- <label for="DateBirth" class="orange-text">Date of Birth</label> -->
				</div>

				<div class="input-field col s6">
					<input id="EMail" type="email" value="${ user.getEMail() }"
						name="EMail" class="validate"> <label for="EMail"
						class="orange-text">E-Mail address</label>
				</div>

				<div class="input-field col s3">
					<input id="PhoneNumber" type="tel"
						value="${ user.getPhoneNumber() }" name="PhoneNumber"
						class="validate"> <label for="PhoneNumber"
						class="orange-text">Phone Number</label>
				</div>
			</div>
			<div class="row">
				<div class="col s2">
				<input type="hidden" name="id" value="${ user.getId() }">
					<button class="btn waves-effect waves-light orange" type="submit">
						Submit <i class="material-icons right">send</i>
					</button>
				</div>
			</div>

		</form>
	</div>

</div>
<jsp:include page="/WEB-INF/layout/footer.jsp" />