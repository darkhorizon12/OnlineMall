<%@ page pageEncoding="UTF-8"%>
	<ul class="pagination">
		<c:if test="${pagination.startPageNo > 0 }">
		<li><a href="?pageNo=1">First</a></li>
		</c:if>
		<c:if test="${pagination.startPageNo > 1 }">
		<li><a href="?pageNo=${pagination.startPageNo - 1 }">&lt;&lt;</a></li>
		</c:if>
		<c:forEach var="pNo" begin="${pagination.startPageNo }" end="${pagination.endPageNo }">
			<c:choose>
				<c:when test="${pNo ==  pagination.pageNo}">
		<li class="active"><a href="#this">${pNo }</a></li>
				</c:when>
				<c:otherwise>
		<li><a href="?pageNo=${pNo }">${pNo }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pagination.endPageNo < pagination.finalPageNo }">
		<li><a href="?pageNo=${pagination.endPageNo + 1 }">&gt;&gt;</a></li>
		</c:if>
		<c:if test="${pagination.endPageNo < pagination.finalPageNo }">
		<li><a href="?pageNo=${pagination.finalPageNo }">End</a></li>
		</c:if>
	</ul>