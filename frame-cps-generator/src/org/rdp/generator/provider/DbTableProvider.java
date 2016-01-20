package org.rdp.generator.provider;

import org.rdp.generator.IModelProvider;
import org.rdp.generator.model.IModel;

/**
 * 
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class DbTableProvider implements IModelProvider {

	private String dbTableName;

	public DbTableProvider(String dbTableName) {
		super();
		this.dbTableName = dbTableName;
	}

	public IModel getModel() throws Exception {
		return DbModelHelper.getInstance().getTable(dbTableName);
	}

}
