package automaton;

abstract public class FiniteAutomaton {
    private char[] possibleStates;
    private char[] alphabet;
    private Transition[] transitions;
    private char initialState;
    private char[] finalStates;
    public abstract boolean wordIsValid();
}
