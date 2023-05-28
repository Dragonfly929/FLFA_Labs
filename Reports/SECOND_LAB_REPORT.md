# Topic: Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy.

### Course: Formal Languages & Finite Automata
### Author: Maia Zaica
### Variant 29

----
## Theory

### Determinism in Finite Automata.
**Non-Deterministic Finite Automata** (NDFA / NFA) is an automata in which
for some current state and input symbol, there exists more than one next output states [[1]](#1).

It is also known as Non-Deterministic Finite Accepter (NFA).

**Deterministic Finite Automata** (or DFA) are finite state machines that accept or reject strings of characters by parsing them through a sequence that is uniquely determined by each string.

The term “deterministic” refers to the fact that each string, and thus each state sequence, is unique.
In a DFA, a string of symbols is parsed through a DFA automata, and each input symbol will move to the next state that can be determined [[2]](#2).

### Conversion from NFA to DFA.

Non-Deterministic Finite Automata is defined by the quintuple -
(Q, ∑, δ, q<sub>0</sub>, F), where:
* **Q** is a finite set of states
* **∑** is a finite set of symbols, called the alphabet of the automaton.
* **δ** is the transition function.
* **q<sub>0</sub>** is the initial state from where any input is processed (q0 ∈ Q).
* **F** is a set of final state/states of Q (F ⊆ Q).

Converted to a DFA (Q', ∑, δ', q<sub>0</sub>, F'),
where:
* **Q'** is the new finite set of states
* **∑** is a finite set of symbols, called the alphabet of the automaton. Input symbols
* **δ'** is the transition function.
* **q<sub>0</sub>** is the initial state from where any input is processed (q0 ∈ Q').
* **F** is the new set of final state/states.

> **Note**: While converting an NFA with n states to a DFA, 2n possible set of states can be reachable but not necessarily reached in the DFA.


Steps for converting NFA to DFA [[3]](#3):

**Step 1**: Initially Q' = ∅

**Step 2**: Add q<sub>0</sub> of NFA to Q'. Then find the transitions from this start state.

**Step 3**: In Q', find the possible set of states for each input symbol. If this set of states is not in Q', then add it to Q'.

**Step 4**: In DFA, the final state will be all the states which contain F(final states of NFA).

### Chomsky Hierarchy [[4]](#4).

**Type 0**. *Recursively Enumerable Grammar*: no restrictions on
productions
> α → β

**Type 1**. *Context-Sensitive Grammars*: all production are in the
form
> α<sub>1</sub>Aα<sub>2</sub> → α<sub>1</sub>βα<sub>2</sub>

There are:
- left – context-sensitive grammar α<sub>1</sub>A → α<sub>1</sub>β
- right – context-sensitive grammar Aα<sub>2</sub> → βα<sub>2</sub>

**Type 2**. *Context-Free Grammar*: the all productions of grammar
G must be in form
> A → β, where Α ∈ V<sub>N</sub>, β ∈ (V<sub>N</sub> ∪ V<sub>T</sub>)*

**Type 3**. *Regular Grammar*.
Type 3 is most restricted grammar, it has two representations as:
* **Right linear grammar**:
> A → aB \
> A → a

where a ∈ V<sub>T</sub> \
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;A, B ∈ V<sub>N</sub>

* **Left linear grammar**:
> A → Ba \
> A → a

where a ∈ V<sub>T</sub> \
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;A, B ∈ V<sub>N</sub>

## Objectives:

1. Understand what an automaton is and what it can be used for.

2. Continuing the work in the same repository and the same project, the following need to be added:
   a. Provide a function in your grammar type/class that could classify the grammar based on Chomsky hierarchy.

   b. For this you can use the variant from the previous lab.

3. According to your variant number (by universal convention it is register ID), get the finite automaton definition and do the following tasks:

   a. Implement conversion of a finite automaton to a regular grammar.

   b. Determine whether your FA is deterministic or non-deterministic.

   c. Implement some functionality that would convert an NDFA to a DFA.

   d. Represent the finite automaton graphically (Optional, and can be considered as a __*bonus point*__):

  - You can use external libraries, tools or APIs to generate the figures/diagrams.

  - Your program needs to gather and send the data about the automaton and the lib/tool/API return the visual representation.


## Implementation

Variant 29:
```
Q = {q0,q1,q2},
∑ = {a,b},
F = {q2},
δ(q0,a) = q1,
δ(q0,a) = q0,
δ(q1,b) = q1,
δ(q1,a) = q2,
δ(q2,b) = q2,
δ(q2,a) = q0.
```
![img.png](images/img1.png)

**Figure. 1** *NFA*


Transition table

NFA = (Q, ∑, δ, q<sub>0</sub>, F)

|  δ  |   a  | b  |
|:---:|:----:|----|
| →q0 | q0q1 | ∅  |
| q1  | q2   | q1 |
| *q2 | q0   | q2 |

DFA = (Q', ∑, δ', q<sub>0</sub>, F')

|    δ'     |     a    | b      |
|:---------:|:--------:|--------|
|    →q0    | {q0q1}   | ∅      |
|  {q0q1}   | {q0q1q2} | q1     |
|    q1     | q2       | q1     |
| *{q0q1q2} | {q0q1q2} | {q1q2} |
|    *q2    | q0       | q2     |
|  *{q1q2}  | {q0q2}   | {q1q2} |
|  *{q0q2}  | {q0q1}   | q2     |

![img.png](images/img4.png)

**Figure. 1** *DFA*

## Implementation

**TO MAKE CHANGES**
```java
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
            // code that checks whether a given set of grammar productions represents a regular grammar
        }
    }
    if(isRegular){
        System.out.println(BLUE + "Type 3. " + RESET + "Regular Grammar");
    } else if(isContextFree){
        System.out.println(BLUE + "Type 2. " + RESET + "Context-Free Grammar");
    } else {
        System.out.println(BLUE + "Type 1. " + RESET + "Context-Sensitive Grammars");
    }
}
```

The given code is a method that determines the type of a given grammar based on Chomsky's hierarchy. Chomsky's hierarchy is a classification of grammars into four types based on the type of rules that they use. The four types of grammars are:

* **Type 0** - Unrestricted or Recursively Enumerable Grammar
* **Type 1** - Context-sensitive Grammar
* **Type 2** - Context-free Grammar
* **Type 3** - Regular Grammar

The given code determines the type of the grammar by analyzing the productions of the grammar. It uses the following criteria to determine the type of the grammar:

If any production has a non-terminal symbol of length greater than 1, then the grammar is not context-free and not regular. It is a recursively enumerable grammar.
If all productions have at most one non-terminal symbol on the right-hand side, then the grammar is regular. Otherwise, it is not regular.
If all productions have at most one non-terminal symbol on the left-hand side, then the grammar is context-free. Otherwise, it is context-sensitive.
Based on these criteria, the code sets the boolean variables isRegular and isContextFree to true or false, and then prints out the type of the grammar.

Note that the code assumes that the grammar is given as a map productions where the keys are the left-hand sides of the productions and the values are lists of the right-hand sides of the productions. 
```java
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
```
The method `isNFA()` that checks whether a given grammar represents a Non-deterministic Finite Automaton (NFA) or a Deterministic Finite Automaton (DFA). Here's a breakdown of how the code works:

1. The method assumes there is an instance variable named `alphabet` which represents the alphabet of the automaton.
2. The method first converts the given automaton into a grammar representation by calling `toGrammar()`, which returns a `Grammar` object.
3. It retrieves the productions of the grammar using the `getProductions()` method, which returns a `HashMap<String, ArrayList<String>>` representing the production rules for each non-terminal symbol.
4. The method iterates over the production rules using a for-each loop, iterating over the `ArrayList<String>` values (states) of the `production` map.
5. Inside the loop, it checks if the size of the states array is greater than the size of the alphabet. If it is, it means that there are more states than symbols in the alphabet, indicating non-determinism.
6. If the condition is true, the method prints "Non-deterministic Finite Automata" and returns immediately.
7. If the loop completes without encountering a non-deterministic condition, the method prints "Deterministic Finite Automata" because the automaton does not violate the condition of having more states than symbols in the alphabet.

It's important to note that the code snippet you provided is incomplete, as it references variables (`alphabet`) and methods (`toGrammar()`) that are not included. Additionally, there may be other parts of the codebase that are necessary to properly evaluate the automaton.

## Conclusions / Screenshots / Results
Before executing the laboratory work we had to get familiar with the concepts of Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy. 
Executing this laboratory work we had to further develop the project we began in Lab1. From hard coding we try to implement for general cases.
```
Lab2: Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy.
Production list of converted grammar: {q1=[bq1, aq2], q2=[bq2], q0=[aq1, aq0]}
Type: Type 3. Regular Grammar

Before
Type: Non-Deterministic Finite Automata

After
Type: Deterministic Finite Automata
```

## References
<a id="1">[1]</a>. AKSHAY, Singhal. *Non Deterministic Finite Automata | NFA*. Gate Vidyalay. Available:
https://www.gatevidyalay.com/non-deterministic-finite-automata-nfa/

<a id="2">[2]</a>. SISENSE. *What is Deterministic Finite Automata?*. [online]. Available: 
https://www.sisense.com/glossary/deterministic-finite-automata/

<a id="3">[3]</a>. GEEKSFORGEEKS. *Conversion from NFA to DFA*. 25 Feb. 2021. Available: 
https://www.geeksforgeeks.org/conversion-from-nfa-to-dfa/

<a id="4">[4]</a>. COJUHARI Irina, DUCA Ludmila, FIODOROV Ion. *Formal Languages and Finite Automata Guide for practical lessons*. Chișinău: Editura Tehnica-UTM, 2022. p. 5
