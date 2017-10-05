<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <th>카테고리 </th>
                <th>상위 카테고리</th>
                <th>하위 카테고리</th>
                <th>수정</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.parent.name}</td>
                    <td>
                <c:choose>
                	<c:when test="${fn:length(category.children) gt 0}">
                        <a href="/categories/${category.id}/list" class="btn btn-primary" role="button">하위</a>
                	</c:when>
                	<c:otherwise>&nbsp;</c:otherwise>
                </c:choose>
                	</td>    
                    <td>
                        <a href="/categories/${category.id}/edit" class="btn btn-primary" role="button">수정</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>
