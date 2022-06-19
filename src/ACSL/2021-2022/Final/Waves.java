/**
 *      ACSL 2021-2022 - Final - Problem 1 - Waves
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Waves {

    public static void main (String [] args) throws IOException {

        String[][] input = new String[15][];
        input[0] = new String[]{"6", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"};
        input[1] = new String[]{"5", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"};
        input[2] = new String[]{"8", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"};
        input[3] = new String[]{"6", "3 1 4 1 5 9 2 6"};
        input[4] = new String[]{"9", "3 141 5926 535 89 72 3 846 26 43 383 27"};
        input[5] = new String[]{"3", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"};
        input[6] = new String[]{"5", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"};
        input[7] = new String[]{"7", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"};
        input[8] = new String[]{"9", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"};
        input[9] = new String[]{"4", "1 2 3 4"};
        input[10] = new String[]{"5", "1 2"};
        input[11] = new String[]{"5", "1"};
        input[12] = new String[]{"3", "1 2 3 4 5 6"};
        input[13] = new String[]{"1", "1 2 3 4 5"};
        input[14] = new String[]{"1", "1"};

        for (int i = 0; i < 15; i++)
        {
            System.out.println(createWave(Integer.parseInt(input[i][0]), input[i][1]));
        }
    }

    public static String createWave(int waveLength, String numbers) {

        String[] tokens = numbers.split(" ");
        int n = tokens.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
        {
            nums[i] = Integer.parseInt(tokens[i]);
        }

        int totalWaveLength = waveLength * (waveLength + 1) / 2;
        int numOfCopy = (int)Math.ceil(totalWaveLength*1.0/n);
        int[] waves = new int[n*numOfCopy];
        for (int j = 0; j < numOfCopy; j++)
        {
            for (int i = 0; i < n; i++)
            {
                waves[j*n+i] = nums[i];
            }
        }

        int start = 0;
        int dir = 1;
        for (int k = waveLength; k > 0; k--)
        {
            int end = start + k;
            Arrays.sort(waves, start, end);

            if (dir < 0) {
                reverseArray(waves, start, end-1);
            }

            dir *= -1;
            start = end;
        }

        if (start < waves.length) {

            Arrays.sort(waves, start, waves.length);
            if (dir < 0) {
                reverseArray(waves, start, waves.length-1);
            }
        }

        String result = "" + waves[0];
        for (int i = 1; i < waves.length; i++)
        {
            result += " " + waves[i];
        }

        return result;
    }

    public static void reverseArray(int[] arrays, int start, int end) {
        while (start < end)
        {
            int temp = arrays[start];
            arrays[start] = arrays[end];
            arrays[end] = temp;
            start++;
            end--;
        }
    }
}
