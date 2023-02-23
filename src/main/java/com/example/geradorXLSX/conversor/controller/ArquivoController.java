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
import com.example.geradorXLSX.util.Util;

@RestController
@RequestMapping(path = "/v1/")
public class ArquivoController implements ArquivoControllerOpenApi {
	
	static final int TAMANHO_BUFFER = 4096; // 4kb

	@RequestMapping(value = "/processar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> processarArquivo(@RequestParam("file") MultipartFile file) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=resultado_processamento.zip")
				.contentType(MediaType.parseMediaType("application/zip")).body(LayoutIdentify.identify(Util.criarArquivoTemporario(file)));
	}
}
