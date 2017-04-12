<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox-content">
				<div class="form-group">
					<div class="row">
						<div class="col-md-3 text-left">

							<a class="btn btn-success" id="btn_add"
								href="${basepath}/template/edit.html"> <i class="fa fa-plus"></i>创建模板
							</a>
						</div>
					</div>
				</div>
				<div id="tableList">
					<table class="table table-striped table-bordered table-hover"
						id="editable">
						<thead>
							<tr>
								<th style="text-align: center;">序号</th>
								<th style="text-align: center;">模板名称</th>
								<th style="text-align: center;">创建时间</th>
								<th style="text-align: center;">更新时间</th>
								<th style="text-align: center;">创建者</th>
								<th style="text-align: center;">更新者</th>
								<th style="text-align: center;">操作</th>

							</tr>
						</thead>
						<tbody>
							<c:if test="${empty pageData.rows}">
								<tr>
									<td colspan="6" class="text-center">暂无数据</td>
								</tr>

							</c:if>
							<c:forEach items="${pageData.rows}" var="row" varStatus="vs">
								<tr class="${vs.count%2 == 0?'gray':''}">
									<td style="text-align: center;"><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
									<td id="name" style="text-align: center;">${row.name}</td>
									<td style="text-align: center;"><fmt:formatDate value="${row.createtime}" type="both"/> </td>
									<td style="text-align: center;"><fmt:formatDate value="${row.updatetime}" type="both"/> </td>
									<td style="text-align: center;">${row.creator}</td>
									<td style="text-align: center;">${row.updator}</td>
									<td id="icon_btn" style="text-align: center;"><a
										class="btn btn-danger btn-bitbucket btn-xs"
										onclick="openDialog(1,'${row.id}');"> <i
											class="fa fa-edit"></i>修改
									</a> <a class="btn btn-danger btn-bitbucket btn-xs"
										onclick="openDialog(2,'${row.id}');"> <i
											class="fa fa-edit"></i>删除
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/views/include/pagination.jsp"%>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script>
		function openDialog(opentype, newstypeid) {
			if (opentype == 1) {
				window.location.href = "edit.html?id=" + newstypeid;
			} else if (opentype == 2) {
				//删除,使用layer弹出对话框提示是否删除
				var index = layer.confirm('您确定要删除该模板吗？', {
					btn : [ '确定', '不要' ]
				//按钮
				}, function() {
					$.ajax({
						type : "POST",
						url : "${basepath}/template/delete.html",
						cache : false,
						data : {
							id : newstypeid
						},
						dataType:"json",
						success : function(data) {
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
						error : function() {
							layer.msg("删除失败", {
								icon : 1
							});
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
