<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp" />

    <form role="form" method="post" enctype="multipart/form-data">
        <!-- id -->
        <input type="hidden" name="id" value="${item.id}" />
		<input type="hidden" name="filename" value="${item.filename }" />
		<input type="hidden" name="orgFilename" value="${item.orgFilename }" />
        <div class="form-group">
            <label for="name">NAME</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="input item name" value="${item.name}">
        </div>
        <div class="form-group">
            <label for="price">PRICE</label>
            <input type="number" name="price" class="form-control" id="price" placeholder="input price" value="${item.price}">
        </div>
        <div class="form-group">
            <label for="stockQuantity">STOCK QUANTITY</label>
            <input type="number" name="stockQuantity" class="form-control" id="stockQuantity" placeholder="input stock quantity" value="${item.stockQuantity}">
        </div>
        <div class="form-group">
            <label for="author">AUTHOR</label>
            <input type="text" name="author" class="form-control" id="author" placeholder="input author" value="${item.author}">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" name="isbn" class="form-control" id="isbn" placeholder="input isbn number" value="${item.isbn}">
        </div>
        <div class="form-group">
            <label for="file">IMG FILE</label>
            <input type="file" name="file" class="form-control" id="file">
            <img src="/download/${item.id }">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />

</div> <!-- /container -->


</body>
</html>
