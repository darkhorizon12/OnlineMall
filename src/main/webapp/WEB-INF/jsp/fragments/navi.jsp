<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">ONLINE SHOP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
         <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" href="#this"><sec:authentication property="principal.email"/></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#this" id="goLogout">LOG OUT</a>
            </li>
         </sec:authorize>   
          
          <sec:authorize access="isAnonymous()">
            <li class="nav-item">
              <a class="nav-link" href="/login">LOG IN</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/members/new">SIGN UP</a>
            </li>
          </sec:authorize>  
          </ul>
          
        <form method="post">
           <select name="lang">
			<option <c:if test="${pageContext.response.locale eq 'ko' }">selected="selected"</c:if> value="ko">KOREA</option>
			<option <c:if test="${pageContext.response.locale eq 'en' }">selected="selected"</c:if> value="en">ENGLISH</option>
			<option <c:if test="${pageContext.response.locale eq 'es' }">selected="selected"</c:if> value="es">SPANISH</option>
           </select>       
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           <input type="submit" value="submit" /> 
        </form>          
          
        </div>
      </div>
    </nav>
<form method="post" action="/logout" id="formInfo">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>     
<script type="text/javascript">
	$(function() {
		$("#goLogout").click(function() {
			$("#formInfo").submit();
		}); 
	})
</script>    