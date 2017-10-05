<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">

    <jsp:include page="../fragments/bodyHeader.jsp" />

    <form role="form" action="/categories/new" method="post">
        <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" name="name" class="form-control" id="inputName" placeholder="이름을 입력하세요">
        </div>
        
        <div class="form-group">
           <label for="parent">상위 카테고리</label>
           <select name="parent" id="parent" class="form-control">
               <option value="">카테고리 선택</option>
               <c:forEach var="category" items="${categories}">
                   <option value="${category.id}">${category.name}</option>
               </c:forEach>
           </select>
       </div>     
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
       <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />
</div> <!-- /container -->

</body>
</html>
