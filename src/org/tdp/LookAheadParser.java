package org.tdp;

import java.util.ArrayList;
import java.util.List;

public class LookAheadParser<RES> extends Parser<RES> {
    private final List<Token<RES>> laTokens = new ArrayList<Token<RES>>();
    
    public LookAheadParser(Tokenizer<RES> tokens) {
        super(tokens);
    }

    @Override
    public void advance() {
        if(laTokens.isEmpty())
            super.advance();
        else
            token = laTokens.remove(0);
    }
    
    public Token<RES> lookAhead(int ahead) {
        if(ahead == 0)
            return token;
        
        Token<RES> lastToken = null;
        for(int i = 0; i < ahead; i++) {
            laTokens.add(lastToken = tokens.next());
        }
        return lastToken;
    }
    
    public boolean check(String type, int ahead) {
        Token<RES> token = lookAhead(ahead);
        return type.equals(token.type);
    }
    
    public boolean check(Token<RES> token, int ahead) {
        return check(token.type, ahead);
    }
}
