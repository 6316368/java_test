package org.rdp.generator.provider;

import org.rdp.generator.IModelProvider;
import org.rdp.generator.model.IModel;

/**
 * 
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class JavaClassProvider implements IModelProvider {

	private Class clazz;

	public JavaClassProvider(Class clazz) {
		super();
		this.clazz = clazz;
	}

	public IModel getModel() {
		return JavaModelHelper.getInstance().createJaveClass(clazz);
	}

}
