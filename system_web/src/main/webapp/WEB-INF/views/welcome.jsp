<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>   
<jsp:include page="common/init.jsp"></jsp:include>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<div style="margin-left:20px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
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
    
    
     <script type="text/javascript">
	 var grid_selector = "#union_base_grid_table";
	var pager_selector = "#union_base_grid_pager";
	
	$(grid_selector).jqGrid({
		url:"${ctx}/orgInfoUnion/list",
		datatype: "json",
		height: 250,
		autowidth:true,
		colNames:[ '供应商编码','供应商名称','是否牵头方'],
		colModel:[
			{name:'ORG_CODE',index:'sdate',width:90, editable:true, sorttype:"date"},
			{name:'ORG_NAME',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
			{name:'stock',index:'stock', width:70, editable: true,}
		], 
		viewrecords : true,
		rowNum:5,
		rowList:[5,10,15],
		altRows: true,
		shrinkToFit: true,
		multiselect: true,
        multiboxonly: true,
		editurl: "${ctx}/orgInfoUnion/edit",//nothing is saved
		caption: "联合体成员",
		pager: pager_selector,
		jsonReader:{
			root:"data",
			totalCount:"recordsTotal"
		}
	});
	

	 $(grid_selector).navGrid(pager_selector,
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
                {
                	editCaption: "新增",
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
	
	$(window).resize(function(){
		$(grid_selector).setGridWidth($(window).width()-310);
	});
	
    
    
    </script>
		</div>
	</div>
	<div class="footer">
		<div class="row">
			
			<div class="span12">
			</div>
		</div>
	
	</div>
</body>

<script type="text/javascript">
	$(function(){
		
	});
</script>
</html>