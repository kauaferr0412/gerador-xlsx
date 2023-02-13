package com.example.geradorXLSX.conversor.processamento;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.example.geradorXLSX.conversor.processamento.rede.ProcessaRedeEEFI;

public class LayoutIdentify {
	
	public static byte[] identify(File arquivo) throws IOException {
		File arquivoEmCatalogacao = new File(arquivo.getPath());
		try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoEmCatalogacao))) {
			String linha = leitura.readLine();
			System.out.println(linha);
			boolean contemRedeCredito = linha.contains("EEVC" );
			boolean contemRedeDebito = linha.contains("EEVD" );
			boolean contemRedeFinanceiro = linha.contains("EEFI");
			if(contemRedeCredito == true) {
				System.out.println("Layout do tipo Rede crédito!");
			}else if(contemRedeDebito == true){
				System.out.println("Layout do tipo Rede débito");
			}else if(contemRedeFinanceiro == true) {
				System.out.println("Layout do tipo Rede financeiro");
				ProcessaRedeEEFI eefi = new ProcessaRedeEEFI();
				leitura.close();
				byte[] bytesZip =  eefi.gerarPlanilhaRedeEEFI(arquivo);
				System.out.println("Arquivos gerados com sucesso!");
				return bytesZip;
			}
			//gerarPlanilhaRedeEEFI
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
