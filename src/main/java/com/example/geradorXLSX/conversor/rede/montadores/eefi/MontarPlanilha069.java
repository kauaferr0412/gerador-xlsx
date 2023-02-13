package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro069;

public class MontarPlanilha069 {

public void criarArquivo069(final String nomeArquivo, final List<Registro069> registros069) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 069");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho069(planilha, numeroDaLinha++);
			
			for(Registro069 registro069: registros069) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula069(linha, 0, registro069.tipoRegistro);
                adicionarCelula069(linha, 1, registro069.numPvOriginal);
                adicionarCelula069(linha, 2, registro069.numRvOriginal);
                adicionarCelula069(linha, 3, registro069.dataRvOriginal);
                adicionarCelula069(linha, 4, registro069.numNovoPvAjustado);
                adicionarCelula069(linha, 5, registro069.numNovoRvAjustado);
                adicionarCelula069(linha, 6, registro069.novoValorParcelaAjustado);
                adicionarCelula069(linha, 7, registro069.valorOriginalParcelaalterada);
                adicionarCelula069(linha, 8, registro069.numReferencia);
                adicionarCelula069(linha, 9, registro069.dataCreditoRvAjustado);
                adicionarCelula069(linha, 10, registro069.valorAjuste);
                adicionarCelula069(linha, 11, registro069.numParcelaRvOriginal);
                adicionarCelula069(linha, 12, registro069.dataCancelamento);
                adicionarCelula069(linha, 13, registro069.valorCancelamentoSolicitado);
                adicionarCelula069(linha, 14, registro069.numCartao);
                adicionarCelula069(linha, 15, registro069.dataTransacao);
                adicionarCelula069(linha, 16, registro069.nsu);
                adicionarCelula069(linha, 17, registro069.tipoDebito);
                adicionarCelula069(linha, 18, registro069.numParcelaRvAjustado);
                adicionarCelula069(linha, 19, registro069.bandeiraRvAjustado);
                adicionarCelula069(linha, 20, registro069.dataRvAjustado);
                adicionarCelula069(linha, 21, registro069.tipoNegociacao);
                adicionarCelula069(linha, 22, registro069.numContratoNegociacao);
                adicionarCelula069(linha, 23, registro069.numCnpjParceiro);
                adicionarCelula069(linha, 24, registro069.dataNegociacao);
                
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
	private void adicionarCabecalho069(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula069(linha, 0, "Tipo do registro");
	    adicionarCelula069(linha, 1, "Número do PV original");
	    adicionarCelula069(linha, 2, "Número do RV original");
	    adicionarCelula069(linha, 3, "Data do RV original");
	    adicionarCelula069(linha, 4, "Número do novo PV ajustado");
	    adicionarCelula069(linha, 5, "Númro do novo RV ajustado");
	    adicionarCelula069(linha, 6, "Novo valor da parcela ajustado");
	    adicionarCelula069(linha, 7, "Valor original da parcela alterada");
	    adicionarCelula069(linha, 8, "Número de referência");
	    adicionarCelula069(linha, 9, "Data de crédito do RV ajustado");
	    adicionarCelula069(linha, 10, "Valor do ajuste");
	    adicionarCelula069(linha, 11, "Número da parcela do RV original");
	    adicionarCelula069(linha, 12, "Data do cancelamento");
	    adicionarCelula069(linha, 13, "Valor do cancelamento solicitado");
	    adicionarCelula069(linha, 14, "Número do cartão");
	    adicionarCelula069(linha, 15, "Data da transação");
	    adicionarCelula069(linha, 16, "NSU");
	    adicionarCelula069(linha, 17, "Tipo de débito");
	    adicionarCelula069(linha, 18, "Número da parcela do RV ajustado");
	    adicionarCelula069(linha, 19, "Bandeira do RV ajustado");
	    adicionarCelula069(linha, 20, "Data do RV ajustado");
	    adicionarCelula069(linha, 21, "Tipo de negociação (Cessão/Gravame)");
	    adicionarCelula069(linha, 22, "Número do contrato de negociação");
	    adicionarCelula069(linha, 23, "Número do CNPJ do parceiro");
	    adicionarCelula069(linha, 24, "Data de negociação");

	}
	private void adicionarCelula069(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
