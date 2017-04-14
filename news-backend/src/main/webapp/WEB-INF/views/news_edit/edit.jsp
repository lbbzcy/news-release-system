<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<c:if test="${empty entity}">
							<h5>新闻发布</h5>
						</c:if>
						<c:if test="${not empty entity}">
							<h5>新闻编辑</h5>
						</c:if>
					</div>
					<div class="ibox-content">
						<form method="POST" class="form-horizontal"
							action="${basepath}/news_edit/add.html"
							onsubmit="return check();">
							<div class="form-group">
								<label class="col-sm-2 control-label">新闻类别</label>
								<div class="col-sm-10 ">
									<select class="form-control m-b" id="typeid" name="typeid"
										value="${entity.typeid}">
										<c:forEach items="${newsTypeList}" var="type">
											<option value="${type.id}"
												<c:if test="${type.id == entity.typeid }">selected="selected"</c:if>>
												<c:out value="${type.name}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">新闻模板</label>
								<div class="col-sm-10 ">
									<select class="form-control m-b" id="templateid"
										name="templateid" value="${entity.templateid}"
										onchange="change">
										<c:forEach items="${newsTemplateList}" var="template">
											<option value="${template.id}"
												<c:if test="${template.id == entity.templateid }">selected="selected"</c:if>>
												<c:out value="${template.name}"></c:out>
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">是否热门</label>
								<div class="col-sm-10 ">
									<select class="form-control m-b" name="ishot" id="ishot"
										value="${entity.ishot}">
										<c:forEach items="${isHotList }" var="en">
											<option value="${en }"
												<c:if test="${en == entity.ishot }">selected="selected"</c:if>><c:out
													value="${en.displayName }" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">新闻标题</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="请填写标题"
										name="title" value="${entity.title}">
								</div>
							</div>
							<!-- 编辑时显示 -->
							<c:if test="${not empty entity}">
								<div class="hr-line-dashed"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">浏览数量</label>
									<div class="col-sm-10 ">
										<input type="text" class="form-control" readonly
											name="viewnum" value="${entity.viewnum}"> </select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">评论次数</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" readonly
											name="commentnum" value="${entity.commentnum}">
									</div>
								</div>
							</c:if>
							<div class="hr-line-dashed"></div>
							<!-- 新闻内容 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">新闻内容</label>
								<div class="col-sm-10">
									<!-- <script id="editor" type="text/plain"></script> -->
									<textarea name="content" id="editor">${entity.content }</textarea>
								</div>
							</div>
							<input type="hidden" name="id" class="form-control"
								value="${entity.id}" id="entityId" />


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
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script type="text/javascript" charset="utf-8"
		src="${basepath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${basepath}/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${basepath}/ueditor/lang/zh-cn/zh-cn.js"></script>
	</script>
	<script type="text/javascript">
		function check() {

			var type = $("select[name='typeid']").val();
			var template = $("select[name='templateid']").val();
			var ishot = $("select[name='ishot']").val();
			var title = $("input[name='title']").val();

			if (validata(type)) {
				layer.msg('请选择新闻类别', {
					time : 2000,
					offset : '100px'
				});
				return false;
			}
			if (validata(template)) {
				layer.msg('请选择新闻模板', {
					time : 2000,
					offset : '100px'
				});
				return false;
			}
			if (validata(ishot)) {
				layer.msg('请选择是否热门', {
					time : 2000,
					offset : '100px'
				});
				return false;
			}
			if (validata(title)) {
				layer.msg('请填写标题', {
					time : 2000,
					offset : '100px'
				});
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
	<script type="text/javascript">
		var ue = UE.getEditor('editor',
				{
					toolbars : [
							[ 'fullscreen', 'source', 'undo', 'redo', 'bold',
									'italic', 'underline', 'strikethrough',
									'indent', 'subscript', 'fontborder',
									'superscript', 'formatmatch', 'horizontal',
									'justifyleft', 'justifyright',
									'justifycenter', 'justifyjustify',
									'forecolor', 'insertorderedlist',
									'insertunorderedlist', 'lineheight',
									'inserttable', ],
							[ 'customstyle', 'fontfamily', 'fontsize',
									'paragraph', 'preview', 'removeformat',
									'time', 'date', 'inserttitle', 'cleardoc',
									'simpleupload', 'emotion', 'spechars',
									'map', 'insertvideo', 'autotypeset', ] ]
				});
	</script>
</body>
</html>