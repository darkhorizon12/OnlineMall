<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>START DT - END DT</th>
                <th>COUNT</th>
                <th>PUBL TYPE</th>
                <th>DISC TYPE</th>
                <th>DISC PRICE</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${couponMasters}" var="couponMaster">
                <tr>
                    <td>${couponMaster.id}</td>
                    <td><a href="/coupons/masters/${couponMaster.id }/view">${couponMaster.name}</a></td>
                    <td>${couponMaster.startDate} - {couponMaster.endDate}</td>
                    <td>${couponMaster.couponCount}</td>
                    <td>${couponMaster.publicationType}</td>
                    <td>${couponMaster.discountType}</td>
                    <td>${couponMaster.discountPrice}</td>
                    <td><a href="/coupons/masters/${couponMaster.id}/del" class="btn btn-primary" role="button">DEL</a></td>
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
