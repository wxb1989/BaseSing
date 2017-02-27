<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>

<input type="hidden" id="currentPage" value="${pageView.currentpage}" />
<input name="page" id="page" type="hidden" value="${pageView.currentpage}" />

共有 ${pageView.totalrecord} 条记录，当前第 ${pageView.currentpage}/${pageView.totalpage} 页，每页 ${pageView.maxresult} 条记录

&nbsp;&nbsp;&nbsp;&nbsp;

<c:choose>
	<c:when test="${pageView.currentpage == 1}">
		<button type="button" class="btn btn-outline btn-default btn-sm" disabled="disabled">首页</button>
	</c:when>
	<c:otherwise>
		<button type="button" class="btn btn-outline btn-default btn-sm" onclick="javascript:gotoPage(1)">首页</button>
	</c:otherwise>
</c:choose>
&nbsp;
<c:choose>
	<c:when test="${pageView.currentpage == 1}">
		<button type="button" class="btn btn-outline btn-default btn-sm" disabled="disabled">上一页</button>
	</c:when>
	<c:otherwise>
		<button type="button" class="btn btn-outline btn-default btn-sm" onclick="javascript:gotoPage(${pageView.currentpage - 1})">上一页</button>
	</c:otherwise>
</c:choose>
&nbsp;
<c:choose>
	<c:when test="${pageView.currentpage < pageView.totalpage}">
		<button type="button" class="btn btn-outline btn-default btn-sm" onclick="javascript:gotoPage(${pageView.currentpage + 1})">下一页</button>
	</c:when>
	<c:otherwise>
		<button type="button" class="btn btn-outline btn-default btn-sm" disabled="disabled">下一页</button>
	</c:otherwise>
</c:choose>
&nbsp;
<c:choose>
	<c:when test="${pageView.currentpage < pageView.totalpage}">
		<button type="button" class="btn btn-outline btn-default btn-sm" onclick="javascript:gotoPage(${pageView.totalpage})">尾页</button>
	</c:when>
	<c:otherwise>
		<button type="button" class="btn btn-outline btn-default btn-sm" disabled="disabled">尾页</button>
	</c:otherwise>
</c:choose>
<input type="text" class="form-control" name="jump_page" id="jump_page" />
<button class="btn btn-outline btn-default btn-sm" onclick="javascript:gotoPageByjump()" type="button">跳转</button>
<script type="text/javascript">
	function gotoPage(n) {
		if (n != null) {
			document.getElementById('page').value = n;
		}
		else {
			var currentPage = document.getElementById('currentPage').value;
			var p = document.getElementById('page');
			if (p.value == null || p.value == 0 || p.value == "") {
				p.value = currentPage;
			}
		}
		document.forms[0].submit();
	}
	
	function gotoPageByjump(){
		
			if('' == document.getElementById('jump_page').value){
				alert("请输入要跳转页！！");
				return false;
			}else if(!IsNum(document.getElementById('jump_page').value)){
				alert("页码数只能为正整数！！");
				return false;
			}else{
				document.getElementById('page').value = document.getElementById('jump_page').value;
				document.forms[0].submit();
			}
		
		
	}
	
	function IsNum(s)
	{
	    if(s!=null){
	        var r,re;
	        re = /\d*/i; //\d表示数字,*表示匹配多个数字
	        r = s.match(re);
	        return (r==s)?true:false;
	    }
	    return false;
	}
</script>
