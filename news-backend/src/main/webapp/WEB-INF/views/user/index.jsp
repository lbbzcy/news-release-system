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
							<div class="col-md-3">
								<div class="input-group">
									<input type="text" placeholder="输入用户名进行搜索" class="form-control"
										id="searchContent">

									<div class="input-group-btn">
										<button class="btn btn-primary " onclick="page(1);">
											<i class="fa fa-search"></i>搜索
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="tableList">
						<table class="table table-striped table-bordered table-hover"
							id="editable">
							<thead>
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">用户名</th>
									<th style="text-align: center;">手机号码</th>
									<th style="text-align: center;">邮箱</th>
									<th style="text-align: center;">注册时间</th>
									<th style="text-align: center;">操作</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="text-align: center;">${s.count}</td>
									<td style="text-align: center;" id="name">${item.name}</td>
									<td style="text-align: center;">${item.mobile}</td>
									<td style="text-align: center;" id="weixin">${item.email}</td>
									<td style="text-align: center;">
										<%-- ${ltfun:dataLongToSNS(item.createtime,"yyyy-MM-dd HH:mm")} --%>
									</td>
									<!-- item.createtime -->
									<td style="text-align: center;" id="icon_btn"><a
										class="btn btn-danger btn-bitbucket btn-xs"
										onclick="openDialog(1,'${item.id}');"> <i
											class="fa fa-edit"></i>修改
									</a> <a class="btn btn-danger btn-bitbucket btn-xs"
										onclick="openDialog(2,'${item.id}');"> <i
											class="fa fa-edit"></i>删除
									</a></td>
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
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script>
    //打开对话框
    function openDialog(opentype, userid) {
        if (opentype == 1) {
            window.location.href="edit.html";
        } else if (opentype == 2) {
            var index = layer.confirm('您确定要删除该用戶吗？', {
                btn:  ['确定', '不要'] //按钮
            }, function(){
                layer.close(index);
            }, function(){
                layer.close(index);
            });
        }
    }
</script>
</body>
</html>
