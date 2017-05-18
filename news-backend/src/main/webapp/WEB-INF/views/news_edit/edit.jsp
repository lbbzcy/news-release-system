<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<style type="text/css">
	.mediasrc {
		display:none;
	}
</style>
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
										onchange="change();">
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
							
							<div class="form-group mediasrc">
								<label class="col-sm-2 control-label">图片</label>
								<div class="col-sm-10">
									<div class="appmsg_edit_item">
										<label class="frm_label"> <strong class="title"
											id="pc_img">图片分辨率：180*180</strong>
										</label>
										<div class="frm_input_box">
											<div class="upload_box">
												<div class="upload_area">
													<input type="file" name="file" id="file_upload" /> <input
														type="hidden" id="mimg" name="mimg" value="${bannersrc}${entity.mediasrc}">
													<ul class="upload_file_box" style="display: none;"
														id="queue"></ul>
												</div>
											</div>
											<div
												style="<c:if test="${empty entity.mediasrc}" >display: none;</c:if>margin-bottom:20px;"
												id="small_prev">
												<span id="image_cover"> <c:if
														test="${not empty entity.mediasrc}">
														<img style='max-width: 100px; max-height: 200px;'
															src='${bannersrc}${entity.mediasrc}'>
													</c:if>
												</span><a class="js_removeCover" href="javascript:void(0);"
													onclick="deleteCover('');">删除</a>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="hr-line-dashed mediasrc"></div>
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
							<div class="form-group">
								<label class="col-sm-2 control-label">新闻简介</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" placeholder="请填写新闻简介"
										name="brief" value="${entity.brief}">
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
		$(function(){
			change();
		});
		function change(){
			//模板选择变化时函数
			var checkText=$("#templateid").find("option:selected").text().trim();
			if(checkText.indexOf("图片")!=-1){
				//增加图片框
				$('.mediasrc').css("display","block");
			} else{
				$('.mediasrc').css("display","none");
			}
		}
		//新闻编辑验证
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
	<script type="text/javascript"
		src="${basepath}/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript">
		var uploadurl = "${basepath}/banner/upload.html";
		$('#file_upload')
				.uploadify(
						{
							'fileObjName' : 'file',
							//允许上传的文件后缀
							'fileTypeExts' : '*.gif;*.jpg; *.png;*.jpeg;*.bmp',
							//在浏览窗口底部的文件类型下拉菜单中显示的文本
							'fileTypeDesc' : 'Image File',
							'overrideEvents' : [ 'onDialogClose' ],
							'onSelectError' : function(file, errorCode,
									errorMsg) {
								if (errorCode == -130) {
									layer.msg('格式不支持', {
										offset : '100px'
									});
									return;
								}
							},
							'buttonText' : '<i class="fa fa-upload"></i>上传图片',
							'buttonClass' : 'upload_access',
							'swf' : '${basepath}/uploadify/uploadify.swf',
							'multi' : false,
							'uploader' : uploadurl,
							'onUploadSuccess' : function(file, data, response) {
								var result = eval("(" + data + ")");
								$("#mimg").val(result.url);
								$("#image_cover")
										.html(
												"<img style='max-width:100px;max-height:200px;' src ='"+ result.url +"'>");
								$("#small_prev").show();
							}
						});
		function deleteCover(){
	    	$("#small_prev").hide();
    	}
	</script>
</body>
</html>