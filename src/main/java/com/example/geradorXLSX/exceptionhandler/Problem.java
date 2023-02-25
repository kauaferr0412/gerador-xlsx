package com.example.geradorXLSX.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
@Schema(name = "Problema")
public class Problem {

	@Schema(example = "400")
	private Integer status;

	@Schema(example = "https://url/dados-invalidos%22")
    private String type;

	@Schema(example = "Dados inválidos")
	private String title;

	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String detail;

	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String userMessage;

	@Schema(example = "2022-07-15T11:21:50")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
}
