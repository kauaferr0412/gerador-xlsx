package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro047;

public class MontarPlanilha047 {

	private static Logger logger = Logger.getLogger(MontarPlanilha047.class);

	public void criarArquivo047(final String nomeArquivo, final List<Registro047> registros47) {

		logger.info("GERANDO ARQUIVO: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 047");

			int numeroDaLinha = 0;

			adicionarCabecalho047(planilha, numeroDaLinha++);

			for (Registro047 registro047 : registros47) {

				var linha = planilha.createRow(numeroDaLinha++);
				adicionarCelula047(linha, 0, registro047.tipo_registro);
				adicionarCelula047(linha, 1, registro047.num_EC);
				adicionarCelula047(linha, 2, registro047.num_OC_ref);
				adicionarCelula047(linha, 3, registro047.valor_OC_ref);
				adicionarCelula047(linha, 4, registro047.data_OC_ref);
				adicionarCelula047(linha, 5, registro047.num_EC_orig_venda);
				adicionarCelula047(linha, 6, registro047.num_rv);
				adicionarCelula047(linha, 7, registro047.data_venda_RV);
				adicionarCelula047(linha, 8, registro047.transact_parcel_tipo);
				adicionarCelula047(linha, 9, registro047.code_band);
				adicionarCelula047(linha, 10, registro047.negociacao_tipo);
				adicionarCelula047(linha, 11, registro047.contrato_number);
				adicionarCelula047(linha, 12, registro047.cnpj_parceiro);
				adicionarCelula047(linha, 13, registro047.num_doc_OC_neg);
				adicionarCelula047(linha, 14, registro047.valor_negoc);
				adicionarCelula047(linha, 15, registro047.data_negociacao);
				adicionarCelula047(linha, 16, registro047.data_liquidacao);
				adicionarCelula047(linha, 17, registro047.bank_number);
				adicionarCelula047(linha, 18, registro047.bank_branch_number);
				adicionarCelula047(linha, 19, registro047.bank_account_number);
				adicionarCelula047(linha, 20, registro047.status_credito);
				adicionarCelula047(linha, 21, registro047.parcela_numero);

			}
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			logger.error("ARQUIVO N??O ENCONTRADO: " + nomeArquivo);
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error("ERRO AO PROCESSAR O ARQUIVO: " + nomeArquivo);
			logger.error(e.getMessage());
		}
		logger.info("ARQUIVO GERADO COM SUCESSO");
	}

	private void adicionarCabecalho047(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula047(linha, 0, "Tipo do registro");
		adicionarCelula047(linha, 1, "N??mero do Estabelecimento");
		adicionarCelula047(linha, 2, "N??mero do Documento OC ??? Refer??ncia");
		adicionarCelula047(linha, 3, "Valor da OC ??? Refer??ncia");
		adicionarCelula047(linha, 4, "Data do lan??amento OC");
		adicionarCelula047(linha, 5, "N??mero do estabelecimento original da venda");
		adicionarCelula047(linha, 6, "N??mero do Resumo de Venda");
		adicionarCelula047(linha, 7, "Data da Venda do RV");
		adicionarCelula047(linha, 8, "Identifica transa????es ?? vista, parceladas etc.");
		adicionarCelula047(linha, 9, "C??digo da Bandeira");
		adicionarCelula047(linha, 10, "Tipo da Negocia????o - Cess??o (1) e ou Gravame (2)");
		adicionarCelula047(linha, 11, "N??mero do Contrato de Negocia????o");
		adicionarCelula047(linha, 12, "N??mero do CNPJ Parceiro");
		adicionarCelula047(linha, 13, "N??mero do Documento OC gerado na negocia????o");
		adicionarCelula047(linha, 14, "Valor Negociado");
		adicionarCelula047(linha, 15, "Data da Negocia????o");
		adicionarCelula047(linha, 16, "Data da liquida????o");
		adicionarCelula047(linha, 17, "Banco");
		adicionarCelula047(linha, 18, "Ag??ncia");
		adicionarCelula047(linha, 19, "Conta");
		adicionarCelula047(linha, 20, "Status do cr??dito ");
		adicionarCelula047(linha, 21, "Parcela");
	}

	private void adicionarCelula047(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
