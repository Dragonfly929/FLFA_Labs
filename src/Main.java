import automaton.FiniteAutomaton;
import grammar.Grammar;

import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // text color
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        //  background
        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN

        // {V_n, V_t, P, S} - grammar
        String[] Vn = {"S", "A", "B", "C"}; // Non-terminal symbols
        String[] Vt = {"a", "b"};  // Terminal symbols
        String[] productionLeft = {"S", "A", "A", "B", "C", "A", "B"};  // left side of production
        String[] productionRight = {"bA", "b", "aB", "bC", "cA", "bA", "aB"};  // right side of production
        String initialState = String.valueOf("S");

        System.out.println(ANSI_GREEN_BACKGROUND + "Lab1: Intro to formal languages. Regular grammars. Finite Automata." + ANSI_RESET);
        Grammar grammar = new Grammar(Vn, Vt, productionLeft, productionRight, initialState);
        System.out.print(grammar.generateWords(5));

        FiniteAutomaton fa = grammar.toFiniteAutomaton("F");
        fa.wordIsValid("bb");
        fa.wordIsValid("abc");


        System.out.println("\n\n"+ANSI_GREEN_BACKGROUND + "Lab2: Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy." + ANSI_RESET);
        HashSet<String> q = new HashSet<>(List.of("q0", "q1", "q2"));
        HashSet<String> alphabet = new HashSet<>(List.of("a", "b"));

        String finalState = "q2";
        String initialState1 = "q0";
        FiniteAutomaton fa1 = new FiniteAutomaton(q, alphabet, initialState1, finalState);
        fa1.setTransition("q0", "a", "q1");
        fa1.setTransition("q0", "a", "q0");
        fa1.setTransition("q1", "b", "q1");
        fa1.setTransition("q1", "a", "q2");
        fa1.setTransition("q2", "b", "q2");
       // fa1.setTransition("q2", "a", "q0");


        Grammar gr = fa1.toGrammar(); //converting FiniteAutomaton to Grammar

        System.out.println("Production list of converted grammar: " + gr.getProductions());  //production list

        gr.grammarType(); //checking the grammar type

        System.out.println("\n-----Before conversion-----");
        fa1.isNFA(); // This FA should be non-deterministic

        fa1.convertToDFA(); //Convert NFA to DFA

//        System.out.println(finiteAutomaton1.getTransitions()); //visualize new transitions. Not required

        System.out.println("\n-----After conversion-----");
        fa1.isNFA();  // Now this should be deterministic
    }
}
