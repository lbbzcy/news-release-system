<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
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
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div class="m-searchform">
						<form id="listQueryForm" action="${basepath}/news_edit/list.html"
							method="post">
							<div class="ipt">
								<label>起止日期：</label> <input type="text" id="startTime"
									name="startTime"
									onclick="WdatePicker({ el:'startTime', dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})"
									value="${startTime}" class="Wdate" readonly="readonly"
									style="width: 95px; cursor: pointer;" /> 至 <input type="text"
									id="endTime" name="endTime"
									onclick="WdatePicker({ el:'endTime', dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"
									value="${endTime}" class="Wdate" readonly="readonly"
									style="width: 95px; cursor: pointer;" />
							</div>
							<div class="ipt">
								<input type="hidden" id="queryDateType" name="queryDateType" />
								<label></label> <a class="btn btn-sm btn-success"
									href="javascript:void(0)" onclick="queryTimeForm('1');"><i
									class="i-query"></i>今天</a> <a class="btn btn-sm btn-info"
									href="javascript:void(0)" onclick="queryTimeForm('2');"><i
									class="i-query"></i>最近1个月</a> <a class="btn btn-sm btn-warning"
									href="javascript:void(0)" onclick="queryTimeForm('3');"><i
									class="i-query"></i>最近1年</a>
							</div>
							<div class="ipt">
								<label>是否热门：</label> <select name="ishot">
									<option value="">所有</option>
									<c:forEach items="${isHotList }" var="en">
										<option value="${en }"
											<c:if test="${en == param.ishot }">selected="selected"</c:if>><c:out
												value="${en.displayName }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="ipt">
								<label>标题：</label> <input placeholder="支持模糊查询" type="text"
									name="title" value="${param.title}" />
							</div>
							<div class="ipt">
								<label>新闻类别：</label> <input placeholder="支持模糊查询" type="text"
									name="typename" value="${param.typename}" />
							</div>
							<div class="ipt">
								<label>新闻模板：</label> <input placeholder="支持模糊查询" type="text"
									name="templatename" value="${param.templatename}" />
							</div>
							<div class="queryreset">
								<div class="pull-left">
									<a class="btn btn-sm btn-success" id="btn_add"><i
										class="fa fa-plus"></i>创建</a>
								</div>
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
									<th style="text-align: center;">所属新闻类别</th>
									<th style="text-align: center;">所属模板名称</th>
									<th style="text-align: center;">标题</th>
									<th style="text-align: center;">浏览数量</th>
									<th style="text-align: center;">评论数量</th>
									<th style="text-align: center;">是否热门</th>
									<th style="text-align: center;">创建时间</th>
									<th style="text-align: center;">更新时间</th>
									<th style="text-align: center;">发布人</th>
									<th style="text-align: center;">更新人</th>
									<th style="text-align: center;">编辑</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty pageData.rows}">
									<tr>
										<td colspan="12" class="text-center">暂无数据</td>
									</tr>

								</c:if>
								<c:forEach items="${pageData.rows}" var="row" varStatus="vs">
									<tr class="${vs.count%2 == 0?'gray':''}">
										<td style="text-align: center;"><c:out
												value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
										<td style="text-align: center;">${row.typename}</td>
										<td style="text-align: center;">${row.templatename}</td>
										<td style="text-align: center;">${row.title}</td>
										<td style="text-align: center;">${row.viewnum}</td>
										<td style="text-align: center;">${row.commentnum}</td>
										<td style="text-align: center;">${row.ishot.displayName}</td>
										<td style="text-align: center;"><fmt:formatDate
												value="${row.createtime}" type="both" /></td>
										<td style="text-align: center;"><fmt:formatDate
												value="${row.updatetime}" type="both" /></td>
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
	<script type="text/javascript">
		$(function() {
			$("#btn_add").bind("click", function() {
				window.location.href = "edit.html";
			})
		});
		function queryForm() {
			$("#queryDateType").val('');
			$("#listQueryForm").submit();
		}
		function queryTimeForm(queryDateType) {
			$("#queryDateType").val(queryDateType);
			$("#listQueryForm").submit();
		}
		function resetForm() {
			$("#listQueryForm :input").not(":button, :submit, :reset, :hidden")
					.val("").removeAttr("checked").remove("selected");
		}
		function openDialog(opentype, rowid) {
			if (opentype == 1) {
				window.location.href = "${basepath}/news_edit/edit.html?id="
						+ rowid;
			} else if (opentype == 2) {
				//删除,使用layer弹出对话框提示是否删除
				var index = layer.confirm('您确定要删除该类别吗？', {
					btn : [ '确定', '不要' ]
				//按钮
				}, function() {
					$.ajax({
						type : "POST",
						url : "${basepath}/news_edit/delete.html",
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
