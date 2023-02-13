package com.example.geradorXLSX.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro034;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@Service
public class JasperReportService {
	
	static final int TAMANHO_BUFFER = 4096; // 4kb
	private byte[] dados = new byte[TAMANHO_BUFFER];

	@SuppressWarnings("deprecation")
	public byte[] exportReport(List<?> lista,  List<String> arquivos) throws JRException, IOException {
		File file = ResourceUtils.getFile(new FileSystemResource("").getFile().getAbsolutePath() + "\\src\\main\\resources\\processamento_arquivo.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("CABECALHO", new FileSystemResource("").getFile().getAbsolutePath() + "\\src\\main\\resources\\cabecalho_report_old.jpg");
		JasperPrint print = JasperFillManager.fillReport(jasper, parameters, dataSource);
		try (ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()) {
				final JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
				exporter.setParameter(JRXlsAbstractExporterParameter.MAXIMUM_ROWS_PER_SHEET, 1048576);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
				exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
				exporter.exportReport();
				File targetFile = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\034_Ordem de Crédito.xlsx");
				try (OutputStream outStream = new FileOutputStream(targetFile)) {
					outStream.write(xlsReport.toByteArray());
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return gerarBytesZip(arquivos);
	}
	
	public byte[] gerarBytesZip(List<String> arquivos) throws IOException {
		int cont;
		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		FileOutputStream destino = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;
		try {
			destino = new FileOutputStream(new FileSystemResource("").getFile().getAbsolutePath() + "\\jasper.zip");
			saida = new ZipOutputStream(new BufferedOutputStream(destino));
			for (int contador = 0; contador < arquivos.size(); contador++) {
				File fileTemp = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\" + arquivos.get(contador));
				streamDeEntrada = new FileInputStream(fileTemp);
				origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
				entry = new ZipEntry(fileTemp.getName());
				saida.putNextEntry(entry);
				while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
					saida.write(dados, 0, cont);
					
				}
				origem.close();
				streamDeEntrada.close();
				origem = null;
				streamDeEntrada = null;
			}
			saida.close();
			destino.close();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		var fileTemp = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\jasper.zip");
		var bytesFileTemp = FileUtils.readFileToByteArray(fileTemp);
		fileTemp.delete();
		return bytesFileTemp;
	}
}
