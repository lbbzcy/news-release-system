<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<style>
	.m-searchform {
	padding-top: 5px;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	margin: 5px 0px 5px 0px;
	background: #F5F5F6;
	border-width: 1px;
	border-style: solid;
	border-color: #e7eaec;
}

.m-searchform .ipt {
	width: 33%;
	display: inline-block;
	padding: 3px 0px;
}

.m-searchform label {
	width: 80px;
	display: inline-block;
	margin-left: 20px;
	text-align: right;
}

.m-searchform input {
	width: 200px;
	height: 30px;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	padding: 5px;
	border-width: 1px;
	border-style: solid;
}

.m-searchform select {
	width: 200px;
	height: 30px;
	padding-left: 5px;
	border-image-source: initial;
	border-image-slice: initial;
	border-image-width: initial;
	border-image-outset: initial;
	border-image-repeat: initial;
	border-width: 1px;
	border-style: solid;
}

.m-searchform .queryreset {
	margin-top: 5px;
	height: 43px;
	line-height: 43px;
	padding: 4px 12px 4px 20px;
	border-top: 1px solid #e7eaec;
}
</style>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="m-searchform">
						<form id="listQueryForm" action="${basepath}/comment/list.html"
							method="post">
							<div class="ipt">
								<label>新闻标题：</label> <input placeholder="支持模糊查询" type="text"
									name="newstitle" value="${param.newstitle}" />
							</div>
							<div class="queryreset">
								<div class="pull-right">
									<a class="btn btn-sm btn-success" href="javascript:void(0)"
										onclick="queryForm();"><i class="fa fa-search"></i>查询</a> <a
										class="btn btn-sm btn-primary" href="javascript:void(0)"
										onclick="resetForm();"><i class="fa fa-refresh"></i>重置</a>
								</div>
								<div class="f-cb"></div>
							</div>
						</form>
					</div>
					<div id="tableList">
						<table class="table table-striped table-bordered table-hover "
							id="editable">
							<thead>
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">新闻标题</th>
									<th style="text-align: center;">评论人</th>
									<th style="text-align: center;">评论内容</th>
									<th style="text-align: center;">评论时间</th>
									<th style="text-align: center;">点赞数量</th>
									<th style="text-align: center;">编辑</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageData.rows}">
									<tr>
										<td colspan="7" class="text-center">暂无数据</td>
									</tr>
								</c:if>
								<c:forEach items="${pageData.rows}" var="row" varStatus="vs">
									<tr class="${vs.count%2 == 0?'gray':''}">
										<td style="text-align: center;"><c:out
												value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
										<td style="text-align: center;">${row.newstitle}</td>
										<td style="text-align: center;">${row.username}</td>
										<td style="text-align: center;">${row.content}</td>
										<td style="text-align: center;"><fmt:formatDate value="${row.createtime}" type="both" /></td>
										<td style="text-align: center;">${row.likenum}</td>
										<td id="icon_btn" style="text-align: center;"><%-- <a
											class="btn btn-danger btn-bitbucket btn-xs"
											onclick="openDialog(1,'${row.id}');"> <i
												class="fa fa-edit"></i>修改
										</a>  --%><a class="btn btn-danger btn-bitbucket btn-xs"
											onclick="openDialog(2,'${row.id}');"> <i
												class="fa fa-edit"></i>删除
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<%@ include file="/WEB-INF/views/include/pagination.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script>
		$(function() {
			
		});
		function queryForm() {
			$("#listQueryForm").submit();
		}
		function resetForm() {
			$("#listQueryForm :input").not(":button, :submit, :reset, :hidden")
					.val("").removeAttr("checked").remove("selected");
		}
		function openDialog(opentype, rowid) {
			if (opentype == 1) {
				window.location.href = "${basepath}/comment/edit.html?id=" + rowid;
			} else if (opentype == 2) {
				//删除,使用layer弹出对话框提示是否删除
				var index = layer.confirm('您确定要删除该条评论吗？', {
					btn : [ '确定', '不要' ]
				//按钮
				}, function() {
					$.ajax({
						type : "POST",
						url : "${basepath}/comment/delete.html",
						cache : false,
						data : {
							id : rowid
						},
						dataType:"json",
						success : function(data) {
							console.log("删除状态:" + data);
							if (data == 'success') {
								layer.close(index);
								layer.msg("删除成功", {
									icon : 1
								});
								window.location.reload();
							} else {
								layer.close(index);
								layer.msg("删除失败", {
									icon : 1
								});
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
							/*弹出jqXHR对象的信息*/
				            console.log(jqXHR.responseText);
				            console.log(jqXHR.status);
				            console.log(jqXHR.readyState);
				            console.log(jqXHR.statusText);
				            /*弹出其他两个参数的信息*/
				            console.log(textStatus);
				            console.log(errorThrown);
							/* console.log("error");
							layer.msg("删除失败", {
								icon : 1
							}); */
						}
					});

				}, function() {
					layer.close(index);
				});
			}
		}
	</script>
</body>
</html>
