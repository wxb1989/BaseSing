<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<a href="${path}/user/loginUI">登入</a>
	
	<form id="detailForm" method="post">
	    <textarea id="content" name="content"></textarea>
	    <input type="button" value="保存" id="save" onclick="save()" />
	</form>

	<script type="text/javascript" src="${path}/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${path}/ckfinder/ckfinder.js"></script>
	
	<script type="text/javascript">
	    var editor = null;
	    window.onload = function(){
	        editor = CKEDITOR.replace('content', {
	            customConfig:'${path}/ckeditor/config.js',
	            
	            on: {
	                instanceReady: function(ev) {
	                    this.dataProcessor.writer.setRules( 'p', {
	                        indent: false,
	                        breakBeforeOpen: false,   //<p>之前不加换行
	                        breakAfterOpen: false,    //<p>之后不加换行
	                        breakBeforeClose: false,  //</p>之前不加换行
	                        breakAfterClose: false    //</p>之后不加换行7
	                    });
	                }
	            }
	        }); //参数‘content’是textarea元素的name属性值，而非id属性值
	        
	        CKFinder.setupCKEditor(editor, '${path}/ckfinder/');
	    }
	    
	    function save(){
	        editor.updateElement(); //非常重要的一句代码
	        //前台验证工作
	        //提交到后台
	    }
	    
	</script>
	
</body>
</html> --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
