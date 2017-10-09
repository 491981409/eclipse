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
	        },{
	            name:'已升级',
	            type:'bar',
	            data:upgrade,
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: 'rgb(124, 181, 236)'},
	                            {offset: 0.5, color: 'rgb(124, 181, 236)'},
	                            {offset: 1, color: 'rgb(124, 181, 236)'}
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
    
    var data1 = [];
    var data2 = [];
    var data3 = [];

    //convert Hex to RGBA
    function convertHex(hex,opacity){
      hex = hex.replace('#','');
      var r = parseInt(hex.substring(0,2), 16);
      var g = parseInt(hex.substring(2,4), 16);
      var b = parseInt(hex.substring(4,6), 16);

      var result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
      return result;
    }

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
    
    
    
    // ***************begin block  **************
    var element = {
   		 data :[{title :"桌面支持",id :"window",color:"facebook"},
   		        {title :"系统运维" ,id:"system",color:"twitter"},
   		        {title :"系统支持",id:"support",color:"linkedin"},
   		        {title :"软件支持",id:"software",color:"google-plus"}]
     };
     
     function initHtml(name,id,index,color){
   	  var html =" <div class=\"col-sm-6 col-lg-6\">" +
         "<div class=\"social-box "+color+"\">"+
         "<i class=\"fa\">"+name+"</i>"+
         "<div class=\"chart-wrapper\">"+
         "<canvas id=\"social-box-chart-"+index+"\" height=\"90\"></canvas>"+
         "</div><ul><li><strong id=\""+id+"U\">0</strong>"+
         "<span>未分配</span></li><li><strong id=\"" +id+"P\""+
         ">0</strong><span>处理中</span></li>"+
         "</ul></div></div>";
   	  return html;
     }
     
     function init(){
   	  var row = $("#queueAmount");
   	  $.each(element.data,function(i,e){
   		  row.append(initHtml(e.title,e.id,i+1,e.color));
   	  });
     }
     init();
     
     
   //convert Hex to RGBA
     function convertHex(hex,opacity){
       hex = hex.replace('#','');
       var r = parseInt(hex.substring(0,2), 16);
       var g = parseInt(hex.substring(2,4), 16);
       var b = parseInt(hex.substring(4,6), 16);

       var result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
       return result;
     }

     //Random Numbers
     function random(min,max) {
       return Math.floor(Math.random()*(max-min+1)+min);
     }

     //Social Box Charts
     var labels = ['January','February','March','April','May','June','July'];

     var options = {
       responsive: true,
       maintainAspectRatio: false,
       legend: {
         display: false,
       },
       scales: {
         xAxes: [{
           display:false,
         }],
         yAxes: [{
           display:false,
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

     var data1 = {
       labels: labels,
       datasets: [{
         backgroundColor: 'rgba(255,255,255,.1)',
         borderColor: 'rgba(255,255,255,.55)',
         pointHoverBackgroundColor: '#fff',
         borderWidth: 2,
         data: [65, 59, 84, 84, 51, 55, 40]
       }]
     };
     var ctx = $('#social-box-chart-1');
     var socialBoxChart1 = new Chart(ctx, {
       type: 'line',
       data: data1,
       options: options
     });

     var data2 = {
       labels: labels,
       datasets: [
         {
           backgroundColor: 'rgba(255,255,255,.1)',
           borderColor: 'rgba(255,255,255,.55)',
           pointHoverBackgroundColor: '#fff',
           borderWidth: 2,
           data: [1, 13, 9, 17, 34, 41, 38]
         }
       ]
     };
     var ctx = $('#social-box-chart-2').get(0).getContext('2d');
     var socialBoxChart2 = new Chart(ctx, {
       type: 'line',
       data: data2,
       options: options
     });

     var data3 = {
       labels: labels,
       datasets: [
         {
           backgroundColor: 'rgba(255,255,255,.1)',
           borderColor: 'rgba(255,255,255,.55)',
           pointHoverBackgroundColor: '#fff',
           borderWidth: 2,
           data: [78, 81, 80, 45, 34, 12, 40]
         }
       ]
     };
     var ctx = $('#social-box-chart-3').get(0).getContext('2d');
     var socialBoxChart3 = new Chart(ctx, {
       type: 'line',
       data: data3,
       options: options
     });

     var data4 = {
       labels: labels,
       datasets: [
         {
           backgroundColor: 'rgba(255,255,255,.1)',
           borderColor: 'rgba(255,255,255,.55)',
           pointHoverBackgroundColor: '#fff',
           borderWidth: 2,
           data: [35, 23, 56, 22, 97, 23, 64]
         }
       ]
     };
     var ctx = $('#social-box-chart-4').get(0).getContext('2d');
     var socialBoxChart4 = new Chart(ctx, {
       type: 'line',
       data: data4,
       options: options
     });
    
    //*************** end  main charts *********************
    
   
    
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