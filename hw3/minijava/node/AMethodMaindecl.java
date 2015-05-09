/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import java.util.*;
import minijava.analysis.*;

@SuppressWarnings("nls")
public final class AMethodMaindecl extends PMaindecl
{
    private PPrivacy _privacy_;
    private TStatic _static_;
    private PType _type_;
    private TId _id_;
    private TLparen _lparen_;
    private PParamlist _paramlist_;
    private TRparen _rparen_;
    private TLbrace _lbrace_;
    private final LinkedList<PStmt> _stmt_ = new LinkedList<PStmt>();
    private TRbrace _rbrace_;

    public AMethodMaindecl()
    {
        // Constructor
    }

    public AMethodMaindecl(
        @SuppressWarnings("hiding") PPrivacy _privacy_,
        @SuppressWarnings("hiding") TStatic _static_,
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TLparen _lparen_,
        @SuppressWarnings("hiding") PParamlist _paramlist_,
        @SuppressWarnings("hiding") TRparen _rparen_,
        @SuppressWarnings("hiding") TLbrace _lbrace_,
        @SuppressWarnings("hiding") List<PStmt> _stmt_,
        @SuppressWarnings("hiding") TRbrace _rbrace_)
    {
        // Constructor
        setPrivacy(_privacy_);

        setStatic(_static_);

        setType(_type_);

        setId(_id_);

        setLparen(_lparen_);

        setParamlist(_paramlist_);

        setRparen(_rparen_);

        setLbrace(_lbrace_);

        setStmt(_stmt_);

        setRbrace(_rbrace_);

    }

    @Override
    public Object clone()
    {
        return new AMethodMaindecl(
            cloneNode(this._privacy_),
            cloneNode(this._static_),
            cloneNode(this._type_),
            cloneNode(this._id_),
            cloneNode(this._lparen_),
            cloneNode(this._paramlist_),
            cloneNode(this._rparen_),
            cloneNode(this._lbrace_),
            cloneList(this._stmt_),
            cloneNode(this._rbrace_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMethodMaindecl(this);
    }

    public PPrivacy getPrivacy()
    {
        return this._privacy_;
    }

    public void setPrivacy(PPrivacy node)
    {
        if(this._privacy_ != null)
        {
            this._privacy_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._privacy_ = node;
    }

    public TStatic getStatic()
    {
        return this._static_;
    }

    public void setStatic(TStatic node)
    {
        if(this._static_ != null)
        {
            this._static_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._static_ = node;
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TLparen getLparen()
    {
        return this._lparen_;
    }

    public void setLparen(TLparen node)
    {
        if(this._lparen_ != null)
        {
            this._lparen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lparen_ = node;
    }

    public PParamlist getParamlist()
    {
        return this._paramlist_;
    }

    public void setParamlist(PParamlist node)
    {
        if(this._paramlist_ != null)
        {
            this._paramlist_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._paramlist_ = node;
    }

    public TRparen getRparen()
    {
        return this._rparen_;
    }

    public void setRparen(TRparen node)
    {
        if(this._rparen_ != null)
        {
            this._rparen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rparen_ = node;
    }

    public TLbrace getLbrace()
    {
        return this._lbrace_;
    }

    public void setLbrace(TLbrace node)
    {
        if(this._lbrace_ != null)
        {
            this._lbrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lbrace_ = node;
    }

    public LinkedList<PStmt> getStmt()
    {
        return this._stmt_;
    }

    public void setStmt(List<PStmt> list)
    {
        this._stmt_.clear();
        this._stmt_.addAll(list);
        for(PStmt e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public TRbrace getRbrace()
    {
        return this._rbrace_;
    }

    public void setRbrace(TRbrace node)
    {
        if(this._rbrace_ != null)
        {
            this._rbrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);       
            }

            node.parent(this);
        }

        this._rbrace_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._privacy_)
            + toString(this._static_)
            + toString(this._type_)
            + toString(this._id_)
            + toString(this._lparen_)
            + toString(this._paramlist_)
            + toString(this._rparen_)
            + toString(this._lbrace_)
            + toString(this._stmt_)
            + toString(this._rbrace_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._privacy_ == child)
        {
            this._privacy_ = null;
            return;
        }

        if(this._static_ == child)
        {
            this._static_ = null;
            return;
        }

        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._lparen_ == child)
        {
            this._lparen_ = null;
            return;
        }

        if(this._paramlist_ == child)
        {
            this._paramlist_ = null;
            return;
        }

        if(this._rparen_ == child)
        {
            this._rparen_ = null;
            return;
        }

        if(this._lbrace_ == child)
        {
            this._lbrace_ = null;
            return;
        }

        if(this._stmt_.remove(child))
        {
            return;
        }

        if(this._rbrace_ == child)
        {
            this._rbrace_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._privacy_ == oldChild)
        {
            setPrivacy((PPrivacy) newChild);
            return;
        }

        if(this._static_ == oldChild)
        {
            setStatic((TStatic) newChild);
            return;
        }

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._lparen_ == oldChild)
        {
            setLparen((TLparen) newChild);
            return;
        }

        if(this._paramlist_ == oldChild)
        {
            setParamlist((PParamlist) newChild);
            return;
        }

        if(this._rparen_ == oldChild)
        {
            setRparen((TRparen) newChild);
            return;
        }

        if(this._lbrace_ == oldChild)
        {
            setLbrace((TLbrace) newChild);
            return;
        }

        for(ListIterator<PStmt> i = this._stmt_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStmt) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._rbrace_ == oldChild)
        {
            setRbrace((TRbrace) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
