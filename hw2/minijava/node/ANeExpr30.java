/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import minijava.analysis.*;

@SuppressWarnings("nls")
public final class ANeExpr30 extends PExpr30
{
    private PExpr30 _left_;
    private TNe _ne_;
    private PExpr40 _right_;

    public ANeExpr30()
    {
        // Constructor
    }

    public ANeExpr30(
        @SuppressWarnings("hiding") PExpr30 _left_,
        @SuppressWarnings("hiding") TNe _ne_,
        @SuppressWarnings("hiding") PExpr40 _right_)
    {
        // Constructor
        setLeft(_left_);

        setNe(_ne_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new ANeExpr30(
            cloneNode(this._left_),
            cloneNode(this._ne_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANeExpr30(this);
    }

    public PExpr30 getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExpr30 node)
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

    public TNe getNe()
    {
        return this._ne_;
    }

    public void setNe(TNe node)
    {
        if(this._ne_ != null)
        {
            this._ne_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ne_ = node;
    }

    public PExpr40 getRight()
    {
        return this._right_;
    }

    public void setRight(PExpr40 node)
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
            + toString(this._ne_)
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

        if(this._ne_ == child)
        {
            this._ne_ = null;
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
            setLeft((PExpr30) newChild);
            return;
        }

        if(this._ne_ == oldChild)
        {
            setNe((TNe) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PExpr40) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
