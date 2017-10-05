<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />
    <form role="form" action="/items/new" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">NAME</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="Input name">
        </div>
        <div class="form-group">
            <label for="price">PRICE</label>
            <input type="number" name="price" class="form-control" id="price" placeholder="Input price">
        </div>
        <div class="form-group">
            <label for="stockQuantity">STOCK QUANTITY</label>
            <input type="number" name="stockQuantity" class="form-control" id="stockQuantity" placeholder="Input stock quantity">
        </div>
        
        <div class="form-group">
            <label for="author">AUTHOR</label>
            <input type="text" name="author" class="form-control" id="author" placeholder="Input author name">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" name="isbn" class="form-control" id="isbn" placeholder="Input ISBN number">
        </div>
        <div class="form-group">
            <label for="file">IMG FILE</label>
            <input type="file" name="file" class="form-control" id="file" placeholder="Select File image">
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
		$("#categoryDiv2").hide();
		
		$("#category1").change(function() {
			if ($(this).val() == "" || typeof ($(this).val()) === "undefined") {
				$("#categoryDiv2").hide();
				return false;
			}
			$("#categoryId").val($(this).val());
			$.ajax({
				url : "/items/category",
				type : "post",
				dataType : "json",
				data : $("#categoryForm").serialize(),
				success : function (data) {
					$("#categoryDiv2").show();
					var innerHTML = "<option value=''>카테고리 선택</option>";
					$.each(data, function(index, category) {
						innerHTML += "<option value='" + category.id + "'>" + category.name + "</option>"; 
					});
					
					$("#category2").html(innerHTML);
					
				},
				error : function(request, status, error) {
					alert(request.responseText);
				}
			});
		}); 
	});
</script>

</body>
</html>
