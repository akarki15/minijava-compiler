package minijava.Typechecker;
import minijava.Type.*;

public class Var {
	private String _name;
	private Type _type; 	
	
	public Var(String n, Type t){
		_name=n;
		_type=t;
	
	}
	public String getName(){		
		return _name;
	}
	public Type getType(){
		return _type;
	}
	public String toString(){
		return _name;
	}
	
	
}
