/* This file was generated by the Amherst version of SableCC */

package minijava.Typechecker;

import minijava.node.*;
import java.util.*;
import minijava.Type.*;
import minijava.Translate.*; 
import minijava.Tree.*;
import minijava.Temp.*;
import minijava.Machine.*;
import minijava.Arch.Simple.*;

public class Phase2
{
	private Typechecker typechecker;
	private SymbolTable st;
	Builtins builtins;
	
	public Phase2(Typechecker t, Builtins b){	
		typechecker=t;
		typechecker.createSymbolTable();
		st=typechecker.returnST(); //storing locally to be able to reference it frequently	
		builtins=b;
	}
	

    void process(Node n) {
    	 throw new RuntimeException(this.getClass() + ": no process method available for " 
	     + n.getClass());
    }

    ///////////////////////////////////////////////////////////////
    void process(Start n) {
         process(n.getPProgram());                   
    }

    ///////////////////////////////////////////////////////////////
    void process(PProgram n) {
        if (n instanceof AProgram) process((AProgram)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PProgram)");

        
    }

    ///////////////////////////////////////////////////////////////
    void process(AProgram n) {
        n.getPublic();				// yields TPublic
        n.getClasstok();				// yields TClasstok
        n.getId();				// yields TId
        n.getLbrace();				// yields TLbrace
	for (PMaindecl p : n.getMaindecl())
	    process(p);				// process(PMaindecl)
        n.getRbrace();				// yields TRbrace

        
    }

    ///////////////////////////////////////////////////////////////
    void process(PMaindecl n) {
        if (n instanceof AVarMaindecl) process((AVarMaindecl)n);
	else if (n instanceof AMethodMaindecl) process((AMethodMaindecl)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PMaindecl)");

        
    }

    ///////////////////////////////////////////////////////////////
    void process(AVarMaindecl n) {
        process(n.getPrivacy());			// process(PPrivacy)
        n.getStatic();				// yields TStatic
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
        n.getSemi();				// yields TSemi

        
    }

    ///////////////////////////////////////////////////////////////
    void process(AMethodMaindecl n) {
        //process(n.getPrivacy());			// process(PPrivacy)
        //n.getStatic();				// yields TStatic
        //process(n.getType());			// process(PType)
        //n.getId();				// yields TId
        //n.getLparen();				// yields TLparen
        //process(n.getParamlist());			// process(PParamlist)
        //n.getRparen();				// yields TRparen
        //n.getLbrace();				// yields TLbrace
    	
    	st.activeMethod=typechecker.returnMethod(n.getId().toString());
    	typechecker.incScope(true);
    	    	
    	
    	LinkedList<PStmt> stmList=n.getStmt();
    	Stm body;
    	if (stmList.size()==0){
    		body=  new ESTM(new CONST(0));	
    	}else {
    		body=process(stmList.get(0));
    	}
    	for (int i=1;i<stmList.size();i++){    		
    			body=new SEQ(body,process(stmList.get(i)));				// process(PStmt)
    	}
		typechecker.decScope();
		//Checking whether the returned type matches the return type declared in the method. 	
		Stm s1=new LABEL(st.activeMethod.getExitLabel());
		body=new SEQ(body,s1);
		if (!st.activeMethod.returnType().isVoid()){
			Exp ptr = getPtr(st.activeMethod.hiddenVar);
			Stm s2=new MOVE (new TEMP(st.activeMethod.getFrame().RV()),ptr);
			body=new SEQ(body,s2);
		}
		st.activeMethod.setBody(body);
		st.activeMethod=null;			// Since we are now outside the method		
    	//n.getRbrace();				// yields TRbrace
    	        
    }

    ///////////////////////////////////////////////////////////////
    void process(PParamlist n) {
        if (n instanceof AListParamlist) process((AListParamlist)n);
	else if (n instanceof AEmptyParamlist) process((AEmptyParamlist)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PParamlist)");

        
    }

    ///////////////////////////////////////////////////////////////
    void process(AListParamlist n) {
        process(n.getType());			// process(PType)
        n.getId();				// yields TId
	for (PParam p : n.getParam())
	    process(p);				// process(PParam)
	throw new UnsupportedOperationException ();     // remove when method is complete
        
    }

    ///////////////////////////////////////////////////////////////
    void process(AEmptyParamlist n) {
    	throw new UnsupportedOperationException ();     // remove when method is complete
        
    }

    ///////////////////////////////////////////////////////////////
    void process(PParam n) {
        if (n instanceof AParam) process((AParam)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PParam)");

        
    }

    ///////////////////////////////////////////////////////////////
    void process(AParam n) {
        n.getComma();				// yields TComma
        process(n.getType());			// process(PType)
        n.getId();				// yields TId

        
    }

    ///////////////////////////////////////////////////////////////
    void process(PPrivacy n) {
        if (n instanceof APublicPrivacy) process((APublicPrivacy)n);
	else if (n instanceof ABlankPrivacy) process((ABlankPrivacy)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrivacy)");

      //  
    }

    ///////////////////////////////////////////////////////////////
    void process(APublicPrivacy n) {
        n.getPublic();				// yields TPublic

     //   
    }

    ///////////////////////////////////////////////////////////////
    void process(ABlankPrivacy n) {

     //   
    }

    ///////////////////////////////////////////////////////////////
    Type process(PType n) {
        if (n instanceof AType) return process((AType)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PType)");  
    }

    ///////////////////////////////////////////////////////////////
    Type process(AType n) {
    	Type returnType=typechecker.getType(n.getId());
    	if (n.getEmptydim().size()==0){
    		if (returnType!=null)
    			return returnType;
    		else 
    			throw new TypecheckerException(n.getId(),"Specified type not allowed");
    	}
    	
        for (PEmptydim p : n.getEmptydim()){
        	returnType=typechecker.makeArrayType(returnType, n.getId()); 
        }
       
        return returnType;
    }

    ///////////////////////////////////////////////////////////////
    Stm process(PStmt n) {
        if (n instanceof AWhileStmt) return process((AWhileStmt)n);
	else if (n instanceof ADeclStmt) return process((ADeclStmt)n);   
	else if (n instanceof ABlockStmt) return process((ABlockStmt)n);
	else if (n instanceof AIfStmt) return process((AIfStmt)n);
	else if (n instanceof AExprStmt) return process((AExprStmt)n);
	else if (n instanceof AReturnStmt) return process((AReturnStmt)n);
	else if (n instanceof APrintStmt) return process((APrintStmt)n);
	else if (n instanceof AEmptyStmt) return process((AEmptyStmt)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PStmt)");

        
    }

    ///////////////////////////////////////////////////////////////
    Stm process(AWhileStmt n) {
        // n.getWhile();				// yields TWhile
        //n.getLparen();				// yields TLparen
    	ExprType e=process(n.getExpr());
    	if (!e.getType().isBoolean()){		// process(PExpr)
    		throw new TypecheckerException(n.getLparen(),"Condition in a while statement needs to be of boolean type.");
    	}	
        //n.getRparen();				// yields TRparen
        typechecker.incScope(false);
        Stm body=process(n.getStmt());			// process(PStmt)
        typechecker.decScope();
        
        Label l1=new Label();
        Label l2=new Label();
        Label l3=new Label();
               
        Stm s1=new LABEL(l1);
        Stm s2=new CJUMP(CJUMP.NE, e.expr.unEx(), new CONST(0), l2, l3);
        Stm s3=new LABEL(l2);
        Stm s4=body;
        Stm s5=new JUMP(l1);
        Stm s6=new LABEL(l3);
        
        return seq(s1,s2,s3,s4,s5,s6);             
    }

    ///////////////////////////////////////////////////////////////
    Stm process(ADeclStmt n) {    
    	Type type=process(n.getType());			// process(PType)
        n.getId();				// yields TId
        //System.out.println("variable : "+n.getId().getText()+" "+type);
        typechecker.addEntry(n.getId().getText().trim(),new Var(n.getId().getText().trim(), type),n.getId());         
        n.getSemi();				// yields TSemi           
        return new ESTM(new CONST(0)); 
    }

    ///////////////////////////////////////////////////////////////
    Stm process(ABlockStmt n) {
        n.getLbrace();				// yields TLbrace
        typechecker.incScope(false);
        StmList list=new StmList();        
		for (PStmt p : n.getStmt())
		    list.add(process(p));				// process(PStmt)
        n.getRbrace();				// yields TRbrace
        typechecker.decScope();
        
        return seq(list.toArray(new Stm[list.size()])); 
        
    }

    ///////////////////////////////////////////////////////////////
    Stm process(AIfStmt n) {
        //n.getIf();				// yields TIf
        //n.getLparen();				// yields TLparen
        ExprType e= process(n.getExpr());			// process(PExpr)
        //n.getRparen();				// yields TRparen
        
        if (!process(n.getExpr()).getType().isBoolean()){		// process(PExpr)
    		throw new TypecheckerException(n.getLparen(),"Condition in an if statement needs to be of boolean type.");
    	}	
        
        typechecker.incScope(false);
        Stm thenClause= process(n.getThenclause());			// process(PStmt)        
        typechecker.decScope();
       
        typechecker.incScope(false);
        Stm elseClause= process(n.getElseclause());			// process(PStmt)
        typechecker.decScope();
        
        Label l1=new Label();
        Label l2=new Label();
        Label l3=new Label();
        
        Stm s1=new CJUMP(CJUMP.EQ, e.expr.unEx(), new CONST(1), l1, l2);
        Stm s2=new LABEL(l1);
        Stm s3=thenClause;        
        Stm s4=new JUMP(l3);
        Stm s5=new LABEL(l2);
        Stm s6=elseClause;        
        Stm s7=new LABEL(l3);
        
        return seq(s1,s2,s3,s4,s5,s6,s7);
    }

    ///////////////////////////////////////////////////////////////
    Stm process(AExprStmt n) {
        ExprType e=process(n.getExpr());			// process(PExpr)        
        return new ESTM(e.expr.unEx());
    }

    ///////////////////////////////////////////////////////////////
    Stm process(AReturnStmt n) {
        n.getReturn();				// yields TReturn
        
        if (st.activeMethod==null){
        	throw new TypecheckerException(n.getReturn(),"Return statement has to be inside a method.");        	
        }

        ExprType returnExpr=new ExprType(null,Type.voidType);
        
        Stm s;
        Exp ptr= getPtr(st.activeMethod.hiddenVar);
        if (n.getExpr() != null){
            returnExpr=process(n.getExpr());		// process(PExpr)
            s= new MOVE(ptr, returnExpr.expr.unEx());
        }
        else 
        	s= new MOVE(ptr, new CONST(0));
        Stm s2=new JUMP(st.activeMethod.getExitLabel());
        s=new SEQ(s,s2);
        
        System.out.println(returnExpr.getType()+" "+st.activeMethod.returnType());
        Type returnedType=returnExpr.getType();//Actual returned type
        Type expReturnType=st.activeMethod.returnType();//Expected return type
        
        if ((!returnedType.canAssignTo(expReturnType) && !expReturnType.isVoid()) 
        	|| (expReturnType.isVoid() && !returnedType.isVoid()) ){        	
        	throw new TypecheckerException(n.getReturn(),"Returned type does not match the expected return type in method header.");
        }
        
        return s;
    }

    ///////////////////////////////////////////////////////////////
    Stm process(APrintStmt n) {
        n.getPrint();				// yields TPrint
        n.getLparen();				// yields TLparen
        ExprType e=process(n.getExpr());			// process(PExpr)
        n.getRparen();				// yields TRparen
        n.getSemi();				// yields TSemi        
        if (e.getType().isVoid()){
        	throw new TypecheckerException(n.getLparen(),"Void type can't be printed.");
        }else if(e.getType().isInt()){
        	Exp intE=new CALL(new NAME(builtins.intToString), new ExpList (e.expr.unEx()));
        	return new ESTM(new CALL(new NAME(builtins.printString), new ExpList (intE)));
        }
        
        return new ESTM(new CALL(new NAME(builtins.printString), new ExpList (e.expr.unEx())));
    }

    ///////////////////////////////////////////////////////////////
    Stm process(AEmptyStmt n) {
        n.getSemi();				// yields TSemi
        return new ESTM(new CONST(0)); //basically a noop
    }
    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr n) {
        if (n instanceof AAssignExpr) return process((AAssignExpr)n);
	else if (n instanceof AExprExpr) return process((AExprExpr)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr)");    
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AAssignExpr n) {    
    	
    	//process(n.getLhs()); // process(TLhs)
    	//Looking up the localVarMap in Typechecker to see the type of lhs
    	//System.out.println(" AAssignExpr "+n+" -"+n.getLhs()+"-");
    	//System.out.println("lookup lhs"+st.lookUp(n.getLhs().toString().trim()));
    	//n.getAssign();				// yields TAssign
    	ExprType lhsExprType=process(n.getLhs());    
    	Type lhsType=lhsExprType.getType();
    	
    	ExprType rhsExprType=process(n.getExpr());
        Type rhsType=rhsExprType.getType(); //Getting type from ExprType
   
    	if (!rhsType.canAssignTo(lhsType)){
        	throw new TypecheckerException(n.getAssign(),"Cannot assign "+rhsType.toString()+" to "+ lhsType.toString());
        }	
    	
    	Temp t1=new Temp();
        Exp e0=new TEMP(t1);
        System.out.println("Creating TEMP "+e0);//t10
        Stm s0=new MOVE(e0, rhsExprType.expr.unEx());
        Exp e4=new TEMP(t1);
        Stm s1=new MOVE(lhsExprType.expr.unEx(), e4);
        Stm s2= seq(s0,s1);
        Exp e3=new TEMP(t1);
        Exp e1=new ESEQ(s2,e3);                
        return new ExprType(new Ex(e1),rhsType);      
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr n) {    	
    	return process(n.getExpr10());			// process(PExpr10)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr10 n) {
        if (n instanceof AOrExpr10) return process((AOrExpr10)n);
	else if (n instanceof AExprExpr10) return process((AExprExpr10)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr10)");

        //
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AOrExpr10 n) {
    	ExprType lhs= process(n.getLeft());			// process(PExpr10)
        //n.getOr();				// yields TOr
    	ExprType rhs= process(n.getRight());			// process(PExpr20)
        
        return new ExprType(new CxOr(lhs.expr,rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr10 n) {
        return process(n.getExpr20());			// process(PExpr20)
        //
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr20 n) {
        if (n instanceof AAndExpr20) return process((AAndExpr20)n);
	else if (n instanceof AExprExpr20) return process((AExprExpr20)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr20)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AAndExpr20 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr20)
        //n.getAnd();				// yields TAnd
    	ExprType rhs=process(n.getRight());			// process(PExpr30)
        return new ExprType(new CxAnd(lhs.expr,rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr20 n) {
        return process(n.getExpr30());			// process(PExpr30)

        //
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr30 n) {
        if (n instanceof AEqExpr30) return process((AEqExpr30)n);
	else if (n instanceof ANeExpr30) return process((ANeExpr30)n);
	else if (n instanceof AExprExpr30) return process((AExprExpr30)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr30)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AEqExpr30 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr30)
        n.getEq();				// yields TEq
        ExprType rhs=process(n.getRight());			// process(PExpr40)        
        return new ExprType(new CxRel(n.getEq(), lhs.expr, rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANeExpr30 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr30)
    	ExprType rhs=process(n.getRight());			// process(PExpr40)
        return new ExprType(new CxRel(n.getNe(), lhs.expr, rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr30 n) {
        return process(n.getExpr40());			// process(PExpr40)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr40 n) {
        if (n instanceof ALtExpr40) return process((ALtExpr40)n);
	else if (n instanceof ALeExpr40) return process((ALeExpr40)n);
	else if (n instanceof AGeExpr40) return process((AGeExpr40)n);
	else if (n instanceof AGtExpr40) return process((AGtExpr40)n);
	else if (n instanceof AExprExpr40) return process((AExprExpr40)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr40)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALtExpr40 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr40)
    	ExprType rhs=process(n.getRight());			// process(PExpr50)
        return new ExprType(new CxRel(n.getLt(), lhs.expr, rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALeExpr40 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr40)       
        ExprType rhs=process(n.getRight());			// process(PExpr50)
        return new ExprType(new CxRel(n.getLe(), lhs.expr, rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AGeExpr40 n) {
        ExprType lhs=process(n.getLeft());			// process(PExpr40)
        ExprType rhs=process(n.getRight());			// process(PExpr50)         
        return new ExprType(new CxRel(n.getGe(), lhs.expr, rhs.expr),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AGtExpr40 n) {
    	ExprType lhs=process(n.getLeft());			// process(PExpr40)
        ExprType rhs=process(n.getRight());			// process(PExpr50)
        return new ExprType(new Ex(new BINOP(0, lhs.expr.unEx(),rhs.expr.unEx())),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AExprExpr40 n) {
        return process(n.getExpr50());			// process(PExpr50)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PExpr50 n) {
        if (n instanceof APlusExpr50) return process((APlusExpr50)n);
	else if (n instanceof AMinusExpr50) return process((AMinusExpr50)n);
	else if (n instanceof ATermExpr50) return process((ATermExpr50)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PExpr50)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APlusExpr50 n) {
        ExprType lhs=process(n.getLeft());			// process(PExpr50)
        ExprType rhs=process(n.getRight());			// process(PTerm)\
        
        System.out.println(lhs.getType()+" "+rhs.getType());
        System.out.println(lhs.getType().isInt()+" "+rhs.getType().isInt());
        if (!((lhs.getType().isInt() || lhs.getType().isString()) && (rhs.getType().isInt() || rhs.getType().isString()))){
        	throw new TypecheckerException(n.getPlus(),"The terms should be either an int or a string");
        }
      //If one of the terms is a string this is a concatenation operation and should return a stringType instead of intType
      
        if (lhs.getType().isString() && rhs.getType().isInt()){
        	Exp rhsString=new CALL(new NAME(builtins.intToString), new ExpList (rhs.expr.unEx()));
        	Exp e=new CALL(new NAME(builtins.stringConcatenate), new ExpList (lhs.expr.unEx(), rhsString));
        	return new ExprType(new Ex(e),Type.stringType);
        }else if (rhs.getType().isString() && lhs.getType().isInt()){
        	Exp lhsString=new CALL(new NAME(builtins.intToString), new ExpList (lhs.expr.unEx()));
        	Exp e=new CALL(new NAME(builtins.stringConcatenate), new ExpList (lhsString, rhs.expr.unEx()));
        	return new ExprType(new Ex(e),Type.stringType);
        }else if (rhs.getType().isString() && lhs.getType().isString()){
        	Exp e=new CALL(new NAME(builtins.stringConcatenate), new ExpList (lhs.expr.unEx(), rhs.expr.unEx()));
        	return new ExprType(new Ex(e),Type.stringType);
        }
        
        return new ExprType(new Ex(new BINOP(0, lhs.expr.unEx(),rhs.expr.unEx())),Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AMinusExpr50 n) {
        ExprType lhs=process(n.getLeft());			// process(PExpr50)
        ExprType rhs=process(n.getRight());			// process(PTerm)
        return new ExprType(new Ex(new BINOP(1, lhs.expr.unEx(),rhs.expr.unEx())),Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATermExpr50 n) {    	
        return process(n.getTerm());			// process(PTerm)
   }

    ///////////////////////////////////////////////////////////////
    ExprType process(PTerm n) {
    	//System.out.println("PTerm");
        if (n instanceof ATimesTerm) return process((ATimesTerm)n);
	else if (n instanceof ADivTerm) return process((ADivTerm)n);
	else if (n instanceof AModTerm) return process((AModTerm)n);
	else if (n instanceof AFactorTerm) return process((AFactorTerm)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PTerm)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATimesTerm n) {
    	//System.out.println("ATimesTerm "+n);
        ExprType lhs=process(n.getLeft());			// process(PTerm)
        ExprType rhs=process(n.getRight());			// process(PFactor)
        if (!process(n.getLeft()).getType().isInt() || !process(n.getRight()).getType().isInt()){
        	throw new TypecheckerException(n.getTimes(),"Only integer multiplication allowed.");
        }
        return new ExprType(new Ex(new BINOP(2,lhs.expr.unEx(),rhs.expr.unEx())) ,Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ADivTerm n) {
        ExprType lhs=process(n.getLeft());			// process(PTerm)
        ExprType rhs=process(n.getRight());			// process(PFactor)
        return new ExprType(new Ex(new BINOP(3,lhs.expr.unEx(),rhs.expr.unEx())),Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AModTerm n) {
    	ExprType lhs=process(n.getLeft());			// process(PTerm)
        ExprType rhs=process(n.getRight());			// process(PFactor)
        return new ExprType(new Ex(new BINOP(10,lhs.expr.unEx(),rhs.expr.unEx())),Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AFactorTerm n) {
        return process(n.getFactor());			// process(PFactor)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PFactor n) {
    	//System.out.println("PFactor");
        if (n instanceof APrimaryFactor) return process((APrimaryFactor)n);
	else if (n instanceof AIdFactor) return process((AIdFactor)n);
	else if (n instanceof ALengthFactor) return process((ALengthFactor)n);
	else if (n instanceof ALength2Factor) return process((ALength2Factor)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PFactor)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimaryFactor n) {
        return process(n.getPrimary());			// process(PPrimary)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIdFactor n) {    	
        Type type= typechecker.getTypeFromId(n.getId());				       
        //System.out.println(n+" is of type "+ type);               
        Var v=st.getVar(n.getId().toString());

        Exp s=getPtr(v);
        
        return new ExprType(new Ex(s) ,type);     
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALengthFactor n) {
    	//System.out.println("ALengthFactor" +n);
        n.getId();				// yields TId
        n.getDot();				// yields TDot
        n.getLength();				// yields TLength
        
        if (!typechecker.getTypeFromId(n.getId()).isArray()){
        	throw new TypecheckerException(n.getId(),"The specified variable does not have length");
        }        
        Var v=st.getVar(n.getId().toString());
        
        Exp s=getPtr(v);
        
        Exp minusOne=new CONST(-1);
        Exp wordSize=new CONST(typechecker.getMachine().wordSize());        
        Exp e0=new BINOP(BINOP.MUL,minusOne,wordSize);
        Exp e1=new BINOP(BINOP.PLUS,s,e0);
        Exp e2=new MEM(e1);
        return new ExprType(new Ex(e2),Type.intType) ;
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ALength2Factor n) {
    	
    	n.getId();				// yields TId
        n.getDot();				// yields TDot
        n.getLength();				// yields TLength
        n.getLparen();				// yields TLparen
        n.getRparen();				// yields TRparen
        if (!typechecker.getTypeFromId(n.getId()).isString()){
        	throw new TypecheckerException(n.getId(),"The specified variable does not have length() function");
        }
        Var v=st.getVar(n.getId().toString());
        Exp s = getPtr(v);
                 
        return new ExprType(new Ex(new CALL(new NAME(builtins.stringLength),new ExpList(s))),Type.intType) ;
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PPrimary n) {
        if (n instanceof ANewarrayPrimary) return process((ANewarrayPrimary)n);
	else if (n instanceof APrimary2Primary) return process((APrimary2Primary)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrimary)");
        //
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANewarrayPrimary n) {
        //n.getNew();				// yields TNew
        Type type= new ArrayType(typechecker.getType(n.getId()));
        //n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)                
        //n.getRbrack();				// yields TRbrack
        ExprType ex=process(n.getExpr()); 
        
        if (!ex.getType().isInt()){
        	throw new TypecheckerException(n.getId(),"Array size should be an int type.");
        }
		for (PEmptydim p : n.getEmptydim()){
		    process(p);				// process(PEmptydim)
		    type=new ArrayType(type);
		}
		
		return new ExprType(new Ex(new CALL(new NAME(builtins.createArray),new ExpList(ex.expr.unEx()))),type);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimary2Primary n) {
        return process(n.getPrimary2());			// process(PPrimary2)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PPrimary2 n) {    	    	
        if (n instanceof AIconstPrimary2) return process((AIconstPrimary2)n);
	else if (n instanceof ASconstPrimary2) return process((ASconstPrimary2)n);
	else if (n instanceof ANullPrimary2) return process((ANullPrimary2)n);
	else if (n instanceof ATruePrimary2) return process((ATruePrimary2)n);
	else if (n instanceof AFalsePrimary2) return process((AFalsePrimary2)n);
	else if (n instanceof AParensPrimary2) return process((AParensPrimary2)n);
	else if (n instanceof ACallPrimary2) return process((ACallPrimary2)n);
	else if (n instanceof AArrayrefPrimary2) return process((AArrayrefPrimary2)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PPrimary2)");

        //
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIconstPrimary2 n) {    	    	
        n.getIconst();				// yields TIconst
        int i=Integer.parseInt(n.getIconst().toString().trim());
        return new ExprType(new Ex(new CONST(i)),Type.intType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ASconstPrimary2 n) {
        n.getSconst();				// yields TSconst
        HashMap<String,Label> labelMap=typechecker.getLabelMap();
        
        Label l;
        if (labelMap.get(n.getSconst().toString())==null){
        	l=new Label();
        	labelMap.put(n.getSconst().toString(),l);
        }else 
        	l=labelMap.get(n.getSconst().toString());
        return new ExprType(new Ex(new NAME(l)),Type.stringType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANullPrimary2 n) {
        n.getNull();				// yields TNull
        return new ExprType(new Ex(new CONST(0)),Type.nullType);      
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ATruePrimary2 n) {
        n.getTrue();				// yields TTrue
        return new ExprType(new Ex(new CONST(1)),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AFalsePrimary2 n) {
        n.getFalse();				// yields TFalse
        return new ExprType(new Ex(new CONST(0)),Type.booleanType);
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AParensPrimary2 n) {
        //n.getLparen();				// yields TLparen
        return process(n.getExpr());			// process(PExpr)
        //n.getRparen();				// yields TRparen
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ACallPrimary2 n) {
    	//System.out.println("ACallPrimary2"+n);
        //n.getId();				// yields TId
        //n.getLparen();				// yields TLparen
    	//System.out.println("getId() : "+n.getId());
    	List <ExprType> argListExpr=new ArrayList<ExprType>();
    	
        if (n.getArglist() != null){        	
        	//Processing the parameters in process(PArglist). Passing n.getId() to identify the method name being called
            argListExpr=process(n.getArglist());		// process(PArglist)            
        }
        //Creating a list of type to call typechecker.checkMethodHeaderAndArgs
        List<Type> argListType=new ArrayList<Type>();
        //Creating a list of Exp to pass to CALL
        ExpList argExpList=new ExpList();
        System.out.println("PARAMETER");
        
        for(ExprType e:argListExpr){        	
        	argExpList.addLast(e.expr.unEx());
        	argListType.add(e.getType());        	
        }        
        if (!typechecker.checkMethodHeaderAndArgs(n.getId().toString(),argListType, n.getId()))
			throw new TypecheckerException(n.getId(),"Method with this name and arguments does not exist");
        
        Label l=typechecker.returnMethod(n.getId().toString()).getLabel();
        Exp ex=new CALL(new NAME(l),argExpList);
        return new ExprType(new Ex(ex),typechecker.methodReturnType(n.getId()));        
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArrayrefPrimary2 n) {
        return process(n.getArrayref());			// process(PArrayref)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PArrayref n) {
        if (n instanceof ANameArrayref) return process((ANameArrayref)n);
	else if (n instanceof APrimaryArrayref) return process((APrimaryArrayref)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArrayref)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(ANameArrayref n) {
        //n.getId();				// yields TId
        //n.getLbrack();				// yields TLbrack    	              
        //System.out.println("ANameArrayref : "+typechecker.getTypeFromId(n.getId()));
        //n.getRbrack();				// yields TRbrack
    	
        if (!(st.lookUp(n.getId().getText().toString().trim()) && st.returnType(n.getId().getText().toString().trim()).isArray())){
        	throw new TypecheckerException(n.getId(),"Refereced array does not exist.");
        }
        Var v=st.getVar(n.getId().getText().toString().trim());
        
        
        
        ExprType exp=process(n.getExpr());
        if (!exp.getType().isInt()){
        	throw new TypecheckerException(n.getId(),"Array reference expression should be an int.");
        }
        Exp ptr=getPtr(v);
        
        Exp index=exp.expr.unEx();
        Exp wordSize=new CONST(typechecker.getMachine().wordSize());        
        Exp e0=new BINOP(BINOP.MUL,index,wordSize);
        Exp e1=new BINOP(BINOP.PLUS,ptr,e0);
        Exp e2=new MEM(e1);
        return new ExprType(new Ex(e2),exp.getType()) ;                        
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(APrimaryArrayref n) {     
        //n.getLbrack();				// yields TLbrack
        process(n.getExpr());			// process(PExpr)
        //n.getRbrack();				// yields TRbrack
        return process(n.getPrimary2());			// process(PPrimary2)
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PLhs n) {
        if (n instanceof AIdLhs) return process((AIdLhs)n);
	else if (n instanceof AArrayrefLhs) return process((AArrayrefLhs)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PLhs)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AIdLhs n) {

    	// n.getId();				// yields TId
    	
    	if (!st.lookUp(n.toString())){
    		 throw new TypecheckerException(n.getId(),"No variable with given ID");
    	}
    	Var v=st.getVar(n.toString());    	
    	Exp ptr=getPtr(v);        	
        return new ExprType(new Ex(ptr), st.returnType(n.getId().getText()));   
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArrayrefLhs n) {    	
    	return process(n.getArrayref());			// process(PArrayref)
    }

    ///////////////////////////////////////////////////////////////

    List<ExprType> process(PArglist n) { 
        if (n instanceof AListArglist)  return process((AListArglist)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArglist)");
    }
     
    List <ExprType> process(AListArglist n) {
    	//process(n.getExpr());			// process(PExpr)
    List <ExprType> t=new ArrayList <ExprType>();        
    t.add(process(n.getExpr()));
    
    for (PArg p : n.getArg()){
    	//    process(p);				// process(PArg)
	    t.add(process(p));	    
	}    
	return t;
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(PArg n) {
        if (n instanceof AArg)  return process((AArg)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PArg)");
    }

    ///////////////////////////////////////////////////////////////
    ExprType process(AArg n) {
        n.getComma();				// yields TComma
        return process(n.getExpr());			// process(PExpr)
        //returning type since that's the only thing that matters in argumentList
    }

    ///////////////////////////////////////////////////////////////
    void process(PEmptydim n) {
        if (n instanceof AEmptydim) process((AEmptydim)n);
	else 
            throw new RuntimeException (this.getClass() + 
                ": unexpected subclass " + n.getClass() + " in process(PEmptydim)");
    }

    ///////////////////////////////////////////////////////////////
    void process(AEmptydim n) {
        n.getLbrack();				// yields TLbrack
        n.getRbrack();				// yields TRbrack
    }
    
    Stm seq(Stm... list){
    	Stm result=null;
    	for (Stm s:list)
    		result=(result==null)?s: new SEQ(result,s);
    	return result;
    }    
    Exp getPtr(Var v){ //returns ptr to the var            	
    	Exp e;    	
    	if (v.getLabel()!=null){
        	e= new MEM(new NAME(v.getLabel()));
    		System.out.println("label : "+v.getLabel());
    	}
        else {        	
        	System.out.println(v.getAccess());
        	e= v.getAccess().exp(new TEMP(st.activeMethod.getFrame().FP()));
        }
    	return e;
    }
}


