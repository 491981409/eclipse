$(function () {

	 utils.websocket(function(data){
		 drawing(data.bar);
		 setCharts(data);
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
	
	
	
	 //Random Numbers
	  function random(min,max) {
	    return Math.floor(Math.random()*(max-min+1)+min);
	  }
	  
	//Main Chart
	  var elements = utils.getCurrentMonthMaxDay();
	  
	  var map =new Map();
	  
	  var data1 = [];
	  var data2 = [];
	  var data3 = [];
	 
	  
	  var ctx = $('#main-chart');
	  
	  var mainChart ;

	  function setCharts(data){
		  map =new Map();
		  var datasets = [];
		  var labels =[];
		  
		  for (var i = 1; i <= elements; i++) {
				labels.push(i);
		  }
		  $.each(data.queueDay,function(k,c){
			  var everyDay = [];
			  for (var i = 1; i <= elements; i++) {
				  var key = i < 10 ?  '0'+ i : i ; 
				  if(c[key] != undefined ){
					  everyDay.push(c[key]);
				  }else{
					  everyDay.push(0);
				  }
			  }
			  map.set(k,everyDay);
		  });
		  randomColorIndex = 0;
		  map.forEach(function(value ,key ,map){
			  var option= {};
			  option.label =  key;
			  var color = getRandomColor();
			  option.backgroundColor = convertHex(color,10);
			  option.borderColor = color;
			  option.pointHoverBackgroundColor = '#fff';
			  option.borderWidth = 2;
			  option.data= value;
			  datasets.push(option);
		  });
		  
		  mainChart = new Chart(ctx, {
			    type: 'line',
			    data: {labels: labels,datasets: datasets},
			    options: options
		    });
		  
	  }
	  
	 
	  
	  var randomColorIndex = 0;
	  var brand = [$.brandSuccess,$.brandInfo,$.brandWarning,$.brandDanger];
	  function getRandomColor(){
		  if(randomColorIndex > brand.length){
			  randomColorIndex = 0;
		  }
		  var color = brand[randomColorIndex];
		  randomColorIndex ++;
		  return color;
	  }


	 //convert Hex to RGBA
	  function convertHex(hex,opacity){
	    hex = hex.replace('#','');
	    var r = parseInt(hex.substring(0,2), 16);
	    var g = parseInt(hex.substring(2,4), 16);
	    var b = parseInt(hex.substring(4,6), 16);

	    var result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
	    return result;
	  }
	  
	 var options = {
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
			          max: 50
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
	 
	  var flag = 1;
	  var bar =  $("#canvas-bar-show");
	  var chart =  $("#main-chart-show");
	   setInterval(function(){
		   if(flag == 1 ){
			   bar.show();
			   chart.hide();
//			   bar[0].style.visibility ='visible'
//			   chart[0].style.visibility = 'hidden';
			   flag = 0;
		   }else{
//			   bar[0].style.visibility ='hidden'
//			   chart[0].style.visibility = 'visible';
			   bar.hide();
			   chart.show();
			   mainChart.update();
			   flag =1;
		   }
	   }, 1000 * 5);
	
	
	
	
});