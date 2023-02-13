package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro053;

public class MontarPlanilha053 {

public void criarArquivo053(final String nomeArquivo, final List<Registro053> registros053) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 053");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho053(planilha, numeroDaLinha++);
			
			for(Registro053 registro053: registros053) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula053(linha, 0, registro053.tipoRegistro);
                adicionarCelula053(linha, 1, registro053.numCartao);
                adicionarCelula053(linha, 2, registro053.dataTransacaoCV);
                adicionarCelula053(linha, 3, registro053.numRvOriginal);
                adicionarCelula053(linha, 4, registro053.numPvOriginal);
                adicionarCelula053(linha, 5, registro053.valorTransacao);
                adicionarCelula053(linha, 6, registro053.numNsu);
                adicionarCelula053(linha, 7, registro053.numAutorizacao);
                adicionarCelula053(linha, 8, registro053.tId);
                adicionarCelula053(linha, 9, registro053.numPedido);   
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

private void adicionarCabecalho053(XSSFSheet planilha, int numeroLinha) {
    var linha = planilha.createRow(numeroLinha++);
    adicionarCelula053(linha, 0, "Tipo do registro");
    adicionarCelula053(linha, 1, "Número do cartão");
    adicionarCelula053(linha, 2, "Data da transação CV");
    adicionarCelula053(linha, 3, "Número do RV original");
    adicionarCelula053(linha, 4, "Número do PV original");
    adicionarCelula053(linha, 5, "Valor da transação");
    adicionarCelula053(linha, 6, "Número de NSU");
    adicionarCelula053(linha, 7, "Número de autorização");
    adicionarCelula053(linha, 8, "TID");
    adicionarCelula053(linha, 9, "Número do pedido");
}

 private void adicionarCelula053(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
