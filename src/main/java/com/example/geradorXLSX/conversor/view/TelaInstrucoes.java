package com.example.geradorXLSX.conversor.view;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.example.geradorXLSX.conversor.controller.ControleTelaInstrucoes;

public class TelaInstrucoes extends TelaBase{
	private static final long serialVersionUID = 1L;

	private JButton voltar;
	
	private ControleTelaInstrucoes controle;
	
	public TelaInstrucoes() {
		super();
		
		JLabel lb1 = new JLabel("Instruções de uso:");
		lb1.setBounds(12, 12, 600, 20);
		
		JLabel lb2 = new JLabel("1 - Baixe o arquivo EDI no conciliador");
		lb2.setBounds(12, 42, 600, 20);
		
		JLabel lb3 = new JLabel("2 - Clique no botão 'Escolher' e selecione o arquivo no seu diretório de download");
		lb3.setBounds(12, 62, 600, 20);
		
		JLabel lb4 = new JLabel("3 - Ao fim do processamento, cada registro vai estar separado por arquivo na mesma pasta do programa");
		lb4.setBounds(12, 82, 600, 20);
		
		JLabel lb5 = new JLabel("4 - O programa será encerrado, caso deseje converter novo arquivo, é necessário iniciar novamente");
		lb5.setBounds(12, 102, 600, 20);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(483, 129, 117, 35);
		
		controle = new ControleTelaInstrucoes(this);
		voltar.addActionListener(controle);
		
		add(lb1);
		add(lb2);
		add(lb3);
		add(lb4);
		add(lb5);
		
		add(voltar);
		
		setVisible(true);
	}

	public JButton getVoltar() {
		return voltar;
	}
	
	
}
