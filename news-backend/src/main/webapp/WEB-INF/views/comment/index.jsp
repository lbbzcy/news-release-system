<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="form-group">
						<div class="row">
							<div class="col-md-4">
								<input type="text" placeholder="" class="form-control"
									id="searchContent">
							</div>
							<div class="col-md-3 text-left">
								<button class="btn btn-primary " onclick="page(1);">
									<i class="fa fa-search"></i>搜索
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
									<th style="text-align: center;">评论新闻标题</th>
									<th style="text-align: center;">评论时间</th>
									<th style="text-align: center;">评论人</th>
									<th style="text-align: center;">编辑</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="text-align: center;">01</td>
									<td style="text-align: center;">${item.title}</td>
									<td style="text-align: center;">
										<%-- ${ltfun:dateLongToStringFormat(item.createtime,'yyyy-MM-dd HH:mm')} --%>
									</td>
									<td style="text-align: center;">commentuser</td>
									<td style="text-align: center;"><a
										class="btn btn-success btn-bitbucket btn-xs" href="edit.html">查看详情</a>
										<a class="btn btn-danger btn-bitbucket btn-xs"
										onclick="deletebanner(1);">删除</a></td>
								</tr>
							</tbody>
						</table>

					</div>
					<!--分页-->
					<div class="col-sm-6">
						<div class="dataTables_info" id="editable_info" role="alert"
							aria-live="polite" aria-relevant="all">显示 1 到 10 项，共 57 项</div>
					</div>
					<div class="col-sm-6">
						<div class="dataTables_paginate paging_simple_numbers"
							id="editable_paginate">
							<ul class="pagination">
								<li class="paginate_button previous disabled"
									aria-controls="editable" tabindex="0" id="editable_previous"><a
									href="#">上一页</a></li>
								<li class="paginate_button active" aria-controls="editable"
									tabindex="0"><a href="#">1</a></li>
								<li class="paginate_button " aria-controls="editable"
									tabindex="0"><a href="#">2</a></li>
								<li class="paginate_button " aria-controls="editable"
									tabindex="0"><a href="#">3</a></li>
								<li class="paginate_button " aria-controls="editable"
									tabindex="0"><a href="#">4</a></li>
								<li class="paginate_button " aria-controls="editable"
									tabindex="0"><a href="#">5</a></li>
								<li class="paginate_button " aria-controls="editable"
									tabindex="0"><a href="#">6</a></li>
								<li class="paginate_button next" aria-controls="editable"
									tabindex="0" id="editable_next"><a href="#">下一页</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/include/mgr_script.jsp"%>
	<script>
		$(function() {
			$("#btn_add").bind("click", function() {
				console.log("您点击了创建banner的操作");
				window.location.href = "edit.html";
			})
		});
		function deletebanner(bannerid) {
			console.log("您点击了删除banner的按钮");
			var index = layer.confirm('您确定要删除该评论吗？', {
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
