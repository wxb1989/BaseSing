<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="${path}/hplus/js/jquery-2.1.1.min.js"></script>
<script src="${path}/hplus/js/bootstrap.min.js?v=3.4.0"></script>
<script src="${path}/hplus/js/content.min.js?v=1.0.0"></script>
<script src="${path}/hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${path}/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${path}/hplus/js/plugins/layer/layer.min.js"></script>
<script src="${path}/hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${path}/hplus/js/hplus.min.js?v=3.0.0"></script>
<script src="${path}/hplus/js/contabs.min.js"></script>
<script src="${path}/hplus/js/plugins/pace/pace.min.js"></script>
<!-- iCheck -->
<script src="${path}/hplus/js/plugins/iCheck/icheck.min.js"></script>

<script type="text/javascript" src="${path}/js/base/minFunc.js"></script>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        $('input#selectAll').on('ifChecked ifChecked ifUnchecked', function(event){
        	if (event.type == 'ifChecked') {
        		$(".selectOne").iCheck('check');
       	    }
        	else {
        		$(".selectOne").iCheck('uncheck');
       	    }
       	});
        
        $("#addForm, #updateForm").submit(function() {
			$("#submitBtn").attr("disabled", true).css("color", "#FFFFFF").text("加载中...");
		});
    });
</script>