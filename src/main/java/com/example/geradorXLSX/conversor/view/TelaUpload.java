package com.example.geradorXLSX.conversor.view;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.example.geradorXLSX.conversor.controller.ControleTelaUpload;
import com.example.geradorXLSX.conversor.processamento.LayoutIdentify;

public class TelaUpload extends TelaBase{

	private static final long serialVersionUID = 1L;

	private ControleTelaUpload controle;
	
	private JFileChooser arquivo;
	private JFrame frame;
	
	public TelaUpload() throws IOException {
		super();
		
		frame = new JFrame();
		frame.setTitle("Interpretador de arquivos EDI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		LayoutIdentify catalogar = new LayoutIdentify();
		controle = new ControleTelaUpload(this);
		
		arquivo = new JFileChooser();
		arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		arquivo.showOpenDialog(frame);
		File operatedFile = arquivo.getSelectedFile();
		
		catalogar.identify(operatedFile);
		
		add(frame);
		frame.add(arquivo);
		
		setVisible(true);
			
	}

	public ControleTelaUpload getControle() {
		return controle;
	}

	public JFileChooser getArquivo() {
		return arquivo;
	}
	
}
