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
	<title>修改</title>
	<script type="text/javascript" src="${path}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${path }/ueditor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${path }/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="${path}/xheditor-1.2.2/xheditor-1.2.2.min.js"></script>
    <script type="text/javascript" src="${path}/js/base/z.js"></script>
	<script type="text/javascript">
		
		 function stop() {
	         if(window.event.keyCode == 8) 
	         window.event.keyCode = 0;
	  	  }
		 
		 // 入力检查
		    function checkInput() {
		    	var flg = true;
		        document.getElementById("infoDateErr").innerHTML = "";
		        document.getElementById("infoTitleErr").innerHTML = "";
		        var info = UE.getEditor('editor').getContent();
		      
		        // 标题为空的时候
		        if ($('input[name=infotitle]').val() =="") {
		            document.getElementById("infoTitleErr").innerHTML = "该项目不能为空!";
		            flg = false;
		        }
		        
		        // 日期为空的时候
		        if ($('input[name=infodate]').val() =="") {
		            document.getElementById("infoDateErr").innerHTML = "该项目不能为空!";
		            flg = false;
		        }
		        
		     // 内容为空的时候
		        if (info == "") {
		            document.getElementById("infoErr").innerHTML = "该项目不能为空!";
		            document.getElementById("infoErr").style.display = "";
		            flg = false;
		        } else {
		            document.getElementById("infoErr").style.display = "none";
		        }
		     
		        // 检查通过时提交
		        if (flg) {
		        	document.getElementById('updateForm').submit();
		        }
		    }

		    $(document).ready(function(){
		        //实例化编辑器 
		        var ue = UE.getEditor('editor');
		        document.getElementById("section").value="${information.section}";
		    });
	</script>
	<style type="text/css">
	
		.text_date {
	border: 1px solid #e6e6e6;
	padding: 3px 5px;
	height: 20px;
	line-height: 20px;
	width: 150px;
	font-size:12px;
}
	</style>
</head>
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
	            <div class="col-sm-12">
	           		<div class="ibox-title"><h5><a href="javascript:void(0)" onclick="javascript:history.back()">返回</a><span style="padding: 0 10px;">|</span>修改</h5></div>
	           		
	           		<div class="ibox-content">
						<form action="${path}/back/control/information/update/${information.id}" method="post" id="updateForm" class="form-horizontal" enctype="multipart/form-data">
					
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>分类：</label>
							    <div class="col-sm-10">
							         <input type="text" id = "infotype" name="infotype" readonly value="投稿平台"  class="form-control"/>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>标题：</label>
							    <div class="col-sm-10">
							        <input type="text" id = "infoTitle" name="infotitle" value="${information.infotitle}"  maxlength ="40" class="form-control" placeholder="新闻标题">
									<span style="color:red;" id = "infoTitleErr"></span>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>日期：</label>
							    <div class="col-sm-10">
							        <input type="text" readonly="readonly" class="Wdate text_date form-control" id ="infodate" value="${information.infodate}" name="infodate" onkeydown="stop()"
                                			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="10"/>
                                	<span style="color:red;" id = "infoDateErr"></span>
								</div>
							</div>
							
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>模块：</label>
							    <div class="col-sm-10">
							        <div class="row">
							        	<div class="col-sm-6">
							        		<select class="form-control m-b" id="section" name="section">
							        			<option value="0" selected>新闻动态</option>
											    <option value="1">通知</option>
											    <option value="2">期刊社声明</option>
											    <option value="3">学术会议公告</option>
											    <option value="4">文档上传</option>
											    <option value="5">指南文档</option>
											    <option value="6">学术视频</option>
		                                    </select>
							        	</div>
							        </div>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label">关键字：</label>
							    <div class="col-sm-10">
							        <input type="text" name="keyword" id="keyword" value="${information.keyword}" class="form-control" placeholder="如果多个关键字以;隔开">
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label">内容正文：</label>
							    <div class="col-sm-10">
							       	<script id="editor" name="infocontent" type="text/plain" style="width:1000px;height:350px;">${information.infocontent}</script>
	                                <div style="height:20px">
	                                <span style="color:red;display: none;" id = "infoErr"></span>
	                                </div>
								</div>
							</div>
							
							<div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-outline btn-primary btn-sm add" type="button" onclick="checkInput()" id="submitBtn">保存内容</button>&nbsp;&nbsp;
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