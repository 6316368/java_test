package org.rdp.generator;

public class GeneratorClient {
	public static void main(String[] args) throws Exception {
		RdpGenerator pg = new RdpGenerator();
		pg.clean();
		//pg.generateCRUDByTable("adress_tree");		//这种方法用于表索引是整数的代码生成
		String  table="pub_employee";
		pg.generateUUIDCRUDByTable(table.toUpperCase());	//用于索引是uuid的代码生成
	}
}
