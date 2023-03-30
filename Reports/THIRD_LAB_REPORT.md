# Topic: Lexer | Scanner
### Course: Formal Languages & Finite Automata
### Author: Maia Zaica

----

## Theory

&ensp;&ensp;&ensp;**Lexical analysis** or **scanning** is the process where the stream of characters making up
the source program is read from left-toright and grouped into tokens [[1]]().

&ensp;&ensp;&ensp;**Tokens** are sequences of characters with a collective meaning.

&ensp;&ensp;&ensp;There are usually only a small number of tokens for a programming language: constants (integer, double, char, string,
etc.) operators (arithmetic, relational, logical), punctuation, and reserved words.

#### Notes on Lexical Analysis
* The **lexical analyzer** takes a source program as input, and produces a stream of tokens as output.
* Lexical analyzer might recognize particular
  instances of tokens, such specific instances are
  called lexemes.
* A lexeme is the actual character sequence
  forming a token, the token is the general class
  that a lexeme belongs to.

There are two primary **methods** for implementing a scanner.
* The first is a program that is hard coded to
  perform the scanning tasks. 
  1. Scanner implementation: loop & switch
* The second is the use of regular expressions
  and finite automata to model the scanning
  process.
  2. Scanner implementation: regular exp-ressions &
    finite automata.
#### Finite Automata and Lexical Analysis
* The tokens of a language are specified using regular expressions.
* A scanner is a big DFA, essentially the “aggregate” of the automata for the individual tokens.
* Issues: \
  &ensp;&ensp;&ensp;What does the scanner automaton look like? \
  &ensp;&ensp;&ensp;How much should we match? (When do we stop?) \
  &ensp;&ensp;&ensp;What do we do when a match is found? \
  &ensp;&ensp;&ensp;Buffer management (for efficiency reasons).

## Objectives
1. Understand what lexical analysis.
2. Get familiar with the inner workings of a lexer/scanner/tokenizer.
3. Implement a sample lexer and show how it works.


## Implementation

## References
<a id="1">[1]</a>. SAUMY, Debray. *Lexical Analysis (Scanning)*. The University of Arizona
Tucson. Available:
https://hama-univ.edu.sy/newsites/technology/wp-content/uploads/2018/10/%D8%A7%D9%84%D9%82%D8%B3%D9%85-%D8%A7%D9%84%D8%AB%D8%A7%D9%84%D8%AB.pdf

https://www2.cs.arizona.edu/classes/cs453/fall10/LectureMaterials/NOTES/PDF/LexicalAnalysis.pdf