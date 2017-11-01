        $(function () {
        	$('#table').bootstrapTable({
        		url: 'remark/list',
        		dataType: "json",
        		pagination: true, //分页
        		singleSelect: false,
        		queryParamsType:'',
        	    sidePagination: "server", //服务端处理分页
        	    columns: [{
        	        field: 'rContent',
        	        title: '标题'
        	    }, {
        	        field: 'rSort',
        	        title: '排序'
        	    }, {
        	        field: 'createTime',
        	        title: '创建时间'
        	    }],
        	    queryParams:function(param){
        	    	return param;
        	    },
        	    responseHandler: function(res) {
        	    	console.log(JSON.stringify(res));
                    return {
                        "total": res.size,//总页数
                        "rows": res.list //数据
                     };
                }
        	});
        });
