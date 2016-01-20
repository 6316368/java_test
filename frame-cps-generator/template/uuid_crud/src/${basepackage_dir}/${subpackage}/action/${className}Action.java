<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.${subpackage}.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.rdp.common.token.Token;
import org.rdp.common.struts.ActionBase;
import ${basepackage}.${subpackage}.model.*;
import ${basepackage}.${subpackage}.services.*;
import org.rdp.common.page.PageRequest;
import org.rdp.common.page.PageResponse;


@Controller
@Scope("prototype")
public class ${className}Action extends ActionBase {
	// result mappings
	protected static final String LISTURL = "/modules/${subpackage}/${className}List.jsp";
	protected static final String ADDURL = "/modules/${subpackage}/${className}Add.jsp";
	protected static final String EDITURL = "/modules/${subpackage}/${className}Edit.jsp";
	// log
	private Logger log = Logger.getLogger(this.getClass());
	// attribute
	private ${className} ${classNameLower};
	private List<${className}> ${classNameLower}List;
	private String[] chkId;
	// spring IOC service
	private ${className}Services ${classNameLower}Services;

	// list
	public String list() {
		PageRequest pageRequest = this.bindPage();
		PageResponse<${className}> page = ${classNameLower}Services.findAll(pageRequest);
		this.getData(page);
		return LISTURL;
	}

	// preAdd
	public String preAdd() throws Exception {
		return ADDURL;
	}

	// save
	@Token
	public String save() throws Exception {
		${classNameLower}Services.save(${classNameLower});
		return list();
	}

	// del
	@Token
	public String del() throws Exception {
		${classNameLower}Services.del(chkId);
		return list();
	}

	// pre edit
	public String preEdit() throws Exception {
		${classNameLower} = ${classNameLower}Services.findById(chkId[0]);
		return EDITURL;
	}

	// edit
	public String edit() throws Exception {
		${classNameLower}Services.update(${classNameLower});
		return list();
	}

	public ${className} get${className}() {
		return ${classNameLower};
	}

	public void set${className}(${className} ${classNameLower}) {
		this.${classNameLower} = ${classNameLower};
	}

	public List<${className}> get${className}List() {
		return ${classNameLower}List;
	}

	public void set${className}List(List<${className}> ${classNameLower}List) {
		this.${classNameLower}List = ${classNameLower}List;
	}

	public String[] getChkId() {
		return chkId;
	}

	public void setChkId(String[] chkId) {
		this.chkId = chkId;
	}

	public ${className}Services get${className}Services() {
		return ${classNameLower}Services;
	}

	public void set${className}Services(${className}Services ${classNameLower}Services) {
		this.${classNameLower}Services = ${classNameLower}Services;
	}
}
