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
    <script type="text/javascript" src="${ctx}/static/plugins/jquery.contextmenu.js"></script>
    <style>
		.contextMenu {
			font-size: 18px;
		}
	</style>
</head>
<body>
<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
    <div class="contextMenu row" id="contextMenu" style="display:none;">
        <ul >
            <li id="add">
                <span class="glyphicon glyphicon-plus" style="float:left"></span>
                <span style="font-size:100%; font-family:Verdana;margin-left:10px;">Add Row</span>
            </li>
            <li id="edit">
                <span class="glyphicon glyphicon-pencil" style="float:left"></span>
                <span style="font-size:100%; font-family:Verdana;margin-left:10px;">Edit Row</span>
            </li>                
            <li id="del">
                <span class="glyphicon glyphicon-trash" style="float:left"></span>
                <span style="font-size:100%; font-family:Verdana;margin-left:10px;">Delete Row</span>
            </li>                
        </ul>
    </div>


    <script type="text/javascript"> 
    
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: '${ctx}/static/json/menu.json',
				// we set the changes to be made at client side using predefined word clientArray
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
                width: 780,
                height: 200,
                rowNum: 150,
                pager: "#jqGridPager",
                gridComplete: initGrid
            });
        });

        function initGrid() {
            $(".jqgrow", "#jqGrid").contextMenu('contextMenu', {
				menuStyle :{
					width : "150px"
				},				
                bindings: {
                    'edit': function (t) {
                        editRow();
                    },
                    'add': function (t) {
                        addRow();
                    },
                    'del': function (t) {
                        delRow();
                    }
                },
                onContextMenu: function (event, menu) {
                    var rowId = $(event.target).parent("tr").attr("id")
                    var grid = $("#jqGrid");
                    grid.setSelection(rowId);

                    return true;
                }
            });

            function addRow() {
                var grid = $("#jqGrid");
                grid.editGridRow("new", { closeAfterAdd: true});
            }

            function editRow() {
                var grid = $("#jqGrid");
                var rowKey = grid.getGridParam("selrow");
                if (rowKey) {
                    grid.editGridRow(rowKey, {closeAfterEdit: true});
                }
                else {
                    alert("No rows are selected");
                }
            }

            function delRow() {
                var grid = $("#jqGrid");
                var rowKey = grid.getGridParam("selrow");
                if (rowKey) {
                    grid.delGridRow(rowKey);
                }
                else {
                    alert("No rows are selected");
                }
            }
        }

    </script>

    
</body>
</html>