<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<link href="${basepath}/uploadify/uploadify.css" rel="stylesheet">
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>新增banner</h5>
					</div>
					<div class="ibox-content">
						<form method="POST" class="form-horizontal"
							action="${basepath}/banner/insert.html"
							onsubmit="return check();">
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">链接新闻</label>
								<div class="col-sm-10">
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#myModal">选择链接新闻</button>
									<input type="text" class="form-control" readonly placeholder="新闻链接"
										id="title" name="title" value="${entity.title}"> <input
										type="hidden" class="form-control" id="link" name="link"
										value="${entity.link}">
								</div>
							</div>

							<div class="hr-line-dashed"></div>
							<div class="form-group">
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
														type="hidden" id="mimg" name="mimg" value="${entity.imgsrc}">
													<ul class="upload_file_box" style="display: none;"
														id="queue"></ul>
												</div>
											</div>
											<div
												style="<c:if test="${empty entity.imgsrc}" >display: none;</c:if>margin-bottom:20px;"
												id="small_prev">
												<span id="image_cover"> <c:if
														test="${not empty entity.imgsrc}">
														<img style='max-width: 100px; max-height: 200px;'
															src='${basepath}${entity.imgsrc}'>
													</c:if>
												</span><a class="js_removeCover" href="javascript:void(0);"
													onclick="deleteCover('');">删除</a>
											</div>
										</div>
									</div>
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
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
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
	</script>
	<script type="text/javascript">
		
	</script>
	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新闻列表</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-bordered table-hover "
						id="editable">
						<thead>
							<tr>
								<th style="text-align: center;">选择</th>
								<th style="text-align: center;">序号</th>
								<th style="text-align: center;">标题</th>
								<th style="text-align: center;">浏览数量</th>
								<th style="text-align: center;">评论数量</th>
								<th style="text-align: center;">是否热门</th>
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
									<td style="text-align: center;"><input type="radio"
										name="newsitem" class="selectOne" value="${row.title}"
										id="${row.id}"></td>
									<td style="text-align: center;"><c:out
											value="${vs.count + (pageData.pageNumber-1)*pageData.pageSize}" /></td>
									<td style="text-align: center;">${row.title}</td>
									<td style="text-align: center;">${row.viewnum}</td>
									<td style="text-align: center;">${row.commentnum}</td>
									<td style="text-align: center;">${row.ishot.displayName}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/views/include/pagination.jsp"%>
				</div>
				<div class="modal-footer">
					<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
					<a type="button" class="btn btn-primary confirmSelect">确定</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('.confirmSelect').click(function() {
				var val = $('input:radio[name="newsitem"]:checked').val();
				var id = $('input:radio[name="newsitem"]:checked').attr("id");
				if (val == null || id == null) {
					layer.msg('请选择新闻链接', {
						time : 1000,
						offset : '100px'
					});
				} else {
					$.ajax({
						type : "POST",
						async : false,
						url : "${basepath}/banner/checkNewsBanner.html",
						cache : false,
						data : {
							id : id
						},
						dataType:"json",
						success : function(data) {
							console.log("返回结果为:"+data);
							if (data == 'failure') {
								layer.msg("该新闻已经关联,请重新选择!", {
									icon : 1
								});
							} else {
								$('#myModal').modal('hide');
								$('#title').val(val);
								$('#link').val("${basepath}/news_edit?id=" + id);
							}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
						}
					});
				}
			});
		});
		function deleteCover(){
	    	$("#small_prev").hide();
    	}
		function check(){
			var link = $("input[name='link']").val();
			var imgsrc = $("input[name='mimg']").val();
			console.log("link:"+link+"imgsrc:"+imgsrc);
			if (validata(link)) {
				layer.msg('请选择链接新闻', {
					time : 1000,
					offset : '100px'
				});
				return false;
			}
			if (validata(imgsrc)) {
				layer.msg('请上传图片', {
					time : 1000,
					offset : '100px'
				});
				return false;
			}
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