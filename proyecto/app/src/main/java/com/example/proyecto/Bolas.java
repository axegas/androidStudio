package com.example.proyecto;

import java.util.*;

public class Bolas {

    private ArrayList<Integer> ALint = new ArrayList<Integer>();
    private int numeros;

    public Bolas(int n){
        this.numeros = n;
        for(int i=1;i<=n;i++){
            ALint.add(i);
        }
    }
    public int sacaBola(){
        Random r = new Random();
        int id = r.nextInt(ALint.size());
        int num = ALint.get(id);
        ALint.remove(id);
        return num;
    }
    public ArrayList<Integer> generaArray(){
        ArrayList<Integer> AL = new ArrayList<>();
        int num;
        for(int i=0;i<numeros;i++){
            num = this.sacaBola()-1;
            AL.add(num);
        }
        return AL;
    }
}
