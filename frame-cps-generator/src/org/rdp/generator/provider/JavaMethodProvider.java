package org.rdp.generator.provider;

import org.rdp.generator.IModelProvider;
import org.rdp.generator.model.IModel;

/**
 * 
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class JavaMethodProvider implements IModelProvider {

	private String methodName;
	private Class clazz;

	public JavaMethodProvider(Class clazz, String methodName) {
		super();
		this.clazz = clazz;
		this.methodName = methodName;
	}

	public IModel getModel() {
		return JavaModelHelper.getInstance()
				.createJavaMethod(clazz, methodName);
	}

}
