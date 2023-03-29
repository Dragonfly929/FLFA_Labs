# Topic: Lexer | Scanner
### Course: Formal Languages & Finite Automata
### Author: Maia Zaica

----

## Theory

**Lexical analysis** or **scanning** is the process where the stream of characters making up
the source program is read from left-toright and grouped into tokens.

**Tokens** are sequences of characters with a collective meaning.

There are usually only a small number of tokens for a programming language: constants (integer, double, char, string,
etc.) operators (arithmetic, relational, logical), punctuation, and reserved words.

#### Finite Automata and Lexical Analysis
* The tokens of a language are specified using regular expressions. \
* A scanner is a big DFA, essentially the “aggregate” of the automata for the individual tokens. \
* Issues: \
  &ensp;&ensp;&ensp;What does the scanner automaton look like? \
  &ensp;&ensp;&ensp;How much should we match? (When do we stop?) \
  &ensp;&ensp;&ensp;What do we do when a match is found? \
  &ensp;&ensp;&ensp;Buffer management (for efficiency reasons).

## Objectives
1. Understand what lexical analysis [1] is.
2. Get familiar with the inner workings of a lexer/scanner/tokenizer.
3. Implement a sample lexer and show how it works.

## References
[1]. SAUMY, Debray. *Lexical Analysis (Scanning)*. The University of Arizona
Tucson

https://hama-univ.edu.sy/newsites/technology/wp-content/uploads/2018/10/%D8%A7%D9%84%D9%82%D8%B3%D9%85-%D8%A7%D9%84%D8%AB%D8%A7%D9%84%D8%AB.pdf

https://www2.cs.arizona.edu/classes/cs453/fall10/LectureMaterials/NOTES/PDF/LexicalAnalysis.pdf