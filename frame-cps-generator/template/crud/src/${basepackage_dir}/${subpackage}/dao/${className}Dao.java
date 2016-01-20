<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${subpackage}.dao;


import org.rdp.common.dao.BaseDao;
import ${basepackage}.${subpackage}.model.*;

public interface ${className}Dao extends BaseDao<${className}>{

}
