# Topic: Intro to formal languages. Regular grammars. Finite Automata.

### Course: Formal Languages & Finite Automata
### Author: Maia Zaica
### Variant 29

----

## Theory
An **alphabet** is specified by giving a finite set, Σ, whose elements are
called **symbols**. For us, any set qualifies as a possible alphabet, so long
as it is finite.

A **string of length** *n* (≥ 0) over an alphabet Σ is just an ordered *n*-tuple
of elements of Σ, written without punctuation.

The **concatenation** of two strings *u, v* ∈ Σ
<sup>*</sup> is the string *uv* obtained by joining the strings end-to-end.

Finite Automata(FA) is the simplest machine to recognize patterns. The finite automata or finite state machine is an abstract machine that has five elements or tuples. It has a set of states and rules for moving from one state to another but it depends upon the applied input symbol.

The mathematical model of finite automata consists of:

* Finite set of states (Q)
* Finite set of input symbols (Σ)
* One Start state (q0)
* Set of final states (qf)
* Transition function (δ)

```
P = {          
    S → bA     
    A → b    
    A → aB   
    B → bC    
    C → cA
    A → bA
    B → aB
}
```
or
```
P = {          
    S → bA     
    A → aB | bA | b  
    B → aB | bC    
    C → cA
}
```

```
RE = b(b+aa*bc)*b
```
![img.png](images/img.png)
## Objectives:

1. Understand what a language is and what it needs to have in order to be considered a formal one.

2. Provide the initial setup for the evolving project that you will work on during this semester. I said project because usually at lab works, I encourage/impose students to treat all the labs like stages of development of a whole project. Basically you need to do the following:

   a. Create a local && remote repository of a VCS hosting service (let us all use Github to avoid unnecessary headaches);

   b. Choose a programming language, and my suggestion would be to choose one that supports all the main paradigms;

   c. Create a separate folder where you will be keeping the report. This semester I wish I won't see reports alongside source code files, fingers crossed;

3. According to your variant number (by universal convention it is register ID), get the grammar definition and do the following tasks:

   a. Implement a type/class for your grammar;

   b. Add one function that would generate 5 valid strings from the language expressed by your given grammar;

   c. Implement some functionality that would convert and object of type Grammar to one of type Finite Automaton;

   d. For the Finite Automaton, please add a method that checks if an input string can be obtained via the state transition from it;

1. You can use 2 classes in order to represent the 2 main object which are the grammar and finite automaton. Additional data model, helper classes etc. can be added but should be used (i.e. you shouldn't have source code file that are not used).

```
public class Grammar
{
    private final HashSet<Character> nonTerminalSymbols = new HashSet<>();
    private final HashSet<Character> terminalSymbols = new HashSet<>();
    private final HashMap<Character, ArrayList<String>> productions = new HashMap<>();
    private final char startSymbol;

    public Grammar(char[] Vn, char[] Vt, char[] productionLeft,
                   String[] productionRight, char startSymbol){
        genNonTerminalSymbols(Vn);
        genTerminalSymbols(Vt);
        genProductions(prodLeft, prodRight);
        this.startSymbol = startSymbol;
    }
}

public class FiniteAutomaton
{
    // Some state variables as needed.
    // {Q, Sigma, delta, q0, F}

    
    private HashSet<Character> possibleStates;
    private HashSet<Character> alphabet;
    private ArrayList<Transition> transitions;
    private char initialState;
    private char finalState;

    public FiniteAutomaton(HashSet<Character> vn,
                           HashSet<Character> vt,
                           char initialState,
                           char finalState
                           ){

        transitions = new ArrayList<>();
        possibleStates = new HashSet<>(vn);
        possibleStates.add(finalState);
        alphabet = new HashSet<>(vt);
        this.initialState = initialState;
        this.finalState = finalState;
    }
}
```

2. In order to show the execution you can implement a client class/type, which is just a "Main" class/type in which you can instantiate the types/classes. Another approach would be to write unit tests if you are familiar with them.

## Implementation description

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```

* About 2-3 sentences to explain each piece of the implementation.


* Code snippets from your files.

```
public static void main() 
{

}
```

* If needed, screenshots.


## Conclusions / Screenshots / Results


## References
https://graphviz.org/Gallery/directed/fsm.html

Finite Automaton

Variable State : Q
Storage : ""




















