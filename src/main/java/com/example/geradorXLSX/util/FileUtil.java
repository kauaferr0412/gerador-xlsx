package com.example.geradorXLSX.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static Logger logger = Logger.getLogger(FileUtil.class);
	static final int TAMANHO_BUFFER = 4096; // 4kb
	private static final byte[] DADOS = new byte[TAMANHO_BUFFER];

	public static void deletarArquivosTemporario(List<String> arquivos) {
		logger.info("DELETANDO ARQUIVOS TEMPORÁRIOS..");
		try {
			for (int contador = 0; contador < arquivos.size(); contador++) {
				Path pathArquivosGerados = Paths.get(new FileSystemResource("").getFile().getAbsolutePath() + "\\" + arquivos.get(contador));
				Files.deleteIfExists(pathArquivosGerados);
			}
			logger.info("ARQUIVOS TEMPORÁRIOS DELETADOS");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File criarArquivoTemporario(MultipartFile file) throws IOException {
		InputStream initialStream = file.getInputStream();
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);
		File targetFile = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\arquivo_processado."
				+ obterExtensao(file.getOriginalFilename()));
		try (OutputStream outStream = new FileOutputStream(targetFile)) {
			outStream.write(buffer);
			outStream.close();
		}
		initialStream.close();
		return targetFile;
	}

	public static String obterExtensao(String Arquivo) {
		return Arquivo.substring(Arquivo.lastIndexOf(".")).replace(".", "");
	}

	public static byte[] gerarBytesZip(List<String> arquivos, String nomeArquivoTempZip) throws IOException {
		int cont;
		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		FileOutputStream destino = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;
		try {
			destino = new FileOutputStream(new FileSystemResource("").getFile().getAbsolutePath() + nomeArquivoTempZip);
			saida = new ZipOutputStream(new BufferedOutputStream(destino));
			for (int contador = 0; contador < arquivos.size(); contador++) {
				File fileTemp = new File(
						new FileSystemResource("").getFile().getAbsolutePath() + "\\" + arquivos.get(contador));
				streamDeEntrada = new FileInputStream(fileTemp);
				origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
				entry = new ZipEntry(fileTemp.getName());
				saida.putNextEntry(entry);
				while ((cont = origem.read(DADOS, 0, TAMANHO_BUFFER)) != -1) {
					saida.write(DADOS, 0, cont);
				}
				origem.close();
				streamDeEntrada.close();
				origem = null;
				streamDeEntrada = null;
			}
			saida.close();
			destino.close();
			saida = null;
			destino = null;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		var fileTemp = new File(new FileSystemResource("").getFile().getAbsolutePath() + nomeArquivoTempZip);
		var bytesFileTemp = FileUtils.readFileToByteArray(fileTemp);
		fileTemp.delete();
		return bytesFileTemp;
	}
}
