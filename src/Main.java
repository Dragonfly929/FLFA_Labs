import automaton.FiniteAutomaton;
import grammar.Grammar;

public class Main {
    public static void main(String[] args) {
        // {V_n, V_t, P, S} - grammar
        char[] Vn = {'S', 'A', 'B', 'C'}; // Non-terminal symbols
        char[] Vt = {'a', 'b'};  // Terminal symbols

        char[] productionLeft = {'S', 'A', 'A', 'B', 'C', 'A', 'B'};  // left side of production
        String[] productionRight = {"bA", "b", "aB", "bC", "cA", "bA", "aB"};  // right side of production
        char initialState = 'S';

        Grammar grammar = new Grammar(Vn, Vt, productionLeft, productionRight, initialState);
        System.out.println(grammar.generateWords(5));

        FiniteAutomaton fa = grammar.toFiniteAutomaton();
        fa.wordIsValid("bb");
        fa.wordIsValid("abc");
    }
}
