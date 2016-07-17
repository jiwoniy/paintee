<%@ tag body-content="empty" pageEncoding="euc-kr" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<c:if test="${not empty pageVO.list}">
	<nav style="text-align: center;">
	  <ul class="pagination">
		<%-- 처음 페이지 설정 --%>
		<c:choose>
			<c:when test="${pageVO.pageNo == 1}">
				<li class="disabled"><a href="#1" aria-label="Previous"><span aria-hidden="true">◀◀</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageVO.url}?pageNo=1" aria-label="Previous"><span aria-hidden="true">◀◀</span></a></li>
			</c:otherwise>
		</c:choose>
		
		<%-- 이전 페이지 설정 --%>
		<c:choose>
			<c:when test="${pageVO.beginPage == 1}">
				<li class="disabled"><a href="#1" aria-label="Previous"><span aria-hidden="true">◀</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageVO.url}?pageNo=${pageVO.beginPage - 1}" aria-label="Previous"><span aria-hidden="true">◀</span></a></li>
			</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${pageVO.beginPage}" end="${pageVO.endPage}">
			<c:choose>
				<c:when test="${i == pageVO.pageNo}">
					<li class="active"><a href="#1" style="background: #1995dc; color: white">${i}  <span class="sr-only">(current)</span></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageVO.url}?pageNo=${i}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<%-- 다음 페이지 설정 --%>
		<c:choose>
			<c:when test="${pageVO.endPage == pageVO.lastPage}">
				<li class="disabled"><a href="#1" aria-label="Next"><span aria-hidden="true">▶</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageVO.url}?pageNo=${pageVO.endPage + 1}" aria-label="Next"><span aria-hidden="true">▶</span></a></li>
			</c:otherwise>
		</c:choose>			
				
		<%-- 마지막 페이지 설정 --%>
		<c:choose>
			<c:when test="${pageVO.pageNo == pageVO.lastPage}">
				<li class="disabled"><a href="#1" aria-label="Next"><span aria-hidden="true">▶▶</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageVO.url}?pageNo=${pageVO.lastPage}" aria-label="Next"><span aria-hidden="true">▶▶</span></a></li>
			</c:otherwise>
		</c:choose>
	  </ul>
	</nav>	
</c:if>