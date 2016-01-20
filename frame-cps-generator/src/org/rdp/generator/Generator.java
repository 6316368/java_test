package org.rdp.generator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import org.rdp.generator.model.IModel;
import org.rdp.generator.utils.FileHelper;
import org.rdp.generator.utils.IOHelper;
import org.rdp.generator.utils.PropertiesProvider;
import org.rdp.generator.utils.StringTemplate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class Generator {
	private static final String WEBAPP_GENERATOR_INSERT_LOCATION = "webapp-generator-insert-location";
	public File templateRootDir =  new File("template/CRUDTemplate").getAbsoluteFile();
	public IModelProvider modelProvider = null;
	
	public Generator() {
	}

	public void generateByModelProvider() throws Exception {
		generateByModel(modelProvider.getModel());
	}
	
	private void generateByModel(IModel model) throws Exception {
		System.out.println("***************************************************************");
		System.out.println("* BEGIN generate model:" + model.getDisplayDescription());
		System.out.println("***************************************************************");
		
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(templateRootDir);
		config.setNumberFormat("###############");
		config.setBooleanFormat("true,false");

		List templateFiles = new ArrayList();
		FileHelper.listFiles(templateRootDir, templateFiles);

		for (int i = 0; i < templateFiles.size(); i++) {
			File templateFile = (File) templateFiles.get(i);
			String templateRelativePath = FileHelper.getRelativePath(
					templateRootDir, templateFile);
			String outputFilePath = templateRelativePath;
			if (templateFile.isDirectory() || templateFile.isHidden())
				continue;
			if (templateRelativePath.trim().equals(""))
				continue;
			if (templateFile.getName().toLowerCase().endsWith(".include")) {
				System.out.println("[skip]\t\t endsWith '.include' template:" + templateRelativePath);
				continue;
			}
			int testExpressionIndex = -1;
			if ((testExpressionIndex = templateRelativePath.indexOf('@')) != -1) {
				outputFilePath = templateRelativePath.substring(0,
						testExpressionIndex);
				String testExpressionKey = templateRelativePath
						.substring(testExpressionIndex + 1);
				Map map = getFilepathDataModel(model);
				Object expressionValue = map.get(testExpressionKey);
				if (!"true".equals(expressionValue.toString())) {
					System.out.println("[not-generate]\t test expression '@"
							+ testExpressionKey + "' is false,template:" + templateRelativePath);
					continue;
				}
			}
			try {
				generateFile(model, config, templateRelativePath,
						outputFilePath);
			} catch (Exception e) {
				throw new RuntimeException("generate model '"
						+ model.getDisplayDescription() + "' oucur error,template is:"
						+ templateRelativePath, e);
			}
		}
	}

	private void generateFile(IModel model, Configuration config,
			String templateRelativePath, String outputFilePath)
			throws Exception {
		Template template = config.getTemplate(templateRelativePath);

		String targetFilename = getTargetFilename(model, outputFilePath);

		Map templateDataModel = getTemplateDataModel(model);
		File absoluteOutputFilePath = getAbsoluteOutputFilePath(targetFilename);
		if (absoluteOutputFilePath.exists()) {
			StringWriter newFileContentCollector = new StringWriter();
			if (isFoundInsertLocation(template, templateDataModel,
					absoluteOutputFilePath, newFileContentCollector)) {
				System.out.println("[insert]\t generate content into:"
						+ targetFilename);
				IOHelper.saveFile(absoluteOutputFilePath,
						newFileContentCollector.toString());
				return;
			}
		}

		System.out.println("[generate]\t template:" + templateRelativePath
				+ " to " + targetFilename);
		saveNewOutputFileContent(template, templateDataModel,
				absoluteOutputFilePath);
	}

	private String getTargetFilename(IModel model, String templateFilepath)
			throws Exception {
		Map fileModel = getFilepathDataModel(model);
		String targetFilename = new StringTemplate(templateFilepath, fileModel).toString();
		return targetFilename;
	}

	/**
	 * 得到生成"文件目录/文件路径"的Model
	 */
	private Map getFilepathDataModel(IModel model)
			throws Exception {
//		Map fileModel = BeanUtils.describe(table);
		Map fileModel = new HashMap();
		fileModel.putAll(PropertiesProvider.getProperties());
		model.mergeFilePathModel(fileModel);
		return fileModel;
	}

	/**
	 * 得到FreeMarker的Model
	 */
	private Map getTemplateDataModel(IModel model) {
		Map map = new HashMap();
		map.putAll(PropertiesProvider.getProperties());
		map.put(model.getTemplateModelName(), model);
		return map;
	}

	private File getAbsoluteOutputFilePath(String targetFilename) {
		String outRoot = getOutRootDir();
		File outputFile = new File(outRoot, targetFilename);
		outputFile.getParentFile().mkdirs();
		return outputFile;
	}

	private boolean isFoundInsertLocation(Template template, Map model,
			File outputFile, StringWriter newFileContent) throws IOException,
			TemplateException {
		LineNumberReader reader = new LineNumberReader(new FileReader(
				outputFile));
		String line = null;
		boolean isFoundInsertLocation = false;

		PrintWriter writer = new PrintWriter(newFileContent);
		while ((line = reader.readLine()) != null) {
			writer.println(line);
			// only insert once
			if (!isFoundInsertLocation
					&& line.indexOf(WEBAPP_GENERATOR_INSERT_LOCATION) >= 0) {
				template.process(model, writer);
				writer.println();
				isFoundInsertLocation = true;
			}
		}

		writer.close();
		reader.close();
		return isFoundInsertLocation;
	}

	private void saveNewOutputFileContent(Template template, Map model,
			File outputFile) throws IOException, TemplateException {
		FileWriter out = new FileWriter(outputFile);
		template.process(model, out);
		out.close();
	}

	public void clean() throws IOException {
		String outRoot = getOutRootDir();
		FileUtils.deleteDirectory(new File(outRoot));
		System.out.println("[Delete Dir]	" + outRoot);
	}

	private String getOutRootDir() {
		return PropertiesProvider.getProperties().getProperty("outRoot");
	}

}
