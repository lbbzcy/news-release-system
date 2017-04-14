<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						<c:if test="${empty entity}">
							新增新闻模板
						</c:if>
						<c:if test="${!empty entity}">
							修改新闻模板
						</c:if>
					</h5>
				</div>
				<div class="ibox-content">
					<form method="POST" class="form-horizontal"
						action="${basepath}/template/add.html"
						onsubmit="return check();">
						<div class="form-group">
							<label class="col-sm-2 control-label">模板名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="请填写模板名称"
									name="name" value="${entity.name}">
							</div>
						</div>
						<input type="hidden" name="id" class="form-control"
							value="${entity.id}" id="entityId" />

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="submit">保存</button>
								<button class="btn btn-white" type="submit"
									onclick="window.history.go(-1);">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script type="text/javascript">
		function check() {
			var template = $("input[name='name']").val();
			if (validata(template)) {
				layer.msg('请填写模板名称', {
					time : 2000,
					offset : '100px'
				});
				return false;
			}
			var flag = true;
			$.ajax({
				async : false,
				type : "POST",
				url : "${basepath}/template/checkTemplate.html",
				cache : false,
				data : {
					name : template
				},
				dataType:"json",
				success : function(data) {
					if (data == 'failure') {
						layer.msg("该模板已经存在！", {
							icon : 1
						});
						flag = false;
					}
				}
			});
			if (flag) {
				return true;
			} else {
				return false;
			}
			layer.load(2, {
				time : 10 * 1000
			});
		}
		function validata(name) {
			if (name == undefined || name == null || name == "") {
				return true;
			} else {
				return false;
			}
		}
	</script>
</body>
</html>
