<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>system</title>
	<jsp:include page="common/init.jsp"></jsp:include>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
</head>
<body>
	<div class="navbar">
		
	</div>
	<div class="container">
		<div class="row">
			<div clas="span12">
				<form class="form-horizontal" action="${ctx}/login" method="post">
					<fieldset>
						<legend>登录</legend>
						<div class="control-group">
							<label class="control-label" for="input01">用户名</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="username" name ="username">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="input01">密 码</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="password" name ="password">
							</div>
						</div>
						<button type="submit" class="btn">登录</button>
					</fieldset>
				</form>
			</div>
		
		</div>
	</div>
</body>
</html>