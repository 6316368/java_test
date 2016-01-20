package org.rdp.generator;

import org.rdp.generator.model.IModel;

/**
 * 提供template数据模型
 * 
 */
public interface IModelProvider {

	public IModel getModel() throws Exception;

}
