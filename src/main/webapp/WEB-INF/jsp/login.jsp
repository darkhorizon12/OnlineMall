<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/head.jsp"/>

  <body>

    <div class="container">

      <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:out value="${code }"  /><br/>
        <label for="inputEmail">Email address</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus><br/>
        <label for="inputPassword">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required><br/>
        
        <label for="remember-me" >Remember Me? : </label> <input type="checkbox" name="remember-me" value="true"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
