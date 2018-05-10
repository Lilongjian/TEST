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
	<title>支付方式-云购物商城</title>
	<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/front/my_add_img/img/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/front/my_add_css/css/base.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/front/my_add_css/css/home.css">
	<!-- <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/modernizr-custom-v2.7.1.min.js"></script> -->
	<style type="text/css">
	/* table{
	width:1500px;
	height:600px;
	} */
	</style>
</head>
<body>


<section id="pc-jie">
	<div class="pc-shopping-cart center">
		<div class="pc-shopping-tab">
			<table>
				<thead>
					<tr class="tab-0">
						<!-- <th class="tab-1"><input type="checkbox" name="s_all" class="s_all tr_checkmr" id="s_all_h"><label for=""> 全选</label></th> -->
						<th class="tab-2">用户ID</th>
						<th class="tab-3">订单号</th>
						<th class="tab-4">商品名称</th>
						<th class="tab-5">商品主图</th>
						<th class="tab-6">商品单价</th>
						<th class="tab-6">数量</th>
						<!-- <th class="tab-7"></th> -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7" style="padding-left:10px; background:#eee">
							<input type="checkbox" checked >
							<label for="">靓淘自营</label>
							<a href="#" style="position:relative;padding-left:50px"><i class="icon-kefu"></i>联系客服</a>
							<ul class="clearfix fr" style="padding-right:20px">
								<li><i class="pc-shop-car-yun"></i>满109元减10</li>
								<li><i class="pc-shop-car-yun"></i>领取3种优惠券, 最高省30元</li>
							</ul>
						</td>
					</tr>
					<c:forEach items="${orderItems}" var="orderItems">
					<input type="hidden" name="check"  value="${orderItems.totalPrice}"/>
					<tr>
					<th>${orderItems.userId}</th>
					<th>${orderItems.orderNo}</th>
					<th>${orderItems.productName}</th>
					<th><img src="/pic/${orderItems.productImage}" width="50px" height="50px"></th>
					<th>${orderItems.currentUnitPrice}</th>
					<th>${orderItems.quantity}</th>
					<th>
					<fmt:formatDate value="${orders.createTime}" pattern="yyyy-MM-dd" ></fmt:formatDate>
					</th>
					</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<div style="height:10px"></div>
	<div class="center">
		<div class="clearfix pc-shop-go">
			<div class="fl pc-shop-fl">
				<input type="checkbox" placeholder="">
				<label for="">全选</label>
				<a href="#">删除</a>
				<a href="#">清楚失效商品</a>
			</div>
			<div class="fr pc-shop-fr">
				<p>共有 <em class="red pc-shop-shu"><kk id="count"></kk></em> 款商品，总计（不含运费）</p>
				<span id="totalPrice"></span>
				<!-- <a href="javaScript:void(0);" onclick = "close()" id="close">去付款</a> -->
			</div>
		</div>
	</div>
</section>



<div style="height:100px"></div>

<script type="text/javascript">
layui.use(['layer'], function(){
	  var layer = layui.layer;
	});
    //hover 触发两个事件，鼠标移上去和移走
    //mousehover 只触发移上去事件
    $(".top-nav ul li").hover(function(){
        $(this).addClass("hover").siblings().removeClass("hover");
        $(this).find("li .nav a").addClass("hover");
        $(this).find(".con").show();
    },function(){
        //$(this).css("background-color","#f5f5f5");
        $(this).find(".con").hide();
        //$(this).find(".nav a").removeClass("hover");
        $(this).removeClass("hover");
        $(this).find(".nav a").removeClass("hover");
    });
    $(function(){
    	refreshTotalPrice();
    })
    function refreshTotalPrice(){
    	var check = $("input[name=check]");
    	$("#count").html(check.length);
    	var totalPrice=0.00;
    	for(var i=0;i<check.length;i++){
    		var price = check[i].getAttribute('value');
    		totalPrice+=parseFloat(price);
    	}
    	$('#totalPrice').html(totalPrice);
    }
</script>
<script type="text/html" id="imgTpl">
    <img src="/pic/{{d.mainImage}}">
 </script>
</body>
</html>