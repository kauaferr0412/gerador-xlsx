package com.example.geradorXLSX.conversor.view;

import javax.swing.JFrame;

public class TelaBase extends JFrame{

	private static final long serialVersionUID = 1L;

	public TelaBase() {
		setTitle("Conversos de arquivos EDI");
		setSize(640, 480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
	}
}
