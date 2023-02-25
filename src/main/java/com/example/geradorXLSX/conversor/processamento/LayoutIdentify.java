package com.example.geradorXLSX.conversor.processamento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.example.geradorXLSX.conversor.processamento.rede.ProcessaRedeEEFI;
import com.example.geradorXLSX.util.FileUtil;

public class LayoutIdentify {
	private static Logger logger = Logger.getLogger(LayoutIdentify.class);
	private static final String EEVC = "EEVC";
	private static final String EEVD = "EEVD";
	private static final String EEFI = "EEFI";

	public static byte[] processarArquivos(MultipartFile arquivo) throws IOException {
		File arquivoEmCatalogacao = FileUtil.criarArquivoTemporario(arquivo);
		logger.info("INICIANDO PROCESSAMENTO DO ARQUIVO: " + arquivo.getOriginalFilename());
		try (BufferedReader leitura = new BufferedReader(new FileReader(arquivoEmCatalogacao))) {
			String linha = leitura.readLine();
			boolean contemRedeCredito = linha.contains(EEVC);
			boolean contemRedeDebito = linha.contains(EEVD);
			boolean contemRedeFinanceiro = linha.contains(EEFI);
			if (contemRedeCredito == true) {
				logger.info("LAYOUT DO TIPO REDE CRÉDITO");
			} else if (contemRedeDebito == true) {
				logger.info("LAYIUT DO TIPO REDE DÉBITO");
			} else if (contemRedeFinanceiro == true) {
				logger.info("LAYOUT DO TIPO REDE FINANCEIRO");
				ProcessaRedeEEFI eefi = new ProcessaRedeEEFI();
				leitura.close();
				byte[] bytesZip = eefi.gerarPlanilhaRedeEEFI(arquivoEmCatalogacao);
				logger.info("ARQUIVOS GERADOS COM SUCESSO");
				return bytesZip;
			}
			// gerarPlanilhaRedeEEFI
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
