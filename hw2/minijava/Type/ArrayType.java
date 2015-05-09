package minijava.Type;

public class ArrayType extends Type{
	private Type _typeOfArray;

	public ArrayType(Type t){ 
		_typeOfArray=t;		
	}
	
	public String toString(){
		return ("Array Type of "+_typeOfArray);
	}
	
}