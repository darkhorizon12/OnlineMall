<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">

    <jsp:include page="../fragments/bodyHeader.jsp" />

    <form role="form" action="/members/new" method="post">
        <div class="form-group">
            <label for="inputName">NAME</label>
            <input type="text" name="name" class="form-control" id="inputName" placeholder="Input Name">
        </div>
        <div class="form-group">
            <label for="email">E-MAIL</label>
            <input type="email" name="email" class="form-control" id="email" placeholder="Input your email address">
        </div>
        <div class="form-group">
            <label for="password">PASSWORD</label>
            <input type="password" name="password" class="form-control" id="password" min="6" placeholder="Input your password">
        </div>
        
        <c:forEach var="role" items="${roles }">
        	<label class="checkbox-inline"><input type="checkbox" name="role" value="${role.id }">${role.role }</label>
        </c:forEach>
        
        <div class="form-group">
            <label for="city">도시</label>
            <input type="text" name="city" class="form-control" id="city" placeholder="도시를 입력하세요">
        </div>
        <div class="form-group">
            <label for="street">거리</label>
            <input type="text" name="street" class="form-control" id="street" placeholder="거리를 입력하세요">
        </div>
        <div class="form-group">
            <label for="zipcode">우편번호</label>
            <input type="text" name="zipcode" class="form-control" id="zipcode" placeholder="우편번호를 입력하세요">
        </div>
        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />
</div> <!-- /container -->

</body>
</html>
