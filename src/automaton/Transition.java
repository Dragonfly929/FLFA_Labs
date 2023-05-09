package automaton;

public record Transition(String currentState, String transitionLabel, String nextState) {
    @Override
    public String toString() {
        String sb = "Current State: " + currentState + "\n" +
                "Transition Label: " + transitionLabel + "\n" +
                "Next State: " + nextState + "\n";
        return sb;
    }
}