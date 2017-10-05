<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />

        <div class="form-group">
            <label>NAME : </label> ${item.name }
        </div>
        <div class="form-group">
            <label>PRICE : </label> ${item.price }
        </div>
        <div class="form-group">
            <label>STOCK QUANTITY : </label> ${item.stockQuantity }
        </div>
        <div class="form-group">
            <label>AUTHOR : </label> ${item.author}
        </div>
        <div class="form-group">
            <label>ISBN : </label> ${item.isbn}
        </div>
        <div class="form-group">
            <label>IMG FILE : </label> 
            <img src="/download/${item.id }" alt="${item.orgFilename }" />
        </div>
	    <form role="form" id="itemViewForm" action="/carts/${item.id }/add" method="post">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <select name="selectedQuantity">
	        	<c:forEach var="qty" begin="1" end="${item.stockQuantity }">
	        	<option value="${qty }">${qty }</option>
	        	</c:forEach>
	        </select>  
	    </form>
        <button type="button" id="cartBtn" class="btn btn-default">ADD CART</button>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->

<script type="text/javascript">
	$(function() {
		$("#cartBtn").click(function() {
			$("#itemViewForm").submit();
		});
	});
</script>

</body>
</html>
