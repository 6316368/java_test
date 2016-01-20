<#include "/jsp_imports.include"> <#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>add</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ui/themes/icon.css">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ui/jquery-1.4.2.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ui/easyloader.js"></script>
	</head>
	<body>
		<s:form name="${className}" action="${classNameLower}/save.action"
			method="post" theme="simple">
			<s:token/>
			<table width="100%" border="0" cellspacing="0" cellpadding="1"
				class="user_mb" height="100%">

				<tr>
					<td background="<%=request.getContextPath()%>/images/privacy_bg.gif" height="27">
						<table width="93" height="25" border="0" cellpadding="0"
							cellspacing="0" height="100%">
							<tr>
								<td background="<%=request.getContextPath()%>/images/privacy_icon.gif"
									class="user_wz">
									${classNameLower}
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0" class="user_mb2" height="100%">
							<tr>
								<td>
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0" class="user_mb3" height="100%">
										<tr>
											<td>
												<table width="98%" border="0" align="center" cellpadding="0"
													cellspacing="25" class="user_mb4" height="96%">
													<tr>
														<td>
															<!-- 添加表单区域，可以直接放一个table-->
															<table border="0" cellspacing="0" cellpadding="5"
																align="center" width="100%">

																<#list table.columns as column> <#if !column.pk>
																<tr>
																	<td align="right" class="pm_wz4">
																		${column.columnNameLower}:
																	</td>
																	<td align="left">
																		<input type="text"
																			name="${classNameLower}.${column.columnNameLower}">
																	</td>
																</tr>
																</#if> </#list>
																<tr>
																	<td width="25%" height="50"></td>
																	<td colspan="3">
																	<a href="#" class="easyui-linkbutton" icon="icon-save"
																			onclick="javascript:toSubmit()">确定</a>
																		<a href="#" class="easyui-linkbutton"
																			icon="icon-cancel" onclick="javascript:toCancel()">取消</a>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
	<script language="javascript">
		function toSubmit(){
			document.forms[0].submit();
		}
		function toCancel(form){
			document.forms[0].action="list.action";
			document.forms[0].submit();
		}
		</script>
</html>
