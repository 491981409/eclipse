$(function(){
	
    //$("#queueAmount").draggable();
	$("#graph").draggable();
	$("#ticketGride").draggable();
	$("#ticketProcess").draggable();
	
	$("#weekWork").draggable();
	
	$("#queue").resizable();
	
	 $('#graph').contextMenu('myMenu1', {
	      bindings: {
	        'max': function(t) {
	          loadPage("views/core/report_poll");
	        }
	      }
	  });
	 
	 $('#ticketGride').contextMenu('myMenu1', {
	      bindings: {
	        'max': function(t) {
	          loadPage("views/ticket/ticket_list");
	        }
	      }
	  });
	 
	 $('#ticketProcess').contextMenu('myMenu1', {
	      bindings: {
	        'max': function(t) {
	          loadPage("views/core/report_poll");
	        }
	      }
	  });
	 
	 
	
  utils.websocket(function(data){
	  drawing(data);
  });
  
  function dateMinus(date1,date2){
	  if(data2 == 0){
		  return "0";
	  }
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
	  jointTable(data['ticketProcessList'].list,$("#reportTicketTable"));
	  jointTable(data['ticketGrideList'].list,$("#reportTicketGrideTable"));
  }
  
  
  $.ajax({
	  url:"ticket/ticketProcess",
	  dataType:"json",
	  async:false,
	  success:function(res){
		  jointTable(res.result.list,$("#reportTicketTable"));
	  }
  });
  
  $.ajax({
	  url:"ticket/ticketGride",
	  dataType:"json",
	  async:false,
	  success:function(res){
		  jointTable(res.result.list,$("#reportTicketGrideTable"));
	  }
  });
  
  
  function jointTable(data,$table){
	  var tbodyHtml ="";
	  $.each(data,function(i,e){
		  var html = "<tr>";
		  if( e.upgrade_count != undefined && e.upgrade_count =='1'){
			  html ="<tr style ='color: red;'>";
		  }
		  var td ="<td class=\"text-center\"><span class=\""+getPriorityStatusIcn(e.ticket_priority_name)+"\">&nbsp;&nbsp;&nbsp;&nbsp;</span></td>";
		 // td += "<td>"+e.tn+"</td>";
		  td += "<td>"+e.title+"</td>";
		  //td += "<td>"+dateMinus(e.system_time , e.escalation_response_time_unix)  +"</td>";
		  //td += "<td>"+dateMinus(e.system_time , e.escalation_update_time_unix)  +"</td>";
		  //td += "<td>"+dateMinus(e.system_time , e.escalation_solution_time_unix)  +"</td>";
		 // td += "<td>"+e.queue_name+"</td>";
		 // td += "<td>"+getTicketStatus(e.ticket_status)+"</td>";
		  td += "<td>"+utils.dateFormat(e.create_time)+"</td>";
		  td += "<td>"+e.user_name+"</td>";
		  html += td + "</tr>";
		  tbodyHtml += html;
	  });
	  $table.html(tbodyHtml);
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
  
  
  $("body").on('click',"[data-ma-action]",function(e){
	  e.preventDefault();
      var $this = $(this)
        , action = $(this).data("ma-action");
	 switch (action) {
	case 'todo-form-open':
		$this.closest(".t-add").addClass("toggled");
		break;
	case 'todo-form-close':
		$this.closest(".t-add").removeClass("toggled"),
        $this.closest(".t-add").find("textarea").val("");
		break;
	case 'week_delete':
		$(this).closest(".list-group-item").remove();
		break;
	default:
		break;
	}
  });
  
  
  ///添加重点工作
  
  var $html = $(document.getElementById('html_template').innerHTML);
  
  $("#weekSubmit").on("click",function(){
	  var value = $("#week_text").val();
	  $html.find("span").html(value);
	  $("#weekContent").prepend($html.html());
  });
  
});
