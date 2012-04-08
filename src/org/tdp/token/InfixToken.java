package org.tdp.token;

import org.tdp.Parser;
import org.tdp.Token;

public abstract class InfixToken<RES> extends Token<RES> {
	public final int lbp;
	public final int rbp;
	
	public InfixToken(String type, int lbp) {
		this(type, null, lbp);
	}

	public InfixToken(String type, int lbp, boolean right) {
		this(type, null, lbp, right);
	}

	public InfixToken(String type, String value, int lbp) {
		this(type, value, lbp, false);
	}
	
	public InfixToken(String type, String value, int lbp, boolean right) {
		super(type, value);
		this.lbp = lbp;
		this.rbp = right ? lbp - 1 : lbp;
	}
	
	@Override
	public RES led(Parser<RES> parser, RES left) {
		RES right = parser.expression(rbp);
		return execute(left, right);
	}
	
	public abstract RES execute(RES left, RES right);
	
	@Override
	public int lbp() {
		return lbp;
	}
}
