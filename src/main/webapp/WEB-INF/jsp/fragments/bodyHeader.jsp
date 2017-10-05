<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="header">
    <ul class="nav nav-pills pull-right">
        <%--<li class="active"><a href="/">Home</a></li>--%>
        <li class="active"><a href="/">Home</a></li>
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
    </ul>
    <a href="/"><h3 class="text-muted">MY OWN SHOP</h3></a>
</div>
<script type="text/javascript">
	$(function() {
		$("#goLogout").click(function() {
			$("#formInfo").submit();
		}); 
	})
</script>