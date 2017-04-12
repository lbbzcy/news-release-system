<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>评论详情</h5>
					</div>
					<div class="ibox-content">
						<form method="POST" class="form-horizontal"
							action="${basepath}/mgr/banner/insert.xhtml"
							onsubmit="return check()">
							<div class="form-group">
								<label class="col-sm-2 control-label">评论新闻标题</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="title"
										value="${entity.title}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">评论内容</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="content"
										value="${entity.title}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">评论人</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="content"
										value="${entity.title}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">评论时间</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="content"
										value="${entity.title}">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<!--<button class="btn btn-primary" type="submit">保存</button>-->
									<button class="btn btn-white" type="submit"
										onclick="window.history.go(-1);">返回</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/include/mgr_script.jsp"%>
</body>
</html>