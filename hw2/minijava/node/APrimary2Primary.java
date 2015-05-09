/* This file was generated by SableCC (http://www.sablecc.org/). */

package minijava.node;

import minijava.analysis.*;

@SuppressWarnings("nls")
public final class APrimary2Primary extends PPrimary
{
    private PPrimary2 _primary2_;

    public APrimary2Primary()
    {
        // Constructor
    }

    public APrimary2Primary(
        @SuppressWarnings("hiding") PPrimary2 _primary2_)
    {
        // Constructor
        setPrimary2(_primary2_);

    }

    @Override
    public Object clone()
    {
        return new APrimary2Primary(
            cloneNode(this._primary2_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPrimary2Primary(this);
    }

    public PPrimary2 getPrimary2()
    {
        return this._primary2_;
    }

    public void setPrimary2(PPrimary2 node)
    {
        if(this._primary2_ != null)
        {
            this._primary2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._primary2_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._primary2_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._primary2_ == child)
        {
            this._primary2_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._primary2_ == oldChild)
        {
            setPrimary2((PPrimary2) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}