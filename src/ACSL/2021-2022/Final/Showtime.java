/**
 *      ACSL 2021-2022 - Final - Problem 2 - Showtime!
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Showtime {

    public static void main (String [] args) throws IOException {

        String[] input = new String[15];
        input[0] = "11:05:10";
        input[1] = "11:55:45";
        input[2] = "00:05:20";
        input[3] = "00:20:00";
        input[4] = "03:15:40";
        input[5] = "04:15:00";
        input[6] = "08:00:25";
        input[7] = "05:10:40";
        input[8] = "09:45:50";
        input[9] = "10:20:15";
        input[10] = "11:55:55";
        input[11] = "00:00:00";
        input[12] = "07:35:35";
        input[13] = "01:45:05";
        input[14] = "06:30:30";

        for (int i = 0; i < 15; i++)
        {
            System.out.println(displayTime(input[i]));
        }
    }

    public static String displayTime(String time) {

        int[] timeToCheck = new int[3];
        timeToCheck[0] = Integer.parseInt(time.substring(0, 2));
        timeToCheck[1] = Integer.parseInt(time.substring(3, 5));
        timeToCheck[2] = Integer.parseInt(time.substring(6));

        ArrayList<String> result = new ArrayList<>();
        generateAllTimeString("", result, timeToCheck);

        String output = result.get(0);
        for (int i = 1; i < result.size(); i++)
        {
            output += " " + result.get(i);
        }

        return output;
    }

    public static void generateAllTimeString(String current, ArrayList<String> result, int[] timeToCheck) {

        if (current.length() == 5) {
            if (isStringGoodTime(current, timeToCheck)) {
                result.add(current);
            }
        }
        else {
            for (int i = 0; i < timeString.length(); i++)
            {
                generateAllTimeString(current+timeString.charAt(i), result, timeToCheck);
            }
        }
    }

    public static String timeString = "bcgkmrwy";
    public static int[] timeFactor = {1, 1, 2, 3 ,5};

    public static boolean isStringGoodTime(String str, int[] timeToCheck) {

        int[] timeFromString = new int[3];
        for (int i = 0; i < 5; i++)
        {
            addTime(str.charAt(i), timeFromString, timeFactor[i]);
        }

        for (int i = 0; i < 3; i++)
        {
            if (timeFromString[i] != timeToCheck[i]) {
                return false;
            }
        }

        return true;
    }

    public static void addTime(char color, int[] timeArray, int timeMultiply) {

        if (color == 'r') {
            timeArray[0] += timeMultiply;
        }
        else if (color == 'g') {
            timeArray[1] += timeMultiply*5;
        }
        else if (color == 'b') {
            timeArray[2] += timeMultiply*5;
        }
        else if (color == 'y') {
            timeArray[0] += timeMultiply;
            timeArray[1] += timeMultiply*5;
        }
        else if (color == 'c') {
            timeArray[1] += timeMultiply*5;
            timeArray[2] += timeMultiply*5;
        }
        else if (color == 'm') {
            timeArray[0] += timeMultiply;
            timeArray[2] += timeMultiply*5;
        }
        else if (color == 'w') {
            timeArray[0] += timeMultiply;
            timeArray[1] += timeMultiply*5;
            timeArray[2] += timeMultiply*5;
        }
    }
}
