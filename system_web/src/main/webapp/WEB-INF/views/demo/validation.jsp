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
                url: 'data.json',
                editurl: 'clientArray',
                datatype: "json",
                colModel: [
                    {
                        name: 'Integer',
                        key: true,
                        editable: true,
                        editrules:{
							//custom rules
                            custom_func: validatePositive,
                            custom: true,
                            required: true
                        },
                        width: 75
                    },
                    {
                        name: 'Number',
                        width: 100,
                        editable: true,
                        editrules: {
							number: true,
                            minValue: 1000,
                            maxValue: 10000,
                            required: true
                        }
                    },
                    {
                        name: 'Email',
                        width: 100,
                        editable: true,
                        editrules: {
                            email: true,
                            required: true
                        }
                    },
                    {
                        name: 'Url',
                        width: 80,
                        editable: true,
                        editrules: {
                            url: true,
                            required: true
                        }
                    }
                ],
				loadonce : true,
                width: 780,
                height: 200,
                rowNum: 10,
                pager: "#jqGridPager"
            });

            $('#jqGrid').jqGrid('navGrid','#jqGridPager',
                // the buttons to appear on the toolbar of the grid
                { edit: true, add: false, del: false, search: false, refresh: false, view: false, position: "left", cloneToTop: false },
                // options for the Edit Dialog
                {
                    editCaption: "The Edit Dialog",
                    recreateForm: true,
                    closeAfterEdit: true,
                    errorTextFormat: function (data) {
                        return 'Error: ' + data.responseText
                    }
                },
                // options for the Add Dialog
                {
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

                function validatePositive(value, column) {
                    if ( isNaN(value) && value < 0)
                        return [false, "Please enter a positive value"];
                    else
                        return [true, ""];
                }

                
        });

    </script>
    
    
   
    
</body>
</html>