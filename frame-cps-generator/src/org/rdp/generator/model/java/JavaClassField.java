package org.rdp.generator.model.java;

import org.rdp.generator.utils.JavaDataTypesUtils;

/**
 * 
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class JavaClassField {
	private JavaClass classDto;
	private String javaType;
	private String fieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public JavaClass getClassDto() {
		return classDto;
	}

	public void setClassDto(JavaClass classDto) {
		this.classDto = classDto;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getAsType() {
		return JavaDataTypesUtils.getPreferredAsType(javaType);
	}

	public boolean getIsDateTimeField() {
		return javaType.equalsIgnoreCase("java.sql.Date")
				|| javaType.equalsIgnoreCase("java.util.Date");
	}

}
