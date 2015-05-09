package minijava.Typechecker;

import minijava.node.*; 
import minijava.Type.*;
import java.util.*;

public class Typechecker {
    Start root;

    HashMap<String,Type> typeMap;
    HashMap<String,Var>  classVarMap;
    List <Method> methodList;
    SymbolTable st;   
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
    	System.out.println("Starting Phase 1 of Type checking. ");
		(new Phase1(this)).process(root);
    }

    public void createClassVar(String name, Type type, Token tok) {    	
    	if (classVarMap.get(name)==null){
    		classVarMap.put(name.trim(), new Var (name.trim(), type ));
    		System.out.println("Variable "+name+"of type "+type +" added to classVarMap");
    	}
    	else
    		throw new TypecheckerException(tok,"Variable with same name and type already exists");
    }

    public void createMethod(String name, Type returnType, 
			     List<Var> paramList, Token tok) {	
    	
    	int index=checkNameAndParamSize(paramList, name);    	
    	if (index!=-1){
    		if (checkParam(paramList,name,index)){
    			throw new TypecheckerException(tok,"Method with same name and parameter list already exists");	
    		}
    		
    	}    		
    	methodList.add(new Method(name.trim(), returnType, paramList));
    	System.out.println("Method "+name+"of return type "+returnType+" and  paramlist "+paramList +" added to methodList");
    	
    }
    
    private int  checkNameAndParamSize(List<Var> paramList, String name){
    	for (int i=0;i<methodList.size();i++){
    		if ((methodList.get(i).getName().equals(name)) && (methodList.get(i).getParam().size()==paramList.size()))
    			return i;
    	}
    	return -1;
    }  
    
    //Checks parameter types
    private boolean checkParam(List<Var> paramList, String name,int index){    	    	
    	boolean boolParam=true;
    	for (int i=0;i <paramList.size();i++){    		
    		
    		//check types
    			 if(!paramList.get(i).getType().toString().equals(methodList.get(index).getParam().get(i).getType().toString())){
    			 boolParam=false;    			 
    			 }
    		
    		// }
    	}
    	return boolParam;
    }
  
    public Type getType(TId idToken) {    //Takes in Type token and returns relevant type object   	
    	return typeMap.get(idToken.getText().trim());
    }
    
    public Type getTypeFromId(TId id){ 		//Takes ID token and returns the variable's type from symboltable    	    	    	
    	if (st.lookUp(id.getText().trim())){
    		return st.returnType(id.getText().trim());
    	}else{
    		throw new TypecheckerException(id,"Variable with this ID does not exist");
    	}
    }
    
    //returns type of method call if it exists  
    public Type methodReturnType(TId methodId){ 
    	for (int i=0;i<methodList.size();i++){
    		if (methodList.get(i).getName().trim().equals(methodId.toString().trim()))
    			return methodList.get(i).returnType();
    	}
    	throw new TypecheckerException(methodId,"The method does not exist");
    }

    public Type makeArrayType(Type t, Token tok) {
    	if (t.toString().equals("void")){
    		throw new TypecheckerException(tok,"Can't make an array of voidType");
    	}
    	return new ArrayType(t);
    }
    //returns pointer to a method given its name
    public Method returnMethod(String name){
    	name=name.trim();
    	for (int i=0;i<methodList.size();i++){
    		if ((methodList.get(i).getName().trim().equals(name)))
    			return methodList.get(i);
    	}     	
    	return null;
    }
	///////////////Phase2//////////////////
    public void phase2(){
  
    	System.out.println("--------------------------------");
    	System.out.println("Starting Phase 2 of Typechecking");
    	(new Phase2(this)).process(root);	
    }
    public void createSymbolTable(){
    	st=new SymbolTable(classVarMap); //passing the classVarMap to add to st with Scope 0
    	
    }
    public SymbolTable returnST(){
    	return st;
    }

    //returns true if the method call made is valid one
    //Similar to checkNameAndParamSize but takes in List of type instead of list of Vars
    public  boolean checkMethodHeaderAndArgs(String name, List<Type> type, TId d){    	
    	boolean match=false;
    	int counter=0; //Keep a counter so that ambiguos calls like in Bad17.java can be detected
    	
    	for (int i=0;i<methodList.size();i++){
    		//If the name and param size matches, call checkArg to see if the arguments match the params expected
    		if ((methodList.get(i).getName().trim().equals(name.trim())) && (methodList.get(i).getParam().size()==type.size())){    			
    			//If arguments match the params expected, immediately return true
    			if(checkArg(type, name, i)){
    				match= true;
    				counter++;
    			}
    		}
    	}
    	if (match && counter==1)
    		return true;
    	else if (match && counter>1)
    		throw new TypecheckerException(d,"Ambiguous method call. Could not determine which method to choose.");
    	else 
    		return false;    	
    }    
    
    private boolean checkArg(List<Type> typeList, String name,int index){    	    	
    	boolean boolParam=true;    	
    	for (int i=0;i <typeList.size();i++){    		
    		//if the passed argument is nullType, param expected does not matter    		
    		if (!typeList.get(i).isNull()){ 
	    			if(!typeList.get(i).canAssignTo(methodList.get(index).getParam().get(i).getType())){
	    			 boolParam=false;    			 
	    			 }
    			}
    	}    	
    	return boolParam;
    }
}
