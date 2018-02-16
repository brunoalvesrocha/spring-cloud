package com.techprimers.stock.helloclient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brunorocha
 */
public class Test {

    public static void main(String[] args) {
        String input = "pcmpcmbbbzz";
        char[] letters = null;
        letters = input.toCharArray();

        Integer p = 0;
        Integer c = 0;
        Integer m = 0;
        Integer b = 0;
        Integer z = 0;
        Integer numberOfTeam = 0;

        for(char letter: letters) {
            String currentLetter = Character.toString(letter).toUpperCase();
            if(currentLetter.equals("P")) {
                p ++;
            }
            if(currentLetter.equals("C")) {
                c ++;
            }
            if(currentLetter.equals("M")) {
                m ++;
            }
            if(currentLetter.equals("B")) {
                b ++;
            }
            if(currentLetter.equals("Z")) {
                z ++;
            }


            if(p==1 && c==1 && m==1 && b==1 && z ==1) {
                numberOfTeam ++;
            }
        }
        System.out.println(numberOfTeam);




    }
}
