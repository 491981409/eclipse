$(function(){
  
  utils.websocket(function(data){
	  drawing(data);
  });
  
  function dateMinus(date1,date2){
	  var diff = (date1 - date2);
	  
	  var result = '';
	  if(diff > 0){
		  result ="-";
	  }else{
		  diff = diff * -1;
	  }
	  
	  var d =  parseInt(diff / 60 / 60 / 24 >>0);
	  var h =  parseInt(diff / 60 / 60 % 24);
	  var m =  parseInt(diff / 60 % 60);
	  var s =  parseInt(diff % 60);
	  if(d > 10000){
		  return '0';
	  }
	  
	  if(d > 0){
		  result += d + "天";
	  }
	  if(h > 0){
		  result += h + "小时";
	  }
	  if(m > 0){
		  result += m + "分钟";
	  }
	  if(s > 0){
		  result += s + "秒";
	  }
	  return result;
  }
  
  //推送数据
  function drawing(data){
	  $.each(data['undistributed'],function(i,e){
		  $("#" + getEleId(e.queue_name) +"U").html(e.amount);
	  });
	  $.each(data['pendding'],function(i,e){
		  $("#"+ getEleId(e.queue_name) +"P").html(e.amount);
	  });
	  
	  var tbody =  $("#reportTicketTable");
	  var tbodyHtml ="";
	  $.each(data['ticketList'].list,function(i,e){
		 
		  var html = "<tr>";
		  if( e.upgrade_count != undefined && e.upgrade_count =='1'){
			  html ="<tr style ='color: red;'>";
		  }
		  var td ="<td class=\"text-center\"><span class=\""+getPriorityStatusIcn(e.ticket_priority_name)+"\">&nbsp;&nbsp;&nbsp;&nbsp;</span></td>";
		  td += "<td>"+e.tn+"</td>";
		  td += "<td>"+e.title+"</td>";
		  td += "<td>"+dateMinus(e.system_time , e.escalation_response_time_unix)  +"</td>";
		  td += "<td>"+dateMinus(e.system_time , e.escalation_update_time_unix)  +"</td>";
		  td += "<td>"+dateMinus(e.system_time , e.escalation_solution_time_unix)  +"</td>";
		  td += "<td>"+e.queue_name+"</td>";
		  td += "<td>"+getTicketStatus(e.ticket_status)+"</td>";
		  td += "<td>"+e.user_name+"</td>";
		  html += td + "</tr>";
		  tbodyHtml += html;
	  });
	  tbody.html(tbodyHtml);
  }
  
  function getTicketStatus(status){
	  var result ="";
	  if("open" ==status){
		  result ="处理中";
	  }else if("pending reminder" == status){
		  result ="提醒";
	  }else if("pending auto" == status){
		  result = "自动提醒";
	  }
	  return result;
  }
  
   function getPriorityStatusIcn(priority_name){
	  var result ="";
	  if(priority_name.indexOf("1") != -1){
		  result ="tag tag-primary";
	  }else if (priority_name.indexOf("2") != -1){
		  result ="tag tag-secondary";
	  }else if (priority_name.indexOf("3") != -1){
		  result ="tag tag-3";
	  }else if (priority_name.indexOf("4") != -1){
		  result ="tag tag-4";
	  }else if (priority_name.indexOf("5") != -1){
		  result ="tag tag-5";
	  }
	  return result;
  }
  
   function getEleId(queueName){
	   var queueId = "";
	   switch(queueName){
	   case "桌面支持":
		   queueId = "window";
		   break;
	   case "系统运维":
		   queueId ="system";
	   		break;
	   case "系统支持":
		   queueId ="support";
		   break;
	   case "软件支持":
		   queueId ="software";
		   break;
	   default:
	   		break;
	   }
	   return queueId;
   }
  
  var element = {
		 data :[{title :"桌面支持",id :"window",color:"facebook"},
		        {title :"系统运维" ,id:"system",color:"twitter"},
		        {title :"系统支持",id:"support",color:"linkedin"},
		        {title :"软件支持",id:"software",color:"google-plus"}]
  };
  
  function initHtml(name,id,index,color){
	  var html =" <div class=\"col-sm-6 col-lg-3\">" +
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



  //Sparkline Charts
  var labels = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];

  var options = {
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
    },
  };

  var data1 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: $.brandPrimary,
        borderWidth: 2,
        data: [35, 23, 56, 22, 97, 23, 64]
      }
    ]
  };
  var ctx = $('#sparkline-chart-1');
  var sparklineChart1 = new Chart(ctx, {
    type: 'line',
    data: data1,
    options: options
  });

  var data2 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: $.brandDanger,
        borderWidth: 2,
        data: [78, 81, 80, 45, 34, 12, 40]
      }
    ]
  };
  var ctx = $('#sparkline-chart-2');
  var sparklineChart2 = new Chart(ctx, {
    type: 'line',
    data: data2,
    options: options
  });

  var data3 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: $.brandWarning,
        borderWidth: 2,
        data: [35, 23, 56, 22, 97, 23, 64]
      }
    ]
  };
  var ctx = $('#sparkline-chart-3');
  var sparklineChart3 = new Chart(ctx, {
    type: 'line',
    data: data3,
    options: options
  });

  var data4 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: $.brandSuccess,
        borderWidth: 2,
        data: [78, 81, 80, 45, 34, 12, 40]
      }
    ]
  };
  var ctx = $('#sparkline-chart-4');
  var sparklineChart4 = new Chart(ctx, {
    type: 'line',
    data: data4,
    options: options
  });

  var data5 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: '#d1d4d7',
        borderWidth: 2,
        data: [35, 23, 56, 22, 97, 23, 64]
      }
    ]
  };
  var ctx = $('#sparkline-chart-5');
  var sparklineChart5 = new Chart(ctx, {
    type: 'line',
    data: data5,
    options: options
  });

  var data6 = {
    labels: labels,
    datasets: [
      {
        backgroundColor: 'transparent',
        borderColor: $.brandInfo,
        borderWidth: 2,
        data: [78, 81, 80, 45, 34, 12, 40]
      }
    ]
  };
  var ctx = $('#sparkline-chart-6');
  var sparklineChart6= new Chart(ctx, {
    type: 'line',
    data: data6,
    options: options
  });
  
  
  

});
