/**
 * author XuXu
 */
//倒计时
function countDown() {
	$('.time').each(function(index) {
		var $time = $(this);
		var $days = $(this).find('.days');
		var $hours = $(this).find('.hours');
		var $minutes = $(this).find('.minutes');
		var $seconds = $(this).find('.seconds');
		
		var days = $days.html();
		var hours = $hours.html();
		var minutes = $minutes.html();
		var seconds = $seconds.html();
		
		if (days == null) days = 0;
		if (hours == null) hours = 0;
		if (minutes == null) minutes = 0;
		if (seconds == null) seconds = 0;
		
		days = parseInt(days);
		hours = parseInt(hours);
		minutes = parseInt(minutes);
		seconds = parseInt(seconds);
		
		var totalSeconds = days*24*3600 + hours*3600 + minutes*60 + seconds;
		
		var i = i + index;
		i = setInterval(cd, 1000);
		
		function cd() {
			if (totalSeconds > 0) { 
				totalSeconds = totalSeconds - 1; 
				var seconds = Math.floor(totalSeconds % 60);
			    var minutes = Math.floor((totalSeconds / 60) % 60);
			    var hours = Math.floor((totalSeconds / 3600) % 24);
			    var days = Math.floor((totalSeconds / 3600) / 24);
			    $days.html(days);
			    $hours.html(hours);
			    $minutes.html(minutes);
			    $seconds.html(seconds);
			    if (totalSeconds < 86400)
			    	$hours.html(hours);
			    	$minutes.html(minutes);
			    	$seconds.html(seconds);
			    if (totalSeconds < 3600)
			    	$minutes.html(minutes);
			    	$seconds.html(seconds);
			    if (totalSeconds < 60)
			    	$seconds.html(seconds);
		    }
		    else {
		    	clearInterval(i);
		    	$time.html("已过期");
		    }
		}	
	});
}

//文件上传处理
function fileUrlChecked(fileName) {
	if (fileName.indexOf("\\") <= 0) {
		return fileName;
	}
	else {
		var start = fileName.lastIndexOf("\\");
		var newFileName = fileName.substring(start+1);
		return newFileName;
	}
}

//图片等比例缩放，垂直居中（图片class、图片最大宽高、图片外层元素class）
function picFix(imgClass, maxLen, imgWrapperClass) {
	var $imgWrapper = $('.' + imgWrapperClass);
	var imgWrapperHeight = null;
	if ($imgWrapper != "" || $imgWrapper != null)
		imgWrapperHeight = $imgWrapper.height();
	$("." + imgClass).each(function() {
		var width = $(this).width();
		var height = $(this).height();
		if (width > maxLen || height > maxLen) {
			if (width > height || width == height) {
				$(this).attr("width", maxLen);
			}
			else {
				$(this).attr("height", maxLen);
			}
		}
		if (imgWrapperHeight != null)
			$(this).css("margin-top", (imgWrapperHeight-$(this).height())/2);
	});
	$("." + imgClass).css("visibility" ,"visible");
}

//（optionClass: 点击对象公共的class，各自唯一class为optionClass1, optionClass2 .....
//	对应的内容公共class为对象class为optionClass_con，各自唯一class为optionClass_con1, optionClass_con2 .....
// mouse 为鼠标动作的行为，click/hover 二选一 
// btnAddClass 为按钮当前显示的样式， 不需要可以为null）
function tabChange(optionClass, mouse, btnAddClass) {
	var $action = null;
	$('.' + optionClass).each(function(index) {
		var run = function() {
			if (btnAddClass != null) {
				$('.' + optionClass).removeClass(btnAddClass);
				$('.' + optionClass + (index+1)).addClass(btnAddClass);
			}
			$('.' + optionClass + "_con").hide();
			$('.' + optionClass + "_con" + (index+1)).show();
		};
		if (mouse == "click") {
			$('.' + optionClass + (index+1)).click(function() {
				run();
			});	
		}
		if (mouse == "hover") {
			$('.' + optionClass + (index+1))
				.mouseover(function() {
					$action = setTimeout(run, 200);
				})
				.mouseout(function() {
					clearTimeout($action);
				});;
		}
	});
}

//控制字数
function cutWords(objClass, counts, addStr) {
	var $obj = $('.' + objClass);
	var objLen = $obj.length;
	$obj.each(function() {
		var $this = $(this);
		var thisText = $this.text();
		if (thisText.length > counts) {
			var newText = thisText.substring(0, counts) + addStr;
			$this.html(newText);
		}
	});
}

//关闭弹出层
function closePop(popDivClass,maskId){
  	$('.' + popDivClass).hide();
	$('#' + maskId).hide();
}

//div居中（div需要absolute）
//要有更好的体验，可以在调用此方法的页面中加上$(window).resize(function() {divCenter});
function divCenter(obj) {
	var objW = $(window);
	var objC = $('#' + obj);
	var brsW = objW.width();
	var brsH = objW.height();
	var sclL = objW.scrollLeft();
	var sclT = objW.scrollTop();
	var curW = objC.width();
	var curH = objC.height();
	
	var left = sclL + (brsW - curW)/2;
	var top = sclT + (brsH - curH)/2;
	objC.css({"left": left, "top": top});
}

//弹出层
//divId - 弹出div的id
//opacity - 遮罩div的id
//closeBtn - 关闭弹出层按钮id
function popDiv(divId, opacityId, closeBtn) {
	var opacityObj = $('#' + opacityId);
	var divIdObj = $('#' + divId);
	var closeBtnObj = $('#' + closeBtn);
	
	opacityObj.height($(window).scrollTop() + opacityObj.height());
	opacityObj.show();
	
	var objW = $(window);
	var brsW = objW.width();
	var brsH = objW.height();
	var sclL = objW.scrollLeft();
	var sclT = objW.scrollTop();
	var curW = divIdObj.width();
	var curH = divIdObj.height();
	var left = sclL + (brsW - curW)/2;
	var top = sclT + (brsH - curH)/2;
	divIdObj.css({"left": left, "top": top});
	divIdObj.show();
	
	closeBtnObj.bind("click", function() {
		opacityObj.hide();
		divIdObj.fadeOut();
	});
	$('html').animate({scrollTop : 100},'slow');
}

//返回手机号码的网络运营商
//result（"illegal[非法], yidong[移动], liantong[联通], dianxin[电信]"）
function mobileNet(mobileNum) {
	var result = "";
	if(!/^\d{11}$/.test(mobileNum)) {
	  result = "illegal";
	}
	else {
		var yidong = /^1(3[4-9]|47|5[0-27-9]|8[2-478])/;
		var liantong = /^1(3[0-2]|5[56]|45|8[56])/;
		var dianxin = /^1([35]3|8[019])/;
		if(yidong.test(mobileNum)) {
			result = "yidong";
		}
		else if(liantong.test(mobileNum)) {
			result = "liantong";
		}
		else if(dianxin.test(mobileNum)) {
			result = "dianxin";
		}
	}
	return result;
}

//仿html5 placeholder 效果
//id - 具体input的id
function placeholder(id) {
	var obj = $('#' + id);
	var oldValue = obj.val();
	obj.focus(function() {
		if ($(this).val() == oldValue) {
			$(this).val("");
		}
	}).blur(function() {
		if ($(this).val() == oldValue || $(this).val() == "" || $(this).val() == null) {
			$(this).val(oldValue);
		}
	});
}

/**
 * 分页
 * list : 分页数据循环对象class
 * size : 每页显示条数
 * wrapper : 存放分页控件的容器id
**/
function pageView(list, size, wrapper) {
	var $recordList = $("." + list);	//分页对象
	var $wrapper = $("#" + wrapper);	//分页容器
	var totalRecordSize = $recordList.size();	//总记录数
	var nowPage = 1;	//当前页
	var totalPage = parseInt(totalRecordSize/size, 10);	//总页数
	if (totalRecordSize % size > 0)
		totalPage++;
	
	showList(1);
	
	$wrapper.html("<span style='padding-left: 30px;'>共 <font style='color: #FF0000;'>" + totalRecordSize + "</font> 条记录</span>" + 
					"<span style='padding-left: 30px;'>当前第 <font id='nowPage' style='color: #FF0000;'>" + nowPage + "</font> 页/共 <font style='color: #FF0000;'>" + totalPage + "</font> 页" + 
					"<span style='padding-left: 30px;'>每页 <font style='color: #FF0000;'>" + size + "</font> 条记录</span>" + 
					"<span id='first' style='padding-left: 30px;'><a href='javascript:void(0)'>首页</a></span>" + 
					"<span id='prev' style='padding-left: 30px;'><a href='javascript:void(0)'>上一页</a></span>" + 
					"<span id='next' style='padding-left: 30px;'><a href='javascript:void(0)'>下一页</a></span>" + 
					"<span id='end' style='padding-left: 30px;'><a href='javascript:void(0)'>末页</a></span>"
					);
	
	var $nowPage = $("#nowPage");
				
	$("#first").bind("click", function() {
		if (nowPage != 1) {
			showList(1);
			nowPage = 1;
			$nowPage.text(nowPage);
		}
	});
	$("#end").bind("click", function() {
		if (nowPage != totalPage) {
			showList(totalPage);
			nowPage = totalPage;
			$nowPage.text(nowPage);
		}
	});
	$("#next").bind("click", function() {
		if (nowPage < totalPage) {
			showList(++nowPage);
			$nowPage.text(nowPage);
		}
	});
	$("#prev").bind("click", function() {
		if (nowPage > 1) {
			showList(--nowPage);
			$nowPage.text(nowPage);
		}
	});
	
	function showList(page) {
		var start = (page-1) * size + 1;
		var end = page * size;
		$recordList.each(function(index) {
			index++;
			if (index > end || index < start) {
				$(this).hide();
			}
			else {
				$(this).show();
			}
		});
	}
	
}

/**
 * 刷新验证码
 */
function refreshSafeCode(imgId) {
	var imgObj = $("#" + imgId);
	var src = imgObj.prop("src");
    if (src.indexOf('?') != -1) {
        src = src.substring(0, src.length);
    }
    src = src + "?timestamp=" + new Date().valueOf();
    imgObj.prop("src", src);
}

/**
 * 数组去重
 */
Array.prototype.unique = function() {
	var re=[this[0]];
	for(var i = 1; i < this.length; i++)
	{
		if( this[i] !== re[re.length-1])
		{
			re.push(this[i]);
		}
	}
	return re;
}