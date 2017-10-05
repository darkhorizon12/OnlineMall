<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>

<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    <form role="form" action="/coupons/masters/new" method="post">
        <div class="form-group">
            <label for="name">NAME</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="Input Coupon Master Name">
        </div>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        <div class="form-group">
            <label for="stockQuantity">STOCK QUANTITY</label>
            <input type="number" name="stockQuantity" class="form-control" id="stockQuantity" placeholder="수량을 입력하세요">
        </div>
        
        <div class="form-group">
            <label for="author">AUTHOR</label>
            <input type="text" name="author" class="form-control" id="author" placeholder="저자를 입력하세요">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" name="isbn" class="form-control" id="isbn" placeholder="ISBN을 입력하세요">
        </div>
        <div class="form-group">
            <label for="file">IMG FILE</label>
            <input type="file" name="file" class="form-control" id="file" placeholder="ISBN을 입력하세요">
        </div>

        <div id="categoryDiv1" class="form-group">
           <label for="category1">MAIN CATEGORY</label>
           <select name="category1" id="category1" class="form-control">
               <option value="">SELECT CATEGORY</option>
               <c:forEach var="category" items="${categories}">
                   <option value="${category.id}">${category.name}</option>
               </c:forEach>
           </select>
       </div>             

        <div id="categoryDiv2" class="form-group">
           <label for="category2">SUB CATEGORY</label>
           <select name="category2" id="category2" class="form-control">
               <option value="">SELECT CATEGORY</option>
           </select>
       </div>             
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />
	<form id="categoryForm">
		<input type="hidden" name="categoryId" id="categoryId" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
	</form>
</div> <!-- /container -->

<script type="text/javascript">
	$(function() {
		$('#datetimepicker1').datetimepicker({
			todayHighlight: true,
			format: "mm/dd/yyyy"
		});
	});
</script>

</body>
</html>
