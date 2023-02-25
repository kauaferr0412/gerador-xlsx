package com.example.geradorXLSX.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class JasperReportService {

	private static Logger logger = Logger.getLogger(JasperReportService.class);
	static final String PATH_CABECALHO = new FileSystemResource("").getFile().getAbsolutePath() + "\\src\\main\\resources\\cabecalho_report_old.jpg";
	static final String CABECALHO = "CABECALHO";

	public String exportReport(List<?> lista, String pathArquivoJasper, String pathArquivoSalvar) throws JRException, IOException {
		File targetFile = new File(pathArquivoSalvar);
		logger.info("GERANDO ARQUIVO: " + targetFile.getName().replace("\\", ""));
		File file = ResourceUtils.getFile(pathArquivoJasper);
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(CABECALHO, PATH_CABECALHO);
		JasperPrint print = JasperFillManager.fillReport(jasper, parameters, dataSource);
		try (ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()) {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(true);
			configuration.setMaxRowsPerSheet(1048576);
			configuration.setOnePagePerSheet(false);
			configuration.setRemoveEmptySpaceBetweenColumns(true);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setWhitePageBackground(false);
			configuration.setIgnoreGraphics(false);
			configuration.setIgnoreCellBorder(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			try (OutputStream outStream = new FileOutputStream(targetFile)) {
				outStream.write(xlsReport.toByteArray());
				outStream.close();
			}
			logger.info("ARQUIVO GERADO COM SUCESSO");
			return targetFile.getName();
		} catch (Exception e) {
			logger.info("ERRO AO PROCESSAR O ARQUIVO: " + targetFile.getName().replace("\\", ""));
			logger.error(e.getMessage());
		}
		return targetFile.getName();
	}
}
