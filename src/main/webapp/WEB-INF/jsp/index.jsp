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
	<title>首页内容</title>
</head>
	<body class="gray-bg">
		<div class="row  border-bottom white-bg dashboard-header">
		
			<div class="col-sm-3">
	            <h2>Hello,${sessionScope.loginUsername}</h2>
	            <small>移动设备访问请扫描以下二维码：</small>
	            <br>
	            <br>
	            <img src="${path}/images/qr_code.png" width="100%" style="max-width:264px;">
	            <br>
	        </div>
            
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-primary pull-right">今天</span>
                        <h5>访客</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">106,120</h1>
                        <div class="stat-percent font-bold text-navy">44% <i class="fa fa-level-up"></i>
                        </div>
                        <small>新访客</small>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-danger pull-right">最近一个月</span>
                        <h5>活跃用户</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">80,600</h1>
                        <div class="stat-percent font-bold text-danger">38% <i class="fa fa-level-down"></i>
                        </div>
                        <small>12月</small>
                    </div>
                </div>
            </div>
            
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>更新日志</h5>
                    </div>
                    <div class="ibox-content no-padding">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                                
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#version" href="#v30">v1.0</a><code class="pull-right">2015.11.24更新</code>
                                        </h5>
                                    </div>
                                    <div id="v30" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                                <li>今天是情人节，H+终于跨到了v3.0，就是是情人节礼物吧，感谢你们的不离不弃，一路相伴！ <a href="#" class="viewlog"><i class="fa fa-eye"></i> 查看升级日志</a>
                                                </li>
                                                <li><span class="text-danger">注意：</span>本次更新调整较大，不建议直接覆盖升级！</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
		</div>
	</body>
</html>