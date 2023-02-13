package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro056;

public class MontarPlanilha056 {

public void criarArquivo056(final String nomeArquivo, final List<Registro056> registros056) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 056");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho056(planilha, numeroDaLinha++);
			
			for(Registro056 registro056: registros056) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula056(linha, 0, registro056.tipoRegistro);
                adicionarCelula056(linha, 1, registro056.numCartao);
                adicionarCelula056(linha, 2, registro056.numNsu);
                adicionarCelula056(linha, 3, registro056.dataCvOriginalDaTransacao);
                adicionarCelula056(linha, 4, registro056.numAutorizacao);
                adicionarCelula056(linha, 5, registro056.valorTransacaoOriginal);
                adicionarCelula056(linha, 6, registro056.numRvOriginal);
                adicionarCelula056(linha, 7, registro056.numPvOriginal);
                adicionarCelula056(linha, 8, registro056.tId);
                adicionarCelula056(linha, 9, registro056.numPedido);
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
	private void adicionarCabecalho056(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula056(linha, 0, "Tipo do registro");
	    adicionarCelula056(linha, 1, "Número do cartão");
	    adicionarCelula056(linha, 2, "Número do NSU (motivos 16, 18 e 23)");
	    adicionarCelula056(linha, 3, "Data do CV original da transação");
	    adicionarCelula056(linha, 4, "Número da autorização");
	    adicionarCelula056(linha, 5, "Valor da transação original");
	    adicionarCelula056(linha, 6, "Número do RV original");
	    adicionarCelula056(linha, 7, "Número do PV original");
	    adicionarCelula056(linha, 8, "TID");
	    adicionarCelula056(linha, 9, "Número do pedido");
	    
	}
	private void adicionarCelula056(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
