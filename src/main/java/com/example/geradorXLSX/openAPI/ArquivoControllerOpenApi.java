package com.example.geradorXLSX.openAPI;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Processamento")
public interface ArquivoControllerOpenApi {
	@Operation(summary = "Upload de arquivos para geração dos retornos em XLSX",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "byte[] = PK"
                                    )
                            })),
                    @ApiResponse(responseCode = "201", description = "OK", content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "byte[] = PK"
                                    )
                            })),
                    @ApiResponse(responseCode = "500", description = "Erro no sistema", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "500", description = "Erro no sistema", content = @Content(schema = @Schema(ref = "Problema"))),
                    @ApiResponse(responseCode = "400", description = "Erro ao processar o arquivo", content = @Content(schema = @Schema(ref = "Problema")))
            })
	 
	 ResponseEntity<?> processarArquivo(@RequestParam("file")  MultipartFile file) throws IOException;

}
