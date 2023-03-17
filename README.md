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


## Development Process
We first tried to implement the syllable counts for words using a rule based method. This counted the number of
syllable 'clumps' by counting each time we switched from syllable to consonant and vice versa, then dividing the
result by two. Then we realised we needed to account for edge cases, such as when the last letter is y or e and 
patterns such as le, re, ey and putting a consonant or vowel before them e.g. Consonant+le or Vowel+y.

We created our own test data set from words off syllablecount.com and created a test file with all the words and another
file with all the expected syllable counts. These syllable counts were compared to our calculated syllable count,
which produced about 89% accuracy. After modifying our rule-based implementation to include for extra common patterns
that increased/decreased the syllable counts, we were able to reach 94% accuracy by using the test data of about 2190 
words (syllableTests.txt).

Unfortunately, this only reached about 88% for easy words and 62% for difficult words in autojudge.
We then tested the rule-based implementation on a larger dataset (mhyph.txt - http://www.delphiforfun.org/programs/Syllables.htm) which has about 187000 words, which only
achieved about 79%. This led us to think about implementing a hashmap based on this dataset, to map each word with
its expected syllable count as there were many odd cases to account for, with many conflicting with one another.
If the word was not in the hashmap, we would use our old rule-based implementation, which was not as accurate but 
acceptable. This produced about 97.6% accuracy on our syllableTests.txt test file, although testing it on the 
data which generated the hashmap only produced 99% accuracy. 

This seemed to pass autojudge, with 97% accuracy on easy words and 80% on difficult words. We are now trying it on another 
dataset to further benchmark our results.

Another idea we had was to use the already split syllable words e.g. mhyph_spaces.txt and map that into unique 'keys' for
the hashmap, so that for each key in the hashmap, a syllable count is added on. This had generated a lot less keys e.g.
~20000 unique keys and ran a lot more quickly for generating the hashmap, but was difficult to implement in terms of
splitting the word up into those syllables/'unique keys', as they may often overlap/be multiple possible combinations.

TODO: 
- may need to do a check for apostrophes/what happens for spaces/bad characters?
- should we try splitting it on each word (in mhyph.txt), pass into hashmap, and if there are multiple words in 
a line, call the count method for each word, then add up the counts of the words?
- side note on FileManip - helper class for generating counts of syllables in words from mhyph_spaces.txt
- also timing of program

## Authors and acknowledgment
Rhys, Daniel, Ben and Ruth

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
