$(function(){
	
	console.log("2343");
	var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
	var timerID = setInterval(updateTime, 1000);
	updateTime();
	function updateTime() {
	    var cd = new Date();
	    $("#time").html(zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2));
	    $("#date").html(zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()]);
	};

	function zeroPadding(num, digit) {
	    var zero = '';
	    for(var i = 0; i < digit; i++) {
	        zero += '0';
	    }
	    return (zero + num).slice(-digit);
	}
	
	

	utils.websocket(function(data){
		drawingBar(data.bar);
		drawingList(data['ticketProcessList'].list);
		drawingCake(data);
		drawingLine(data.line);
		drawingAnnular(data);
		$("#monthCount").html(data.monthCount);
		drawingRemarkList(data.remark);
	});
	
/*********************************begin 重点工作列表 ************************************/
	var $remark = $(document.getElementById('remark_template').innerHTML);
	function drawingRemarkList(data){
		var html ='';
		$.each(data,function(i,e){
			$remark.find(".remark_number").html("NO."+(i+1));
			$remark.find(".remark_content").html(e.rContent);
			html+= $remark.html();
		});
		$("#remark").html(html);
	}
	
/*********************************end 重点工作列表 ************************************/
	
	
	
 /*********************************begin 未处理工单列表 ************************************/
	
	
	function drawingList(data){
		var html ='';
		$.each($("#processTable").children(),function(i,e){
			if(i>0){
				$(e).remove().fadeOut();
			}
			
		});
		
		$.each(data,function(i,e){
			//$("#processTable").children().eq(1).remove();
			var $html = $(document.getElementById('html_template').innerHTML);
			 if((i+1) % 2 == 0 && i >0){
				 $html.children().css("background-color","rgba(255,255,255,0.05)");
			 }
			$html.find(".cell-content-number").html(i+1);
			$html.find(".cell-content-title").html(e.title);
			$html.find(".cell-content-time").html(utils.dateFormat(e.create_time,'MM-dd'));
			$html.find(".cell-content-user").html(e.user_name);
			$("#processTable").append($html.html()).fadeIn();
		});
		
	}
	
/*********************************end 未处理工单列表 ************************************/

	
	
	
	
 /*********************************begin 饼图 ************************************/
	var currentIndex = -1;
	var cakeInter;
	function drawingCake(data){
		optionCake.series[0].data=[];
		//optionCake.legend.data=[];
		$.each(data.pendding,function(i,e){
			optionCake.series[0].data.push({value:e.amount,name:e.queue_name});
			//optionCake.legend.data.push(e.queue_name);
		});
		myChartCake.setOption(optionCake);
		myChartCake.resize();
		
		clearInterval(cakeInter);
		
		dynamicCake();
		
		cakeInter = setInterval(function () {
			dynamicCake();
		}, 1000*10);
	}
	
	function dynamicCake(){
		
		 var dataLen = optionCake.series[0].data.length;
		    // 取消之前高亮的图形
		    myChartCake.dispatchAction({
		        type: 'downplay',
		        seriesIndex: 0,
		        dataIndex: currentIndex
		    });
		    currentIndex = (currentIndex + 1) % dataLen;
		    // 高亮当前图形
		    myChartCake.dispatchAction({
		        type: 'highlight',
		        seriesIndex: 0,
		        dataIndex: currentIndex
		    });
		    // 显示 tooltip
		    myChartCake.dispatchAction({
		        type: 'showTip',
		        seriesIndex: 0,
		        dataIndex: currentIndex
		    });
	}
	
	optionCake = {
		    color:['#03a9f4','#26c0c0','#d7504b','#e2d00f'],
		    series : [
		        {
//		            name:'面积模式',
//		            type:'pie',
//		            radius : [30, 110],
//		            center : ['50%', 160],
//		            roseType : 'area',
//		            x: '50%',               // for funnel
//		            max: 40,                // for funnel
//		            sort : 'ascending',     // for funnel
		            type:'pie',
		            radius: ['50%', '70%'],
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center',
		                    formatter: '{b}：{c}',
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data:[
		            ]
		        }
		    ]
		};
	var myChartCake = echarts.init(document.getElementById('canvas-cake'));
	myChartCake.setOption(optionCake);
	
	/**************************** end 饼图**************************************/
	
	
	
	
	
	
	/**************************** begin 线形图**************************************/
	var linexAxisData=[];
	var lineSeriesCreate =[];
	var lineSeriesGrade =[];
	
	
	function drawingLine(data){
		optionLine.xAxis[0].data = data.lable;
		optionLine.series[0].data = data.create;
		optionLine.series[1].data = data.grade;
		myChartLine.setOption(optionLine);
		myChartLine.resize();
	}
	
	optionLine = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['创建','升级'],
		        textStyle:{    //图例文字的样式
		            color:'#CCC',
		            fontSize:12
		        }
		    
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : linexAxisData,
		            axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
		            splitLine:{
		                show:false
		            },
		              splitArea:{
		                show:false
		              }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
		            splitLine:{
		                show:false
		            	},
		              splitArea:{
		                show:false
		              }
		        }
		    ],
		    series : [
		        {
		            name:'创建',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:lineSeriesCreate
		        },
		        {
		            name:'升级',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:lineSeriesGrade
		        }
		    ]
		};
	
	var myChartLine = echarts.init(document.getElementById('canvas-line'));
	myChartLine.setOption(optionLine);
	
	/**************************** end 线形图**************************************/
		                    
	
	
	
	
	
	/**************************** biegin 柱形图**************************************/
	
	var categories = [];
	var upgrade = [];
	var open = [];
	
	$.ajax({
		url:"../../ticket/bar",
		dataType:"json",
		async:false,
		success:function(data){
			$.each(data,function(i,e){
				categories.push(e.userName);
				upgrade.push(e.open);
				open.push(e.open-e.upgrade);
			});
		}
	});

	
	function drawingBar(data){
		var categories = [];
		var upgrade = [];
		var open = [];
		$.each(data,function(i,e){
			categories.push(e.userName);
			upgrade.push(e.open);
			open.push((e.open-e.upgrade));
		});
		optionBar.xAxis[0].data = categories;
		optionBar.series[0].data = upgrade;
		optionBar.series[1].data = open;
		optionBar.series[2].data = upgrade;
		optionBar.series[3].data = upgrade;
		myChartBar.setOption(optionBar, true);
		myChartBar.resize();
	}
	
	var	optionBar = {
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				}
			},
			legend : {
				data : [ '未处理', '已升级' ],
				textStyle : {
					color : '#ccc'
				}
			},
			xAxis : [{
				data : categories,
				axisLine : {
					lineStyle : {
						color : '#ccc'
					}
				}
			}],
			yAxis : {
				splitLine : {
					show : false
				},
				axisLine : {
					lineStyle : {
						color : '#ccc'
					}
				}
			},
			series: [{
		        name: '已升级',
		        type: 'line',
		        smooth: true,
		        showAllSymbol: true,
		        symbol: 'emptyCircle',
		        symbolSize: 15,
		        data: upgrade
		    }, {
		        name: '未处理',
		        type: 'bar',
		        barWidth: 10,
		        itemStyle: {
		            normal: {
		                barBorderRadius: 5,
		                color: new echarts.graphic.LinearGradient(
		                    0, 0, 0, 1,
		                    [
		                        {offset: 0, color: '#14c8d4'},
		                        {offset: 1, color: '#43eec6'}
		                    ]
		                )
		            }
		        },
		        data: open
		    }, {
		        name: '已升级',
		        type: 'bar',
		        barGap: '-100%',
		        barWidth: 10,
		        itemStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(
		                    0, 0, 0, 1,
		                    [
		                        {offset: 0, color: 'rgba(20,200,212,0.5)'},
		                        {offset: 0.2, color: 'rgba(20,200,212,0.2)'},
		                        {offset: 1, color: 'rgba(20,200,212,0)'}
		                    ]
		                )
		            }
		        },
		        z: -12,
		        data:  upgrade
		    }, {
		        name: 'dotted',
		        type: 'pictorialBar',
		        symbol: 'rect',
		        itemStyle: {
		            normal: {
		                color: 'rgba(63, 153, 243, 0.28)'
		            }
		        },
		        symbolRepeat: true,
		        symbolSize: [12, 4],
		        symbolMargin: 1,
		        z: -10,
		        data: upgrade
		    }]
		};

		// 使用刚指定的配置项和数据显示图表。
		var myChartBar = echarts.init(document.getElementById('canvas-bar'));
		myChartBar.setOption(optionBar);
		myChartBar.resize();
		
   /**************************** end 柱形图**************************************/
   
		
		
   
  /**************************** begin 环形图 **************************************/
		var monthCount =0;
		function drawingAnnular(data){
			monthCount =data.monthCount ;
			optionAnnular.series[0].data[0].value =data.monthCount - data.gradeCount;
			optionAnnular.series[0].data[1].value =data.gradeCount;
			optionAnnular.series[1].data[0].value =data.monthCount - data.openCount;
			optionAnnular.series[1].data[1].value =data.openCount;
			optionAnnular.series[2].data[0].value =data.monthCount - data.closedCount;
			optionAnnular.series[2].data[1].value =data.closedCount;
			myChartAnnular.setOption(optionAnnular);
		}
		
		var labelTop = {
			    normal : {
			        label : {
			            show : true,
			            position : 'center',
			            formatter : '{b}',
			            textStyle: {
			                baseline : 'bottom'
			            }
			        },
			        labelLine : {
			            show : false
			        }
			    }
			};
			var labelFromatter = {
			    normal : {
			        label : {
			            formatter : function (params){
			                return monthCount- params.value;
			            },
			            textStyle: {
			                baseline : 'top'
			            }
			        }
			    },
			}
			var labelBottom = {
			    normal : {
			        color: '#ccc',
			        label : {
			            show : true,
			            position : 'center'
			        },
			        labelLine : {
			            show : false
			        }
			    },
			    emphasis: {
			        color: 'rgba(0,0,0,0)'
			    }
			};
			var radius = [40, 55];
			optionAnnular = {
					  color:['#d7504b','#fad860','#60c0dd'],  
			    series : [
			        {
			            type : 'pie',
			            center : ['10%', '50%'],
			            radius : radius,
			            x: '0%', // for funnel
			            itemStyle : labelFromatter,
			            data : [
			                {name:'other', value:0, itemStyle : labelBottom},
			                {name:'升级工单', value:0,itemStyle : labelTop}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['30%', '50%'],
			            radius : radius,
			            x:'20%', // for funnel
			            itemStyle : labelFromatter,
			            data : [
			                {name:'other', value:0, itemStyle : labelBottom},
			                {name:'未关闭工单', value:0,itemStyle : labelTop}
			            ]
			        },
			        {
			            type : 'pie',
			            center : ['50%', '50%'],
			            radius : radius,
			            x:'40%', // for funnel
			            itemStyle : labelFromatter,
			            data : [
			                {name:'other', value:0, itemStyle : labelBottom},
			                {name:'已处理工单', value:0,itemStyle : labelTop}
			            ]
			        }
			    ]
			};
			
			var myChartAnnular = echarts.init(document.getElementById('canvas-Annular'));
			myChartAnnular.setOption(optionAnnular);	
			
   /**************************** end 环形图 **************************************/	
		
   
});
