<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.11.0.min.js"></script>
<script type="text/ecmascript" src="${ctx}/static/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.jqGrid.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/static/css/ui.jqgrid-bootstrap.css" />
<script>
		$.jgrid.defaults.width = 780;
		$.jgrid.defaults.responsive = true;
		$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/base.js"></script>