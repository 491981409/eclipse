<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <jsp:include page="../common/init.jsp"></jsp:include>
</head>
<body>
<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
    <script type="text/javascript"> 
    
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: '${ctx}/static/json/data.json',
                datatype: "json",
                colNames: ['Category Name', 'Product Name', 'Country', 'Price', 'Quantity'],
                colModel: [
					{ label: 'Category Name', name: 'CategoryName', width: 100, 'frozen': true },
					{ label: 'Product Name', name: 'ProductName', width: 150, 'frozen': true },
					{ label: 'Country', name: 'Country', width: 200 },
					{ label: 'Price', name: 'Price', width: 250, sorttype: 'number' },
					{ label: 'Quantity', name: 'Quantity', width: 250, sorttype: 'integer' }                   
                ],
                shrinkToFit: false, // must be set with frozen columns, otherwise columns will be shrank to fit the grid width
                width: 780,
                height: 200,
                rowNum: 15,
                pager: "#jqGridPager"
            });

            $("#jqGrid").jqGrid("setFrozenColumns");
        });
    </script>

    
</body>
</html>