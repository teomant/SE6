package buildings.office;

import buildings.*;
import buildings.dwelling.Flat;

/**
 * Created by Teomant on 29.09.2017.
 */
public class Office implements Space, Cloneable {

    private float area;
    private int room_number;
    private static final int DEFAULT_ROOM_NUMBER=1;
    private static final float DEFAULT_AREA=250;


    public Office(){
        this.area=DEFAULT_AREA;
        this.room_number=DEFAULT_ROOM_NUMBER;
    }

    public Office(float area){
        if (area<=0)
        {
            throw new InvalidSpaceAreaException();
        }
        this.area=area;
        this.room_number=DEFAULT_ROOM_NUMBER;
    }

    public Office(float area, int room_number){

        if (room_number<=0)
        {
            throw new InvalidRoomsCountException();
        }
        if (area<=0)
        {
            throw new InvalidSpaceAreaException();
        }
        this.area=Math.abs(area);
        this.room_number=room_number;
    }

    public void setArea (float area){
        if (area<=0)
        {
            throw new InvalidSpaceAreaException();
        }
        this.area=area;
    }

    public void setRoomNumber (int room_number){
        if (room_number<=0)
        {
            throw new InvalidRoomsCountException();
        }
        this.room_number=room_number;
    }

    public float getArea ( ){
        return area;
    }

    public int getRoomNumber ( ){
        return room_number;
    }

    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof Office))
            return false;
        Office e= (Office) object;
        if (e.getArea()==this.getArea()&&e.getRoomNumber()==this.getRoomNumber())
            return true;
        return false;
    }

    @Override
    public int hashCode()
    {
        return (Float.floatToIntBits(this.getArea())^this.getRoomNumber());
    }

    @Override
    public String toString() {
        return ("Office ("+room_number+", "+area+")");
    }

    @Override
    public Object clone(){

        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) { }
        return null;
    }

    @Override
    public int compareTo(Space s)
    {
        if (this.getArea()>s.getArea())
            return 1;
        if (this.getArea()==s.getArea())
            return 0;
        return -1;
    }
}
