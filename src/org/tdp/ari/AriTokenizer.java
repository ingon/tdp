package org.tdp.ari;

import java.io.Reader;

import org.tdp.Parser;
import org.tdp.Token;
import org.tdp.ps.ReaderSingleParserSource;
import org.tdp.token.EndToken;
import org.tdp.token.InfixToken;
import org.tdp.token.LiteralToken;
import org.tdp.token.SeparatorToken;

public class AriTokenizer extends ReaderSingleParserSource<Double> {
	private final Token<Double> ADD = new InfixToken<Double>("+", 50) {
		@Override
		public Double execute(Double left, Double right) {
			return left + right;
		}
	};
	private final Token<Double> SUB = new InfixToken<Double>("-", 50) {
		@Override
		public Double nud(Parser<Double> parser) {
			return - parser.expression(100);
		}
		
		@Override
		public Double execute(Double left, Double right) {
			return left - right;
		}
	};
	private final Token<Double> MUL = new InfixToken<Double>("*", 60) {
		@Override
		public Double execute(Double left, Double right) {
			return left * right;
		}
	};
	private final Token<Double> DIV = new InfixToken<Double>("/", 60) {
		@Override
		public Double execute(Double left, Double right) {
			return left / right;
		}
	};
	private final Token<Double> POW = new InfixToken<Double>("^", 70, true) {
		@Override
		public Double execute(Double left, Double right) {
			return Math.pow(left, right);
		}
	};
	private final Token<Double> LPR = new Token<Double>("(", null) {
		@Override
		public Double nud(Parser<Double> parser) {
			Double res = parser.expression(0);
			parser.advance(")");
			return res;
		}
	};
	private final Token<Double> RPR = new SeparatorToken<Double>(")");
	private final Token<Double> END = new EndToken<Double>();
	
	public AriTokenizer(Reader src) {
		super(src);
	}
	
	@Override
	public Token<Double> next() {
		int ch = read();
		while(ch != -1) {
			if(ch != ' ')
				break;
			ch = read();
		}
		
		if(ch == '+') {
			return ADD;
		} else if(ch == '-') {
			return SUB;
		} else if(ch == '*') {
			return MUL;
		} else if(ch == '/') {
			return DIV;
		} else if(ch == '^') {
			return POW;
		} else if(ch == '(') {
			return LPR;
		} else if(ch == ')') {
			return RPR;
		} else if(isnum(ch)) {
			sb.append((char) ch);
			
			while(isnum(ch = read())) {
				sb.append((char) ch);
			}
			unread(ch);

			String text = sb.toString();
			sb.setLength(0);
			return new LiteralToken<Double>("(literal)", text) {
				@Override
				protected Double toValue(String str) {
					return Double.parseDouble(str);
				}
			};
		} else if(ch == -1) {
			return END;
		} else {
			throw new RuntimeException("Unknown token: " + ((char) ch));
		}
	}

	private boolean isnum(int ch) {
		return ch >= '0' && ch <= '9';
	}
}
