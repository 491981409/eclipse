$(function () {

	 utils.websocket(function(data){
		 drawing(data.bar);
	 });
	
	var categories = [];
	var upgrade = [];
	var open = [];
	
	$.ajax({
		url:"ticket/bar",
		dataType:"json",
		async:false,
		success:function(data){
			$.each(data,function(i,e){
				categories.push(e.userName);
				upgrade.push(e.upgrade);
				open.push(e.open);
			});
		}
	});
	
	
	function drawing(data){
		var categories = [];
		var upgrade = [];
		var open = [];
		$.each(data,function(i,e){
			categories.push(e.userName);
			upgrade.push(e.upgrade);
			open.push(e.open);
		});
		chart.xAxis[0].setCategories(categories);
		chart.series[0].setData(open);
		chart.series[1].setData(upgrade);
	}
	
	
	var chart = new Highcharts.Chart('canvas-bar', {
        chart: {
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 0,
                beta: 0,
                viewDistance: 20,
                depth: 45
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
            name: '处理中',
            data: open,
            stack: 'status'
        }, {
            name: '已升级',
            data: upgrade,
            stack: 'status'
        }]
    });
});