/**
*
* @author Esra KIZILELMA esra.kizilelma@ogr.sakarya.edu.tr
* @since 01.04.2022
* <p>
* 2B birinci ogretim 
* </p>
*/
package b191210040;

import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import b191210040.Lexical;

public class Program {

	public static void main(String[] args) {
		
		  
		
		Lexical lexical = new Lexical();
		System.out.println("Operator Bilgisi: ");
	    System.out.println("\t Tekli opetator Sayisi: "+ lexical.tekli_operator);
	    System.out.println("\t Ikili opetator Sayisi: " + lexical.ikili_operator);
	    System.out.println("\t Sayisal opetator Sayisi: " + lexical.sayisal_operators);
	    System.out.println("\t Iliskisel opetator Sayisi: " + lexical.iliskisel_operators);
	    System.out.println("\t Mantiksal opetator Sayisi: " + lexical.mantiksal_operators);
	    System.out.println("Operand Bilgisi:");
	    System.out.println("\t Toplam Operand Sayisi: "+ lexical.operand);
	    }
}
