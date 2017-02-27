<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/jsp/commons/globalJS.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>修改系统用户</title>
	
</head>
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
	            <div class="col-sm-12">
	           		<div class="ibox-title"><h5><a href="javascript:void(0)" onclick="javascript:history.back()">返回</a><span style="padding: 0 10px;">|</span>修改系统用户</h5></div>
	           		
	           		<div class="ibox-content">
						<form action="${path}/back/control/sysUserManage/update/${sysUser.id}" method="post" id="updateForm" class="form-horizontal">
					
							<input type="hidden" value="${query}" name="query" />
					          
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>真实姓名：</label>
							    <div class="col-sm-10">
							        <input type="text" name="realname" class="form-control" value="${sysUser.realname}" required="">
							    </div>
							</div>
							
							<div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-outline btn-primary btn-sm add" type="submit" id="submitBtn">保存内容</button>&nbsp;&nbsp;
                                    <button class="btn btn-outline btn-default btn-sm add" type="button" onclick="javascript:history.back()">返回</button>
                                </div>
                            </div>
					          
						</form>
					</div>
	           		
	            </div>
				
	    	</div>
		</div>
	</body>
</html>