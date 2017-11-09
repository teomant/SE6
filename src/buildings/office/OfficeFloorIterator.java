package buildings.office;

import java.util.Iterator;

import buildings.Floor;
import buildings.Space;

public class OfficeFloorIterator implements Iterator {
    private Floor floor;
    private Space current;
    private int currentPosition;

    public OfficeFloorIterator(OfficeFloor floor)
    {
        this.floor=floor;
        this.currentPosition=0;
        current=floor.getSpace(currentPosition);
    }

    public boolean hasNext()
    {
        if (currentPosition<floor.getSpacesNumber())
            return true;
        return false;
    }

    public Space next()
    {
        if (this.hasNext())
        {
            currentPosition=currentPosition+1;
            current=floor.getSpace(currentPosition);
            return current;
        }
        return null;
    }
}
