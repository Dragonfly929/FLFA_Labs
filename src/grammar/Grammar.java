package grammar;
import automaton.FiniteAutomaton;
import automaton.Transition;
import java.util.*;

public class Grammar {
    private final HashSet<Character> nonTerminalSymbols = new HashSet<>();
    private final HashSet<Character> terminalSymbols = new HashSet<>();
    private final HashMap<Character, ArrayList<String>> productions = new HashMap<>();
    private final char startSymbol;

    public Grammar(char[] Vn, char[] Vt, char[] prodLeft,
                   String[] prodRight, char initialState){
        genNonTerminalSymbols(Vn);
        genTerminalSymbols(Vt);
        genProductions(prodLeft, prodRight);
        this.startSymbol = initialState;
    }
    public void genNonTerminalSymbols(char[] Vn){
        for (char c : Vn) {
            nonTerminalSymbols.add(c);
        }
    }
    public void genTerminalSymbols(char[] Vt){
        for (char c : Vt) {
            terminalSymbols.add(c);
        }
    }
    public void genProductions(char[] prodLeft, String[] prodRight) {
        for(int i = 0; i < prodLeft.length; i++){

            if(!productions.containsKey(prodLeft[i])){
                productions.put(prodLeft[i], new ArrayList<>());
            }
            productions.get(prodLeft[i]).add(prodRight[i]);
        }
    }

    public ArrayList<String> generateWords(int wordsAmount){
        ArrayList<String> result = new ArrayList<>();
        Random random = new Random();

        System.out.println("\nStrings formation:");
        while(result.size()  < wordsAmount){
            Stack<Character> stack = new Stack<>();
            StringBuilder stringBuilder = new StringBuilder();

            stack.add(startSymbol);
            System.out.print("\n" + startSymbol + " ---> ");

            while(!stack.isEmpty()){
                char term = stack.pop();

                if(nonTerminalSymbols.contains(term)){
                    ArrayList<String> tempArrayRes = productions.get(term);
                    String tempRes = tempArrayRes.get(random.nextInt(tempArrayRes.size()));
                    System.out.print(stringBuilder + tempRes + " ---> ");

                    for(int i = tempRes.length() - 1; i >= 0; i--){
                        stack.add(tempRes.charAt(i));
                    }
                } else {
                    stringBuilder.append(term);
                }
            }
            result.add(stringBuilder.toString());
            System.out.print("<" + stringBuilder + ">");
        }
        System.out.print("\n\n" + "Final set of strings: ");
        return result;
    }

    public FiniteAutomaton toFiniteAutomaton(){
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(nonTerminalSymbols,
                                              terminalSymbols,
                                              startSymbol, 'F');
        for(char key: productions.keySet()){
            for(String element: productions.get(key)){
                if(element.length() < 2){
                    finiteAutomaton.setTransitions(new Transition(key,element.charAt(0),
                                                   finiteAutomaton.getFinalState()));
                } else {
                    finiteAutomaton.setTransitions(new Transition(key,element.charAt(0),
                                                   element.charAt(1)));
                }
            }
        }
        return finiteAutomaton;
    }
}
