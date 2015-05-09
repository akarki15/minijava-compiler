package minijava.Type;
import minijava.node.*;


public abstract class Type {

    public final static Type intType = new IntType();
    public final static Type stringType = new StringType();
    public final static Type nullType = new NullType();
    public final static Type voidType = new VoidType();
    public final static Type booleanType = new BooleanType();

    abstract public String toString();
    public boolean canAssignTo(Type t) {
    	if (t.isArray()){
    		ArrayType temp=(ArrayType)t;			
			return canAssignTo(temp.returnType());
    		}
    	return this.equals(t);}
    public boolean equals(Type t) { return this == t;}
    
    public boolean isInt(){ return false;}
    public boolean isString(){ return false;}
    public boolean isNull(){ return false;}
    public boolean isVoid(){ return false;}
    public boolean isBoolean(){ return false;}
    public boolean isArray(){return false;}
    
    static class IntType extends Type {
	public String toString() {
	    return "int";
	}
	public boolean isInt(){ return true;}
    }

    static class StringType extends Type {
	public String toString() {
	    return "String";
	}
	public boolean isString(){ return true;}    	
    }

    static class BooleanType extends Type {
	public String toString() {
	    return "boolean";
	}
	public boolean isBoolean(){ return true;}
    }

    static class NullType extends Type {
	public String toString() {
	    return "null";
	}
	
	public boolean canAssignTo (Type t) {
//		System.out.println(t+" wowo "+(t==stringType));
	    return t == this || t.isArray() ||/*t instanceof ArrayType ||*/
		t == stringType;
	}
	public boolean isNull(){ return true;}
    }

    static class VoidType extends Type {
	public String toString() {
	    return "void";
	}
	public boolean canAssignTo (Type t) {
	    return false;
	}
	public boolean isVoid(){ return true;}
    }
        
}