package com.example.geradorXLSX.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {
	
	@Autowired
	private MessageSource messageSource;

	public String getMensagem(String chave) {
		return messageSource.getMessage(chave, null, LocaleContextHolder.getLocale());
	}
}