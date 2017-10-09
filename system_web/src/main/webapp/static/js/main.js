
require.config({
    paths: {
        "jquery": "libs/jquery-1.11.0.min"
    },
    shim: {
        "test" : {
        	deps:[],
            exports : "StringTest"
        }
    }
});

require(["jquery","test"]);

//
//require(['jquery'], function ($){
//	
//	$("#logout").click(function(){
//			$.ajax({
//				url:"login/loginout",
//				type:"post",
//				success:function(data){
//					window.location.href = "login";
//				}
//			});
//		});
//		
//		$("html").keyup(function(data){
//			if(data.keyCode == 72){
//				var flag = $("#header").is(":hidden");
//				$(".navbar-toggler").trigger("click");
//				if(flag){
//					$("#header").hide();
//				}else{
//					$("#header").hide();
//				}
//			}
//		});
//});