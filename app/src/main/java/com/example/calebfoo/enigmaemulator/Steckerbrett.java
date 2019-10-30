package com.example.calebfoo.enigmaemulator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Steckerbrett {
    HashMap<Character,Character> plugboard;
    Stack<Character> stack;


    public Steckerbrett(){
        this.plugboard = new HashMap<>();
        this.stack = new Stack<>();
    }

    public void cleanString(String s){
        boolean fail = false;
        String[] letters = s.split(":");
        List<Character> clist = new ArrayList<>();
        for(String l:letters){
            if(l != null) {
                l = l.trim();
                if (l.length() > 1) {
                    fail = true;
                    break;
                } else {
                    clist.add(l.charAt(0));
                }
            }
        }
        if(!fail){
            addPlug(clist.get(0),clist.get(1));
            System.out.print("First Plug" + clist.get(0));
            System.out.print("Second Plug" + clist.get(1));
        }

    }

    private void addPlug(char f, char s){
        this.plugboard.put(f,s);
        this.plugboard.put(s,f);
        stack.push(f);
    }

    public void removePlug(){
        if(!this.plugboard.isEmpty()) {
            char prev = this.stack.pop();
            char temp = this.plugboard.get(prev);
            this.plugboard.remove(prev);
            this.plugboard.remove(temp);

        }
    }

    public void reset(){
        this.plugboard.clear();
    }

    public char checkSwap(char c){
        if(this.plugboard.containsKey(c)){
            return this.plugboard.get(c);
        }
        return c;
    }

}
