package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro049;

public class MontarPlanilha049 {

public void criarArquivo049(final String nomeArquivo, final List<Registro049> registros049) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 049");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho049(planilha, numeroDaLinha++);
			
			for(Registro049 registro049: registros049) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula049(linha, 0, registro049.tipoRegistro);
                adicionarCelula049(linha, 1, registro049.numPvOriginal);
                adicionarCelula049(linha, 2, registro049.numRvOriginal);
                adicionarCelula049(linha, 3, registro049.numReferencia);
                adicionarCelula049(linha, 4, registro049.dataDoCredito);
                adicionarCelula049(linha, 5, registro049.novoValorParcela);
                adicionarCelula049(linha, 6, registro049.valorOriginalParcelaAlterada);
                adicionarCelula049(linha, 7, registro049.valorAjuste);
                adicionarCelula049(linha, 8, registro049.dataCancelamento);
                adicionarCelula049(linha, 9, registro049.valorRvOriginal);
                adicionarCelula049(linha, 10, registro049.valorCancelamentoSolicitado);
                adicionarCelula049(linha, 11, registro049.numCartao);
                adicionarCelula049(linha, 12, registro049.dataTransacao);
                adicionarCelula049(linha, 13, registro049.nsu);
                adicionarCelula049(linha, 14, registro049.tipoDebito);
                adicionarCelula049(linha, 15, registro049.numParcela);
                adicionarCelula049(linha, 16, registro049.bandeiraRvOrigem);
 
            }
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
		}catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + nomeArquivo);
        }
       System.out.println("Arquivo gerado com sucesso!");
	}
	private void adicionarCabecalho049(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha++);
        adicionarCelula049(linha, 0, "Tipo do registro");
        adicionarCelula049(linha, 1, "Número do PV original");
        adicionarCelula049(linha, 2, "Número do RV original");
        adicionarCelula049(linha, 3, "Número de referência");
        adicionarCelula049(linha, 4, "Data do crédito");
        adicionarCelula049(linha, 5, "Novo valor da parcela");
        adicionarCelula049(linha, 6, "Valor original da parcela alterada");
        adicionarCelula049(linha, 7, "Valor do ajuste");
        adicionarCelula049(linha, 8, "Data do cancelamento");
        adicionarCelula049(linha, 9, "Valor do RV original");
        adicionarCelula049(linha, 10, "Valor do cancelamento solicitado");
        adicionarCelula049(linha, 11, "Número do cartão");
        adicionarCelula049(linha, 12, "Data da transação");
        adicionarCelula049(linha, 13, "NSU");
        adicionarCelula049(linha, 14, "Tipo de débito");
        adicionarCelula049(linha, 15, "Número da parcela");
        adicionarCelula049(linha, 16, "Bandeira do RV de origem");

    }
	private void adicionarCelula049(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
