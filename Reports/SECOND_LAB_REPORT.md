# Topic: Determinism in Finite Automata. Conversion from NFA to DFA. Chomsky Hierarchy.

### Course: Formal Languages & Finite Automata
### Author: Maia Zaica
### Variant 29

----
## Theory

#### Determinism in Finite Automata.
**Non-Deterministic Finite Automata** (NDFA / NFA) is an automata in which
for some current state and input symbol, there exists more than one next output states [[1]](#1).

It is also known as Non-Deterministic Finite Accepter (NFA).

**Deterministic Finite Automata** (or DFA) are finite state machines that accept or reject strings of characters by parsing them through a sequence that is uniquely determined by each string.

The term “deterministic” refers to the fact that each string, and thus each state sequence, is unique. 
In a DFA, a string of symbols is parsed through a DFA automata, and each input symbol will move to the next state that can be determined [[2]](#2).

#### Conversion from NFA to DFA.
Non-Deterministic Finite Automata is defined by the quintuple -
(Q, ∑, δ, q<sub>0</sub>, F), where:
* **Q** is a finite set of states
* **∑** is a finite set of symbols, called the alphabet of the automaton.
* **δ** is the transition function.
* **q<sub>0</sub>** is the initial state from where any input is processed (q0 ∈ Q).
* **F** is a set of final state/states of Q (F ⊆ Q).

Steps for converting NFA to DFA:

**Step 1**: Initially Q' = ϕ

**Step 2**: Add q<sub>0</sub> of NFA to Q'. Then find the transitions from this start state.

**Step 3**: In Q', find the possible set of states for each input symbol. If this set of states is not in Q', then add it to Q'.

**Step 4**: In DFA, the final state will be all the states which contain F(final states of NFA)

```math
% Please add the following required packages to your document preamble:
% \usepackage[table,xcdraw]{xcolor}
% If you use beamer only pass "xcolor=table" option, i.e. \documentclass[xcolor=table]{beamer}
\begin{table}[]
\begin{tabular}{|l|l|l|}
\hline
\multicolumn{1}{|c|}{\cellcolor[HTML]{FFFFFF}{\color[HTML]{FFFFFF} }} & \multicolumn{1}{c|}{\cellcolor[HTML]{FFFFFF}{\color[HTML]{000000} a}} & b  \\ \hline
→q0                                                                   & q0q1                                                                  & ∅  \\ \hline
q1                                                                    & q2                                                                    & q1 \\ \hline
*q2                                                                   & q0                                                                    & q2 \\ \hline
\end{tabular}
\end{table}
```
#### Chomsky Hierarchy.

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
>
where a ∈ V<sub>T</sub> \
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;A, B ∈ V<sub>N</sub>

## Objectives:

* First objective.
* ...
![img.png](images/img1.png)
**Figure. 1**

## Implementation description

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
* About 2-3 sentences to explain each piece of the implementation.


* Code snippets from your files.

```
public static void main() 
{

}
```

* If needed, screenshots.


## Conclusions / Screenshots / Results

Here is a simple footnote[^1].

A footnote can also have multiple lines[^2].

You can also use words, to fit your writing style more closely[^note].

[^1]: My reference.
[^2]: Every new line should be prefixed with 2 spaces.  
This allows you to have a footnote with multiple lines.
[^note]:
Named footnotes will still render with numbers instead of the text but allow easier identification and linking.  
This footnote also has been made with a different syntax using 4 spaces for new lines.
## References
<a id="1">[1]</a>. https://www.gatevidyalay.com/non-deterministic-finite-automata-nfa/
<a id="2">[2]</a>. SISENSE. *What is Deterministic Finite Automata?*. [online]  https://www.sisense.com/glossary/deterministic-finite-automata/