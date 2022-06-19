/**
 *      ACSL 2021-2022 - Final - Problem 3 - WordleMatch
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class WordleMatch {

    public static void main (String [] args) throws IOException {

        String[][] input = new String[15][];
        input[0] = new String[]{"ports", "climb spots sport parts stops traps sorts porch props shots prank"};
        input[1] = new String[]{"bread", "reads breed dread reeds braid plead creed darts seeds leads arbor heads drape capes"};
        input[2] = new String[]{"helps", "roads track fears sight jumps zones"};
        input[3] = new String[]{"cubic", "color graph quips whine cable cubes strip brink"};
        input[4] = new String[]{"trees", "start eater stack truck tears zones stamp strip sport latex parts kinds lives wings turns hopes meant yearn taste"};
        input[5] = new String[]{"storm", "elope below tears downy elbow fiend heart igloo mails pluck parts marts"};
        input[6] = new String[]{"bytes", "birds yearn steam cable burst heats tykes tubes bites sight"};
        input[7] = new String[]{"debug", "greed feuds drone longs bugle judge debug bagel"};
        input[8] = new String[]{"heaps", "spear heart parts apart sheep pears harts peers brash ships shape happy"};
        input[9] = new String[]{"depth", "peach heaps truth drips deeds"};
        input[10] = new String[]{"equal", "queen cruel extra hoops squat proof label"};
        input[11] = new String[]{"local", "scope lucky apart clown cloud claps clots cools loops sully lucks clock pluck scoop civic troll"};
        input[12] = new String[]{"cycle", "cysts junky earth laces click yells cells truly learn chime cubic clean equal stack clock likes"};
        input[13] = new String[]{"civic", "igloo color ocean vivid crack views crave wicks havoc"};
        input[14] = new String[]{"queue", "equal reeds usurp proud queer quite juice pique quota"};

        for (int i = 0; i < 15; i++)
        {
            System.out.println(findMatch(input[i][0], input[i][1]));
        }
    }

    public static String findMatch(String word, String guesses) {

        String[] tokens = guesses.split(" ");
        int n = tokens.length;
        Result[] results = new Result[n];
        int numValid = 0;
        int[] charCount = new int[26];
        for (int i = 0; i < n; i++)
        {
            results[i] = new Result(word, tokens[i]);
            if (results[i].isValid()) {
                numValid++;
            }

            for (int j = 0; j < 5; j++)
            {
                charCount[tokens[i].charAt(j) - 'a']++;
            }
        }

        String output = "";
        if (numValid >= 6) {
            Arrays.sort(results);
            output = results[0].guess;
            for (int i = 1; i < 6; i++)
            {
                output += " " + results[i].guess;
            }
        }
        else {
            for (int i = 0; i < 26; i++)
            {
                if (charCount[i] == 0) {
                    output += (char)('a' + i);
                }
            }
        }

        return output;
    }

    static class Result implements Comparable<Result> {

        private String guess;
        private int numGreen;
        private int numYellow;
        private boolean isFirstGreen;
        private boolean isLastGreen;
        private int numGreenVowel;

        public Result(String target, String guess) {

            this.guess = guess;
            this.numGreen = 0;
            this.numGreenVowel = 0;

            int[] charCount1 = new int[26];
            int[] charCount2 = new int[26];

            for (int i = 0; i < 5; i++)
            {
                char ch1 = target.charAt(i);
                char ch2 = guess.charAt(i);
                if (ch1 == ch2) {
                    this.numGreen++;

                    if (ch1 == 'a' || ch1 == 'e' || ch1 == 'i' || ch1 == 'o' || ch1 == 'u') {
                        this.numGreenVowel++;
                    }
                }
                else {
                    charCount1[ch1 - 'a']++;
                    charCount2[ch2 - 'a']++;
                }
            }

            this.numYellow = 0;
            for (int i = 0; i < 26; i++)
            {
                this.numYellow += Math.min(charCount1[i], charCount2[i]);
            }

            this.isFirstGreen = (target.charAt(0) == guess.charAt(0));
            this.isLastGreen = (target.charAt(4) == guess.charAt(4));
        }

        public boolean isValid() {
            return this.numGreen > 0 || this.numYellow > 0;
        }

        public int compareTo(Result other) {

            if (this.numGreen != other.numGreen) {
                return other.numGreen - this.numGreen;
            }
            else if (this.numYellow != other.numYellow) {
                return other.numYellow - this.numYellow;
            }
            else if (this.isFirstGreen != other.isFirstGreen) {
                return this.isFirstGreen ? -1 : 1;
            }
            else if (this.isLastGreen != other.isLastGreen) {
                return this.isLastGreen ? -1 : 1;
            }
            else if (this.numGreenVowel != other.numGreenVowel) {
                return other.numGreenVowel - this.numGreenVowel;
            }
            else {
                return this.guess.compareTo(other.guess);
            }
        }
    }
}
