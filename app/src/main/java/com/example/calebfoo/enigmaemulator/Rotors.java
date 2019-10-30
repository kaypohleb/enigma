package com.example.calebfoo.enigmaemulator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Rotors {
    Rotor[] rotors  =  new Rotor[3];


    String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private HashMap<String, String> arrRotorsMap = new HashMap<>();
    private HashMap<String, int[]> arrNudge = new HashMap<>();
    public Rotors(){
        arrRotorsMap.put("I", "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        arrRotorsMap.put("II", "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        arrRotorsMap.put("III", "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        arrRotorsMap.put("IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB");
        arrRotorsMap.put("V", "VZBRGITYUPSDNHLXAWMJQOFECK");
        arrRotorsMap.put("VI", "JPGVOUMFYQBENHZRDKASXLICTW");
        arrRotorsMap.put("VII", "NZJHGRCXMYSWBOUFAIVLPEKQDT");
        arrRotorsMap.put("VIII", "FKQHTLXOCBJSPDZRAMEWNIUYGV");
        arrRotorsMap.put("Beta", "LEYJVCNIXWPBQMDRTAKZGFUHOS");
        arrRotorsMap.put("Gamma", "FSOKANUERHMBTIYCWLQPZXVGJD");
        arrNudge.put("I", new int[]{17}); //Q
        arrNudge.put("II", new int[]{5, 5});//E
        arrNudge.put("III", new int[]{22,22}); //V
        arrNudge.put("IV", new int[]{10,10}); //J
        arrNudge.put("V", new int[]{26,26}); //Z
        arrNudge.put("VI", new int[]{26,13}); // Z/M
        arrNudge.put("VII", new int[]{26,13}); // Z/M
        arrNudge.put("VIII", new int[]{26,13}); // Z/M
    }

    //map is inner, rotor is outer.
    public String getRotorString(String n){
        return this.arrRotorsMap.get(n);
    }
    public int[] getKnockOffPoints(String n){
        return this.arrNudge.get(n);
    }
    public void rotorStep(){
        boolean nextStep = true;
        for(int i=0; i<3;i++) {
            if (nextStep) {
                this.rotors[i].posIncrement();
                nextStep = this.rotors[i].isKnockOff();
            } else {
                break;
            }
        }
    }

    public char sendForward(char c){
        for(int i = 2 ; i>=0;i--) {
            c = this.rotors[i].forwardPermutate(c);
        }
        return c;
    }
    public char sendInverse(char c){
        char temp = c;
        for(int i = 0; i<3 ;i++) {
            temp = this.rotors[i].inversePermutate(temp);
        }
        return temp;
    }


}