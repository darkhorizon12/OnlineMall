<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<jsp:include page="fragments/head.jsp"/>

<body>

<div class="container">

    <jsp:include page="fragments/bodyHeader.jsp" />

    <div class="jumbotron">
        <h1><spring:message code="main.title" /> </h1>
        <!-- p class="lead">MEMBER</p-->
        <p><a class="btn btn-lg btn-primary" href="/members">MEMBER LIST</a></p>
        <p><a class="btn btn-lg btn-info" href="/items/new">REGISTER PRODUCT</a></p>
        <p><a class="btn btn-lg btn-info" href="/items">PRODUCT LIST</a></p>
        <p><a class="btn btn-lg btn-success" href="/carts">CART</a></p>
        <p><a class="btn btn-lg btn-success" href="/coupons/masters">COUPON MASTER LIST</a></p>
        <p><a class="btn btn-lg btn-success" href="/order">ORDER LIST</a></p>
    </div>

    <jsp:include page="fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>
