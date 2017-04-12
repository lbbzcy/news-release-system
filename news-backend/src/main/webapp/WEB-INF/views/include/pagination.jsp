<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!--分页-->
<c:if test="${pagination.total ge 1 }">
	<div class="col-sm-6">
		<div class="dataTables_info" id="editable_info" role="alert"
			aria-live="polite" aria-relevant="all">
			共
			<c:out value="${pagination.total }" />
			条记录，共
			<c:out value="${pagination.lastPage }" />
			页，每页显示 <select
				onchange="location.href='?pageNumber=1<c:out value="${paginationQueryParam}" />&pageSize='+this.value">
				<option value="10"
					<c:if test="${pagination.pageSize == 10 }">selected="selected"</c:if>>10</option>
				<option value="15"
					<c:if test="${pagination.pageSize == 15 }">selected="selected"</c:if>>15</option>
				<option value="20"
					<c:if test="${pagination.pageSize == 20 }">selected="selected"</c:if>>20</option>
				<option value="50"
					<c:if test="${pagination.pageSize == 50 }">selected="selected"</c:if>>50</option>
				<option value="100"
					<c:if test="${pagination.pageSize == 100 }">selected="selected"</c:if>>100</option>
			</select> 条记录
		</div>
	</div>
	<div class="col-sm-6">
		<div class="dataTables_paginate paging_simple_numbers"
			id="editable_paginate">
			<ul class="pagination">
				<c:if test="${pagination.currentPage == 1 }">
					<li class="paginate_button active" aria-controls="editable"><a
						href="javascript:void(0);" title="首页" class="active">首页</a></li>
					<li class="paginate_button disabled" aria-controls="editable"><a
						href="javascript:void(0);" title="上一页" class="active">上一页</a></li>
				</c:if>
				<c:if test="${pagination.currentPage != 1 }">
					<li class="paginate_button" aria-controls="editable"><a
						href="?pageNumber=1&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />"
						title="首页" class="i-prev">首页</a></li>
					<li class="paginate_button" aria-controls="editable"><a
						href="?pageNumber=${pagination.prePage }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />"
						title="上一页" class="i-prev">上一页</a></li>
				</c:if>

				<c:forEach items="${pagination.pageNumList }" var="num">
					<c:choose>
						<c:when test="${fn:indexOf(num , '_') > -1 }">
							<li class="paginate_button" aria-controls="editable"><a
								href="?pageNumber=${fn:split(num,'_')[0] }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />">...</a>
							</li>
						</c:when>
						<c:otherwise>
							<c:if test="${(pagination.currentPage) == num }">
								<li class="paginate_button active" aria-controls="editable"><a
									href="javascript:void(0)" class="active"><c:out
											value="${num }" /></a></li>
							</c:if>
							<c:if test="${(pagination.currentPage) != num }">
								<li class="paginate_button" aria-controls="editable"><a
									href="?pageNumber=${num }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />"><c:out
											value="${num }" /></a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${pagination.currentPage == pagination.lastPage }">
					<li class="paginate_button disabled" aria-controls="editable"><a
						href="javascript:void(0);" title="下一页" class="active">下一页</a></li>
					<li class="paginate_button" aria-controls="editable"><a
						href="javascript:void(0);" title="尾页" class="active">尾页</a></li>
				</c:if>
				<c:if test="${pagination.currentPage != pagination.lastPage }">
					<li class="paginate_button" aria-controls="editable"><a
						href="?pageNumber=${pagination.nextPage }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />"
						title="下一页">下一页</a></li>
					<li class="paginate_button" aria-controls="editable"><a
						href="?pageNumber=${pagination.lastPage }&pageSize=${pagination.pageSize }<c:out value="${paginationQueryParam}" />"
						title="尾页">尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</c:if>
