package Test;

import CNF.CFGtoCNFConverter;
import grammar.Grammar;
import org.junit.Test;

import java.util.*;

import static CNF.CFGtoCNFConverter.removeEpsilonProductions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testRemoveEpsilonProductions {

    @Test
    public void testRemoveEpsilonProductions() {
        // Create a grammar with epsilon productions
        String[] V_n = {"S","A", "B", "C", "D"};
        String[] V_t = {"a", "b"};
        String[] prodLeft = {"S", "S", "A", "A", "A", "B", "B", "C", "D", "D"};
        String[] prodRight = {"aB", "DA", "a", "BD", "aDADB", "b", "ASB", "BA", "", "BA"};
        String initialSymbol = "S";

        Grammar grammar = new Grammar(V_n, V_t, prodLeft, prodRight, initialSymbol);
        CFGtoCNFConverter.getCopyModGrammar(grammar);

        // create the expected output
        String[] Vn = {"S","A", "B", "C", "D"};
        String[] Vt = {"a", "b"};
        String[] leftHand = {"S", "S", "S", "A", "A", "A", "A", "A", "A", "A", "B", "B", "C", "D"};
        String[] rightHand = {"aB", "A", "DA", "a", "B", "BD", "aADB", "aAB", "aDAB", "aDADB", "b", "ASB", "BA", "BA"};
        String startSymbol = "S";
        Grammar expected = new Grammar(Vn, Vt, leftHand, rightHand, startSymbol);

        // compare the expected output with the actual output
        assertTrue(expected.getProductions().equals(getModifiedProductions(grammar)));
    }

    public static HashMap<String, ArrayList<String>> getModifiedProductions(Grammar grammar) {
        removeEpsilonProductions();
        return grammar.getProductions();
    }
}
