<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>靓淘网</title>
		<link rel="stylesheet" href="${ctx}/static/front/css/index_style.css" />
		<link rel="stylesheet" href="${ctx}/static/front/css/productList.css" />
	</head>

	<body>
		<!-----------------------1.top-------------------->
		<div class="bg_color">
			<div class="top_center">
				<div class="left">
					<span class="wel">
						欢迎来到靓淘！
					</span>
				</div>
				<div class="right">
					<ul>
						<li><a class="login" href="login.shtml" target="_blank">
						<c:if test="${CURRENT_USER.username==null}">请登录</c:if>
                        <c:if test="${CURRENT_USER.username!=null}">${CURRENT_USER.username}</c:if>
						</a></li>
						<li><a href="register.html" target="_blank">快速注册</a></li>
						<li><a class="collect" href="">我的收藏</a></li>
						<li><a class="indent" href="">我的订单</a></li>
						<li><a class="phone" href="">手机靓淘</a></li>
						<li><a href="">我的积分</a></li>
						<li><a href="">我的评价</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-----------------------2.logo-------------------->
		<div class="logo_center">
			<div class="left">
				<a href="${ctx}/index.shtml"><img class="logo_img" src="${ctx}/static/front/img/LOGO.png" /></a>
			</div>
			<div class="center">
				<input class="btn1" type="text" value="安慕希" />
				<input class="btn2" type="button" value="搜索" />
				<ul class="nav">
					<li><a href="" style="color: pink;">保湿 | </a></li>
					<li><a href="">面膜 | </a></li>
					<li><a href="">洗面奶 | </a></li>
					<li><a href="">补水 | </a></li>
					<li><a href="">香水 | </a></li>
					<li><a href="">眼霜 | </a></li>
					<li><a href="">口红 | </a></li>
					<li><a href="">护肤套装 | </a></li>
					<li><a href="">BB霜 | </a></li>
				</ul>
			</div>
			<div class="right">
				<div class="car">
					<a class="car_pic" href="${ctx}/cart/getCartPage.shtml" target="_blank">去购物车结算</a>
				</div>
				<div class="narrow"></div>
			</div>
		</div>
			<div class="photo">
		<ul>
		    <c:forEach items="${products}" var="products">
			<li>
			<a href="${ctx}/product/getProductDetail.shtml?productId=${products.id}">
			<img src="/pic/${products.mainImage}" class="productimg"/>
			</a>
			<%-- <c:set  var="namesubstring" value="${fn:substring(products.name,0,8)}"></c:set><p>${namesubstring}...</p> --%>
			 <c:if test="${fn:length(products.name)>15}">
			<p>${fn:substring(products.name, 0, 15)}...</p>
			</c:if>
			<c:if test="${fn:length(products.name)<=8}">
			<p>${products.name}</p>
			<p style="color:blue;">￥${products.price}</p>
			</c:if>	
			<!-- <p>000</p> -->
			</li>
			</c:forEach>
		</ul>
			<div class="div4"></div>
	    </div>
	    
		<div class="sp">
			<div class="sp1">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp2">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp3">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp4">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="more">
			<div class="mb1">
				<ul>
					<li>
						购物指南
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;免费注册
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;开通支付宝
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;支付宝充值
						</a>
					</li>
				</ul>
			</div>
			<div class="mb2">
				<ul>
					<li>
						品质保障
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;发票保障
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;售后规则
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;缺货赔付
						</a>
					</li>
				</ul>
			</div>
			<div class="mb3">
				<ul>
					<li>
						支付方式
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;快捷支付
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;信用卡
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;货到付款
						</a>
					</li>
				</ul>
			</div>
			<div class="mb4">
				<ul>
					<li>
						商家服务
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;商家入驻
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;商家中心
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;运营服务
						</a>
					</li>
				</ul>
			</div>
			<div class="mb5">
				<ul>
					<li>
						手机靓淘
					</li>
					<li>
						<img src="${ctx}/static/front/img/98.png"/>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="link">
			<ul>
				<li>
					<a href="">
						关于靓淘
					</a>
				</li>
				<li>
					<a href="">
						帮助中心
					</a>
				</li>
				<li>
					<a href="">
						开放平台
					</a>
				</li>
				<li>
					<a href="">
						诚聘精英
					</a>
				</li>
				<li>
					<a href="">
						联系我们
					</a>
				</li>
				<li>
					<a href="">
						网站合作
					</a>
				</li>
				<li>
					<a href="">
						法律声明及隐私政策
					</a>
				</li>
				<li>
					<a href="">
						知识产权
					</a>
				</li>
				<li>
					<a href="">
						廉政举报
					</a>
				</li>
				<li>
					<a href="">
						规则意见征集
					</a>
				</li>
			</ul>
		</div>
		<div class="copyright">
			COPYRIGHT 2010-2017 北京创锐文化传媒有限公司 JUMEI.COM 保留一切权利. 客服热线：400-123-888888<br /> 
			京公网安备 110101020011226|京ICP证111033号|食品流通许可证 SP1101051110165515（1-1）|营业执照
		</div>
		<script type="text/javascript">
			var m = 0;
			$(".banner_box .banner").hide().eq(0).show();
			function changeImg(){
				if(m<3){m=m+1}else{m=0}
				$(".circle li").removeClass("current").eq(m).addClass("current");
				$(".banner_box .banner").hide().eq(m).show();
			}
			t = setInterval(changeImg,1000);
			$(".banner_box").mouseenter(
				function(){
				clearInterval(t);
				}
			).mouseleave(
				function(){
				t = setInterval(changeImg,1000);
				}
			)
			$(".circle li").click(
				function(){
					m = $(this).index();
					$(".circle li").removeClass("current").eq(m).addClass("current");
					$(".banner_box .banner").hide().eq(m).show();
				}
			)
/*			
			$(".banner_box .banner").hide().eq(0).show();
			$(".circle li").click(
				function(){
					var i = 0;
					i = $(this).index();
					$(".banner_box .banner").hide().eq(i).show();
					
				}
			)
			$(".circle li").click(
				function(){
					var n = 0;
					n = $(this).index();
					$(".circle li").removeClass("current").eq(n).addClass("current");
				}
			)*/
			//Demo
				  
		</script>
	</body>

</html>