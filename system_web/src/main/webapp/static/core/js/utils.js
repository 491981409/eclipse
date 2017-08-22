/**
 * 公用js
 */
var utils = {
	/**
	 * bootstrap查询参数封装
	 * 
	 * @param form
	 *            自定义form表单参数
	 * @param params
	 *            bootstrap自带参数
	 * @returns
	 */
	bootstrapQueryParam : function(form, params) {
		var strQuery = '{';
		var x = $(form).serializeArray();
		$.each(x, function(i, field) {
			if (i == 0)
				strQuery += '"' + field.name + '":"' + field.value + '"';
			else
				strQuery += ',"' + field.name + '":"' + field.value + '"';
		});
		strQuery += '}';
		var json = JSON.parse(strQuery);
		var query = $.extend({}, json, params);
		return query;
	},
	/**
	 * * 格式化金额 *
	 * 
	 * @param s
	 *            要格式化的金额 *
	 * @param n
	 *            保留的金额的小数点位数 *
	 * @returns {String}
	 */
	formatAmount : function(s, n) {
		n = n > 0 && n <= 20 ? n : 2;
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length  && l[i+1] != "-"? "," : "");
		}
		return t.split("").reverse().join("") + "." + r;
	},
	/**
	 * 时间戳转换成日期
	 * @param shijianchuo
	 * @param datePattern
	 * @returns {String}
	 */
	dateFormat: function (shijianchuo,datePattern)
	{
		if(!shijianchuo){return '-';}
		//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		m = m<10?'0'+m:m;
		var d = time.getDate();
		d = d<10?'0'+d:d;
		var h = time.getHours();
		h = h<10?'0'+h:h;
		var mm = time.getMinutes();
		mm = mm<10?'0'+mm:mm;
		var s = time.getSeconds();
		s = s<10?'0'+s:s;
		if(datePattern=='yyyy-MM-dd' || datePattern=='yyyy-MM-dd'){
			return y+'-'+m+'-'+d;
		}else{
			return y+'-'+m+'-'+d+' '+h+':'+mm+':'+s;
		}
	},
	/**
	 *  限制输入数字和. 
	 * 
	 * 
	 * @param ev
	 *            object *
	 *
	 */
	decimal: function(ev){
		ev.value=ev.value.replace(/[^\d.]/g,'');
	},
	getCurrentMonthMaxDay : function(){
		var date = new Date(); 
		var day = new Date(date.getFullYear(),date.getMonth(),"0");
		return day.getDate();
	}
}

