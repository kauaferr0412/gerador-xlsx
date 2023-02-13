package com.example.geradorXLSX.conversor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.example.geradorXLSX.conversor.view.TelaInstrucoes;
import com.example.geradorXLSX.conversor.view.TelaMenu;
import com.example.geradorXLSX.conversor.view.TelaUpload;

public class ControleTelaMenu implements ActionListener{

	private static TelaMenu tela;
	
	public ControleTelaMenu(TelaMenu tela) {
		ControleTelaMenu.tela = tela;
	}
	public static void mostrarTelaMenu() {
		tela.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tela.getInstrucoes()) {
			new TelaInstrucoes();
			tela.setVisible(false);
		}else if(e.getSource() == tela.getEscolher()) {
			try {
				new TelaUpload();
				tela.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			tela.setVisible(false);
		}else if(e.getSource() == tela.getSair()) {
			System.exit(0);
		}
		
	}
}
