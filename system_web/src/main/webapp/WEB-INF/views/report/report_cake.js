$(function(){
	console.log("wer");
	
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
	
	
	
	
	$("html").keyup(function(data){
		if(data.keyCode == 72){
			var flag = $("#header").is(":hidden");
			$(".navbar-toggler").trigger("click");
			if(flag){
				$("#header").hide();
			}else{
				$("#header").hide();
			}
		}
	});
	
	
	option = {
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:['桌面支持','系统支持','系统运维','软件支持']
		    },
		    title: {
		        text:'2000',
		        x: 'center',
		        y: 'center',
		        textStyle: {
		            fontSize: '42',
		            color:'white',
		            fontWeight: 'normal'
		        }
		   },
		    series : [
		        
		        {
		            name:'访问来源',
		            type:'pie',
		            center : ['50%', 200],
		            radius : [110, 140],
		            label: {
		                normal: {
		                    formatter: '{b|{b}：}{c}',
		                    backgroundColor: '#eee',
		                    borderColor: '#aaa',
		                    borderWidth: 1,
		                    borderRadius: 4,
		                    // shadowBlur:3,
		                    // shadowOffsetX: 2,
		                    // shadowOffsetY: 2,
		                    // shadowColor: '#999',
		                     padding: [0, 7],
		                    rich: {
		                         d: {
		                             backgroundColor: '#333',
		                             width: '100%',
		                             align: 'right',
		                             height: 22,
		                             fontSize: 26,
		                             borderRadius: [4, 4, 0, 0]
		                         },
		                        b: {
		                            fontSize: 22,
		                            lineHeight: 33
		                        },
		                        per: {
		                            color: '#eee',
		                            backgroundColor: '#334455',
		                            padding: [2, 4],
		                            borderRadius: 2
		                        }
		                    }
		                }
		            },
		            data:[
		                {value:335, name:'桌面支持',itemStyle : {
	                        normal : {
	                            label : {
	                                textStyle : {
	                                    color : 'rgba(30,144,255,0.8)',
	                                    fontFamily : '微软雅黑',
	                                    fontSize : 22,
	                                    fontWeight : 'bolder'
	                                }
	                            }
	                        }
	                    }
		                },
		                {value:310, name:'系统支持',itemStyle : {
	                        normal : {
	                            label : {
	                                textStyle : {
	                                    color : 'rgba(30,144,255,0.8)',
	                                    fontFamily : '微软雅黑',
	                                    fontSize : 22,
	                                    fontWeight : 'bolder'
	                                }
	                            }
	                        }
	                    }},
		                {value:234, name:'系统运维',itemStyle : {
	                        normal : {
	                            label : {
	                                textStyle : {
	                                    color : 'rgba(30,144,255,0.8)',
	                                    fontFamily : '微软雅黑',
	                                    fontSize : 22,
	                                    fontWeight : 'bolder'
	                                }
	                            }
	                        }
	                    }},
		                {
		                    value:1048,
		                    name:'软件支持',
		                    itemStyle : {
		                        normal : {
		                            label : {
		                                textStyle : {
		                                    color : 'rgba(30,144,255,0.8)',
		                                    fontFamily : '微软雅黑',
		                                    fontSize : 22,
		                                    fontWeight : 'bolder'
		                                }
		                            }
		                        }
		                    }
		                }
		            ]
		        }
		    ]
		};
		                                  
	
	/*option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel']
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : false,
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            selectedMode: 'single',
		            radius : [0, 70],
		            
		            // for funnel
		            x: '20%',
		            width: '40%',
		            funnelAlign: 'right',
		            max: 1548,
		            
		            itemStyle : {
		                normal : {
		                    label : {
		                        position : 'inner'
		                    },
		                    labelLine : {
		                        show : false
		                    }
		                }
		            },
		            data:[
		                {value:335, name:'直达'},
		                {value:679, name:'营销广告'},
		                {value:1548, name:'搜索引擎', selected:true}
		            ]
		        },
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : [100, 140],
		            
		            // for funnel
		            x: '60%',
		            width: '35%',
		            funnelAlign: 'left',
		            max: 1048,
		            
		            data:[
		                {value:335, name:'直达'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1048, name:'百度'},
		                {value:251, name:'谷歌'},
		                {value:147, name:'必应'},
		                {value:102, name:'其他'}
		            ]
		        }
		    ]
		};*/
		                    
	
	var myChart = echarts.init(document.getElementById('canvas-cake'));
	myChart.setOption(option);
   
});
