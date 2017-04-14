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
							新增新闻类别
						</c:if>
						<c:if test="${!empty entity}">
							修改新闻类别
						</c:if>
					</h5>
				</div>
				<div class="ibox-content">
					<form method="POST" class="form-horizontal"
						action="${basepath}/news_type/add.html" onsubmit="return check();">
						<div class="form-group">
							<label class="col-sm-2 control-label">类别名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="类别名称"
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
			var newstype = $("input[name='name']").val();
			console.log("newstype:"+newstype);
			if (validata(newstype)) {
				layer.msg('请填写类别名称', {
					time : 2000,
					offset : '100px'
				});
				return false;
			}
			var flag = true;
			$.ajax({
				type : "POST",
				async: false, 
				url : "${basepath}/news_type/checkNewsType.html",
				cache : false,
				data : {
					name : newstype
				},
				dataType:"json",
				success : function(data) {
					console.log("判断新闻类别是否可用:"+data);
					if (data == 'failure') {
						layer.msg("该类别名称已经存在！", {
							icon : 1
						});
						flag =  false;
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
				}
			});
			console.log("flag:"+flag);
			return flag;
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
