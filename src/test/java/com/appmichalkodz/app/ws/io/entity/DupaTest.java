package com.appmichalkodz.app.ws.io.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DupaTest {

    @Test
    public void zadanie6(){
        int n = 9;
        String poczatekIKoniec = "";
        String srodek = "";

        for (int i = 0; i < n; i++) {
            poczatekIKoniec += "*";

        }
        for (int i = 1; i < n - 1; i++) {
            srodek += " ";

        }
        srodek = "*" + srodek + "*";

            for (int i = 0; i < n; i++) {
                if (i == n - 1 || i == 0) {
                    System.out.println(poczatekIKoniec);
                } else {
                    System.out.println(srodek);
                }
        }
    }


    @Test
    public void zadanie7(){
        //trojkat rownoboczny
        System.out.println("*");
        System.out.println("**");
        System.out.println("***");
    }


    @Test
    public void zadanie8(){
        int n = 5;
        String counter = "*";

        for(int i =0; i<n;i++){
            System.out.println(counter);
            counter+="*";
        }
    }

    @Test
        public void zadanie9(){
            //modulo
            int n = 4;
            int reszta= n % 2;
            if(reszta == 0){
                System.out.println("to jesrt liczba parzytsrta");
            } else {
                System.out.println("to nie jest parzysta liczba");


            }

    }

    @Test
    public void zadanie10() {

        double x1 = 13.1;
        double x2 = 14.5;
        double x3 = 6.7;

        if (x1 < x2) {
            if (x1 < x3) {
                System.out.println("X1");
            } else {
                System.out.println("X3");
            }
        } else {
            if (x2 < x3) {
                System.out.println("X2");
            } else {
                System.out.println("X3");
            }
        }
    }

    @Test
    public void zadanie10A() {
        double tablica[] = {13.1, 14.5, 256.7, 4.7, 1.1, 1.2, 66.6, 0.1};
        double min=tablica[0];

        for(int i =0; i< tablica.length-1; i++){
            if(min>tablica[i+1]){
                min = tablica[i+1];
            }
        }
        System.out.println(min + "Wynik");

    }

    @Test
    public void zadanie11(){
        int suma =0;
        for(int i =0;i< 10;i++){
            suma+=i;
        }

        System.out.println(suma);
    }


    @Test
    public void dmeo(){
        metoda(7, 2);
    }
public void metoda (int a, int k){
    System.out.println(a);
    a=a+k;
    if(a<99){
        metoda(a, k);
    }

}

}