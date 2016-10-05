# Words with Friends Problem

## Problem Statement

Given the included dictionary file `words.txt` write a program to take a shuffled set of letters and return:

 - what words from the dictionary the user can spell with these letters?
 - what words begin with these letters as a prefix?
 - what words end with these letters as a suffix?

You may use any programming language with which you're comfortable.  

Include along with your sourcecode any documentation necessary to compile / run your program.
 
Extra credit for performance optimizations and/or unit tests.

## Example Usage

Assume you're program is called "scrabbler".

To find all the words you can spell with 7 letters "abcdefg" you should be able to type:

    $ scrabbler abcdefg
    
And get back a list of words as output.  Something like:

    ache
    ...
    chafed
    
To find all words that begin with a specific prefix:

    $ scrabbler --prefix fi
    fix
    fixed
    ...
    fiz
    fizzy
    
To find all words with a specific suffix:

    $ scrabbler --suffix o
    aero
    ...
    zydeco
    

# Solution

- It is written in java. 
- The java version in my mac is "1.8.0_60".
- To run the code, put the code file and word.txt in the same directory. 
- In the terminal, go to the directory. Then input:
```
javac WordSearch.java
java WordSearch
```
- Follow the instructions which showed in the terminal. I copy them in the following.
- To find the words that can spell with given letters, just directly input the letters, then press the ENTER key.
- To find the words starting with the prefix letters, input:
```
--prefix <letters>
```
Here <letters> stand for the prefix letters.
- To find the words ending with the suffix letters, input:
```
--suffix <letters>
```
- To close the program, just do Ctrl + C.

### Implementation
- For word search. I made several assumptions.
- First, letters can be used more than once. For example, with input "aple", we can use it to spell "apple".
- Second, input letters are not necessarily used up to spell a word. For example, with input "cakeb", we can use it to spell "cake".
- I just read the file of words and construct an ArrayList composed of those words. For each input of letters, I check each word from the ArrayList that whether it can be spelled by the input of letters.
- For the prefix and suffix search, I firstly implemented a Trie (prefix tree). Two Tries are used, one for prefix search, one for suffix search. Though Trie is supposed to do prefix search, by reversing the words, it is easily to make it work for suffix search.

### Test
- I have done different cases manually and found they really work.
