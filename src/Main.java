import CNF.CFGtoCNFConverter;
import automaton.FiniteAutomaton;
import grammar.Grammar;

import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // text color
        final String RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        final String CYAN_BOLD = "\033[1;96m";

        // {V_n, V_t, P, S} - grammar
        String[] Vn = {"S", "A", "B", "C"}; // Non-terminal symbols
        String[] Vt = {"a", "b"};  // Terminal symbols
        String[] productionLeft = {"S", "A", "A", "B", "C", "A", "B"};  // left side of production
        String[] productionRight = {"bA", "b", "aB", "bC", "cA", "bA", "aB"};  // right side of production
        String initialState = String.valueOf("S");

        System.out.println(CYAN_BOLD + "Lab1: Intro to formal languages. Regular grammars. Finite Automata." + RESET);
        Grammar grammar = new Grammar(Vn, Vt, productionLeft, productionRight, initialState);
        System.out.print(grammar.generateWords(5));

        FiniteAutomaton fa = grammar.toFiniteAutomaton("F");
        fa.wordIsValid("bb");
        fa.wordIsValid("abc");


        System.out.println("\n" + CYAN_BOLD + "Lab2: Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy." + RESET + "\n");
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

        Grammar gr = fa1.toGrammar();

        System.out.println("Production list of converted grammar: " + gr.getProductions());  //production list
        gr.grammarType();
        System.out.println("\nBefore");
        fa1.isNFA();
        fa1.convertToDFA();
        System.out.println("\nAfter");
        fa1.isNFA();

        System.out.println("\n\n" + CYAN_BOLD + "Lab4: Chomsky Normal Form." + RESET);
        String[] V_n = {"S","A", "B", "C", "D"};
        String[] V_t = {"a", "b"};
        String[] prodLeft = {"S", "S", "A", "A", "A", "B", "B", "C", "D", "D"};  //left side of production
        String[] prodRight = {"aB", "DA", "a", "BD", "aDADB", "b", "ASB", "BA", "", "BA"};  //right side of production
        String initialSymbol = "S";

        Grammar givenGrammar = new Grammar(V_n, V_t, prodLeft, prodRight, initialSymbol);
        Grammar cnf = CFGtoCNFConverter.getCopyModGrammar(givenGrammar); //works with the copy of provided grammar

        System.out.println(PURPLE + "Context Free Grammar: " + RESET);
        System.out.println(givenGrammar.displayProductions());

        System.out.println(BLUE + "\nChomsky Normal Form: " + RESET);
        System.out.println(cnf.displayProductions());
    }
}
