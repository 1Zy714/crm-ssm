<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"
+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">

	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
	<script type="text/javascript">

	$(function(){

		/*
    	*	设置time类属性 年月日
    	* */
		$(".time").datetimepicker({
			minView: "month",//可选择最小视图
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,//选择完日期自动关闭
			todayBtn: true,
			clearBtn:true,
			pickerPosition: "bottom-left"
		});

		//页面加载完毕刷新页面
		pageList(1,5);
		<%--给模态窗口用户下拉列表赋值--%>
		var html="";
		<c:forEach items="${users}" var="u">
		html += "<option value='${u.id}'>${u.name}</option>"
		</c:forEach>
		$("#create-marketActivityOwner").html(html);
		$("#edit-marketActivityOwner").html(html);
		$("#createBtn").click(function(){
			//清空模态窗口数据
			$("#create-form")[0].reset();
			//清空提示信息
			$(".msg").text("");
			//显示模态窗口
			$("#createActivityModal").modal("show");
		})
		$("#saveBtn").click(function(){
			$(".msg").text("");
			var owner=$("#create-marketActivityOwner").val();
			var name = $.trim($("#create-marketActivityName").val());
			var startDate=$("#create-startDate").val();
			var endDate = $("#create-endDate").val();
			var cost = $.trim($("#create-cost").val());
			var description = $.trim($("#create-description").val());
			//判断数据是否符合要求
			if(owner == ""){
				$("#owner-msg").text("所有者不可为空");
				return;
			}if(name == ""){
				$("#name-msg").text("名称不可为空");
				return;
			}if(startDate!==""&&endDate!=""){
				if(startDate > endDate){
					$("#date-msg").text("结束日期应该在开始日期之后");
					return;
				}
			}
			/*
			* 正则表达式：
			* 1.正则表达式是一种语言，有自己的语法，可以用来判断字符串是否符合规定的格式
			* 2.var reqExp = /^(([1-9]\d*)|0)$/; 表示1-9匹配多个或者0
			* */
			var reqExp = /^(([1-9]\d*)|0)$/;
			if(!(cost==""||reqExp.test(cost))){
				$("#cost-msg").text("成本应该为非负整数");
				return;
			}
			$.ajax({
				url:"workbench/activity/save.do",
				data:{
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.code=="1"){
						$("#createActivityModal").modal("hide");
						//刷新市场活动列表，显示第一页数据
						pageList(1,$("#pageSize"))
					}else{
						$("#createActivityModal").modal("show");
						alert(${data.msg});
					}
				}

			})
		})//创建按钮单击事件
		$("#selectBtn").click(function(){
			/*
			* 点击查询按钮的时候将搜索框的信息保存到隐藏域
			* */
			$("#hidden-name").val($.trim($("#select-name").val()));
			$("#hidden-owner").val($.trim($("#select-owner").val()));
			$("#hidden-startDate").val($.trim($("#select-startDate").val()));
			$("#hidden-endDate").val($.trim($("#select-endDate").val()));
			pageList(1,$("#pageSize").val());
			;
		})
		//回车事件
		$(window).keydown(function(event){
			/*
			* 回车的时候将搜索框的信息保存到隐藏域
			* */
			$("#hidden-name").val($.trim($("#select-name").val()));
			$("#hidden-owner").val($.trim($("#select-owner").val()));
			$("#hidden-startDate").val($.trim($("#select-startDate").val()));
			$("#hidden-endDate").val($.trim($("#select-endDate").val()));
			if(event.key=='Enter')
				pageList(1,$("#pageSize").val());

		})
		//全选复选框单击事件
		$("#selectAll").change(function(){
			//如果是>只能获取直接子标签 而空格可以获取	全部子标签
			$("#activityBody input[type='checkbox']").prop("checked",this.checked);
		})
		//为动态生成复选框增加与全选框的判断
		//动态生成的元素要以on方法的形式来出发时间
		/*
        * 1、绑定元素有效的外层元素
        * 2、绑定的元素调用on绑定事件的方法
        * 	$(有效的外层元素).on(绑定的事件,需要绑定的元素,方法)
        * */
		$("#activityBody").on("click",$("input[name=select]"),function () {
			//如果状态为checked的复选框数量等同于复选框数量，则对全选框赋值true，否则为false
			$("#selectAll").prop("checked",$("input[name=select]").length==$("input[name=select]:checked").length);
		})
		//删除按钮增加单击事件
		$("#deleteBtn").click(function(){
			//手机参数
			var checkedIds = $("#activityBody input[type=checkbox]:checked");
			if(checkedIds.size() == 0){
				alert("请选择要删除的市场活动");
				return;
			}
			if(!window.confirm("请确定是否删除"))
				return;
			//获取选择到的数组id
			var ids="";
			$.each(checkedIds,function(){
				//获取dom对象的属性
				ids += "id="+this.value+"&";
				<%--alert(this.value)--%>
			});
			//截取字符串
			ids = ids.substr(0,ids.length-1);
			//发送请求到后台
			$.ajax({
				url:"workbench/activity/delete.do",
				data:ids,
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.code=="1"){
						pageList(1,$("#pageSize").val());
					}else {
						alert(data.msg);
					}
				}
			})
		})
	});//入口函数

	//分页方法
	function pageList(pageNo,pageSize){
		//将复选框的√干掉
		$("#selectAll").prop("checked",false);
		<%--$("#activityBody input[type='checkbox']").prop("checked",false);--%>
		//保存每页显示数量
		$("#pageSize").val(pageSize);
		//查询前将隐藏域值赋给搜索域
		$("#select-name").val($.trim($("#hidden-name").val()));
		$("#select-owner").val($.trim($("#hidden-owner").val()));
		$("#select-startDate").val($.trim($("#hidden-startDate").val()));
		$("#select-endDate").val($.trim($("#hidden-endDate").val()));

		var name = $.trim($("#select-name").val());
		var owner = $.trim($("#select-owner").val());
		var startDate = $.trim($("#select-startDate").val());
		var endDate = $.trim($("#select-endDate").val());


		$.ajax({
			url:"workbench/activity/pageList.do",
			data:{
				name:name,
				owner:owner,
				startDate:startDate,
				endDate:endDate,
				pageNo:pageNo,
				pageSize:pageSize
			},
			type:"post",
			dataType:"json",
			success:function(data){
				var html ="";
				$.each(data.activityList,function(i,n){
					html +='<tr class="active">';
					html +='	<td><input type="checkbox" name="select" value="'+n.id+'"/></td>';
					html +='	<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'detail.html\';">'+n.name+'</a></td>';
					html +='	<td>'+n.owner+'</td>';
					html +='	<td>'+n.startDate+'</td>';
					html +='	<td>'+n.endDate+'</td>';
					html +='</tr>';


				})
				$("#activityBody").html(html);
				//计算总页数
				var totalPages = 1;
				if(data.totalRows%pageSize==0){
					totalPages = data.totalRows/pageSize;
				}else{
					totalPages = parseInt(data.totalRows/pageSize)+1;
				}
				//分页插件函数
				$("#page").bs_pagination({
					currentPage:pageNo,						//当前页号
					rowsPerPage:pageSize,					//每页显示条数
					totalRows:data.totalRows,				//总条数
					totalPages:totalPages,					//总页数
					visiblePageLinks:5,						//最多显示卡片数
					showGoToPage:true,						//控制是否显示跳转页面,默认为true
					showRowsPerPage:true,					//是否显示每页显示条数，默认是true
					showRowsInfo:true,						//是否显示记录信息，默认是true
					onChangePage:function(event,pageObj){	//用户切换页面时发生的事件，每次切换页面都返回切换页号之后的pageNo和pageSize
						pageList(pageObj.currentPage,pageObj.rowsPerPage);
					}
				})

			}

		});
	}
	
</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="create-form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">
								</select>
								<span id="owner-msg" class="msg" style="color: #b92c28"></span>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control " id="create-marketActivityName">
								<span id="name-msg" class="msg" style="color: #b92c28"></span>
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly>
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate" readonly>
								<span id="date-msg" class="msg" style="color: #b92c28"></span>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
								<span id="cost-msg" class="msg" style="color: #b92c28"></span>
							</div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
			<input type="hidden" id="hidden-name"/>
			<input type="hidden" id="hidden-owner"/>
			<input type="hidden" id="hidden-startDate"/>
			<input type="hidden" id="hidden-endDate"/>
			<input type="hidden" id="pageSize"/>

			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="select-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="select-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="select-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="select-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="selectBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportActivityXzBtn" type="button" class="btn btn-default" ><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox"id="selectAll"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">


					</tbody>
				</table>
				<div id="page"></div>
			</div>
		<%--
			<div style="height: 50px; position: relative;top: 30px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRows"></b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>
			
		</div>
		
	</div>
	--%>
</body>
</html>