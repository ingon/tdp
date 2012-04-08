package org.tdp.ari;

import java.io.StringReader;

import org.tdp.Parser;

public class Ari {
	public static void main(String[] args) {
		AriTokenizer lexer = new AriTokenizer(new StringReader("3 * (2 + -4) ^ 4"));
//		AriParserSource lexer = new AriParserSource(new StringReader("3 - 2 + 4 * -5"));
		Parser<Double> parser = new Parser<Double>(lexer);
		Double res = parser.parse();
		
		System.out.println("RES: " + res);
	}
}
