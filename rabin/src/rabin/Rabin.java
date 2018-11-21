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
import static rabin.Rabin.compute;

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
 
    public static long[] compute(long a, long b)
	    {               
	        long x = 0, y = 1;
	        long prevx = 1, prevy = 0, temp;
                long res_x, res_y;
	        while (b != 0)
	        {
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
	        System.out.println("Roots  x : "+ prevx +" y :"+ prevy);
	        res_x = prevx;
	        res_y = prevy;
                
                long[] wynik = new long[2];
                wynik[0] = res_x;
                wynik[1] = res_y;
                return wynik;
}
    


    public static void main(String[] args) throws IOException {
        int p = 31;
        if(p%4!=3) System.err.println("p nie spelnia wymagan");
        int q = 19;
        if(q%4!=3) System.err.println("q nie spelnia wymagan");
        int klucz = generujKlucz(p, q);
        System.out.println("klucz " + klucz);
        long[] wynik = new long[2];
        wynik=compute(p,q);
        int M = 86;
        int c = (M*M)%klucz;
        
        System.out.println("c " + c);
        int mp = (int)((Math.pow(c, 0.25*(p+1)))%p);
        int mq = (int)((Math.pow(c, 0.25*(q+1)))%q);

        System.out.println(mp + " "+ mq);
        
        int r = abs((int)((wynik[0]*p*mq)+(wynik[1]*q*mp))%klucz);
        int _r = abs((int)(klucz - r));
        int s = abs((int)((5*p*mq)-(-6*q*mp))%klucz);
        int _s = abs((int)(klucz - s));     
              
        System.out.println(r + " "+ _r + " " + s + " " + _s);
        
        //compute(p, q);
        //System.out.println(res_x + res_y);
        
    }
    
}
