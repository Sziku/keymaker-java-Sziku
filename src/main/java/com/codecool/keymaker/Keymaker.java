package com.codecool.keymaker;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Keymaker {

    public static void main(String[] args) {
        /*Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine().toLowerCase();
        System.out.println("Your key: " + hashIt(name));*/

        //System.out.println(shiftCharacters("abby",5));
        //System.out.println(padUpTo("morpheus",15,19));
        //System.out.println(abcMirror(padUpTo("morpheus",15,19)));
        //System.out.println(Arrays.toString(createMatrix(padUpTo("morpheus",15,19), abcMirror(padUpTo("morpheus",15,19)))));
        //System.out.println(zigZagConcatenate(createMatrix(padUpTo("morpheus",15,19), abcMirror(padUpTo("morpheus",15,19)))));
        //System.out.println(rotateRight("abcdefgh", 3));
        //System.out.println(getSquareIndexChars("abcdefghijklm"));
        //System.out.println(removeOddBlocks("abcdefghijklm", 3));
        //System.out.println(reduceToFixedWord("abcdefghijklm", 6));

        System.out.println(hashIt("morpheus"));
        System.out.println(hashIt("trinity"));
        System.out.println(hashIt("neo"));
    }

    private static String hashIt(String word) {
        /*
         * hashIt("morpheus") -> "trowdo"
         */
        String padded = padUpTo(word, 15, 19);
        String elogenated = zigZagConcatenate(createMatrix(padded, abcMirror(padded)));
        String rotated = rotateRight(elogenated, 3000003);
        String cherryPicked = getSquareIndexChars(rotated);
        String halved = removeOddBlocks(cherryPicked, 3);
        String key = reduceToFixedWord(halved, 6);
        return key;
    }

    private static String shiftCharacters(String word, int shift) {
        /*
         * shiftCharacters("abby", 5) -> "fggd"
         */
        int shiftLetter;
        StringBuilder result = new StringBuilder();
        for(char c : word.toLowerCase().toCharArray()){
            shiftLetter=c+shift;
            while (shiftLetter>122){
                shiftLetter-=26;
            }
            result.append((char)shiftLetter);
        }
        return result.toString();
    }

    private static String padUpTo(String word, int shift, int n) {
        /*
         * padUpTo("abb", 5, 11) -> "abbfggkllpq"
         */
        int shiftLetter;
        StringBuilder result = new StringBuilder();
        result.append(word);
        int loopCount=n/word.length()+1;
        for (int i=1; i<loopCount+1; i++) {
            for (char c : word.toLowerCase().toCharArray()) {
                shiftLetter = c + shift*i;
                while (shiftLetter>122){
                    shiftLetter-=26;
                }
                result.append((char) shiftLetter);
            }
        }

        return result.substring(0,n);
    }

    private static String abcMirror(String word) {
        /*
         * abcMirror("abcd") -> "zyxw"
         */
        StringBuilder result = new StringBuilder();
        for(char c : word.toLowerCase().toCharArray()){
            result.append((char)(219-c));
        }

        return result.toString();
    }

    private static String[] createMatrix(String word1, String word2) {
        /*
         * createMatrix("mamas", "papas") -> ["bpbph", "mamas", "bpbph", "mamas", "esesk"]
         */
        String[] result = new String[word2.length()];
        int index=0;
        int shiftLetter;
        for(char c : word2.toLowerCase().toCharArray()) {
            StringBuilder tempString = new StringBuilder();
            for (char d : word1.toLowerCase().toCharArray()) {
                shiftLetter=(c-97)+d;
                while (shiftLetter>122){
                    shiftLetter-=26;
                }
                tempString.append((char) shiftLetter);
            }
            result[index]=tempString.toString();
            index++;
        }
        return result;
    }

    private static String zigZagConcatenate(String[] matrix) {
        /*
         * zigZagConcatenate(new String[] {"abc", "def", "ghi", "jkl"}) -> "abgjkhebcfil"
         */
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix[0].length(); i++) {
            for (int j = 0; j < matrix.length; j++) {
                result.append(matrix[j].charAt(i));
            }
        }
        return  result.toString();
    }

    private static String rotateRight(String word, int n) {
        /*
         * rotateRight("abcdefgh", 3) -> "fghabcde"
         */
        int index = 1;
        int wordLength = word.length();
        char[] wordChar = word.toCharArray();
        for (int i = 0; i < n; i++) {
            char[] oneRight = new char[wordLength];
            for(char c : wordChar){
                if(index==wordLength){
                    index=0;
                }
                oneRight[index] = c;
                index++;
            }
            wordChar=oneRight;
        }
       return String.valueOf(wordChar);
    }

    private static String getSquareIndexChars(String word) {
        /*
         * getSquareIndexChars("abcdefghijklm") -> "abej"
         */
        int i=0;
        StringBuilder result = new StringBuilder();
        while (i*i<word.length()){
            result.append(word.charAt(i*i));
            i++;
        }
        return result.toString();
    }

    private static String removeOddBlocks(String word, int blockLength) {
        /*
         * removeOddBlocks("abcdefghijklm", 3) -> "abcghim"
         */
        StringBuilder result = new StringBuilder();
        int i = 0;
        while ( i < word.length()) {
            result.append(word.charAt(i));
            i++;
            if(i%blockLength==0){
                i+=blockLength;
            }

        }

        return result.toString();
    }

    private static String reduceToFixedWord(String word, int n) {
        /*
         * reduceToFixed("abcdefghijklm", 6) -> "bafedc"
         */
        String temp = word.substring(0,n);
        int leftRotate = n/3;
        int wordLength = temp.length();
        int index = wordLength-1;
        char[] wordChar = temp.toCharArray();
        for (int i = 0; i < leftRotate; i++) {
            char[] oneLeft = new char[wordLength];
            for(char c : wordChar){

                oneLeft[index] = c;
                if(index==wordLength-1){
                    index=-1;
                }
                index++;
            }
            wordChar=oneLeft;
        }
        char[] result = new char[wordLength];
        int index2=0;
        for (int i = wordLength-1; i >-1 ; i--) {
            result[index2]=wordChar[i];
            index2++;
        }

        return String.valueOf(result);
    }

}
