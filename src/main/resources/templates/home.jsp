<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	Welcome to the luv2code company home page!
	<hr>
	<p>
	User: <security:authentication property="principal.username" />
	<br><br>
	Role(s): <security:authentication property="principal.authorities" />
	</p>

	<hr>

    <security:authorize access="hasRole('MANAGER')">

        <!-- Add a link to point to Mangers -->
        <p>
            <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
            (for Mangers only)
        </p>

	</security:authorize>

    <security:authorize access="hasRole('ADMIN')">

        <!-- Add a link to point to Admins -->
            <p>
                <a href="${pageContext.request.contextPath}/systems">IT System Meeting</a>
                (for Admins only)
            </p>
    </security:authorize>

	<hr>

	<!-- Add a logout button -->
    <form:form action="${pageContext.request.contextPath}/logout"
    			   method="POST">

    	<input type="submit" value="Logout" />

    </form:form>

</body>

</html>