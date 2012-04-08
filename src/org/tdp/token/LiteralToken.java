package org.tdp.token;

import org.tdp.Parser;
import org.tdp.Token;

public abstract class LiteralToken<RES> extends Token<RES> {
	private final RES val;
	
	public LiteralToken(String type, String value) {
		super(type, value);
		this.val = toValue(value);
	}
	
	@Override
	public RES nud(Parser<RES> parser) {
		return val;
	}
	
	protected abstract RES toValue(String str);
}
