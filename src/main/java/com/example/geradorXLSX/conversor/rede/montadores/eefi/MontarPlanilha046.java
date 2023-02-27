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

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro046;

public class MontarPlanilha046 {

	private static Logger logger = Logger.getLogger(MontarPlanilha046.class);

	public void criarArquivo046(final String nomeArquivo, final List<Registro046> registros46) {

		logger.info("GERANDO ARQUIVO: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 046");
			int numeroDaLinha = 0;
			adicionarCabecalho046(planilha, numeroDaLinha++);
			for (Registro046 registro046 : registros46) {
				var linha = planilha.createRow(numeroDaLinha++);
				adicionarCelula046(linha, 0, registro046.tipo_registro);
				adicionarCelula046(linha, 1, registro046.num_EC);
				adicionarCelula046(linha, 2, registro046.num_OC_ref);
				adicionarCelula046(linha, 3, registro046.valor_OC_ref);
				adicionarCelula046(linha, 4, registro046.data_OC_ref);
				adicionarCelula046(linha, 5, registro046.num_EC_orig_venda);
				adicionarCelula046(linha, 6, registro046.num_rv);
				adicionarCelula046(linha, 7, registro046.data_venda_RV);
				adicionarCelula046(linha, 8, registro046.transact_parcel_tipo);
				adicionarCelula046(linha, 9, registro046.code_band);
				adicionarCelula046(linha, 10, registro046.negociacao_tipo);
				adicionarCelula046(linha, 11, registro046.contrato_number);
				adicionarCelula046(linha, 12, registro046.cnpj_parceiro);
				adicionarCelula046(linha, 13, registro046.num_doc_OC_neg);
				adicionarCelula046(linha, 14, registro046.valor_negoc);
				adicionarCelula046(linha, 15, registro046.data_negociacao);
				adicionarCelula046(linha, 16, registro046.data_liquidacao);
				adicionarCelula046(linha, 17, registro046.bank_number);
				adicionarCelula046(linha, 18, registro046.bank_branch_number);
				adicionarCelula046(linha, 19, registro046.bank_account_number);
				adicionarCelula046(linha, 20, registro046.status_credito);
				adicionarCelula046(linha, 21, registro046.parcela_numero);

			}
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			logger.error("ARQUIVO NÃO ENCONTRADO: " + nomeArquivo);
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error("ERRO AO PROCESSAR O ARQUIVO: " + nomeArquivo);
			logger.error(e.getMessage());
		}
		logger.info("ARQUIVO GERADO COM SUCESSO");
	}

	private void adicionarCabecalho046(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula046(linha, 0, "Tipo do registro");
		adicionarCelula046(linha, 1, "Número do Estabelecimento");
		adicionarCelula046(linha, 2, "Número do Documento OC – Referência");
		adicionarCelula046(linha, 3, "Valor da OC – Referência");
		adicionarCelula046(linha, 4, "Data do lançamento OC");
		adicionarCelula046(linha, 5, "Número do estabelecimento original da venda");
		adicionarCelula046(linha, 6, "Número do Resumo de Venda");
		adicionarCelula046(linha, 7, "Data da Venda do RV");
		adicionarCelula046(linha, 8, "Identifica transações à vista, parceladas etc.");
		adicionarCelula046(linha, 9, "Código da Bandeira");
		adicionarCelula046(linha, 10, "Tipo da Negociação - Cessão (1) e ou Gravame (2)");
		adicionarCelula046(linha, 11, "Número do Contrato de Negociação");
		adicionarCelula046(linha, 12, "Número do CNPJ Parceiro");
		adicionarCelula046(linha, 13, "Número do Documento OC gerado na negociação");
		adicionarCelula046(linha, 14, "Valor Negociado");
		adicionarCelula046(linha, 15, "Data da Negociação");
		adicionarCelula046(linha, 16, "Data da liquidação");
		adicionarCelula046(linha, 17, "Banco");
		adicionarCelula046(linha, 18, "Agência");
		adicionarCelula046(linha, 19, "Conta");
		adicionarCelula046(linha, 20, "Status do crédito ");
		adicionarCelula046(linha, 21, "Parcela");
	}

	private void adicionarCelula046(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
