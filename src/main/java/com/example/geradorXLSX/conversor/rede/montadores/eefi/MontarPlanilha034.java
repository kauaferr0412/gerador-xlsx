package com.example.geradorXLSX.conversor.rede.montadores.eefi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.geradorXLSX.conversor.rede.models.eefi.Registro034;
import com.example.geradorXLSX.service.JasperReportService;

import net.sf.jasperreports.engine.JRException;

public class MontarPlanilha034 {
	
	private JasperReportService jasper = new JasperReportService();

	public byte[] criarArquivo034(final String nomeArquivo, final List<Registro034> registros034, String tipo) throws IOException {

		System.out.println("Gerando arquivo: " + nomeArquivo);

		try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(nomeArquivo)) {
			var planilha = workbook.createSheet("Layout Rede - Registros 034");

			int numeroDaLinha = 0;
			
			adicionarCabecalho034(planilha, numeroDaLinha++);

			for (Registro034 registro034 : registros034) {

				var linha = planilha.createRow(numeroDaLinha++);
				adicionarCelula034(linha, 0, registro034.tipo_registro);
				adicionarCelula034(linha, 1, registro034.num_pv_centralizador);
				adicionarCelula034(linha, 2, registro034.num_documento);
				adicionarCelula034(linha, 3, registro034.data_lancamento);
				adicionarCelula034(linha, 4, registro034.valor_lancamento);
				adicionarCelula034(linha, 5, registro034.credito);
				adicionarCelula034(linha, 6, registro034.banco);
				adicionarCelula034(linha, 7, registro034.agencia);
				adicionarCelula034(linha, 8, registro034.conta);
				adicionarCelula034(linha, 9, registro034.data_movimento);
				adicionarCelula034(linha, 10, registro034.num_rv);
				adicionarCelula034(linha, 11, registro034.data_rv);
				adicionarCelula034(linha, 12, registro034.bandeira);
				adicionarCelula034(linha, 13, registro034.tipo_de_transacao);
				adicionarCelula034(linha, 14, registro034.valor_bruto_rv);
				adicionarCelula034(linha, 15, registro034.valor_taxa_desconto);
				adicionarCelula034(linha, 16, registro034.num_parcel_total);
				adicionarCelula034(linha, 17, registro034.status_credito);
				adicionarCelula034(linha, 18, registro034.num_pv_original);
			}
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: " + nomeArquivo);
		} catch (IOException e) {
			System.out.println("Erro ao processar o arquivo: " + nomeArquivo);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Arquivo gerado com sucesso!");
		return null;
	}

	private void adicionarCabecalho034(XSSFSheet planilha, int numeroLinha) {
		var linha = planilha.createRow(numeroLinha++);
		adicionarCelula034(linha, 0, "Tipo do registro");
		adicionarCelula034(linha, 1, "Número do PV centralizador");
		adicionarCelula034(linha, 2, "Número do Documento");
		adicionarCelula034(linha, 3, "Data do lançamento");
		adicionarCelula034(linha, 4, "Valor do lançamento");
		adicionarCelula034(linha, 5, "C (Crédito)");
		adicionarCelula034(linha, 6, "Banco");
		adicionarCelula034(linha, 7, "Agência");
		adicionarCelula034(linha, 8, "Conta corrente");
		adicionarCelula034(linha, 9, "Data do movimento");
		adicionarCelula034(linha, 10, "Número do RV");
		adicionarCelula034(linha, 11, "Data do RV");
		adicionarCelula034(linha, 12, "Bandeira");
		adicionarCelula034(linha, 13, "Tipo de transação");
		adicionarCelula034(linha, 14, "Valor bruto do RV");
		adicionarCelula034(linha, 15, "Valor da taxa de desconto");
		adicionarCelula034(linha, 16, "Número da parcela/total");
		adicionarCelula034(linha, 17, "Status do crédito");
		adicionarCelula034(linha, 18, "Número do PV original");
	}

	private void adicionarCelula034(Row linha, int coluna, String valor) {
		Cell cell = linha.createCell(coluna);
		cell.setCellValue(valor);
	}
}
