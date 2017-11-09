package buildings.office;

import buildings.*;

import java.io.Serializable;

/**
 * Created by Teomant on 30.09.2017.
 */
public class OfficeFloorNode implements Serializable, Cloneable{
    Floor floor;
    OfficeFloorNode next;
    OfficeFloorNode prev;

    public OfficeFloorNode(Floor floor)
    {
        this.floor=floor;
    }

    public boolean hasNext() {
        return (next!=null);
    }

    public OfficeFloorNode getNext()
    {
        return next;
    }

    public void setNext(OfficeFloorNode next) {this.next=next;}

    public boolean hasPrev() {
        return (prev!=null);
    }

    public OfficeFloorNode getPrev()
    {
        return prev;
    }

    public void setPrev(OfficeFloorNode prev) {this.prev=prev;}

    public void setFloor(Floor floor) {this.floor=(OfficeFloor)floor;}

    public Floor getFloor() {return floor;}

    @Override
    public Object clone(){
        OfficeFloorNode result=null;
        try {
            result = (OfficeFloorNode) super.clone();
            result.floor=(OfficeFloor) floor.clone();
            result.next = (OfficeFloorNode) next.clone();
            result.prev = (OfficeFloorNode) prev.clone();
        } catch (CloneNotSupportedException ex) { }
        return result;
    }
}
