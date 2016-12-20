<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>

<h1>Promotion Code</h1>

<input onclick="popup()" type="button" value="코드 생성"/>
<hr />
<table class="table table-striped table-hover table-promotioncode">
	<thead>
		<tr>
			<th class="tcenter">No.</th>
			<th class="tcenter">Code</th>
			<th class="tcenter">User id</th>
			<th class="tcenter">User email</th>
			<th class="tcenter">Create Date</th>
			<th class="tcenter">Used Date</th>
			<th class="tcenter">
			
				<select id="useYn${data.seq}" name="useYn">
					<option value="Y">사용완료</option>
					<option value="N">미사용</option>
				</select>
				<script>
					$("#useYn${data.seq}").val("${data.useYn}");
					
					if ("${data.useYn}" != "Y") {
						$("#useYn${data.seq}").find("option").not(":selected");
					}
				</script>
				</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${list}" varStatus="loop">
		<tr>
			<td class="tcenter">${data.seq}</td>
			<td class="tcenter">${data.codeValue}</td>
			<td class="tcenter">${data.usedUser}</td>
			<td class="tcenter">${data.usedEmail}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.issueDate}" pattern="yyyy-MM-dd" />
			</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.usedDate}" pattern="yyyy-MM-dd" />
			</td>
 			<td class="tcenter">${data.useYn}</td> 
		</tr>
	</c:forEach>
	<%--  만약, 게시글이 하나도 등록되어 있지 않다면 --%>
	<c:if test="${empty list}">
		<tr>
			<td colspan='7'>No Content</td>
		</tr>
	</c:if>
	</tbody>
</table>

<SCRIPT LANGUAGE="Javascript">
function popup(){
	var url = "${pageContext.request.contextPath}/admin/promotioncode/popup";
	var name = "프로모션 코드 생성";
	window.open(url,name,"width=700,height=600,toolbar=no,status=no,location=no,scrollbars=no,menubar=no,resizable=no,left=50,right=50");
	
}

</SCRIPT>


<c:import url="/WEB-INF/jsp/template/footer.jsp" />