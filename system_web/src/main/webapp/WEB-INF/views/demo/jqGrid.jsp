<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>jquery</title>
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
                url: '${ctx}/static/json/menu.json',
                editurl: 'clientArray',
                datatype: "json",
                colModel: [
                    {
						label: 'Customer ID',
                        name: 'CustomerID',
                        width: 75,
						key: true,
						editable: true,
						editrules : { required: true}
                    },
                    {
						label: 'Company Name',
                        name: 'CompanyName',
                        width: 140,
                        editable: true // must set editable to true if you want to make the field editable
                    },
                    {
						label : 'Phone',
                        name: 'Phone',
                        width: 100,
                        editable: true
                    },
                    {
						label: 'Postal Code',
                        name: 'PostalCode',
                        width: 80,
                        editable: true
                    },
                    {
						label: 'City',
                        name: 'City',
                        width: 140,
                        editable: true
                    }
                ],
				sortname: 'CustomerID',
				sortorder : 'asc',
				loadonce: true,
				viewrecords: true,
                height: 200,
                rowNum: 10,
                pager: "#jqGridPager"
            });

            $('#jqGrid').navGrid('#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                { edit: true, add: true, del: true, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
                // options for the Edit Dialog
                {
                    editCaption: "修改",
                    recreateForm: true,
					checkOnUpdate : true,
					checkOnSubmit : true,
                    closeAfterEdit: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {	editCaption: "新增",
                    closeAfterAdd: true,
                    recreateForm: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Delete Dailog
                {
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                });
        });

    </script>
    

    
</body>
</html>