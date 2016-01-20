<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.services;

import java.util.List;

import java.lang.String;

import org.rdp.common.page.PageRequest;
import org.rdp.common.page.PageResponse;

import ${basepackage}.${subpackage}.model.*;
import org.rdp.common.domain.QueryCondition;

public interface ${className}Services{
	public ${className} create(${className} ${classNameLower});
	
	public ${className} update(${className} ${classNameLower});
	
	public ${className} save(${className} ${classNameLower});
	
	public void del(String id);
	public void del(String[] ids);
	
	public List<${className}> findAll();
	
	public PageResponse<${className}> findAll(PageRequest pageRequest);
	
	public ${className} findById(String id);
	
	public PageResponse<${className}> findPageByListQuery(List<QueryCondition> queryConditions, PageRequest pageRequest);
	public PageResponse<${className}> findPageByListQuery(List<QueryCondition> queryConditions, String orderHql, PageRequest pageRequest);
	
	public List<${className}> findListByListQueryCondition(List<QueryCondition> queryConditions);
	public List<${className}> findListByListQueryCondition(List<QueryCondition> queryConditions, String orderHql);
}
