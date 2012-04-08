package org.tdp.ps;

import java.io.IOException;
import java.io.Reader;

import org.tdp.Tokenizer;

public abstract class ReaderSingleParserSource<RES> implements Tokenizer<RES> {
	public final Reader src;
	protected final StringBuilder sb = new StringBuilder();
	private int tmpChar = -1;
	
	public ReaderSingleParserSource(Reader src) {
		this.src = src;
	}

	protected int read() {
		if(tmpChar != -1) {
			int ch = tmpChar;
			tmpChar = -1;
			return ch;
		}
		
		try {
			return src.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void unread(int ch) {
		tmpChar = ch;
	}
}
