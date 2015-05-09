package minijava.Typechecker;

import minijava.node.*; 
import minijava.Type.*;
import minijava.Machine.*;
import java.util.*;
import minijava.Temp.*;
import minijava.Arch.Simple.*;
import minijava.Translate.*; 
import minijava.Frame.*; 
import minijava.Tree.*;
public class Typechecker {
    Start root;

    HashMap<String,Type> typeMap;
    HashMap<String,Var>  classVarMap;
    HashMap<String,Label> labelMap; //9. 
    List <Method> methodList;
    SymbolTable st; 
    Machine machine;
    String fileBaseName;
    Builtins builtins;
    
    public Typechecker (Start s, String str,Machine m ) {
    root = s;
    machine=m;
    fileBaseName=str;
    builtins=new Builtins(machine);
	typeMap = new HashMap<String,Type>();
	typeMap.put ("int", Type.intType);
	typeMap.put ("String", Type.stringType);
	typeMap.put ("void", Type.voidType);
	typeMap.put ("boolean", Type.booleanType);
	classVarMap = new HashMap<String,Var>();
	methodList = new LinkedList<Method>();	
	labelMap=new HashMap<String,Label>();
    }
    public Machine getMachine(){
    	return machine;
    }
    public HashMap<String,Label> getLabelMap(){
    	return labelMap;
    }
    public void phase1() {    	
    	System.out.println("Starting Phase 1 of Type checking. ");
		(new Phase1(this)).process(root);
		//3. Creating label for each global variable
		for (Var v: classVarMap.values()){
			v.setLabel(machine.makeLabel("v_"+v.getName()));
		}
		//4. Doing same for the methods
		
		for (int i=0; i< methodList.size();i++){			
			Label label = machine.makeLabel("m_"+methodList.get(i).getName());
			methodList.get(i).setLabel(label);
			methodList.get(i).makeFrame(machine);	
			//allocating local space for the hidden var
			methodList.get(i).hiddenVar.setAccess(methodList.get(i).getFrame().allocLocal());
		}		
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
    	(new Phase2(this, builtins)).process(root);	
    }
    public void createSymbolTable(){
    	st=new SymbolTable(classVarMap); //passing the classVarMap to add to st with Scope 0
    	
    }
    public SymbolTable returnST(){
    	return st;
    }
    public void addEntry(String name, Var v,Token tok){
    	 if (!st.lookUp(name)){
    		 v.setAccess(st.activeMethod.getFrame().allocLocal());
    		 st.addEntry(name, v);		 
    		 // 6.  calling frame.allocLocal for the activemethod 
    		     		 
    	 }else{
    		 throw new TypecheckerException(tok,"Variable with same name and type already declared in the local context");
    	 }
    }
    public void incScope(boolean isNewMethod){    	
    	//Allocating local space for params when a method body is encountered
    	/*if (isNewMethod){
    		for (Var v: st.activeMethod.getParam()){	
    			v.setAccess(st.activeMethod.getFrame().allocLocal());
    		}
    	}*/
    	st.incScope(isNewMethod);    	
    }
    public void decScope(){
    	st.decScope();
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
    public Label checkLabel(String s){
    	if (labelMap.get(s.trim())==null){
    		Label l=new Label(s);
    		labelMap.put(s,l);
    		return l;
    	}else 
    		return labelMap.get(s.trim());
    }
    //14.
    Method findMainMethod() {
        for (Method m : methodList) {
        	System.out.println("-"+m.getName()+"-");
        	System.out.println("-"+m.getParam().get(0)+"-");
        	System.out.println(m.getParam().get(0).getType().equals(new ArrayType(Type.stringType)));
        	
        	System.out.println(m.getParam().get(0).getType());
        	System.out.println(new ArrayType(Type.stringType));
            if (m.getName().equals("main") &&
                m.getParam().size()==1 &&
            	m.getParam().get(0).getType().equals(new ArrayType(Type.stringType)))                
                return m;
        }
        throw new RuntimeException ("no main method!");
    }
    //15.
    public String createICode() {
        StringBuffer sb = new StringBuffer();
        for (String s: labelMap.keySet())
             sb.append ("string " + labelMap.get(s) + " " + s + "\n");
         for (Var v : classVarMap.values())
             sb.append ("globalVar " + v.getLabel() + "\n");
         sb.append ("mainMethod " + findMainMethod().getLabel() + "\n\n");
         for (Method m : methodList)
             sb.append (m.createICode() + "\n");
         return sb.toString();
     }
    

}
