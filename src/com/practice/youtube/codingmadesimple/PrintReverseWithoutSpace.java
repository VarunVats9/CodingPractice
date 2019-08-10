package com.practice.youtube.codingmadesimple;

public class PrintReverseWithoutSpace {

    public static void main(String[] args) {
        reverseSentence("Hello I am Varun".toCharArray(), -1, false);
    }

    private static void reverseSentence(char[] sentence, int ind, boolean print) {
        if (sentence.length == ind) {
            return;
        }
        if (ind >= 0 && sentence[ind] == '%') {
            sentence[ind] = ' ';
            return;
        }

        if (print) {
            System.out.print(sentence[ind]);
        }
        reverseSentence(sentence, ind+1, print);
        if (ind >= 0 && sentence[ind] == ' ') {
            reverseSentence(sentence, ind+1, true);
            System.out.print(sentence[ind]);
            sentence[ind] = '%';
        }

        if (ind == -1) {
            reverseSentence(sentence, ind+1, true);
        }
    }
}
