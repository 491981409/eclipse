$(function(){
	
	  //Random Numbers
	  function random(min,max) {
	    return Math.floor(Math.random()*(max-min+1)+min);
	  }
	
	//Main Chart
	  var elements = utils.getCurrentMonthMaxDay();
	  var data1 = [];
	  var data2 = [];
	  var data3 = [];
	  var labels =[];

	  for (var i = 1; i <= elements; i++) {
		labels.push(i);
	    data1.push(random(50,150));
	    data2.push(random(80,100));
	    data3.push(0);
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
	
	 var data = {
			    labels: labels,
			    datasets: [
			      {
			        label: 'My First dataset',
			        backgroundColor: convertHex($.brandInfo,10),
			        borderColor: $.brandInfo,
			        pointHoverBackgroundColor: '#fff',
			        borderWidth: 2,
			        data: data1
			      },
			      {
			        label: 'My Second dataset',
			        backgroundColor: 'transparent',
			        borderColor: $.brandSuccess,
			        pointHoverBackgroundColor: '#fff',
			        borderWidth: 2,
			        data: data2
			      },
			      {
			        label: 'My Third dataset',
			        backgroundColor: 'transparent',
			        borderColor: $.brandDanger,
			        pointHoverBackgroundColor: '#fff',
			        borderWidth: 1,
			        borderDash: [8, 5],
			        data: data3
			      }
			    ]
			  };
	 
	 var options = {
			    maintainAspectRatio: false,
			    legend: {
			      display: false
			    },
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
			          stepSize: Math.ceil(150 / 15),
			          max: 150
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
			    },
			  };
	
	
	var ctx = $('#main-chart');
	  var mainChart = new Chart(ctx, {
	    type: 'line',
	    data: data,
	    options: options,
	    scaleShowLabels : true,
	  });

	
	
});