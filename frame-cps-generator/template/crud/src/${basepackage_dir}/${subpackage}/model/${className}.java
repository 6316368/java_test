<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.model;

import javax.persistence.*;

//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.rdp.common.domain.BaseEntity;

@Entity
@Table(name = "${table.sqlName}")
<#if !table.compositeId>
	<#list table.columns as column>
		<#if column.pk>
@AttributeOverride(name = "id", column = @Column(name = "${column.sqlName}"))
		</#if>
	</#list>
</#if>
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${className} extends BaseEntity {
	
	<@generateFields/>
	<@generateConstructor className/>
	<@generateNotPkProperties/>
	<@generateJavaManyToOne/>
}

<#macro generateFields>

<#if table.compositeId>
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	//columns START
	<#list table.columns as column>
		<#if !column.pk>
			<#if !column.fk>
	private ${column.javaType} ${column.columnNameLower};
			</#if>
		</#if>
	</#list>

	<#list table.importedKeys.associatedTables?values as foreignKey>
		<#assign fkSqlTable = foreignKey.sqlTable>
		<#assign fkTable    = fkSqlTable.className>
		<#assign fkPojoClass = fkSqlTable.className>
		<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	private ${fkPojoClass} ${fkPojoClassVar};
	</#list>
	//columns END
	
</#if>

</#macro>


<#macro generateNotPkProperties>
	<#list table.columns as column>
		<#if !column.pk>
	@Column(name = "${column.sqlName}", unique = ${column.unique?string}, nullable = ${column.nullable?string}, insertable = true, updatable = true, length = ${column.size})
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
		</#if>
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
    </#list>
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

