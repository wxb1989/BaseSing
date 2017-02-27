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
	<title>添加项目</title>
	<script type="text/javascript">
		
		 function stop() {
	         if(window.event.keyCode == 8) 
	         window.event.keyCode = 0;
	  	  }
		 
		 // 入力检查
		    function checkInput() {
		    	var flg = true;
		        document.getElementById("pNameErr").innerHTML = "";
		        document.getElementById("addXueshuErr").innerHTML = "";
		       
		        //添加所有的医院以及负责人
		        var strHos = '[';
		     	for(var i=0; i<($("#hos_id tr").length-1); i++){
		     		strHos += '{"hosname":"'+ $("#hosSel"+i).find("option:selected").text() +'","hosdirector":"'+  $("#addhosPers"+i).val() +'","hosid":"' +  $("#hosSel"+i).val()+'","roomname":"'+  $("#roomSel"+i).find("option:selected").text()  +'"},';
		     		
		     	}
		     	strHos = strHos.substring(0,strHos.lastIndexOf(','));
		     	strHos +=']';
		        
		        //添加所有的社区以及负责人
		     	var strCom = '[';
		     	for(var i=0; i<($("#com_id tr").length-1); i++){
		     		strCom += '{"comname":"'+ $("#comSel"+i).find("option:selected").text() +'","comdirector":"'+  $("#addcomPers"+i).val() +'","comid":"'+  $("#comSel"+i).val() +'"},';
		     		
		     	}
		     	strCom = strCom.substring(0,strCom.lastIndexOf(','));
		     	strCom +=']';
		     
		     	$("#hosSelSum").val(strHos);
		     	$("#comSelSum").val(strCom);
		        
			     // 项目名称为空的时候
			        if ($('input[name=pName]').val() =="") {
			            document.getElementById("pNameErr").innerHTML = "该项目不能为空!";
			            flg = false;
			        }
			        
			        // 学术组负责人为空的时候
			        if ($('input[name=xueshu_director]').val() =="") {
			            document.getElementById("addXueshuErr").innerHTML = "该项目不能为空!";
			            flg = false;
			        }
			     
		        // 检查通过时提交
		        if (flg) {
		        	document.getElementById('addForm').submit();
		        }
		    }
		 	var hoscount  = 1;
		 	var comcount  = 1;
		 	function addhospital() {
		    	 var Table = document.getElementById("hos_id");   //取得自定义的表对象
		         NewRow = Table.insertRow();                        //添加行
		         NewCell1= NewRow.insertCell();                     //添加列
		         NewCell2=NewRow.insertCell();
		         NewCell3=NewRow.insertCell();
		         NewCell4=NewRow.insertCell();
		         NewCell5=NewRow.insertCell();
		         NewCell6=NewRow.insertCell();
		         NewCell1.innerHTML = "<label class='col-sm-2 control-label'><font color='#FF0000'></font>医院名称：</label>";          //添加数据
		         NewCell2.innerHTML="<select class='form-control m-b' id='hosSel"+hoscount+"' name='hosSel"+hoscount+"'><option value='0' selected>常州第一人民医院</option><option value='1'>苏州第一人民医院</option><option value='2'>无锡第一人民医院</option><option value='3'>上海2院</option></select>";
		         NewCell3.innerHTML = "<label class='col-sm-2 control-label'><font color='#FF0000'></font>科室名称：</label>"; 
		         NewCell4.innerHTML="<select class='form-control m-b' id='roomSel"+hoscount+"' name='roomSel"+hoscount+"'><option value='0' selected>心内科</option><option value='1'>产科</option><option value='2'>内分泌</option><option value='3'>妇科</option></select>";
		         NewCell5.innerHTML="<label class='col-sm-2 control-label'><font color='#FF0000'></font>该医院组负责人：</label>";
		         NewCell6.innerHTML="<input type='text' id = 'addhosPers"+ hoscount+"' name='addhosPers"+ hoscount+"'  maxlength ='40' class='form-control' placeholder='负责人'>";
		         hoscount++;
		  }
		    
		    function addcommunity() {
		    	 var Table = document.getElementById("com_id");   //取得自定义的表对象
		         NewRow = Table.insertRow();                        //添加行
		         NewCell1= NewRow.insertCell();                     //添加列
		         NewCell2=NewRow.insertCell();
		         NewCell3=NewRow.insertCell();
		         NewCell4=NewRow.insertCell();
		         NewCell1.innerHTML = "<label class='col-sm-2 control-label'><font color='#FF0000'></font>社区医院名称：</label>";          //添加数据
		         NewCell2.innerHTML="<select class='form-control m-b' id='comSel"+comcount+"' name='comSel"+comcount+"'><option value='0' selected>常州茶山街道社区医院</option><option value='1'>苏州社区医院</option><option value='2'>无锡第一社区医院</option><option value='3'>上海小区</option></select>";
		         NewCell3.innerHTML="<label class='col-sm-2 control-label'><font color='#FF0000'></font>该社区组负责人：</label>";
		         NewCell4.innerHTML="<input type='text' id = 'addcomPers"+ comcount+"' name='addcomPers"+ comcount+"'  maxlength ='40' class='form-control' placeholder='社区组负责人'>";
		         comcount++;
		  }
		    
		 
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
	           		<div class="ibox-title">
	           			<h5><a href="javascript:void(0)" onclick="javascript:history.back()">返回</a><span style="padding: 0 10px;">|</span>添加</h5>
	           		</div>
	           		
	           		<div class="ibox-content">
						<form action="${path}/project/add" method="post" id="addForm" class="form-horizontal" enctype="multipart/form-data">
							<input type="hidden" id="hosSelSum" name="hosSelSum">
							<input type="hidden" id="comSelSum" name="comSelSum">
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>项目名称：</label>
							    <div class="col-sm-10">
							         <input type="text" id = "pName" name="pName"  value=""  class="form-control" placeholder="请输入项目名称"/>
							         <span style="color:red;" id = "pNameErr"></span>
								</div>
							</div>
							
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>所在城市：</label>
							    <div class="col-sm-10">
							        <div class="row">
							        	<div class="col-sm-6">
							        		<select class="form-control m-b" id="pCity" name="pCity">
							        			<option value="0" selected>常州</option>
											    <option value="1">苏州</option>
											    <option value="2">无锡</option>
											    <option value="3">上海</option>
		                                    </select>
							        	</div>
							        </div>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>涉及的医院(二三甲)：</label>
							    <div class="col-sm-10">
							        <table id="hos_id">
							        	<tr>
							        		<td> <button class="btn btn-outline btn-default btn-sm add" type="button" onclick="addhospital()">添加</button></td>
							        	</tr>
							        	<tr>
							        		<td><label class="col-sm-2 control-label"><font color="#FF0000"></font>医院名称：</label></td>
							        		<td>
							        			<select class="form-control m-b" id="hosSel0" name="hosSel0">
								        			<option value="0" selected>常州第一人民医院</option>
												    <option value="1">苏州第一人民医院</option>
												    <option value="2">无锡第一人民医院</option>
												    <option value="3">上海2院</option>
		                                   		 </select>
							        		</td>
							        		<td><label class="col-sm-2 control-label"><font color="#FF0000"></font>科室名称：</label></td>
							        		<td>
							        			<select class="form-control m-b" id="roomSel0" name="roomSel0">
								        			<option value="0" selected>心内科</option>
												    <option value="1">产科</option>
												    <option value="2">内分泌科</option>
												    <option value="3">妇科</option>
		                                   		 </select>
							        		</td>
							        		<td><label class="col-sm-2 control-label"><font color="#FF0000"></font>该医院组负责人：</label></td>
							        		<td><input type="text" id = "addhosPers0" name="addhosPers0"  maxlength ="40" class="form-control" placeholder="负责人"></td>
							        	</tr>
							        </table>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>涉及的社区医院：</label>
							    <div class="col-sm-10">
							        <table id="com_id">
							        	<tr>
							        		<td> <button class="btn btn-outline btn-default btn-sm add" type="button" onclick="addcommunity()">添加</button></td>
							        	</tr>
							        	<tr>
							        		<td><label class="col-sm-2 control-label"><font color="#FF0000"></font>社区医院名称：</label></td>
							        		<td>
							        			<select class="form-control m-b" id="comSel0" name="comSel0">
								        			<option value="0" selected>常州茶山街道社区医院</option>
												    <option value="1">苏州社区医院</option>
												    <option value="2">无锡第一社区民医院</option>
												    <option value="3">上海小区医院</option>
		                                   		 </select>
							        			<span style="color:red;" id = "comSelErr"></span>
							        		</td>
							        		<td><label class="col-sm-2 control-label"><font color="#FF0000"></font>该社区医院组负责人：</label></td>
							        		<td><input type="text" id = "addcomPers0" name="addcomPers0"  maxlength ="40" class="form-control" placeholder="社区组负责人"></td>
							        	</tr>
							        </table>
								</div>
							</div>
							
							<div class="form-group">
							    <label class="col-sm-2 control-label"><font color="#FF0000">*</font>学术组负责人：</label>
							    <div class="col-sm-10">
							        <input type="text" name="xueshu_director" id="xueshu_director" class="form-control" placeholder="如果多个以;隔开">
							        <span style="color:red;" id = "addXueshuErr"></span>
								</div>
							</div>
							
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