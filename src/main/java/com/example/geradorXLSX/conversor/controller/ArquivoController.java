package com.example.geradorXLSX.conversor.controller;

import java.io.IOException;

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
import com.example.geradorXLSX.openAPI.ArquivoControllerOpenApi;

@RestController
@RequestMapping(path = "/v1/")
public class ArquivoController implements ArquivoControllerOpenApi {

	private static final String ATTACHMENT_FILE = "attachment;filename=resultado_processamento.zip";
	
	@RequestMapping(value = "/gerarXlsx", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> processarArquivo(@RequestParam("file") MultipartFile file) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILE)
				.contentType(MediaType.parseMediaType("application/zip")).body(LayoutIdentify.processarArquivos(file));
	}
}
