package minijava.Typechecker;
import java.util.*;
import minijava.Type.*;
import minijava.Translate.*; 
import minijava.Temp.*;
import minijava.Frame.*;
import minijava.Machine.*;
import minijava.Tree.*;

public class Method {
	private String name;
	private Type type;
	private List<Var> paramList;
	private Label label;
	private Frame frame;
	
	public Var hiddenVar;//7.
	private Label exitLabel;//8. 
	private List<Access> parameterAccess;
	Stm body;
	
	public Method(String n, Type t, List<Var> p){
		name=n;
		type=t;
		paramList=p;		
		hiddenVar=new Var(null,null);
		exitLabel=new Label();
	}
	public String getName(){
		return name;
	}
	public List<Var> getParam(){
		return paramList;
	}
	public Type returnType(){
		return type;
	}
	public void setLabel(Label l){
		label=l;
	}
	public Label getLabel(){
		return label;
	}
	public Frame makeFrame(Machine machine){
		frame=machine.makeFrame(label);
		parameterAccess=frame.createParameterAccesses(paramList.size());
		for (int i=0;i<paramList.size();i++){
			paramList.get(i).setAccess(parameterAccess.get(i));
		}
		return frame;
	}
	public Frame getFrame(){
		return frame;
	}
	public void setBody(Stm b){
		body=b;
	}
	 public String createICode() {
         
		 Stm b = frame.procEntryExit1(body);                 
         return "method " + label + " "
             + b.idString() + " "
             + frame.getFrameInfo() + "\n"
             + new ICode(b, frame)
             + "endMethod\n\n";
	 }
	 public Label getExitLabel(){
		 return exitLabel;
	 }
}

	
