package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro055;

public class MontarPlanilha055 {

public void criarArquivo055(final String nomeArquivo, final List<Registro055> registros055) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 055");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho055(planilha, numeroDaLinha++);
			
			for(Registro055 registro055: registros055) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula055(linha, 0, registro055.tipoRegistro);
                adicionarCelula055(linha, 1, registro055.numCartao);
                adicionarCelula055(linha, 2, registro055.numNsu);
                adicionarCelula055(linha, 3, registro055.dataCvOriginalDaTransacao);
                adicionarCelula055(linha, 4, registro055.numAutorizacao);
                adicionarCelula055(linha, 5, registro055.valorTransacaoOriginal);
                adicionarCelula055(linha, 6, registro055.numRvOriginal);
                adicionarCelula055(linha, 7, registro055.numPvOriginal);
                adicionarCelula055(linha, 8, registro055.tId);
                adicionarCelula055(linha, 9, registro055.numPedido);
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

	private void adicionarCabecalho055(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula055(linha, 0, "Tipo do registro");
	    adicionarCelula055(linha, 1, "Número do cartão");
	    adicionarCelula055(linha, 2, "Número do NSU (motivos 16, 18 e 23)");
	    adicionarCelula055(linha, 3, "Data do CV original da transação");
	    adicionarCelula055(linha, 4, "Número da autorização");
	    adicionarCelula055(linha, 5, "Valor da transação original");
	    adicionarCelula055(linha, 6, "Número do RV original");
	    adicionarCelula055(linha, 7, "Número do PV original");
	    adicionarCelula055(linha, 8, "TID");
	    adicionarCelula055(linha, 9, "Número do pedido");
	    
	}
	private void adicionarCelula055(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
