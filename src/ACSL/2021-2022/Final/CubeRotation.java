/**
 *      ACSL 2021-2022 - Final - Problem 4 - Cube Rotation
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class CubeRotation {

    static String[][][] cube;
    static String[] color = {"R", "G", "Y", "B", "O", "P"};
    static String[][][] colRotation, rowRotation;

    public static void main (String [] args) throws IOException {

        String[][] input = new String[15][];
        input[0] = new String[]{"P2", "CC1 RC4 CR3"};
        input[1] = new String[]{"G5", "RC5"};
        input[2] = new String[]{"O7", "CR7"};
        input[3] = new String[]{"Y2", "RR1 CC1"};
        input[4] = new String[]{"G1", "CC1 RR5 CR7 RC3"};
        input[5] = new String[]{"O8", "CC2"};
        input[6] = new String[]{"P3", "CR4"};
        input[7] = new String[]{"G0", "RR3"};
        input[8] = new String[]{"B6", "RC1"};
        input[9] = new String[]{"R5", "RC1 CR2"};
        input[10] = new String[]{"R8", "CR4 RC3"};
        input[11] = new String[]{"Y5", "RC1 CR1 RR1 CC1"};
        input[12] = new String[]{"B6", "CR3 CC4 RC1 RR2"};
        input[13] = new String[]{"P4", "RR0 CC0 RC7 CR9"};
        input[14] = new String[]{"Y2", "CR1 RC2 CR3 RC4 CC5 RC6 CR7 RC8 CR9"};

        for (int i = 0; i < 15; i++)
        {
            System.out.println(rotateCube(input[i][0], input[i][1]));
        }
    }

    public static String rotateCube (String start, String moves) {

        initializeCube();
        initializeRotation();

        String[] tokens = moves.split(" ");
        for (String token : tokens)
        {
            int[] pos = find(start);
            int num = token.charAt(2) - '0';
            rotate (pos, token.charAt(0), token.charAt(1), num);
            // printCube();
        }

        int[] position = find(start);
        return getSideAsString(position[0]);
    }

    static void initializeCube () {

        cube = new String[6][3][3];
        for (int i = 0; i < 6; i++)
        {
            int number = 0;
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    cube[i][j][k] = color[i] + number;
                    number++;
                }
            }
        }
    }

    static void initializeRotation () {

        colRotation = new String[6][3][];
        colRotation[0][0] = new String[]{"000", "010", "020", "500", "510", "520", "222", "212", "202", "400", "410", "420"};
        colRotation[0][1] = new String[]{"001", "011", "021", "501", "511", "521", "221", "211", "201", "401", "411", "421"};
        colRotation[0][2] = new String[]{"002", "012", "022", "502", "512", "522", "220", "210", "200", "402", "412", "422"};
        colRotation[1][0] = new String[]{"100", "110", "120", "502", "501", "500", "322", "312", "302", "420", "421", "422"};
        colRotation[1][1] = new String[]{"101", "111", "121", "512", "511", "510", "321", "311", "301", "410", "411", "412"};
        colRotation[1][2] = new String[]{"102", "112", "122", "522", "521", "520", "320", "310", "300", "400", "401", "402"};
        colRotation[2][0] = new String[]{"200", "210", "220", "522", "512", "502", "022", "012", "002", "422", "412", "402"};
        colRotation[2][1] = new String[]{"201", "211", "221", "521", "511", "501", "021", "011", "001", "421", "411", "401"};
        colRotation[2][2] = new String[]{"202", "212", "222", "520", "510", "500", "020", "010", "000", "420", "410", "400"};
        colRotation[3][0] = new String[]{"300", "310", "320", "520", "521", "522", "122", "112", "102", "402", "401", "400"};
        colRotation[3][1] = new String[]{"301", "311", "321", "510", "511", "512", "121", "111", "101", "412", "411", "410"};
        colRotation[3][2] = new String[]{"302", "312", "322", "500", "501", "502", "120", "110", "100", "422", "421", "420"};
        colRotation[4][0] = new String[]{"400", "410", "420", "000", "010", "020", "500", "510", "520", "222", "212", "202"};
        colRotation[4][1] = new String[]{"401", "411", "421", "001", "011", "021", "501", "511", "521", "221", "211", "201"};
        colRotation[4][2] = new String[]{"402", "412", "422", "002", "012", "022", "502", "512", "522", "220", "210", "200"};
        colRotation[5][0] = new String[]{"500", "510", "520", "222", "212", "202", "400", "410", "420", "000", "010", "020"};
        colRotation[5][1] = new String[]{"501", "511", "521", "221", "211", "201", "401", "411", "421", "001", "011", "021"};
        colRotation[5][2] = new String[]{"502", "512", "522", "220", "210", "200", "402", "412", "422", "002", "012", "022"};

        rowRotation = new String[6][3][];
        rowRotation[0][0] = new String[]{"000", "001", "002", "100", "101", "102", "200", "201", "202", "300", "301", "302"};
        rowRotation[0][1] = new String[]{"010", "011", "012", "110", "111", "112", "210", "211", "212", "310", "311", "312"};
        rowRotation[0][2] = new String[]{"020", "021", "022", "120", "121", "122", "220", "221", "222", "320", "321", "322"};
        rowRotation[1][0] = new String[]{"100", "101", "102", "200", "201", "202", "300", "301", "302", "000", "001", "002"};
        rowRotation[1][1] = new String[]{"110", "111", "112", "210", "211", "212", "310", "311", "312", "010", "011", "012"};
        rowRotation[1][2] = new String[]{"120", "121", "122", "220", "221", "222", "320", "321", "322", "020", "021", "022"};
        rowRotation[2][0] = new String[]{"200", "201", "202", "300", "301", "302", "000", "001", "002", "100", "101", "102"};
        rowRotation[2][1] = new String[]{"210", "211", "212", "310", "311", "312", "010", "011", "012", "110", "111", "112"};
        rowRotation[2][2] = new String[]{"220", "221", "222", "320", "321", "322", "020", "021", "022", "120", "121", "122"};
        rowRotation[3][0] = new String[]{"300", "301", "302", "000", "001", "002", "100", "101", "102", "200", "201", "202"};
        rowRotation[3][1] = new String[]{"310", "311", "312", "010", "011", "012", "110", "111", "112", "210", "211", "212"};
        rowRotation[3][2] = new String[]{"320", "321", "322", "020", "021", "022", "120", "121", "122", "220", "221", "222"};
        rowRotation[4][0] = new String[]{"400", "401", "402", "102", "112", "122", "522", "521", "520", "320", "310", "300"};
        rowRotation[4][1] = new String[]{"410", "411", "412", "101", "111", "121", "512", "511", "510", "321", "311", "301"};
        rowRotation[4][2] = new String[]{"420", "421", "422", "100", "110", "120", "502", "501", "500", "322", "312", "302"};
        rowRotation[5][0] = new String[]{"500", "501", "502", "120", "110", "100", "422", "421", "420", "302", "312", "322"};
        rowRotation[5][1] = new String[]{"510", "511", "512", "121", "111", "101", "412", "411", "410", "301", "311", "321"};
        rowRotation[5][2] = new String[]{"520", "521", "522", "122", "112", "102", "402", "401", "400", "300", "310", "320"};
    }

    static int[] find (String entry) {

        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (entry.equals(cube[i][j][k])) {
                        return new int[]{i, j, k};
                    }
                }
            }
        }

        return new int[]{-1,-1,-1};
    }

    static String getSideAsString (int i) {

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 3; j++)
        {
            for (int k = 0; k < 3; k++)
            {
                sb.append(cube[i][j][k]);
            }
        }

        return sb.toString();
    }

    static void rotate (int[] position, char rc, char dir, int num) {

        int side = position[0];
        int row = position[1];
        int col = position[2];

        String[] indexArray;
        if (rc == 'R') {
            indexArray = rowRotation[side][row];
        }
        else {
            indexArray = colRotation[side][col];
        }

        String[] oldValue = new String[12];
        for (int i = 0; i < 12; i++)
        {
            int s = indexArray[i].charAt(0) - '0';
            int r = indexArray[i].charAt(1) - '0';
            int c = indexArray[i].charAt(2) - '0';

            oldValue[i] = cube[s][r][c];
        }

        String[] newValue = new String[12];
        for (int i = 0; i < 12; i++)
        {
            int newIndex = i;
            if (dir == 'R') {
                newIndex = (newIndex + num) % 12;
            }
            else {
                newIndex = (newIndex + 12 - num) % 12;
            }

            newValue[newIndex] = oldValue[i];
        }

        for (int i = 0; i < 12; i++)
        {
            int s = indexArray[i].charAt(0) - '0';
            int r = indexArray[i].charAt(1) - '0';
            int c = indexArray[i].charAt(2) - '0';

            cube[s][r][c] = newValue[i];
        }
    }

    static void printCube () {
        for (int i = 0; i < 6; i++)
        {
            System.out.println("Face " + i);
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}
