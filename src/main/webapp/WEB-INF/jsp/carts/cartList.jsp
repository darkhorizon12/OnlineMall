<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <th>STOCK</th>
                <th>UNIT PRICE</th>
                <th>TOTAL PRICE</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.content}" var="cart">
                <tr>
                    <td>${cart.id}</td>
                    <td><a href="/items/${cart.item.id }/view">${cart.item.name}</a></td>
                    <td>${cart.quantity}</td>
                    <td>${cart.orderPrice}</td>
                    <td>${cart.orderPrice * cart.quantity}</td>
                    <td><a href="/carts/${cart.id}/del" class="btn btn-primary" role="button">DEL</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
	<div class="form-group">
	    <p align="right">Subtotal(${fn:length(carts) } item) : ${totalPrice }</p>
	</div>
	<button type="button" id="orderBtn" class="btn btn-default">ORDER</button>    
	<button type="button" id="continueBtn" class="btn btn-default">CONTINUE</button>    
    
    ${pagenation }

    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>
