<%@page import="com.MarkovskiSolutions.Entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="UsersManager" scope="request" />
<c:import url="/WEB-INF/layout/header.jsp" />

<div class="container">
	<div class="">
		<p class="valign center-align red-text">${ error }</p>
	</div>
</div>
<jsp:include page="/WEB-INF/layout/footer.jsp" />