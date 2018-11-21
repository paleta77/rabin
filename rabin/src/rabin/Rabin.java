/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author pawel
 */
public class Rabin {

    /**
     * @param args the command line arguments
     */
    public static int generujKlucz(int p, int q){
        return p*q;
    }
    
    public static void szyfruj(int klucz, String path) throws IOException{
      //otwieranie zaszyfrowanego pliku
        File file = new File(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        //tablica ktora zostanie pozniej zapisana do pliku
        int[] output = new int[fileContent.length];
        //szyfrowanie
        for (int i = 0; i < fileContent.length; i++) {
            System.out.print(fileContent[i] + " ");
            output[i] = (fileContent[i]*fileContent[i])%klucz;
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
 
public static int gcdExtended(int a, int b, int x, int y) 
    { 
        // Base Case 
        if (a == 0) 
        { 
            x = 0; 
            y = 1; 
            return b; 
        } 
  
        int x1=1, y1=1; // To store results of recursive call 
        int gcd = gcdExtended(b%a, a, x1, y1); 
  
        // Update x and y using results of recursive 
        // call 
        x = y1 - (b/a) * x1; 
        y = x1; 
        //System.out.println(x + " " + y);
        return gcd; 
    } 
    


    public static void main(String[] args) throws IOException {
        int p = 7;
        int q = 11;
        int klucz = generujKlucz(p, q);
        
        int c = (10*10)%klucz;
        
        System.out.println(c);
        int mp = (int)((Math.pow(c, 0.25*(p+1)))%p);
        int mq = (int)((Math.pow(c, 0.25*(q+1)))%q);
        int x=1, y=0;
        int g = gcdExtended(mp, mq, x, y); 
        System.out.println("gcd(" + mp +  " , " + mq+ ") = " + g); 
        System.out.println(x + " " + y);
        
        int r = (int)(-3*p*mq+2*q*mp)%klucz;
        int _r = (int)(klucz - r);
        int s = (int)(-3*p*mq-2*q*mp)%klucz;
        int _s = (int)(klucz - s);     
        
        System.out.println(mp + " "+ mq);
        System.out.println(r + " "+ _r + " " + s + " " + _s);
        //System.out.println(M1 + " "+ M2 + " " + M3 + " " + M4);
        //System.out.println(M1 + " "+ M2 + " " + M3 + " " + M4);
        
        
    }
    
}
