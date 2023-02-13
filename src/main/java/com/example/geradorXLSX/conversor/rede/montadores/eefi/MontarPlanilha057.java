package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro057;

public class MontarPlanilha057 {

public void criarArquivo057(final String nomeArquivo, final List<Registro057> registros057) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 057");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho057(planilha, numeroDaLinha++);
			
			for(Registro057 registro057: registros057) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula057(linha, 0, registro057.tipoRegistro);
                adicionarCelula057(linha, 1, registro057.numPvOriginal);
                adicionarCelula057(linha, 2, registro057.numRvOriginal);
                adicionarCelula057(linha, 3, registro057.valorRvOriginal);
                adicionarCelula057(linha, 4, registro057.numCartao);
                adicionarCelula057(linha, 5, registro057.dataTransacao);
                adicionarCelula057(linha, 6, registro057.nsu);
                adicionarCelula057(linha, 7, registro057.tId);
                adicionarCelula057(linha, 8, registro057.numPedido);
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
	private void adicionarCabecalho057(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula057(linha, 0, "Tipo do registro");
	    adicionarCelula057(linha, 1, "Número do PV original");
	    adicionarCelula057(linha, 2, "Número do RV original");
	    adicionarCelula057(linha, 3, "Valor do RV original");
	    adicionarCelula057(linha, 4, "Número do cartão");
	    adicionarCelula057(linha, 5, "Data da transação");
	    adicionarCelula057(linha, 6, "NSU");
	    adicionarCelula057(linha, 7, "TID");
	    adicionarCelula057(linha, 8, "Número do pedido");
	    
	}
	private void adicionarCelula057(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
