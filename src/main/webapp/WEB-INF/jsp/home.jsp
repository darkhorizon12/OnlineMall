<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<jsp:include page="fragments/head.jsp"/>

<body>

<jsp:include page="fragments/navi.jsp"/>
<div class="container">

	<div class="row">
	<jsp:include page="fragments/menu.jsp"/> 

        <div class="col-lg-9">

          <div class="row">
          <c:forEach items="${mainItems }" var="item">

            <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <a href="#"><img class="card-img-top" width="700px" height="400px" src="/download/${item.id }" alt="${item.name }"></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="/items/${item.id }/view">${item.name }</a>
                  </h4>
                  <h5>${item.price }</h5>
                  <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
                </div>
                <div class="card-footer">
                  <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </div>
              </div>
            </div>
          </c:forEach>

           </div>

          <div class="row">
          <c:forEach items="${page.content }" var="item">

            <div class="col-lg-4 col-md-6 mb-4">
              <div class="card h-100">
                <a href="#"><img class="card-img-top" width="700px" height="400px" src="/download/${item.id }" alt="${item.name }"></a>
                <div class="card-body">
                  <h4 class="card-title">
                    <a href="/items/${item.id }/view">${item.name }</a>
                  </h4>
                  <h5>${item.price }</h5>
                  <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
                </div>
                <div class="card-footer">
                  <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                </div>
              </div>
            </div>
          </c:forEach>

           </div>
          <!-- /.row -->

        </div>
	</div>
</div> <!-- /container -->
<jsp:include page="fragments/footer.jsp" />
</body>
</html>
