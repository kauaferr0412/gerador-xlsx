package com.example.geradorXLSX.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	
	public static String obterExtensao(String Arquivo) {
		return Arquivo.substring(Arquivo.lastIndexOf(".")).replace(".", "");
	}
	
	public static File criarArquivoTemporario(MultipartFile file) throws IOException {
		InputStream initialStream = file.getInputStream();
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);
		File targetFile = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\arquivo_processado." + obterExtensao(file.getOriginalFilename()));
		try (OutputStream outStream = new FileOutputStream(targetFile)) {
			outStream.write(buffer);
			outStream.close();
		}
		initialStream.close();
		return targetFile;
	}
	
	public static void deletarArquivosTemporario(List<String> arquivos) {
		for (int contador = 0; contador < arquivos.size(); contador++) {
			Path pathArquivosGerados = Paths.get(new FileSystemResource("").getFile().getAbsolutePath() + "\\" + arquivos.get(contador));
			try {
	            boolean result = Files.deleteIfExists(pathArquivosGerados);
	            if (result) {
	                System.out.println("Arquivo temporário deletado com sucesso.");
	            }
	            else {
	                System.out.println("Falha ao deletar arquivo temporário");
	            }
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }		
		}
	}
	
	public static String getName(String nome) {
		ResourceBundle rb =  ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
		return rb.getString(nome);
	}
	
	public static String converterStringToDate(String value) {
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
	        Date data = formatoData.parse(value);
	        return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}catch (Exception e) {
			return null;
			// TODO: handle exception
		}
		
	}
}
