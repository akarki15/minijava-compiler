/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import minijava.analysis.*;

@SuppressWarnings("nls")
public final class ALengthFactor extends PFactor
{
    private TId _id_;
    private TDot _dot_;
    private TLength _length_;

    public ALengthFactor()
    {
        // Constructor
    }

    public ALengthFactor(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TDot _dot_,
        @SuppressWarnings("hiding") TLength _length_)
    {
        // Constructor
        setId(_id_);

        setDot(_dot_);

        setLength(_length_);

    }

    @Override
    public Object clone()
    {
        return new ALengthFactor(
            cloneNode(this._id_),
            cloneNode(this._dot_),
            cloneNode(this._length_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALengthFactor(this);
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

    public TDot getDot()
    {
        return this._dot_;
    }

    public void setDot(TDot node)
    {
        if(this._dot_ != null)
        {
            this._dot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._dot_ = node;
    }

    public TLength getLength()
    {
        return this._length_;
    }

    public void setLength(TLength node)
    {
        if(this._length_ != null)
        {
            this._length_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._length_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._dot_)
            + toString(this._length_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._dot_ == child)
        {
            this._dot_ = null;
            return;
        }

        if(this._length_ == child)
        {
            this._length_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._dot_ == oldChild)
        {
            setDot((TDot) newChild);
            return;
        }

        if(this._length_ == oldChild)
        {
            setLength((TLength) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
