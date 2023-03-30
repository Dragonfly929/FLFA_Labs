package Lexer;

public class Token {
    String token;
    String lexeme;

    public Token(String token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }
    public String toString() {
        return formatOutPut(lexeme, token);
    }
    String formatOutPut(String l,String t){
        return l + " ".repeat(Math.max(0, 16 - l.length())) + token;
    }
}
