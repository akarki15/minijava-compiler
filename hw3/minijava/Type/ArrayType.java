package minijava.Type;
import minijava.node.*;

public class ArrayType extends Type{
	private Type _typeOfArray;
		
	public ArrayType(Type t){ 
		_typeOfArray=t;		
	}
	
	public String toString(){
		return ("Array Type of "+_typeOfArray);
	}
	public boolean isArray(){
		return true;
	}
	public Type returnType(){
		return _typeOfArray;
	}
	public boolean canAssignTo(Type t){
		if (t.isArray()){
    		ArrayType temp=(ArrayType)t;//Downcasting legal since isArray() true
    		t=temp.returnType();
    		}
		return this.returnType().canAssignTo(t);
	}
	public boolean equals(Type t){		
		if (t.isArray()){
			ArrayType aT=(ArrayType)t;//Downcasting legal since isArray() is true
			return (this.returnType().equals(aT.returnType()));				
		}
		return false;
	}
}