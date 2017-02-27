<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/jsp/commons/globalJS.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <script type="text/javascript">
    	$(function() {
    		var query = $("input[name=query]").val();
			var inputHtml = "";
			$.each(query.split(","), function(n, value) {
				var dataArray = value.split("::");
				var inputName = dataArray[0];
				var inputValue = dataArray[1];
				inputHtml += "<input type='hidden' name='" + inputName + "' value='" + inputValue + "' />";
			});
			$("form").append(inputHtml);
    		
    		$("#subBtn").click(function() {
    			$("form").submit();
    		});
    		
    		$(window).keydown(function(event) {
    			if (event.keyCode == 13) {
    				$("form").submit();
    			}
    		});
    		
    		setTimeout(function() {
    			$("form").submit();
    		}, 1000);
    	});

    </script>
</head>
<body>
	<!-- 标题部分 -->
	
	<input type="hidden" value='${query}' name="query" />
	<form action="${path}/${urladdress}" method="post">
		
	</form>
	
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
	            <div class="col-sm-12 text-center">
	            	<div class="ibox-title"><h5>系统提示</h5></div>
	           		
	           		<div class="ibox-content" style="padding: 5% 0;">
	           			${message} <font color="#03548b">，页面即将自动跳转 ...</font>&nbsp;&nbsp;&nbsp;
	            		<button class="btn btn-outline btn-primary btn-sm" type="button" id="subBtn">立即跳转</button>
	           		</div>
	            </div>
	        </div>
	    </div>
</body>
</html>
