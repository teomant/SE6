package buildings.office;

import buildings.*;

import java.io.Serializable;

/**
 * Created by Teomant on 30.09.2017.
 */
public class OfficeNode implements Serializable, Cloneable {
    private Space office;
    private OfficeNode next;

    OfficeNode(Space office)
    {
        this.office=office;
    }

    public boolean hasNext() {
        return (next!=null);
    }

    public OfficeNode getNext()
    {
        return next;
    }

    public void setNext(OfficeNode next) {this.next=next;}

    public void setOffice(Space office) {this.office=office;}

    public Space getOffice() {return office;}

    @Override
    public Object clone(){
        OfficeNode result=null;
        try {
            result = (OfficeNode) super.clone();
            result.office=(Office) office.clone();
            //result.next = (OfficeNode) next.clone();
        } catch (CloneNotSupportedException ex) { }
        return result;
    }
}
