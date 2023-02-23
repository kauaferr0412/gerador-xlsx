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

import com.example.geradorXLSX.conversor.processamento.LayoutIdentify;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro035;

public class MontarPlanilha035 {
	private static Logger logger = Logger.getLogger(LayoutIdentify.class);

	public void criarArquivo035(final String nomeArquivo, final List<Registro035> registros035) {

		logger.info("GERANDO ARQUIVO: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 035");

			int numeroDaLinha = 0;

			adicionarCabecalho035(planilha, numeroDaLinha++);

			for (Registro035 registro035 : registros035) {

				var linha = planilha.createRow(numeroDaLinha++);
				adicionarCelula035(linha, 0, registro035.tipoRegistro);
				adicionarCelula035(linha, 1, registro035.numPvAjustado);
				adicionarCelula035(linha, 2, registro035.numRvAjustado);
				adicionarCelula035(linha, 3, registro035.dataAjuste);
				adicionarCelula035(linha, 4, registro035.valorAjuste);
				adicionarCelula035(linha, 5, registro035.debito);
				adicionarCelula035(linha, 6, registro035.motivoAjuste);
				adicionarCelula035(linha, 7, registro035.motivoAjusteTxt);
				adicionarCelula035(linha, 8, registro035.numCartao);
				adicionarCelula035(linha, 9, registro035.dataTransacaoCv);
				adicionarCelula035(linha, 10, registro035.numRvOriginal);
				adicionarCelula035(linha, 11, registro035.numRefCartaFax);
				adicionarCelula035(linha, 12, registro035.dataCarta);
				adicionarCelula035(linha, 13, registro035.mesRef);
				adicionarCelula035(linha, 14, registro035.numPvOriginal);
				adicionarCelula035(linha, 15, registro035.dataRvOriginal);
				adicionarCelula035(linha, 16, registro035.valorTransacao);
				adicionarCelula035(linha, 17, registro035.desagendamentoOuNet);
				adicionarCelula035(linha, 18, registro035.dataCredito);
				adicionarCelula035(linha, 19, registro035.novoValorParcela);
				adicionarCelula035(linha, 20, registro035.valorOriginalParcela);
				adicionarCelula035(linha, 21, registro035.valorBrutoRvOriginal);
				adicionarCelula035(linha, 22, registro035.valorCancelamentoSolicitado);
				adicionarCelula035(linha, 23, registro035.numNsu);
				adicionarCelula035(linha, 24, registro035.numAutorizacao);
				adicionarCelula035(linha, 25, registro035.tipoDebito);
				adicionarCelula035(linha, 26, registro035.numOrdemDebito);
				adicionarCelula035(linha, 27, registro035.valorDebitoTotal);
				adicionarCelula035(linha, 28, registro035.valorPedente);
				adicionarCelula035(linha, 29, registro035.bandeiraRvOrigem);
				adicionarCelula035(linha, 30, registro035.bandeiraRvAjustado);
				adicionarCelula035(linha, 31, registro035.numParcelaRvAjustado);
				adicionarCelula035(linha, 32, registro035.numParcelaRvOriginal);
				adicionarCelula035(linha, 33, registro035.dataRvAjustado);

			}
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			logger.error("ARQUIVO NÃO ENCONTRADO: " + nomeArquivo);
		} catch (IOException e) {
			logger.error("ERRO AO PROCESSARO O ARQUIVO " + nomeArquivo);
		}
		logger.info("ARQUIVO GERADO COM SUCESSO");
	}

	private void adicionarCabecalho035(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula035(linha, 0, "Tipo do registro");
		adicionarCelula035(linha, 1, "Número do PV ajustado");
		adicionarCelula035(linha, 2, "Número do RV ajustado");
		adicionarCelula035(linha, 3, "Data do ajuste");
		adicionarCelula035(linha, 4, "Valor do ajuste");
		adicionarCelula035(linha, 5, "D (Débito)");
		adicionarCelula035(linha, 6, "Motivo do ajuste");
		adicionarCelula035(linha, 7, "Motivo do ajuste (String)");
		adicionarCelula035(linha, 8, "Número do cartão");
		adicionarCelula035(linha, 9, "Data da transação CV");
		adicionarCelula035(linha, 10, "Número do RV original");
		adicionarCelula035(linha, 11, "Número de referência da carta/fax");
		adicionarCelula035(linha, 12, "Data da carta");
		adicionarCelula035(linha, 13, "Mês de referência (serviços, POS etc.)");
		adicionarCelula035(linha, 14, "Número do PV original");
		adicionarCelula035(linha, 15, "Data do RV original");
		adicionarCelula035(linha, 16, "Valor da transação");
		adicionarCelula035(linha, 17, "D (Desagendamento) ou N (Net)");
		adicionarCelula035(linha, 18, "Data do crédito");
		adicionarCelula035(linha, 19, "Novo valor da parcela");
		adicionarCelula035(linha, 20, "Valor original da parcela");
		adicionarCelula035(linha, 21, "Valor bruto do resumo de vendas original");
		adicionarCelula035(linha, 22, "Valor do cancelamento solicitado");
		adicionarCelula035(linha, 23, "Número do NSU (motivos 16, 18 e23)");
		adicionarCelula035(linha, 24, "Número da autorização");
		adicionarCelula035(linha, 25, "Tipo de débito");
		adicionarCelula035(linha, 26, "Número da Ordem de Débito");
		adicionarCelula035(linha, 27, "Valor do débito total");// ok até aqui
		adicionarCelula035(linha, 28, "Valor pendente");
		adicionarCelula035(linha, 29, "Bandeira do RV de origem (2)");
		adicionarCelula035(linha, 30, "Bandeira do RV ajustado (2)");
		adicionarCelula035(linha, 31, "Número da parcela ajustado");
		adicionarCelula035(linha, 32, "Número da parcela RV original");
		adicionarCelula035(linha, 33, "Data do RV ajustado");
	}

	private void adicionarCelula035(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
