package org.tdp;

public abstract class Token<RES> {
	public String type;
	public String value;
	
	public Token(String type) {
		this(type, null);
	}
	
	public Token(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public RES nud(Parser<RES> parser) {
		throw new RuntimeException("Expected token: " + type + "[" + value + "]");
	}
	
	public RES led(Parser<RES> parser, RES left) {
		throw new RuntimeException("Expected operator");
	}
	
	public int lbp() {
		return 0;
	}
}
