<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.services;

import java.util.*;

import org.springframework.stereotype.Component;
import org.rdp.common.page.PageRequest;
import org.rdp.common.page.PageResponse;
import ${basepackage}.${subpackage}.dao.*;
import ${basepackage}.${subpackage}.model.*;
import org.rdp.common.domain.QueryCondition;

@Component
public class ${className}ServicesImpl implements ${className}Services{

	private ${className}Dao ${classNameLower}Dao;
	/**通过spring注入${className}Dao*/
	public void set${className}Dao(${className}Dao dao) {
		this.${classNameLower}Dao = dao;
	}
	
	
	public ${className} create(${className} ${classNameLower}){
		return ${classNameLower}Dao.insert(${classNameLower});
	}
	
	public ${className} update(${className} ${classNameLower}){
		return ${classNameLower}Dao.update(${classNameLower});
	}
	
	public ${className} save(${className} ${classNameLower}){
		if(${classNameLower}.getId() == null ){
			return create(${classNameLower});
		}else{
			return update(${classNameLower});
		}
	}
	
	public void del(String id){
		${classNameLower}Dao.delete(id);
	}
	
	public void del(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			this.del(ids[i]);
		}
	}
	
	public List<${className}> findAll(){
		return ${classNameLower}Dao.findAll();
	}
	
	public PageResponse<${className}> findAll(PageRequest pageRequest){
		return ${classNameLower}Dao.findAll(pageRequest);
	}
	
	public ${className} findById(String id){
		return ${classNameLower}Dao.findById(id);
	}
	
	public PageResponse<${className}> findPageByListQuery(List<QueryCondition> queryConditions, PageRequest pageRequest){
		return ${classNameLower}Dao.query(queryConditions, pageRequest);
	}
	public PageResponse<${className}> findPageByListQuery(List<QueryCondition> queryConditions, String orderHql, PageRequest pageRequest){
		return ${classNameLower}Dao.query(queryConditions, orderHql, pageRequest);
	}
	public List<${className}> findListByListQueryCondition(List<QueryCondition> queryConditions){
		return ${classNameLower}Dao.query(queryConditions);
	}
	public List<${className}> findListByListQueryCondition(List<QueryCondition> queryConditions, String orderHql){
		return ${classNameLower}Dao.query(queryConditions, orderHql);
	}
}
