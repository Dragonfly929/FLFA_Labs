# Topic: Parser & Building an Abstract Syntax Tree
### Course: Formal Languages & Finite Automata
### Author: Maia Zaica

----

## Theory
Parser is a compiler that is used to break the data into smaller elements coming from lexical analysis phase [1].

A parser takes input in the form of sequence of tokens and produces output in the form of parse tree.

Parsing is of two types: top down parsing and bottom up parsing.

**Top down paring**

* The top-down parsing is known as recursive parsing or predictive parsing.
* Bottom up parsing is used to construct a parse tree for an input string.
* In the top-down parsing, the parsing starts from the start symbol and transform it into the input symbol.

**Bottom up parsing**

* Bottom up parsing is also known as shift-reduce parsing.
* Bottom up parsing is used to construct a parse tree for an input string.
* In the bottom up parsing, the parsing starts with the input symbol and construct the parse tree up to the start symbol by tracing out the rightmost derivations of string in reverse.
## Objectives
1. Get familiar with parsing, what it is and how it can be programmed [2].
2. Get familiar with the concept of AST [3].
3. In addition to what has been done in the 3rd lab work do the following:
    1. In case you didn't have a type that denotes the possible types of tokens you need to:
        1. Have a type __*TokenType*__ (like an enum) that can be used in the lexical analysis to categorize the tokens.
        2. Please use regular expressions to identify the type of the token.
    2. Implement the necessary data structures for an AST that could be used for the text you have processed in the 3rd lab work.
    3. Implement a simple parser program that could extract the syntactic information from the input text.

## References:
[1]. [Parser Javatpoint](https://www.javatpoint.com/parser)

[2]. [Parsing Wiki](https://en.wikipedia.org/wiki/Parsing)

[3]. [Abstract Syntax Tree Wiki](https://en.wikipedia.org/wiki/Abstract_syntax_tree)

