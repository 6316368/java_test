package org.rdp.generator.model.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import org.rdp.generator.model.IModel;

/**
 * 
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class JavaMethod implements IModel {
	private JavaClass clazz;
	private String methodName;
	private MethodParameter returnType;
	private List<MethodParameter> parameters = new ArrayList<MethodParameter>();

	public JavaClass getClazz() {
		return clazz;
	}

	public void setClazz(JavaClass javaClass) {
		this.clazz = javaClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public MethodParameter getReturnType() {
		return returnType;
	}

	public void setReturnType(MethodParameter returnValue) {
		this.returnType = returnValue;
	}

	public List<MethodParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<MethodParameter> parameters) {
		this.parameters = parameters;
	}

	public String getDisplayDescription() {
		return "java method:" + getMethodName();
	}

	public String getTemplateModelName() {
		return "method";
	}

	public void mergeFilePathModel(Map fileModel) throws Exception {
		fileModel.putAll(BeanUtils.describe(this));
		fileModel.putAll(BeanUtils.describe(getClazz()));
	}

	public String getMethodNameUpper() {
		return Character.toUpperCase(getMethodName().charAt(0))
				+ getMethodName().substring(1);
	}

}
