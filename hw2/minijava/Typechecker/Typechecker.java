package minijava.Typechecker;

import minijava.node.*;
import minijava.Type.*;
import java.util.*;

public class Typechecker {
    Start root;

    HashMap<String,Type> typeMap;
    HashMap<String,Var>  classVarMap;
    List <Method> methodList;

    public Typechecker (Start s) {
    root = s;

	typeMap = new HashMap<String,Type>();
	typeMap.put ("int", Type.intType);
	typeMap.put ("String", Type.stringType);
	typeMap.put ("void", Type.voidType);
	typeMap.put ("boolean", Type.booleanType);
	classVarMap = new HashMap<String,Var>();
	methodList = new LinkedList<Method>();
    }

    public void phase1() {
		(new Phase1(this)).process(root);
    }

    public void createClassVar(String name, Type type, Token tok) {//what's the use of token here?
    	if (classVarMap.get(name)==null){
    		classVarMap.put(name, new Var (name, type ));
    		System.out.println("Variable "+name+"of type "+type +" created");
    	}
    	else
    		throw new TypecheckerException(tok,"Variable with same name and type already exists");
    }

    public void createMethod(String name, Type returnType, 
			     List<Type> paramTypes, Token tok) {	
    	
    	int index=checkNameAndParamSize(paramTypes, name);    	
    	if (index!=-1){
    		if (checkParam(paramTypes,name,index)){
    			throw new TypecheckerException(tok,"Method with same name and parameter list already exists");	
    		}
    		
    	}    		
    	methodList.add(new Method(name, returnType, paramTypes));
    	System.out.println("Method "+name+"of return type "+returnType+" and  paramlist "+paramTypes +" created");
    	
    }
    
    private boolean checkParam(List<Type> paramTypes, String name,int index){    	    	
    	boolean boolParam=true;
    	for (int i=0;i <paramTypes.size();i++){
    		 if (!paramTypes.get(i).toString().equals(methodList.get(index).getParam().get(i).toString())){
    			 boolParam=false;    			 
    		 }
    	}
    	return boolParam;
    }
    private int  checkNameAndParamSize(List<Type> paramTypes, String name){
    	for (int i=0;i<methodList.size();i++){
    		if ((methodList.get(i).getName().equals(name)) && (methodList.get(i).getParam().size()==paramTypes.size()))
    			return i;
    	}
    	return -1;
    }
    public Type getType(TId idToken) {    	
    	return typeMap.get(idToken.getText());
    }

    public Type makeArrayType(Type t, Token tok) {
    	if (t.toString().equals("void")){
    		throw new TypecheckerException(tok,"Can't make an array of voidType");
    	}
    	return new ArrayType(t);
    }
	
}
