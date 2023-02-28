package com.example.geradorXLSX.conversor.rede.models.eefi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro034 {
	private String tipo_registro;
	private String num_pv_centralizador;
	private String num_documento;
	private String data_lancamento;
	private String valor_lancamento;
	private String credito;
	private String banco;
	private String agencia;
	private String conta;
	private String data_movimento;
	private String num_rv;
	private String data_rv;
	private String bandeira;
	private String tipo_de_transacao;
	private String valor_bruto_rv;
	private String valor_taxa_desconto;
	private String num_parcel_total;
	private String status_credito;
	private String num_pv_original;
	
}
