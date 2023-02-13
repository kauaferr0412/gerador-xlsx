package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro044;

public class MontarPlanilha044 {
	
	public void criarArquivo044(final String nomeArquivo, final List<Registro044> registros44) {
		
		System.out.println("Gerando arquivo: " + nomeArquivo);
		
		try(var workbook = new XSSFWorkbook();var outputStream = new FileOutputStream(nomeArquivo)){
			var planilha = workbook.createSheet("Layout Rede - Registros 044");
			
			int numeroDaLinha = 0;
			
			adicionarCabecalho044(planilha, numeroDaLinha++);
			
			for(Registro044 registro044: registros44) {
				
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula044(linha, 0, registro044.tipoRegistro);
                adicionarCelula044(linha, 1, registro044.numeroPv);
                adicionarCelula044(linha, 2, registro044.numeroOrdemDebito);
                adicionarCelula044(linha, 3, registro044.dataOrdemDebito);
                adicionarCelula044(linha, 4, registro044.valorOrdemDebito);
                adicionarCelula044(linha, 5, registro044.motivoAjuste);
                adicionarCelula044(linha, 6, registro044.motivoAjusteString);
                adicionarCelula044(linha, 7, registro044.numCartao);
                adicionarCelula044(linha, 8, registro044.numNsu);
                adicionarCelula044(linha, 9, registro044.dataCvOriginalTransacao);
                adicionarCelula044(linha, 10, registro044.numAutorizacao);
                adicionarCelula044(linha, 11, registro044.valorTransacaoOriginal);
                adicionarCelula044(linha, 12, registro044.numRvOriginal);
                adicionarCelula044(linha, 13, registro044.dataRvOriginal);
                adicionarCelula044(linha, 14, registro044.numPvOriginal);
                adicionarCelula044(linha, 15, registro044.numReferenciaCartaFax);
                adicionarCelula044(linha, 16, registro044.dataCarta);
                adicionarCelula044(linha, 17, registro044.numProcessoChargeback);
                adicionarCelula044(linha, 18, registro044.mesReferencia);
                adicionarCelula044(linha, 19, registro044.valorCompensado);
                adicionarCelula044(linha, 20, registro044.dataPagamento);
                adicionarCelula044(linha, 21, registro044.valorPendenteDebito);
                adicionarCelula044(linha, 22, registro044.numProcessoRetencao);
                adicionarCelula044(linha, 23, registro044.meioCompensacao);
                adicionarCelula044(linha, 24, registro044.meioCompensacaoString);
                adicionarCelula044(linha, 25, registro044.bandeira);
                 
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
	private void adicionarCabecalho044(XSSFSheet planilha, int numeroLinha) {
        var linha = planilha.createRow(numeroLinha++);
        adicionarCelula044(linha, 0, "Tipo do registro");
        adicionarCelula044(linha, 1, "Número do PV");
        adicionarCelula044(linha, 2, "Número da ordem de débito");
        adicionarCelula044(linha, 3, "Data da ordem de débito");
        adicionarCelula044(linha, 4, "Valor da ordem de débito");
        adicionarCelula044(linha, 5, "Motivo do ajuste (Tabela III)");
        adicionarCelula044(linha, 6, "Motivo do ajuste (String)");
        adicionarCelula044(linha, 7, "Número do cartão");
        adicionarCelula044(linha, 8, "Número do NSU");
        adicionarCelula044(linha, 9, "Data do CV original da transação");
        adicionarCelula044(linha, 10, "Número da autorização");
        adicionarCelula044(linha, 11, "Valor da transação original");
        adicionarCelula044(linha, 12, "Número do RV original");
        adicionarCelula044(linha, 13, "Data do RV original");
        adicionarCelula044(linha, 14, "Número do PV original");
        adicionarCelula044(linha, 15, "Número de referência da carta/fax");
        adicionarCelula044(linha, 16, "Data da carta");
        adicionarCelula044(linha, 17, "Número do processo de chargeback");
        adicionarCelula044(linha, 18, "Mês de referência");
        adicionarCelula044(linha, 19, "Valor compensado/pago");
        adicionarCelula044(linha, 20, "Data do pagamento");
        adicionarCelula044(linha, 21, "Valor pendente de débito");  
        adicionarCelula044(linha, 22, "Número do processo de retenção");
        adicionarCelula044(linha, 23, "Meio de compensação");
        adicionarCelula044(linha, 24, "Meio de compensação (String)");
        adicionarCelula044(linha, 25, "Identifica a Bandeira");
    }
	private void adicionarCelula044(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
