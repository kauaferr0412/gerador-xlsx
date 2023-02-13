package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro048;

public class MontarPlanilha048 {

	public void criarArquivo048(final String nomeArquivo, final List<Registro048> registros048) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 048");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho048(planilha, numeroDaLinha++);
			
			for(Registro048 registro048: registros048) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                
                adicionarCelula048(linha, 0, registro048.tipoRegistro);
                adicionarCelula048(linha, 1, registro048.numEstabelecimento);
                adicionarCelula048(linha, 2, registro048.numDocumentoOc);
                adicionarCelula048(linha, 3, registro048.valorOcReferencia);
                adicionarCelula048(linha, 4, registro048.dataLancamentoOc);
                adicionarCelula048(linha, 5, registro048.numEcOriginalVenda);
                adicionarCelula048(linha, 6, registro048.numRV);
                adicionarCelula048(linha, 7, registro048.dataVendaDoRV);
                adicionarCelula048(linha, 8, registro048.identificaTransacaoAvistaParcelada);
                adicionarCelula048(linha, 9, registro048.codigoBandeira);
                adicionarCelula048(linha, 10, registro048.tipoNegociacao);
                adicionarCelula048(linha, 11, registro048.numeroContratoNegociacao);
                adicionarCelula048(linha, 12, registro048.numCnpjParceiro);
                adicionarCelula048(linha, 13, registro048.numDocOCGeradoNegoc);
                adicionarCelula048(linha, 14, registro048.valorNegociado);
                adicionarCelula048(linha, 15, registro048.dataNegociacao);
                adicionarCelula048(linha, 16, registro048.dataLiquidacao);
                adicionarCelula048(linha, 17, registro048.banco);
                adicionarCelula048(linha, 18, registro048.agencia);
                adicionarCelula048(linha, 19, registro048.contaCorrente);
                adicionarCelula048(linha, 20, registro048.statusCredito);
                adicionarCelula048(linha, 21, registro048.numParcela);

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
	
	private void adicionarCabecalho048(XSSFSheet planilha, int numeroLinha) {
	    var linha = planilha.createRow(numeroLinha++);
	    adicionarCelula048(linha, 0, "Tipo do registro");
	    adicionarCelula048(linha, 1, "Número do estabelecimento");
	    adicionarCelula048(linha, 2, "Número do documento OC - Referência");
	    adicionarCelula048(linha, 3, "Valor da OC - Referência");
	    adicionarCelula048(linha, 4, "Data do lançamento OC - Referência");
	    adicionarCelula048(linha, 5, "Número do estabelecimento original da venda");
	    adicionarCelula048(linha, 6, "Número do resumo da venda");
	    adicionarCelula048(linha, 7, "Data da venda do RV");
	    adicionarCelula048(linha, 8, "Transações a vista ou parceladas (Tabela I)");
	    adicionarCelula048(linha, 9, "Código da bandeira");
	    adicionarCelula048(linha, 10, "Tipo de negociação (Cessão/Gravame)");
	    adicionarCelula048(linha, 11, "Número do contrato de negociação");
	    adicionarCelula048(linha, 12, "Número do CNPJ do parceiro");
	    adicionarCelula048(linha, 13, "Número do documento OC gerado na negociação");
	    adicionarCelula048(linha, 14, "Valor Negociado");
	    adicionarCelula048(linha, 15, "Data da negociação");
	    adicionarCelula048(linha, 16, "Data de liquidação");
	    adicionarCelula048(linha, 17, "Banco");
	    adicionarCelula048(linha, 18, "Agência");
	    adicionarCelula048(linha, 19, "Conta corrente");
	    adicionarCelula048(linha, 20, "Status do crédito - Tabela III");
	    adicionarCelula048(linha, 21, "Número da Parcela");
	    
	}
	
	private void adicionarCelula048(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
   }
}
