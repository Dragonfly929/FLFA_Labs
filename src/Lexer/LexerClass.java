package Lexer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexerClass {

    BufferedReader reader;
    char current;
    List<Token> tokenList = new ArrayList<>();
    public static final String[] KEY_WORDS = new String[]{
            "import", "class", "while", "if", "else", "public",
            "private", "protected", "switch", "case", "super",
            "static", "implements", "interface", "package", "new",
            "continue", "try", "this", "final", "byte", "int", "char",
            "String", "float", "double", "boolean", "return"};

    public LexerClass(File file) {

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        current = readNextChar();
    }

    List<Token> generateTokens() {
        Token token = readNextToken();
        while (token != null) {
            tokenList.add(token);
            token = readNextToken();
        }
        return tokenList;
    }

    Token readNextToken() {
        int state = 1;

        while (true) {
            if (current == (char) (-1)) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            switch (state) {
                case 1 -> {
                    if (current == ' ' || current == '\n' || current == '\t' ||
                            current == '\f' || current == '\b' || current == '\r') {
                        current = readNextChar();
                    } else if (current == ';') {
                        current = readNextChar();
                        return new Token("Semicolon", ";");
                    } else if (current == '+') {
                        current = readNextChar();
                        return new Token("Plus Operator", "+");
                    } else if (current == '-') {
                        current = readNextChar();
                        return new Token("Minus Operator", "-");
                    } else if (current == '*') {
                        current = readNextChar();
                        return new Token("Multiplication Operator", "*");
                    } else if (current == '/') {
                        current = readNextChar();
                        return new Token("Division Operator", "/");
                    } else if (current == '%') {
                        current = readNextChar();
                        return new Token("Remainder Operator", "%");
                    } else if (current == '{') {
                        current = readNextChar();
                        return new Token("Left Bracket", "{");
                    } else if (current == '}') {
                        current = readNextChar();
                        return new Token("Right Bracket", "}");
                    } else if (current == '(') {
                        current = readNextChar();
                        return new Token("Left Parenthesis", "(");
                    } else if (current == ')') {
                        current = readNextChar();
                        return new Token("Right Parenthesis", ")");
                    } else if (current == ',') {
                        current = readNextChar();
                        return new Token("Comma", ",");
                    } else if (current == '=') {
                        current = readNextChar();
                        if (current == '=') {
                            current = readNextChar();
                            return new Token("Equal Operator", "==");
                        } else {
                            return new Token("Assign Operator", "=");
                        }
                    } else if (current == '!') {
                        current = readNextChar();
                        if (current == '=') {
                            current = readNextChar();
                            return new Token("Not Equal Operator ", "!=");
                        } else return new Token("Not Defined", "!");
                    } else if (current == '&') {
                        current = readNextChar();
                        if (current == '&') {
                            current = readNextChar();
                            return new Token("Conditional And", "&&");
                        } else return new Token("Not Defined", "&");
                    } else if (current == '|') {
                        current = readNextChar();
                        if (current == '|') {
                            current = readNextChar();
                            return new Token("Conditional Or", "||");
                        } else return new Token("Not Defined", "|");
                    } else {
                        state = 2;
                    }
                }
                case 2 -> {
                    {
                        if (isNumber(current)) {
                            StringBuilder num = new StringBuilder(String.valueOf(current));
                            for (; ;) {
                                current = readNextChar();
                                if (isNumber(current) || current == '.') {
                                    num.append(current);
                                } else {
                                    if (num.toString().contains(".")) {
                                        return new Token("\u001B[32m" + "Decimal"  + "\u001B[0m", num.toString());
                                    } else return new Token( "\u001B[36m" + "Integer"  + "\u001B[0m", num.toString());
                                }
                            }
                        }
                    }
                    {
                        if (isLetter(current) || current == '_') {
                            String word = String.valueOf(current);
                            for (; ; ) {
                                current = readNextChar();
                                if (isLetter(current) || current == '_' || isNumber(current)) {
                                    word += String.valueOf(current);
                                } else {
                                    List<String> key_words = Arrays.asList(KEY_WORDS);

                                    if (key_words.contains(word))
                                        return new Token("\u001B[34m" + "Keyword" + "\u001B[0m", word);
                                    else {
                                        return new Token("\u001B[35m" + "Identifier" + "\u001B[0m", word);
                                    }
                                }
                            }
                        } else {
                            current = readNextChar();
                            return new Token("\u001B[31m"+"Error"+"\u001B[0m", "Not Defined " + current);
                        }
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + state);
            }
        }
    }

    char readNextChar() {
        try {
            return (char) reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) (-1);
    }

    boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z')
            return true;
        return c >= 'A' && c <= 'Z';
    }
}
