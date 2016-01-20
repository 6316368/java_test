<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${subpackage}.dao;


import org.rdp.common.dao.BaseDaoUUID;
import ${basepackage}.${subpackage}.model.*;

public interface ${className}Dao extends BaseDaoUUID<${className}>{

}
