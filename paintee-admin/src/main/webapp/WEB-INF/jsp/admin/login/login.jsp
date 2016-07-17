<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<div class="col-md-6 col-md-offset-3">
	<div class="well bs-component">
		<form class="form-horizontal" name="mForm" method="post" action="${pageContext.request.contextPath}/admin/login/login">
		<fieldset>
	    <legend>Login</legend>
	    <div class="form-group">
	    <label for="inputID" class="col-lg-2 control-label">ID</label>
	      <div class="col-lg-10">
	        <input type="text" class="form-control" id="id" name="id" placeholder="ID">
	      </div>
	    </div>
	    <div class="form-group">
	    <label for="inputID" class="col-lg-2 control-label">Password</label>
	      <div class="col-lg-10">
	        <input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
	      </div>
	    </div>
	    <div class="form-group">
	      <div class="col-lg-10 col-lg-offset-2">
			<input type="submit" value="Login" class="btn btn-primary"/>
	        <a href="${pageContext.request.contextPath}/" class="btn btn-default">Cancel</a>
	      </div>
	    </div>
		</fieldset>
		</form>
	</div>
</div>
<c:import url="/WEB-INF/jsp/template/footer.jsp" />