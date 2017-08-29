$(function(){
	
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
	 
	  
	  utils.websocket(function(data){
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
		  var ctx = $('#main-chart');
		    var mainChart = new Chart(ctx, {
			    type: 'line',
			    data: {labels: labels,datasets: datasets},
			    options: options
		    });
	   });
	  
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
	
});