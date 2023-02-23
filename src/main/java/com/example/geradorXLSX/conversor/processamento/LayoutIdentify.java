package com.example.geradorXLSX.conversor.processamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.example.geradorXLSX.conversor.processamento.rede.ProcessaRedeEEFI;

public class LayoutIdentify {
    private static Logger logger = Logger.getLogger(LayoutIdentify.class);
    private static final String EEVC = "EEVC";
    private static final String EEVD = "EEVD";
    private static final String EEFI = "EEFI";

	public static byte[] identify(File arquivo) throws IOException {
		File arquivoEmCatalogacao = new File(arquivo.getPath());
		try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoEmCatalogacao))) {
			String linha = leitura.readLine();
			boolean contemRedeCredito = linha.contains(EEVC);
			boolean contemRedeDebito = linha.contains(EEVD);
			boolean contemRedeFinanceiro = linha.contains(EEFI);
			if(contemRedeCredito == true) {
		        logger.info("LAYOUT DO TIPO REDE CRÉDITO");
			}else if(contemRedeDebito == true){
		        logger.info("LAYIUT DO TIPO REDE DÉBITO");
			}else if(contemRedeFinanceiro == true) {
		        logger.info("LAYOUT DO TIPO REDE FINANCEIRO");
				ProcessaRedeEEFI eefi = new ProcessaRedeEEFI();
				leitura.close();
				byte[] bytesZip =  eefi.gerarPlanilhaRedeEEFI(arquivo);
		        logger.info("ARQUIVOS GERADOS COM SUCESSO");
				return bytesZip;
			}
			//gerarPlanilhaRedeEEFI
		}catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
