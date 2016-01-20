<#include "/jsp_imports.include"> <#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<%=request.getContextPath()%>/css/default.css"
		rel="stylesheet" type="text/css" />
	<s:include value="/common/include/jqueryUI.jsp"></s:include>
	<head>
		<title>列表页面</title>
	</head>

	<body>
		<s:form action="${classNameLower}/list.action" method="post"
			theme="simple">
			<s:token />
			<table width="100%" border="0" cellspacing="0" cellpadding="1"
				class="user_mb" height="100%">
				<!-- 列表页面title -->
				<tr>
					<td
						background="<%=request.getContextPath()%>/images/privacy_bg.gif"
						height="27">
						<table width="93" height="25" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td
									background="<%=request.getContextPath()%>/images/privacy_icon.gif"
									class="user_wz">
									${classNameLower}
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- 列表start -->
				<tr>
					<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="5" class="user_mb2" height="100%">
							<tr>
								<td>
									<!-- 查询表单与记录与翻页start -->
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="pm_mb1" id="con1" height="100%">
										<tr>
											<td valign="top">
												<!-- 查询表单start -->
												<table width="95%" border="0" align="center" cellpadding="0"
													cellspacing="0" class="pm_mb2">
													<tr>
														<td width="5%">
															<img
																src="<%=request.getContextPath()%>/images/pm_icon.gif"
																width="58" height="78" />
														</td>
														<!-- 查询表单查询条件start -->
														<td width="65%">
															<table border="0" cellspacing="2" cellpadding="0"
																align="center" width="100%">
																<tr>
																	<td align="right">
																		test1:
																	</td>
																	<td align="left">
																		<input type="text">
																	</td>
																	<td align="right">
																		test1:
																	</td>
																	<td align="left">
																		<input type="text">
																	</td>
																</tr>
															</table>
														</td>
														<!-- 查询表单查询按钮start -->
														<td width="30%">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="10" class="pm_mb3">
																<tr>
																	<td height="50" align="center">
																		<a href="#" class="easyui-linkbutton" icon="icon-cut"
																			onclick="javascript:reset()">重置</a>
																		<a href="#" class="easyui-linkbutton"
																			icon="icon-search" onclick="javascript:onSearch()">查询</a>
																	</td>
																</tr>
															</table>
														</td>
														<!-- 查询表单查询按钮end -->
													</tr>
												</table>
												<!-- 查询表单end -->

												<!-- 功能按钮start -->
												<table width="95%" border="0" align="center" cellpadding="0"
													cellspacing="1" class="pm_mb4">
													<tr>
														<td>
															<a href="#" class="easyui-linkbutton" icon="icon-add"
																onclick="javascript:toAdd()">新增</a>
															<a href="#" class="easyui-linkbutton" icon="icon-edit"
																onclick="javascript:toEdit()">编辑</a>
															<a href="#" class="easyui-linkbutton" icon="icon-remove"
																onclick="javascript:toDel()">删除</a>
														</td>
													</tr>
												</table>
												<!-- 功能按钮end -->

												<!-- 记录行标题与行start -->
												<table width="95%" border="0" align="center" cellpadding="0"
													cellspacing="1" class="pm_mb5">
													<tr>
														<td class="pm_wz3" width="5%">
															<input type="checkbox" name="ckAll"
																onclick="CheckAll(this.form)">
														</td>

														<#list table.columns as column> <#if !column.pk>
														<td class="pm_wz3"
															onclick="Sort('${column.columnNameLower}')"
															style="cursor:pointer">
															${column.columnNameLower}
														</td>
														</#if> </#list>
													</tr>

													<s:iterator value="datalist" status="index">
														<s:if test="#index.odd==true">
															<tr bgcolor="#f4f5fa">
														</s:if>
														<s:else>
															<tr bgcolor="#ffffff">
														</s:else>
													<#list table.columns as column> <#if column.pk>
														<td>
															<input type="checkbox" name="chkId"
																value="<s:property value='id'/>">
														</td>
															</#if> <#if !column.pk>
															<td>
															<s:property value="${column.columnNameLower}" />
														</td>
														</#if> </#list>
														</tr>
													</s:iterator>


												</table>
												<!-- 记录行标题与行start -->

												<!-- 翻页start -->
												<table width="95%" border="0" align="center" cellpadding="0"
													cellspacing="0" class="pm_mb6">
													<tr>
														<td width="4%" align="right">
															<s:include value="/common/page/Paging.jsp"></s:include>
														</td>
													</tr>
												</table>
												<!-- 翻页end -->
											</td>
										</tr>
									</table>
									<!-- 查询与记录与翻页end -->
								</td>
							</tr>
						</table>
				</td>
				</tr>
				<!-- 列表end -->
			</table>
		</s:form>
	</body>
</html>
<script language="javascript">
function toAdd(){
	document.forms[0].action="preAdd.action";
	document.forms[0].submit();
}
function CheckAll(form) {
	for (var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];
		if (e.name == "chkId")
			e.checked = form.ckAll.checked;
	}
}

function countid(form) {
	var j = 0;
    var l = 0;
    l = form.elements.length;
	for (var i = 0; i < l; i++) {
		var e = form.elements[i];
		if (e.name == "chkId" && e.checked == true) 
			j++;
	}
    return j;
}
function toDel(){
	
	if(countid(document.forms[0])==0){
		$.messager.alert('警告','请选择要删除的记录!','warning');
		return;
	}
	$.messager.confirm('提示信息', '您确认要删除记录吗?', function(data){
	if(data){
	document.forms[0].action="del.action";
	document.forms[0].submit();
	}
	});	
}
function onSearch(){
	document.forms[0].submit();
}
function reset(){
	
}
function toEdit(){
	if(countid(document.forms[0])==0){
		$.messager.alert('警告','请选择要更新的记录!','warning');
		return;
	}
	document.forms[0].action="preEdit.action";
	document.forms[0].submit();
}
</script>