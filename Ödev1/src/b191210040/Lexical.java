/**
*
* @author Esra KIZILELMA esra.kizilelma@ogr.sakarya.edu.tr
* @since 01.04.2022
* <p>
* 2B birinci ogretim 
* </p>
*/
package b191210040;

import java.io.*; 
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Lexical {

	public enum Isaret
    {
        hicbirsey,
        tektirnak,		//'
        cifttirnak,		// "
        slash		//  /
    } //yorum sat�r�,char,string olup olmad��� kontrol edilir ",',/ 
	
	Isaret isaret=Isaret.hicbirsey;
	String Text=""; 
    String fName = "z.java"; 		
    String line = null; 
    int operator = 0;		  
    int operand = 0; 		 
    int tekli_operator = 0;
    int ikili_operator = 0;
    int sayisal_operators = 0;
    int iliskisel_operators = 0;
    int mantiksal_operators = 0;
    boolean ismorethanoneline = true;
    { 
		try                                    //dosya okuma
    	{            
            FileReader fReader =new FileReader("z.java");
            BufferedReader bReader =new BufferedReader(fReader);
            
            while((line = bReader.readLine()) != null) 
            {
                Text+=line+"\n";
            }
            bReader.close();
        }
        catch(FileNotFoundException exception) 
        {
            System.out.println("Dosya Ac�lam�yor '" +fName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Dosya Okuma Hatas� '" + fName + "'");
        }   	                                  
        
	 for(int i = 1 ; i < Text.length() ; i++ )		//dosyadaki t�m karakterlere tek tek bak�l�yor
     {
         if(ismorethanoneline)          //kod olup olmad��� kontrol edilir
         {   
        	 if((Text.charAt(i-1)=='/'&&Text.charAt(i)=='*'))
             {
            	 ismorethanoneline=false;    //yorum sat�r�
            	 isaret=Isaret.slash;
             }
             if(Text.charAt(i-1)==Text.charAt(i))
             {
            	 if(Text.charAt(i)=='/')		//yorum sat�r�
            	 {
            		 isaret=Isaret.slash;
            	 }
             }
             if(Text.charAt(i)=='\n'&&isaret==Isaret.slash&& ismorethanoneline) 	//kod olup olmad��� kontrol edilir
             {
            	isaret=Isaret.hicbirsey;
             }

             if(Text.charAt(i)=='\''&&isaret!=Isaret.slash) 		//kod olup olmad��� kontrol edilir
             {
            	 if(isaret==Isaret.hicbirsey)
            		 isaret=Isaret.tektirnak;
            	 else if(isaret==Isaret.tektirnak)
            		 isaret=Isaret.hicbirsey;
             }
             else if(Text.charAt(i)=='\"'&&isaret!=Isaret.slash)
             {
            	 if(isaret==Isaret.hicbirsey)
            		 isaret=Isaret.cifttirnak;
            	 else if(isaret==Isaret.cifttirnak)
            		 isaret=Isaret.hicbirsey;
             }
             if(isaret==Isaret.hicbirsey)
             {
            	 if((Text.charAt(i-1)=='+'||Text.charAt(i-1)=='-'||Text.charAt(i-1)=='*'||Text.charAt(i-1)=='/'||Text.charAt(i-1)=='%'||Text.charAt(i-1)=='&'||Text.charAt(i-1)=='|'||Text.charAt(i-1)=='^'||Text.charAt(i-1)=='='))
            	 { 
            		 if(Text.charAt(i-1)!='='&&Text.charAt(i)=='=')		//+= -= /= *= %= &= |= ^= bulunur
            		 {
            			 sayisal_operators--;
                		 ikili_operator--;
                		 operand -= 2; 
            		 }
            		 if(Text.charAt(i-1)==Text.charAt(i))		// || && ++ -- == bulunur
                	 {            			 
                		 if(Text.charAt(i)=='|'||Text.charAt(i)=='&') 
                		 {
                			 ikili_operator--;
                			 mantiksal_operators++; 
                			 sayisal_operators-=2;
                			 operand -= 2;
                		 } 
                		 if(Text.charAt(i)=='+'||Text.charAt(i)=='-') 
                		 {
                			 ikili_operator--;
                			 tekli_operator++;
                			 sayisal_operators--;
                			 operand -= 3;
                		 }
                		 if(Text.charAt(i)=='=') 
            			 {
                			 sayisal_operators-=2;
                			 ikili_operator--;		//ikili operator, say�sal operator olmad��� i�in ikili operator say�s� azalt�l�r
            				 iliskisel_operators++;
            				 operand -= 2;
            			 }
                		 ikili_operator--;
                	 }
            		 ikili_operator++;		//genel bir artt�rma yapt�m ikili kabul edilmeyenleri yukar�da azaltt�m
            	     sayisal_operators++;
            	     operand+=2;
            	 }
            	 if((Text.charAt(i)=='<'||Text.charAt(i)=='>'||Text.charAt(i)=='!')&&Text.charAt(i+1)=='=')		//<= >= !=
            	 {
            	 	 ikili_operator--;
               	     sayisal_operators--;
               	     iliskisel_operators++;            	 		
            	 } 
            	 else if((Text.charAt(i)=='<'||Text.charAt(i)=='>') && (Text.charAt(i+1)!='='))			//< >
            	 {
            	 	 iliskisel_operators++;
            	 	 operand += 2;;
            	 }
            	 else if(Text.charAt(i)=='!' && (Text.charAt(i+1)!='=')) 		// ! saymak i�in
                 {
            		 mantiksal_operators++; 
            		 operand++;           	 
            	 }
             }
         }
		 if(Text.charAt(i-1)=='*'&&Text.charAt(i)=='/') 		//yorum sat�r� sonu
         {
             ismorethanoneline=true;		
             isaret=Isaret.hicbirsey;
             i++;		//yorum sat�r�ndan bir sonraki indexten devam etmek i�in 
         }
     }
   }
}