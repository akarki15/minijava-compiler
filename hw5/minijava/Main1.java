package minijava;

import java.io.*;

import minijava.ErrorHandler.*;
import minijava.lexer.*;
import minijava.node.*;

public class Main1 {
    
    public static void main(String[] args){
	
	ErrorHandler errorHandler = null;
	Token t=null;
	try{
	    if (args.length != 1) {
		System.err.println("Usage: java Main filename");
		System.exit(1);
	    }

	    String filename = args[0];
	    
	    if (!(filename.substring(filename.length()-5).equals(".java"))) {
		System.err.println ("Filename must have suffix .java");
		return;
	    }

	    String fileBaseName = filename.substring(0,filename.length()-5);

	    Reader in = new FileReader(filename);
	    errorHandler = new ErrorHandler(filename);

	    Lexer l = new Lexer(new PushbackReader(in, 1024));

	    
	    do {
		t = l.next();
		System.out.println("Found token from " + t.getClass() 
				   + ".\n   Lexeme is " + t);
		//System.out.println("__"+errorHandler.getLongMessage (t.getText())); 
	    } while (!(t instanceof EOF));
	}
	catch(LexerException e) {
	    System.err.println(e);
	    System.err.println(errorHandler.getLongMessage(e.getMessage()));
	}
	catch(Exception e){
	    throw new RuntimeException(e);
	}
    }
}