<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<script>
	// 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7 
	function initStatus(selectStatus, item) {
		switch (selectStatus) {
		// 요청일 경우  발송, 환불요청, 삭제만 가능함
		case "1":  
			switch (item.value) {
			case "4": case "5": case "6": case "99": 
				$(item).remove();
				break;
			}
			break;
		// 발송일 경우 재발송처리, 삭제만 가능함	
		case "2":  
			switch (item.value) {
			case "1": case "3": case "4": case "6": case "99": 
				$(item).remove();
				break;
			}
			break;
		// 환불요청일 경우 환불처리, 삭제만 가능함	
		case "3":  
			switch (item.value) {
			case "1": case "2": case "4": case "5": case "99": 
				$(item).remove();
				break;
			}
			break;
		// 재발송 요청일 경우 재발송 처리, 삭제만 가능함	
		case "4":  
			switch (item.value) {
			case "1": case "2": case "3": case "6": case "99": 
				$(item).remove();
				break;
			}
			break;
		// 재발송 처리일 경우 삭제만 가능함	
		case "5":  
			switch (item.value) {
			case "1": case "2": case "3": case "4":  case "6": case "99":
				$(item).remove();
				break;
			}
			break;
		// 완료일 경우	
		case "99":  
			switch (item.value) {
			case "1": case "2": case "3": case "4":  case "5": case "6": case "7":
				$(item).remove();
				break;
			}
			break;
		}
	}
</script>
<h1>New Purchase</h1>
<hr />
<table class="table table-striped table-hover table-purchase">
	<thead>
		<tr>
			<th class="tcenter">구매자</th>
			<th class="tcenter">보내는사람</th>
			<th class="tcenter">받는사람</th>
			<th class="tcenter">도시</th>
			<th class="tcenter">구매일</th>
			<th class="tcenter">다운로드</th>
			<th class="tcenter">상태</th>
		</tr>
		<tr>
			<th colspan="3">한마디</th>
			<th colspan="4">주소</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${list}">
		<tr>
			<td class="tcenter">
				<input type="hidden" id="purchaseStatus${data.seq}" value="${data.purchaseStatus}" />
				<input type="hidden" id="userId${data.seq}" value="${data.userId}" />
				<input type="hidden" id="paintingId${data.seq}" value="${data.paintingId}" />
				${data.userName}
			</td>
 			<td class="tcenter">${data.senderName}</td> 
			<td class="tcenter">${data.receiverName}</td>
			<td class="tcenter">${data.receiverCity}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter"><a href="${pageContext.request.contextPath}/cmm/file/download/${data.fileInfo.id}">다운</a></td>
			<td class="tcenter">
				<select id="purchaseSel${data.seq}" name="purchaseSel">
				<c:forEach var="status" items="${statusList}">
					<option value="${status.codeValue}">${status.codeName}</option>
				</c:forEach>	
				</select>
				<script>
					$("#purchaseSel${data.seq}").val("${data.purchaseStatus}");
					$("#purchaseSel${data.seq} option").each(function (index, item) {
						// 상태값에 따른 OPTION 생성
						initStatus("${data.purchaseStatus}", item);
					}); 
				</script>
			</td>
		</tr>
		<tr>
			<td colspan="3">${data.sentence}</td>
			<td colspan="4">(${data.receiverZipcode})${data.receiverBasicAddr} ${data.receiverDetailAddr}</td>
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

<form name="purchaseForm" method="post" action="${pageContext.request.contextPath}/admin/purchase/mod">
	<input type="hidden" name="seq" />
	<input type="hidden" name="userId" />
	<input type="hidden" name="paintingId" />
	<input type="hidden" name="purchaseStatus" />
</form>

<script>
	if ('${msg}') alert('${msg}');
	$("[name=purchaseSel]").change(function (event) {
		var seq = this.id.replace("purchaseSel", "");
		if (confirm("상태를 변경하시겠습니까?")) {
			$("[name=seq]").val(seq);
			$("[name=userId]").val($("#userId" + seq).val());
			$("[name=paintingId]").val($("#paintingId" + seq).val());
			$("[name=purchaseStatus]").val(this.value);
			document.purchaseForm.submit();
		} else {
			$(this).val($("#purchaseStatus" + seq).val());
		}
	});	
</script>

<c:import url="/WEB-INF/jsp/template/footer.jsp" />