package minijava.Typechecker;
import minijava.Type.*;
import minijava.node.*;
import java.util.*;

public class SymbolTable {

	HashMap <String,Var>  localVarMap;
	
	Stack <List> stackList; //stack of lists to keep track of scopes
	
	List <Var> currentList; // keeps track of the ongoing localvars until the list gets added to 	
  							 // stackList or the list gets discarded  		
	//Following two variables help guide how return statment in method should be used
	public Method activeMethod; //this holds pointer to the method in typechecker.methodList which is currently being processed.
								//Helpful in phase2 when checking what a method should return at the end. 
								//Making its value null when outside a Method.
	
	
	private int scope; //current Scope
	
	public SymbolTable(HashMap<String,Var> classVarMap){
		scope =0; //start with 0 scope
		stackList=new Stack <List> ();
		currentList=new ArrayList <Var>();
		localVarMap=new HashMap<String,Var>();
		
		///////Adding classvar to symboltable////////
		
		System.out.println("ClassVars being added to Symbol table with scope 0:");
		for (Var a:classVarMap.values()){
    		System.out.println(a);
    		localVarMap.put(a.getName(),a);
    		currentList.add(a);
    	}
		
	}
	
	public void addEntry(String name, Var v,Token tok){		
	 if (!lookUp(name)){
		 currentList.add(v);//adding to the list to keep track of scope
		 localVarMap.put(name,v); //adding to the hashmap as well
		 System.out.println("Adding variable "+name+" with scope "+scope);		 
	 }else{
		 throw new TypecheckerException(tok,"Variable with same name and type already declared in the local context");
	 }
	}
	
	public void  incScope(boolean isNewMethod){		
		scope++;
		System.out.println("Scope of Symboltable increased to "+scope);
		stackList.push(currentList);	
		currentList=new ArrayList<Var>(); // Letting Garbage Collector do it's work	
		if (isNewMethod){ //add the arguments passed to the symbol table when incScope is called due to a new method
			for (Var i: activeMethod.getParam()){			
				currentList.add(i);
				localVarMap.put(i.getName(),i);
				System.out.println("Passed Variable "+i+" added to Symbol table with scope "+scope);
			}
		}
	}
	
	public void decScope(){	
		//removing currentList vars from the localVarMap 
		for (Var v: currentList){
			localVarMap.remove(v.getName());
			System.out.println("Removing variable "+v +" with scope "+ scope+" from localVarMam");
		}	
		
		if (stackList.size()!=0){
			currentList=stackList.pop();	
		}else 
			currentList=new ArrayList <Var>();
			
		scope--;
		System.out.println("Scope of Symboltable decreased to "+scope);
	}
	
	public boolean lookUp(Var v){
		return lookUp(v.getName());
	}
	
	public boolean lookUp(String s){	
			
		if (localVarMap.get(s.trim())==null){			
			return false;
		}else {			
			return true;
		}
	}
	public boolean lookUp(String s, Type t){
		if(lookUp(s)){
			if(localVarMap.get(s).getType()==t)
				return true;
		}
		return false;
	}
	public Type returnType(String s){ //takes in a string and returns its type [assumes var with that name exists] 	
			return localVarMap.get(s).getType();		
	}
	
}
