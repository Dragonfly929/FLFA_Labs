# Topic: Parser & Building an Abstract Syntax Tree
### Course: Formal Languages & Finite Automata
### Author: Maia Zaica

----

## Theory
Parser is a compiler that is used to break the data into smaller elements coming from lexical analysis phase [1].

The parser is that phase of the compiler which takes a token string as input and with the help of existing grammar, converts it into the corresponding Intermediate Representation(IR). The parser is also known as Syntax Analyzer.

![img.png](images/img7.png)
Parsing is of two types: *Top-down Parsing* and *Bottom-up Parsing*.

### Top down parsing

* The top-down parsing is known as recursive parsing or predictive parsing.
* Bottom up parsing is used to construct a parse tree for an input string.
* In the top-down parsing, the parsing starts from the start symbol and transform it into the input symbol.

1. **Recursive descent parser** is also known as the Brute force parser or the backtracking parser. It basically generates the parse tree by using brute force and backtracking.
2. **Non-recursive descent parser** is also known as LL(1) parser or predictive parser or without backtracking parser or dynamic parser. It uses a parsing table to generate the parse tree instead of backtracking.

### Bottom up parsing

* Bottom up parsing is also known as shift-reduce parsing.
* Bottom up parsing is used to construct a parse tree for an input string.
* In the bottom up parsing, the parsing starts with the input symbol and construct the parse tree up to the start symbol by tracing out the rightmost derivations of string in reverse.

**LR parser** is the bottom-up parser that generates the parse tree for the given string by using unambiguous grammar. It follows the reverse of the rightmost derivation.

**Operator precedence parser** generates the parse tree from given grammar and string but the only condition is two consecutive non-terminals and epsilon never appears on the right-hand side of any production.

The operator precedence parsing techniques can be applied to Operator grammars.

**Operator grammar**: A grammar is said to be operator grammar if there does not exist any production rule on the right-hand side.
> 1. as ε(Epsilon)
> 2. Two non-terminals appear consecutively, that is, without any terminal between them operator precedence parsing is not a simple               technique to apply to most the language constructs, but it evolves into an easy technique to implement where a suitable grammar             may be produced.


## Objectives
1. Get familiar with parsing, what it is and how it can be programmed [2].
2. Get familiar with the concept of AST [3].
3. In addition to what has been done in the 3rd lab work do the following:
    1. In case you didn't have a type that denotes the possible types of tokens you need to:
        1. Have a type __*TokenType*__ (like an enum) that can be used in the lexical analysis to categorize the tokens.
        2. Please use regular expressions to identify the type of the token.
    2. Implement the necessary data structures for an AST that could be used for the text you have processed in the 3rd lab work.
    3. Implement a simple parser program that could extract the syntactic information from the input text.

## Implementation

## Colnclusion

## References:
[1]. [Parser Javatpoint](https://www.javatpoint.com/parser)

[2]. [Parsing Wiki](https://en.wikipedia.org/wiki/Parsing)

[3]. [Abstract Syntax Tree Wiki](https://en.wikipedia.org/wiki/Abstract_syntax_tree)

