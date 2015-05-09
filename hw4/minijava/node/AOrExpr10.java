/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import minijava.analysis.*;

@SuppressWarnings("nls")
public final class AOrExpr10 extends PExpr10
{
    private PExpr10 _left_;
    private TOr _or_;
    private PExpr20 _right_;

    public AOrExpr10()
    {
        // Constructor
    }

    public AOrExpr10(
        @SuppressWarnings("hiding") PExpr10 _left_,
        @SuppressWarnings("hiding") TOr _or_,
        @SuppressWarnings("hiding") PExpr20 _right_)
    {
        // Constructor
        setLeft(_left_);

        setOr(_or_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AOrExpr10(
            cloneNode(this._left_),
            cloneNode(this._or_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrExpr10(this);
    }

    public PExpr10 getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExpr10 node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TOr getOr()
    {
        return this._or_;
    }

    public void setOr(TOr node)
    {
        if(this._or_ != null)
        {
            this._or_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._or_ = node;
    }

    public PExpr20 getRight()
    {
        return this._right_;
    }

    public void setRight(PExpr20 node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._or_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._or_ == child)
        {
            this._or_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PExpr10) newChild);
            return;
        }

        if(this._or_ == oldChild)
        {
            setOr((TOr) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PExpr20) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
