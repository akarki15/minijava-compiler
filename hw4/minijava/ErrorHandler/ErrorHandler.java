package minijava.ErrorHandler;
import minijava.lexer.*;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PushbackReader;
import minijava.node.*;
import java.io.IOException;
import java.util.*;

public class ErrorHandler {

    String filename;

    public ErrorHandler (String filename) {
	this.filename=filename;
    }

    public String getLongMessage(String s) {	
	String line="";
	String col="";
	int n=1;
	do {
	    line+=s.charAt(n);
	    n++;
		}while(s.charAt(n)!=',');
	n++;
	do {
	    col+=s.charAt(n);
	    n++;
	}while(s.charAt(n)!=']');
	int l=Integer.parseInt(line);
	int c=Integer.parseInt(col);
	System.out.println("The Error was detected at line "+l+", column "+ c+".");
	System.out.println("Here is line "+l+". The carat mark(^) indicates where the error was detected.");
	int currentChar=-1;	
	int lineCounter=1;
	int colCounter=0;
	String colPointer="";

       
	try{
	    FileReader in=new FileReader(filename);
	    BufferedReader br=new BufferedReader(in); 
	    
	    while((currentChar=br.read())!=-1){
		if (currentChar==10) 
		    lineCounter++;
		if (lineCounter==l)
		    break;
	    }
	    System.out.println();
	    while((currentChar=br.read())!=10){
		
		System.out.print((char)currentChar);
		colCounter++;
		if (colCounter==c)colPointer+="^";
		else if (Character.isWhitespace((char)currentChar))
		    colPointer+=(char)currentChar;
		else
		    colPointer+=" ";
	    }
	}catch(IOException e){
	    e.printStackTrace();
	}
	System.out.println();
	System.out.println(colPointer);
	
	
	throw new UnsupportedOperationException();
    }
}
