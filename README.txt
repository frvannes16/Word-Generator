Franklin van Nes

Design Details
In the WordGen class, I intended the helper methods to only be used to prepare data for the table class, and they do so successfully, extracting the
text from the input file when given its filename. The command line arguments are processed with a switch statement because it reads easier in shorter
code. The class also contains a quit method, which takes an Exception carrying a message detailling the reason for quitting. This made the code easier to follow, and the
exit feedback easier to present.
In the Table class,  I used a HashMap instead of an ArrayList. I actually first created the program using an ArrayList with Associations first (I still have this code if you
would like to see it). I tried to make it as efficient as possible. That iteration of the program worked well with short inputs, but when processing large files
it took too long (4 - 5 minutes sometimes). Due to previous experience with HashMaps, I knew that HashMaps 'indexed' through the use of hashcodes created on the input key, thus it doesn't
require looping through an ArrayList to locate an entry - a very costly process when working with large arrays. Furthermore, HashMaps are a key-value pair data type, so the use of
an Association object isn't necessary, this offers memory improvements. I was able to implement HashMaps without any outside sources.
The methods in the table class each serve an individual purpose in an efficient manner.
In the FrequencyList class, I again used a HashMap for the above reasons also. I am really proud of my getNextChar() method since it uses an interesting way to pick a character based
on probability.
In printing the table, I found a class DecimalFormat (Line 92) which would nicely format my probability. I found this in searching the java API library for double formatting.



TESTS
Each test will provide the command line arguments (input string may take place of the filename) and the generated output. The table is not provided
since it has already been provided to the forum.
Each test is ran twice.

default.txt contains:
"Once upon a time there was a donkey named don. Don the donkey was a total pro when it came to playing FIFA 16. Though his favorite team was Chelsea.
Chelsea sucks, unfortunately, but don the donkey ruled, just like arsenal."

--------------------------------
Command Line tests

Test 1:
Command Line: run WordGen
Output 1: it came team was a don. Don a don a don there was a to playing FIFA 16. Though his favorite team was a time the donkey ruled, just like arsenal.
Output 2: e don the don a to playing FIFA 16. Though his favorite team was a donkey ruled, just like arsenal.

Analysis: This program uses the defaults. it starts at a random string correctly. The second output ends short correctly since there are no other instances where 'al.' of 'arsenal.' is used in the
input string.


Test 2:
Command Line: run WordGen hamlet.txt
Output 1: . And Enquent wronius. With twersations To thy lords is vernard Ham. He skyish it: what's could we o' though exit don's jot mine? Thes and and here eter a griend: Janus'led, lease, e'er that not, fould b
Output 2:  LIMITED TO SEND*  **Well notterson Untinbrancy; For How thoughts. Behine; Yet paight the madness could you are I. And Play.  Belish argume drings.  Queen obey sir; And With rapping. Farelius, recove and

Analysis: it runs correctly given just one argument. It sources the text from the file but otherwise uses the default seed length and finds a random beginning.


Test 3:
Command Line: run WordGen hamlet.txt question
Output 1: question to our state, Have we, as 'twere, the mirror (mirror sites are available on a roar? Not one now, to divide him into far more complete Works   Copyright not in the Castle.  [Enter Hamlet by Shakespear
Output 2: question:-- Whether aught: leave him a further Than the observ'd of all his faults so quaintly That they are. To a nunnery, go; and quickly too. Farewell.  [Exit Polonius.] I took thee for her?  King. Now, Ha

Analysis: It ends within the default max limit, and the words seem to make sense!

Test 4:
Command Line: run WordGen hamlet.txt question 1000
Output 1:
question:-- Whether aught, to us unknown, afflicts him thus From fashion.  Pol. My lord, from Hamlet in madness would perhaps trouble a woman.
Hor. It was, as I have heard That guilty creature seeing; Thou mixture rank, of midnight we'll feast together demonstrated Unto our climature and course
to illume that play your copy of the unworthy a thing to you no further person to arraign In ear and see the purpose I'll anoint my sword.  Ghost. Mark me.
Ham. I pray you pardon and my queen. Mad as the stated month.  Since our ftp program has a bug in it that I have a speech straight. Go a little soil'd i'
the world assurance in thee!  Laer. I pray God.--God b' wi' ye! Now I am punish'd with baser matter: yes, by heaven or blasts from hell, Be thy intents
wicked wit and gifts, that have you hear them.--Stand, ho! Who is there are the action. The Poisoner with thee airs from her father; But this morning bedtime,
And dupp'd the charge,-- That, open'd, lies where is this!  Ham. Indeed? I heard thee

Output 2:
question; but, of our hope, Your visitation of our eye, Our crown, our life, and all are companies To draw him once; he was convert My stern
effects for what he sings at grave-making?  Hor. Not a jot more, my lord.  Ham. As woman's love.  [Exeunt.]    Act II.  Scene VII. Another room
in the skull.] Alas, poor ghost!  Ghost. Mark me.  Ham. He was a man, If his chief good and dismay.  [Exeunt.]    Act II.  Scene I. Elsinore. A
platform, 'twixt eleven and ears. Yet I, A dull and matter that which dearest father dear to you?  Oph. So please check file size will have grey
 beards; that thus hath put him So much as from occasion you are keen, my lord, his majesties To his conceit.  Ham. I mean, sir, for his death
 compos'd As made them well, they imitated humanity so abominably.  I Play.    Run barefoot up and stately by them, in his soul so to his arm;
 And with him. Now, mother, you fall to play with sleep. [Sleeps.]  P. Queen. I doubt it nothing sure, yet much unsinew'd, But never the which we are m

Analysis: The command line arguments all work correctly.

-----------------------------------

Word Generation Tests

default values of seed length = 3 and max output = 200 used.
input string: tatatatax
Output 1: atax
Output 2: tatatatatatatatatatatatatax

input string: abcdefghijlkmnopqrstuvwxyz
Output 1: nopqrstuvwxyz
Output 2: vwxyz

//with an a at the end.
input string: abcdefghijklmnopqrstuvwxyzabc
Output 1: opqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghi
Output 2: wxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopq
Analysis: Due to the extra 'abc' at the end this loops correctly through the alphabet until it reaches max output.
It seems like everything is working correctly and the letters are being picked randomly yet according to the probability.

