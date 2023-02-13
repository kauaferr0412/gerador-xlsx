package com.example.geradorXLSX.conversor.view;

import java.awt.FlowLayout;

import javax.swing.JButton;

import com.example.geradorXLSX.conversor.controller.ControleTelaMenu;

public class TelaMenu extends TelaBase{
	private static final long serialVersionUID = 1L;
	
	private JButton instrucoes;
	private JButton escolher;
	private JButton sair;
	 
	private ControleTelaMenu controle;
	
	public  TelaMenu() {
		super();
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		instrucoes = new JButton("Instruções de uso");
		escolher = new JButton("Escolha o arquivo");
		sair = new JButton("Sair");
		
		add(instrucoes);
		add(escolher);
		add(sair);
		
		controle = new ControleTelaMenu(this);
		
		instrucoes.addActionListener(controle);
		escolher.addActionListener(controle);
		sair.addActionListener(controle);
		
		setVisible(true);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getInstrucoes() {
		return instrucoes;
	}

	public JButton getEscolher() {
		return escolher;
	}

	public JButton getSair() {
		return sair;
	}
	
	
}
