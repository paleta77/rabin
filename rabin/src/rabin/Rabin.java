/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Math.abs;
import java.nio.file.Files;
import static rabin.Rabin.euklides;

/**
 *
 * @author pawel
 */
public class Rabin {

    /**
     * @param args the command line arguments
     */
    public static int generujKlucz(int p, int q) {
        return p * q;
    }

    public static void szyfruj(int klucz, String path) throws IOException {
        //otwieranie zaszyfrowanego pliku
        File file = new File(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        //tablica ktora zostanie pozniej zapisana do pliku
        int[] output = new int[fileContent.length];
        //szyfrowanie
        for (int i = 0; i < fileContent.length; i++) {
            System.out.print(fileContent[i] + " ");
            output[i] = (fileContent[i] * fileContent[i]) % klucz;
        }
        for (int i = 0; i < fileContent.length; i++) {
            System.out.print(output[i]);
        }
        //zapis odszyfrowanego pliku
        File fileOut = new File("plikOdszyfrowany.pdf");
        FileOutputStream fop = new FileOutputStream(fileOut);
        //fop.write(output);
        fop.close();
    }

    public static long[] euklides(long a, long b) {
        long x = 0, y = 1;
        long prevx = 1, prevy = 0, temp;
        long res_x, res_y;
        while (b != 0) {
            long q = a / b;
            long r = a % b;
            a = b;
            b = r;

            temp = x;
            x = prevx - q * x;
            prevx = temp;

            temp = y;
            y = prevy - q * y;
            prevy = temp;
        }
        //System.out.println("Roots  x : "+ prevx +" y :"+ prevy);
        res_x = prevx;
        res_y = prevy;

        long[] wynik = new long[2];
        wynik[0] = res_x;
        wynik[1] = res_y;
        return wynik;
    }

    public static int decrypt(int c, int p, int q, int klucz, long wynik[]) {

        int mp = (int) ((Math.pow(c, 0.25 * (p + 1))) % p);
        int mq = (int) ((Math.pow(c, 0.25 * (q + 1))) % q);

        int r = (int) abs(((wynik[0] * p * mq) + (wynik[1] * q * mp)) % klucz);
        int _r = (int) abs((klucz - r));
        int s = (int) abs(((wynik[0] * p * mq) - (wynik[1] * q * mp)) % klucz);
        int _s = (int) abs((klucz - s));

        //System.out.println(checkPadding(_r));
        //System.out.println(r + " " + _r + " " + s + " " + _s);
        if (checkPadding(r) == true) {
            return removePadding(r);
        }
        if (checkPadding(_r) == true) {
            return removePadding(_r);
        }
        if (checkPadding(s) == true) {
            return removePadding(s);
        }
        if (checkPadding(_s) == true) {
            return removePadding(_s);
        }

        return 0;
    }

    public static int encrypt(int m, int n) {
        int c = (m * m) % n;
        return c;
    }

    public static int addPadding(int c) {
        int dlugosc = 7;
        int[] bits = new int[dlugosc];
        bits[dlugosc-1] = 2;
        bits[dlugosc-2] = 0;
        bits[dlugosc-3] = 2;
        for (int i =0; i < dlugosc-4; i++) {
            bits[i] = (c%2)*2;
            c=c/2;
        }
        for (int i = dlugosc - 1; i >= 0; i--) {
            //System.out.println(bits[i]);
        }
        int decimal = 0;
        for (int i = 0; i < dlugosc; i++) {
            decimal += Math.pow(bits[i], i);
        }
        System.out.print("decimal " + decimal + " ");

        return decimal;
    }

    public static boolean checkPadding(int input) {
        int dlugosc = 7;
        int[] bits = new int[dlugosc];
        System.out.println("checkPadding");
        for (int i =0; i < dlugosc-4; i++) {
            bits[i] = (input%2)*2;
            input=input/2;
        }
        if (bits[2] == 2 && bits[1] == 0 && bits[0] == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static int removePadding(int input) {
        int dlugosc = 6;
        boolean[] bits = new boolean[dlugosc];
        for (int i = dlugosc - 1; i >= 0; i--) {
            bits[i] = (input & (1 << i)) != 0;
        }
        bits[dlugosc - 1] = false;
        bits[dlugosc - 2] = false;
        bits[dlugosc - 3] = false;
        int[] bitsInt = new int[dlugosc];
        for (int i = dlugosc - 1; i >= 0; i--) {
            if (bits[i] == true) {
                bitsInt[i] = 2;
            } else {
                bitsInt[i] = 0;
            }
        }
        int decimal = 0;
        for (int i = 0; i < dlugosc; i++) {
            decimal += Math.pow(bitsInt[i], i);
        }
        System.out.print("decimal " + decimal + " ");
        return decimal;
    }

    public static void main(String[] args) throws IOException {
        int p = 7;
        if (p % 4 != 3) {
            System.err.println("p nie spelnia wymagan");
        }
        int q = 11;
        if (q % 4 != 3) {
            System.err.println("q nie spelnia wymagan");
        }
        int klucz = generujKlucz(p, q);
        System.out.println("klucz " + klucz);

        //String napis = "ABCDE";
        /*File file = new File("plik.txt");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        int [] fileContentInt = new int[fileContent.length];
        for (int i=0;i<fileContent.length;i++)
        {
            fileContentInt[i] = fileContent[i];
        }
        System.out.println("Wiadmosc");
        for (int i=0; i<fileContentInt.length; i++) System.out.print(fileContentInt[i] + " ");
        int c[] = new int[fileContentInt.length];
        System.out.println("");*/
        //szyfrowanie
        //for (int i=0; i<fileContentInt.length; i++) 
        
        int c = encrypt(addPadding(4), klucz);

        System.out.println("c " + c);
        //deszyfrowanie
        long[] wynik = new long[5];
        wynik = euklides(p, q);
        System.out.println("odszyfrowana wiadomosc");
        //for (int i=0; i<fileContentInt.length; i++){
        System.out.println(decrypt((int) c, p, q, klucz, wynik));;
        //}
        
        

    }

}
