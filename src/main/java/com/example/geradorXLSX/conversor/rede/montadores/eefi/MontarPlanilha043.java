package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro043;

public class MontarPlanilha043 {

public void criarArquivo043(final String nomeArquivo, final List<Registro043> registros043) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 043");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho043(planilha, numeroDaLinha++);
			
			for(Registro043 registro043: registros043) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula043(linha, 0, registro043.tipoRegistro);
                adicionarCelula043(linha, 1, registro043.numPvCreditado);
                adicionarCelula043(linha, 2, registro043.numResumoCredito);
                adicionarCelula043(linha, 3, registro043.numDocumento);
                adicionarCelula043(linha, 4, registro043.dataEmissao);
                adicionarCelula043(linha, 5, registro043.dataCredito);
                adicionarCelula043(linha, 6, registro043.valorCredito);
                adicionarCelula043(linha, 7, registro043.credito);
                adicionarCelula043(linha, 8, registro043.banco);
                adicionarCelula043(linha, 9, registro043.agencia);
                adicionarCelula043(linha, 10, registro043.contaCorrente);
                adicionarCelula043(linha, 11, registro043.motivoCredito);
                adicionarCelula043(linha, 12, registro043.motivoCreditoString);
                adicionarCelula043(linha, 13, registro043.bandeira);
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
	private void adicionarCabecalho043(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula043(linha, 0, "Tipo do registro");
	    adicionarCelula043(linha, 1, "Número do PV creditado");
	    adicionarCelula043(linha, 2, "Número do resumo do crédito");
	    adicionarCelula043(linha, 3, "Número do documento");
	    adicionarCelula043(linha, 4, "Data da emissão");
	    adicionarCelula043(linha, 5, "Data do crédito");
	    adicionarCelula043(linha, 6, "Valor do crédito");
	    adicionarCelula043(linha, 7, "C (Crédito)");
	    adicionarCelula043(linha, 8, "Banco");
	    adicionarCelula043(linha, 9, "Agência");
	    adicionarCelula043(linha, 10, "Conta corrente");
	    adicionarCelula043(linha, 11, "Motivo do crédito (Tabela III)"); 
	    adicionarCelula043(linha, 12, "Motivo do crédito (String)");
	    adicionarCelula043(linha, 13, "Bandeira");
	}
	private void adicionarCelula043(Row linha, int coluna, String valor) {
	        Cell cell = linha.createCell(coluna);
	        cell.setCellValue(valor);
	}
}
