package com.example.geradorXLSX.conversor.processamento.rede;

import java.io.BufferedInputStream
;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import com.example.geradorXLSX.conversor.processamento.LayoutIdentify;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro034;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro035;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro036;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro038;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro043;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro044;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro045;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro046;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro047;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro048;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro049;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro053;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro054;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro055;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro056;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro057;
import com.example.geradorXLSX.conversor.rede.models.eefi.Registro069;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha035;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha036;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha038;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha043;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha044;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha045;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha046;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha047;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha048;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha049;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha053;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha054;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha055;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha056;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha057;
import com.example.geradorXLSX.conversor.rede.montadores.eefi.MontarPlanilha069;
import com.example.geradorXLSX.service.JasperReportService;
import com.example.geradorXLSX.util.Util;

import net.sf.jasperreports.engine.JRException;

public class ProcessaRedeEEFI {
    private static Logger logger = Logger.getLogger(LayoutIdentify.class);

	static final String PATH_JASPER = new FileSystemResource("").getFile().getAbsolutePath() + "\\src\\main\\resources\\jasperReport\\processamento_arquivo_034.jrxml";
	static final String PATH_CABECALHO = new FileSystemResource("").getFile().getAbsolutePath() + "\\src\\main\\resources\\cabecalho_report_old.jpg";
	static final String PATH_ARQUIVO_034 = new FileSystemResource("").getFile().getAbsolutePath() + "\\034_Ordem de Crédito.xlsx";
	static final String NOME_ARQUIVO_TEMP = Util.getName("nome_arquivo_temporario");
	static final int TAMANHO_BUFFER = 4096; // 4kb
	private byte[] dados = new byte[TAMANHO_BUFFER];
	
	private JasperReportService jasper = new JasperReportService();
	
	public byte[] gerarPlanilhaRedeEEFI(File arquivoParam) throws IOException, JRException {
		
		//Start process
		String path = arquivoParam.getPath();
		
		File arquivo = new File(path);
		Scanner entrada = new Scanner(arquivo);
		
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
		linhaLeitura.skip(arquivo.length());
		int qtdLinhas = linhaLeitura.getLineNumber();
		
		//EEFI
		List<Registro034> registros034 = new ArrayList<>();
		List<Registro035> registros035 = new ArrayList<>();
		List<Registro053> registros053 = new ArrayList<>();
		List<Registro036> registros036 = new ArrayList<>();
		List<Registro038> registros038 = new ArrayList<>();
		List<Registro054> registros054 = new ArrayList<>();
		List<Registro055> registros055 = new ArrayList<>();
		List<Registro056> registros056 = new ArrayList<>();
		List<Registro057> registros057 = new ArrayList<>();
		List<Registro043> registros043 = new ArrayList<>();
		List<Registro044> registros044 = new ArrayList<>();
		List<Registro045> registros045 = new ArrayList<>();
		List<Registro046> registros046 = new ArrayList<>();
		List<Registro047> registros047 = new ArrayList<>();
		List<Registro048> registros048 = new ArrayList<>();
		List<Registro049> registros049 = new ArrayList<>();
		List<Registro069> registros069 = new ArrayList<>();
		
		String[] registro = new String[qtdLinhas + 1];
		String[] identifySubregistro = new String[qtdLinhas + 1];
		
		//EEFI
		String[] subRegistro034 = new String[19];
		String[] subRegistro035 = new String[34];
		String[] subRegistro036 = new String[19];
		String[] subRegistro053 = new String[11];
		String[] subRegistro038 = new String[31];
		String[] subRegistro054 = new String[11];
		String[] subRegistro055 = new String[11];
		String[] subRegistro056 = new String[11];
		String[] subRegistro057 = new String[10];
		String[] subRegistro043 = new String[14];
		String[] subRegistro044 = new String[26];
		String[] subRegistro045 = new String[26];
		String[] subRegistro046 = new String[22];
		String[] subRegistro047 = new String[22];
		String[] subRegistro048 = new String[22];
		String[] subRegistro049 = new String[17];
		String[] subRegistro069 = new String[25];
		
		try (BufferedReader arq = new BufferedReader(new FileReader(arquivo))){
			
			boolean condit = entrada.hasNext();
			int controle = 0;
			
			while(condit == true && controle < qtdLinhas) {
			
			registro[controle] = arq.readLine();
			
			identifySubregistro[0] = registro[controle].substring(0, 3);	
					
				
			if(identifySubregistro[0].equals("034")){
				registros034.add(Registro034
							.builder()
							.tipo_registro(identifySubregistro[0])
							.num_pv_centralizador(registro[controle].substring(3, 12))
							.num_documento(registro[controle].substring(12, 23))
							.data_lancamento(Util.converterStringToDate(registro[controle].substring(23, 31)))
							.valor_lancamento(registro[controle].substring(31, 46))
							.credito(registro[controle].substring(46, 47))
							.banco(registro[controle].substring(47, 50))
							.agencia(registro[controle].substring(50, 56))
							.conta(registro[controle].substring(56, 67))
							.data_movimento(Util.converterStringToDate(registro[controle].substring(67, 75)))
							.num_rv(registro[controle].substring(75, 84))
							.data_rv(Util.converterStringToDate(registro[controle].substring(84, 92)))
							.bandeira(registro[controle].substring(92, 93))
							.tipo_de_transacao(registro[controle].substring(93, 94))
							.valor_bruto_rv(registro[controle].substring(94, 109))
							.valor_taxa_desconto(registro[controle].substring(109, 124))
							.num_parcel_total(registro[controle].substring(124, 129))
							.status_credito(registro[controle].substring(129, 131))
							.num_pv_original(registro[controle].substring(131, 140))
							.build());
				
			}else if(identifySubregistro[0].equals("035")) {
				Registro035 regis35 = new Registro035();
				
				subRegistro035[0] = identifySubregistro[0];
				regis35.tipoRegistro = subRegistro035[0];
				
				subRegistro035[1] = registro[controle].substring(3, 12);
				regis35.numPvAjustado = subRegistro035[1];
				
				subRegistro035[2] = registro[controle].substring(12, 21);
				regis35.numRvAjustado = subRegistro035[2];
				
				subRegistro035[3] = registro[controle].substring(21, 29);
				regis35.dataAjuste = subRegistro035[3];
				
				subRegistro035[4] = registro[controle].substring(29, 44);
				regis35.valorAjuste = subRegistro035[4];
				
				subRegistro035[5] = registro[controle].substring(44, 45);
				regis35.debito = subRegistro035[5];
				
				subRegistro035[6] = registro[controle].substring(45, 47);
				regis35.motivoAjuste = subRegistro035[6];
				
				subRegistro035[7] = registro[controle].substring(47, 75);
				regis35.motivoAjusteTxt = subRegistro035[7];
				
				subRegistro035[8] = registro[controle].substring(75, 91);
				regis35.numCartao = subRegistro035[8];
				
				subRegistro035[9] = registro[controle].substring(91, 99);
				regis35.dataTransacaoCv = subRegistro035[9];
				
				subRegistro035[10] = registro[controle].substring(99, 108);
				regis35.numRvOriginal = subRegistro035[10];
				
				subRegistro035[11] = registro[controle].substring(108, 123);
				regis35.numRefCartaFax = subRegistro035[11];
				
				subRegistro035[12] = registro[controle].substring(123, 131);
				regis35.dataCarta = subRegistro035[12];
				
				subRegistro035[13] = registro[controle].substring(131, 137);
				regis35.mesRef = subRegistro035[13];
				
				subRegistro035[14] = registro[controle].substring(137, 146);
				regis35.numPvOriginal = subRegistro035[14];
				
				subRegistro035[15] = registro[controle].substring(146, 154);
				regis35.dataRvOriginal = subRegistro035[15];
				
				subRegistro035[16] = registro[controle].substring(154, 169);
				regis35.valorTransacao = subRegistro035[16];
				
				subRegistro035[17] = registro[controle].substring(169, 170);
				regis35.desagendamentoOuNet = subRegistro035[17];
				
				subRegistro035[18] = registro[controle].substring(170, 178);
				regis35.dataCredito = subRegistro035[18];
				
				subRegistro035[19] = registro[controle].substring(178, 193);
				regis35.novoValorParcela = subRegistro035[19];
				
				subRegistro035[20] = registro[controle].substring(193, 208);
				regis35.valorOriginalParcela = subRegistro035[20];
				
				subRegistro035[21] = registro[controle].substring(208, 223);
				regis35.valorBrutoRvOriginal = subRegistro035[21];
				
				subRegistro035[22] = registro[controle].substring(223, 238);
				regis35.valorCancelamentoSolicitado = subRegistro035[22];
				
				subRegistro035[23] = registro[controle].substring(238, 250);
				regis35.numNsu = subRegistro035[23];
				
				subRegistro035[24] = registro[controle].substring(250, 256);
				regis35.numAutorizacao = subRegistro035[24];
				
				subRegistro035[25] = registro[controle].substring(256, 257);
				regis35.tipoDebito = subRegistro035[25];
				
				subRegistro035[26] = registro[controle].substring(257, 268);
				regis35.numOrdemDebito = subRegistro035[26];
						
				subRegistro035[27] = registro[controle].substring(268, 283);
				regis35.valorDebitoTotal = subRegistro035[27];
				
				subRegistro035[28] = registro[controle].substring(283 ,298);
				regis35.valorPedente = subRegistro035[28];
				
				subRegistro035[29] = registro[controle].substring(298, 299);
				regis35.bandeiraRvOrigem = subRegistro035[29];
				
				subRegistro035[30] = registro[controle].substring(299, 300);
				regis35.bandeiraRvAjustado = subRegistro035[30];
				
				subRegistro035[31] = registro[controle].substring(300, 302);
				regis35.numParcelaRvAjustado = subRegistro035[31];
				
				subRegistro035[32] = registro[controle].substring(302, 304);
				regis35.numParcelaRvOriginal = subRegistro035[32];
				
				subRegistro035[33] = registro[controle].substring(304, 312);
				regis35.dataRvAjustado = subRegistro035[33];
				
				registros035.add(regis35);
				
			}else if(identifySubregistro[0].equals("053")) {
				Registro053 regis53 = new Registro053();
				
				subRegistro053[0] = identifySubregistro[0];
				regis53.tipoRegistro = subRegistro053[0];
				
				subRegistro053[1] = registro[controle].substring(3, 19);
				regis53.numCartao = subRegistro053[1];
				
				subRegistro053[2] =  registro[controle].substring(19, 27);
				regis53.dataTransacaoCV = subRegistro053[2];
				
				subRegistro053[3] = registro[controle].substring(27, 36);
				regis53.numRvOriginal = subRegistro053[3];
				
				subRegistro053[4] = registro[controle].substring(36, 45);
				regis53.numPvOriginal = subRegistro053[4];
				
				subRegistro053[5] = registro[controle].substring(45, 60);
				regis53.valorTransacao = subRegistro053[5];
				
				subRegistro053[6] = registro[controle].substring(60, 72);
				regis53.numNsu = subRegistro053[6];
				
				subRegistro053[7] = registro[controle].substring(72, 78);
				regis53.numAutorizacao = subRegistro053[7];
				
				subRegistro053[8] = registro[controle].substring(78, 98);
				regis53.tId = subRegistro053[8];
				
				subRegistro053[9] = registro[controle].substring(98, 128);
				regis53.numPedido = subRegistro053[9];
				
				registros053.add(regis53);
				
			}else if(identifySubregistro[0].equals("036")) { 
				Registro036 regis36 = new Registro036();
				
				subRegistro036[0] = identifySubregistro[0];
				regis36.tipoRegistro = subRegistro036[0];
				
				subRegistro036[1] = registro[controle].substring(3, 12);
				regis36.numPV = subRegistro036[1];
				
				subRegistro036[2] = registro[controle].substring(12, 23);
				regis36.numDocumento = subRegistro036[2];
				
				subRegistro036[3] = registro[controle].substring(23, 31);
				regis36.dataLancamento = subRegistro036[3];
				
				subRegistro036[4] = registro[controle].substring(31, 46);
				regis36.valorLancamento = subRegistro036[4];
				
				subRegistro036[5] = registro[controle].substring(46, 47);
				regis36.credito = subRegistro036[5];
				
				subRegistro036[6] = registro[controle].substring(47, 50);
				regis36.banco = subRegistro036[6];
				
				subRegistro036[7] = registro[controle].substring(50, 56);
				regis36.agencia = subRegistro036[7];
				
				subRegistro036[8] = registro[controle].substring(56, 67);
				regis36.conta = subRegistro036[8];
				
				subRegistro036[9] = registro[controle].substring(67, 76);
				regis36.numRvCorresp = subRegistro036[9];
				
				subRegistro036[10] = registro[controle].substring(76, 84);
				regis36.dataRvCorresp = subRegistro036[10];
				
				subRegistro036[11] = registro[controle].substring(84, 99);
				regis36.valorCreditoOriginal = subRegistro036[11];
				
				subRegistro036[12] = registro[controle].substring(99, 107);
				regis36.dataVencimentoOriginal = subRegistro036[12];
				
				subRegistro036[13] = registro[controle].substring(107, 112);
				regis36.numParcelaTotal = subRegistro036[13];
				
				subRegistro036[14] = registro[controle].substring(112, 127);
				regis36.valorBruto = subRegistro036[14];
				
				subRegistro036[15] = registro[controle].substring(127, 142);
				regis36.valorTaxaDesconto = subRegistro036[15];
				
				subRegistro036[16] = registro[controle].substring(142, 151);
				regis36.numPvOriginal = subRegistro036[16];
				
				subRegistro036[17] = registro[controle].substring(151, 152);
				regis36.bandeira = subRegistro036[17];
				
				registros036.add(regis36);
				
			}else if(identifySubregistro[0].equals("038")) {
				Registro038 regis38 = new Registro038();
				
				subRegistro038[0] = identifySubregistro[0];
				regis38.tipoRegistro = subRegistro038[0];
				
				subRegistro038[1] = registro[controle].substring(3, 12);
				regis38.numeroPv = subRegistro038[1];
				
				subRegistro038[2] = registro[controle].substring(12, 23);
				regis38.numeroDocumento = subRegistro038[2];
				
				subRegistro038[3] = registro[controle].substring(23, 31);
				regis38.dataEmissao = subRegistro038[3];
				
				subRegistro038[4] = registro[controle].substring(31, 46);
				regis38.valorDebito = subRegistro038[4];
				
				subRegistro038[5] = registro[controle].substring(46, 47);
				regis38.debito = subRegistro038[5];
				
				subRegistro038[6] = registro[controle].substring(47, 50);
				regis38.banco = subRegistro038[6];
				
				subRegistro038[7] = registro[controle].substring(50, 56);
				regis38.agencia = subRegistro038[7];
				
				subRegistro038[8] = registro[controle].substring(56, 67);
				regis38.conta = subRegistro038[8];
				
				subRegistro038[9] = registro[controle].substring(67, 76);
				regis38.numeroRvOriginal = subRegistro038[9];
				
				subRegistro038[10] = registro[controle].substring(76, 84);
				regis38.dataRvoriginal = subRegistro038[10];
				
				subRegistro038[11] = registro[controle].substring(84, 99);
				regis38.valorCreditoOriginal = subRegistro038[11];
				
				subRegistro038[12] = registro[controle].substring(99, 101);
				regis38.motivoDebito = subRegistro038[12];
				
				subRegistro038[13] = registro[controle].substring(101, 129);
				regis38.motivoDebitoString = subRegistro038[13];
				
				subRegistro038[14] = registro[controle].substring(129, 145);
				regis38.numeroCartao = subRegistro038[14];
				
				subRegistro038[15] = registro[controle].substring(145, 160);
				regis38.numReferenciaCartaFax = subRegistro038[15];
				
				subRegistro038[16] = registro[controle].substring(160, 166);
				regis38.mesDeReferencia = subRegistro038[16];
				
				subRegistro038[17] = registro[controle].substring(166, 174);
				regis38.dataCarta = subRegistro038[17];
				
				subRegistro038[18] = registro[controle].substring(174, 189);
				regis38.valorCancelamentoSolicitado = subRegistro038[18];
				
				subRegistro038[19] = registro[controle].substring(189, 204);
				regis38.numProcesso = subRegistro038[19];
				
				subRegistro038[20] = registro[controle].substring(204, 213);
				regis38.numPvOriginal = subRegistro038[20];
				
				subRegistro038[21] = registro[controle].substring(213, 221);
				regis38.dataTransacaoCv = subRegistro038[21];
				
				subRegistro038[22] = registro[controle].substring(221, 233);
				regis38.numNsu = subRegistro038[22];
				
				subRegistro038[23] = registro[controle].substring(233, 242);
				regis38.numResumoDebito = subRegistro038[23];
				
				subRegistro038[24] = registro[controle].substring(242, 250);
				regis38.dataDebito = subRegistro038[24];
				
				subRegistro038[25] = registro[controle].substring(250, 265);
				regis38.valorTransacaoOriginal = subRegistro038[25];
				
				subRegistro038[26] = registro[controle].substring(265, 271);
				regis38.numAutorizacao = subRegistro038[26];
				
				subRegistro038[27] = registro[controle].substring(271, 272);
				regis38.tipoDebito = subRegistro038[27];
				
				subRegistro038[28] = registro[controle].substring(272, 287);
				regis38.valorDebitototal = subRegistro038[28];
				
				subRegistro038[29] = registro[controle].substring(287, 302);
				regis38.valorPendente = subRegistro038[29];
				
				subRegistro038[30] = registro[controle].substring(302, 303);
				regis38.bandeiraRvOrigem = subRegistro038[30];
				
				registros038.add(regis38);
				
			}else if(identifySubregistro[0].equals("054")) {
				
				Registro054 regis54 = new Registro054();
				
				subRegistro054[0] = identifySubregistro[0];
				regis54.tipoRegistro = subRegistro054[0];
				
				subRegistro054[1] = registro[controle].substring(3, 12);
				regis54.numRvOriginal = subRegistro054[1];
				
				subRegistro054[2] = registro[controle].substring(12, 28);
				regis54.numCartao = subRegistro054[2];
				
				subRegistro054[3] = registro[controle].substring(28, 37);
				regis54.numPvOriginal = subRegistro054[3];
				
				subRegistro054[4] = registro[controle].substring(37, 45);
				regis54.DataTransacaoCv = subRegistro054[4];
				
				subRegistro054[5] = registro[controle].substring(45, 57);
				regis54.NumNsu = subRegistro054[5];
				
				subRegistro054[6] = registro[controle].substring(57, 72);
				regis54.valorTransacaoOriginal = subRegistro054[6];
				
				subRegistro054[7] = registro[controle].substring(72, 78);
				regis54.numAutorizacao = subRegistro054[7];
				
				subRegistro054[8] = registro[controle].substring(78, 98);
				regis54.tId = subRegistro054[8];
				
				subRegistro054[9] = registro[controle].substring(98, 128);
				regis54.numPedido = subRegistro054[9];
				
				registros054.add(regis54);
				
			}else if(identifySubregistro[0].equals("055")) {
				Registro055 regis55 = new Registro055();
				
				subRegistro055[0] = identifySubregistro[0];
				regis55.tipoRegistro = subRegistro055[0];
				
				subRegistro055[1] = registro[controle].substring(3, 19);
				regis55.numCartao = subRegistro055[1];
				
				subRegistro055[2] = registro[controle].substring(19, 31);
				regis55.numNsu = subRegistro055[2];
				
				subRegistro055[3] = registro[controle].substring(31, 39);
				regis55.dataCvOriginalDaTransacao = subRegistro055[3];
				
				subRegistro055[4] = registro[controle].substring(39, 45);
				regis55.numAutorizacao = subRegistro055[4];
				
				subRegistro055[5] = registro[controle].substring(45, 60);
				regis55.valorTransacaoOriginal = subRegistro055[5];
				
				subRegistro055[6] = registro[controle].substring(60, 69);
				regis55.numRvOriginal = subRegistro055[6];
				
				subRegistro055[7] = registro[controle].substring(69, 78);
				regis55.numPvOriginal = subRegistro055[7];
				
				subRegistro055[8] = registro[controle].substring(78, 98);
				regis55.tId = subRegistro055[8];
				
				subRegistro055[9] = registro[controle].substring(98, 128);
				regis55.numPedido = subRegistro055[9];
				
				registros055.add(regis55);
				
			}else if(identifySubregistro[0].equals("056")) {

				Registro056 regis56 = new Registro056();
				
				subRegistro056[0] = identifySubregistro[0];
				regis56.tipoRegistro = subRegistro056[0];
				
				subRegistro056[1] = registro[controle].substring(3, 19);
				regis56.numCartao = subRegistro056[1];
				
				subRegistro056[2] = registro[controle].substring(19, 31);
				regis56.numNsu = subRegistro056[2];
				
				subRegistro056[3] = registro[controle].substring(31, 39);
				regis56.dataCvOriginalDaTransacao = subRegistro056[3];
				
				subRegistro056[4] = registro[controle].substring(39, 45);
				regis56.numAutorizacao = subRegistro056[4];
				
				subRegistro056[5] = registro[controle].substring(45, 60);
				regis56.valorTransacaoOriginal = subRegistro056[5];
				
				subRegistro056[6] = registro[controle].substring(60, 69);
				regis56.numRvOriginal = subRegistro056[6];
				
				subRegistro056[7] = registro[controle].substring(69, 78);
				regis56.numPvOriginal = subRegistro056[7];
				
				subRegistro056[8] = registro[controle].substring(78, 98);
				regis56.tId = subRegistro056[8];
				
				subRegistro056[9] = registro[controle].substring(98, 128);
				regis56.numPedido = subRegistro056[9];
				
				registros056.add(regis56);
				
			}else if(identifySubregistro[0].equals("057")){

				Registro057 regis57 = new Registro057();
				
				subRegistro057[0] = identifySubregistro[0];
				regis57.tipoRegistro = subRegistro057[0];
				
				subRegistro057[1] = registro[controle].substring(3, 12);
				regis57.numPvOriginal = subRegistro057[1];
				
				subRegistro057[2] = registro[controle].substring(12, 21);
				regis57.numRvOriginal = subRegistro057[2];
				
				subRegistro057[3] = registro[controle].substring(21, 36);
				regis57.valorRvOriginal = subRegistro057[3];
				
				subRegistro057[4] = registro[controle].substring(36, 52);
				regis57.numCartao = subRegistro057[4];
				
				subRegistro057[5] = registro[controle].substring(52, 60);
				regis57.dataTransacao = subRegistro057[5];
				
				subRegistro057[6] = registro[controle].substring(60, 72);
				regis57.nsu = subRegistro057[6];
				
				subRegistro057[7] = registro[controle].substring(72, 92);
				regis57.tId = subRegistro057[7];
				
				subRegistro057[8] = registro[controle].substring(92, 122);
				regis57.numPedido = subRegistro057[8];
								
				registros057.add(regis57);
				
			}else if(identifySubregistro[0].equals("043")) {
				Registro043 regis43 = new Registro043();
				
				subRegistro043[0] = identifySubregistro[0];
				regis43.tipoRegistro = subRegistro043[0];
				
				subRegistro043[1] = registro[controle].substring(3, 12);
				regis43.numPvCreditado = subRegistro043[1];
				
				subRegistro043[2] = registro[controle].substring(12, 21);
				regis43.numResumoCredito = subRegistro043[2];
				
				subRegistro043[3] = registro[controle].substring(21, 32);
				regis43.numDocumento = subRegistro043[3];
				
				subRegistro043[4] = registro[controle].substring(32, 40);
				regis43.dataEmissao = subRegistro043[4];
				
				subRegistro043[5] = registro[controle].substring(40, 48);
				regis43.dataCredito = subRegistro043[5];
				
				subRegistro043[6] = registro[controle].substring(48, 63);
				regis43.valorCredito = subRegistro043[6];
				
				subRegistro043[7] = registro[controle].substring(63, 64);
				regis43.credito = subRegistro043[7];
				
				subRegistro043[8] = registro[controle].substring(64, 67);
				regis43.banco = subRegistro043[8];
				
				subRegistro043[9] = registro[controle].substring(67, 73);
				regis43.agencia = subRegistro043[9];
				
				subRegistro043[10] = registro[controle].substring(73, 84);
				regis43.contaCorrente = subRegistro043[10];
				
				subRegistro043[11] = registro[controle].substring(84, 86);
				regis43.motivoCredito = subRegistro043[11];
				
				subRegistro043[12] = registro[controle].substring(86, 114);
				regis43.motivoCreditoString = subRegistro043[12];
				
				subRegistro043[13] = registro[controle].substring(114, 115);
				regis43.bandeira = subRegistro043[13];
				
				registros043.add(regis43);
				
			}else if(identifySubregistro[0].equals("044")) {
				Registro044 regis44 = new Registro044();
				
				subRegistro044[0] = identifySubregistro[0];
				regis44.tipoRegistro = 	subRegistro044[0];
				
				subRegistro044[1] = registro[controle].substring(3, 12);
				regis44.numeroPv = subRegistro044[1];
				
				subRegistro044[2] = registro[controle].substring(12, 23);
				regis44.numeroOrdemDebito = subRegistro044[2];
				
				subRegistro044[3] = registro[controle].substring(23, 31);
				regis44.dataOrdemDebito = subRegistro044[3];
				
				subRegistro044[4] = registro[controle].substring(31, 46);
				regis44.valorOrdemDebito = subRegistro044[4];
				
				subRegistro044[5] = registro[controle].substring(46, 48);
				regis44.motivoAjuste = subRegistro044[5];
				
				subRegistro044[6] = registro[controle].substring(48, 76);
				regis44.motivoAjusteString = subRegistro044[6];
				
				subRegistro044[7] = registro[controle].substring(76, 92);
				regis44.numCartao = subRegistro044[7];
				
				subRegistro044[8] = registro[controle].substring(92, 104);
				regis44.numNsu = subRegistro044[8];
				
				subRegistro044[9] = registro[controle].substring(104, 112);
				regis44.dataCvOriginalTransacao = subRegistro044[9];
				
				subRegistro044[10] = registro[controle].substring(112, 118);
				regis44.numAutorizacao = subRegistro044[10];
				
				subRegistro044[11] = registro[controle].substring(118, 133);
				regis44.valorTransacaoOriginal = subRegistro044[11];
				
				subRegistro044[12] = registro[controle].substring(133, 142);
				regis44.numRvOriginal = subRegistro044[12];
				
				subRegistro044[13] = registro[controle].substring(142, 150);
				regis44.dataRvOriginal = subRegistro044[13];
				
				subRegistro044[14] = registro[controle].substring(150, 159);
				regis44.numPvOriginal = subRegistro044[14];
				
				subRegistro044[15] = registro[controle].substring(159, 174);
				regis44.numReferenciaCartaFax = subRegistro044[15];
				
				subRegistro044[16] = registro[controle].substring(174, 182);
				regis44.dataCarta = subRegistro044[16];
				
				subRegistro044[17] = registro[controle].substring(182, 197);
				regis44.numProcessoChargeback = subRegistro044[17];
				
				subRegistro044[18] = registro[controle].substring(197, 203);
				regis44.mesReferencia = subRegistro044[18];
				
				subRegistro044[19] = registro[controle].substring(203, 218);
				regis44.valorCompensado = subRegistro044[19];
				
				subRegistro044[20] = registro[controle].substring(218, 226);
				regis44.dataPagamento = subRegistro044[20];
				
				subRegistro044[21] = registro[controle].substring(226, 241);
				regis44.valorPendenteDebito = subRegistro044[21];
				
				subRegistro044[22] = registro[controle].substring(241, 256);
				regis44.numProcessoRetencao = subRegistro044[22];
				
				subRegistro044[23] = registro[controle].substring(256, 258);
				regis44.meioCompensacao = subRegistro044[23];
				
				subRegistro044[24] = registro[controle].substring(258, 286);
				regis44.meioCompensacaoString = subRegistro044[24];
				
				subRegistro044[25] = registro[controle].substring(286, 287);
				regis44.bandeira = subRegistro044[25];
				
				registros044.add(regis44);
			}else if(identifySubregistro[0].equals("045")) {
				
				Registro045 regis45 = new Registro045();
				
				subRegistro045[0] = identifySubregistro[0];
				regis45.tipoRegistro = subRegistro045[0];
				
				subRegistro045[1] = registro[controle].substring(3, 12);
				regis45.numeroPv = subRegistro045[1];
				
				subRegistro045[2] = registro[controle].substring(3, 12);
				regis45.numeroOrdemDebito = subRegistro045[2];
				
				subRegistro045[3] = registro[controle].substring(3, 12);
				regis45.dataOrdemDebito = subRegistro045[3];
				
				subRegistro045[4] = registro[controle].substring(3, 12);
				regis45.valorOrdemDebito = subRegistro045[4];
				
				subRegistro045[5] = registro[controle].substring(3, 12);
				regis45.motivoAjuste = subRegistro045[5];
				
				subRegistro045[6] = registro[controle].substring(3, 12);
				regis45.motivoAjusteString = subRegistro045[6];
				
				subRegistro045[7] = registro[controle].substring(3, 12);
				regis45.numCartao = subRegistro045[7];
				
				subRegistro045[8] = registro[controle].substring(3, 12);
				regis45.numNsu = subRegistro045[8];
				
				subRegistro045[9] = registro[controle].substring(3, 12);
				regis45.dataCvOriginalTransacao = subRegistro045[9];
				
				subRegistro045[10] = registro[controle].substring(3, 12);
				regis45.numAutorizacao = subRegistro045[10];
				
				subRegistro045[11] = registro[controle].substring(3, 12);
				regis45.valorTransacaoOriginal = subRegistro045[11];
				
				subRegistro045[12] = registro[controle].substring(3, 12);
				regis45.numRvOriginal = subRegistro045[12];
				
				subRegistro045[13] = registro[controle].substring(3, 12);
				regis45.dataRvOriginal = subRegistro045[13];
				
				subRegistro045[14] = registro[controle].substring(3, 12);
				regis45.numPvOriginal = subRegistro045[14];
				
				subRegistro045[15] = registro[controle].substring(3, 12);
				regis45.numReferenciaCartaFax = subRegistro045[15];
				
				subRegistro045[16] = registro[controle].substring(3, 12);
				regis45.dataCarta = subRegistro045[16];
				
				subRegistro045[17] = registro[controle].substring(3, 12);
				regis45.numProcessoChargeback = subRegistro045[17];
				
				subRegistro045[18] = registro[controle].substring(3, 12);
				regis45.mesReferencia = subRegistro045[18];
				
				subRegistro045[19] = registro[controle].substring(3, 12);
				regis45.valorLiquidado = subRegistro045[19];
				
				subRegistro045[20] = registro[controle].substring(3, 12);
				regis45.dataLiquidacao = subRegistro045[20];
				
				subRegistro045[21] = registro[controle].substring(3, 12);
				regis45.numProcessoRetencao = subRegistro045[21];
				
				subRegistro045[22] = registro[controle].substring(3, 12);
				regis45.meioCompensacao = subRegistro045[22];
				
				subRegistro045[23] = registro[controle].substring(3, 12);
				regis45.meioCompensacaoString = subRegistro045[23];
				
				subRegistro045[24] = registro[controle].substring(3, 12);
				regis45.bandeira = subRegistro045[24];
						
				registros045.add(regis45);
				
			}else if(identifySubregistro[0].equals("046")) {
				
				Registro046 regis46 = new Registro046();
				
				subRegistro046[0] = identifySubregistro[0];
				regis46.tipo_registro = subRegistro046[0];

				subRegistro046[1] = registro[controle].substring(3, 12);
				regis46.num_EC = subRegistro046[1];
				
				subRegistro046[2] = registro[controle].substring(12, 23);
				regis46.num_OC_ref = subRegistro046[2];
				
				subRegistro046[3] = registro[controle].substring(23, 40);
				regis46.valor_OC_ref =  subRegistro046[3];
				
				subRegistro046[4] = registro[controle].substring(40, 48);
				regis46.data_OC_ref = subRegistro046[4];
					
				subRegistro046[5] = registro[controle].substring(48, 57);
				regis46.num_EC_orig_venda = subRegistro046[5];
					
				subRegistro046[6] = registro[controle].substring(57, 66);
				regis46.num_rv = subRegistro046[6];
					
				subRegistro046[7] = registro[controle].substring(66, 74);
				regis46.data_venda_RV = subRegistro046[7];
					
				subRegistro046[8] = registro[controle].substring(74, 75);
				regis46.transact_parcel_tipo = subRegistro046[8];
				
				subRegistro046[9] = registro[controle].substring(75, 76);
				regis46.code_band  = subRegistro046[9];
					
				subRegistro046[10] = registro[controle].substring(76, 77);
				regis46.negociacao_tipo = subRegistro046[10];
					
				subRegistro046[11] = registro[controle].substring(77, 94);
				regis46.contrato_number = subRegistro046[11];
					
				subRegistro046[12] = registro[controle].substring(94, 109);
				regis46.cnpj_parceiro = subRegistro046[12];
					
				subRegistro046[13] = registro[controle].substring(109, 120);
				regis46.num_doc_OC_neg = subRegistro046[13];
					
				subRegistro046[14] = registro[controle].substring(120, 137);
				regis46.valor_negoc = subRegistro046[14];
					
				subRegistro046[15] = registro[controle].substring(137, 145);
				regis46.data_negociacao = subRegistro046[15];

				subRegistro046[16] = registro[controle].substring(145, 153);
				regis46.data_liquidacao = subRegistro046[16];
					
				subRegistro046[17] = registro[controle].substring(153, 156);
				regis46.bank_number = subRegistro046[17];
					
				subRegistro046[18] = registro[controle].substring(156, 162);
				regis46.bank_branch_number = subRegistro046[18];
					
				subRegistro046[19] = registro[controle].substring(162, 173);
				regis46.bank_account_number = subRegistro046[19];
					
				subRegistro046[20] = registro[controle].substring(173, 175);
				regis46.status_credito = subRegistro046[20];
					
				subRegistro046[21] = registro[controle].substring(175, 177);
				regis46.parcela_numero = subRegistro046[21];
			
				registros046.add(regis46);
				
			} else if(identifySubregistro[0].equals("047")) {
				
				Registro047 regis47 = new Registro047();
				
				subRegistro047[0] = identifySubregistro[0];
				regis47.tipo_registro = subRegistro047[0];
				
				subRegistro047[1] = registro[controle].substring(3, 12);
				regis47.num_EC = subRegistro047[1];
				
				subRegistro047[2] = registro[controle].substring(12, 23);
				regis47.num_OC_ref = subRegistro047[2];
				
				subRegistro047[3] = registro[controle].substring(23, 40);
				regis47.valor_OC_ref =  subRegistro047[3];
				
				subRegistro047[4] = registro[controle].substring(40, 48);
				regis47.data_OC_ref = subRegistro047[4];
					
				subRegistro047[5] = registro[controle].substring(48, 57);
				regis47.num_EC_orig_venda = subRegistro047[5];
					
				subRegistro047[6] = registro[controle].substring(57, 66);
				regis47.num_rv = subRegistro047[6];
					
				subRegistro047[7] = registro[controle].substring(66, 74);
				regis47.data_venda_RV = subRegistro047[7];
					
				subRegistro047[8] = registro[controle].substring(74, 75);
				regis47.transact_parcel_tipo = subRegistro047[8];
				
				subRegistro047[9] = registro[controle].substring(75, 76);
				regis47.code_band  = subRegistro047[9];
					
				subRegistro047[10] = registro[controle].substring(76, 77);
				regis47.negociacao_tipo = subRegistro047[10];
					
				subRegistro047[11] = registro[controle].substring(77, 94);
				regis47.contrato_number = subRegistro047[11];
					
				subRegistro047[12] = registro[controle].substring(94, 109);
				regis47.cnpj_parceiro = subRegistro047[12];
					
				subRegistro047[13] = registro[controle].substring(109, 120);
				regis47.num_doc_OC_neg = subRegistro047[13];
					
				subRegistro047[14] = registro[controle].substring(120, 137);
				regis47.valor_negoc = subRegistro047[14];
					
				subRegistro047[15] = registro[controle].substring(137, 145);
				regis47.data_negociacao = subRegistro047[15];

				subRegistro047[16] = registro[controle].substring(145, 153);
				regis47.data_liquidacao = subRegistro047[16];
					
				subRegistro047[17] = registro[controle].substring(153, 156);
				regis47.bank_number = subRegistro047[17];
					
				subRegistro047[18] = registro[controle].substring(156, 162);
				regis47.bank_branch_number = subRegistro047[18];
					
				subRegistro047[19] = registro[controle].substring(162, 173);
				regis47.bank_account_number = subRegistro047[19];
					
				subRegistro047[20] = registro[controle].substring(173, 175);
				regis47.status_credito = subRegistro047[20];
					
				subRegistro047[21] = registro[controle].substring(175, 177);
				regis47.parcela_numero = subRegistro047[21];
				
				registros047.add(regis47);
				
			}else if(identifySubregistro[0].equals("048")) {
				Registro048 regis48 = new Registro048();
				
				subRegistro048[0] = identifySubregistro[0];
				regis48.tipoRegistro = subRegistro048[0];
				
				subRegistro048[1] = registro[controle].substring(3, 12);
				regis48.numEstabelecimento = subRegistro048[1];
				
				subRegistro048[2] = registro[controle].substring(12, 23);
				regis48.numDocumentoOc = subRegistro048[2];
				
				subRegistro048[3] = registro[controle].substring(23, 40);
				regis48.valorOcReferencia = subRegistro048[3];
				
				subRegistro048[4] = registro[controle].substring(40, 48);
				regis48.dataLancamentoOc = subRegistro048[4];
				
				subRegistro048[5] = registro[controle].substring(48, 57);
				regis48.numEcOriginalVenda = subRegistro048[5];
				
				subRegistro048[6] = registro[controle].substring(57, 66);
				regis48.numRV = subRegistro048[6];
				
				subRegistro048[7] = registro[controle].substring(66, 74);
				regis48.dataVendaDoRV = subRegistro048[7];
				
				subRegistro048[8] = registro[controle].substring(74, 75);
				regis48.identificaTransacaoAvistaParcelada = subRegistro048[8];
				
				subRegistro048[9] = registro[controle].substring(75, 76);
				regis48.codigoBandeira = subRegistro048[9];
				
				subRegistro048[10] = registro[controle].substring(76, 77);
				regis48.tipoNegociacao = subRegistro048[10];
				
				subRegistro048[11] = registro[controle].substring(77, 94);
				regis48.numeroContratoNegociacao = subRegistro048[11];
				
				subRegistro048[12] = registro[controle].substring(94, 109);
				regis48.numCnpjParceiro = subRegistro048[12];
				
				subRegistro048[13] = registro[controle].substring(109, 120);
				regis48.numDocOCGeradoNegoc = subRegistro048[13];
				
				subRegistro048[14] = registro[controle].substring(120, 137);
				regis48.valorNegociado = subRegistro048[14];
				
				subRegistro048[15] = registro[controle].substring(137, 145);
				regis48.dataNegociacao = subRegistro048[15];
				
				subRegistro048[16] = registro[controle].substring(145, 153);
				regis48.dataLiquidacao = subRegistro048[16];
				
				subRegistro048[17] = registro[controle].substring(153, 156);
				regis48.banco = subRegistro048[17];
				
				subRegistro048[18] = registro[controle].substring(156, 162);
				regis48.agencia = subRegistro048[18];
				
				subRegistro048[19] = registro[controle].substring(162, 173);
				regis48.contaCorrente = subRegistro048[19];
				
				subRegistro048[20] = registro[controle].substring(173, 175);
				regis48.statusCredito = subRegistro048[20];
				
				subRegistro048[21] = registro[controle].substring(175, 177);
				regis48.numParcela = subRegistro048[21];
				
				registros048.add(regis48);
			} else if(identifySubregistro[0].equals("049")) {

				Registro049 regis49 = new Registro049();
				
				subRegistro049[0] = identifySubregistro[0];
				regis49.tipoRegistro = subRegistro049[0];
				
				subRegistro049[1] = registro[controle].substring(3, 12);
				regis49.numPvOriginal = subRegistro049[1];
				
				subRegistro049[2] = registro[controle].substring(12, 21);
				regis49.numRvOriginal = subRegistro049[2];
				
				subRegistro049[3] = registro[controle].substring(21, 36);
				regis49.numReferencia = subRegistro049[3];
				
				subRegistro049[4] = registro[controle].substring(36, 44);
				regis49.dataDoCredito = subRegistro049[4];
				
				subRegistro049[5] = registro[controle].substring(44, 59);
				regis49.novoValorParcela = subRegistro049[5];
				
				subRegistro049[6] = registro[controle].substring(59, 74);
				regis49.valorOriginalParcelaAlterada = subRegistro049[6];
				
				subRegistro049[7] = registro[controle].substring(74, 89);
				regis49.valorAjuste = subRegistro049[7];
				
				subRegistro049[8] = registro[controle].substring(89, 97);
				regis49.dataCancelamento = subRegistro049[8];
				
				subRegistro049[9] = registro[controle].substring(97, 112);
				regis49.valorRvOriginal = subRegistro049[9];
				
				subRegistro049[10] = registro[controle].substring(112, 127);
				regis49.valorCancelamentoSolicitado = subRegistro049[10];
				
				subRegistro049[11] = registro[controle].substring(127, 143);
				regis49.numCartao = subRegistro049[11];
				
				subRegistro049[12] = registro[controle].substring(143, 151);
				regis49.dataTransacao = subRegistro049[12];
				
				subRegistro049[13] = registro[controle].substring(151, 163);
				regis49.nsu = subRegistro049[13];
				
				subRegistro049[14] = registro[controle].substring(163, 164);
				regis49.tipoDebito = subRegistro049[14];
				
				subRegistro049[15] = registro[controle].substring(164, 166);
				regis49.numParcela = subRegistro049[15];
				
				subRegistro049[16] = registro[controle].substring(166, 167);
				regis49.bandeiraRvOrigem = subRegistro049[16];
				
				registros049.add(regis49);
				
			}else if(identifySubregistro[0].equals("069")) {

				Registro069 regis69 = new Registro069();
				
				subRegistro069[0] = identifySubregistro[0];
				regis69.tipoRegistro = subRegistro069[0];
				
				subRegistro069[1] = registro[controle].substring(3, 12);
				regis69.numPvOriginal = subRegistro069[1];
				
				subRegistro069[2] = registro[controle].substring(12, 21);
				regis69.numRvOriginal = subRegistro069[2];
				
				subRegistro069[3] = registro[controle].substring(21, 29);
				regis69.dataRvOriginal = subRegistro069[3];
				
				subRegistro069[4] = registro[controle].substring(29, 38);
				regis69.numNovoPvAjustado = subRegistro069[4];
				
				subRegistro069[5] = registro[controle].substring(38, 47);
				regis69.numNovoRvAjustado = subRegistro069[5];
				
				subRegistro069[6] = registro[controle].substring(47, 62);
				regis69.novoValorParcelaAjustado = subRegistro069[6];
				
				subRegistro069[7] = registro[controle].substring(62, 77);
				regis69.valorOriginalParcelaalterada = subRegistro069[7];
				
				subRegistro069[8] = registro[controle].substring(77, 92);
				regis69.numReferencia = subRegistro069[8];
				
				subRegistro069[9] = registro[controle].substring(92, 100);
				regis69.dataCreditoRvAjustado = subRegistro069[9];
				
				subRegistro069[10] = registro[controle].substring(100, 115);
				regis69.valorAjuste = subRegistro069[10];
				
				subRegistro069[11] = registro[controle].substring(115, 117);
				regis69.numParcelaRvOriginal = subRegistro069[11];
				
				subRegistro069[12] = registro[controle].substring(117, 125);
				regis69.dataCancelamento = subRegistro069[12];
				
				subRegistro069[13] = registro[controle].substring(125, 140);
				regis69.valorCancelamentoSolicitado = subRegistro069[14];
				
				subRegistro069[14] = registro[controle].substring(140, 156);
				regis69.numCartao = subRegistro069[14];
				
				subRegistro069[15] = registro[controle].substring(156, 164);
				regis69.dataTransacao = subRegistro069[15];
				
				subRegistro069[16] = registro[controle].substring(164, 176);
				regis69.nsu = subRegistro069[16];
				
				subRegistro069[17] = registro[controle].substring(176, 177);
				regis69.tipoDebito = subRegistro069[17];
				
				subRegistro069[18] = registro[controle].substring(177, 179);
				regis69.numParcelaRvAjustado = subRegistro069[18];
				
				subRegistro069[19] = registro[controle].substring(179, 180);
				regis69.bandeiraRvAjustado = subRegistro069[19];
				
				subRegistro069[20] = registro[controle].substring(180, 188);
				regis69.dataRvAjustado = subRegistro069[20];
				
				subRegistro069[21] = registro[controle].substring(188, 189);
				regis69.tipoNegociacao = subRegistro069[21];
				
				subRegistro069[22] = registro[controle].substring(189, 206);
				regis69.numContratoNegociacao = subRegistro069[22];
				
				subRegistro069[23] = registro[controle].substring(206, 221);
				regis69.numCnpjParceiro = subRegistro069[23];
				
				subRegistro069[24] = registro[controle].substring(221, 229);
				regis69.dataNegociacao = subRegistro069[24];
				
				registros069.add(regis69);
			}
				controle++;
				
				linhaLeitura.close();
			}
			
			//EEFI
			MontarPlanilha035 start035 = new MontarPlanilha035();
			MontarPlanilha036 start036 = new MontarPlanilha036();
			MontarPlanilha053 start053 = new MontarPlanilha053();
			MontarPlanilha038 start038 = new MontarPlanilha038();
			MontarPlanilha054 start054 = new MontarPlanilha054();
			MontarPlanilha043 start043 = new MontarPlanilha043();
			MontarPlanilha046 start046 = new MontarPlanilha046();
			MontarPlanilha047 start047 = new MontarPlanilha047();
			MontarPlanilha055 start055 = new MontarPlanilha055();
			MontarPlanilha056 start056 = new MontarPlanilha056();
			MontarPlanilha057 start057 = new MontarPlanilha057();
			MontarPlanilha048 start048 = new MontarPlanilha048();
			MontarPlanilha044 start044 = new MontarPlanilha044();
			MontarPlanilha045 start045 = new MontarPlanilha045();
			MontarPlanilha049 start049 = new MontarPlanilha049();
			MontarPlanilha069 start069 = new MontarPlanilha069();
			
			//Null validation
			List<String> arquivos = new ArrayList<>();
			arquivos.add(arquivoParam.getName());
			//EEFI
			if(registros034.size() > 0) {
				 arquivos.add(jasper.exportReport(registros034, PATH_JASPER, PATH_CABECALHO, PATH_ARQUIVO_034));
			}
			if(registros035.size() > 0) {
				start035.criarArquivo035("035_Ajustes Net e Desagendamento.xlsx", registros035);
				 arquivos.add("035_Ajustes Net e Desagendamento.xlsx");
			}
			if(registros036.size() > 0) {
				start036.criarArquivo036("036_Antecipações.xlsx", registros036);
				 arquivos.add("036_Antecipações.xlsx");
			}
			if(registros038.size() > 0) {
				start038.criarArquivo038("038_Ajustes a débito(Via Banco).xlsx", registros038);
				 arquivos.add("038_Ajustes a débito(Via Banco).xlsx");
			}
			if(registros043.size() > 0) {
				start043.criarArquivo043("043_Ajustes a Créditos.xlsx", registros043);
				 arquivos.add("043_Ajustes a Créditos.xlsx");

			}
			if(registros044.size() > 0) {
				start044.criarArquivo044("044_Débitos Pendentes.xlsx", registros044);
				 arquivos.add("044_Débitos Pendentes.xlsx");

			}
			if(registros045.size() > 0) {
				start045.criarArquivo045("045_Débitos liquidados.xlsx", registros045);
				 arquivos.add("045_Débitos liquidados.xlsx");

			}
			if(registros046.size() > 0) {
				start046.criarArquivo046("046_Transações Negociadas e Liquidadas(vendas crédito).xlsx", registros046);
				 arquivos.add("046_Transações Negociadas e Liquidadas(vendas crédito).xlsx");

			}
			if(registros047.size() > 0) {
				start047.criarArquivo047("047_Transações em negociação(vendas crédito).xlsx", registros047);
				 arquivos.add("047_Transações em negociação(vendas crédito).xlsx");

			}
			if(registros048.size() > 0) {
				start048.criarArquivo048("048_Negociações desfeitas.xlsx", registros048);
				 arquivos.add("048_Negociações desfeitas.xlsx");

			}
			if(registros049.size() > 0) {
				start049.criarArquivo049("049_Desagendamento de parcelas.xlsx", registros049);
				 arquivos.add("049_Desagendamento de parcelas.xlsx");

			}
			if(registros053.size() > 0) {
				start053.criarArquivo053("053_Ajustes Net e Desagendamentos (E-Commerce).xlsx", registros053);
				 arquivos.add("053_Ajustes Net e Desagendamentos (E-Commerce).xlsx");

			}
			if(registros054.size() > 0) {
				start054.criarArquivo054("054_Ajustes a Débito(via Banco- E-Commerce).xlsx", registros054);
				 arquivos.add("054_Ajustes a Débito(via Banco- E-Commerce).xlsx");

			}
			if(registros055.size() > 0) {
				start055.criarArquivo055("055_Débitos Pendentes(E-Commerce).xlsx", registros055);
				 arquivos.add("055_Débitos Pendentes(E-Commerce).xlsx");
				 

			}
			if(registros056.size() >0) {
				start056.criarArquivo056("056_Débitos Liquidados(E-Commerce).xlsx", registros056);
				 arquivos.add("056_Débitos Liquidados(E-Commerce).xlsx");

			}
			if(registros057.size() > 0) {
				start057.criarArquivo057("057_Desagendamento de parcelas (e-commerce).xlsx", registros057);
				 arquivos.add("057_Desagendamento de parcelas (e-commerce).xlsx");

			}
			if(registros069.size() > 0) {
				start069.criarArquivo069("069_ Desagendamento de parcelas.xlsx", registros069);
				 arquivos.add("069_ Desagendamento de parcelas.xlsx");

			}
			arq.close();	
			entrada.close();
			byte[] bytesZip = gerarBytesZip(arquivos);
			Util.deletarArquivosTemporario(arquivos);
			return bytesZip;
		}
	}
	
	

	public byte[] gerarBytesZip(List<String> arquivos) throws IOException {
		int cont;
		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		FileOutputStream destino = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;
		try {
			destino = new FileOutputStream(new FileSystemResource("").getFile().getAbsolutePath() + NOME_ARQUIVO_TEMP);
			saida = new ZipOutputStream(new BufferedOutputStream(destino));
			for (int contador = 0; contador < arquivos.size(); contador++) {
				File fileTemp = new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\" + arquivos.get(contador));
				streamDeEntrada = new FileInputStream(fileTemp);
				origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
				entry = new ZipEntry(fileTemp.getName());
				saida.putNextEntry(entry);
				while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
					saida.write(dados, 0, cont);
					
				}
				origem.close();
				streamDeEntrada.close();
				origem = null;
				streamDeEntrada = null;
			}
			saida.close();
			destino.close();
			saida = null;
			destino = null;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		var fileTemp = new File(new FileSystemResource("").getFile().getAbsolutePath() + NOME_ARQUIVO_TEMP);
		var bytesFileTemp = FileUtils.readFileToByteArray(fileTemp);
		fileTemp.delete();
		return bytesFileTemp;
	}
}
