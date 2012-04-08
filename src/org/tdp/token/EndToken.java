package org.tdp.token;

import org.tdp.Token;

public class EndToken<RES> extends Token<RES> {
	public EndToken() {
		super("(end)", null);
	}
}
