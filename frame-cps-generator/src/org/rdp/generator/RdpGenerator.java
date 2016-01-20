package org.rdp.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.rdp.generator.model.db.Table;
import org.rdp.generator.provider.DbModelHelper;
import org.rdp.generator.provider.DbTableProvider;


/**
 * @author Linlin Yu
 * @email yulinlincom(a)gmail.com
 */
public class RdpGenerator {

	Generator g = new Generator();
	public void clean() throws IOException{
		g.clean();
	}
	
	public void generateCRUDByTable(String tableName) throws Exception {
		g.templateRootDir = new File("template/crud").getAbsoluteFile();
		g.modelProvider =  new DbTableProvider(tableName);
		g.generateByModelProvider();
	}
	
	public void generateUUIDCRUDByTable(String tableName) throws Exception {
		g.templateRootDir = new File("template/uuid_crud").getAbsoluteFile();
		g.modelProvider = new DbTableProvider(tableName);
		g.generateByModelProvider();
	}

	public void generateCRUDByAllTable() throws Exception {
		g.templateRootDir = new File("template/crud").getAbsoluteFile();
		List<Table> allTable = DbModelHelper.getInstance().getAllTables();
		for(Table t : allTable) {
			g.modelProvider = new DbTableProvider(t.getSqlName());
			g.generateByModelProvider();
		}
	}
}
