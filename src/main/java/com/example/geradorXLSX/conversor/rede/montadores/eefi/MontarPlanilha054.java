package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro054;

public class MontarPlanilha054 {

	public void criarArquivo054(final String nomeArquivo, final List<Registro054> registros054) {

		System.out.println("Gerando arquivo: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 054");

			int numeroDaLinha = 0;

			adicionarCabecalho054(planilha, numeroDaLinha++);

			for (Registro054 registro054 : registros054) {

				var linha = planilha.createRow(numeroDaLinha++);
				adicionarCelula054(linha, 0, registro054.tipoRegistro);
				adicionarCelula054(linha, 1, registro054.numRvOriginal);
				adicionarCelula054(linha, 2, registro054.numCartao);
				adicionarCelula054(linha, 3, registro054.numPvOriginal);
				adicionarCelula054(linha, 4, registro054.DataTransacaoCv);
				adicionarCelula054(linha, 5, registro054.NumNsu);
				adicionarCelula054(linha, 6, registro054.valorTransacaoOriginal);
				adicionarCelula054(linha, 7, registro054.numAutorizacao);
				adicionarCelula054(linha, 8, registro054.tId);
				adicionarCelula054(linha, 9, registro054.numPedido);
			}
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: " + nomeArquivo);
		} catch (IOException e) {
			System.out.println("Erro ao processar o arquivo: " + nomeArquivo);
		}
		System.out.println("Arquivo gerado com sucesso!");
	}

	private void adicionarCabecalho054(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula054(linha, 0, "Tipo do registro");
		adicionarCelula054(linha, 1, "N??mero do RV original");
		adicionarCelula054(linha, 2, "N??mero do cart??o");
		adicionarCelula054(linha, 3, "N??mero do PV original");
		adicionarCelula054(linha, 4, "Data da transa????o CV");
		adicionarCelula054(linha, 5, "N??mero do NSU (motivos 16, 18 e 23");
		adicionarCelula054(linha, 6, "Valor da transa????o original");
		adicionarCelula054(linha, 7, "N??mero da autoriza????o");
		adicionarCelula054(linha, 8, "TID");
		adicionarCelula054(linha, 9, "N??mero do pedido");

	}

	private void adicionarCelula054(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
