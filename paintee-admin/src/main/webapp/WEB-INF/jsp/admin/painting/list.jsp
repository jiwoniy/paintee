<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<script>
	// 정상-N/블라인드-B/삭제-D
	function initStatus(selectStatus, item) {
		switch (selectStatus) {
		// 삭제 요청일 경우	
		case "D":  
			switch (item.value) {
			case "B":  
				$(item).remove();
				break;
			}
			break;
		}
	}
	
	function settingStatus() {
		
	}
</script>
<h1>New Upload</h1>
<hr />
<table class="table table-striped table-hover table-upload">
	<thead>
		<tr>
			<th class="tcenter">Artist Name</th>
			<th>Sentence</th>
			<th class="tcenter">Posted</th>
			<th class="tcenter">Share</th>
			<th class="tcenter">Posted People</th>
			<th class="tcenter">Created Date</th>
			<th class="tcenter">status</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${list}" varStatus="loop">
		<tr>
			<td class="tcenter">
				<input type="hidden" id="artistId${data.seq}" value="${data.artistId}" />
				<input type="hidden" id="paintingStatus${data.seq}" value="${data.paintingStatus}" />
				${data.artistName}
			</td>
 			<td>${data.sentence}</td> 
			<td class="tcenter">${data.postedNum}</td>
			<td class="tcenter">${data.shareCnt}</td>
			<td class="tcenter">${data.postedPeopleCnt}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter">
				<select id="paintingSel${data.seq}" name="paintingSel">
					<option value="N">정상</option>
					<option value="B">블라인드</option>
					<option value="D">삭제</option>
				</select>
				<script>
					$("#paintingSel${data.seq} option").each(function (index, item) {
						// 상태값에 따른 OPTION 생성
						initStatus("${data.paintingStatus}", item);
					}); 
					$("#paintingSel${data.seq}").val("${data.paintingStatus}");
				</script>
			</td>
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

<script>
	$("[name=paintingSel]").change(function (event) {
		var seq = this.id.replace("paintingSel", "");
		var paintingStatus = this.value;
		
		if (confirm("상태를 변경하시겠습니까?")) {
			var data = {
				"seq": seq,
				"paintingStatus": paintingStatus,
				"artistId": $("#artistId" + seq).val()
			};
			$.ajax({
					url: "${pageContext.request.contextPath}/admin/painting/mod",
					type: "GET",
					async: true,
					cache: false,
					data: data
			})
			.done(function (result) {
				
				var selHtml  = '<option value="N">정상</option>';
					selHtml += '<option value="B">블라인드</option>';
					selHtml += '<option value="D">삭제</option>';
				$("#paintingSel" + seq).html(selHtml);
				$("#paintingSel" + seq).val(paintingStatus);
				
				$("#paintingSel" + seq + " option").each(function (index, item) {
					initStatus(paintingStatus, item);
				});
				
// 				if (result) {
// 					alert(result.msg);			
// 				}
			})
			.fail(function () {
				alert("상태 변경중 오류가 발생했습니다.");
			});
		} else {
			$(this).val($("#paintingStatus" + seq).val());
		}
	});	
</script>

<c:import url="/WEB-INF/jsp/template/footer.jsp" />