package minijava.Typechecker;
import minijava.Type.*;
import minijava.Translate.*; 
import minijava.Temp.*;
import minijava.Frame.*;
public class Var {
	private String name;
	private Type type; 	
	private Label label=null;
	private Access access=null;
	
	public Var(String n, Type t){
		name=n;
		type=t;
	}
	public String getName(){		
		return name;
	}
	public Type getType(){
		return type;
	}
	public String toString(){
		return name;
	}
	public void setLabel(Label l){
		label=l;
	}
	public Label getLabel(){
		return label;
	}
	//Not sure whether var should have an access object
	public void setAccess(Access a){
		access=a;
	}
	public Access getAccess(){
		return access;
	}	
}
