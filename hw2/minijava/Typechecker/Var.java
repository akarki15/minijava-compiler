package minijava.Typechecker;
import minijava.Type.*;

public class Var {
	private String _name;
	private Type _type; 
	//private boolean _static;
	//private int _privacy; 	//1: public, 2: Protected, 3: no modifier, 4: private
	
	public Var(String n, Type t){
		_name=n;
		_type=t;
		
		//_static=s;
		//_privacy=p;
	}
	
}
