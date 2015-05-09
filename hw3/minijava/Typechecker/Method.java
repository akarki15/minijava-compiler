package minijava.Typechecker;
import java.util.*;
import minijava.Type.*;

public class Method {
	private String _name;
	private Type _type; 
	//private boolean _static;
	//private int _privacy; 	//1: public, 2: Protected, 3: no modifier, 4: private
	private List<Var> _paramList;
	
	public Method(String n, Type t, List<Var> p){
		_name=n;
		_type=t;
		_paramList=p;		
	}
	public String getName(){
		return _name;
	}
	public List<Var> getParam(){
		return _paramList;
	}
	public Type returnType(){
		return _type;
	}
}

	
