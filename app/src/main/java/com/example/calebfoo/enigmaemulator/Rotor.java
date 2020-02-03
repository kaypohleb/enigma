package com.example.calebfoo.enigmaemulator;

import android.util.Log;

public class Rotor {
    private String plainText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String originalMap;
    private String alphaMap;
    private int[] knockOffpts;
    private int pos;
    private String name;
    public Rotor(String name,String map,int[] kp) {
        this.knockOffpts = kp;
        this.originalMap = map;

    }


    public void posIncrement(){
        this.pos = (this.pos+1) % 26;
        step();
        System.out.println("Pos:" + String.valueOf(this.pos));
    }
    public void step(){
        String newStr = this.alphaMap.toString();
        newStr = newStr.charAt(newStr.length() - 1) + newStr.substring(0, newStr.length() - 1);
        this.alphaMap = newStr;
        System.out.println(this.alphaMap);
    }

    public int[] getKnockOffpts() {
        return this.knockOffpts;
    }

    public int getPos() {
        return this.pos;
    }

    public void setPos(char c){
        setInitialPos(c);
    }

    public String getAlphaMap() {
        return this.alphaMap.toString();
    }
    public boolean isKnockOff(){
        for(int k : this.knockOffpts){
            if(k == this.pos) {
                return true;
            }
        }
        return false;
    }

    public char inversePermutate(char c){
        int i = this.alphaMap.indexOf(c);
        System.out.println(i);
        System.out.println(this.plainText.charAt(i));
        return this.plainText.charAt(i);
    }

    public char forwardPermutate(char c){
        int i = this.plainText.indexOf(c);
        System.out.println(i);
        System.out.println(this.alphaMap.charAt(i));
        return this.alphaMap.charAt(i);
    }
    public char returnPosAlp(){
        return (char)(this.pos+64);
    }
    public void setInitialPos(char c){
        int n = Character.getNumericValue(c)-10;
        this.pos = 0;
        Log.i("InitPos",String.valueOf(n));
        this.alphaMap = this.originalMap;
        if(n>0) {
            for (int i = 0; i < n; i++) {
                posIncrement();
            }
        }
    }
}
