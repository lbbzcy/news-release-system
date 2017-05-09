<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<style type="text/css">
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
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="m-searchform">
						<form id="listQueryForm" action="${basepath}/user/list.html"
							method="post">
							<div class="ipt">
								<label>用户名：</label> <input placeholder="支持模糊查询" type="text"
									name="username" value="${param.username}" />
							</div>
							<div class="ipt">
								<label>昵称：</label> <input placeholder="支持模糊查询" type="text"
									name="nickname" value="${param.nickname}" />
							</div>
							<div class="ipt">
								&nbsp;
							</div>	
							<div class="ipt">
								<label>手机号：</label> <input placeholder="支持模糊查询" type="text"
									name="mobile" value="${param.mobile}" />
							</div>
							<div class="ipt">
								<label>邮箱：</label> <input placeholder="支持模糊查询" type="text"
									name="email" value="${param.email}" />
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
						<table class="table table-striped table-bordered table-hover"
							id="editable">
							<thead>
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">用户名</th>
									<th style="text-align: center;">头像</th>
									<th style="text-align: center;">昵称</th>
									<th style="text-align: center;">手机号码</th>
									<th style="text-align: center;">邮箱</th>

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
									<td style="text-align: center;"><c:out
											value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
									<td style="text-align: center;" id="name">${row.username}</td>
									<td style="text-align: center;width:100px;" id="name">
										<img style="height:100px;width:100px;border-radius:100px;" src="${row.header}"/>
									</td>
									<td style="text-align: center;" id="name">${row.nickname}</td>
									<td style="text-align: center;">${row.mobile}</td>
									<td style="text-align: center;" id="weixin">${row.email}</td>
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
		function queryForm() {
			$("#listQueryForm").submit();
		}
		function resetForm() {
			$("#listQueryForm :input").not(":button, :submit, :reset, :hidden")
					.val("").removeAttr("checked").remove("selected");
		}
</script>
</body>
</html>
