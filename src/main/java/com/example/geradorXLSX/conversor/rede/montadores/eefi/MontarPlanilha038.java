package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro038;

public class MontarPlanilha038 {
public void criarArquivo038(final String nomeArquivo, final List<Registro038> registros038) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 038");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho038(planilha, numeroDaLinha++);
			
			for(Registro038 registro038: registros038) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula038(linha, 0, registro038.tipoRegistro);
                adicionarCelula038(linha, 1, registro038.numeroPv);
                adicionarCelula038(linha, 2, registro038.numeroDocumento);
                adicionarCelula038(linha, 3, registro038.dataEmissao);
                adicionarCelula038(linha, 4, registro038.valorDebito);
                adicionarCelula038(linha, 5, registro038.debito);
                adicionarCelula038(linha, 6, registro038.banco);
                adicionarCelula038(linha, 7, registro038.agencia);
                adicionarCelula038(linha, 8, registro038.conta);
                adicionarCelula038(linha, 9, registro038.numeroRvOriginal);
                adicionarCelula038(linha, 10, registro038.dataRvoriginal);
                adicionarCelula038(linha, 11, registro038.valorCreditoOriginal);
                adicionarCelula038(linha, 12, registro038.motivoDebito);
                adicionarCelula038(linha, 13, registro038.motivoDebitoString);
                adicionarCelula038(linha, 14, registro038.numeroCartao);
                adicionarCelula038(linha, 15, registro038.numReferenciaCartaFax);
                adicionarCelula038(linha, 16, registro038.mesDeReferencia);
                adicionarCelula038(linha, 17, registro038.dataCarta);
                adicionarCelula038(linha, 18, registro038.valorCancelamentoSolicitado);
                adicionarCelula038(linha, 19, registro038.numProcesso);
                adicionarCelula038(linha, 20, registro038.numPvOriginal);
                adicionarCelula038(linha, 21, registro038.dataTransacaoCv);
                adicionarCelula038(linha, 22, registro038.numNsu);
                adicionarCelula038(linha, 23, registro038.numResumoDebito);
                adicionarCelula038(linha, 24, registro038.dataDebito);
                adicionarCelula038(linha, 25, registro038.valorTransacaoOriginal);
                adicionarCelula038(linha, 26, registro038.numAutorizacao);
                adicionarCelula038(linha, 27, registro038.tipoDebito);
                adicionarCelula038(linha, 28, registro038.valorDebitototal);
                adicionarCelula038(linha, 29, registro038.valorPendente);
                adicionarCelula038(linha, 30, registro038.bandeiraRvOrigem);  
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
	private void adicionarCabecalho038(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula038(linha, 0, "Tipo do registro");
	    adicionarCelula038(linha, 1, "N??mero do PV");
	    adicionarCelula038(linha, 2, "N??mero do documento");
	    adicionarCelula038(linha, 3, "Data da emiss??o");
	    adicionarCelula038(linha, 4, "Valor do d??bito");
	    adicionarCelula038(linha, 5, "D (D??bito)");
	    adicionarCelula038(linha, 6, "Banco");
	    adicionarCelula038(linha, 7, "Ag??ncia");
	    adicionarCelula038(linha, 8, "Conta corrente");
	    adicionarCelula038(linha, 9, "N??mero do RV original");
	    adicionarCelula038(linha, 10, "Data do RV original");
	    adicionarCelula038(linha, 11, "Valor do cr??dito original");
	    adicionarCelula038(linha, 12, "Motivo do d??bito");
	    adicionarCelula038(linha, 13, "Motivo do d??bito (String - Tabela Item III");
	    adicionarCelula038(linha, 14, "N??mero do cart??o");
	    adicionarCelula038(linha, 15, "N??mero de refer??ncia da carta/fax");
	    adicionarCelula038(linha, 16, "M??s de refer??ncia (servi??os, POS etc.)");
	    adicionarCelula038(linha, 17, "Data da carta");
	    adicionarCelula038(linha, 18, "Valor do cancelamento solicitado");
	    adicionarCelula038(linha, 19, "N??mero do processo");
	    adicionarCelula038(linha, 20, "N??mero do PV original");
	    adicionarCelula038(linha, 21, "Data da transa????o CV");
	    adicionarCelula038(linha, 22, "N??mero do NSU");
	    adicionarCelula038(linha, 23, "N??mero do resumo do d??bito");
	    adicionarCelula038(linha, 24, "Data do d??bito");
	    adicionarCelula038(linha, 25, "Valor da transa????o original");
	    adicionarCelula038(linha, 26, "N??mero da autoriza????o");
	    adicionarCelula038(linha, 27, "Tipo de d??bito");
	    adicionarCelula038(linha, 28, "Valor do d??bito total");
	    adicionarCelula038(linha, 29, "Valor pendente");
	    adicionarCelula038(linha, 30, "Bandeira do RV de origem");
	}
	
	private void adicionarCelula038(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
