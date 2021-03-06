$(function () {
	
	$("#graph").draggable();
	
	 $('#graph').contextMenu('myMenu1', {
	      bindings: {
	        'home': function(t) {
	        	setUpUrl("views/core/report_screen");
	        },
	        'max':function(){
	        	loadPage("views/core/report_poll");
	        }
	      }
	 });
	 
	 
	 utils.websocket(function(data){
		 drawing(data.bar);
		 setline(data.line);
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
		option.xAxis[0].data = categories;
		option.series[0].data = open;
		option.series[1].data = upgrade;
		myChart.setOption(option, true);
		myChart.resize();
	}
	
	var myChart = echarts.init(document.getElementById('canvas-bar'));

	option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            
	            type : 'shadow'
	        }
	    },
	    legend: {
            data:["处理中","已升级"]
        },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            data : categories
	        }
	    ],
	    yAxis : {},
	    series : [
	        {
	            name:'处理中',
	            type:'bar',
	            data:open,
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset: 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            label: {
	                normal: {
	                    show: true,
	                    position: 'top',
	                }
	            }
	        },{
	            name:'已升级',
	            type:'bar',
	            data:upgrade,
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: 'rgb(144, 237, 125)'},
	                            {offset: 0.5, color: 'rgb(144, 237, 125)'},
	                            {offset: 1, color: 'rgb(144, 237, 125)'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: 'rgb(124, 181, 236)'},
	                            {offset: 0.7, color: 'rgb(124, 181, 236)'},
	                            {offset: 1, color: 'rgb(124, 181, 236)'}
	                        ]
	                    )
	                }
	            },
	            label: {
	                normal: {
	                    show: true,
	                    position: 'top',
	                }
	            }
	        },
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart.resize();
    ///end bar 柱形图
    
    
    
    
    //begin charts
    

    //convert Hex to RGBA
    function convertHex(hex,opacity){
      hex = hex.replace('#','');
      var r = parseInt(hex.substring(0,2), 16);
      var g = parseInt(hex.substring(2,4), 16);
      var b = parseInt(hex.substring(4,6), 16);

      var result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
      return result;
    }
    
    var data1 = [];
    var data2 = [];
    var data3 = [];
    
    var chartsData = {
      labels: [],
      datasets: [
        {
          label: '创建',
          backgroundColor: convertHex($.brandInfo,10),
          borderColor: $.brandInfo,
          pointHoverBackgroundColor: '#fff',
          borderWidth: 2,
          data: data1
        },
        {
          label: '关闭',
          backgroundColor: 'transparent',
          borderColor: $.brandSuccess,
          pointHoverBackgroundColor: '#fff',
          borderWidth: 2,
          data: data2
        },
        {
          label: '升级',
          backgroundColor: 'transparent',
          borderColor: $.brandDanger,
          pointHoverBackgroundColor: '#fff',
          borderWidth: 1,
          borderDash: [8, 5],
          data: data3
        }
      ]
    };
    
    
    function setline(data){
    	chartsData.datasets[0].data = data.create;
    	chartsData.datasets[1].data = data.close;
    	chartsData.datasets[2].data = data.grade;
    	chartsData.labels =data.lable;
    	mainChart.update();
    }
    
    
    var chartsOptions = {
		    responsive: true,
		    maintainAspectRatio: false,
		    scales: {
		      xAxes: [{
		        gridLines: {
		          drawOnChartArea: false,
		        }
		      }],
		      yAxes: [{
		        ticks: {
		          beginAtZero: true,
		          maxTicksLimit: 5,
		          stepSize: Math.ceil(50 / 10),
		          max: 60
		        }
		      }]
		    },
		    elements: {
		      point: {
		        radius: 0,
		        hitRadius: 10,
		        hoverRadius: 4,
		        hoverBorderWidth: 3,
		      }
		    }
		  };

    var ctx = $('#main-chart');
    
    var mainChart = new Chart(ctx, {
	      type: 'line',
	      data: chartsData,
	      options: chartsOptions
	});
    
    $.ajax({
		url:"ticket/ticketCharts",
		dataType:"json",
		async:false,
		success:function(data){
			setline(data.result);
		}
	});
    
    // end charts
    

    // interval begin
     var poll =  $("#poll").children();
     var interval = poll.length;
     var currentIndex  = 0;
     poll.hide();
     poll.eq(currentIndex).show();
     
     setInterval(next,1000*10);
     
     function next(){
    	 var preIndex = currentIndex; 
    	 currentIndex = ++currentIndex % interval;
    	 poll.hide();
    	 poll.eq(preIndex).fadeIn(2000);
    	 myChart.setOption(option);
    	 myChart.resize();
     }
    // interval end
    
});