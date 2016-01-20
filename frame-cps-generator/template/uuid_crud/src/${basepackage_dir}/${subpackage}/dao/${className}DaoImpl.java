<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${subpackage}.dao;


import org.springframework.stereotype.Component;

import org.rdp.common.dao.DefaultBaseDaoUUIDImpl;
import ${basepackage}.${subpackage}.model.*;

@Component
public class ${className}DaoImpl extends DefaultBaseDaoUUIDImpl<${className}>
	implements ${className}Dao {

	public Class getEntityClass() {
		return ${className}.class;
	}

}
