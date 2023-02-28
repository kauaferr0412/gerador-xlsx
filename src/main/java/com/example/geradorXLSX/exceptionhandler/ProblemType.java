package com.example.geradorXLSX.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	PARAMETRO_OBRIGATORIO("/parametro-obrigatorio", "Parâmetro obrigatorio"),
	ACESSO_NAO_AUTORIZADO("/acesso-nao-autorizado", "Acesso não autorizado"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	REQUISICAO_FALHA("/requisicao-ma-sucedida", "Requisição Falhou");

	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = path;
		this.title = title;
	}
}