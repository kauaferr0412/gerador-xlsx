package com.example.geradorXLSX.conversor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.geradorXLSX.conversor.processamento.LayoutIdentify;

import jakarta.servlet.ServletContext;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class ArquivoController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 * @throws IOException
	 * 
	 */

	@Autowired
	ServletContext context;
	
	static final int TAMANHO_BUFFER = 4096; // 4kb

	@RequestMapping(value = "/processar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> processarArquivo(@RequestParam("file") MultipartFile file)throws IOException {
		byte[] bytes = LayoutIdentify.identify(converterMultipartFileToFile(file));
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=resultado_processamento.zip").contentType(MediaType.parseMediaType("application/zip")).body(bytes);
	}
	
	public String obterExtensao(String Arquivo) {
		return Arquivo.substring(Arquivo.lastIndexOf(".")).replace(".", "");
	}
	
	public File converterMultipartFileToFile(MultipartFile file) throws IOException {
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
}
