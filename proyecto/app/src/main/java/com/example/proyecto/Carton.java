package com.example.proyecto;

import android.widget.*;
import java.util.*;

public class Carton {

    private ArrayList<TextView> AL;
    private ArrayList<Integer> numeros = new ArrayList<>();
    private int[][] matriz = new int[3][9];
    public Boolean fila = false;
    public Boolean bingo = false;

    public Carton(ArrayList<TextView> AL){

        this.AL = AL;

        iniciaNumeros();
        ponHuecos();
        iniciaArray();
        iniciaMatriz();
    }
    public void marcar(int n){
        TextView aux;
        Iterator<TextView> iter = AL.iterator();
        while(iter.hasNext()) {
            aux = iter.next();
            if (aux.getText().equals(Integer.toString(n))) {
                aux.setBackgroundColor(0xFF00FF00);
                actualiza(n);
                if (!fila) {
                    pruebaFila(n);
                } else {
                    pruebaBingo(n);
                }
            }
        }
    }

    public void pruebaFila(int n){
        int sumafila = 0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                sumafila+=matriz[i][j];
            }
            if(sumafila==0){
                fila=true;
                break;
            }else{
                sumafila=0;
            }
        }
    }
    public void pruebaBingo(int n){
        int sumabingo = 0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                sumabingo += matriz[i][j];
            }
        }
        if(sumabingo==0){
            bingo=true;
        }
    }
    public void actualiza(int n){
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                if(matriz[i][j] == n){
                    matriz[i][j]=0;
                }
            }
        }
    }
    public void iniciaArray(){
        int num;
        TextView aux;
        Iterator<TextView> iter = AL.iterator();
        Iterator<Integer> iter2 = numeros.iterator();
        while(iter.hasNext() && iter2.hasNext()){
            num = iter2.next();
            aux = iter.next();
            if(num==0){
                aux.setText("X");
                aux.setBackgroundColor(0xFF00FF00);
            }else{
                aux.setBackgroundColor(0x00000000);
                aux.setText(Integer.toString(num));
            }
        }
    }

    public void iniciaNumeros(){
        int num;
        Bolas bolas = new Bolas(90);
        for(int i=0;i<AL.size();i++){
            num = bolas.sacaBola();
            numeros.add(num);
        }
        Collections.sort(numeros);
    }
    public void iniciaMatriz(){
        int k=0;
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                matriz[i][j] = numeros.get(k);
                k++;
            }
        }
    }
    public void ponHuecos(){
        ArrayList<Integer> linea1 = (new Bolas(9)).generaArray();
        ArrayList<Integer> linea2 = (new Bolas(9)).generaArray();
        ArrayList<Integer> linea3 = (new Bolas(9)).generaArray();

        ArrayList<Integer> huecos = new ArrayList<>();

        for(int i=0;i<4;i++){
            huecos.add(linea1.get(i));
            huecos.add(linea2.get(i)+9);
            huecos.add(linea3.get(i)+18);
        }

        Iterator<Integer> iter = huecos.iterator();
        while(iter.hasNext()){
            numeros.set(iter.next(),0);
        }
    }
}
