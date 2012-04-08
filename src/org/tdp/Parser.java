package org.tdp;

public class Parser<RES> {
	public final Tokenizer<RES> tokens;
	protected Token<RES> token;
	
	public Parser(Tokenizer<RES> tokens) {
		this.tokens = tokens;
	}

	public RES parse() {
		advance();
		return expression(0);
	}
	
	public RES expression(int rbp) {
	    Token<RES> t = token;
		advance();
		RES left = t.nud(this);
		
		while(rbp < token.lbp()) {
			t = token;
			advance();
			
			left = t.led(this, left);
		}
		
		return left;
	}
	
	public void advance() {
		token = tokens.next();
	}
	
	public void advance(String type) {
		expect(type);
		advance();
	}

	public void advanceAndExpect(String type) {
		advance();
		expect(type);
	}

	public void advanceAndExpect(String current, String next) {
		advance(current);
		expect(next);
	}
	
	public boolean check(String type) {
		return type.equals(token.type);
	}

	public void advance(Token<RES> token) {
		advance(token.type);
	}

	public void advanceAndExpect(Token<RES> token) {
		advanceAndExpect(token.type);
	}

	public boolean check(Token<RES> token) {
		return check(token.type);
	}

	public void expect(String type) {
		if(type != null && !type.equals(token.type))
			throw new RuntimeException("Expected: " + type + " but encountered: " + token.type);
	}

	public Token<RES> token() {
		return token;
	}
	
	public String val() {
		return token.value;
	}
}
