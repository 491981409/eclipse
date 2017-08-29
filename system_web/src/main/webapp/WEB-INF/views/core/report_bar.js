$(function () {

	 utils.websocket(function(data){
		 drawing(data.bar);
	 });
	
	var categories = [];
	var pending = [];
	var open = [];
	
	$.ajax({
		url:"ticket/bar",
		dataType:"json",
		async:false,
		success:function(data){
			$.each(data,function(i,e){
				categories.push(e.userName);
				pending.push(e.pending);
				open.push(e.open);
			});
		}
	});
	
	
	function drawing(data){
		var categories = [];
		var pending = [];
		var open = [];
		$.each(data,function(i,e){
			categories.push(e.userName);
			pending.push(e.pending);
			open.push(e.open);
		});
		chart.xAxis[0].setCategories(categories);
		chart.series[0].setData(open);
		chart.series[1].setData(pending);
	}
	
	
	var chart = new Highcharts.Chart('canvas-bar', {
        chart: {
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                viewDistance: 25,
                depth: 40
            },
            marginTop: 80,
            marginRight: 40
        },
        title: {
            text: ' '
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            allowDecimals: false,
            min: 0,
            title: {
                text: ' '
            }
        },
        tooltip: {
            headerFormat: '<b>{point.key}</b><br>',
            pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
        },
        plotOptions: {
            column: {
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#D7DEE9'
                    }
                }
            }
        },
        series: [{
            name: '未处理',
            data: open,
            stack: 'status'
        }, {
            name: '待处理',
            data: pending,
            stack: 'status'
        }]
    });
});