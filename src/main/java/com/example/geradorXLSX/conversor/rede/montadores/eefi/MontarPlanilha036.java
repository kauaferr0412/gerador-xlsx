package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro036;

public class MontarPlanilha036 {

public void criarArquivo036(final String nomeArquivo, final List<Registro036> registros036) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 036");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho036(planilha, numeroDaLinha++);
			
			for(Registro036 registro036: registros036) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula036(linha, 0, registro036.tipoRegistro);
                adicionarCelula036(linha, 1, registro036.numPV);
                adicionarCelula036(linha, 2, registro036.numDocumento);
                adicionarCelula036(linha, 3, registro036.dataLancamento);
                adicionarCelula036(linha, 4, registro036.valorLancamento);
                adicionarCelula036(linha, 5, registro036.credito);
                adicionarCelula036(linha, 6, registro036.banco);
                adicionarCelula036(linha, 7, registro036.agencia);
                adicionarCelula036(linha, 8, registro036.conta);
                adicionarCelula036(linha, 9, registro036.numRvCorresp);
                adicionarCelula036(linha, 10, registro036.dataRvCorresp);
                adicionarCelula036(linha, 11, registro036.valorCreditoOriginal);
                adicionarCelula036(linha, 12, registro036.dataVencimentoOriginal);
                adicionarCelula036(linha, 13, registro036.numParcelaTotal);
                adicionarCelula036(linha, 14, registro036.valorBruto);
                adicionarCelula036(linha, 15, registro036.valorTaxaDesconto);
                adicionarCelula036(linha, 16, registro036.numPvOriginal);
                adicionarCelula036(linha, 17, registro036.bandeira); 
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

private void adicionarCabecalho036(XSSFSheet planilha, int numeroLinha) {
    var linha = planilha.createRow(numeroLinha++);
    adicionarCelula036(linha, 0, "Tipo do registro");
    adicionarCelula036(linha, 1, "Número do PV");
    adicionarCelula036(linha, 2, "Número do documento");
    adicionarCelula036(linha, 3, "Data do lançamento");
    adicionarCelula036(linha, 4, "Valor do lançamento");
    adicionarCelula036(linha, 5, "C (Crédito)");
    adicionarCelula036(linha, 6, "Banco");
    adicionarCelula036(linha, 7, "Agência");
    adicionarCelula036(linha, 8, "Conta corrente");
    adicionarCelula036(linha, 9, "Número do RV correspondente");
    adicionarCelula036(linha, 10, "Data do RV correspondente");
    adicionarCelula036(linha, 11, "Valor do crédito original"); 
    adicionarCelula036(linha, 12, "Data do vencimento original");
    adicionarCelula036(linha, 13, "Número da parcela/total");
    adicionarCelula036(linha, 14, "Valor bruto");
    adicionarCelula036(linha, 15, "Valor da taxa de desconto");
    adicionarCelula036(linha, 16, "Número do PV original");
    adicionarCelula036(linha, 17, "Bandeira");
}

 private void adicionarCelula036(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
