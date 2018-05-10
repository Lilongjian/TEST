<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<style type="text/css">
.main_div {
	margin: 15px;
}
</style>
<title>- 分类添加</title>
</head>
<body>
	<div class="main_div">
		<form id="form_add" class="layui-form layui-form-pane" action=""
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${user.id }"/>
			<div class="layui-form-item">
				<label class="layui-form-label">商品分类</label>
				<div class="layui-input-inline">
					<select name="parentId" id="topCategory"
						lay-filter="topCategoryFilter">
						<option value="">请选择一级分类</option>
					</select>
				</div>
				<div class="layui-input-inline">
			      <select name="categoryId" id="secondCategory">
			        <option value="">请选择二级分类</option>
			      </select>
			    </div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">状态</label>
					<div class="layui-input-block">
						<input type="text" name="status" autocomplete="off" value="${category.status}"
							placeholder="请输入分类的状态" class="layui-input">
					</div>
				</div>
			<div class="layui-form-item">
				<button type="button" class="layui-btn" onclick="submitForm()">修改</button>
			</div>
		</form>
	</div>
	<script>
		//Demo
		layui.use('form', function() {
			var form = layui.form;
			form.render('select');//刷新select选择框渲染，不然不显示
		});
		$(function() {
			//加载一级分类
			$.ajax({
				url : '${ctx}/category/selectTopCategory.action',
				type : "POST",
				dataType : "json",
				success : function(jsonObj) {
					console.log(jsonObj);
					if (jsonObj.code == util.SUCCESS) {
						var html = '<option value="">请选一级分类</option>';
						var data = jsonObj.data;
						for (var i = 0; i < data.length; i++) {
							console.log(${category.parentId});
							if("${category.parentId}" == data[i].id){
								/* if("${category.parentId}" == 0){
								   html +='<option selected value="">当前是一级分类</option>'; 
								  break;
								}else{ */
								html +='<option selected value="'+data[i].id+'">'
								+data[i].name + '</option>';
								/* } */
							}
							  else if("${category.parentId}" == 0){ 
								
								 /* html +='<option value="'+data[i].id+'">'
								+data[i].name + '</option>'; */
							 	  html +='<option selected value="">当前是一级分类</option>';  
								  break;
							} 
							else{
								html +='<option value="'+data[i].id+'">'
								+data[i].name + '</option>';
							}
							
						}
						$('#topCategory').html(html);
					} else {
					}
				}
			});
		});
		function submitForm() {
			$.ajax({
				url : '${ctx}/category/add.action',
				data : $('#form_add').serialize(),
				type : 'POST',
				dataType : 'json',
				success : function(jsonObj) {
					if (jsonObj.code == util.SUCCESS) {
						/* mylayer.success(jsonObj.msg); */
						mylayer.confirm("添加成功，是否跳转到分类列表界面？",
								"${ctx}/category/getCategoryPage.action");
					} else {
						mylayer.errorMsg(jsonObj.msg);
					}
				}
			});
		}
	</script>
</body>
</html>