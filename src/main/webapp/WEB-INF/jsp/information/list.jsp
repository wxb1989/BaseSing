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
	<title>列表</title>
	
	<script type="text/javascript">
    
    	$(function() {
    		$(".add").click(function() {
				var action = "${path}/project/addUI";
				$("form").prop("action", action).submit();
			});
			
			$(".update").click(function() {
				var action = "${path}/back/control/information/updateUI/" + $(this).attr("entryId") + "?uploadStatus=old";
				$("form").prop("action", action).submit();
			});
    		
    		$("#deleteBtn").click(function() {
    			var ids = "";
            	$("input.selectOne").each(function(){
            		if(true == $(this).is(':checked')) {
                        ids += $(this).val() + ",";
                    }
                });
            	swal({
    		        title: "确定删除吗？",
    		        type: "warning",
    		        showCancelButton: true,
    		        confirmButtonColor: "#DD6B55",
    		        confirmButtonText: "删除",
    		        closeOnConfirm: false
    		    }, function () {
    		    	var action = "${path}/back/control/information/delete/" + ids;
    		    	if (ids == "") {
    		    		 swal({title:"提示", text:"至少选择一条数据！"});
    		    	}
    		    	else {
    		    		$("form").prop("action", action).submit();
    		    	}
    		    });
            	
            });

    	}); 
    		
    </script>
</head>
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
	            <div class="col-sm-12">
	            
	            	
		                <div class="ibox float-e-margins">
		               		 <form action="${path}/back/control/information/list" method="post" role="form" class="form-inline">
		                    <div class="ibox-title">
		                    	<div class="row">
								  <div class="col-sm-1">
								  	<button type="button" class="btn btn-outline btn-danger btn-sm" id="deleteBtn">删除</button>
								  </div>
								  <div class="col-sm-10">
								  	<div class="row">
								  		<div class="col-md-1"></div>
								  		<div class="col-md-1"></div>
								  	</div>
								  	<div class="form-group">
	                                    <label>项目名称：</label>
	                                    <input type="text" class="form-control" name="pName" value="${formData.pName}" />
	                                </div>&nbsp;&nbsp;
	                                <button class="btn btn-outline btn-primary btn-sm" type="submit">查询</button>
								  </div>
								  <div class="col-sm-1 text-right"></div>
								</div>
		                    </div>
		                    
		                    <div class="ibox-content">
		                        <table class="table table-striped table-bordered table-hover table-fontsize">
		                            <thead>
		                                <tr>
		                                    <th style="width: 10%;"><input type="checkbox" class="i-checks" id="selectAll" />&nbsp;&nbsp;序号</th>
		                                    <th style="width: 15%;">项目名称</th>
		                                    <th style="width: 15%;">学术负责人</th>
		                                    <th style="width: 10%;">编辑</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            	<c:if test="${empty pageView.records}">
											<tr>
												<td colspan="8" style="color: #333; text-align: center;">暂时还没有任何记录！</td>
											</tr>
										</c:if>
		                                <c:set value="${(pageView.currentpage-1) * pageView.maxresult}" var="page"></c:set>
										<c:forEach items="${pageView.records}" var="entry">
											<c:set value="${page+1}" var="page"></c:set>
											<tr class="gradeX">
												<td>
													<input type="checkbox" class="i-checks selectOne" value="${entry.id}" />
													${page}
												</td>
												<td>${entry.pName}</td>
												
												
												<td>${entry.xueshu_director}</td>
												<td>
													<a href="javascript:void(0)" entryId="${entry.id}" class="update alink">修改</a>
												</td>
											</tr>
										</c:forEach>
		                            </tbody>
		                        </table>
		                        
		                        <div class="row">
			                    	<div class="col-sm-4">
			                    		<button type="button" class="btn btn-outline btn-primary btn-sm add">添加</button></td>
			                    		
			                    	</div>
			                    	<div class="col-sm-8 text-right">
			                    		<%@ include file="/WEB-INF/jsp/commons/paging.jsp" %>
			                    	</div>
			                    </div>
			                    
		                    </div>
  						 </form>
  						  
	          	  
	             	   	
	               
  						 
		                </div>
	              
	            </div>
	        </div>
	    </div>
	</body>
</html>