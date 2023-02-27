package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro047;

public class MontarPlanilha047 {
	
	public void criarArquivo047(final String nomeArquivo, final List<Registro047> registros47) {
			
			System.out.println("Gerando arquivo: " + nomeArquivo);
			
			try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
				var planilha = workbook.createSheet("Layout Rede - Registros 047");
				
				int numeroDaLinha = 0;
				
				adicionarCabecalho047(planilha, numeroDaLinha++);
				
				for(Registro047 registro047: registros47) {
					
	                var linha = planilha.createRow(numeroDaLinha++);
	                adicionarCelula047(linha, 0, registro047.tipo_registro);
	                adicionarCelula047(linha, 1, registro047.num_EC);
	                adicionarCelula047(linha, 2, registro047.num_OC_ref);
	                adicionarCelula047(linha, 3, registro047.valor_OC_ref);
	                adicionarCelula047(linha, 4, registro047.data_OC_ref);
	                adicionarCelula047(linha, 5, registro047.num_EC_orig_venda);
	                adicionarCelula047(linha, 6, registro047.num_rv);
	                adicionarCelula047(linha, 7, registro047.data_venda_RV);
	                adicionarCelula047(linha, 8, registro047.transact_parcel_tipo);
	                adicionarCelula047(linha, 9, registro047.code_band);
	                adicionarCelula047(linha, 10, registro047.negociacao_tipo);
	                adicionarCelula047(linha, 11, registro047.contrato_number);
	                adicionarCelula047(linha, 12, registro047.cnpj_parceiro);
	                adicionarCelula047(linha, 13, registro047.num_doc_OC_neg);
	                adicionarCelula047(linha, 14, registro047.valor_negoc);
	                adicionarCelula047(linha, 15, registro047.data_negociacao);
	                adicionarCelula047(linha, 16, registro047.data_liquidacao);
	                adicionarCelula047(linha, 17, registro047.bank_number);
	                adicionarCelula047(linha, 18, registro047.bank_branch_number);
	                adicionarCelula047(linha, 19, registro047.bank_account_number);
	                adicionarCelula047(linha, 20, registro047.status_credito);
	                adicionarCelula047(linha, 21, registro047.parcela_numero);
	                
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
	
	private void adicionarCabecalho047(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha++);
        adicionarCelula047(linha, 0, "Tipo do registro");
        adicionarCelula047(linha, 1, "Número do Estabelecimento");
        adicionarCelula047(linha, 2, "Número do Documento OC – Referência");
        adicionarCelula047(linha, 3, "Valor da OC – Referência");
        adicionarCelula047(linha, 4, "Data do lançamento OC");
        adicionarCelula047(linha, 5, "Número do estabelecimento original da venda");
        adicionarCelula047(linha, 6, "Número do Resumo de Venda");
        adicionarCelula047(linha, 7, "Data da Venda do RV");
        adicionarCelula047(linha, 8, "Identifica transações à vista, parceladas etc.");
        adicionarCelula047(linha, 9, "Código da Bandeira");
        adicionarCelula047(linha, 10, "Tipo da Negociação - Cessão (1) e ou Gravame (2)");
        adicionarCelula047(linha, 11, "Número do Contrato de Negociação");
        adicionarCelula047(linha, 12, "Número do CNPJ Parceiro");
        adicionarCelula047(linha, 13, "Número do Documento OC gerado na negociação");
        adicionarCelula047(linha, 14, "Valor Negociado");
        adicionarCelula047(linha, 15, "Data da Negociação");
        adicionarCelula047(linha, 16, "Data da liquidação");
        adicionarCelula047(linha, 17, "Banco");
        adicionarCelula047(linha, 18, "Agência");
        adicionarCelula047(linha, 19, "Conta");
        adicionarCelula047(linha, 20, "Status do crédito");
        adicionarCelula047(linha, 21, "Parcela");        
    }
	
	 private void adicionarCelula047(Row linha, int coluna, String valor) {
	        Cell cell = linha.createCell(coluna);
	        cell.setCellValue(valor);
	    }
}

