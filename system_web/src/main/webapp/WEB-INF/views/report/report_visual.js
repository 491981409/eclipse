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
		        x : 'center',
		        y : 'bottom',
		        data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'面积模式',
		            type:'pie',
		            radius : [30, 110],
		            center : ['50%', 130],
		            roseType : 'area',
		            x: '50%',               // for funnel
		            max: 40,                // for funnel
		            sort : 'ascending',     // for funnel
		            data:[
		                {value:10, name:'rose1'},
		                {value:5, name:'rose2'},
		                {value:15, name:'rose3'},
		                {value:25, name:'rose4'},
		                {value:20, name:'rose5'},
		                {value:35, name:'rose6'},
		                {value:30, name:'rose7'},
		                {value:40, name:'rose8'}
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
	
	
	
	
	lineOption = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['意向','预购','成交']
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['周一','周二','周三','周四','周五','周六','周日'],
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
		            name:'成交',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:[10, 12, 21, 54, 260, 830, 710]
		        },
		        {
		            name:'预购',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:[30, 182, 434, 791, 390, 30, 10]
		        },
		        {
		            name:'意向',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:[1320, 1132, 601, 234, 120, 90, 20]
		        }
		    ]
		};
		                    
	
	
	var myChartLine = echarts.init(document.getElementById('canvas-line'));
	myChartLine.setOption(lineOption);
	
	
	
	
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
		                return 100 - params.value + '%'
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
		    legend: {
		        x : 'center',
		        y : 'center',
		        data:[
		            'GoogleMaps','Facebook','Youtube','Google+','Weixin'
		        ]
		    },
		    series : [
		        {
		            type : 'pie',
		            center : ['10%', '50%'],
		            radius : radius,
		            x: '0%', // for funnel
		            itemStyle : labelFromatter,
		            data : [
		                {name:'other', value:46, itemStyle : labelBottom},
		                {name:'GoogleMaps', value:54,itemStyle : labelTop}
		            ]
		        },
		        {
		            type : 'pie',
		            center : ['30%', '50%'],
		            radius : radius,
		            x:'20%', // for funnel
		            itemStyle : labelFromatter,
		            data : [
		                {name:'other', value:56, itemStyle : labelBottom},
		                {name:'Facebook', value:44,itemStyle : labelTop}
		            ]
		        },
		        {
		            type : 'pie',
		            center : ['50%', '50%'],
		            radius : radius,
		            x:'40%', // for funnel
		            itemStyle : labelFromatter,
		            data : [
		                {name:'other', value:65, itemStyle : labelBottom},
		                {name:'Youtube', value:35,itemStyle : labelTop}
		            ]
		        },
		        {
		            type : 'pie',
		            center : ['70%', '50%'],
		            radius : radius,
		            x:'60%', // for funnel
		            itemStyle : labelFromatter,
		            data : [
		                {name:'other', value:70, itemStyle : labelBottom},
		                {name:'Google+', value:30,itemStyle : labelTop}
		            ]
		        }
		    ]
		};
		
		var myChartAnnular = echarts.init(document.getElementById('canvas-Annular'));
		myChartAnnular.setOption(optionAnnular);
   
});
