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

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro048;

public class MontarPlanilha048 {

	private static Logger logger = Logger.getLogger(MontarPlanilha048.class);

	public void criarArquivo048(final String nomeArquivo, final List<Registro048> registros048) {

		logger.info("GERANDO O ARQUIVO: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 048");

			int numeroDaLinha = 0;

			adicionarCabecalho048(planilha, numeroDaLinha++);

			for (Registro048 registro048 : registros048) {

				var linha = planilha.createRow(numeroDaLinha++);

				adicionarCelula048(linha, 0, registro048.tipoRegistro);
				adicionarCelula048(linha, 1, registro048.numEstabelecimento);
				adicionarCelula048(linha, 2, registro048.numDocumentoOc);
				adicionarCelula048(linha, 3, registro048.valorOcReferencia);
				adicionarCelula048(linha, 4, registro048.dataLancamentoOc);
				adicionarCelula048(linha, 5, registro048.numEcOriginalVenda);
				adicionarCelula048(linha, 6, registro048.numRV);
				adicionarCelula048(linha, 7, registro048.dataVendaDoRV);
				adicionarCelula048(linha, 8, registro048.identificaTransacaoAvistaParcelada);
				adicionarCelula048(linha, 9, registro048.codigoBandeira);
				adicionarCelula048(linha, 10, registro048.tipoNegociacao);
				adicionarCelula048(linha, 11, registro048.numeroContratoNegociacao);
				adicionarCelula048(linha, 12, registro048.numCnpjParceiro);
				adicionarCelula048(linha, 13, registro048.numDocOCGeradoNegoc);
				adicionarCelula048(linha, 14, registro048.valorNegociado);
				adicionarCelula048(linha, 15, registro048.dataNegociacao);
				adicionarCelula048(linha, 16, registro048.dataLiquidacao);
				adicionarCelula048(linha, 17, registro048.banco);
				adicionarCelula048(linha, 18, registro048.agencia);
				adicionarCelula048(linha, 19, registro048.contaCorrente);
				adicionarCelula048(linha, 20, registro048.statusCredito);
				adicionarCelula048(linha, 21, registro048.numParcela);

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

	private void adicionarCabecalho048(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula048(linha, 0, "Tipo do registro");
		adicionarCelula048(linha, 1, "N??mero do estabelecimento");
		adicionarCelula048(linha, 2, "N??mero do documento OC - Refer??ncia");
		adicionarCelula048(linha, 3, "Valor da OC - Refer??ncia");
		adicionarCelula048(linha, 4, "Data do lan??amento OC - Refer??ncia");
		adicionarCelula048(linha, 5, "N??mero do estabelecimento original da venda");
		adicionarCelula048(linha, 6, "N??mero do resumo da venda");
		adicionarCelula048(linha, 7, "Data da venda do RV");
		adicionarCelula048(linha, 8, "Transa????es a vista ou parceladas (Tabela I)");
		adicionarCelula048(linha, 9, "C??digo da bandeira");
		adicionarCelula048(linha, 10, "Tipo de negocia????o (Cess??o/Gravame)");
		adicionarCelula048(linha, 11, "N??mero do contrato de negocia????o");
		adicionarCelula048(linha, 12, "N??mero do CNPJ do parceiro");
		adicionarCelula048(linha, 13, "N??mero do documento OC gerado na negocia????o");
		adicionarCelula048(linha, 14, "Valor Negociado");
		adicionarCelula048(linha, 15, "Data da negocia????o");
		adicionarCelula048(linha, 16, "Data de liquida????o");
		adicionarCelula048(linha, 17, "Banco");
		adicionarCelula048(linha, 18, "Ag??ncia");
		adicionarCelula048(linha, 19, "Conta corrente");
		adicionarCelula048(linha, 20, "Status do cr??dito - Tabela III");
		adicionarCelula048(linha, 21, "N??mero da Parcela");

	}

	private void adicionarCelula048(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
