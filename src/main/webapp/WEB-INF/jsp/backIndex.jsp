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
	<title>首页</title>
	<!--[if lt IE 8]>
    <script>
        alert('系统已不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->
    <script type="text/javascript">
    	$(function() {
    		//菜单显示权限控制
    		$(".priItem, .priTotal").css("display", "none")
    		var privilegesArray = $("#privileges").val().split(",");
    		$(".priItem").each(function() {
    			var $this = $(this);
    			var privilege = $this.attr("privilege");
    			$.each(privilegesArray, function(i, n) {
    				if (n == privilege) {
    					$this.css("display", "block");
    				}
    			});
    		});
    		$(".priTotal").each(function() {
    			var $totalThis = $(this);
    			$(this).find(".nav-second-level li").each(function() {
    				if ($(this).css("display") == "block") {
    					$totalThis.css("display", "block");
    					return false;
        			}
    				
    			});
    		});
    		
    	});
    </script>
</head>
<body class="fixed-sidebar full-height-layout gray-bg">

	<input type="hidden" id="privileges" value="${privileges}" />

	<div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                        	<span><img alt="image" class="img-circle" src="${path}/images/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${sessionScope.loginUsername}</strong></span>
                                <span class="text-muted text-xs block">管理员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="${path}/sysUser/exit">安全退出</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">TG</div>
                    </li>
                    
                    <li>
                        <a class="J_menuItem" href="${path}/back/control/index">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>

                    </li>
                    
                    <li class="priTotal">
                        <a href="#">
                        	<i class="fa fa-cutlery"></i>
                        	<span class="nav-label">系统管理</span>
                        	 <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li class="priItem" privilege="sysUserManage_list"><a class="J_menuItem" href="${path}/back/control/sysUserManage/list">用户管理</a></li>
                            <li class="priItem" privilege="privilegegroup_list"><a class="J_menuItem" href="${path}/back/control/privilegegroup/list">权限组管理</a></li>
                            <li class="priItem" privilege="updatePassword_list"><a class="J_menuItem" href="${path}/back/control/sysUserManage/updatePasswordUI">个人密码修改</a></li>
                        </ul>
                    </li>
                    
                    <li class="priTotal">
                        <a href="#">
                        	<i class="fa fa-cutlery"></i>
                        	<span class="nav-label">数据维护</span>
                        	 <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li class="priItem" privilege="periodical_list"><a class="J_menuItem" href="${path}/back/control/periodical/list">队伍管理</a></li>
                            <li class="priItem" privilege="periodical_list"><a class="J_menuItem" href="${path}/back/control/information/list">裁判风采</a></li>
                            <li class="priItem" privilege="periodical_list"><a class="J_menuItem" href="${path}/back/control/periodical/list">用户管理</a></li>
                           
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        
        <!--右侧部分开始-->
        
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                    	<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
                    		<i class="fa fa-bars"></i> 
                    	</a>
                    </div>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${path}/back/control/index">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <button class="roll-nav roll-right dropdown J_tabClose"><span class="dropdown-toggle" data-toggle="dropdown">关闭操作<span class="caret"></span></span>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </button>
                <a href="${path}/sysUser/exit" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${path}/back/control/index" frameborder="0" data-id="${path}/back/control/index" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2015-2016 <a href="#">常笑投稿平台</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->

    </div>
	
</body>
</html>