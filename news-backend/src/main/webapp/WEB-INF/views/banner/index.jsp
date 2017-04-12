<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<button class="btn btn-success" id="btn_add">
									<i class="fa fa-plus"></i>创建
								</button>
							</div>
						</div>
					</div>
					<div id="tableList">
						<table class="table table-striped table-bordered table-hover "
							id="editable">
							<thead>
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">新闻链接标题</th>
									<th style="text-align: center;">图片</th>
									<th style="text-align: center;">创建时间</th>
									<th style="text-align: center;">修改时间</th>
									<th style="text-align: center;">创建者</th>
									<th style="text-align: center;">更新者</th>
									<th style="text-align: center;">编辑</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageData.rows}">
								<tr>
									<td colspan="8" class="text-center">暂无数据</td>
								</tr>

							</c:if>
							<c:forEach items="${pageData.rows}" var="row" varStatus="vs">
								<tr class="${vs.count%2 == 0?'gray':''}">
									<td style="text-align: center;"><c:out value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
									<td style="text-align: center;">${row.title}</td>
									<td style="padding:1px;text-align: center;width:150px;height:80px;">
										<img style="width:100%;height:100%;" alt="${row.link}" src="${bannersrc}${row.imgsrc}">
									</td>
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

	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script>
		$(function() {
			$("#btn_add").bind("click", function() {
				console.log("您点击了创建banner的操作");
				window.location.href = "${basepath}/banner/edit.html";
			})
		});
		function deletebanner(bannerid) {
			console.log("您点击了删除banner的按钮");
			var index = layer.confirm('您确定要删除该banner吗？', {
				btn : [ '确定', '不要' ]
			//按钮
			}, function() {
				layer.close(index);
			}, function() {
				layer.close(index);
			});
		}
	</script>
</body>
</html>
