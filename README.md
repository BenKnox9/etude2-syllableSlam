# Etude 2 - Syllable Slam

## Description

This program accepts words as input and computes the number of syllables in that word.

## Running Program - Normal usage
After entering a word, it will produce the number of syllables after it.
```
javac SyllableSlamApp.java
java SyllableSlamApp.java
hello
2
words
1
```
## Testing Method: 

To test the program, we passed in a file with words in which to compute the number of syllables, separated by new lines, and compared it to a second file with the expected number of syllables on each line. We then printed the incorrect results out where our number of syllables did not match the expected number. Then a summary of the percentage it passed and total number of tests is printed out.

e.g.
```
javac SyllableSlamApp.java
java SyllableSlamApp.java syllableTests.txt out.txt

bar 2 1
bomb 2 1
coup 2 1
...
acetonemia 5 6
gastroenterologist 7 6
academicism 5 6
Tests Concluded
Problem count = 42
Total: 2193
Percentage: 0.9808481532147743
```
Note: the first number is our calculated number of syllables, following it is the expected number.


## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.


## Authors and acknowledgment
Rhys, Daniel, Ben and Ruth

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
