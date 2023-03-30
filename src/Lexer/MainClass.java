package Lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        // text color
        final String RESET = "\u001B[0m";
        final String CYAN_BOLD_BRIGHT = "\033[1;96m";

        for (String s : Arrays.asList(CYAN_BOLD_BRIGHT + "\n   Lexical Analysis" + RESET, "______________________", "Lexemes         Tokens", "______________________")) {
            out.println(s);
        }
        //Scanner reader = new Scanner(new File("C:\\Users\\zaica\\IdeaProjects\\FLFA_Labs\\src\\LexicalAnalyzer\\input.txt"));
        File file;
        try {
            file = new File("C:\\Users\\zaica\\IdeaProjects\\FLFA_Labs\\src\\Lexer\\input.txt");
            LexerClass lexerClass = new LexerClass(file);
            List<Token> tokenList = lexerClass.generateTokens();
            for (Token token : tokenList) {
                out.println(token.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
