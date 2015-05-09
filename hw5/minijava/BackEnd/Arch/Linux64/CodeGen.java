package minijava.BackEnd.Arch.Linux64;

import minijava.BackEnd.Assem.*;

import minijava.Temp.Temp;
import minijava.Temp.TempList;
import minijava.Temp.Label;

import minijava.Tree.Stm;
import minijava.Tree.ESTM;
import minijava.Tree.MOVE;
import minijava.Tree.JUMP;
import minijava.Tree.CJUMP;
import minijava.Tree.LABEL;
import minijava.Tree.ExpList;
import minijava.Tree.Exp;
import minijava.Tree.TEMP;
import minijava.Tree.MEM;
import minijava.Tree.NAME;
import minijava.Tree.BINOP;
import minijava.Tree.CONST;
import minijava.Tree.CALL;
import java.util.*;

/** An instruction selector for the Pentium architecture.
 */
class CodeGen {

    private PFrame frame;

    private InstrList ilist;

    private void emit (Instr inst) {
	if (ilist == null) 
	    ilist = new InstrList();
	ilist.add(inst);
    }

    /**
     * Class constructor
     * @param f the PFrame for the method for which code will be generated.
     */
    CodeGen (PFrame f) {frame = f;}
    
    private static String tab = "\t";
    

    /**
     * Returns an instruction list for a given intermediate tree.
     * @param s an Stm
     * @return an instruction list
     */

    InstrList codegen (Stm s) {
	
	munchStm (s);
	return ilist;
    }

    private void munchStm (Stm s) {
	if (s == null) return;
	else if (s instanceof ESTM) munchStm ((ESTM) s);
	else if (s instanceof JUMP) munchStm ((JUMP) s);
	else if (s instanceof CJUMP) munchStm ((CJUMP) s);
	else if (s instanceof LABEL) munchStm ((LABEL) s);
	else if (s instanceof MOVE) munchStm ((MOVE) s);
	else throw new RuntimeException ("unknown Stm");
    }
    
    private void munchStm (ESTM s) {  	
	munchExp (s.exp);
    }

    private void munchStm (MOVE s) {
    	
    	Temp t=new Temp();
    	/*
    	if (s.dst instanceof TEMP){
    		emit (new OPERInstr (tab + "movq " + munchExp(s.dst) + ",`d0\n", new TempList(t), null));
    	}
    	else {// it must be an instance of MEM. We don't want to dereference that using () 
    		MEM m=(MEM)s.dst;
    		emit (new OPERInstr (tab + "movq " + munchExp(m.exp) + ",`d0\n", new TempList(t), null));
    	}
    	*/
    	Temp src=munchExp(s.src);
    	Temp dst=munchExp(s.dst);
    	
    	System.out.println("MOVE "+dst+" "+src);
    	
    	emit (new MOVEInstr (tab + "movq `s0,`d0 \n", t, src));
    	emit (new MOVEInstr (tab + "movq `s0,`d0 \n", dst, t));
    	
    	
    }

    private void munchStm (LABEL s) {
    	System.out.println("LABEL");
	emit (new LABELInstr (s.label.toString() + ":\n", s.label));
	
    }
      
    private void munchStm (JUMP s) {
    	System.out.println("JUMP");
	emit (new OPERInstr (tab + "jmp " + s.target + "\n", null, null, s.target));
    }

    private void munchStm (CJUMP s) {
    	System.out.println("CJUMP");
    	
    	Temp leftTemp=munchExp(s.left);
    	Temp rightTemp=munchExp(s.right);

    	
    	emit (new OPERInstr (tab + "cmpq `s1, `s0 \n", null, new TempList(leftTemp,rightTemp), s.iftrue, s.iffalse));
    	
    	if (s.relop==0){
    		emit(new OPERInstr (tab + "je " + s.iftrue + "\n", null, null));    		
    	}
    	else if(s.relop==1){
    		emit(new OPERInstr (tab + "jne " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==2){
    		emit(new OPERInstr (tab + "jl " + s.iftrue + "\n", null, null ));
    	}
    	else if(s.relop==3){
    		emit(new OPERInstr (tab + "jg " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==4){
    		emit(new OPERInstr (tab + "jle " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==5){
    		emit(new OPERInstr (tab + "jge " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==6){
    		emit(new OPERInstr (tab + "je " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==7){
    		emit(new OPERInstr (tab + "je " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==8){
    		emit(new OPERInstr (tab + "je " + s.iftrue + "\n", null, null));
    	}
    	else if(s.relop==9){
    		emit(new OPERInstr (tab + "je " + s.iftrue + "\n", null, null));
    	}
    	//public final static int EQ=0, NE=1, LT=2, GT=3, LE=4, GE=5,
    	//ULT=6, ULE=7, UGT=8, UGE=9;
    }

    private Temp munchExp (Exp e) {
	if (e instanceof CONST) return munchExp((CONST) e);
	else if (e instanceof NAME) return munchExp((NAME) e);
	else if (e instanceof TEMP) return munchExp((TEMP) e);
	else if (e instanceof BINOP) return munchExp((BINOP) e);
	else if (e instanceof MEM) return munchExp((MEM) e);
	else if (e instanceof CALL) return munchExp((CALL) e);
	else throw new RuntimeException ("unknown Exp");
    }

    private Temp munchExp (CONST e) {
    	System.out.println("CONST");
	Temp t = new Temp();	
	
	Temp z= new Temp();
	//move the const to a temp z
	emit (new OPERInstr (tab + "movq $" + e.value + ",`d0 \n", 
			new TempList(z), null)); 	
	emit (new MOVEInstr (tab + "movq `s0,`d0 \n", t, z));
	return t;
    }

    private Temp munchExp (TEMP e) {
    	System.out.println("TEMP");
	return e.temp;
    }

    private Temp munchExp (NAME e) {      // the expression is a pointer
    	System.out.println("NAME");
    Temp t = new Temp();	
	emit (new OPERInstr (tab + "leaq " + e.label + "(%rip),`d0\n", 
			new TempList(t), null));
	return t;
    }

    private Temp munchExp (MEM e) {
    	System.out.println("MEM");
	Temp t = new Temp();
	
	Temp sr=munchExp(e.exp);
	
	
	//move the (sr) to a temp z
	emit (new MOVEInstr (tab + "movq (`s0),`d0 \n", 
			t, sr)); 
	
	/*emit (new MOVEInstr (tab + "movq `s0,`d0 \n", 
			t, z));*/
	// stuff needed here

	return t;
    }

    private Temp munchExp (BINOP e) {
    	System.out.println("BINOP");
	Temp t = new Temp();
	
	/* public final static int PLUS=0, MINUS=1, MUL=2, DIV=3, 
	AND=4,OR=5,LSHIFT=6,RSHIFT=7,ARSHIFT=8,XOR=9, MOD=10;*/
	Temp leftTemp=munchExp(e.left);
	Temp rightTemp=munchExp(e.right);
	System.out.println("leftTemp "+leftTemp+ " rightTemp "+rightTemp);
	if (e.binop==0){
		emit (new OPERInstr (tab + "addq `s1,`s0 \n", 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));		
	}else if (e.binop==1){
		emit (new OPERInstr (tab + "subq `s1,`s0 \n", 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==2){
		emit (new OPERInstr (tab + "imulq `s1,`s0 \n", 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==3){		
		Temp rax= new Temp();
		//move the %rax to a temp z
		emit (new OPERInstr (tab + "movq %rax,`d0 \n", 
				new TempList(rax), null)); 		
		emit (new MOVEInstr (tab + "movq `s0,`d0 \n", 
				rax, leftTemp));
		
		Temp rdx= new Temp();
		//move the %rax to a temp rax
		emit (new OPERInstr (tab + "movq %rax,`d0 \n", 
				new TempList(rdx), null));
		
		Temp zero= new Temp();
		//move the 0 to a temp zero
		emit (new OPERInstr (tab + "movq $0,`d0 \n", 
				new TempList(zero), null));
		
		emit (new MOVEInstr (tab + "movq `s0,`d0  \n", 
				rdx, zero));
		emit (new OPERInstr (tab + "idivq `s0 \n", 
				null, new TempList(rightTemp)));
		
		
		
		emit (new OPERInstr (tab + "movq %rax,`d0 \n", 
				new TempList(rax), null));
		emit (new MOVEInstr (tab + "movq `s0,`d0  \n", 
				leftTemp, rax));													
	}else if (e.binop==4){
		emit (new OPERInstr (tab + "andq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==5){
		emit (new OPERInstr (tab + "orq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==6){
		emit (new OPERInstr (tab + "shlq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==7){
		emit (new OPERInstr (tab + "shrq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==8){
		emit (new OPERInstr (tab + "sarq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==9){
		emit (new OPERInstr (tab + "addq `s1, `s0 \n" , 
				new TempList(leftTemp), new TempList(leftTemp, rightTemp)));
	}else if (e.binop==10){		
		Temp rax= new Temp();
		//move the %rax to a temp z
		emit (new OPERInstr (tab + "movq %rax,`d0 \n", 
				new TempList(rax), null)); 		
		emit (new MOVEInstr (tab + "movq `s0,`d0 \n", 
				rax, leftTemp));
		
		Temp rdx= new Temp();
		//move the %rax to a temp rax
		emit (new OPERInstr (tab + "movq %rax,`d0 \n", 
				new TempList(rdx), null));
		
		Temp zero= new Temp();
		//move the 0 to a temp zero
		emit (new OPERInstr (tab + "movq $0,`d0 \n", 
				new TempList(zero), null));
		
		emit (new MOVEInstr (tab + "movq `s0,`d0  \n", 
				rdx, zero));
		emit (new OPERInstr (tab + "idivq `s0 \n", 
				null, new TempList(rightTemp)));		
		
		emit (new OPERInstr (tab + "movq %rdx,`d0 \n", 
				new TempList(rdx), null)); //Updating the result to rdx				
		
		emit (new MOVEInstr (tab + "movq `s0,`d0  \n", 
				leftTemp, rdx));	
	}
	
	if (e.binop!=3 || e.binop!=10){
		emit (new MOVEInstr (tab + "movq `s0,`d0 \n", 
				t ,rightTemp));//rightTemp stores result for all the operations
	}
	
	return t;
    
    }

    private Temp munchExp (CALL e) {   
    	System.out.println("CALL");
    	//just assume parameter passing by registers
    	
    	Iterator <Exp> itr=e.args.iterator();    	    
    	LinkedList<Temp> list=new LinkedList<Temp>();
    	
    	while(itr.hasNext()){
    		list.add(munchExp(itr.next())); //list of temps corresponding to the arguments CALL.e has 
    	}
    	
    	TempList callersaves=frame.callersaves; //Getting list of frame registers 
    	
    	for(int i=0;i<list.size();i++) {
    		String d=frame.tempMap(callersaves.get(i)); //converts Temp object to string we can use in the assembly     	    		    	
    		emit (new OPERInstr (tab + "movq `s0,"+d+" \n", 
    				null, new TempList(list.get(i))));    
    	}
    	NAME name=(NAME)e.func;
    	emit (new OPERInstr (tab + "call "+name.label+ " \n", 
				frame.callersaves, null));
    	    	
	return frame.RV();
    }
}
