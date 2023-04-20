# Etude 2 - Syllable Slam

## Description

This program accepts words as input and computes the number of syllables in that word.

## Running the Program - Normal usage
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

To test the program, we passed in a file with words in which to compute the number of syllables, separated by new lines, 
and compared it to a second file with the expected number of syllables on each line. We printed the incorrect results out 
where our number of syllables did not match the expected number. Then a summary of the percentage it passed and total 
number of tests is printed out.

e.g.
```
javac SyllableSlamApp.java
java SyllableSlamApp.java testdata_small_in.txt testdata_small_out.txt 
air 2 1
bar 2 1
bid 2 1
blow 2 1
...
acetonemia 5 6
gastroenterologist 7 6
academicism 5 6
Tests Concluded
Problem count = 51
Total: 2193
Percentage: 0.9767441860465116
Total execution time: 1256
```
Note: the first number following each word is our calculated number of syllables, then the actual number of syllables.
e.g. blow 2 1

## Project Structure
This project consists of test files and files supporting the `syllableHashMap` hashmap, which both end in '.txt' as well as program files, ending in '.java'. The main application class is `SyllableSlamApp.java`, which uses the syllable hashmap 'syllabelHashMap' and the counting support class `CountSyllables.java` to compute syllables (which uses a rule-based method). 

There are two other support classes which were used for generating test data (PrintDataSetValues.java and GenerateTestDataLarge.java). 

We have two sets of test files, which are generated from testdata_small_in.txt and testdata_large.txt. 
The test files can be run with the program using 
```
java SyllableSlamApp.java filename_in.txt filename_out.txt
```

The support files for our `syllableHashMap` start with the keyword 'dataset'. The original file the hashmap was created from is 'dataset.txt', which is further cleaned (by replacing the bullet points to spaces) to be the file dataset_space_separated.txt. This file is then used to generate the keys of the hashmap, stored in 'dataset_keys.txt' and its respective values from 'dataset_values.txt'. 

## Program Structure
We used the main method in SyllableSlamApp.java to create our hashmap `syllableHashMap` and depending on the number of command line arguments passed it runs in either normal or testing mode. That is, if there are zero command line arguments, it runs normally but if two file names are passed, it runs in testing mode. 

The two modes are separated by the two static methods in the `SyllableSlamApp.java`. The main difference between them is that in `testingProgram()` method, we print out the incorrect words with their computed vs expected syllable counts. We also report on the final percentage of correctly computed words based on the two testing files.
In `scanWords()`, the input words are expected to be typed into stdin and each syllable count is output on a new line. 

In both methods, if the word is not in our static hashmap, we look to the rule-based method in `CountSyllables.java`, which contains the `countSyllables()` method for computing the syllable counts. 

## Hashmap

Our syllableHashMap was created based off `dataset.txt` (http://www.delphiforfun.org/programs/Syllables.htm), which contained words on each line with their syllables separated by a bullet point/unknown character. 

To get the values for the hashmap, we computed the syllable counts for these bullet-point-separated syllable words. These were stored in `dataset_values.txt`.We also needed the original words from the dataset for the keys in our hashmap, which are stored in `dataset_keys.txt`. 

To get dataset.txt's respective syllable counts, we first found and replaced the bullet points with spaces and saved this file as `dataset_space_separated.txt`. We then used `PrintDataSetValues.java` to read in dataset_space_separated.txt to get the number of tokens on each line, which were the syllable counts for the words in dataset.txt. The syllable counts were output to the terminal, which we copied to dataset_values.txt by using the command: `java PrintDataSetValues.java > dataset_values.txt`

To get the clean words in dataset.txt (without bullet points) we found and replaced the bullet points in dataset.txt with an empty string. These clean words were then stored in dataset_keys.txt. 

We then implemented the hashmap by making each line in dataset_keys.txt a key and its corresponding hashmap value came from the same line in dataset_values.txt. This produced a dictionary of almost 170,000 words that we know the syllable counts of.

## CountSyllables
`CountSyllables` worked on the base idea that a switch from vowel to a consonant or a consonant to a vowel would be one syllable. With this we could increase our `clumpCount` each time it changed from vowel to consonant and vice versa and in the end divide 'clumpCount' by two to get the actual number of syllables. We also added multiple extra checking methods for edge cases. For example, when it ends in a consonant followed
by 'y', we want to increase the syllable count, and increase the count if it ends a y or is y followed by a 
consonant but not if there is a vowel clump after it. 
We also wanted to increase the count for patterns such as le, re, ey, which made us check for special cases when the
last letter was 'e' or 'y', and increase the syllable count depending on what the second last or third last letters were.
E.g. Consonant+y, Consonant+le, Consonant+re.
We also implemented more exceptions/edge cases such as for words ending in -ion, -ism, -ely, -gue, -que.


# Development Process + Benchmarking
## Pure Rule-based Method
We first tried to implement the syllable counts for words using a rule based method. This counted the number of
syllable 'clumps' by counting each time we switched from syllable to consonant and vice versa, then dividing the
result by two. Then we realised we needed to account for edge cases (as mentioned previously). 

We created our own test data set from words off syllablecount.com and created a testdata_small_in.txt which contains 
all the words.
In another file (testdata_small_out.txt) we added all the expected syllable counts. These syllable counts were compared 
to our calculated syllable count, which produced about 89% accuracy. After modifying our rule-based implementation to 
include for 
extra common patterns that increased/decreased the syllable counts, we were able to reach 94% accuracy by using the 
this test data, which was about 2190 words (testdata_small_in.txt).

## Pure Rule-based Results
Unfortunately, using a pure-ruled based method only reached about 88% for easy words and 62% for difficult words in AutoJudge.
We then tested the rule-based implementation on a larger dataset (dataset.txt - http://www.delphiforfun.org/programs/Syllables.htm) which has about 187000 words. This only achieved about 79%. 
As adding more rules easily conflicted with the ones we added before, we started thinking about implementing a 
hashmap based on this dataset, that is to map each word with its expected syllable count. Although this would
be more time consuming to load in the data to the hashmap, it would be much more accurate, provided our dataset
covered a good range of words.

## Method with Hashmap
If the word was not in the hashmap, we would use our old rule-based implementation as a backup, which is not as 
accurate but still acceptable. This used our `CountSyllables.java` code, again this code was a rule based syllable counter which would acheive on average 80% accuracy. And so would still have a good chance of getting the correct number of syllables for the edge case words. 

## Results with Hashmap + Other attempts
This combination produced about 97.6% accuracy on our testdata_small_in.txt test file, although testing it on the 
data which generated the hashmap only produced 99% accuracy. (This may have been due to some discrepencies in the
provided dataset)

This passed autojudge, with 97% accuracy on easy words and 80% on difficult words and is our best attempt yet. 
We also tested it on a much larger dataset of 25000 words (testdata_large.txt), producing 99% accuracy, and it took less than a second
to compute.

As there were sometimes multiple words on a line in the data set used for generating our hashmap, we tried 
splitting each line first into separate words before passing it into our hashmap. Unfortunately, this was 
easy to generate more errors, producing 98% accuracy instead of 99% for our original dataset and slightly less than
98% for testdata_small_in.txt. This was likely due to different formats in the words e.g. hyphens used between
or not between words and sometimes combining syllable counts for words introduced more errors if not done 
properly. 

We found the number of 'unique' words not too much smaller than total number of words,
(187175 compared to 177084) so discarding small repeated words/phrases would not have produced much of a 
difference. The dataset we used was also 
sufficient in the range of words as it contained small sub words e.g. 'well' and 'very', as well as large words 
e.g. 'well-summarized' and 'avery', 'very high frequency' that are combined by hyphens and/or spaces. As these were 
also actually words from the dictionary, which would mean they wouldn't change as much, it was alright to use a 
hashmap with slightly more repetition than necessary.

Another idea we had was to use the already split syllable words e.g. dataset_space_separated.txt and map that into unique 'keys' for
the hashmap, so that for each key in the hashmap, a syllable count is added on. This had generated a lot less keys e.g.
~20000 unique keys and ran a lot more quickly for generating the hashmap, but was difficult to implement in terms of
splitting the word up into those syllables/'unique keys', as they may often overlap/be multiple possible combinations. 

Therefore, provided the input words were mostly from the dictionary and our dataset was of a sufficient size and
range for the input words, our current implementation of counting the number of syllables in word is probably
the most effective way so far. 

## Helper classes
We used `PrintDataSetValues.java` as a helper class for generating counts of syllables in words from dataset_space_separated.txt.
We also used `GenerateTestDataLarge.java` as a helper class for generating counts of syllables in words from testdata_large.txt (which had syllables separated by ';').

## Authors and acknowledgment
Rhys, Daniel, Ben and Ruth
