<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />

    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>STOCK</th>
                <th></th>
                <sec:authorize access="hasRole('ADMIN')">
                <th></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td><a href="/items/${item.id }/view">${item.name}</a></td>
                    <td>${item.price}</td>
                    <td>${item.stockQuantity}</td>
                    <td><a href="/items/${item.id}/edit" class="btn btn-primary" role="button">EDIT</a></td>
	                <sec:authorize access="hasRole('ADMIN')">
                    <td><a href="/items/${item.id}/del" class="btn btn-primary" role="button">DEL</a></td>
    	            </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
    <%@ include file="../fragments/pagination.jsp" %>

    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>
