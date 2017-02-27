<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/jsp/commons/globalJS.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
    <title>个人密码修改</title>
    <script type="text/javascript">
    	$(function() {
    		$("#updatePassForm").submit(function() {
    			var oldPassword = $("#oldPassword").val();
    			var password = $("#password").val();
    			var rePassword = $("#rePassword").val();

    			if (password != rePassword) {
    				swal({title:"提示", text:"密码输入不一致！"});
    				return false;
    			}
    		});
    	});
    </script>
  </head>
  
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
	            <div class="col-sm-12">
	           		
	           		<div class="ibox-content">
						<form action="${path}/back/control/sysUserManage/updatePassword" method="post" id="updatePassForm" class="form-horizontal">
					
							<input type="hidden" value="${query}" name="query" />
					          
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>原密码：</label>
							    <div class="col-sm-10">
							        <input type="password" name="oldPassword" id="oldPassword" class="form-control" required="">
							    </div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>新密码：</label>
							    <div class="col-sm-10">
							        <input type="password" name="password" id="password" class="form-control" required="">
							    </div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>新密码确认：</label>
							    <div class="col-sm-10">
							        <input type="password" name="rePassword" id="rePassword" class="form-control" required="">
							    </div>
							</div>
							
							<div class="hr-line-dashed"></div>
	                           <div class="form-group">
	                               <div class="col-sm-4 col-sm-offset-2">
	                                   <button class="btn btn-outline btn-primary btn-sm add" type="submit">保存内容</button>
	                               </div>
	                           </div>
					          
						</form>
					</div>
	           		
	            </div>
				
	    	</div>
		</div>
	</body>
  
</html>
