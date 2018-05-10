<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I分类统计</title>
</head>
<body>
    <div id="main" style="width: 1300px ;height:400px;"></div>
    <script type="text/javascript" src="${ctx}/static/lib/echarts/echarts.js" ></script>
    <script type="text/javascript">
    $(function(){
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
    	url:'${ctx}/category/getCategoryCountAnalysis.action',
    	type:'POST',
    	dataType:'json',
    	success:function(jsonObj){
    		if(jsonObj.code==util.SUCCESS){
    			var data = jsonObj.data;
    			var xArrayData = new Array();
    			var yArrayData = new Array();
    			for(var i= 0;i<data.length;i++){
    				xArrayData.push(data[i].name);
    				yArrayData.push(data[i].count);
    			}
    			var option = {
    		            title: {
    		                text: '分类数量统计'
    		            },
    		            tooltip: {},
    		            legend: {
    		                data:['销量']
    		            },
    		            xAxis: {
    		                data: xArrayData
    		            },
    		            yAxis: {},
    		            series: [{
    		                name: '销量',
    		                type: 'bar',
    		                data: yArrayData
    		            }]
    		        };

    		        // 使用刚指定的配置项和数据显示图表。
    		        myChart.setOption(option);
    			
    		}
    	}
    })
    })
    </script>

</body>
</html>