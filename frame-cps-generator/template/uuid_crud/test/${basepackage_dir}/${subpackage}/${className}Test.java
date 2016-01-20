<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package test.${basepackage}.${subpackage};

import org.rdp.common.test.TestBaseNoTransactional;
import ${basepackage}.${subpackage}.services.${className}Services;

public class ${className}Test extends TestBaseNoTransactional{
	
	private ${className}Services ${classNameLower}Services;
	public void set${className}Services(${className}Services ${classNameLower}Services) {
		this.${classNameLower}Services = ${classNameLower}Services;
	}
	
	public void testQuery(){
		${classNameLower}Services.findAll();
	}
	
}