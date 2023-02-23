package com.example.geradorXLSX.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public String exportReport(List<?> lista, String pathArquivoJasper, String pathArquivoCabecalho, String pathArquivoSalvar) throws JRException, IOException {
		File file = ResourceUtils.getFile(pathArquivoJasper);
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("CABECALHO", pathArquivoCabecalho);
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
			File targetFile = new File(pathArquivoSalvar);
			try (OutputStream outStream = new FileOutputStream(targetFile)) {
				outStream.write(xlsReport.toByteArray());
				outStream.close();
			}
			return targetFile.getName();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathArquivoSalvar;
	}
}
