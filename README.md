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
java SyllableSlamApp.java syllableTests.txt syllableTests_out.txt

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


## Development Process + Benchmarking
We first tried to implement the syllable counts for words using a rule based method. This counted the number of
syllable 'clumps' by counting each time we switched from syllable to consonant and vice versa, then dividing the
result by two. Then we realised we needed to account for edge cases, such as when it ends in a consonant followed
by 'y', we want to increase the syllable count by two, but only increase it by one if it ends in 'ey'. We also
wanted to increase the count for patterns such as le, re, ey.

We created our own test data set from words off syllablecount.com and created a test file with all the words.
In another file we added all the expected syllable counts. These syllable counts were compared to our calculated 
syllable count, which produced about 89% accuracy. After modifying our rule-based implementation to include for 
extra common patterns that increased/decreased the syllable counts, we were able to reach 94% accuracy by using the 
this test data, which was about 2190 words (syllableTests.txt).

Unfortunately, this only reached about 88% for easy words and 62% for difficult words in autojudge.
We then tested the rule-based implementation on a larger dataset (mhyph.txt - http://www.delphiforfun.org/programs/Syllables.htm) which has about 187000 words. This only achieved about 79%. 
As adding more rules easily conflicted with the ones we added before, we started thinking about implementing a 
hashmap based on this dataset, that is to map each word with its expected syllable count. Although this would
be more time consuming to load in the data to the hashmap, it would be much more accurate, provided our dataset
covered a good range of words.

If the word was not in the hashmap, we would use our old rule-based implementation as a backup, which is not as 
accurate but still acceptable. 

This produced about 97.6% accuracy on our syllableTests.txt test file, although testing it on the 
data which generated the hashmap only produced 99% accuracy. (This may have been due to some discrepencies in the
provided dataset)

This passed autojudge, with 97% accuracy on easy words and 80% on difficult words and is our best attempt yet. 
We also tested it on a much larger dataset of 25000 words, producing 99% accuracy, and it took less than a second
to compute.

As there were sometimes multiple words on a line in the data set used for generating our hashmap, we tried 
splitting each line first into separate words before passing it into our hashmap. Unfortunately, this was 
easy to generate more errors, producing 98% accuracy instead of 99% for our original dataset and slightly less than
98% for syllableTests.txt. This was likely due to different formats in the words e.g. hyphens used between
or not between words and sometimes combining syllable counts for words introduced more errors if not done 
properly. 

We found the number of 'unique' words not too much smaller than total number of words,
(187175 compared to 177084) so discarding small repeated words/phrases would not have produced much of a 
difference. The dataset we used was also 
sufficient in the range of words as it contained small sub words e.g. 'well' and 'very', as well as large words 
e.g. 'well-summarized' and 'avery', 'very high frequency' that are combined by hyphens and/or spaces. As these were 
also actually words from the dictionary, which would mean they wouldn't change as much, it was alright to use a 
hashmap with slightly more repetition than necessary.

Another idea we had was to use the already split syllable words e.g. mhyph_spaces.txt and map that into unique 'keys' for
the hashmap, so that for each key in the hashmap, a syllable count is added on. This had generated a lot less keys e.g.
~20000 unique keys and ran a lot more quickly for generating the hashmap, but was difficult to implement in terms of
splitting the word up into those syllables/'unique keys', as they may often overlap/be multiple possible combinations. 

Therefore, provided the input words were mostly from the dictionary and our dataset was of a sufficient size and
range for the input words, our current implementation of counting the number of syllables in word is probably
the most effective way so far. 

## Helper classes
We used FileManip class as a helper class for generating counts of syllables in words from mhyph_spaces.txt


## Authors and acknowledgment
Rhys, Daniel, Ben and Ruth