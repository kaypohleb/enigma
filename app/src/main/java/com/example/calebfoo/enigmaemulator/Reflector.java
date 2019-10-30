package com.example.calebfoo.enigmaemulator;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Objects;

public class Reflector {
    HashMap<Character,Character> map = new HashMap<>();
    private String name;
    private String[] matches;
    private String b_thin = "AE BN CK DQ FU GY HW IJ LO MP RX SZ TV";
    private String c_thin = "AR BD CO EJ FN GT HK IV LM PW QZ SX UY";
    public Reflector(String n){
        this.name = n;
        if(Objects.equals(n, "b_thin")){
            matches = b_thin.split(" ");
        }
        else if(Objects.equals(n, "c_thin")){
            matches = c_thin.split(" ");
        }
        else{
            matches = null;
        }
        for(String s:matches){
            char[] letters = s.toCharArray();
            this.map.put(letters[0],letters[1]);
            this.map.put(letters[1],letters[0]);

        }
        System.out.println("Reflector set");
    }
    public void clear(){
        map.clear();
    }
    public char reflect(char c){
        if(map.containsKey(c)){
            return map.get(c);
        }
        return c;
    }
}
