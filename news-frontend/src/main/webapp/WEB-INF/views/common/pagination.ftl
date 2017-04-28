<!--分页-->
<#if pagination.total?length gte 1>
	<div class="block_pager">
		<a href="?pageNumber=${pagination.prePage }&pageSize=${pagination.pageSize }${paginationQueryParam}" class="prev">上一页</a> 
		<a href="?pageNumber=${pagination.lastPage }&pageSize=${pagination.pageSize }${paginationQueryParam}" class="next">下一页</a>

		<div class="pages">
			<ul>
				<#list pagination.pageNumList as num>
					<#if num_index lte 5>
						<#if pagination.currentPage == num>
							<li class="current"><a href="javascript:void(0)">${num}</a></li>
						<#else>
							<li class="current"><a href="?pageNumber=${num}&pageSize=${pagination.pageSize}${paginationQueryParam}">${num}</a></li>
						</#if>
					<#elseif num_index lt pagination.lastPage>
						...
					<#else>
						<li><a href="?pageNumber=${num}&pageSize=${pagination.pageSize}${paginationQueryParam}">${pagination.lastPage}</a></li>
					</#if>
				</#list>
			</ul>
		</div>

		<div class="clearboth"></div>
	</div>
</#if>