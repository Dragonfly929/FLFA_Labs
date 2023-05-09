package automaton;

import grammar.*;
import java.util.*;
import java.util.regex.*;

public class FiniteAutomaton {
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    private HashSet<String> possibleStates;
    private HashSet<String> alphabet;
    private ArrayList<Transition> transitions;
    private final String initialState;
    private final String finalState;

    public FiniteAutomaton(Set<String> Vn,
                           Set<String> Vt,
                           String initialState,
                           String finalState){
        transitions = new ArrayList<>();
        possibleStates = new HashSet<>(Vn);
        possibleStates.add(finalState);
        alphabet = new HashSet<>(Vt);
        this.initialState = initialState;
        this.finalState = finalState;
    }
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }
    public HashSet<String> getPossibleStates() {
        return possibleStates;
    }
    public HashSet<String> getAlphabet() {
        return alphabet;
    }

    public void setTransitions(Transition transition) {
        transitions.add(transition);
    }
    public void setTransition(String currentState, String transitionLabel, String nextState) {
        transitions.add(new Transition(currentState, transitionLabel, nextState));
    }

    public void printTransitions(){
        System.out.println("\nTransitions set: ");
        for(Transition el: transitions){
            System.out.println(el.toString());
        }
    }

    public String getFinalState() {
        return finalState;
    }

    public boolean wordIsValid(String word){
        boolean valid = false;
        String currentState = initialState;

        myBreakLabel:
        for(int i = 0; i < word.length(); i++){
            int count = 0;
            for(Transition tr : transitions){
                count++;
                if(tr.currentState().equals(currentState) &&
                        tr.transitionLabel().equals(String.valueOf(word.charAt(i)))){
                    currentState = tr.nextState();
                    valid = true;
                    break;
                }
                else{
                    valid = false;
                    if(count == transitions.size()){
                        break myBreakLabel;
                    }
                }
            }
        }
        if(valid && currentState.equals(finalState)){
            System.out.println("\n<"+ word +"> is "+ ANSI_GREEN + "valid"+ANSI_RESET);
        } else{
            System.out.println("\n<"+ word +"> is "+ ANSI_RED + "not valid"+ANSI_RESET);
        }
        return valid;
    }

    public Grammar toGrammar(){

        ArrayList<String> prodKey = new ArrayList<>();
        ArrayList<String> prodVal = new ArrayList<>();
        for(Transition tr: transitions){
            prodKey.add(tr.currentState());
            prodVal.add(tr.transitionLabel() + tr.nextState());
        }

        String[] pk = prodKey.toArray(new String[0]);
        String[] pv = prodVal.toArray(new String[0]);
        String[] ps = possibleStates.toArray(new String[0]);
        String[] alph = alphabet.toArray(new String[0]);

        return new Grammar(ps, alph, pk, pv, initialState);
    }

//    public void isNFA(){
//        Grammar grammar = this.toGrammar();
//        HashMap<String, ArrayList<String>> production = grammar.getProductions();
//
//        boolean isNFA = false;
//        for(ArrayList<String> states : production.values()){
//            for(int i=0; i<alphabet.size(); i++){
//                HashSet<String> transitions = new HashSet<>();
//                for(String state : states){
//                    String[] transitionsForSymbol = state.split(",");
//                    if(transitionsForSymbol.length > 1){
//                        isNFA = true;
//                        break;
//                    }
//                    transitions.add(transitionsForSymbol[i]);
//                }
//                if(transitions.size() > 1){
//                    isNFA = true;
//                    break;
//                }
//            }
//            if(isNFA){
//                System.out.println("Type: Non-deterministic Finite Automaton");
//                return;
//            }
//        }
//        System.out.println("Type: Deterministic Finite Automaton");
//    }
    public void isNFA(){
        Grammar grammar = this.toGrammar();
        HashMap<String, ArrayList<String>> production = grammar.getProductions();

        for(ArrayList<String> states : production.values()){
            if(states.size() > alphabet.size()){
                System.out.println("Non-deterministic Finite Automata");
                return;
            }
        }
        System.out.println("Deterministic Finite Automata");
    }


    public void convertToDFA(){
        ArrayList<Transition> newTransitions = new ArrayList<>();
        Grammar grammar = this.toGrammar();
        Stack<String> stack = new Stack<>();
        ArrayList<String> analyzed = new ArrayList<>();
        stack.add(initialState);

        while (!stack.empty()){
            HashMap<String, ArrayList<String>> states = new HashMap<>();
            String term = stack.pop();
            analyzed.add(term);
            ArrayList<String> prodList = new ArrayList<>();

            if(possibleStates.contains(term)){
                prodList = grammar.getProductions().get(term);
            }
            else {
                HashSet<String> unique = new HashSet<>();
                String pattern = "q\\d+";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(term);

                while (m.find()) {
                    String match = m.group();
                    unique.addAll(grammar.getProductions().get(match));
                }
                prodList.addAll(unique);
                Collections.sort(prodList);
            }
            for(String element : prodList){
                String trLabel = element.substring(0, 1);
                String transition = element.substring(1);

                if(!states.containsKey(trLabel)){
                    states.put(trLabel, new ArrayList<>());
                }
                states.get(trLabel).add(transition);
            }

            for(String label : alphabet){
                String newNextState;
                List<String> labelStates = states.get(label);
                if(labelStates != null){
                    newNextState = String.join("", labelStates);
                }
                else{
                    newNextState = "empty";
                }
                newTransitions.add(new Transition(term, label , newNextState));

                if(!analyzed.contains(newNextState) && !newNextState.equals("empty")){
                    stack.add(newNextState);
                    analyzed.add(newNextState);
                }
            }
        }
        transitions = newTransitions;
    }
}