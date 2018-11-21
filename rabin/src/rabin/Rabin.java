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
 
    public static void compute(long a, long b)
	    {
                long res_x,res_y;
	        long x = 0, y = 1;
	        long prevx = 1, prevy = 0, temp;
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
}
    


    public static void main(String[] args) throws IOException {
        int p = 7;
        int q = 11;
        int klucz = generujKlucz(p, q);
        System.out.println("klucz " + klucz);
        int M = 68;
        int c = (M*M)%klucz;
        
        System.out.println("c " + c);
        int mp = (int)((Math.pow(c, 0.25*(p+1)))%p);
        int mq = (int)((Math.pow(c, 0.25*(q+1)))%q);

        System.out.println(mp + " "+ mq);
        
        int m1 = (int)(Math.pow(c,(p+1)/4)%p);
        int m3 = (int)(Math.pow(c,(q+1)/4)%q);
        int m2 = (int)(p - Math.pow(c,(p+1)/4)%p);
        int m4 = (int)(q - Math.pow(c,(q+1)/4)%q);
        
        int a = (int)(q*(Math.pow(q, -1)%p));
        int b = (int)(p*(Math.pow(p, -1)%q));
        
        System.out.println("a: " + a + " b:" + b);
        
        int M1 = (int)(a*m1+b*m3)%klucz;
        int M2 = (int)(a*m1+b*m4)%klucz;
        int M3 = (int)(a*m2+b*m3)%klucz;
        int M4 = (int)(a*m2+b*m4)%klucz;
        
        int r = abs((int)((-3*p*mq)+(2*q*mp))%klucz);
        int _r = abs((int)(klucz - r));
        int s = abs((int)((-3*p*mq)-(2*q*mp))%klucz);
        int _s = abs((int)(klucz - s));     
        
        
        System.out.println(r + " "+ _r + " " + s + " " + _s);
        
       //System.out.println(m1 + " "+ m2 + " " + m3 + " " + m4);
       //System.out.println(M1 + " "+ M2 + " " + M3 + " " + M4);
        
        
    }
    
}
