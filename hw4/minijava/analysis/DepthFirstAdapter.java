/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.analysis;

import java.util.*;
import minijava.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgram().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgram(AProgram node)
    {
        defaultIn(node);
    }

    public void outAProgram(AProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgram(AProgram node)
    {
        inAProgram(node);
        if(node.getPublic() != null)
        {
            node.getPublic().apply(this);
        }
        if(node.getClasstok() != null)
        {
            node.getClasstok().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLbrace() != null)
        {
            node.getLbrace().apply(this);
        }
        {
            List<PMaindecl> copy = new ArrayList<PMaindecl>(node.getMaindecl());
            for(PMaindecl e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getRbrace() != null)
        {
            node.getRbrace().apply(this);
        }
        outAProgram(node);
    }

    public void inAVarMaindecl(AVarMaindecl node)
    {
        defaultIn(node);
    }

    public void outAVarMaindecl(AVarMaindecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarMaindecl(AVarMaindecl node)
    {
        inAVarMaindecl(node);
        if(node.getPrivacy() != null)
        {
            node.getPrivacy().apply(this);
        }
        if(node.getStatic() != null)
        {
            node.getStatic().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outAVarMaindecl(node);
    }

    public void inAMethodMaindecl(AMethodMaindecl node)
    {
        defaultIn(node);
    }

    public void outAMethodMaindecl(AMethodMaindecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMethodMaindecl(AMethodMaindecl node)
    {
        inAMethodMaindecl(node);
        if(node.getPrivacy() != null)
        {
            node.getPrivacy().apply(this);
        }
        if(node.getStatic() != null)
        {
            node.getStatic().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getParamlist() != null)
        {
            node.getParamlist().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        if(node.getLbrace() != null)
        {
            node.getLbrace().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getRbrace() != null)
        {
            node.getRbrace().apply(this);
        }
        outAMethodMaindecl(node);
    }

    public void inAListParamlist(AListParamlist node)
    {
        defaultIn(node);
    }

    public void outAListParamlist(AListParamlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListParamlist(AListParamlist node)
    {
        inAListParamlist(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            for(PParam e : copy)
            {
                e.apply(this);
            }
        }
        outAListParamlist(node);
    }

    public void inAEmptyParamlist(AEmptyParamlist node)
    {
        defaultIn(node);
    }

    public void outAEmptyParamlist(AEmptyParamlist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyParamlist(AEmptyParamlist node)
    {
        inAEmptyParamlist(node);
        outAEmptyParamlist(node);
    }

    public void inAParam(AParam node)
    {
        defaultIn(node);
    }

    public void outAParam(AParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParam(AParam node)
    {
        inAParam(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAParam(node);
    }

    public void inAPublicPrivacy(APublicPrivacy node)
    {
        defaultIn(node);
    }

    public void outAPublicPrivacy(APublicPrivacy node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPublicPrivacy(APublicPrivacy node)
    {
        inAPublicPrivacy(node);
        if(node.getPublic() != null)
        {
            node.getPublic().apply(this);
        }
        outAPublicPrivacy(node);
    }

    public void inABlankPrivacy(ABlankPrivacy node)
    {
        defaultIn(node);
    }

    public void outABlankPrivacy(ABlankPrivacy node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlankPrivacy(ABlankPrivacy node)
    {
        inABlankPrivacy(node);
        outABlankPrivacy(node);
    }

    public void inAType(AType node)
    {
        defaultIn(node);
    }

    public void outAType(AType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAType(AType node)
    {
        inAType(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PEmptydim> copy = new ArrayList<PEmptydim>(node.getEmptydim());
            for(PEmptydim e : copy)
            {
                e.apply(this);
            }
        }
        outAType(node);
    }

    public void inAWhileStmt(AWhileStmt node)
    {
        defaultIn(node);
    }

    public void outAWhileStmt(AWhileStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileStmt(AWhileStmt node)
    {
        inAWhileStmt(node);
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        if(node.getStmt() != null)
        {
            node.getStmt().apply(this);
        }
        outAWhileStmt(node);
    }

    public void inADeclStmt(ADeclStmt node)
    {
        defaultIn(node);
    }

    public void outADeclStmt(ADeclStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclStmt(ADeclStmt node)
    {
        inADeclStmt(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outADeclStmt(node);
    }

    public void inABlockStmt(ABlockStmt node)
    {
        defaultIn(node);
    }

    public void outABlockStmt(ABlockStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlockStmt(ABlockStmt node)
    {
        inABlockStmt(node);
        if(node.getLbrace() != null)
        {
            node.getLbrace().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getRbrace() != null)
        {
            node.getRbrace().apply(this);
        }
        outABlockStmt(node);
    }

    public void inAIfStmt(AIfStmt node)
    {
        defaultIn(node);
    }

    public void outAIfStmt(AIfStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfStmt(AIfStmt node)
    {
        inAIfStmt(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        if(node.getThenclause() != null)
        {
            node.getThenclause().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getElseclause() != null)
        {
            node.getElseclause().apply(this);
        }
        outAIfStmt(node);
    }

    public void inAExprStmt(AExprStmt node)
    {
        defaultIn(node);
    }

    public void outAExprStmt(AExprStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprStmt(AExprStmt node)
    {
        inAExprStmt(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outAExprStmt(node);
    }

    public void inAReturnStmt(AReturnStmt node)
    {
        defaultIn(node);
    }

    public void outAReturnStmt(AReturnStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAReturnStmt(AReturnStmt node)
    {
        inAReturnStmt(node);
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outAReturnStmt(node);
    }

    public void inAPrintStmt(APrintStmt node)
    {
        defaultIn(node);
    }

    public void outAPrintStmt(APrintStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrintStmt(APrintStmt node)
    {
        inAPrintStmt(node);
        if(node.getPrint() != null)
        {
            node.getPrint().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outAPrintStmt(node);
    }

    public void inAEmptyStmt(AEmptyStmt node)
    {
        defaultIn(node);
    }

    public void outAEmptyStmt(AEmptyStmt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptyStmt(AEmptyStmt node)
    {
        inAEmptyStmt(node);
        if(node.getSemi() != null)
        {
            node.getSemi().apply(this);
        }
        outAEmptyStmt(node);
    }

    public void inAAssignExpr(AAssignExpr node)
    {
        defaultIn(node);
    }

    public void outAAssignExpr(AAssignExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignExpr(AAssignExpr node)
    {
        inAAssignExpr(node);
        if(node.getLhs() != null)
        {
            node.getLhs().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAAssignExpr(node);
    }

    public void inAExprExpr(AExprExpr node)
    {
        defaultIn(node);
    }

    public void outAExprExpr(AExprExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprExpr(AExprExpr node)
    {
        inAExprExpr(node);
        if(node.getExpr10() != null)
        {
            node.getExpr10().apply(this);
        }
        outAExprExpr(node);
    }

    public void inAOrExpr10(AOrExpr10 node)
    {
        defaultIn(node);
    }

    public void outAOrExpr10(AOrExpr10 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExpr10(AOrExpr10 node)
    {
        inAOrExpr10(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAOrExpr10(node);
    }

    public void inAExprExpr10(AExprExpr10 node)
    {
        defaultIn(node);
    }

    public void outAExprExpr10(AExprExpr10 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprExpr10(AExprExpr10 node)
    {
        inAExprExpr10(node);
        if(node.getExpr20() != null)
        {
            node.getExpr20().apply(this);
        }
        outAExprExpr10(node);
    }

    public void inAAndExpr20(AAndExpr20 node)
    {
        defaultIn(node);
    }

    public void outAAndExpr20(AAndExpr20 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExpr20(AAndExpr20 node)
    {
        inAAndExpr20(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAAndExpr20(node);
    }

    public void inAExprExpr20(AExprExpr20 node)
    {
        defaultIn(node);
    }

    public void outAExprExpr20(AExprExpr20 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprExpr20(AExprExpr20 node)
    {
        inAExprExpr20(node);
        if(node.getExpr30() != null)
        {
            node.getExpr30().apply(this);
        }
        outAExprExpr20(node);
    }

    public void inAEqExpr30(AEqExpr30 node)
    {
        defaultIn(node);
    }

    public void outAEqExpr30(AEqExpr30 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqExpr30(AEqExpr30 node)
    {
        inAEqExpr30(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqExpr30(node);
    }

    public void inANeExpr30(ANeExpr30 node)
    {
        defaultIn(node);
    }

    public void outANeExpr30(ANeExpr30 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANeExpr30(ANeExpr30 node)
    {
        inANeExpr30(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getNe() != null)
        {
            node.getNe().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outANeExpr30(node);
    }

    public void inAExprExpr30(AExprExpr30 node)
    {
        defaultIn(node);
    }

    public void outAExprExpr30(AExprExpr30 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprExpr30(AExprExpr30 node)
    {
        inAExprExpr30(node);
        if(node.getExpr40() != null)
        {
            node.getExpr40().apply(this);
        }
        outAExprExpr30(node);
    }

    public void inALtExpr40(ALtExpr40 node)
    {
        defaultIn(node);
    }

    public void outALtExpr40(ALtExpr40 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALtExpr40(ALtExpr40 node)
    {
        inALtExpr40(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getLt() != null)
        {
            node.getLt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALtExpr40(node);
    }

    public void inALeExpr40(ALeExpr40 node)
    {
        defaultIn(node);
    }

    public void outALeExpr40(ALeExpr40 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALeExpr40(ALeExpr40 node)
    {
        inALeExpr40(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALeExpr40(node);
    }

    public void inAGeExpr40(AGeExpr40 node)
    {
        defaultIn(node);
    }

    public void outAGeExpr40(AGeExpr40 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGeExpr40(AGeExpr40 node)
    {
        inAGeExpr40(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getGe() != null)
        {
            node.getGe().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGeExpr40(node);
    }

    public void inAGtExpr40(AGtExpr40 node)
    {
        defaultIn(node);
    }

    public void outAGtExpr40(AGtExpr40 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtExpr40(AGtExpr40 node)
    {
        inAGtExpr40(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getGt() != null)
        {
            node.getGt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGtExpr40(node);
    }

    public void inAExprExpr40(AExprExpr40 node)
    {
        defaultIn(node);
    }

    public void outAExprExpr40(AExprExpr40 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprExpr40(AExprExpr40 node)
    {
        inAExprExpr40(node);
        if(node.getExpr50() != null)
        {
            node.getExpr50().apply(this);
        }
        outAExprExpr40(node);
    }

    public void inAPlusExpr50(APlusExpr50 node)
    {
        defaultIn(node);
    }

    public void outAPlusExpr50(APlusExpr50 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpr50(APlusExpr50 node)
    {
        inAPlusExpr50(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAPlusExpr50(node);
    }

    public void inAMinusExpr50(AMinusExpr50 node)
    {
        defaultIn(node);
    }

    public void outAMinusExpr50(AMinusExpr50 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExpr50(AMinusExpr50 node)
    {
        inAMinusExpr50(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMinusExpr50(node);
    }

    public void inATermExpr50(ATermExpr50 node)
    {
        defaultIn(node);
    }

    public void outATermExpr50(ATermExpr50 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATermExpr50(ATermExpr50 node)
    {
        inATermExpr50(node);
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        outATermExpr50(node);
    }

    public void inATimesTerm(ATimesTerm node)
    {
        defaultIn(node);
    }

    public void outATimesTerm(ATimesTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATimesTerm(ATimesTerm node)
    {
        inATimesTerm(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getTimes() != null)
        {
            node.getTimes().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outATimesTerm(node);
    }

    public void inADivTerm(ADivTerm node)
    {
        defaultIn(node);
    }

    public void outADivTerm(ADivTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivTerm(ADivTerm node)
    {
        inADivTerm(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outADivTerm(node);
    }

    public void inAModTerm(AModTerm node)
    {
        defaultIn(node);
    }

    public void outAModTerm(AModTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAModTerm(AModTerm node)
    {
        inAModTerm(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMod() != null)
        {
            node.getMod().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAModTerm(node);
    }

    public void inAFactorTerm(AFactorTerm node)
    {
        defaultIn(node);
    }

    public void outAFactorTerm(AFactorTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFactorTerm(AFactorTerm node)
    {
        inAFactorTerm(node);
        if(node.getFactor() != null)
        {
            node.getFactor().apply(this);
        }
        outAFactorTerm(node);
    }

    public void inAPrimaryFactor(APrimaryFactor node)
    {
        defaultIn(node);
    }

    public void outAPrimaryFactor(APrimaryFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrimaryFactor(APrimaryFactor node)
    {
        inAPrimaryFactor(node);
        if(node.getPrimary() != null)
        {
            node.getPrimary().apply(this);
        }
        outAPrimaryFactor(node);
    }

    public void inAIdFactor(AIdFactor node)
    {
        defaultIn(node);
    }

    public void outAIdFactor(AIdFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdFactor(AIdFactor node)
    {
        inAIdFactor(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdFactor(node);
    }

    public void inALengthFactor(ALengthFactor node)
    {
        defaultIn(node);
    }

    public void outALengthFactor(ALengthFactor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALengthFactor(ALengthFactor node)
    {
        inALengthFactor(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
        }
        outALengthFactor(node);
    }

    public void inALength2Factor(ALength2Factor node)
    {
        defaultIn(node);
    }

    public void outALength2Factor(ALength2Factor node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALength2Factor(ALength2Factor node)
    {
        inALength2Factor(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        outALength2Factor(node);
    }

    public void inANewarrayPrimary(ANewarrayPrimary node)
    {
        defaultIn(node);
    }

    public void outANewarrayPrimary(ANewarrayPrimary node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANewarrayPrimary(ANewarrayPrimary node)
    {
        inANewarrayPrimary(node);
        if(node.getNew() != null)
        {
            node.getNew().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLbrack() != null)
        {
            node.getLbrack().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRbrack() != null)
        {
            node.getRbrack().apply(this);
        }
        {
            List<PEmptydim> copy = new ArrayList<PEmptydim>(node.getEmptydim());
            for(PEmptydim e : copy)
            {
                e.apply(this);
            }
        }
        outANewarrayPrimary(node);
    }

    public void inAPrimary2Primary(APrimary2Primary node)
    {
        defaultIn(node);
    }

    public void outAPrimary2Primary(APrimary2Primary node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrimary2Primary(APrimary2Primary node)
    {
        inAPrimary2Primary(node);
        if(node.getPrimary2() != null)
        {
            node.getPrimary2().apply(this);
        }
        outAPrimary2Primary(node);
    }

    public void inAIconstPrimary2(AIconstPrimary2 node)
    {
        defaultIn(node);
    }

    public void outAIconstPrimary2(AIconstPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIconstPrimary2(AIconstPrimary2 node)
    {
        inAIconstPrimary2(node);
        if(node.getIconst() != null)
        {
            node.getIconst().apply(this);
        }
        outAIconstPrimary2(node);
    }

    public void inASconstPrimary2(ASconstPrimary2 node)
    {
        defaultIn(node);
    }

    public void outASconstPrimary2(ASconstPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASconstPrimary2(ASconstPrimary2 node)
    {
        inASconstPrimary2(node);
        if(node.getSconst() != null)
        {
            node.getSconst().apply(this);
        }
        outASconstPrimary2(node);
    }

    public void inANullPrimary2(ANullPrimary2 node)
    {
        defaultIn(node);
    }

    public void outANullPrimary2(ANullPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANullPrimary2(ANullPrimary2 node)
    {
        inANullPrimary2(node);
        if(node.getNull() != null)
        {
            node.getNull().apply(this);
        }
        outANullPrimary2(node);
    }

    public void inATruePrimary2(ATruePrimary2 node)
    {
        defaultIn(node);
    }

    public void outATruePrimary2(ATruePrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATruePrimary2(ATruePrimary2 node)
    {
        inATruePrimary2(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATruePrimary2(node);
    }

    public void inAFalsePrimary2(AFalsePrimary2 node)
    {
        defaultIn(node);
    }

    public void outAFalsePrimary2(AFalsePrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalsePrimary2(AFalsePrimary2 node)
    {
        inAFalsePrimary2(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalsePrimary2(node);
    }

    public void inAParensPrimary2(AParensPrimary2 node)
    {
        defaultIn(node);
    }

    public void outAParensPrimary2(AParensPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParensPrimary2(AParensPrimary2 node)
    {
        inAParensPrimary2(node);
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        outAParensPrimary2(node);
    }

    public void inACallPrimary2(ACallPrimary2 node)
    {
        defaultIn(node);
    }

    public void outACallPrimary2(ACallPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallPrimary2(ACallPrimary2 node)
    {
        inACallPrimary2(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLparen() != null)
        {
            node.getLparen().apply(this);
        }
        if(node.getArglist() != null)
        {
            node.getArglist().apply(this);
        }
        if(node.getRparen() != null)
        {
            node.getRparen().apply(this);
        }
        outACallPrimary2(node);
    }

    public void inAArrayrefPrimary2(AArrayrefPrimary2 node)
    {
        defaultIn(node);
    }

    public void outAArrayrefPrimary2(AArrayrefPrimary2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayrefPrimary2(AArrayrefPrimary2 node)
    {
        inAArrayrefPrimary2(node);
        if(node.getArrayref() != null)
        {
            node.getArrayref().apply(this);
        }
        outAArrayrefPrimary2(node);
    }

    public void inANameArrayref(ANameArrayref node)
    {
        defaultIn(node);
    }

    public void outANameArrayref(ANameArrayref node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANameArrayref(ANameArrayref node)
    {
        inANameArrayref(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLbrack() != null)
        {
            node.getLbrack().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRbrack() != null)
        {
            node.getRbrack().apply(this);
        }
        outANameArrayref(node);
    }

    public void inAPrimaryArrayref(APrimaryArrayref node)
    {
        defaultIn(node);
    }

    public void outAPrimaryArrayref(APrimaryArrayref node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrimaryArrayref(APrimaryArrayref node)
    {
        inAPrimaryArrayref(node);
        if(node.getPrimary2() != null)
        {
            node.getPrimary2().apply(this);
        }
        if(node.getLbrack() != null)
        {
            node.getLbrack().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getRbrack() != null)
        {
            node.getRbrack().apply(this);
        }
        outAPrimaryArrayref(node);
    }

    public void inAIdLhs(AIdLhs node)
    {
        defaultIn(node);
    }

    public void outAIdLhs(AIdLhs node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdLhs(AIdLhs node)
    {
        inAIdLhs(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdLhs(node);
    }

    public void inAArrayrefLhs(AArrayrefLhs node)
    {
        defaultIn(node);
    }

    public void outAArrayrefLhs(AArrayrefLhs node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArrayrefLhs(AArrayrefLhs node)
    {
        inAArrayrefLhs(node);
        if(node.getArrayref() != null)
        {
            node.getArrayref().apply(this);
        }
        outAArrayrefLhs(node);
    }

    public void inAListArglist(AListArglist node)
    {
        defaultIn(node);
    }

    public void outAListArglist(AListArglist node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListArglist(AListArglist node)
    {
        inAListArglist(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        {
            List<PArg> copy = new ArrayList<PArg>(node.getArg());
            for(PArg e : copy)
            {
                e.apply(this);
            }
        }
        outAListArglist(node);
    }

    public void inAArg(AArg node)
    {
        defaultIn(node);
    }

    public void outAArg(AArg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArg(AArg node)
    {
        inAArg(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAArg(node);
    }

    public void inAEmptydim(AEmptydim node)
    {
        defaultIn(node);
    }

    public void outAEmptydim(AEmptydim node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEmptydim(AEmptydim node)
    {
        inAEmptydim(node);
        if(node.getLbrack() != null)
        {
            node.getLbrack().apply(this);
        }
        if(node.getRbrack() != null)
        {
            node.getRbrack().apply(this);
        }
        outAEmptydim(node);
    }
}
