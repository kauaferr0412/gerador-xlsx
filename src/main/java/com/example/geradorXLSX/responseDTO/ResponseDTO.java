package com.example.geradorXLSX.responseDTO;

import java.util.List;

public class ResponseDTO {

	private List<byte[]>  bytes;
	private String tipo;
	
	public List<byte[]> getBytes() {
		return bytes;
	}
	public void setBytes(List<byte[]> bytes) {
		this.bytes = bytes;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
