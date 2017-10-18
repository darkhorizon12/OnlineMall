<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header">
    <ul class="nav nav-pills pull-right">
        <%--<li class="active"><a href="/">Home</a></li>--%>
        <li class="active"><a href="/">HOME</a></li>
        <sec:authorize access="isAuthenticated()">
        <li><a href="#this"><sec:authentication property="principal.email" /></a></li>
        <li><a href="#this" id="goLogout">LOG OUT</a></li>
		 <form method="post" action="/logout" id="formInfo">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>       
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
        <li><a href="/login">LOGIN</a></li>
        <li><a href="/members/new">SING UP</a></li>
        </sec:authorize>
        
        <form method="post">
           <select name="lang">
			<option <c:if test="${pageContext.response.locale eq 'ko' }">selected="selected"</c:if> value="ko">KOEEA</option>
			<option <c:if test="${pageContext.response.locale eq 'en' }">selected="selected"</c:if> value="en">ENGLISH</option>
			<option <c:if test="${pageContext.response.locale eq 'es' }">selected="selected"</c:if> value="es">SPANISH</option>
           </select>       
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           <input type="submit" value="submit" /> 
        </form>
        
    </ul>
</div>
<script type="text/javascript">
	$(function() {
		$("#goLogout").click(function() {
			$("#formInfo").submit();
		}); 
	})
</script>