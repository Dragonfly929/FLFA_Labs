package grammar;

import automaton.FiniteAutomaton;
import automaton.Transition;

import java.util.*;

public class Grammar {

    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    private final Set<String> nonTerminalSymbols;
    private final Set<String> terminalSymbols;
    private final HashMap<String, ArrayList<String>> productions;
    private final String initialSymbol;

    public Grammar(String[] Vn, String[] Vt, String[] productionLeft, String[] productionRight, String initialSymbol){
        nonTerminalSymbols = new HashSet<>(Arrays.asList(Vn));
        terminalSymbols = new HashSet<>(Arrays.asList(Vt));
        productions = new HashMap<String, ArrayList<String>>();
        genProductions(productionLeft, productionRight);
        this.initialSymbol = initialSymbol;
    }

    public Grammar(Grammar grammar) {
        this.nonTerminalSymbols = new HashSet<>(grammar.nonTerminalSymbols);
        this.terminalSymbols = new HashSet<>(grammar.terminalSymbols);
        this.productions = new HashMap<>(grammar.productions);
        this.initialSymbol = grammar.initialSymbol;
    }

    public HashMap<String, ArrayList<String>> getProductions() {
        return productions;
    }

    public String displayProductions() {
        StringBuilder sb = new StringBuilder();
        for (String key : productions.keySet()) {
            sb.append(key).append(" ⟶ ");
            ArrayList<String> values = productions.get(key);
            for (int i = 0; i < values.size(); i++) {
                sb.append(values.get(i));
                if (i < values.size() - 1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void genProductions(String[] productionLeft, String[] productionRight) {
        for(int i = 0; i < productionLeft.length; i++){
            if(!productions.containsKey(productionLeft[i])){
                productions.put(productionLeft[i], new ArrayList<>());
            }
            productions.get(productionLeft[i]).add(productionRight[i]);
        }
    }

    public Set<String> getNonTerminalSymbols() {
        return nonTerminalSymbols;
    }

    public Set<String> getTerminalSymbols() {
        return terminalSymbols;
    }

    public String getInitialSymbol() {
        return initialSymbol;
    }

    public ArrayList<String> generateWords(int wordsAmount){
        ArrayList<String> result = new ArrayList<>();
        Random random = new Random();

        System.out.println("\nProcess of strings formation:");
        while(result.size()  < wordsAmount){
            Stack<String> stack = new Stack<>();
            StringBuilder stringBuilder = new StringBuilder();
            stack.add(initialSymbol);
            System.out.print("\n" + initialSymbol + " ⟶ ");

            while(!stack.isEmpty()){
                String term = stack.pop();
                if(nonTerminalSymbols.contains(term)){
                    ArrayList<String> tempArrayRes = productions.get(term);
                    String tempRes = tempArrayRes.get(random.nextInt(tempArrayRes.size()));
                    System.out.print(stringBuilder + tempRes + " ⟶ ");
                    for(int i = tempRes.length() - 1; i >= 0; i--){
                        stack.add(String.valueOf(tempRes.charAt(i)));
                    }
                } else stringBuilder.append(term);
            }
            result.add(stringBuilder.toString());
            System.out.print("<" + stringBuilder + ">");
        }
        System.out.print("\n" + "Generated Words: ");
        return result;
    }

    public FiniteAutomaton toFiniteAutomaton(String finalState){
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(nonTerminalSymbols,
                terminalSymbols,
                initialSymbol, finalState);
        for(String key: productions.keySet()){
            for(String element: productions.get(key)){
                if(element.length() < 2 ){
                    finiteAutomaton.setTransitions(new Transition(key, element,
                            finiteAutomaton.getFinalState()));
                }
                else{
                    finiteAutomaton.setTransitions(new Transition(key,String.valueOf(element.charAt(0)),
                            element.substring(1)));
                }
            }
        }
        return finiteAutomaton;
    }

    public void grammarType(){
        System.out.println("Grammar Type based on Chomsky Hierarchy");
        boolean isRegular = true;
        boolean isContextFree = true;
        for(String key : productions.keySet()){
            if (key.length() > 1 && !nonTerminalSymbols.contains(key)) {
                isContextFree = false;
                isRegular = false;
                break;
            }
        }

        for(ArrayList<String> list : productions.values()){
            for(String element : list){

                if(element.length() == 0){
                    System.out.println(BLUE + "Type 0" + RESET + ". Recursively Enumerable Grammar: No restrictions on productions.");
                    return;
                }
                boolean isTwoSymbol = nonTerminalSymbols.contains(element.substring(1));
                if(element.length() > 2 && !isTwoSymbol){
                    isRegular = false;
                }
                else if(element.length() == 2 || isTwoSymbol){
                    String first = String.valueOf(element.charAt(0));
                    String second = element.substring(1);
                    if(!terminalSymbols.contains(first) || !nonTerminalSymbols.contains(second)){
                        isRegular = false;
                    }
                }

                else {
                    String symbol = String.valueOf(element.charAt(0));
                    if(!terminalSymbols.contains(symbol)){
                        isRegular = false;
                    }
                }
            }
        }
        if(isRegular){
            System.out.println(BLUE + "Type 3. " + RESET + "Regular Grammar");
        }
        else if(isContextFree){
            System.out.println(BLUE + "Type 2. " + RESET + "Context-Free Grammar");
        }
        else {
            System.out.println(BLUE + "Type 1. " + RESET + "Context-Sensitive Grammars");
        }
    }


    public void printGrammar() {
        System.out.println("Start Symbol: " + initialSymbol);
        System.out.println("Productions:");
        for (String key : productions.keySet()) {
            ArrayList<String> prodList = productions.get(key);
            for (String prod : prodList) {
                System.out.println(key + " ⟶ " + prod);
            }
        }
    }
}