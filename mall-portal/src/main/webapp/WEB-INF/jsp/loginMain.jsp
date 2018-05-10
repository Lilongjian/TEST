<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta name="renderer" content="webkit">
	<title>登录.云购物商城</title>
	<!-- <link rel="shortcut icon" type="image/x-icon" href="img/icon/favicon.ico"> -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/base.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/home.css">
</head>
<body>

<header id="pc-header">
	<div class="center">
		<div class="pc-fl-logo">
		<br>
			<p>
				<img src="${ctx}/static/front/img/LOGO.png">
			</p>
			<br>
		</div>
	</div>
</header>
<section>
	<div class="pc-login-bj">
		<div class="center clearfix">
			<div class="fl"></div>
			<div class="fr pc-login-box">
				<div class="pc-login-title"><h2>用户登录</h2></div>
				<form action="" id="login-form">
					<div class="pc-sign">
						<input type="text" name="username" id="username" placeholder="用户名/邮箱/手机号">
					</div>
					<div class="pc-sign">
						<input type="password" name="password" id="password" placeholder="请输入您的密码">
					</div>
					<div class="pc-submit-ss">
						<input type="button" onclick="login()" value="登录" placeholder="">
					</div>
					<div class="pc-item-san clearfix">
						<a href="#"><img src="${ctx}/static/front/img/icon/weixin.png" alt="">微信登录</a>
						<a href="#"><img src="${ctx}/static/front/img/icon/weibo.png" alt="">微博登录</a>
						<a href="#" style="margin-right:0"><img src="${ctx}/static/front/img/icon/tengxun.png" alt="">QQ登录</a>
					</div>
					<div class="pc-reg">
						<a href="#">忘记密码</a>
						<a href="register.html" class="red">免费注册</a>
					</div>
				</form>
			
			</div>
		</div>
	</div>
</section>

<footer>
	<div class="center">
		<div class="pc-footer-login">
			<p>关于我们 招聘信息 联系我们 商家入驻 商家后台 商家社区 ©2017 Yungouwu.com 北京云购物网络有限公司</p>
			<p style="color:#999">营业执照注册号：990106000129004 | 网络文化经营许可证：北网文（2016）0349-219号 | 增值电信业务经营许可证：京2-20110349 | 安全责任书 | 京公网安备 99010602002329号 </p>
		</div>
	</div>
</footer>
</body>
 <script type="text/javascript">
      layui.use(['layer'],function(){
      	var layer = layui.layer;
      });
        function login(){
    	  var username=$("#username").val();
    	    var password=$("#password").val();
    	    if(util.isNull(username)){
    	    	mylayer.errorMsg("用户名不能为空");
    	    	return;
    	    }
    	  //1.2、是否合法：4-8数字或字母
    		if(!isUsernameValid(username)) {
    			mylayer.errorMsg("用户名不合法，4-8数字或字母");
    			return;
    		}
    		
    		//2、密码不能为空
    		if(util.isNull(password)) {
    			mylayer.errorMsg("密码不能为空");
    			return;
    		}
    		//3.ajax提交用户名和密码，并且接受后台的返回的json的数值
    	    $.ajax({
    	    	url:"${ctx}/user/login.shtml",
    	    	type:"POST",
    	    	dataType:"json",
    	    	 /* async:false, */
    	    	data:$("#login-form").serialize(),
    	    	success:function(data){
    	    			if(data.code == util.SUCCESS){
    	    				/* ${ctx}/order/getOrderPage.shtml","_top */
    	    			 /*  window.open("${ctx}/index.shtml"); */ /* ,"_blank" */
    	    			  window.location.href="${ctx}/index.shtml";
    	    			 /*  window.location.href="${ctx}/order/getOrderPage.shtml"; */
    	    		}else{
    	    			mylayer.errorMsg(data.msg);
    	    		}
    	    	}
    	    });
      }
      /*是否合法：4-8数字或字母*/
        	function isUsernameValid(value) {
        		var pattern = /^[0-9a-zA-Z]{4,8}$/;
        		return pattern.test(value);
          	} 
      </script>
</html>