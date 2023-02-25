package com.example.geradorXLSX.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Util {

	public static String getName(String nome) {
		ResourceBundle rb = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
		return rb.getString(nome);
	}

	public static String formatarStringToDate(String value) {
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
			Date data = formatoData.parse(value);
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		} catch (Exception e) {
			return null;
		}
	}
}
